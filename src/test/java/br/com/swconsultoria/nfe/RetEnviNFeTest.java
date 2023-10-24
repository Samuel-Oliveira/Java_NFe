package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.schema_4.retEnviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

final class RetEnviNFeTest {
    @Test
    void testRetornoProtNfeXmlPR() throws JAXBException {
        String xml = "<retEnviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"4.00\"><tpAmb>2</tpAmb><verAplic>PR-v4_8_22</verAplic><cStat>104</cStat><xMotivo>Lote processado</xMotivo><cUF>41</cUF><dhRecbto>2023-10-06T19:00:34-03:00</dhRecbto><protNFe versao=\"4.00\"><infProt Id=\"ID141230000768070\"><tpAmb>2</tpAmb><verAplic>PR-v4_8_22</verAplic><chNFe>41231084683481035710550020000012851946140393</chNFe><dhRecbto>2023-10-06T19:00:34-03:00</dhRecbto><nProt>141230000768070</nProt><digVal>jnN1CTgme+CSzJjXSayBXzP+TIE=</digVal><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo></infProt></protNFe></retEnviNFe>";
        testRetornoProtNfeInterno(xml);
    }

    void testRetornoProtNfeInterno(final String xml) throws JAXBException {
        TRetEnviNFe resultado = XmlNfeUtil.xmlToObject(xml, TRetEnviNFe.class);

        assertNotNull(resultado);
        assertNotNull(resultado.getProtNFe());
    }
}
