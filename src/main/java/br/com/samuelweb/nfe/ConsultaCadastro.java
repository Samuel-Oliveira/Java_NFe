package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.exception.NfeValidacaoException;
import br.com.samuelweb.nfe.util.*;
import br.inf.portalfiscal.nfe.schema.consCad.TConsCad;
import br.inf.portalfiscal.nfe.schema.retConsCad.TRetConsCad;
import br.inf.portalfiscal.nfe_4.wsdl.CadConsultaCadastro4Stub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

/**
 * Classe responsavel por Consultar a Situaçao do XML na SEFAZ.
 *
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 *
 */

public class ConsultaCadastro {

    private static CadConsultaCadastro4Stub.NfeResultMsg result;
    private static CertificadoUtil certUtil;

    /**
     * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
     *
     * @param consCad
     * @param valida
     * @return
     * @throws NfeException
     */

    public static TRetConsCad consultaCadastro(TConsCad consCad, boolean valida) throws NfeException {

        certUtil = new CertificadoUtil();

        try {

            certUtil.iniciaConfiguracoes();

            String xml = XmlUtil.objectToXml(consCad);

            if (valida) {
                String erros = Validar.validaXml(xml, Validar.CONSULTA_CADASTRO);
                if (!ObjetoUtil.isEmpty(erros)) {
                    throw new NfeValidacaoException("Erro Na Validação do Xml: " + erros);
                }
            }

            System.out.println("Xml Consulta: " + xml);
            OMElement ome = AXIOMUtil.stringToOM(xml);

            CadConsultaCadastro4Stub.NfeDadosMsg dadosMsg = new CadConsultaCadastro4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            CadConsultaCadastro4Stub stub = new CadConsultaCadastro4Stub(WebServiceUtil.getUrlConsultaCadastro(consCad.getInfCons().getUF().toString()));
            result = stub.consultaCadastro(dadosMsg);

            return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsCad.class);

        } catch (RemoteException | XMLStreamException | JAXBException e) {
            throw new NfeException(e.getMessage());
        }

    }

}