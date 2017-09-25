package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.CertificadoUtil;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.distdfeint.DistDFeInt;
import br.inf.portalfiscal.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.inf.portalfiscal.www.nfe.wsdl.NFeDistribuicaoDFe.NFeDistribuicaoDFeStub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;


/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 *
 */
public class DistribuicaoDFe {

	public static final String CNPJ = "CNPJ";
	public static final String CPF = "CPF";
	public static final String NSU = "NSU";
	public static final String CHAVE = "CHAVE";

	/**
	 * Classe Reponsavel Por Consultar as NFE na SEFAZ
	 * 
	 * @param distDFeInt
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	public static RetDistDFeInt consultaNfe(String tipoCliente, String cpfCnpj , String tipoConsulta , String nsuChave) throws NfeException{
		
		try {

			/**
			 * Carrega Informaçoes do Certificado Digital.
			 */
			ConfiguracoesIniciaisNfe config = CertificadoUtil.iniciaConfiguracoes();

			DistDFeInt distDFeInt = new DistDFeInt();
			distDFeInt.setVersao(ConstantesUtil.VERSAO.DIST_DFE);
			distDFeInt.setTpAmb(config.getAmbiente());
			distDFeInt.setCUFAutor(config.getEstado().getCodigoIbge());

			if(CNPJ.equals(tipoCliente)){
				distDFeInt.setCNPJ(cpfCnpj);
			}else{
				distDFeInt.setCPF(cpfCnpj);
			}

			if(NSU.equals(tipoConsulta)){
				DistDFeInt.DistNSU distNSU = new DistDFeInt.DistNSU();
				distNSU.setUltNSU(nsuChave);
				distDFeInt.setDistNSU(distNSU);
			}else{
				DistDFeInt.ConsChNFe chNFe = new DistDFeInt.ConsChNFe();
				chNFe.setChNFe(nsuChave);
				distDFeInt.setConsChNFe(chNFe);
			}

			String xml = XmlUtil.objectToXml(distDFeInt);

			System.out.println("Xml: "+xml);
			
			OMElement ome = AXIOMUtil.stringToOM(xml);
			
			NFeDistribuicaoDFeStub.NfeDadosMsg_type0 dadosMsgType0 = new NFeDistribuicaoDFeStub.NfeDadosMsg_type0();  
			dadosMsgType0.setExtraElement(ome);  
			  
			NFeDistribuicaoDFeStub.NfeDistDFeInteresse distDFeInteresse = new NFeDistribuicaoDFeStub.NfeDistDFeInteresse();  
			distDFeInteresse.setNfeDadosMsg(dadosMsgType0);  
			  
			NFeDistribuicaoDFeStub stub = new NFeDistribuicaoDFeStub( WebServiceUtil.getUrl(ConstantesUtil.NFE, ConstantesUtil.SERVICOS.DISTRIBUICAO_DFE));
			NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse result = stub.nfeDistDFeInteresse(distDFeInteresse);

			return XmlUtil.xmlToObject(result.getNfeDistDFeInteresseResult().getExtraElement().toString(), RetDistDFeInt.class);  

		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}
	}


}
