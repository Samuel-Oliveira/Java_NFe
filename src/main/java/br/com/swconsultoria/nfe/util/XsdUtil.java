package br.com.swconsultoria.nfe.util;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * @author Samuel Oliveira - samuel@swconsultoria.com.br
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
    private static final QName _TDistDFeInt_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "distDFeInt");
    private static final QName _TRetDistDFeInt_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "retDistDFeInt");

    public interface enviNfe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TNfeProc", scope = br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc> createTNfeProc(br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc>(_TNfeProc_QNAME, br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc.class, br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProtNFe", scope = br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe> createTProtNFe(br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe>(_TProtNFe_QNAME, br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe.class, br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnviNFe", scope = br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe> createTRetEnviNFe(br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe>(_TRetEnviNFe_QNAME, br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe.class, br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe.class, value);
        }
    }

    public interface distDFeInt {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "distDFeInt", scope = br.com.swconsultoria.nfe.schema.distdfeint.DistDFeInt.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.distdfeint.DistDFeInt> createDistDFeInt(br.com.swconsultoria.nfe.schema.distdfeint.DistDFeInt value) {
            return new JAXBElement<>(_TDistDFeInt_QNAME, br.com.swconsultoria.nfe.schema.distdfeint.DistDFeInt.class, br.com.swconsultoria.nfe.schema.distdfeint.DistDFeInt.class, value);
        }
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "retDistDFeInt", scope = br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt> createRetDistDFeInt(br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt value) {
            return new JAXBElement<>(_TRetDistDFeInt_QNAME, br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt.class,
                    br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt.class, value);
        }
    }

    public interface retConsReciNfe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "protNFe", scope = br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TProtNFe.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TProtNFe> createTProtNFe(br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TProtNFe value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TProtNFe>(_TProtNFe_QNAME, br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TProtNFe.class, br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TProtNFe.class, value);
        }
    }

    public interface retConsSitNfe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProtNFe", scope = br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TProtNFe.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TProtNFe> createTProtNFe(br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TProtNFe value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TProtNFe>(_TProtNFe_QNAME, br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TProtNFe.class, br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TProtNFe.class, value);
        }
    }

    public interface retEnviNfe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProtNFe", scope = br.com.swconsultoria.nfe.schema_4.retEnviNFe.TProtNFe.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema_4.retEnviNFe.TProtNFe> createTProtNFe(br.com.swconsultoria.nfe.schema_4.retEnviNFe.TProtNFe value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema_4.retEnviNFe.TProtNFe>(_TProtNFe_QNAME, br.com.swconsultoria.nfe.schema_4.retEnviNFe.TProtNFe.class, br.com.swconsultoria.nfe.schema_4.retEnviNFe.TProtNFe.class, value);
        }
    }

    public interface inutNfe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProcInutNFe", scope = br.com.swconsultoria.nfe.schema_4.inutNFe.TProcInutNFe.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema_4.inutNFe.TProcInutNFe> createTProcInutNFe(br.com.swconsultoria.nfe.schema_4.inutNFe.TProcInutNFe value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema_4.inutNFe.TProcInutNFe>(_TProcInutNFe_QNAME, br.com.swconsultoria.nfe.schema_4.inutNFe.TProcInutNFe.class, br.com.swconsultoria.nfe.schema_4.inutNFe.TProcInutNFe.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetInutNFe", scope = br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe> createTRetInutNfe(br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe>(_TProcInutNFe_QNAME, br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe.class, br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe.class, value);
        }


    }

    public interface epec {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProcEvento", scope = br.com.swconsultoria.nfe.schema.envEpec.TProcEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envEpec.TProcEvento> createTProcEvento(br.com.swconsultoria.nfe.schema.envEpec.TProcEvento value) {
            return new JAXBElement<>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envEpec.TProcEvento.class, br.com.swconsultoria.nfe.schema.envEpec.TProcEvento.class, value);
        }
    }

    public interface envEventoAtorInteressado {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProcEvento", scope = br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TProcEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TProcEvento> createTProcEvento(br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TProcEvento value) {
            return new JAXBElement<>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TProcEvento.class, br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TProcEvento.class, value);
        }
    }

    public interface envEventoCancNFe {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProcEvento", scope = br.com.swconsultoria.nfe.schema.envEventoCancNFe.TProcEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envEventoCancNFe.TProcEvento> createTProcEvento(br.com.swconsultoria.nfe.schema.envEventoCancNFe.TProcEvento value) {
            return new JAXBElement<>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envEventoCancNFe.TProcEvento.class, br.com.swconsultoria.nfe.schema.envEventoCancNFe.TProcEvento.class, value);
        }
    }

    public interface envEventoCancSubst {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProcEvento", scope = br.com.swconsultoria.nfe.schema.envEventoCancSubst.TProcEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envEventoCancSubst.TProcEvento> createTProcEvento(br.com.swconsultoria.nfe.schema.envEventoCancSubst.TProcEvento value) {
            return new JAXBElement<>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envEventoCancSubst.TProcEvento.class, br.com.swconsultoria.nfe.schema.envEventoCancSubst.TProcEvento.class, value);
        }
    }
    public interface envcce {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProcEvento", scope = br.com.swconsultoria.nfe.schema.envcce.TProcEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envcce.TProcEvento> createTProcEvento(br.com.swconsultoria.nfe.schema.envcce.TProcEvento value) {
            return new JAXBElement<>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envcce.TProcEvento.class, br.com.swconsultoria.nfe.schema.envcce.TProcEvento.class, value);
        }
    }

    public interface manifestacao {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TProcEvento", scope = br.com.swconsultoria.nfe.schema.envConfRecebto.TProcEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envConfRecebto.TProcEvento> createTProcEvento(br.com.swconsultoria.nfe.schema.envConfRecebto.TProcEvento value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema.envConfRecebto.TProcEvento>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envConfRecebto.TProcEvento.class, br.com.swconsultoria.nfe.schema.envConfRecebto.TProcEvento.class, value);
        }
    }

    public interface retEnvEvento {
        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnvEvento", scope = br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento> createTRetEnvEvento(br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento.class, br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnvEvento", scope = br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento> createTRetEnvEvento(br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento.class, br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnvEvento", scope = br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento> createTRetEnvEvento(br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento.class, br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnvEvento", scope = br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento> createTRetEnvEvento(br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento.class, br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnvEvento", scope = br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento> createTRetEnvEvento(br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento.class, br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento.class, value);
        }

        @XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "TRetEnvEvento", scope = br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TRetEnvEvento.class)
        static JAXBElement<br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TRetEnvEvento> createTRetEnvEvento(br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TRetEnvEvento value) {
            return new JAXBElement<br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TRetEnvEvento>(_TProcEvento_QNAME, br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TRetEnvEvento.class, br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TRetEnvEvento.class, value);
        }
    }

}
