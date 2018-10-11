/**
 * 
 */
package br.com.samuelweb.nfe.util;

import br.com.samuelweb.nfe.dom.ConfiguracoesNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil.TipoDoc_e;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Samuel Oliveira
 *
 */
public class WebServiceUtil {

	public static String getUrl(ConfiguracoesNfe config, String tipo, String servico) throws NfeException {
		return getUrl(config, ConstantesUtil.TipoDoc_e.valueOf(tipo.toUpperCase()), ConstantesUtil.SERVICOS.valueOf(servico.toUpperCase()));
	}
	
	public static String getUrl(ConfiguracoesNfe config, TipoDoc_e tipo, ConstantesUtil.SERVICOS servico) throws NfeException {

		try {

			String secao = tipo.getTipo() + "." + config.getEstado() + "."
					+ (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");

			InputStream is = WebServiceUtil.class.getResourceAsStream("/WebServicesNfe.properties");
			Properties prop = new Properties();			
			prop.load(is);
			
			String url = prop.getProperty(secao+".Usar");

			if (servico.equals(ConstantesUtil.SERVICOS.DISTRIBUICAO_DFE)
					|| servico.equals(ConstantesUtil.SERVICOS.MANIFESTACAO)) {
				secao = config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "NFe.AN.H" : "NFe.AN.P";

				if (servico.equals(ConstantesUtil.SERVICOS.MANIFESTACAO)) {
					servico = ConstantesUtil.SERVICOS.EVENTO;
				}
				
			// codigo abaixo foi comentado pq quando for buscar a url preciso da secao original, ver comentario mais abaixo	
			//} else if (!servico.equals(ConstantesUtil.SERVICOS.URL_CONSULTANFCE)
			//		&& !servico.equals(ConstantesUtil.SERVICOS.URL_QRCODE) && ObjetoUtil.differentNull(url)) {
			//	secao = url;
				
			} else if (config.isContigenciaSCAN()) {
				// SVC-RS
				if (config.getEstado().equals(Estados.GO) || config.getEstado().equals(Estados.AM)
						|| config.getEstado().equals(Estados.BA) || config.getEstado().equals(Estados.CE)
						|| config.getEstado().equals(Estados.MA) || config.getEstado().equals(Estados.MS)
						|| config.getEstado().equals(Estados.MT) || config.getEstado().equals(Estados.PA)
						|| config.getEstado().equals(Estados.PE) || config.getEstado().equals(Estados.PI)
						|| config.getEstado().equals(Estados.PR)) {
					secao = tipo + ".SVRS."
							+ (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");
					// SVC-AN
				} else {
					secao = tipo + ".SVAN."
							+ (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");
				}
			}
			//primeiro procuro o serviço na secao da UF se nao existir, verifico se na "Usar" contem o serviço
			url = prop.getProperty(secao+"."+servico.getServico());
			if (ObjetoUtil.isEmpty(url)) {
				if(prop.getProperty(secao+".Usar") != null) {
					secao = prop.getProperty(secao+".Usar");
					if(secao!=null) {						
						url = prop.getProperty(secao+"."+servico.getServico());
					}
				}	
			}

			if (ObjetoUtil.isEmpty(url)) {
				if(config.isLog()) {
					throw new NfeException(
							"WebService de " + servico + " não encontrado para " + config.getEstado().getNome() + " chave procurada:"+ secao+"."+servico.getServico());
				}else {
					throw new NfeException(
						"WebService de " + servico + " não encontrado para " + config.getEstado().getNome());
				}
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

		ConstantesUtil.TipoDoc_e tipo = ConstantesUtil.TipoDoc_e.NFE;
		ConstantesUtil.SERVICOS servico = ConstantesUtil.SERVICOS.CONSULTA_CADASTRO;
		
		return getUrl(config, tipo, servico);
	}

}
