package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ChaveUtilTest {

    private static final String CNPJ = "10732644000128";
    private static final String MODELO_NFE = "55";
    private static final String TIPO_EMISSAO = "1";
    private static final LocalDateTime DATA_EMISSAO = LocalDateTime.of(2024, 3, 15, 10, 30, 0);

    // -------------------------------------------------------------------------
    // completarComZerosAEsquerda
    // -------------------------------------------------------------------------

    @Test
    void completarComZerosAEsquerda_stringMenorQueAlvo_adicionaZeros() {
        assertEquals("00042", ChaveUtil.completarComZerosAEsquerda("42", 5));
    }

    @Test
    void completarComZerosAEsquerda_stringExataAoTamanho_naoAltera() {
        assertEquals("12345", ChaveUtil.completarComZerosAEsquerda("12345", 5));
    }

    @Test
    void completarComZerosAEsquerda_stringMaiorQueAlvo_naoAltera() {
        assertEquals("123456", ChaveUtil.completarComZerosAEsquerda("123456", 3));
    }

    @Test
    void completarComZerosAEsquerda_stringVazia_retornaSoZeros() {
        assertEquals("000", ChaveUtil.completarComZerosAEsquerda("", 3));
    }

    @Test
    void completarComZerosAEsquerda_tamanhoUm_retornaUmDigito() {
        assertEquals("5", ChaveUtil.completarComZerosAEsquerda("5", 1));
    }

    // -------------------------------------------------------------------------
    // getChaveNF
    // -------------------------------------------------------------------------

    @Test
    void getChaveNF_retornaChaveComPrefixoNFe() {
        ChaveUtil chave = new ChaveUtil(EstadosEnum.GO, CNPJ, MODELO_NFE, 1, 1, TIPO_EMISSAO, "12345678", DATA_EMISSAO);
        assertTrue(chave.getChaveNF().startsWith("NFe"));
    }

    @Test
    void getChaveNF_retornaTamanhoCorreto() {
        // "NFe" (3) + 43 dígitos da chave + 1 dígito verificador = 47 chars
        ChaveUtil chave = new ChaveUtil(EstadosEnum.SP, CNPJ, MODELO_NFE, 1, 92756, TIPO_EMISSAO, "12345678", DATA_EMISSAO);
        assertEquals(47, chave.getChaveNF().length());
    }

    @Test
    void getChaveNF_conteudoNaoNulo() {
        ChaveUtil chave = new ChaveUtil(EstadosEnum.MG, CNPJ, MODELO_NFE, 1, 1, TIPO_EMISSAO, "00000001", DATA_EMISSAO);
        assertNotNull(chave.getChaveNF());
    }

    // -------------------------------------------------------------------------
    // getDigitoVerificador
    // -------------------------------------------------------------------------

    @Test
    void getDigitoVerificador_retornaUmDigito() {
        ChaveUtil chave = new ChaveUtil(EstadosEnum.GO, CNPJ, MODELO_NFE, 1, 1, TIPO_EMISSAO, "12345678", DATA_EMISSAO);
        assertEquals(1, chave.getDigitoVerificador().length());
    }

    @Test
    void getDigitoVerificador_consistenteComChave() {
        ChaveUtil chave = new ChaveUtil(EstadosEnum.GO, CNPJ, MODELO_NFE, 1, 1, TIPO_EMISSAO, "12345678", DATA_EMISSAO);
        String chaveCompleta = chave.getChaveNF(); // "NFe" + 43 dígitos + dv
        String dvDaChave = chaveCompleta.substring(chaveCompleta.length() - 1);
        assertEquals(dvDaChave, chave.getDigitoVerificador());
    }

    @Test
    void getDigitoVerificador_diferentes_numerosNfe_geramChavesDiferentes() {
        ChaveUtil chave1 = new ChaveUtil(EstadosEnum.GO, CNPJ, MODELO_NFE, 1, 100, TIPO_EMISSAO, "12345678", DATA_EMISSAO);
        ChaveUtil chave2 = new ChaveUtil(EstadosEnum.GO, CNPJ, MODELO_NFE, 1, 200, TIPO_EMISSAO, "12345678", DATA_EMISSAO);
        assertNotEquals(chave1.getChaveNF(), chave2.getChaveNF());
    }

    @Test
    void getDigitoVerificador_diferentesEstados_geramChavesDiferentes() {
        ChaveUtil chaveGO = new ChaveUtil(EstadosEnum.GO, CNPJ, MODELO_NFE, 1, 1, TIPO_EMISSAO, "12345678", DATA_EMISSAO);
        ChaveUtil chaveSP = new ChaveUtil(EstadosEnum.SP, CNPJ, MODELO_NFE, 1, 1, TIPO_EMISSAO, "12345678", DATA_EMISSAO);
        assertNotEquals(chaveGO.getChaveNF(), chaveSP.getChaveNF());
    }
}
