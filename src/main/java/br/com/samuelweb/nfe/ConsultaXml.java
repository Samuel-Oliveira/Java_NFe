package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.exception.NfeValidacaoException;
import br.com.samuelweb.nfe.util.*;
import br.inf.portalfiscal.nfe.schema_4.consSitNFe.TConsSitNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.inf.portalfiscal.nfe_4.wsdl.NFeConsultaProtocolo4Stub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

/**
 * Classe responsavel por Consultar a Situaçao do XML na SEFAZ.
 *
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 */

public class ConsultaXml {

    private static NFeConsultaProtocolo4Stub.NfeResultMsg result;
    private static ConfiguracoesIniciaisNfe configuracoesNfe;
    private static CertificadoUtil certUtil;

    /**
     * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
     *
     * @param consSitNFe
     * @param valida
     * @param tipo
     * @return
     * @throws NfeException
     */
    public static TRetConsSitNFe consultaXml(TConsSitNFe consSitNFe, boolean valida, String tipo) throws NfeException {

        certUtil = new CertificadoUtil();
        configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();
        boolean nfce = tipo.equals(ConstantesUtil.NFCE);

        try {

            certUtil.iniciaConfiguracoes();

            String xml = XmlUtil.objectToXml(consSitNFe);

            if (valida) {
                String erros = Validar.validaXml(xml, Validar.CONSULTA_XML);
                if (!ObjetoUtil.isEmpty(erros)) {
                    throw new NfeValidacaoException("Erro Na Validação do Xml: " + erros);
                }
            }

            System.out.println("Xml Consulta: " + xml);
            OMElement ome = AXIOMUtil.stringToOM(xml);


            NFeConsultaProtocolo4Stub.NfeDadosMsg dadosMsg = new NFeConsultaProtocolo4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            NFeConsultaProtocolo4Stub stub = new NFeConsultaProtocolo4Stub(nfce ? WebServiceUtil.getUrl(ConstantesUtil.NFCE, ConstantesUtil.SERVICOS.CONSULTA_XML) : WebServiceUtil.getUrl(ConstantesUtil.NFE, ConstantesUtil.SERVICOS.CONSULTA_XML));
            result = stub.nfeConsultaNF(dadosMsg);

            return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsSitNFe.class);

        } catch (RemoteException | XMLStreamException | JAXBException e) {
            throw new NfeException(e.getMessage());
        }

    }

}