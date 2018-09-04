package br.com.samuelweb.nfe.util;

import br.com.samuelweb.nfe.util.model.Emit;
import br.com.samuelweb.nfe.util.model.Ide;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;

public class NfeUtil {

    public static String gerarChaveAcesso(Ide ide, Emit emit){
        if (ide == null || emit == null) {
            return "";
        }
        if (!StringUtils.isNotBlank(ide.getCnf())) {
            ide.setCnf(NfeUtil.gerarCNF(ide));
        }
        StringBuilder sbChaveAcesso = new StringBuilder();
        sbChaveAcesso.append(StringUtils.leftPad(ide.getCuf().toString(), 2, "0"));
        sbChaveAcesso.append(ide.getDhEmi().format(DateTimeFormatter.ofPattern("yyMM")));
        sbChaveAcesso.append(StringUtils.rightPad(emit.getCnpjCpf(), 14, "0"));
        sbChaveAcesso.append(StringUtils.leftPad(ide.getMod(), 2, "0"));
        sbChaveAcesso.append(StringUtils.leftPad(ide.getSerie().toString(), 3, "0"));
        sbChaveAcesso.append(StringUtils.leftPad(ide.getNnf().toString(), 9, "0"));
        sbChaveAcesso.append(StringUtils.leftPad(ide.getTpEmis().toString(), 1, "0"));
        sbChaveAcesso.append(StringUtils.leftPad(ide.getCnf(), 8, "0"));
        ide.setCdv(calcularModulo11(sbChaveAcesso.toString()));
        sbChaveAcesso.append(ide.getCdv());
        return sbChaveAcesso.toString();
    }

    private static Integer calcularModulo11(String valor) {
        return modulo11(valor, 9, false);
    }

    public static Integer modulo11(String valor, int base, boolean resto) {
        Integer soma = 0;
        Integer peso = 2;
        Integer digito;
        for (int i = valor.length()-1; i >= 0; i--) {
            soma += Integer.parseInt(String.valueOf(valor.charAt(i))) * peso;
            if (peso < base) {
                peso++;
            } else {
                peso = 2;
            }
        }
        if (resto) {
            return (soma % 11);
        } else {
            digito = 11-(soma % 11);
            if (digito > 9) {
                digito = 0;
            }
            return digito;
        }
    }

    private static String gerarCNF(Ide ide) {
        final String cTroca = "0587294361";
        StringBuilder cSoma = new StringBuilder();
        String nNf = ide.getNnf().toString();
        Integer n;
        Integer nPeso;
        if (!StringUtils.isNotBlank(nNf)) {
            nNf = "0";
        }
        nNf = StringUtils.leftPad(nNf, 8, "0");
        nPeso = new Integer(nNf.substring(7));
        if (nPeso < 4) {
            nPeso = 9 - nPeso;
        }
        n = nPeso;
        for (int i = 0; i < nNf.length(); i++) {
            cSoma.append(n.toString());
            if (n == 0) {
                n = nPeso;
            } else {
                n--;
            }
        }
        cSoma = new StringBuilder(String.valueOf(Integer.parseInt(nNf) + Integer.parseInt(cSoma.toString())));
        cSoma = new StringBuilder(cSoma.toString().substring(0, 7));
        StringBuilder codigoCNf = new StringBuilder();
        for (int i = 0; i < cSoma.toString().length(); i++) {
            n = Integer.parseInt(String.valueOf(cSoma.toString().charAt(i)));
            if (n == 0) {
                n = 10;
            }
            codigoCNf.append(String.valueOf(cTroca.charAt(n-1)));
        }
        return StringUtils.leftPad(codigoCNf.toString(), 8, "0");
    }
}
