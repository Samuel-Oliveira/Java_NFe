package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas.DistDFeInt;
import br.com.swconsultoria.nfe.schemas.RetDistDFeInt;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import br.com.swconsultoria.nfe.wsdl.NFeDistribuicaoDFe.NFeDistribuicaoDFeStub;
import lombok.extern.java.Log;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.transport.http.HTTPConstants;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.rmi.RemoteException;

@Log
public class DistribuicaoDFeA3 {

    static RetDistDFeInt consultaNfe(ConfiguracoesNfe config, String xmlAssinado) throws NfeException {
        try {
            log.info("[XML-ENVIO]: " + xmlAssinado);

            OMElement ome = AXIOMUtil.stringToOM(xmlAssinado);

            NFeDistribuicaoDFeStub.NfeDadosMsg_type0 dadosMsgType0 = new NFeDistribuicaoDFeStub.NfeDadosMsg_type0();
            dadosMsgType0.setExtraElement(ome);

            NFeDistribuicaoDFeStub.NfeDistDFeInteresse distDFeInteresse = new NFeDistribuicaoDFeStub.NfeDistDFeInteresse();
            distDFeInteresse.setNfeDadosMsg(dadosMsgType0);

            NFeDistribuicaoDFeStub stub = new NFeDistribuicaoDFeStub(
                    WebServiceUtil.getUrl(config, DocumentoEnum.NFE, ServicosEnum.DISTRIBUICAO_DFE));

            // Timeout
            if (ObjetoUtil.verifica(config.getTimeout()).isPresent()) {
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.SO_TIMEOUT, config.getTimeout());
                stub._getServiceClient().getOptions().setProperty(HTTPConstants.CONNECTION_TIMEOUT,
                        config.getTimeout());
            }
            NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse result = stub.nfeDistDFeInteresse(distDFeInteresse);

            log.info("[XML-RETORNO]: " + result.getNfeDistDFeInteresseResult().getExtraElement().toString());
            return XmlNfeUtil.xmlToObject(result.getNfeDistDFeInteresseResult().getExtraElement().toString(),
                    RetDistDFeInt.class);

        } catch (RemoteException | XMLStreamException e) {
            throw new NfeException(e.getMessage());
        }

    }

    static String montarXML(ConfiguracoesNfe config, PessoaEnum tipoPessoa, String cpfCnpj, ConsultaDFeEnum tipoConsulta,
            String nsuChave) throws JAXBException, NfeException {
        DistDFeInt distDFeInt = new DistDFeInt();
        distDFeInt.setVersao(ConstantesUtil.VERSAO.DIST_DFE);
        distDFeInt.setTpAmb(config.getAmbiente().getCodigo());
        distDFeInt.setCUFAutor(config.getEstado().getCodigoUF());

        if (PessoaEnum.JURIDICA.equals(tipoPessoa)) {
            distDFeInt.setCNPJ(cpfCnpj);
        } else {
            distDFeInt.setCPF(cpfCnpj);
        }

        switch (tipoConsulta) {
            case NSU:
                DistDFeInt.DistNSU distNSU = new DistDFeInt.DistNSU();
                distNSU.setUltNSU(nsuChave);
                distDFeInt.setDistNSU(distNSU);
                break;
            case NSU_UNICO:
                DistDFeInt.ConsNSU consNSU = new DistDFeInt.ConsNSU();
                consNSU.setNSU(nsuChave);
                distDFeInt.setConsNSU(consNSU);
                break;
            case CHAVE:
                DistDFeInt.ConsChNFe chNFe = new DistDFeInt.ConsChNFe();
                chNFe.setChNFe(nsuChave);
                distDFeInt.setConsChNFe(chNFe);
                break;
        }

        return XmlNfeUtil.objectToXml(distDFeInt, config.getEncode());

    }

}
