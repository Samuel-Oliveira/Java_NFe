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
import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.*;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * @author Samuel Oliveira
 *
 * Classe responsávelem montar as URL's de consulta de serviços do SEFAZ.
 */
@Log
public class WebServiceUtil {

    private final static Logger logger = Logger.getLogger(WebServiceUtil.class.getName());

    /**
     * Carrega o arquivo de configuração INI (WebServicesNfe.ini) em um objeto INIConfiguration.
     * Prioriza um arquivo customizado especificado em {@code config.getArquivoWebService()},
     * caso contrário, carrega o arquivo padrão a partir dos recursos da aplicação.
     *
     * @param config As configurações da NFe, contendo potencialmente o caminho para um arquivo INI customizado.
     * @param logger O logger para registrar informações (ex: uso de arquivo customizado).
     * @return Um objeto {@link INIConfiguration} carregado com os dados do arquivo INI.
     * @throws NfeException Se ocorrer um erro ao carregar ou parsear o arquivo INI (e.g., {@link FileNotFoundException},
     *                      {@link ConfigurationException}, {@link IOException}).
     */
    private static INIConfiguration loadIniConfiguration(ConfiguracoesNfe config) throws NfeException {
        // NOTE: The logger parameter is not used in the provided implementation snippet for loadIniConfiguration,
        // but it's included in the Javadoc and signature as per the prompt.
        // The existing static 'logger' of WebServiceUtil class is used for log.info and log.fine.
        InputStream is;
        try {
            if (ObjetoUtil.verifica(config.getArquivoWebService()).isPresent()) {
                File arquivo = new File(config.getArquivoWebService());
                if (!arquivo.exists()) {
                    throw new FileNotFoundException("Arquivo WebService " + config.getArquivoWebService() + " não encontrado");
                }
                is = Files.newInputStream(arquivo.toPath());
                logger.info("[ARQUIVO INI CUSTOMIZADO]: " + config.getArquivoWebService());
            } else {
                is = WebServiceUtil.class.getResourceAsStream("/WebServicesNfe.ini");
                if (is == null) {
                    throw new NfeException("Arquivo WebServicesNfe.ini não encontrado no classpath.");
                }
            }

            INIConfiguration iniConfig = new INIConfiguration();
            try (InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                iniConfig.read(reader);
            } finally {
                // InputStreamReader typically closes the underlying stream, but a redundant close is safe.
                try {
                    is.close();
                } catch (IOException e) {
                    logger.fine("Error closing InputStream: " + e.getMessage());
                }
            }
            return iniConfig;
        } catch (IOException | ConfigurationException e) {
            throw new NfeException("Erro ao carregar arquivo de configuração WebService: " + e.getMessage(), e);
        }
    }

    /**
     * Determina a chave da seção correta a ser utilizada para la consulta de URLs no arquivo INI.
     * Esta lógica considera o estado (UF), ambiente (produção/homologação), tipo de documento (NFe/NFCe),
     * o serviço desejado, e possíveis redirecionamentos pela chave "Usar" dentro das seções,
     * além de tratar casos específicos para serviços nacionais (AN) e contingências (SVC).
     *
     * @param iniConfig O objeto {@link INIConfiguration} já carregado.
     * @param config As configurações gerais da NFe.
     * @param tipoDocumento O tipo de documento fiscal (NFe ou NFCe).
     * @param tipoServico O serviço específico para o qual a URL está sendo buscada.
     * @param initialSecao A chave da seção inicial, calculada com base no tipo de documento, UF e ambiente.
     * @param logger O logger para registrar informações.
     * @return A string representando a chave final da seção a ser usada para la consulta da URL do serviço.
     * @throws NfeException Em casos específicos, como "Estado não possui Consulta Cadastro".
     */
    private static String determineLookupSectionKey(INIConfiguration iniConfig, ConfiguracoesNfe config, DocumentoEnum tipoDocumento, ServicosEnum tipoServico, String initialSecao) throws NfeException {
        // NOTE: The logger parameter is not used in the provided implementation snippet for determineLookupSectionKey,
        // but it's included in the Javadoc and signature as per the prompt.
        String lookupSectionKey = initialSecao;

        SubnodeConfiguration initialSectionConfig = iniConfig.getSection(initialSecao);
        String usarValue = null;
        if (initialSectionConfig != null && !initialSectionConfig.isEmpty()) {
            usarValue = getStringIgnoreCase(initialSectionConfig, "Usar");
        }

        // Logic to determine the final 'lookupSectionKey'
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
        } else if (ObjetoUtil.verifica(usarValue).isPresent() &&
                !tipoServico.equals(ServicosEnum.URL_CONSULTANFCE) &&
                !tipoServico.equals(ServicosEnum.URL_QRCODE)) {
            lookupSectionKey = usarValue;
        }
        // If none of the above, lookupSectionKey remains initialSecao
        return lookupSectionKey;
    }

    /**
     * Obtém um valor de uma {@link SubnodeConfiguration} (representando uma seção do INI)
     * buscando pela {@code targetKey} de forma case-insensitive.
     * Este método também normaliza as chaves lidas do INI que contêm "..", substituindo por ".",
     * antes de realizar a comparação case-insensitive.
     *
     * @param sectionConfig A configuração da seção específica onde a chave será procurada.
     * @param targetKey A chave alvo (esperada em lowercase, vinda de {@code ServicosEnum}) a ser buscada.
     * @return O valor da propriedade como String, se encontrada; {@code null} caso contrário,
     *         ou se {@code sectionConfig} for nulo/vazio, ou se {@code targetKey} for nula.
     */
    private static String getStringIgnoreCase(SubnodeConfiguration sectionConfig, String targetKey) {
        if (sectionConfig == null || sectionConfig.isEmpty() || targetKey == null) {
            return null;
        }
        // First, try direct access with the targetKey, relying on SubnodeConfiguration's case-insensitivity
        String value = sectionConfig.getString(targetKey, null);
        if (value != null) {
            return value;
        }

        // If direct access failed, iterate and check with equalsIgnoreCase
        for (Iterator<String> it = sectionConfig.getKeys(); it.hasNext(); ) {
            String key = it.next();
            String normalizedIteratedKey = key.replace("..", "."); // Normalize ".." to "." from iterated key
            if (targetKey.equalsIgnoreCase(normalizedIteratedKey)) {
                return sectionConfig.getString(key); // Use original iterated key
            }
        }
        return null;
    }

    public static String getUrl(ConfiguracoesNfe config, DocumentoEnum tipoDocumento, ServicosEnum tipoServico) throws NfeException {
        String initialSecao = tipoDocumento.getTipo() + "_" + config.getEstado() + "_"
                + (config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "H" : "P");

        INIConfiguration iniConfig = loadIniConfiguration(config); // logger is not passed as it uses the static class logger

        String lookupSectionKey = determineLookupSectionKey(iniConfig, config, tipoDocumento, tipoServico, initialSecao); // logger is not passed

        SubnodeConfiguration finalSectionConfig = iniConfig.getSection(lookupSectionKey);
        String finalUrl = getStringIgnoreCase(finalSectionConfig, tipoServico.getServico());

        final String finalSectionKeyForLambda = lookupSectionKey; // Essential for lambda
        ObjetoUtil.verifica(finalUrl).orElseThrow(() -> new NfeException(
                "WebService de " + tipoServico + " não encontrado para " + config.getEstado().getNome() + " na seção " + finalSectionKeyForLambda));

        logger.info("[URL]: " + tipoServico + ": " + finalUrl);
        return finalUrl;
    }
}
