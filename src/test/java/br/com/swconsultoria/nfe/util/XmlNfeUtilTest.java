package br.com.swconsultoria.nfe.util;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 26/05/2019 - 12:38
 */
class XmlNfeUtilTest {

    @Test
    void leXmlCorreto() throws IOException {
        String xml = XmlNfeUtil.leXml("src/test/resources/TesteLeXml.xml");
        assertEquals("<teste>\n    <tag>123</tag>\n</teste>", xml);
    }

    @Test
    void leXmlArquivoNaoExiste() {
        String arquivo = "TesteLeXml.xml";
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> {
            String xml = XmlNfeUtil.leXml(arquivo);
        });
        assertEquals(exception.getMessage(), "Arquivo "+arquivo+" não encontrado.");
    }

    @Test
    void leXmlParametroVazioNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            String xml = XmlNfeUtil.leXml(null);
        });
        assertEquals(exception.getMessage(), "Arquivo xml não pode ser nulo/vazio.");

        exception = assertThrows(IllegalArgumentException.class, () -> {
            String xml = XmlNfeUtil.leXml("");
        });
        assertEquals(exception.getMessage(), "Arquivo xml não pode ser nulo/vazio.");
    }
}