package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schemas.TRetConsSitNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

/**
 * @author Samuel Oliveira
 *
 */
public class ConsultaXmlTeste {

	public static void main(String[] args) {

		try {

            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            //Informe a chave a ser Consultada
            String chave = "52260610732644000128550010000927561865874165";

            //Efetua a consulta
			TRetConsSitNFe retorno = Nfe.consultaXml(config, chave, DocumentoEnum.NFE);

            //Resultado
            System.out.println("XML: "+ XmlNfeUtil.objectToXml(retorno));
            System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());


		} catch (Exception e) {
			System.err.println();
			System.err.println(e.getMessage());
		}
	}
}
