package br.com.swconsultoria.nfe.impressao.v2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

/**
 * Formatação dos valores brutos do XML para impressão do DANFE v2.
 * Exposto ao template Thymeleaf como "fmt": ${fmt.valor(danfe.icmsTot.vNF)}
 * Todos os métodos aceitam null e retornam "" nesse caso.
 */
public class FormatadorDanfeV2 {

    private static final DecimalFormatSymbols SIMBOLOS_BR = DecimalFormatSymbols.getInstance(new Locale("pt", "BR"));

    /** Valor monetário: 1.234,56 */
    public String valor(String valor) {
        return numero(valor, "#,##0.00");
    }

    /** Quantidade/valor unitário: 2 a 4 casas decimais. */
    public String quantidade(String valor) {
        return numero(valor, "#,##0.00##");
    }

    /** Alíquota com símbolo: 18,00% */
    public String aliquota(String valor) {
        String numero = numero(valor, "#,##0.00##");
        return numero.isEmpty() ? numero : numero + "%";
    }

    /** Peso: 3 casas decimais. */
    public String peso(String valor) {
        return numero(valor, "#,##0.000");
    }

    /** Alíquota ad rem do IBS/CBS monofásico (R$ por unidade de medida): 4 casas decimais, sem símbolo. */
    public String adRem(String valor) {
        return numero(valor, "#,##0.0000");
    }

