package br.com.samuelweb.nfe.util;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 26/01/2018 - 23:19
 */
@XmlRegistry
public class XsdUtil {

    private final static QName _TNfeProc_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "nfeProc");
    private final static QName _TProtNFe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "protNFe");
    private final static QName _TProcInutNFe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "procInutNFe");
    private final static QName _TRetEnviNFe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "retEnviNFe");
    private static final QName _TProcEvento_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "procEvento");
    private static final QName _TRetEnvEvento_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "retEnvEvento");
    private static final QName _TRetInutNFe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "retInutNFe");

    public interface enviNfe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TNfeProc", scope = br.inf.portalfiscal.nfe.schema_4.enviNFe.TNfeProc.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema_4.enviNFe.TNfeProc> createTNfeProc(br.inf.portalfiscal.nfe.schema_4.enviNFe.TNfeProc value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema_4.enviNFe.TNfeProc>(_TNfeProc_QNAME, br.inf.portalfiscal.nfe.schema_4.enviNFe.TNfeProc.class, br.inf.portalfiscal.nfe.schema_4.enviNFe.TNfeProc.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProtNFe", scope = br.inf.portalfiscal.nfe.schema_4.enviNFe.TProtNFe.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema_4.enviNFe.TProtNFe> createTProtNFe(br.inf.portalfiscal.nfe.schema_4.enviNFe.TProtNFe value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema_4.enviNFe.TProtNFe>(_TProtNFe_QNAME, br.inf.portalfiscal.nfe.schema_4.enviNFe.TProtNFe.class, br.inf.portalfiscal.nfe.schema_4.enviNFe.TProtNFe.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnviNFe", scope = br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe> createTRetEnviNFe(br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe>(_TRetEnviNFe_QNAME, br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe.class, br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe.class, value);
        }
    }

    public interface retConsReciNfe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "protNFe", scope = br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TProtNFe.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TProtNFe> createTProtNFe(br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TProtNFe value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TProtNFe>(_TProtNFe_QNAME, br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TProtNFe.class, br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TProtNFe.class, value);
        }
    }

    public interface retConsSitNfe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProtNFe", scope = br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TProtNFe.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TProtNFe> createTProtNFe(br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TProtNFe value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TProtNFe>(_TProtNFe_QNAME, br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TProtNFe.class, br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TProtNFe.class, value);
        }
    }

    public interface retEnviNfe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProtNFe", scope = br.inf.portalfiscal.nfe.schema_4.retEnviNFe.TProtNFe.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema_4.retEnviNFe.TProtNFe> createTProtNFe(br.inf.portalfiscal.nfe.schema_4.retEnviNFe.TProtNFe value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema_4.retEnviNFe.TProtNFe>(_TProtNFe_QNAME, br.inf.portalfiscal.nfe.schema_4.retEnviNFe.TProtNFe.class, br.inf.portalfiscal.nfe.schema_4.retEnviNFe.TProtNFe.class, value);
        }
    }

    public interface inutNfe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProcInutNFe", scope = br.inf.portalfiscal.nfe.schema_4.inutNFe.TProcInutNFe.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema_4.inutNFe.TProcInutNFe> createTProcInutNFe(br.inf.portalfiscal.nfe.schema_4.inutNFe.TProcInutNFe value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema_4.inutNFe.TProcInutNFe>(_TProcInutNFe_QNAME, br.inf.portalfiscal.nfe.schema_4.inutNFe.TProcInutNFe.class, br.inf.portalfiscal.nfe.schema_4.inutNFe.TProcInutNFe.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetInutNFe", scope = br.inf.portalfiscal.nfe.schema_4.inutNFe.TRetInutNFe.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema_4.inutNFe.TRetInutNFe> createTRetInutNfe(br.inf.portalfiscal.nfe.schema_4.inutNFe.TRetInutNFe value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema_4.inutNFe.TRetInutNFe>(_TProcInutNFe_QNAME, br.inf.portalfiscal.nfe.schema_4.inutNFe.TRetInutNFe.class, br.inf.portalfiscal.nfe.schema_4.inutNFe.TRetInutNFe.class, value);
        }


    }

    public interface epec {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProcEvento", scope = br.inf.portalfiscal.nfe.schema.envEpec.TProcEvento.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema.envEpec.TProcEvento> createTProcEvento(br.inf.portalfiscal.nfe.schema.envEpec.TProcEvento value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema.envEpec.TProcEvento>(_TProcEvento_QNAME, br.inf.portalfiscal.nfe.schema.envEpec.TProcEvento.class, br.inf.portalfiscal.nfe.schema.envEpec.TProcEvento.class, value);
        }
    }

    public interface retEnvEvento {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnvEvento", scope = br.inf.portalfiscal.nfe.schema.envEpec.TRetEnvEvento.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema.envEpec.TRetEnvEvento> createTRetEnvEvento(br.inf.portalfiscal.nfe.schema.envEpec.TRetEnvEvento value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema.envEpec.TRetEnvEvento>(_TProcEvento_QNAME, br.inf.portalfiscal.nfe.schema.envEpec.TRetEnvEvento.class, br.inf.portalfiscal.nfe.schema.envEpec.TRetEnvEvento.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnvEvento", scope = br.inf.portalfiscal.nfe.schema.envConfRecebto.TRetEnvEvento.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema.envConfRecebto.TRetEnvEvento> createTRetEnvEvento(br.inf.portalfiscal.nfe.schema.envConfRecebto.TRetEnvEvento value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema.envConfRecebto.TRetEnvEvento>(_TProcEvento_QNAME, br.inf.portalfiscal.nfe.schema.envConfRecebto.TRetEnvEvento.class, br.inf.portalfiscal.nfe.schema.envConfRecebto.TRetEnvEvento.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnvEvento", scope = br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento> createTRetEnvEvento(br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento>(_TProcEvento_QNAME, br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento.class, br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnvEvento", scope = br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento.class)
        static JAXBElement<br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento> createTRetEnvEvento(br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento value) {
            return new JAXBElement<br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento>(_TProcEvento_QNAME, br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento.class, br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento.class, value);
        }
    }

}
