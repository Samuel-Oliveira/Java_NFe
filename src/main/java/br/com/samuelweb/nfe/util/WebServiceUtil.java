/**
 * 
 */
package br.com.samuelweb.nfe.util;

import java.io.IOException;
import java.io.InputStream;

import org.ini4j.Wini;

import br.com.samuelweb.nfe.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;

/**
 * @author Samuel Oliveira
 *
 */
public class WebServiceUtil {

	public static String getUrl(String tipo, String servico) throws NfeException {

		try {

			ConfiguracoesIniciaisNfe config = ConfiguracoesIniciaisNfe.getInstance();
			String secao = tipo + "_" + config.getEstado() + "_"
					+ (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");

			InputStream is = WebServiceUtil.class.getResourceAsStream("/WebServices.ini");

			Wini ini = new Wini(is);
			String url = ini.get(secao, "Usar");

			if (servico.equals(ConstantesUtil.SERVICOS.DOWNLOAD)
					|| servico.equals(ConstantesUtil.SERVICOS.DISTRIBUICAO_DFE)
					|| servico.equals(ConstantesUtil.SERVICOS.CONSULTA_DEST)
					|| servico.equals(ConstantesUtil.SERVICOS.MANIFESTACAO)) {
				secao = config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "NFe_AN_H" : "NFe_AN_P";

				if (servico.equals(ConstantesUtil.SERVICOS.MANIFESTACAO)) {
					servico = ConstantesUtil.SERVICOS.EVENTO;
				}

			} else if (!servico.equals(ConstantesUtil.SERVICOS.URL_CONSULTANFCE)
					&& !servico.equals(ConstantesUtil.SERVICOS.URL_QRCODE) && ObjetoUtil.differentNull(url)) {
				secao = url;
			} else if(config.isContigenciaSCAN()){
				//SVC-RS
				if(config.getEstado().equals(ConstantesUtil.ESTADO.GO)
						|| config.getEstado().equals(ConstantesUtil.ESTADO.AM)
						|| config.getEstado().equals(ConstantesUtil.ESTADO.BA)
						|| config.getEstado().equals(ConstantesUtil.ESTADO.CE)
						|| config.getEstado().equals(ConstantesUtil.ESTADO.MA)
						|| config.getEstado().equals(ConstantesUtil.ESTADO.MS)
						|| config.getEstado().equals(ConstantesUtil.ESTADO.MT)
						|| config.getEstado().equals(ConstantesUtil.ESTADO.PA)
						|| config.getEstado().equals(ConstantesUtil.ESTADO.PE)
						|| config.getEstado().equals(ConstantesUtil.ESTADO.PI)
						|| config.getEstado().equals(ConstantesUtil.ESTADO.PR)){
					secao = tipo + "_SVRS_" + (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");
				//SVC-AN	
				}else{
					secao = tipo + "_SVAN_" + (config.getAmbiente().equals(ConstantesUtil.AMBIENTE.HOMOLOGACAO) ? "H" : "P");
				}
			}

			url = ini.get(secao, servico);

			return ini.get(secao, servico);

		} catch (IOException e) {
			throw new NfeException(e.getMessage());
		}

	}

}
