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

    /** Quantidade: 2 a 4 casas decimais. */
    public String quantidade(String valor) {
        return numero(valor, "#,##0.00##");
    }

    /** Valor unitário: 2 a 10 casas decimais, o limite do Schema (TDec_1110v). Não arredonda o que veio no XML. */
    public String valorUnitario(String valor) {
        return numero(valor, "#,##0.00########");
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

    /** Tipo de Regime de Apuração do IBS e da CBS (emit/tpRegimeIBSCBS, NT 2026.005). */
    public String tipoRegimeApuracao(String valor) {
        if (vazio(valor)) {
            return "";
        }
        switch (valor) {
            case "1": return "1 - SIMPLES NACIONAL - COM REGIME REGULAR";
            case "2": return "2 - OPÇÃO PENDENTE PELO SIMPLES NACIONAL - SEM REGIME REGULAR";
            case "3": return "3 - OPÇÃO PENDENTE PELO SN (EXCESSO SUBLIMITE) - SEM REGIME REGULAR";
            case "4": return "4 - OPÇÃO PENDENTE PELO SIMPLES NACIONAL - COM REGIME REGULAR";
            default: return valor;
        }
    }

    /**
     * Forma de pagamento (pag/detPag/tPag) por extenso, para o DANFE Simplificado Tipo 2. Com 99 (Outros) usa a
     * descrição do xPag quando informada; código desconhecido devolve o próprio código.
     */
    public String formaPagamento(String tPag, String xPag) {
        if (vazio(tPag)) {
            return vazio(xPag) ? "" : xPag;
        }
        switch (tPag) {
            case "01": return "Dinheiro";
            case "02": return "Cheque";
            case "03": return "Cartão de Crédito";
            case "04": return "Cartão de Débito";
            case "05": return "Cartão da Loja";
            case "10": return "Vale Alimentação";
            case "11": return "Vale Refeição";
            case "12": return "Vale Presente";
            case "13": return "Vale Combustível";
            case "15": return "Boleto Bancário";
            case "16": return "Depósito Bancário";
            case "17": return "PIX";
            case "18": return "Transferência Bancária";
            case "19": return "Programa de Fidelidade";
            case "20": return "PIX";
            case "21": return "Crédito em Loja";
            case "22": return "Pagamento Eletrônico";
            case "90": return "Sem Pagamento";
            case "99": return vazio(xPag) ? "Outros" : xPag;
            default: return tPag;
        }
    }

    /**
     * Rótulo da identificação do consumidor do DANFE Simplificado Tipo 2 (Divisão VI), a partir do grupo dest:
     * "CONSUMIDOR CNPJ:", "CONSUMIDOR CPF:", "CONSUMIDOR ID. ESTRANGEIRO:" ou "CONSUMIDOR NÃO IDENTIFICADO".
     * O número vem de {@link #documento(String)} sobre o primeiro de CNPJ, CPF e idEstrangeiro.
     */
    public String consumidor(Map<String, String> dest) {
        if (dest != null && !vazio(dest.get("CNPJ"))) {
            return "CONSUMIDOR CNPJ:";
        }
        if (dest != null && !vazio(dest.get("CPF"))) {
            return "CONSUMIDOR CPF:";
        }
        if (dest != null && !vazio(dest.get("idEstrangeiro"))) {
            return "CONSUMIDOR ID. ESTRANGEIRO:";
        }
        return "CONSUMIDOR NÃO IDENTIFICADO";
    }

    /**
     * Número que acompanha o rótulo de {@link #consumidor(Map)}: CNPJ ou CPF formatados, ou o idEstrangeiro como veio
     * no XML (ele tem letras, que {@link #documento(String)} descartaria). "" sem destinatário identificado.
     */
    public String documentoConsumidor(Map<String, String> dest) {
        if (dest == null) {
            return "";
        }
        String cnpjCpf = primeiro(dest.get("CNPJ"), dest.get("CPF"));
        if (!cnpjCpf.isEmpty()) {
            return documento(cnpjCpf);
        }
        return vazio(dest.get("idEstrangeiro")) ? "" : dest.get("idEstrangeiro");
    }

    /** O valor existe e é diferente de zero (valor não numérico conta como presente). */
    public boolean naoZero(String valor) {
        if (vazio(valor)) {
            return false;
        }
        try {
            return new BigDecimal(valor.trim()).signum() != 0;
        } catch (NumberFormatException e) {
            return true;
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
     * Endereço completo de um grupo (emit ou dest), sem o país, numa linha:
     * "Rua X, 10 - Sala 2, Centro, São Paulo - SP, CEP 01001-000". Partes ausentes são omitidas.
     */
    public String enderecoCompleto(Map<String, String> grupo) {
        if (grupo == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(endereco(grupo));
        acrescenta(sb, ", ", grupo.get("xBairro"));
        String municipio = vazio(grupo.get("xMun")) ? "" : grupo.get("xMun")
                + (vazio(grupo.get("UF")) ? "" : " - " + grupo.get("UF"));
        acrescenta(sb, ", ", municipio);
        acrescenta(sb, ", CEP ", cep(grupo.get("CEP")));
        return sb.toString();
    }

    /** Junta a parte com o separador; no início da linha, o separador perde a vírgula (", CEP " vira "CEP "). */
    private static void acrescenta(StringBuilder sb, String separador, String parte) {
        if (vazio(parte)) {
            return;
        }
        sb.append(sb.length() > 0 ? separador : separador.substring(2)).append(parte);
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
