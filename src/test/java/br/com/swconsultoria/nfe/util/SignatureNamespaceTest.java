package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoCancelamento;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes de regressão para a issue #352: os 8 tipos XMLDSig duplicados em
 * {@code br.com.swconsultoria.nfe.schemas_eventos} perderam o atributo {@code namespace} do
 * {@code @XmlType}, ao contrário dos mesmos tipos em {@code br.com.swconsultoria.nfe.schemas}.
 * Sem esse namespace, o unmarshal descartava silenciosamente os filhos de {@code Signature}
 * (SignedInfo, SignatureValue, KeyInfo) por esperá-los no namespace do portal fiscal em vez do
 * namespace real do XMLDSig, resultando em um {@code <Signature/>} vazio gravado no procEventoNFe.
 */
class SignatureNamespaceTest {

    private static final String XMLDSIG_NS = "http://www.w3.org/2000/09/xmldsig#";

    @Test
    void deveMapearSignedInfoSignatureValueEKeyInfoAoDesserializarEventoAssinado() throws IOException {
        // Given
        String xml = XmlNfeUtil.leXml("src/test/resources/EventoCancelamentoAssinado.xml");

        // When
        TEnvEventoCancelamento envEvento = XmlNfeUtil.xmlToObject(xml, TEnvEventoCancelamento.class);

        // Then
        assertEquals(1, envEvento.getEvento().size(), "Deve conter exatamente um evento");
        assertNotNull(envEvento.getEvento().get(0).getSignature(), "Signature não deve ser nulo");
        assertNotNull(envEvento.getEvento().get(0).getSignature().getSignedInfo(),
                "SignedInfo deve ter sido mapeado a partir do namespace xmldsig#");
        assertNotNull(envEvento.getEvento().get(0).getSignature().getSignatureValue(),
                "SignatureValue deve ter sido mapeado a partir do namespace xmldsig#");
        assertNotNull(envEvento.getEvento().get(0).getSignature().getKeyInfo(),
                "KeyInfo deve ter sido mapeado a partir do namespace xmldsig#");

        byte[] certificado = envEvento.getEvento().get(0).getSignature()
                .getKeyInfo().getX509Data().getX509Certificate();
        assertNotNull(certificado, "X509Certificate deve sobreviver ao unmarshal dentro de KeyInfo/X509Data");
        assertTrue(certificado.length > 0, "X509Certificate não deve estar vazio");

        byte[] signatureValue = envEvento.getEvento().get(0).getSignature().getSignatureValue().getValue();
        assertNotNull(signatureValue, "SignatureValue.value deve ter sido preenchido");
        assertTrue(signatureValue.length > 0, "SignatureValue.value não deve estar vazio");

        assertNotNull(envEvento.getEvento().get(0).getSignature().getSignedInfo().getReference(),
                "Reference dentro de SignedInfo deve ter sido mapeado");
        assertNotNull(envEvento.getEvento().get(0).getSignature().getSignedInfo().getReference().getDigestValue(),
                "DigestValue dentro de Reference deve ter sido mapeado");
    }

    @Test
    void deveSerializarSignatureNoNamespaceXmldsigSemXmlnsDuplicado() throws IOException, JAXBException, NfeException {
        // Given
        String xmlOriginal = XmlNfeUtil.leXml("src/test/resources/EventoCancelamentoAssinado.xml");
        TEnvEventoCancelamento envEvento = XmlNfeUtil.xmlToObject(xmlOriginal, TEnvEventoCancelamento.class);

        // When
        String xmlGerado = XmlNfeUtil.objectToXml(envEvento);

        // Then
        assertTrue(xmlGerado.contains("<Signature xmlns=\"" + XMLDSIG_NS + "\">"),
                "Signature deve ser emitido explicitamente no namespace do XMLDSig");
        assertFalse(xmlGerado.contains("ns2:"), "Não deve sobrar prefixo ns2: após o marshal");

        String tagSignatureAbertura = xmlGerado.substring(
                xmlGerado.indexOf("<Signature"),
                xmlGerado.indexOf('>', xmlGerado.indexOf("<Signature")) + 1);
        int ocorrenciasXmlns = tagSignatureAbertura.split("xmlns=", -1).length - 1;
        assertEquals(1, ocorrenciasXmlns, "A tag <Signature> não pode ter xmlns duplicado: " + tagSignatureAbertura);

        assertTrue(xmlGerado.contains("<SignedInfo>"), "SignedInfo deve estar presente no XML gerado");
        assertTrue(xmlGerado.contains("<SignatureValue>"), "SignatureValue deve estar presente no XML gerado");
        assertTrue(xmlGerado.contains("<KeyInfo>"), "KeyInfo deve estar presente no XML gerado");
        assertTrue(xmlGerado.contains("<X509Certificate>"), "X509Certificate deve estar presente no XML gerado");
    }
}
