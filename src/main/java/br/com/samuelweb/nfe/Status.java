package br.com.samuelweb.nfe;

import java.rmi.RemoteException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.samuelweb.nfe.dom.ConfiguracoesNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.WebServiceUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema_4.consStatServ.TConsStatServ;
import br.inf.portalfiscal.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.inf.portalfiscal.www.nfe_400.wsdl.NFeStatusServico4.NFeStatusServico4Stub;

/**
 * Classe responsável por fazer a Verificação do Status Do Webservice
 * 
 * @author Samuel Oliveira
 *
 */
class Status {

	/**
	 * Metodo para Consulta de Status de Serviço
         *<p>
	 * Cria um objeto do tipo TConsStatServ usando as propriedades passadas
         *  pelo argumento <b>config</b>. Após, este objeto é convertido em um obejto
         * OMElement manipulável onde é passado para o atributo extraElement da
         * classe NFeStatusServico4Stub.NfeDadosMsg.
         * </p>
         * 
         * <p>
         * O método statusServico então cria uma instância de NFeStatusServico4Stub
         * passando o argumento <b>tipo</b> e <b>config</b> em seu construtor, onde será montada a URL
         * de consulta do status do serviço dependendo das configuções
         * (ambiente, Estado, NF-e ou NFC-e)
         * </p>
         * 
         * <p>
         * Então o método nfeStatusServicoNF efetuará a consulta e retornará o 
         * resultado que será convertido em um objeto e enfim retornado por este
         * método.
         * </p>
         * 
         * @param confi ConfiguracoesNfe, interface de configuração da NF-e ou NFC-e.
	 * @param tipo ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * @return TRetConsStatServ - objeto que contém o resultado
         * da transmissão do XML.
         * 
	 * @throws NfeException
         * 
         * @see ConfiguracoesNfe
         * @see ConstantesUtil
         * @see WebServiceUtil
         * @see XmlUtil
	 */
	static TRetConsStatServ statusServico(ConfiguracoesNfe config, String tipo) throws NfeException {

            try {

                TConsStatServ consStatServ = new TConsStatServ();
                consStatServ.setTpAmb(config.getAmbiente());
                consStatServ.setCUF(config.getEstado().getCodigoIbge());
                consStatServ.setVersao(config.getVersaoNfe());
                consStatServ.setXServ("STATUS");
                String xml = XmlUtil.objectToXml(consStatServ);

                if (config.isLog()) {
                    System.out.println("Xml Status: " + xml);
                }
                OMElement ome = AXIOMUtil.stringToOM(xml);

                NFeStatusServico4Stub.NfeDadosMsg dadosMsg = new NFeStatusServico4Stub.NfeDadosMsg();
                dadosMsg.setExtraElement(ome);

                NFeStatusServico4Stub stub = new NFeStatusServico4Stub(tipo.equals(ConstantesUtil.NFCE)
                        ? WebServiceUtil.getUrl(config, ConstantesUtil.NFCE, ConstantesUtil.SERVICOS.STATUS_SERVICO)
                        : WebServiceUtil.getUrl(config, ConstantesUtil.NFE, ConstantesUtil.SERVICOS.STATUS_SERVICO));
                NFeStatusServico4Stub.NfeResultMsg result = stub.nfeStatusServicoNF(dadosMsg);

                return XmlUtil.xmlToObject(result.getExtraElement().toString(), TRetConsStatServ.class);

            } catch (RemoteException | XMLStreamException | JAXBException e) {
                throw new NfeException(e.getMessage());
            }
	}

}