/**
 *
 */
package br.com.samuelweb.nfe.util;

import br.com.samuelweb.nfe.exception.NfeException;
import br.inf.portalfiscal.nfe.schema.consCad.TConsCad;
import br.inf.portalfiscal.nfe.schema.distdfeint.DistDFeInt;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.inf.portalfiscal.nfe.schema_4.consReciNFe.TConsReciNFe;
import br.inf.portalfiscal.nfe.schema_4.consSitNFe.TConsSitNFe;
import br.inf.portalfiscal.nfe.schema_4.consStatServ.TConsStatServ;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNfeProc;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TProtNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.inutNFe.TInutNFe;
import br.inf.portalfiscal.nfe.schema_4.inutNFe.TProcInutNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.inf.portalfiscal.nfe.schema_4.util.XsdUtil;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.zip.GZIPInputStream;

/**
 * Classe Responsavel por Metodos referentes ao XML
 *
 * @author Samuel Oliveira
 */
public class XmlUtil {

    private static final String STATUS = "TConsStatServ";
    private static final String SITUACAO_NFE = "TConsSitNFe";
    private static final String ENVIO_NFE = "TEnviNFe";
    private static final String DIST_DFE = "DistDFeInt";
    private static final String INUTILIZACAO = "TInutNFe";
    private static final String NFEPROC = "TNfeProc";
    private static final String EVENTO = "TEnvEvento";
    private static final String TPROCEVENTO = "TProcEvento";
    private static final String TCONSRECINFE = "TConsReciNFe";
    private static final String TConsCad = "TConsCad";
    private static final String TPROCINUT = "TProcInutNFe";
    private static final String RETORNO_ENVIO = "TRetEnviNFe";
    private static final String SITUACAO_NFE_RET = "TRetConsSitNFe";

    private static final String TPROCCANCELAR = "br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TProcEvento";
    private static final String TPROCCCE = "br.inf.portalfiscal.nfe.schema.envcce.TProcEvento";

    private static final String TProtNFe = "TProtNFe";
    private static final String TProtEnvi = "br.inf.portalfiscal.nfe.schema_4.enviNFe.TProtNFe";
    private static final String TProtCons = "br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TProtNFe";
    private static final String TProtReci = "br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TProtNFe";

    private static final String CANCELAR = "br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento";
    private static final String CCE = "br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento";
    private static final String MANIFESTAR = "br.inf.portalfiscal.nfe.schema.envConfRecebto.TEnvEvento";

