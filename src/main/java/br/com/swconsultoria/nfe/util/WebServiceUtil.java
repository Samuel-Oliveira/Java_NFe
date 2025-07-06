/**
 *
 */
package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import lombok.extern.java.Log;

import java.io.*;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Samuel Oliveira
 *
 * Classe responsávelem montar as URL's de consulta de serviços do SEFAZ.
 */
@Log
public class WebServiceUtil {

    private final static Logger logger = Logger.getLogger(WebServiceUtil.class.getName());
    private static final Pattern sectionPattern = Pattern.compile("^\\[(.+)\\]$");

    /**
     * Obtém um valor de um Mapa que representa uma seção de um arquivo INI,
     * buscando pela {@code targetKey} de forma case-insensitive.
     * Este método também normaliza as chaves lidas do mapa (que vêm do arquivo INI)
     * que contêm "..", substituindo por ".", antes de realizar a comparação case-insensitive.
     *
     * @param sectionMap O Mapa ({@code Map<String, String>}) contendo os pares de chave-valor da seção específica.
     *                   Pode ser nulo ou vazio.
     * @param targetKey A chave alvo (geralmente esperada em lowercase, vinda de {@code ServicosEnum}, ou "Usar" em PascalCase)
     *                  a ser buscada dentro da seção.
     * @param logger O logger para registrar informações de depuração (ex: qual chave está sendo comparada).
     * @return O valor da propriedade como String, se uma correspondência case-insensitive for encontrada;
     *         {@code null} caso contrário, ou se {@code sectionMap} for nulo/vazio, ou se {@code targetKey} for nula.
     */
    private static String getIniValueIgnoreCase(Map<String, String> sectionMap, String targetKey, Logger logger) {
        if (sectionMap == null || sectionMap.isEmpty() || targetKey == null) {
            return null;
        }
        for (Map.Entry<String, String> entry : sectionMap.entrySet()) {
            String keyFromIni = entry.getKey();
            String normalizedKeyFromIni = keyFromIni.replace("..", ".");
            String normalizedTargetKey = targetKey.replace("..", ".");
            if (normalizedTargetKey.equalsIgnoreCase(normalizedKeyFromIni)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Analisa (parse) um arquivo INI a partir de um {@link InputStream} e o carrega em uma estrutura de dados aninhada de Mapas.
     * O método lê o stream linha por linha, identificando seções (ex: {@code [NomeDaSecao]}),
     * pares de chave-valor (ex: {@code chave=valor} ou {@code chave:valor}), e linhas de comentário (iniciadas com ';' ou '#').
     * Espaços em branco ao redor de nomes de seção, chaves e valores são removidos (trim).
     * As seções e chaves são armazenadas preservando o case original do arquivo.
     *
     * @param inputStream O {@link InputStream} do arquivo INI a ser analisado. O stream é fechado ao final do parsing.
     * @return Um {@code Map<String, Map<String, String>>} representando os dados do INI.
     *         A chave do mapa externo é o nome da seção. O valor é outro mapa contendo
     *         os pares de chave-valor daquela seção.
     * @throws IOException Se ocorrer um erro de I/O durante a leitura do stream.
     * @throws NfeException Se forem encontradas linhas malformadas que não se encaixam no padrão esperado
     *                      de seção ou chave-valor (ex: nome de seção vazio em {@code []}, ou uma chave-valor fora de uma seção).
     */
    private static Map<String, Map<String, String>> parseIniFile(InputStream inputStream) throws IOException, NfeException {
        Map<String, Map<String, String>> iniData = new HashMap<>();
        String currentSectionName = null;
        Map<String, String> currentSectionMap = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith(";") || line.startsWith("#")) {
                    continue; // Skip empty lines and comments
                }

                Matcher sectionMatcher = sectionPattern.matcher(line);
                if (sectionMatcher.matches()) {
                    // If currentSectionMap is not null and not empty, it means a previous section was being processed.
                    // It's already in iniData, as we put it there when its name was found.
                    currentSectionName = sectionMatcher.group(1).trim();
                    if (currentSectionName.isEmpty()) {
                        throw new NfeException("Nome da seção inválido (vazio) no arquivo INI.");
                    }
                    // Ensure new section map is created, even if previous one with same name existed (though INI typically doesn't repeat sections)
                    currentSectionMap = new HashMap<>();
                    iniData.put(currentSectionName, currentSectionMap);
                } else {
                    if (currentSectionName == null) {
                        // Property outside of any section - not expected for WebServicesNfe.ini
                        // For now, we can log and ignore, or throw an exception.
                        // Based on prompt, let's be strict for this specific INI structure.
                        throw new NfeException("Propriedade encontrada fora de uma seção: " + line);
                    }

                    int separatorPos = -1;
                    int equalsPos = line.indexOf('=');
                    // According to INI standards, some parsers also accept ':' but '=' is more common.
                    // The original ini4j might have handled both. For this custom parser, let's stick to '=' for simplicity
                    // unless ':' is confirmed to be used in WebServicesNfe.ini for key-value.
                    // A quick check of WebServicesNfe.ini shows only '='.
                    separatorPos = equalsPos;

                    if (separatorPos != -1) {
                        String key = line.substring(0, separatorPos).trim();
                        String value = line.substring(separatorPos + 1).trim();
                        if (!key.isEmpty() && currentSectionMap != null) {
                            currentSectionMap.put(key, value);
                        } else if (key.isEmpty()){
                            logger.warning("Linha malformada (chave vazia): " + line);
                        } else {
                            // currentSectionMap should not be null here if currentSectionName is set.
                            // This case implies currentSectionName was set, but currentSectionMap wasn't put in iniData or was null.
                            // This should ideally not happen if logic is correct.
                            logger.warning("Tentativa de adicionar propriedade a uma seção nula: " + line);
                        }
                    } else {
                        // Line is not a comment, not a section, and not a valid key-value pair.
                        logger.warning("Linha malformada ignorada: " + line);
                    }
                }
            }
        }
        return iniData;
    }


