package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChaveUtil {

    private String cDv;
    private String chave;

    /**
     * Contrutor para montar a Chave
     * @param estado
     * @param cnpj
     * @param modelo
     * @param serie
     * @param numeroNf
     * @param tipoEmissao
     * @param codigoNf
     * @param dataEmissao
     */
    public ChaveUtil(EstadosEnum estado, String cnpj, String modelo, int serie, int numeroNf, String tipoEmissao, String codigoNf, LocalDateTime dataEmissao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMM");
        String cUf = estado.getCodigoUF();
        String aamm = dataEmissao.format(formatter);
        String serie1 = completarComZerosAEsquerda(String.valueOf(serie), 3);
        String nNf = completarComZerosAEsquerda(String.valueOf(numeroNf), 9);
        String cNf = completarComZerosAEsquerda(codigoNf, 8);
        cnpj = cnpj.length() < 14 ? "000"+cnpj : cnpj;
        this.chave = cUf + aamm + cnpj + modelo + serie1 + nNf + tipoEmissao + cNf;
        this.cDv = String.valueOf(modulo11(chave));

    }

    /**
     * Retorna a Chave da Nfe
     * @return
     */
    public String getChaveNF() {
        return "NFe" + chave + cDv;
    }

    /**
     * Retorna o Digito Verificador
     * @return
     */
    public String getDigitoVerificador() {
        return cDv;
    }

    /**
     * Calcula Digito Verificador
     * @param chave
     * @return
     */
    private static int modulo11(String chave) {
        int total = 0;
        int peso = 2;

        for (int i = 0; i < chave.length(); i++) {
            total += (chave.charAt((chave.length() - 1) - i) - '0') * peso;
            peso++;
            if (peso == 10)
                peso = 2;
        }
        int resto = total % 11;
        return (resto == 0 || resto == 1) ? 0 : (11 - resto);
    }

    /**
     * Completa com zeros a esquerda ate o tamanho passado.
     *
     * @param value
     * @param length
     * @return
     */
    public static String completarComZerosAEsquerda(String value, int length) {
        int tam = value.length();
        StringBuilder result = new StringBuilder(value);

        for (int i = tam; i < length; i++) {
            result.insert(0, "0");
        }
        return result.toString();

    }
}
