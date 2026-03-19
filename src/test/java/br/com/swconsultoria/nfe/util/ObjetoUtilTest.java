package br.com.swconsultoria.nfe.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ObjetoUtilTest {

    // -------------------------------------------------------------------------
    // verifica(T)
    // -------------------------------------------------------------------------

    @Test
    void verifica_null_retornaVazio() {
        Optional<Object> result = ObjetoUtil.verifica(null);
        assertFalse(result.isPresent());
    }

    @Test
    void verifica_stringVazia_retornaVazio() {
        assertFalse(ObjetoUtil.verifica("").isPresent());
    }

    @Test
    void verifica_stringBrancos_retornaVazio() {
        assertFalse(ObjetoUtil.verifica("   ").isPresent());
    }

    @Test
    void verifica_stringNull_retornaVazio() {
        assertFalse(ObjetoUtil.verifica("null").isPresent());
    }

    @Test
    void verifica_stringNullCaseInsensitive_retornaVazio() {
        assertFalse(ObjetoUtil.verifica("NULL").isPresent());
    }

    @Test
    void verifica_stringValida_retornaPresente() {
        Optional<String> result = ObjetoUtil.verifica("valor");
        assertTrue(result.isPresent());
        assertEquals("valor", result.get());
    }

    @Test
    void verifica_colecaoVazia_retornaVazio() {
        assertFalse(ObjetoUtil.verifica(Collections.emptyList()).isPresent());
    }

    @Test
    void verifica_colecaoComElementos_retornaPresente() {
        assertTrue(ObjetoUtil.verifica(Arrays.asList("a", "b")).isPresent());
    }

    // -------------------------------------------------------------------------
    // getBigDecimalOrZero(String)
    // -------------------------------------------------------------------------

    @Test
    void getBigDecimalOrZero_null_retornaZero() {
        assertEquals(BigDecimal.ZERO, ObjetoUtil.getBigDecimalOrZero(null));
    }

    @Test
    void getBigDecimalOrZero_stringVazia_retornaZero() {
        assertEquals(BigDecimal.ZERO, ObjetoUtil.getBigDecimalOrZero(""));
    }

    @Test
    void getBigDecimalOrZero_stringInvalida_retornaZero() {
        assertEquals(BigDecimal.ZERO, ObjetoUtil.getBigDecimalOrZero("abc"));
    }

    @Test
    void getBigDecimalOrZero_valorValido_retornaValor() {
        assertEquals(new BigDecimal("1.50"), ObjetoUtil.getBigDecimalOrZero("1.50"));
    }

    @Test
    void getBigDecimalOrZero_valorInteiro_retornaValor() {
        assertEquals(new BigDecimal("100"), ObjetoUtil.getBigDecimalOrZero("100"));
    }

    // -------------------------------------------------------------------------
    // getOrZero(BigDecimal)
    // -------------------------------------------------------------------------

    @Test
    void getOrZero_null_retornaZero() {
        assertEquals(BigDecimal.ZERO, ObjetoUtil.getOrZero(null));
    }

    @Test
    void getOrZero_valorPositivo_retornaValor() {
        BigDecimal valor = new BigDecimal("25.99");
        assertEquals(valor, ObjetoUtil.getOrZero(valor));
    }

    @Test
    void getOrZero_zero_retornaZero() {
        assertEquals(BigDecimal.ZERO, ObjetoUtil.getOrZero(BigDecimal.ZERO));
    }

    // -------------------------------------------------------------------------
    // getValor2Casas(BigDecimal)
    // -------------------------------------------------------------------------

    @Test
    void getValor2Casas_null_retornaZeroFormatado() {
        assertEquals("0.00", ObjetoUtil.getValor2Casas(null));
    }

    @Test
    void getValor2Casas_negativo_retornaZeroFormatado() {
        assertEquals("0.00", ObjetoUtil.getValor2Casas(new BigDecimal("-1")));
    }

    @Test
    void getValor2Casas_arredondaHalfUp() {
        assertEquals("1.24", ObjetoUtil.getValor2Casas(new BigDecimal("1.235")));
    }

    @Test
    void getValor2Casas_valorExato() {
        assertEquals("13.00", ObjetoUtil.getValor2Casas(new BigDecimal("13")));
    }

    // -------------------------------------------------------------------------
    // getValor4Casas(BigDecimal)
    // -------------------------------------------------------------------------

    @Test
    void getValor4Casas_null_retornaZeroFormatado() {
        assertEquals("0.0000", ObjetoUtil.getValor4Casas(null));
    }

    @Test
    void getValor4Casas_negativo_retornaZeroFormatado() {
        assertEquals("0.0000", ObjetoUtil.getValor4Casas(new BigDecimal("-0.01")));
    }

    @Test
    void getValor4Casas_arredondaHalfUp() {
        assertEquals("5.0140", ObjetoUtil.getValor4Casas(new BigDecimal("5.01395")));
    }

    @Test
    void getValor4Casas_valorExato() {
        assertEquals("1.0000", ObjetoUtil.getValor4Casas(BigDecimal.ONE));
    }
}
