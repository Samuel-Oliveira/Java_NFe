package br.com.samuelweb.nfe.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chave {

    private String cUf;
    private String aa;
    private String mm;
    private String cnpj;
    private String mod;
    private String serie;
    private String nNf;
    private String cNf;
    private String tpEmis;
    
    public Chave(String cUF, String cnpj, String mod, String serie, String nnf, String tpEmis, String cnf) {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyMM");
        String aamm = format.format(now);

        while (cUF.length()<2) { cUF = "0" + cUF; }
        this.cUf = cUF;
        this.aa = aamm.substring(0, 2);
        this.mm = aamm.substring(2);
        while (cnpj.length()<14) { cnpj = "0" + cnpj; }
        this.cnpj = cnpj;
        while (mod.length()<2) { mod = "0" + mod; }
        this.mod = mod;
        while (serie.length()<3) { serie = "0" + serie; }
        this.serie = serie;
        while (nnf.length()<9) { nnf = "0" + nnf; }
        this.nNf = nnf;
        this.tpEmis = tpEmis;
        while (cnf.length()<8) { cnf = "0" + cnf; }
        this.cNf = cnf;
    }

    private String getChave() {
        return cUf + aa + mm + cnpj + mod + serie + nNf + tpEmis + cNf;
    }

    public void setAno(String ano) {
        while (ano.length()<2) { ano = "0" + ano; }
        this.aa = ano;
    }

    public void setMes(String mes) {
        while (mes.length()<2) { mes = "0" + mes; }
        this.mm = mes;
    }

    public String getChNFe() {
        return "NFe" + getChave() + String.valueOf(getMod11(getChave()));
    }
    
    private int getMod11(String chaveSemDigito) {        // UMA CHAVE DE ACESSO DE NF-E TEM 44 DIGITOS, ENTAO O CALCULO SE DÁ COM OS 43 ANTERIORES  
        int[] aux = new int[chaveSemDigito.length()];  
        int variavel = 2;  
        int total = 0;  
        int dv = 0;  
        for (int i = aux.length - 1; i >= 0; i--) {  
            aux[i] = Integer.parseInt("" + chaveSemDigito.charAt(i));  
            aux[i] = aux[i] * variavel;  
            variavel++;  
            if (variavel > 9)  
                variavel = 2;  
            total += aux[i];  
        }  
        total = total % 11; //Porque o total é modulado por onze após as somas...
        if (total == 0 || total == 1)
            dv = 0;
        else  
            dv = 11 - total;    
        
        return dv; 
    }
}