    /**
     * Retorna a URL para consulta de operações do SEFAZ.<br>
     *
     * <p>
     * O método carrega o arquivo <b>WebServicesNfe.ini</b> (utilizando um parser customizado)
     * que contêm as URL's de operações do SEFAZ, busca pela seção no arquivo .ini que
     * corresponda com os argumentos <b>tipo</b>, <b>config</b>, <b>servico</b>
     * e retorna essa URL.
     * </p>
     *
     * @param config interface que contêm os dados necessários para a comunicação.
     * @param tipoDocumento {@link DocumentoEnum#NFE} ou {@link DocumentoEnum#NFCE}.
     * @param tipoServico é a operação que se deseja fazer.<br>
     * Ex.: para consultas status deserviço no ambiente de produção
     * use ServicosEnum.NfeStatusServico_4.00
     *
     * @return url String que representa a URL do serviço.
     * @throws NfeException
     *
     * @see ConfiguracoesNfe
     */
    public static String getUrl(ConfiguracoesNfe config, DocumentoEnum tipoDocumento, ServicosEnum tipoServico) throws NfeException {
        InputStream is = null;
        Map<String, Map<String, String>> iniData;
        try {
            if (ObjetoUtil.verifica(config.getArquivoWebService()).isPresent()) {
                File arquivo = new File(config.getArquivoWebService());
                if (!arquivo.exists()) {
                    throw new FileNotFoundException("Arquivo WebService " + config.getArquivoWebService() + " não encontrado");
                }
                is = new FileInputStream(arquivo);
                logger.info("[ARQUIVO INI CUSTOMIZADO]: " + config.getArquivoWebService());
            } else {
                is = WebServiceUtil.class.getResourceAsStream("/WebServicesNfe.ini");
                if (is == null) {
                    throw new NfeException("Arquivo WebServicesNfe.ini não encontrado no classpath.");
                }
            }
            iniData = parseIniFile(is);
        } catch (IOException e) {
            throw new NfeException("Erro ao carregar arquivo de configuração WebService: " + e.getMessage(), e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.fine("Erro ao fechar InputStream: " + e.getMessage());
                }
            }
        }

        String initialSecaoKey = tipoDocumento.getTipo() + "_" + config.getEstado() + "_"
                + (config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "H" : "P");

