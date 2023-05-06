package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.consCad.TConsCad;
import br.com.swconsultoria.nfe.schema.consCad.TUfCons;
import br.com.swconsultoria.nfe.schema.retConsCad.TRetConsCad;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.CadConsultaCadastro4Stub;
import lombok.extern.java.Log;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

/**
 * Classe responsavel por Consultar a Situaçao do XML na SEFAZ.
 *
 * @author Samuel Oliveira - samuel@swconsultoria.com.br - www.swconsultoria.com.br
 */
@Log
class ConsultaCadastro {

    private ConsultaCadastro() {}

    /**
     * Classe Reponsavel Por Consultar o status da NFE na SEFAZ
     */

    static TRetConsCad consultaCadastro(ConfiguracoesNfe config, PessoaEnum tipoPessoa, String cnpjCpf, EstadosEnum estado)
            throws NfeException {

        try {

            TConsCad consCad = new TConsCad();
            consCad.setVersao(ConstantesUtil.VERSAO.CONSULTA_CADASTRO);

            TConsCad.InfCons infCons = new TConsCad.InfCons();
            if (PessoaEnum.JURIDICA.equals(tipoPessoa)) {
                infCons.setCNPJ(cnpjCpf);
            } else {
                infCons.setCPF(cnpjCpf);
            }
            infCons.setXServ("CONS-CAD");
            infCons.setUF(TUfCons.valueOf(estado.toString()));

            consCad.setInfCons(infCons);

            String xml = XmlNfeUtil.objectToXml(consCad, config.getEncode());

            log.info("[XML-ENVIO]: " + xml);

            OMElement ome = AXIOMUtil.stringToOM(xml);

            ConfiguracoesNfe configConsulta = new ConfiguracoesNfe();
            configConsulta.setContigenciaSVC(config.isContigenciaSVC());
            configConsulta.setEstado(estado);
            configConsulta.setAmbiente(config.getAmbiente());

            if (EstadosEnum.MS.equals(estado)) {
                br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.ms.CadConsultaCadastro4Stub.NfeDadosMsg dadosMsg =
                        new br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.ms.CadConsultaCadastro4Stub.NfeDadosMsg();
                dadosMsg.setExtraElement(ome);

                br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.ms.CadConsultaCadastro4Stub stub = new br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.ms.CadConsultaCadastro4Stub(
                        WebServiceUtil.getUrl(configConsulta, DocumentoEnum.NFE, ServicosEnum.CONSULTA_CADASTRO));

                // Timeout
                if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
                            config.getTimeout());
                }

                br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.ms.CadConsultaCadastro4Stub.NfeResultMsg result = stub.consultaCadastro(dadosMsg);

                log.info("[XML-RETORNO]: " + result.getExtraElement().toString());
                return XmlNfeUtil.xmlToObject(result.getExtraElement().toString(), TRetConsCad.class);
            } else if (EstadosEnum.MT.equals(estado)) {
                br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.rs.CadConsultaCadastro4Stub.ConsultaCadastro consultaCadastro =
                        new br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.rs.CadConsultaCadastro4Stub.ConsultaCadastro();
                br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.rs.CadConsultaCadastro4Stub.NfeDadosMsg_type0 dadosMsg = new br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.rs.CadConsultaCadastro4Stub.NfeDadosMsg_type0();
                dadosMsg.setExtraElement(ome);
                consultaCadastro.setNfeDadosMsg(dadosMsg);

                br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.rs.CadConsultaCadastro4Stub stub = new br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.rs.CadConsultaCadastro4Stub(
                        WebServiceUtil.getUrl(configConsulta, DocumentoEnum.NFE, ServicosEnum.CONSULTA_CADASTRO));

                // Timeout
                if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
                            config.getTimeout());
                }

                br.com.swconsultoria.nfe.wsdl.CadConsultaCadastro.rs.CadConsultaCadastro4Stub.NfeResultMsg result = stub.consultaCadastro(consultaCadastro);

                log.info("[XML-RETORNO]: " + result.getConsultaCadastroResult().getExtraElement().toString());
                return XmlNfeUtil.xmlToObject(result.getConsultaCadastroResult().getExtraElement().toString(), TRetConsCad.class);
            } else {
                CadConsultaCadastro4Stub.NfeDadosMsg dadosMsg = new CadConsultaCadastro4Stub.NfeDadosMsg();
                dadosMsg.setExtraElement(ome);

                CadConsultaCadastro4Stub stub = new CadConsultaCadastro4Stub(
                        WebServiceUtil.getUrl(configConsulta, DocumentoEnum.NFE, ServicosEnum.CONSULTA_CADASTRO));

                // Timeout
                if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                    stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
                            config.getTimeout());
                }

                CadConsultaCadastro4Stub.NfeResultMsg result = stub.consultaCadastro(dadosMsg);

                log.info("[XML-RETORNO]: " + result.getExtraElement().toString());
                return XmlNfeUtil.xmlToObject(result.getExtraElement().toString(), TRetConsCad.class);
            }

        } catch (RemoteException | XMLStreamException | JAXBException e) {
            throw new NfeException(e.getMessage(),e);
        }

    }

}