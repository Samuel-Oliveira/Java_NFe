package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.*;
import br.inf.portalfiscal.nfe.schema.distdfeint.DistDFeInt;
import br.inf.portalfiscal.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.inf.portalfiscal.www.nfe.wsdl.NFeDistribuicaoDFe.NFeDistribuicaoDFeStub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;


/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 *
 */
class DistribuicaoDFe {

	/**
	 * Classe Reponsavel Por Consultar as NFE na SEFAZ
	 * 
	 * @param distDFeInt
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	static RetDistDFeInt consultaNfe(String tipoCliente, String cpfCnpj , String tipoConsulta , String nsuChave) throws NfeException{
		
		try {

			/**
			 * Carrega Informaçoes do Certificado Digital.
			 */
			ConfiguracoesIniciaisNfe config = CertificadoUtil.iniciaConfiguracoes();

			DistDFeInt distDFeInt = new DistDFeInt();
			distDFeInt.setVersao(ConstantesUtil.VERSAO.DIST_DFE);
			distDFeInt.setTpAmb(config.getAmbiente());
			distDFeInt.setCUFAutor(config.getEstado().getCodigoIbge());

			if(ConstantesUtil.TIPOS.CNPJ.equals(tipoCliente)){
				distDFeInt.setCNPJ(cpfCnpj);
			}else{
				distDFeInt.setCPF(cpfCnpj);
			}

			if(ConstantesUtil.TIPOS.NSU.equals(tipoConsulta)){
				DistDFeInt.DistNSU distNSU = new DistDFeInt.DistNSU();
				distNSU.setUltNSU(nsuChave);
				distDFeInt.setDistNSU(distNSU);
			}else{
				DistDFeInt.ConsChNFe chNFe = new DistDFeInt.ConsChNFe();
				chNFe.setChNFe(nsuChave);
				distDFeInt.setConsChNFe(chNFe);
			}

			String xml = XmlUtil.objectToXml(distDFeInt);

			if (config.isLog()) {
				System.out.println("Xml: " + xml);
			}
			
			OMElement ome = AXIOMUtil.stringToOM(xml);
			
			NFeDistribuicaoDFeStub.NfeDadosMsg_type0 dadosMsgType0 = new NFeDistribuicaoDFeStub.NfeDadosMsg_type0();  
			dadosMsgType0.setExtraElement(ome);  
			  
			NFeDistribuicaoDFeStub.NfeDistDFeInteresse distDFeInteresse = new NFeDistribuicaoDFeStub.NfeDistDFeInteresse();  
			distDFeInteresse.setNfeDadosMsg(dadosMsgType0);  
			  
			NFeDistribuicaoDFeStub stub = new NFeDistribuicaoDFeStub( WebServiceUtil.getUrl(ConstantesUtil.NFE, ConstantesUtil.SERVICOS.DISTRIBUICAO_DFE));
            //Timeout
            if (!ObjetoUtil.isEmpty(config.getTimeout())) {
                stub._getServiceClient().getOptions().setProperty(
                        HTTPConstants.SO_TIMEOUT, config.getTimeout());
                stub._getServiceClient().getOptions().setProperty(
                        HTTPConstants.CONNECTION_TIMEOUT, config.getTimeout());
            }
			NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse result = stub.nfeDistDFeInteresse(distDFeInteresse);

			return XmlUtil.xmlToObject(result.getNfeDistDFeInteresseResult().getExtraElement().toString(), RetDistDFeInt.class);  

		} catch (RemoteException | XMLStreamException | JAXBException e) {
			throw new NfeException(e.getMessage());
		}
	}


}