        String lookupSectionKey = initialSecaoKey;
        Map<String, String> initialSectionMap = iniData.get(initialSecaoKey);
        // Pass the static logger from the class to the helper method
        String usarValue = getIniValueIgnoreCase(initialSectionMap, "Usar", logger);

        String finalUrl = null;

        if (tipoServico.equals(ServicosEnum.CONSULTA_CADASTRO) && (
                config.getEstado().equals(EstadosEnum.PA) ||
                        config.getEstado().equals(EstadosEnum.AM) ||
                        config.getEstado().equals(EstadosEnum.AL) ||
                        config.getEstado().equals(EstadosEnum.AP) ||
                        config.getEstado().equals(EstadosEnum.DF) ||
                        config.getEstado().equals(EstadosEnum.PI) ||
                        config.getEstado().equals(EstadosEnum.RJ) ||
                        config.getEstado().equals(EstadosEnum.RO) ||
                        config.getEstado().equals(EstadosEnum.SE) ||
                        config.getEstado().equals(EstadosEnum.TO))) {
            throw new NfeException("Estado não possui Consulta Cadastro.");
        } else if (tipoServico.equals(ServicosEnum.DISTRIBUICAO_DFE) ||
                tipoServico.equals(ServicosEnum.MANIFESTACAO) ||
                tipoServico.equals(ServicosEnum.EPEC)) {
            lookupSectionKey = config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "NFe_AN_H" : "NFe_AN_P";
            Map<String, String> nationalSectionMap = iniData.get(lookupSectionKey);
            finalUrl = getIniValueIgnoreCase(nationalSectionMap, tipoServico.getServico(), logger);
        } else if (!tipoServico.equals(ServicosEnum.URL_CONSULTANFCE) &&
                !tipoServico.equals(ServicosEnum.URL_QRCODE) &&
                config.isContigenciaSVC() && tipoDocumento.equals(DocumentoEnum.NFE)) {
            if (config.getEstado().equals(EstadosEnum.GO) || config.getEstado().equals(EstadosEnum.AM) ||
                    config.getEstado().equals(EstadosEnum.BA) || config.getEstado().equals(EstadosEnum.CE) ||
                    config.getEstado().equals(EstadosEnum.MA) || config.getEstado().equals(EstadosEnum.MS) ||
                    config.getEstado().equals(EstadosEnum.MT) || config.getEstado().equals(EstadosEnum.PA) ||
                    config.getEstado().equals(EstadosEnum.PE) || config.getEstado().equals(EstadosEnum.PI) ||
                    config.getEstado().equals(EstadosEnum.PR)) {
                lookupSectionKey = tipoDocumento.getTipo() + "_SVRS_" + (config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "H" : "P");
            } else {
                lookupSectionKey = tipoDocumento.getTipo() + "_SVC-AN_" + (config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "H" : "P");
            }
            Map<String, String> svcSectionMap = iniData.get(lookupSectionKey);
            finalUrl = getIniValueIgnoreCase(svcSectionMap, tipoServico.getServico(), logger);
        } else if (ObjetoUtil.verifica(usarValue).isPresent() &&
                !tipoServico.equals(ServicosEnum.URL_CONSULTANFCE) &&
                !tipoServico.equals(ServicosEnum.URL_QRCODE)) {
            lookupSectionKey = usarValue;
            Map<String, String> usarRedirectedSectionMap = iniData.get(lookupSectionKey);
            finalUrl = getIniValueIgnoreCase(usarRedirectedSectionMap, tipoServico.getServico(), logger);
        } else {
            Map<String, String> currentSectionMap = iniData.get(lookupSectionKey);
            finalUrl = getIniValueIgnoreCase(currentSectionMap, tipoServico.getServico(), logger);
        }

        final String finalLookupSectionKeyForLambda = lookupSectionKey;
        ObjetoUtil.verifica(finalUrl).orElseThrow(() -> new NfeException(
                "WebService de " + tipoServico + " não encontrado para " + config.getEstado().getNome() + " na seção " + finalLookupSectionKeyForLambda));

        logger.info("[URL]: " + tipoServico + ": " + finalUrl);
        return finalUrl;
    }
}
