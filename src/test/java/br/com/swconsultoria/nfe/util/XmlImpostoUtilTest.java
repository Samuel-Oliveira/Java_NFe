package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.*;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISQtde;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBElement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlImpostoUtilTest {

    private static final ObjectFactory FACTORY = new ObjectFactory();

    // -------------------------------------------------------------------------
    // getVPIS
    // -------------------------------------------------------------------------

    @Test
    void getVPIS_listaVazia_retornaZero() {
        assertEquals(BigDecimal.ZERO, XmlImpostoUtil.getVPIS(Collections.emptyList()));
    }

    @Test
    void getVPIS_pisAliq_retornaValor() {
        PIS pis = new PIS();
        PISAliq pisAliq = new PISAliq();
        pisAliq.setVPIS("16.81");
        pis.setPISAliq(pisAliq);

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoPIS(pis));

        assertEquals(new BigDecimal("16.81"), XmlImpostoUtil.getVPIS(impostos));
    }

    @Test
    void getVPIS_pisQtde_retornaValor() {
        PIS pis = new PIS();
        PISQtde pisQtde = new PISQtde();
        pisQtde.setVPIS("5.00");
        pis.setPISQtde(pisQtde);

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoPIS(pis));

        assertEquals(new BigDecimal("5.00"), XmlImpostoUtil.getVPIS(impostos));
    }

    @Test
    void getVPIS_tipoErrado_retornaZero() {
        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoICMS(new ICMS()));

        assertEquals(BigDecimal.ZERO, XmlImpostoUtil.getVPIS(impostos));
    }

    // -------------------------------------------------------------------------
    // getVCOFINS
    // -------------------------------------------------------------------------

    @Test
    void getVCOFINS_listaVazia_retornaZero() {
        assertEquals(BigDecimal.ZERO, XmlImpostoUtil.getVCOFINS(Collections.emptyList()));
    }

    @Test
    void getVCOFINS_cofinsAliq_retornaValor() {
        COFINS cofins = new COFINS();
        COFINSAliq cofinsAliq = new COFINSAliq();
        cofinsAliq.setVCOFINS("77.41");
        cofins.setCOFINSAliq(cofinsAliq);

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoCOFINS(cofins));

        assertEquals(new BigDecimal("77.41"), XmlImpostoUtil.getVCOFINS(impostos));
    }

    @Test
    void getVCOFINS_cofinsQtde_retornaValor() {
        COFINS cofins = new COFINS();
        COFINSQtde cofinsQtde = new COFINSQtde();
        cofinsQtde.setVCOFINS("10.00");
        cofins.setCOFINSQtde(cofinsQtde);

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoCOFINS(cofins));

        assertEquals(new BigDecimal("10.00"), XmlImpostoUtil.getVCOFINS(impostos));
    }

    // -------------------------------------------------------------------------
    // getVICMS
    // -------------------------------------------------------------------------

    @Test
    void getVICMS_listaVazia_retornaZero() {
        assertEquals(BigDecimal.ZERO, XmlImpostoUtil.getVICMS(Collections.emptyList()));
    }

    @Test
    void getVICMS_icms00_retornaValor() {
        ICMS icms = new ICMS();
        ICMS.ICMS00 icms00 = new ICMS.ICMS00();
        icms00.setVICMS("208.63");
        icms.setICMS00(icms00);

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoICMS(icms));

        assertEquals(new BigDecimal("208.63"), XmlImpostoUtil.getVICMS(impostos));
    }

    @Test
    void getVICMS_icms10_retornaValor() {
        ICMS icms = new ICMS();
        ICMS.ICMS10 icms10 = new ICMS.ICMS10();
        icms10.setVICMS("100.00");
        icms.setICMS10(icms10);

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoICMS(icms));

        assertEquals(new BigDecimal("100.00"), XmlImpostoUtil.getVICMS(impostos));
    }

    @Test
    void getVICMS_semVICMSPreenchido_retornaZero() {
        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoICMS(new ICMS()));

        assertEquals(BigDecimal.ZERO, XmlImpostoUtil.getVICMS(impostos));
    }

    // -------------------------------------------------------------------------
    // getVICMSUFDest
    // -------------------------------------------------------------------------

    @Test
    void getVICMSUFDest_listaVazia_retornaZero() {
        assertEquals(BigDecimal.ZERO, XmlImpostoUtil.getVICMSUFDest(Collections.emptyList()));
    }

    @Test
    void getVICMSUFDest_comValor_retornaValor() {
        ICMSUFDest icmsUfDest = new ICMSUFDest();
        icmsUfDest.setVICMSUFDest("45.00");

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoICMSUFDest(icmsUfDest));

        assertEquals(new BigDecimal("45.00"), XmlImpostoUtil.getVICMSUFDest(impostos));
    }

    // -------------------------------------------------------------------------
    // getVFCP
    // -------------------------------------------------------------------------

    @Test
    void getVFCP_listaVazia_retornaZero() {
        assertEquals(BigDecimal.ZERO, XmlImpostoUtil.getVFCP(Collections.emptyList()));
    }

    @Test
    void getVFCP_icms00ComFCP_retornaValor() {
        ICMS icms = new ICMS();
        ICMS.ICMS00 icms00 = new ICMS.ICMS00();
        icms00.setVFCP("12.50");
        icms.setICMS00(icms00);

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoICMS(icms));

        assertEquals(new BigDecimal("12.50"), XmlImpostoUtil.getVFCP(impostos));
    }

    // -------------------------------------------------------------------------
    // getVFCPUFDest
    // -------------------------------------------------------------------------

    @Test
    void getVFCPUFDest_listaVazia_retornaZero() {
        assertEquals(BigDecimal.ZERO, XmlImpostoUtil.getVFCPUFDest(Collections.emptyList()));
    }

    @Test
    void getVFCPUFDest_comValor_retornaValor() {
        ICMSUFDest icmsUfDest = new ICMSUFDest();
        icmsUfDest.setVFCPUFDest("8.00");

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoICMSUFDest(icmsUfDest));

        assertEquals(new BigDecimal("8.00"), XmlImpostoUtil.getVFCPUFDest(impostos));
    }

    // -------------------------------------------------------------------------
    // getVICMSMono
    // -------------------------------------------------------------------------

    @Test
    void getVICMSMono_listaVazia_retornaZero() {
        assertEquals(BigDecimal.ZERO, XmlImpostoUtil.getVICMSMono(Collections.emptyList()));
    }

    @Test
    void getVICMSMono_icms02_retornaValor() {
        ICMS icms = new ICMS();
        ICMS.ICMS02 icms02 = new ICMS.ICMS02();
        icms02.setVICMSMono("30.00");
        icms.setICMS02(icms02);

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoICMS(icms));

        assertEquals(new BigDecimal("30.00"), XmlImpostoUtil.getVICMSMono(impostos));
    }

    // -------------------------------------------------------------------------
    // getVISSQN
    // -------------------------------------------------------------------------

    @Test
    void getVISSQN_listaVazia_retornaZero() {
        assertEquals(BigDecimal.ZERO, XmlImpostoUtil.getVISSQN(Collections.emptyList()));
    }

    @Test
    void getVISSQN_comValor_retornaValor() {
        ISSQN issqn = new ISSQN();
        issqn.setVISSQN("99.99");

        List<JAXBElement<?>> impostos = new ArrayList<>();
        impostos.add(FACTORY.createTNFeInfNFeDetImpostoISSQN(issqn));

        assertEquals(new BigDecimal("99.99"), XmlImpostoUtil.getVISSQN(impostos));
    }
}
