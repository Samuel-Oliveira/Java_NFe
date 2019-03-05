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
import org.ini4j.Wini;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * @author Samuel Oliveira
 *
 * Classe responsávelem montar as URL's de consulta de serviços do SEFAZ.
 */
public class WebServiceUtil {

    private final static Logger logger = Logger.getLogger(WebServiceUtil.class.getName());

    /**
     * Retorna a URL para consulta de operações do SEFAZ.<br>
     *
     * <p>
     * O método carrega o arquivo <b>WebServicesNfe.ini</b> que contêm as
     * URL's de operações do SEFAZ, busca pela seção no arquivo .ini que
     * corresponda com os argumentos <b>tipo</b>, <b>config</b>, <b>servico</b>
     * e retorna essa URL.
     * </p>
     *
     * @param config interface que contêm os dados necessários para a comunicação.
     * @param tipoDocumento DocumentoEnum.NFE e ConstantesUtil.NFCE
     * @param tipoServico é a operação que se deseja fazer.<br>
     * Ex.: para consultas status deserviço no ambiente de produção
     * use ServicosEnum.NfeStatusServico_4.00
     *
     * @return url String que representa a URL do serviço.
     * @throws NfeException
     *
     * @see ConfiguracoesNfe
     * @see ConstantesUtil
     **/
    public static String getUrl(ConfiguracoesNfe config, DocumentoEnum tipoDocumento, ServicosEnum tipoServico) throws NfeException {

        try {

            String secao = tipoDocumento.getTipo() + "_" + config.getEstado() + "_"
                    + (config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "H" : "P");

            InputStream is = WebServiceUtil.class.getResourceAsStream("/WebServicesNfe.ini");

            Wini ini = new Wini();
            ini.getConfig().setLowerCaseOption(true);
            ini.load(is);
            String url = ini.get(secao, "usar");

            // URLS CONSULTA CADASTO
            if (tipoServico.equals(ServicosEnum.CONSULTA_CADASTRO)) {
                if (config.getEstado().equals(EstadosEnum.AC) || config.getEstado().equals(EstadosEnum.RN)
                        || config.getEstado().equals(EstadosEnum.PB) || config.getEstado().equals(EstadosEnum.SC)) {
                    secao = DocumentoEnum.NFE.getTipo() + "_SVRS_"
                            + (config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "H" : "P");
                } else {

                    secao = DocumentoEnum.NFE.getTipo() + "_" + config.getEstado() + "_"
                            + (config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "H" : "P");
                }
            // URLS de ambiente nacional
            }else if (tipoServico.equals(ServicosEnum.DISTRIBUICAO_DFE)
                    || tipoServico.equals(ServicosEnum.MANIFESTACAO)) {
                secao = config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "NFe_AN_H" : "NFe_AN_P";

            } else if (!tipoServico.equals(ServicosEnum.URL_CONSULTANFCE)
                    && !tipoServico.equals(ServicosEnum.URL_QRCODE) && ObjetoUtil.verifica(url).isPresent()) {
                secao = url;
            } else if (config.isContigenciaSCAN()) {
                // SVC-RS
                if (config.getEstado().equals(EstadosEnum.GO) || config.getEstado().equals(EstadosEnum.AM)
                        || config.getEstado().equals(EstadosEnum.BA) || config.getEstado().equals(EstadosEnum.CE)
                        || config.getEstado().equals(EstadosEnum.MA) || config.getEstado().equals(EstadosEnum.MS)
                        || config.getEstado().equals(EstadosEnum.MT) || config.getEstado().equals(EstadosEnum.PA)
                        || config.getEstado().equals(EstadosEnum.PE) || config.getEstado().equals(EstadosEnum.PI)
                        || config.getEstado().equals(EstadosEnum.PR)) {
                    secao = tipoDocumento.getTipo() + "_SVRS_"
                            + (config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "H" : "P");
                    // SVC-AN
                } else {
                    secao = tipoDocumento.getTipo() + "_SVC-AN_"
                            + (config.getAmbiente().equals(AmbienteEnum.HOMOLOGACAO) ? "H" : "P");
                }
            }

            url = ini.get(secao, tipoServico.getServico().toLowerCase());

            ObjetoUtil.verifica(url).orElseThrow(() -> new NfeException(
                    "WebService de " + tipoServico + " não encontrado para " + config.getEstado().getNome()));

            LoggerUtil.log(WebServiceUtil.class,"[URL]: " +tipoServico+": " + url);

            return url;

        } catch (IOException e) {
            throw new NfeException(e.getMessage());
        }

    }
}