    /**
     * Linha complementar de um item para os valores do IBS/CBS que não cabem nas colunas de bases, alíquotas e
     * valores: retenção, retido anteriormente e diferença de biocombustível do monofásico, e a competência do
     * ajuste. Só cita o que existe no XML; "" quando não há nada.
     */
    public String complementoIbsCbs(Map<String, String> item) {
        if (item == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        junta(sb, "Retenção monofásica", item.get("vIBSMonoReten"), item.get("vCBSMonoReten"));
        junta(sb, "Retido anteriormente", item.get("vIBSMonoRet"), item.get("vCBSMonoRet"));
        junta(sb, "Diferença biocombustível", item.get("vIBSDiferenca"), item.get("vCBSDiferenca"));
        if (!vazio(item.get("competApur"))) {
            separa(sb).append("Ajuste de competência ").append(competencia(item.get("competApur")));
        }
        return sb.toString();
    }

    /** Competência aaaa-mm (TCompetApur) para mm/aaaa. */
    public String competencia(String valor) {
        if (vazio(valor) || valor.length() < 7) {
            return vazio(valor) ? "" : valor;
        }
        return valor.substring(5, 7) + "/" + valor.substring(0, 4);
    }

    private void junta(StringBuilder sb, String rotulo, String ibs, String cbs) {
        if (vazio(ibs) && vazio(cbs)) {
            return;
        }
        separa(sb).append(rotulo);
        if (!vazio(ibs)) {
            sb.append(" IBS ").append(valor(ibs));
        }
        if (!vazio(cbs)) {
            sb.append(" CBS ").append(valor(cbs));
        }
    }

    private static StringBuilder separa(StringBuilder sb) {
        return sb.length() == 0 ? sb : sb.append(" - ");
    }

    /** CNPJ (14 dígitos) ou CPF (11 dígitos); outros valores são devolvidos como estão. */
    public String documento(String valor) {
        if (vazio(valor)) {
            return "";
        }
        String d = digitos(valor);
        if (d.length() == 14) {
            return d.substring(0, 2) + "." + d.substring(2, 5) + "." + d.substring(5, 8) + "/" + d.substring(8, 12) + "-" + d.substring(12);
        }
        if (d.length() == 11) {
            return d.substring(0, 3) + "." + d.substring(3, 6) + "." + d.substring(6, 9) + "-" + d.substring(9);
        }
        return valor;
    }

    public String cep(String valor) {
        if (vazio(valor)) {
            return "";
        }
        String d = digitos(valor);
        return d.length() == 8 ? d.substring(0, 5) + "-" + d.substring(5) : valor;
    }

    public String fone(String valor) {
        if (vazio(valor)) {
            return "";
        }
        String d = digitos(valor);
        if (d.length() == 10) {
            return "(" + d.substring(0, 2) + ") " + d.substring(2, 6) + "-" + d.substring(6);
        }
        if (d.length() == 11) {
            return "(" + d.substring(0, 2) + ") " + d.substring(2, 7) + "-" + d.substring(7);
        }
        return valor;
    }

    /** Chave de acesso em grupos de 4 dígitos. */
    public String chave(String valor) {
        if (vazio(valor)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String d = digitos(valor);
        for (int i = 0; i < d.length(); i += 4) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(d, i, Math.min(i + 4, d.length()));
        }
        return sb.toString();
    }

    /** Número da NF: 000.000.001 */
    public String numeroNota(String valor) {
        if (vazio(valor)) {
            return "";
        }
        String d = String.format("%9s", digitos(valor)).replace(' ', '0');
        return d.substring(0, 3) + "." + d.substring(3, 6) + "." + d.substring(6);
    }

    /** Série: 001 */
    public String serie(String valor) {
        return vazio(valor) ? "" : String.format("%3s", digitos(valor)).replace(' ', '0');
    }

    /** Data ISO (aaaa-mm-dd ou aaaa-mm-ddThh:mm:ss...) para dd/mm/aaaa. */
    public String data(String valor) {
        if (vazio(valor) || valor.length() < 10) {
            return vazio(valor) ? "" : valor;
        }
        return valor.substring(8, 10) + "/" + valor.substring(5, 7) + "/" + valor.substring(0, 4);
    }

    /** Hora de uma data/hora ISO: hh:mm:ss */
    public String hora(String valor) {
        return vazio(valor) || valor.length() < 19 ? "" : valor.substring(11, 19);
    }

    public String dataHora(String valor) {
        return (data(valor) + " " + hora(valor)).trim();
    }

    /** Modalidade do frete (transp/modFrete). */
    public String modFrete(String valor) {
        if (vazio(valor)) {
            return "";
        }
        switch (valor) {
            case "0": return "0 - Remetente";
            case "1": return "1 - Destinatário";
            case "2": return "2 - Terceiros";
            case "3": return "3 - Próprio Remetente";
            case "4": return "4 - Próprio Destinatário";
            case "9": return "9 - Sem Frete";
            default: return valor;
        }
    }

    /** Código do Regime Tributário (emit/CRT). */
    public String crt(String valor) {
        if (vazio(valor)) {
            return "";
        }
        switch (valor) {
            case "1": return "1 - SIMPLES NACIONAL";
            case "2": return "2 - SIMPLES NACIONAL - EXCESSO DE SUBLIMITE";
            case "3": return "3 - REGIME NORMAL";
            case "4": return "4 - SIMPLES NACIONAL - MEI";
            default: return valor;
        }
    }

    /** Logradouro, número e complemento de um grupo de endereço (emit ou dest): "Rua X, 10 - Sala 2" */
    public String endereco(Map<String, String> grupo) {
        if (grupo == null || vazio(grupo.get("xLgr"))) {
            return "";
        }
        StringBuilder sb = new StringBuilder(grupo.get("xLgr"));
        if (!vazio(grupo.get("nro"))) {
            sb.append(", ").append(grupo.get("nro"));
        }
        if (!vazio(grupo.get("xCpl"))) {
            sb.append(" - ").append(grupo.get("xCpl"));
        }
        return sb.toString();
    }

    /**
     * Origem + CST/CSOSN do ICMS (ex.: "0" + "00" = "000").
     * Concatenar no template com "+" não serve: o Thymeleaf soma strings numéricas.
     */
    public String cst(String orig, String cst) {
        return (vazio(orig) ? "" : orig) + (vazio(cst) ? "" : cst);
    }

    /** Primeiro valor não vazio (ex.: fmt.primeiro(dest.CNPJ, dest.CPF)). */
    public String primeiro(String a, String b) {
        return !vazio(a) ? a : (vazio(b) ? "" : b);
    }

    private static String numero(String valor, String padrao) {
        if (vazio(valor)) {
            return "";
        }
        try {
            // DecimalFormat arredonda HALF_EVEN por padrão (1,005 -> 1,00); documento fiscal usa HALF_UP
            DecimalFormat formato = new DecimalFormat(padrao, SIMBOLOS_BR);
            formato.setRoundingMode(RoundingMode.HALF_UP);
            return formato.format(new BigDecimal(valor.trim()));
        } catch (NumberFormatException e) {
            return valor;
        }
    }

    private static String digitos(String valor) {
        return valor.replaceAll("\\D", "");
    }

    private static boolean vazio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }
}
