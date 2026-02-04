package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIBSCBSMonoTot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TTribNFe;
import br.com.swconsultoria.nfe.util.IbsCbsUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Samuel Oliveira
 */
class IbsCbsTeste {

    @Test
    void testeIbsCbs() throws Exception {

        ConfiguracoesNfe config = getConfiguracoesNfe();
        TEnviNFe enviNFe = getEnviNFe();
        String json = getIbsCbsJson();
        String cclassTrib = "000001";

        IbsCbsUtil ibsCbsUtil = new IbsCbsUtil(json, DocumentoEnum.NFE);

        for (TNFe.InfNFe.Det det : enviNFe.getNFe().get(0).getInfNFe().getDet()) {
            TTribNFe ibsCbs = ibsCbsUtil.montaImpostosDet(cclassTrib, det);
            JAXBElement<TTribNFe> ibsCbsElement = new JAXBElement<>(new QName("IBSCBS"), TTribNFe.class, ibsCbs);
            det.getImposto().getContent().add(ibsCbsElement);
        }

        enviNFe = addTotaisIbsCbs(ibsCbsUtil, enviNFe, config);

        String esperado = XmlNfeUtil.leXml("src/test/resources/IbsCbs.xml");
        assertEquals(esperado, XmlNfeUtil.objectToXml(enviNFe));
    }

    @Test
    void testeIbsCbsRegular() throws Exception {

        ConfiguracoesNfe config = getConfiguracoesNfe();
        TEnviNFe enviNFe = getEnviNFe();
        String json = getIbsCbsJson();
        String cclassTrib = "550001";
        String cclassTribRegular = "000001";

        IbsCbsUtil ibsCbsUtil = new IbsCbsUtil(json, DocumentoEnum.NFE);

        for (TNFe.InfNFe.Det det : enviNFe.getNFe().get(0).getInfNFe().getDet()) {
            TTribNFe ibsCbs = ibsCbsUtil.montaImpostosDet(cclassTrib, det, cclassTribRegular);
            JAXBElement<TTribNFe> ibsCbsElement = new JAXBElement<>(new QName("IBSCBS"), TTribNFe.class, ibsCbs);
            det.getImposto().getContent().add(ibsCbsElement);

        }

        enviNFe = addTotaisIbsCbs(ibsCbsUtil, enviNFe, config);

        String esperado = XmlNfeUtil.leXml("src/test/resources/IbsCbsRegular.xml");
        assertEquals(esperado, XmlNfeUtil.objectToXml(enviNFe));
    }

    @Test
    void testeIbsCbsMonofasico() throws Exception {

        ConfiguracoesNfe config = getConfiguracoesNfe();
        TEnviNFe enviNFe = getEnviNFe();
        String json = getIbsCbsJson();
        String cclassTrib = "620006";

        IbsCbsUtil ibsCbsUtil = new IbsCbsUtil(json, DocumentoEnum.NFE);

        for (TNFe.InfNFe.Det det : enviNFe.getNFe().get(0).getInfNFe().getDet()) {
            TTribNFe ibsCbs = ibsCbsUtil.montaImpostosDet(cclassTrib, det);
            JAXBElement<TTribNFe> ibsCbsElement = new JAXBElement<>(new QName("IBSCBS"), TTribNFe.class, ibsCbs);
            det.getImposto().getContent().add(ibsCbsElement);

        }

        enviNFe = addTotaisIbsCbs(ibsCbsUtil, enviNFe, config);

        String esperado = XmlNfeUtil.leXml("src/test/resources/IbsCbsMonofasico.xml");
        assertEquals(esperado, XmlNfeUtil.objectToXml(enviNFe));
    }

    @Test
    void testeIbsCbsDiferimento() throws Exception {

        ConfiguracoesNfe config = getConfiguracoesNfe();
        TEnviNFe enviNFe = getEnviNFe();
        String json = getIbsCbsJson();
        String cclassTrib = "515001";

        IbsCbsUtil ibsCbsUtil = new IbsCbsUtil(json, DocumentoEnum.NFE);

        for (TNFe.InfNFe.Det det : enviNFe.getNFe().get(0).getInfNFe().getDet()) {
            ibsCbsUtil.setpAliqDiferimento(BigDecimal.valueOf(100));
            TTribNFe ibsCbs = ibsCbsUtil.montaImpostosDet(cclassTrib, det);
            JAXBElement<TTribNFe> ibsCbsElement = new JAXBElement<>(new QName("IBSCBS"), TTribNFe.class, ibsCbs);
            det.getImposto().getContent().add(ibsCbsElement);
        }

        enviNFe = addTotaisIbsCbs(ibsCbsUtil, enviNFe, config);

        String esperado = XmlNfeUtil.leXml("src/test/resources/IbsCbsDiferimento.xml");
        assertEquals(esperado, XmlNfeUtil.objectToXml(enviNFe));
    }

    private static TEnviNFe addTotaisIbsCbs(IbsCbsUtil ibsCbsUtil, TEnviNFe enviNFe, ConfiguracoesNfe config) throws NfeException {
        BigDecimal vNfTot = ibsCbsUtil.calculaVnfTot(enviNFe.getNFe().get(0).getInfNFe().getTotal().getICMSTot().getVNF());
        enviNFe.getNFe().get(0).getInfNFe().getTotal().setVNFTot(ObjetoUtil.getValor2Casas(vNfTot));

        TIBSCBSMonoTot totaisIbsCsb = ibsCbsUtil.preencheTotaisIbsCsb();
        enviNFe.getNFe().get(0).getInfNFe().getTotal().setIBSCBSTot(totaisIbsCsb);
        return Nfe.montaNfe(config, enviNFe, true);
    }

    private static String getIbsCbsJson() throws IOException {
        return XmlNfeUtil.leXml("src/test/resources/ibscbs.json");
    }

    private static TEnviNFe getEnviNFe() throws IOException {
        String xml = XmlNfeUtil.leXml("src/test/resources/TesteXml.xml");
        return XmlNfeUtil.xmlToObject(xml, TEnviNFe.class);
    }

    private static ConfiguracoesNfe getConfiguracoesNfe() throws Exception {
        return ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);
    }

}
