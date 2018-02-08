package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.CertificadoUtil;
import br.com.samuelweb.nfe.util.ObjetoUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.consCad.TConsCad;
import br.inf.portalfiscal.nfe.schema.consCad.TUfCons;
import br.inf.portalfiscal.nfe.schema.retConsCad.TRetConsCad;
import br.inf.portalfiscal.www.nfe_400.wsdl.CadConsultaCadastro.CadConsultaCadastro4Stub;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

/**
 * Classe responsavel por Consultar a Situaçao do XML na SEFAZ.
 *
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 */

class ConsultaCadastro {

    static final String CNPJ = "CNPJ";
    static final String CPF = "CPF";

    /**
     * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
     *
     * @param consCad
     * @param valida
     * @return
     * @throws NfeException
     */

    static TRetConsCad consultaCadastro(String tipo, String cnpjCpf) throws NfeException {

        try {

            ConfiguracoesIniciaisNfe config = CertificadoUtil.iniciaConfiguracoes();

            TConsCad consCad = new TConsCad();
            consCad.setVersao("2.00");

            TConsCad.InfCons infCons = new TConsCad.InfCons();
            if (CNPJ.equals(tipo)) {
                infCons.setCNPJ(cnpjCpf);
            } else {
                infCons.setCPF(cnpjCpf);
            }
            infCons.setXServ("CONS-CAD");
            infCons.setUF(TUfCons.valueOf(config.getEstado().toString()));

            consCad.setInfCons(infCons);

            String xml = XmlUtil.objectToXml(consCad);

            if (config.isLog()) {
                System.out.println("Xml Consulta: " + xml);
            }
            OMElement ome = AXIOMUtil.stringToOM(xml);

            CadConsultaCadastro4Stub.NfeDadosMsg dadosMsg = new CadConsultaCadastro4Stub.NfeDadosMsg();
            dadosMsg.setExtraElement(ome);

            CadConsultaCadastro4Stub stub = new CadConsultaCadastro4Stub(WebServiceUtil.getUrlConsultaCadastro(consCad.getInfCons().getUF().toString()));

            //Timeout
            if (!ObjetoUtil.isEmpty(config.getTimeout())) {
                stub._getServiceClient().getOptions().setProperty(
                        HTTPConstants.SO_TIMEOUT, config.getTimeout());
                stub._getServiceClient().getOptions().setProperty(
                        HTTPConstants.CONNECTION_TIMEOUT, config.getTimeout());
            }

            CadConsultaCadastro4Stub.NfeResultMsg result = stub.consultaCadastro(dadosMsg);

            return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsCad.class);

        } catch (RemoteException | XMLStreamException | JAXBException e) {
            throw new NfeException(e.getMessage());
        }

    }

}