package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento;
import br.com.swconsultoria.nfe.schema_4.consSitNFe.TProtNFe;
import br.com.swconsultoria.nfe.schema_4.consSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.util.CancelamentoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import java.time.LocalDateTime;

/**
 * @author Samuel Oliveira
 *
 */
public class ConsultaXmlCancelamentoTeste {

	public static void main(String[] args) {

		try {

            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            //Informe a chave a ser Consultada
            String chave = "52240610732644000128550010000927431392573498";

            //Efetua a consulta
			TRetConsSitNFe retorno = Nfe.consultaXml(config, chave, DocumentoEnum.NFE);

            //Resultado
            System.out.println("XML: "+ XmlNfeUtil.objectToXml(retorno));
            System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());

			if(retorno.getCStat().equals("101")){
				//if retorno for cancelado
				TProtNFe retCancNFe = retorno.getProtNFe();

				TRetEvento retCance = new TRetEvento();
				retCance.setVersao("1.00");
				TRetEvento.InfEvento infEvento = new TRetEvento.InfEvento();
				infEvento.setTpAmb(retCancNFe.getInfProt().getTpAmb());
				infEvento.setVerAplic(retCancNFe.getInfProt().getVerAplic());
				infEvento.setCOrgao(EstadosEnum.GO.getCodigoUF());
				infEvento.setCStat("135");
				infEvento.setXMotivo("Evento registrado e vinculado a NF-e");
				infEvento.setChNFe(retCancNFe.getInfProt().getChNFe());
				infEvento.setTpEvento("110111");
				infEvento.setXEvento("Cancelamento registrado");
				infEvento.setNSeqEvento("1");
				infEvento.setCNPJDest(null);
				infEvento.setEmailDest(null);
				infEvento.setDhRegEvento(retCancNFe.getInfProt().getDhRecbto().toString());
				infEvento.setNProt(retCancNFe.getInfProt().getNProt());

				retCance.setInfEvento(infEvento);

				Evento cancela = new Evento();
				cancela.setChave("52240610732644000128550010000927431392573498");
				cancela.setProtocolo("152230024577165");
				cancela.setCnpj("10732644000128");
				cancela.setMotivo("Teste de Cancelamento");
				cancela.setDataEvento(LocalDateTime.of(2024,6,13,15,4,50));

				//Monta o Evento de Cancelamento
				TEnvEvento enviEvento = CancelamentoUtil.montaCancelamento(cancela, config);

				//Cria ProcEvento de Cacnelamento
				String proc = CancelamentoUtil.criaProcEventoCancelamento(config, enviEvento, retCance);
				System.out.println(proc);
			} else if(retorno.getCStat().equals("100") || retorno.getCStat().equals("150")){
				//monta xml de autorizacao
			}

		} catch (Exception e) {
			System.err.println();
			System.err.println(e.getMessage());
		}
	}
}
