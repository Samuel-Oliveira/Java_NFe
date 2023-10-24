package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.schema_4.retEnviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

final class RetEnviNFeTest {
    @Test
    void testRetornoProtNfeXmlMS() throws Exception {
        //XML "igual retorno Sefaz" (Sefaz gerando Errado)
        String xml = "<retEnviNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"4.00\"><tpAmb>1</tpAmb><verAplic>MS_2.1.22</verAplic><cStat>104</cStat><xMotivo>Lote processado</xMotivo><cUF>50</cUF><dhRecbto>2023-10-24T08:06:08-04:00</dhRecbto><protNFe xmlns=\"http://www.w3.org/2000/09/xmldsig#\" versao=\"4.00\"><infProt xmlns=\"http://www.portalfiscal.inf.br/nfe\" Id=\"ID150230352825939\"><tpAmb>1</tpAmb><verAplic>MS_2.1.22</verAplic><chNFe>50231084683481030084650010006763301921283483</chNFe><dhRecbto>2023-10-24T08:06:08-04:00</dhRecbto><nProt>150230352825939</nProt><digVal>fAY8CPL23PfJlWT9HsR85flFfMQ=</digVal><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo></infProt><Signature><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#ID150230352825939\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>Zy07C8/sOa7Opv8WGJQKqK7a1pY=</DigestValue></Reference></SignedInfo><SignatureValue>enTKkOw6yw/SJJdJEJtyoKRrt0SlBYUnOop8se7BFlO5HIHW/rFBqFPlTFgnBRXXowJL1gm0crFi&#xd;ypG8+C4z9Bs/mVtePPOPi8Q574HUPNUmAFPWokoAt/hO27hEBu0CvgiLKK67anK0pW4tAyXSYd2y&#xd;Ey97sb0BY7YPqBpsa0t5UdM9Sio+0GE7ZVW4KtvHoLF+7GZSvN2qofXqB1KShkiERUaG9BDBXS5G&#xd;6KT9mZs5C/KE0f7NjM7O8fKb+jtDwFjnZKs8Y8viu8hBFxzBtbCo/rWFwLpprxIzL67WZug4+EU3&#xd;GUML0E+1UNv6pimfojOw5sgmxBx2kZtJJpOzmg==</SignatureValue><KeyInfo><X509Data><X509Certificate>MIIHZjCCBU6gAwIBAgIIEd4jCBRmBKIwDQYJKoZIhvcNAQELBQAwWTELMAkGA1UEBhMCQlIxEzAR&#xd;BgNVBAoTCklDUC1CcmFzaWwxFTATBgNVBAsTDEFDIFNPTFVUSSB2NTEeMBwGA1UEAxMVQUMgU09M&#xd;VVRJIE11bHRpcGxhIHY1MB4XDTIzMDgxNzE0NDQwMFoXDTI0MDgxNjE0NDQwMFowgfUxCzAJBgNV&#xd;BAYTAkJSMRMwEQYDVQQKEwpJQ1AtQnJhc2lsMQswCQYDVQQIEwJNUzEVMBMGA1UEBxMMQ2FtcG8g&#xd;R3JhbmRlMR4wHAYDVQQLExVBQyBTT0xVVEkgTXVsdGlwbGEgdjUxFzAVBgNVBAsTDjA5NDYxNjQ3&#xd;MDAwMTk1MRkwFwYDVQQLExBWaWRlb2NvbmZlcmVuY2lhMRowGAYDVQQLExFDZXJ0aWZpY2FkbyBQ&#xd;SiBBMTE9MDsGA1UEAxM0U0VGQVogU0VDUkVUQVJJQSBERSBFU1RBRE8gREUgRkFaRU5EQTowMjkz&#xd;NTg0MzAwMDEwNTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAIJQuGTIkQqn4wsqpDWL&#xd;X4KTCyqttM0NXUPNaTdqitGt2DT6xOHEFiuq4O5R3P6XUbJmfkG20OaYc2QujKxHh1RmgEn4J327&#xd;fDJ66hBpX5b/pLG9NKXXQkZk1myVn8CdKwDivOuz6LplmbCN/tY4VTabQG00Fw9LZzvxykud5qXK&#xd;Icr04MFQKdugWgG0Q4CGBLYIMn2MUU5Ir3xGR13/mPxfY9P0sSNkz45vY+ccaiVUjgAvocjyb6NT&#xd;iOkjDXEl8z3h2wdeMZgQQ+CP6KlzPuMgpeAgKeLIJFHPcpxpS4gFYqj73BeaKZKzgbrX1B9/9zrz&#xd;T6B0WYRDY83nEuY4v1ECAwEAAaOCApMwggKPMAkGA1UdEwQCMAAwHwYDVR0jBBgwFoAUxVLtJYAJ&#xd;35yCyJ9Hxt20XzHdubEwVAYIKwYBBQUHAQEESDBGMEQGCCsGAQUFBzAChjhodHRwOi8vY2NkLmFj&#xd;c29sdXRpLmNvbS5ici9sY3IvYWMtc29sdXRpLW11bHRpcGxhLXY1LnA3YjCBzgYDVR0RBIHGMIHD&#xd;gSRjZXJ0aWZpY2Fkb2RpZ2l0YWxAZmF6ZW5kYS5tcy5nb3YuYnKgLQYFYEwBAwKgJBMiQ0FJTyBH&#xd;UkFDTyBQT01QRVUgU0FCSU5PIERFIEFSQVVKT6AZBgVgTAEDA6AQEw4wMjkzNTg0MzAwMDEwNaA4&#xd;BgVgTAEDBKAvEy0xNDAxMTk3NzY5MjY2Mzk1MTUzMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDCg&#xd;FwYFYEwBAwegDhMMMDAwMDAwMDAwMDAwMF0GA1UdIARWMFQwUgYGYEwBAgEmMEgwRgYIKwYBBQUH&#xd;AgEWOmh0dHA6Ly9jY2QuYWNzb2x1dGkuY29tLmJyL2RvY3MvZHBjLWFjLXNvbHV0aS1tdWx0aXBs&#xd;YS5wZGYwHQYDVR0lBBYwFAYIKwYBBQUHAwIGCCsGAQUFBwMEMIGMBgNVHR8EgYQwgYEwPqA8oDqG&#xd;OGh0dHA6Ly9jY2QuYWNzb2x1dGkuY29tLmJyL2xjci9hYy1zb2x1dGktbXVsdGlwbGEtdjUuY3Js&#xd;MD+gPaA7hjlodHRwOi8vY2NkMi5hY3NvbHV0aS5jb20uYnIvbGNyL2FjLXNvbHV0aS1tdWx0aXBs&#xd;YS12NS5jcmwwHQYDVR0OBBYEFJPr8GaWvzehPja3uQ6LL1xm+n0tMA4GA1UdDwEB/wQEAwIF4DAN&#xd;BgkqhkiG9w0BAQsFAAOCAgEAaNCzTIZHLjCmw48G2uBTy7V43wuqfYA08B1CC7A30qrlHC/0E50l&#xd;40ap4jjFwn03DyDXvehvpyqAcGNsbUL68vyloH6hdHW2ify7O8iz9y1/p/00zEYqYYlA0wUuSHDr&#xd;DlDVDRyh8g4I5a4vnJo0xmbw+DvD6RoOCqvbiw2sg+BPgjLBLNj8F3xqCBS982kjUNtutuY2t1IJ&#xd;AihKqUVQcRUk6SrriHELKYWcdLna0ORkr+JhYXGcbu8G3tskQ1mzEr+IgBjGwa1c4iNkUn6U7emB&#xd;zmn+ZkGoJKHu6IS/QcqP7DI8HJTmImI6q6hbZ76TpP4hRfRt+Yngn6MMqur6A6v7alrqfwq/+yEu&#xd;eqdcJYW4pbX+p3MEQ1lzeqaX3yxusEfK5DTmq4BrN6JWZ1UeYmcu+FKiyLc4sAeeHj57nKc3ckDy&#xd;KaJPk0H6KgZkBOP0Im4dLgthQwvazbSDVDOisDBVeANFYSiErYS24WUYOU0H8SjbSF5udFtDzL1s&#xd;tSMSR0ViPDzuomuZWzkfjS8CkojFE1LwedK+bt4CVQiA3Q/uhg9Dxl/agwNjEpZ2oqc4hQRZtHlz&#xd;+MF09IjpsSt4IlzN63tCGWCie1z4CD//eUHYsole5reaqc/WiPAF45pMHuxlDbO5w2i3/sg77nXc&#xd;1bmW4uJPNMyIQQXi5V2smis=</X509Certificate></X509Data></KeyInfo></Signature></protNFe></retEnviNFe>";

        testRetornoProtNfeInterno(xml);
    }

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
