/**
 * 
 */
package br.com.samuelweb.nfe.util;

import br.com.samuelweb.nfe.dom.ConfiguracoesNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import org.ini4j.Wini;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Samuel Oliveira
 *
 * Classe responsávelem montar a URL de consulta de serviços do SEFAZ.
 */
public class WebServiceUtil {

    /**
     * Retorna a URL para consulta de operações do SEFAZ.<p>
     * 
     * O método carrega o arquivo <b>WebServicesNfe.ini</b> que contêm as 
     * URL's de operações do SEFAZ, busca pela seção no arquivo .ini que 
     * corresponda com os argumentos <b>tipo</b>, <b>config</b>, <b>servico</b> 
     * e retorna essa URL.<p>
     * 
     * @param config interface que contêm os dados necessários para a comunicação.
     * @param tipo ConstantesUtil.NFE e ConstantesUtil.NFCE
     * @param servico é a operação que se deseja fazer.<p> 
     * Ex.: para consultas status deserviço no ambiente de produção
     * use NfeStatusServico_4.00
     * @return url String que representa a URL do serviço.
     * @throws NfeException 
     * 
     * @see ConfiguracoesNfe
     * @see ConstantesUtil
     **/
    public static String getUrl(ConfiguracoesNfe config, String tipo, String servico) throws NfeException {

        try {

            String secao = tipo + "_" + config.getEstado() + "_"
                    + (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");

            InputStream is = WebServiceUtil.class.getResourceAsStream("/WebServicesNfe.ini");

            Wini ini = new Wini();
            ini.getConfig().setLowerCaseOption(true);
            ini.load(is);
            String url = ini.get(secao, "usar");

            if (servico.equals(ConstantesUtil.SERVICOS.DISTRIBUICAO_DFE)
                    || servico.equals(ConstantesUtil.SERVICOS.MANIFESTACAO)) {
                secao = config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "NFe_AN_H" : "NFe_AN_P";

                if (servico.equals(ConstantesUtil.SERVICOS.MANIFESTACAO)) {
                    servico = ConstantesUtil.SERVICOS.EVENTO;
                }

            } else if (!servico.equals(ConstantesUtil.SERVICOS.URL_CONSULTANFCE)
                    && !servico.equals(ConstantesUtil.SERVICOS.URL_QRCODE) && ObjetoUtil.differentNull(url)) {
                secao = url;
            } else if (config.isContigenciaSCAN()) {
                // SVC-RS
                if (config.getEstado().equals(Estados.GO) || config.getEstado().equals(Estados.AM)
                        || config.getEstado().equals(Estados.BA) || config.getEstado().equals(Estados.CE)
                        || config.getEstado().equals(Estados.MA) || config.getEstado().equals(Estados.MS)
                        || config.getEstado().equals(Estados.MT) || config.getEstado().equals(Estados.PA)
                        || config.getEstado().equals(Estados.PE) || config.getEstado().equals(Estados.PI)
                        || config.getEstado().equals(Estados.PR)) {
                    secao = tipo + "_SVRS_"
                            + (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");
                    // SVC-AN
                } else {
                    secao = tipo + "_SVAN_"
                            + (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");
                }
            }

            url = ini.get(secao, servico.toLowerCase());

            if (ObjetoUtil.isEmpty(url)) {
                throw new NfeException(
                        "WebService de " + servico + " não encontrado para " + config.getEstado().getNome());
            }

            if (config.isLog()) {
                System.out.println("WebService - " + url);
            }
            return url;

        } catch (IOException e) {
            throw new NfeException(e.getMessage());
        }

    }

	public static String getUrlConsultaCadastro(ConfiguracoesNfe config, String uf) throws NfeException {

		String tipo = ConstantesUtil.NFE;
		String servico = ConstantesUtil.SERVICOS.CONSULTA_CADASTRO;
		try {

            String secao;
            if (uf.toUpperCase().equals(Estados.AC.toString()) || uf.toUpperCase().equals(Estados.RN.toString())
                    || uf.toUpperCase().equals(Estados.PB.toString()) || uf.toUpperCase().equals(Estados.SC.toString())) {
                secao = tipo + "_SVRS_"
                        + (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");
            } else {

                secao = tipo + "_" + uf.toUpperCase() + "_"
                        + (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");
            }

			InputStream is = WebServiceUtil.class.getResourceAsStream("/WebServicesNfe.ini");

			Wini ini = new Wini();
			ini.getConfig().setLowerCaseOption(true);
			ini.load(is);
			String url = ini.get(secao, servico.toLowerCase());

			if (ObjetoUtil.isEmpty(url)) {
				throw new NfeException("WebService de " + servico + " não encontrado para " + uf);
			}

			if (config.isLog()) {
				System.out.println("WebService - " + url);
			}
			return url;

		} catch (IOException e) {
			throw new NfeException(e.getMessage());
		}

	}

}
