package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.*;
import br.inf.portalfiscal.nfe.schema_4.consReciNFe.TConsReciNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.inf.portalfiscal.www.nfe_400.wsdl.NFeRetAutorizacao.NFeRetAutorizacao4Stub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;


/**
 * Classe Responsavel Por pegar o Retorno da NFE, apos o Envio.
 *
 * @author Samuel Oliveira
 */
class ConsultaRecibo {

    /**
     * Metodo Responsavel Por Pegar o Xml De Retorno.
     *
     * @param tConsReciNFe
     * @param valida
     * @param tipo
     * @return
     * @throws NfeException
     */

    static TRetConsReciNFe reciboNfe(String recibo, String tipo) throws NfeException {

        try {

            /**
             * Informaçoes do Certificado Digital.
             */
            ConfiguracoesIniciaisNfe configuracoesNfe = CertificadoUtil.iniciaConfiguracoes();

            TConsReciNFe consReciNFe = new TConsReciNFe();
            consReciNFe.setVersao(configuracoesNfe.getVersaoNfe());
            consReciNFe.setTpAmb(configuracoesNfe.getAmbiente());
            consReciNFe.setNRec(recibo);

            String xml = XmlUtil.objectToXml(consReciNFe);

            OMElement ome = AXIOMUtil.stringToOM(xml);
            NFeRetAutorizacao4Stub.NfeDadosMsg dadosMsg = new NFeRetAutorizacao4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            NFeRetAutorizacao4Stub stub = new NFeRetAutorizacao4Stub(tipo.equals(ConstantesUtil.NFCE) ? WebServiceUtil.getUrl(ConstantesUtil.NFCE, ConstantesUtil.SERVICOS.CONSULTA_RECIBO) : WebServiceUtil.getUrl(ConstantesUtil.NFE, ConstantesUtil.SERVICOS.CONSULTA_RECIBO));
            //Timeout
            if (!ObjetoUtil.isEmpty(configuracoesNfe.getTimeout())) {
                stub._getServiceClient().getOptions().setProperty(
                        HTTPConstants.SO_TIMEOUT, configuracoesNfe.getTimeout());
                stub._getServiceClient().getOptions().setProperty(
                        HTTPConstants.CONNECTION_TIMEOUT, configuracoesNfe.getTimeout());
            }
            NFeRetAutorizacao4Stub.NfeResultMsg result = stub.nfeRetAutorizacaoLote(dadosMsg);

            return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsReciNFe.class);

        } catch (RemoteException | XMLStreamException | JAXBException e) {
            throw new NfeException(e.getMessage());
        }

    }
}
