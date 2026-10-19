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
        String xml = XmlNfeUtil.objectToXml(EnvioNfceTeste.montaEnviNFe(configuracoes(), CNPJ_CERTIFICADO));

        String xsd = Paths.get("schemas", ServicosEnum.ENVIO.getXsd()).toString();
        assertTrue(new Validar().isValidXml(xsd, xml),
                "A NFCe de combustivel deve validar contra o XSD de envio");
    }

    @Test
    void deveEmitirGrupoMonofasicoRetidoParaOsTresCombustiveis() throws Exception {
        TEnviNFe enviNFe = EnvioNfceTeste.montaEnviNFe(configuracoes(), CNPJ_CERTIFICADO);
        String xml = XmlNfeUtil.objectToXml(enviNFe);

        assertEquals(3, enviNFe.getNFe().get(0).getInfNFe().getDet().size(),
                "a nota deve ter gasolina, etanol e diesel");
        assertEquals(3, contar(xml, "<gIBSCBSMono>"), "cada item deve ter o grupo monofasico");
        assertEquals(3, contar(xml, "<gIBSMonoAdRem>"), "monofasia de combustivel e ad rem");
        assertEquals(3, contar(xml, "<gCBSMonoAdRem>"), "IBS e CBS sao eixos independentes");
        assertEquals(0, contar(xml, "AdValorem"), "nao deve haver grupo ad valorem");

        // cClassTrib 620006 e monofasia retida anteriormente: so sai o gMonoRet
        assertEquals(0, contar(xml, "<gMonoPadrao>"), "620006 nao tem monofasia padrao");
        assertEquals(0, contar(xml, "<gMonoReten>"), "620006 nao e sujeito a retencao");

        assertTrue(xml.contains("<cProdANP>320102001</cProdANP>"), "gasolina comum");
        assertTrue(xml.contains("<cProdANP>810101001</cProdANP>"), "etanol comum");
        assertTrue(xml.contains("<cProdANP>820101012</cProdANP>"), "oleo diesel");
    }

    @Test
    void deveSomarOsValoresMonofasicosNosTotaisDaNota() throws Exception {
        TEnviNFe enviNFe = EnvioNfceTeste.montaEnviNFe(configuracoes(), CNPJ_CERTIFICADO);
        String xml = XmlNfeUtil.objectToXml(enviNFe);

        assertTrue(xml.contains("<IBSCBSTot>"), "os totais de IBS/CBS devem estar presentes");
        assertTrue(xml.contains("<gMono>"),
                "com valores monofasicos nao-zero, o gMono dos totais deve ser emitido");

        // Aliquota ad rem repartida em IBS (10%) e CBS (90%), aplicada por item:
        //   gasolina 20L: 0,0793 -> 1,59 IBS | 0,7132 -> 14,26 CBS
        //   etanol   30L: 0,0192 -> 0,58 IBS | 0,1730 ->  5,19 CBS
        //   diesel   25L: 0,0352 -> 0,88 IBS | 0,3163 ->  7,91 CBS
        assertTrue(xml.contains("<vIBSMonoRet>3.05</vIBSMonoRet>"),
                "total de IBS retido: 1,59 + 0,58 + 0,88");
        assertTrue(xml.contains("<vCBSMonoRet>27.36</vCBSMonoRet>"),
                "total de CBS retido: 14,26 + 5,19 + 7,91");
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