    /**
     * Transforma o String do XML em Objeto
     *
     * @param xml
     * @param classe
     * @return T
     */
    public static <T> T xmlToObject(String xml, Class<T> classe) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(classe);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), classe).getValue();
    }

    /**
     * Transforma o Objeto em XML(String)
     *
     * @param obj
     * @return
     * @throws JAXBException
     * @throws NfeException
     */
    public static <T> String objectToXml(Object obj) throws JAXBException, NfeException {

        JAXBContext context = null;
        JAXBElement<?> element = null;

        switch (obj.getClass().getSimpleName()) {

            case STATUS:
                context = JAXBContext.newInstance(TConsStatServ.class);
                element = new br.inf.portalfiscal.nfe.schema_4.consStatServ.ObjectFactory().createConsStatServ((TConsStatServ) obj);
                break;

            case ENVIO_NFE:
                context = JAXBContext.newInstance(TEnviNFe.class);
                element = new br.inf.portalfiscal.nfe.schema_4.enviNFe.ObjectFactory().createEnviNFe((TEnviNFe) obj);
                break;

            case RETORNO_ENVIO:
                context = JAXBContext.newInstance(TRetEnviNFe.class);
                element = XsdUtil.enviNfe.createTRetEnviNFe((TRetEnviNFe) obj);
                break;

            case SITUACAO_NFE:
                context = JAXBContext.newInstance(TConsSitNFe.class);
                element = new br.inf.portalfiscal.nfe.schema_4.consSitNFe.ObjectFactory().createConsSitNFe((TConsSitNFe) obj);
                break;

            case DIST_DFE:
                context = JAXBContext.newInstance(DistDFeInt.class);
                element = new br.inf.portalfiscal.nfe.schema.distdfeint.ObjectFactory().createDistDFeInt((DistDFeInt) obj);
                break;

            case TCONSRECINFE:
                context = JAXBContext.newInstance(TConsReciNFe.class);
                element = new br.inf.portalfiscal.nfe.schema_4.consReciNFe.ObjectFactory().createConsReciNFe((TConsReciNFe) obj);
                break;

            case TConsCad:
                context = JAXBContext.newInstance(TConsCad.class);
                element = new br.inf.portalfiscal.nfe.schema.consCad.ObjectFactory().createConsCad((TConsCad) obj);
                break;

            case INUTILIZACAO:
                context = JAXBContext.newInstance(TInutNFe.class);
                element = new br.inf.portalfiscal.nfe.schema_4.inutNFe.ObjectFactory().createInutNFe((TInutNFe) obj);
                break;

            case SITUACAO_NFE_RET:
                context = JAXBContext.newInstance(TRetConsSitNFe.class);
                element = new br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.ObjectFactory().createRetConsSitNFe((TRetConsSitNFe) obj);
                break;

            case TPROCEVENTO:
                if (obj.getClass().getName().equals(TPROCCANCELAR)) {
                    context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TProcEvento.class);
                    element = new br.inf.portalfiscal.nfe.schema.envEventoCancNFe.ObjectFactory().createTProcEvento((br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TProcEvento) obj);
                } else if (obj.getClass().getName().equals(TPROCCCE)) {
                    context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema.envcce.TProcEvento.class);
                    element = new br.inf.portalfiscal.nfe.schema.envcce.ObjectFactory().createTProcEvento((br.inf.portalfiscal.nfe.schema.envcce.TProcEvento) obj);
                }

                break;

            case NFEPROC:
                context = JAXBContext.newInstance(TNfeProc.class);
                element = XsdUtil.enviNfe.createTNfeProc((TNfeProc) obj);
                break;

            case TPROCINUT:
                context = JAXBContext.newInstance(TProcInutNFe.class);
                element = XsdUtil.inutNfe.createTProcInutNFe((TProcInutNFe) obj);
                break;

            case EVENTO:
                switch (obj.getClass().getName()) {
                    case CANCELAR:
                        context = JAXBContext.newInstance(TEnvEvento.class);
                        element = new br.inf.portalfiscal.nfe.schema.envEventoCancNFe.ObjectFactory().createEnvEvento((TEnvEvento) obj);
                        break;
                    case CCE:
                        context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento.class);
                        element = new br.inf.portalfiscal.nfe.schema.envcce.ObjectFactory().createEnvEvento((br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento) obj);
                        break;
                    case MANIFESTAR:
                        context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema.envConfRecebto.TEnvEvento.class);
                        element = new br.inf.portalfiscal.nfe.schema.envConfRecebto.ObjectFactory().createEnvEvento((br.inf.portalfiscal.nfe.schema.envConfRecebto.TEnvEvento) obj);
                        break;
                }
                break;

            case TProtNFe:
                switch (obj.getClass().getName()) {
                    case TProtEnvi:
                        context = JAXBContext.newInstance(TProtNFe.class);
                        element = XsdUtil.enviNfe.createTProtNFe((br.inf.portalfiscal.nfe.schema_4.enviNFe.TProtNFe) obj);
                        break;
                    case TProtCons:
                        context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TProtNFe.class);
                        element = XsdUtil.retConsSitNfe.createTProtNFe((br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TProtNFe) obj);
                        break;
                    case TProtReci:
                        context = JAXBContext.newInstance(br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TProtNFe.class);
                        element = XsdUtil.retConsReciNfe.createTProtNFe((br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TProtNFe) obj);
                        break;
                }
                break;

            default:
                throw new NfeException("Objeto não mapeado no XmlUtil:" + obj.getClass().getSimpleName());
        }
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty("jaxb.encoding", "Unicode");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter sw = new StringWriter();

        if (obj.getClass().getSimpleName().equals(ENVIO_NFE) || obj.getClass().getSimpleName().equals(NFEPROC)) {
            try {
                CDATAContentHandler cdataHandler = new CDATAContentHandler(sw, "utf-8");
                marshaller.marshal(element, cdataHandler);
            } catch (IOException e) {
                throw new NfeException(e.getMessage());
            }
        } else {
            marshaller.marshal(element, sw);
        }
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(sw.toString());

        if ((obj.getClass().getSimpleName().equals(TPROCEVENTO))) {
            return replacesNfe(xml.toString().replaceAll("procEvento", "procEventoNFe"));
        } else {
            return replacesNfe(xml.toString());
        }

    }

    public static String gZipToXml(byte[] conteudo) throws IOException {
        if (conteudo == null || conteudo.length == 0) {
            return "";
        }
        GZIPInputStream gis;
        gis = new GZIPInputStream(new ByteArrayInputStream(conteudo));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuilder outStr = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            outStr.append(line);
        }

        return outStr.toString();
    }

    public static String criaNfeProc(TEnviNFe enviNfe, Object retorno) throws JAXBException, NfeException {

        TNfeProc nfeProc = new TNfeProc();
        nfeProc.setVersao("4.00");
        nfeProc.setNFe(enviNfe.getNFe().get(0));
        String xml = XmlUtil.objectToXml(retorno);
        nfeProc.setProtNFe(XmlUtil.xmlToObject(xml, TProtNFe.class));

        return XmlUtil.objectToXml(nfeProc);
    }

    public static String removeAcentos(String str) {

        str = str.replaceAll("\r", "");
        str = str.replaceAll("\t", "");
        str = str.replaceAll("\n", "");
        str = str.replaceAll("&", "E");
        str = str.replaceAll(">\\s+<", "><");
        CharSequence cs = new StringBuilder(str == null ? "" : str);
        return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

    }

    private static String replacesNfe(String xml) {

        xml = xml.replaceAll("ns2:", "");
        xml = xml.replaceAll("<!\\[CDATA\\[<!\\[CDATA\\[", "<!\\[CDATA\\[");
        xml = xml.replaceAll("\\]\\]>\\]\\]>", "\\]\\]>");
        xml = xml.replaceAll("ns3:", "");
        xml = xml.replaceAll("&lt;", "<");
        xml = xml.replaceAll("&amp;", "&");
        xml = xml.replaceAll("&gt;", ">");
        xml = xml.replaceAll("<Signature>", "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">");
        xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
        xml = xml.replaceAll(" xmlns=\"\" xmlns:ns3=\"http://www.portalfiscal.inf.br/nfe\"", "");

        return xml;

    }

    /**
     * Le o Arquivo XML e retona String
     *
     * @return String
     * @throws NfeException
     */
    public static String leXml(String arquivo) throws NfeException {

        StringBuilder xml = new StringBuilder();
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
            String linha;

            while ((linha = in.readLine()) != null) {
                xml.append(linha);

            }
            in.close();
        } catch (IOException e) {
            throw new NfeException("Ler Xml: " + e.getMessage());
        }
        return xml.toString();
    }

    public static String dataNfe() throws NfeException {
        try {
            LocalDateTime dataASerFormatada = LocalDateTime.now();
            GregorianCalendar calendar = GregorianCalendar.from(dataASerFormatada.atZone(ZoneId.of("Brazil/East")));

            XMLGregorianCalendar  xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            xmlCalendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);

            return (xmlCalendar.toString());
        } catch (DatatypeConfigurationException e) {
            throw new NfeException(e.getMessage());
        }

    }

}
