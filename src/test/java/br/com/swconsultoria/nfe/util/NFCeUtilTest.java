package br.com.swconsultoria.nfe.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NFCeUtilTest {

    @Test
    void geraHashCSRTSucesso() throws NoSuchAlgorithmException {
        String chave = "41180678393592000146558900000006041028190697";
        String csrt = "G8063VRTNDMO886SFNK5LDUDEI24XJ22YIPO";

        assertArrayEquals("aWv6LeEM4X6u4+qBI2OYZ8grigw=".getBytes(StandardCharsets.UTF_8), geraHashCSRTBase64(chave, csrt));
    }

    @Test
    void geraHashCSRTParametrosInvalidos() {
        String chave = "41180678393592000146558900000006041028190697";
        String csrt = "G8063VRTNDMO886SFNK5LDUDEI24XJ22YIPO";

        //Chave Nula
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()
                -> geraHashCSRTBase64(null, csrt)
        );
        assertEquals(exception.getMessage(), "Chave não deve ser nula ou vazia");

        //Chave Com menos Caracteres
        exception = assertThrows(IllegalArgumentException.class, ()
                -> geraHashCSRTBase64("123", csrt)
        );
        assertEquals(exception.getMessage(), "Chave deve conter 44 caracteres.");

        //CSRC Vazio
        exception = assertThrows(IllegalArgumentException.class, ()
                -> geraHashCSRTBase64(chave, "")
        );
        assertEquals(exception.getMessage(), "CSRT não deve ser nulo ou vazio");
    }

    private byte[] geraHashCSRTBase64(String chave, String csrt) throws NoSuchAlgorithmException {
        //O XSD é xs:base64Binary e já faz o base64, aqui é uma "simulação" para os testes
        return Base64.getEncoder().encode(NFCeUtil.geraHashCSRT(chave, csrt));
    }

}