package br.com.samuelweb.nfe.util.enumeration;

import java.util.Arrays;

public enum ProcEmi {

    //0=Emissão de NF-e com aplicativo do contribuinte;
    //1=Emissão de NF-e avulsa pelo Fisco;
    //2=Emissão de NF-e avulsa, pelo contribuinte com seu certificado digital, através do site do Fisco;
    //3=Emissão NF-e pelo contribuinte com aplicativo fornecido pelo Fisco.

    EMISSAO_CONTRIBUINTE("0"),
    EMISSAO_AVULSA_PELO_FISCO("1"),
    EMISSAO_AVULSA_PELO_CONTRIBUINTE("2"),
    EMISSAO_CONTRIBUINTE_APP_FISCO("3");

    private String value;

    ProcEmi(String value) {
        this.value = value;
    }

    public static ProcEmi getByValue(String value) {
        return Arrays.stream(ProcEmi.values())
                .filter(e -> e.value.equals(value))
                .findAny()
                .orElse(ProcEmi.EMISSAO_CONTRIBUINTE);
    }

    public String getValue() {
        return value;
    }
}
