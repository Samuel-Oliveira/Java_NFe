package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.Validar;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.schemas.TEnviNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Valida a NFCe de combustiveis montada por {@link EnvioNfceTeste} sem enviar para a
 * SEFAZ: confere a estrutura dos grupos monofasicos de IBS/CBS e roda o XML completo
 * contra o XSD, que e o mesmo arquivo que a SEFAZ usa para rejeitar na recepcao.
 */
class EnvioNfceCombustivelTest {

    /** CNPJ do NAO_UTILIZE.pfx; a assinatura exige que bata com o emitente. */
    private static final String CNPJ_CERTIFICADO = "99999999999999";

    @Test
    void deveMontarNfceDeCombustivelValidaContraOSchema() throws Exception {
        String xml = XmlNfeUtil.objectToXml(EnvioNfceTeste.montaEnviNFe(configuracoes(), CNPJ_CERTIFICADO, 2026));

        String xsd = Paths.get("schemas", ServicosEnum.ENVIO.getXsd()).toString();
        assertTrue(new Validar().isValidXml(xsd, xml),
                "A NFCe de combustivel deve validar contra o XSD de envio");
    }

    @Test
    void deveEmitirGrupoMonofasicoRetidoParaOsTresCombustiveis() throws Exception {
        TEnviNFe enviNFe = EnvioNfceTeste.montaEnviNFe(configuracoes(), CNPJ_CERTIFICADO, 2026);
        String xml = XmlNfeUtil.objectToXml(enviNFe);

        assertEquals(3, enviNFe.getNFe().get(0).getInfNFe().getDet().size(),
                "a nota deve ter gasolina, etanol e diesel");
        assertEquals(3, contar(xml, "<gIBSCBSMono>"), "cada item deve ter o grupo monofasico");
        // Em 2026 os dois eixos sao ad valorem (NT 2025.002-RTC, UB85a-10 e UB95a-10).
        // Informar ad rem neste ano e rejeitado com cStat 1219 (IBS) ou 1245 (CBS),
        // confirmado em homologacao da SEFAZ-GO. A partir de 2027 a CBS passa a ad rem.
        assertEquals(3, contar(xml, "<gIBSMonoAdValorem>"), "em 2026 o IBS e ad valorem");
        assertEquals(3, contar(xml, "<gCBSMonoAdValorem>"), "em 2026 a CBS e ad valorem");
        assertEquals(0, contar(xml, "<gIBSMonoAdRem>"), "ad rem do IBS so a partir de 2029");
        assertEquals(0, contar(xml, "<gCBSMonoAdRem>"), "ad rem da CBS so a partir de 2027");

        // cClassTrib 620006 e monofasia retida anteriormente: so sai o gMonoRet
        assertEquals(0, contar(xml, "<gMonoPadrao>"), "620006 nao tem monofasia padrao");
        assertEquals(0, contar(xml, "<gMonoReten>"), "620006 nao e sujeito a retencao");

        assertTrue(xml.contains("<cProdANP>320102001</cProdANP>"), "gasolina comum");
        assertTrue(xml.contains("<cProdANP>810101001</cProdANP>"), "etanol comum");
        assertTrue(xml.contains("<cProdANP>820101012</cProdANP>"), "oleo diesel");
    }

    @Test
    void naoDeveInformarValorNoRetidoAnteriormentePorqueASefazCalcula() throws Exception {
        TEnviNFe enviNFe = EnvioNfceTeste.montaEnviNFe(configuracoes(), CNPJ_CERTIFICADO, 2026);
        String xml = XmlNfeUtil.objectToXml(enviNFe);

        assertTrue(xml.contains("<IBSCBSTot>"), "os totais de IBS/CBS devem estar presentes");

        // Na monofasia retida anteriormente a SEFAZ calcula o valor e rejeita (cStat 1240)
        // quando o informado diverge. Com o grupo zerado a nota e autorizada.
        assertEquals(3, contar(xml, "<vIBSMonoRet>0.00</vIBSMonoRet>"), "IBS retido sai zerado");
        assertEquals(3, contar(xml, "<vCBSMonoRet>0.00</vCBSMonoRet>"), "CBS retido sai zerado");

        // Consequencia: como nada acumula valor, o gMono dos totais nao e emitido.
        // O XSD permite a ausencia (minOccurs=0) e a SEFAZ autorizou assim.
        assertFalse(xml.contains("<gMono>"),
                "sem valor monofasico acumulado, o gMono dos totais nao deve ser emitido");
    }

    private static int contar(String texto, String trecho) {
        int n = 0;
        int i = texto.indexOf(trecho);
        while (i >= 0) {
            n++;
            i = texto.indexOf(trecho, i + trecho.length());
        }
        return n;
    }

    /** Usa o certificado de teste do repositorio, nao o certificado local do desenvolvedor. */
    private static ConfiguracoesNfe configuracoes() throws Exception {
        URI uri = Objects.requireNonNull(EnvioNfceCombustivelTest.class.getClassLoader()
                .getResource("NAO_UTILIZE.pfx")).toURI();
        Certificado certificado = CertificadoService.certificadoPfx(Paths.get(uri).toString(), "123456");
        return ConfiguracoesNfe.criarConfiguracoes(
                EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO, certificado, "./schemas");
    }
}
