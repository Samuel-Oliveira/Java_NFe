/**
 * 
 */
package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.exception.NfeException;
import br.inf.portalfiscal.nfe.schema.conssitnfe.TConsSitNFe;
import br.inf.portalfiscal.nfe.schema.consstatserv.TConsStatServ;
import br.inf.portalfiscal.nfe.schema.distdfeint.DistDFeInt;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.inf.portalfiscal.nfe.schema.envinfe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema.envinfe.TRetEnviNFe;
import br.inf.portalfiscal.nfe.schema.inutnfe.TInutNFe;
import br.inf.portalfiscal.nfe.schema.retconssitnfe.TRetConsSitNFe;
import br.inf.portalfiscal.nfe.schema.retconsstatserv.TRetConsStatServ;
import br.inf.portalfiscal.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.inf.portalfiscal.nfe.schema.retinutnfe.TRetInutNFe;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 *
 */
public class Nfe {
	
	/**
	 * Construtor privado
	 */
	private Nfe() {
	}

	/**
	 * Metodo Responsavel Buscar o Status de Serviço do Servidor da Sefaz
	 * 
	 * @param TConsStatServ
	 * @return TRetConsStatServ
	 * @throws NfeException
	 */
	public static TRetConsStatServ statusServico(TConsStatServ consStatServ) throws NfeException{
		
		return Status.statusServico(consStatServ);
		
	}
	
	/**
	 * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
	 * 
	 * @param consSitNFe
	 * @return TRetConsSitNFe
	 * @throws NfeException 
	 */
	public static TRetConsSitNFe consultaXml(TConsSitNFe consSitNFe) throws NfeException{
		
		return ConsultaXml.consultaXml(consSitNFe);
			
	}
	
	/**
	 * Classe Reponsavel Por Inutilizar a NFE na SEFAZ
	 * 
	 * @param TInutNFe
	 * @return TRetInutNFe
	 * @throws NfeException 
	 */
	public static TRetInutNFe inutilizacao(TInutNFe inutNFe) throws NfeException{
		
		return Inutilizar.inutiliza(inutNFe);
		
	}
	
	/**
	 * Classe Reponsavel Por Consultar a Distribuiçao da NFE na SEFAZ
	 * 
	 * @param consSitNFe
	 * @return TRetConsSitNFe
	 * @throws NfeException 
	 */
	public static RetDistDFeInt distribuicaoDfe(DistDFeInt distDFeInt) throws NfeException{
		
		return DistribuicaoDFe.consultaNfe(distDFeInt);
		
	}
	
	/**
	 * Metodo para Montar a NFE.
	 * 
	 * @param TEnviNFe
	 * @return String
	 * @throws NfeException 
	 */
	public static TEnviNFe montaNfe(TEnviNFe enviNFe) throws NfeException{
		
		return Enviar.montaNfe(enviNFe);
				
	}
	
	/**
	 * Metodo para Enviar a NFE.
	 * 
	 * @param String
	 * @return TRetEnviNFe
	 * @throws NfeException 
	 */
	public static TRetEnviNFe enviarNfe(TEnviNFe enviNFe) throws NfeException{
		
		return Enviar.enviaNfe(enviNFe);
		
	}
	
	/**
	 * Metodo para Cancelar a NFE.
	 * 
	 * @param String
	 * @return TRetEnvEvento
	 * @throws NfeException 
	 */
	public static TRetEnvEvento cancelarNfe(TEnvEvento evento) throws NfeException{
		
		return Evento.eventoCancelamento(evento);
		
	}
	
	/**
	 * Metodo para Envio da Carta De Correção da NFE.
	 * 
	 * @param String
	 * @return TRetEnvEvento
	 * @throws NfeException 
	 */
	public static br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento cce(br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento evento) throws NfeException{
		
		return Evento.eventoCce(evento);
		
	}

	/**
	 * Metodo para Manifestação da NFE.
	 * 
	 * @param String
	 * @return TRetEnvEvento
	 * @throws NfeException 
	 */
	public static br.inf.portalfiscal.nfe.schema.retEnvConfRecebto.TRetEnvEvento manifestacao(br.inf.portalfiscal.nfe.schema.envConfRecebto.TEnvEvento envEvento) throws NfeException {
		
		return Evento.eventoManifestacao(envEvento);
		
	}
	
	
}
