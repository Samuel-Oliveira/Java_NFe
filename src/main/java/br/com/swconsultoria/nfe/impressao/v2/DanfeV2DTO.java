package br.com.swconsultoria.nfe.impressao.v2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Dados do DANFE v2 extraídos do XML da NF-e (leiaute NT 2026.010 - Reforma Tributária).
 * Cada grupo é um Map cuja chave é o nome da tag no XML (ex.: ide.nNF, emit.xNome, icmsTot.vNF).
 * Valores são o texto bruto do XML (sem formatação); tags inexistentes não estão no Map.
 * Chaves que não são nomes de tag estão documentadas no {@link LeitorDanfeV2}.
 * Exposto ao template Thymeleaf como "danfe".
 */
public class DanfeV2DTO {

    /** Duplicatas por linha no quadro "Fatura / Duplicatas". */
    private static final int DUPLICATAS_POR_LINHA = 3;

    private String chave;
    private final Map<String, String> ide = new LinkedHashMap<>();
    private final Map<String, String> emit = new LinkedHashMap<>();
    private final Map<String, String> dest = new LinkedHashMap<>();
    private final Map<String, String> prot = new LinkedHashMap<>();
    private final Map<String, String> icmsTot = new LinkedHashMap<>();
    private final Map<String, String> ibsCbsTot = new LinkedHashMap<>();
    private final Map<String, String> isTot = new LinkedHashMap<>();
    private final Map<String, String> issqnTot = new LinkedHashMap<>();
    private final Map<String, String> transp = new LinkedHashMap<>();
    private final Map<String, String> infAdic = new LinkedHashMap<>();
    private final Map<String, String> supl = new LinkedHashMap<>();
    private final Map<String, String> pag = new LinkedHashMap<>();
    private final List<Map<String, String>> pagamentos = new ArrayList<>();
    private final List<Map<String, String>> duplicatas = new ArrayList<>();
    private final List<Map<String, String>> itens = new ArrayList<>();

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Map<String, String> getIde() {
        return ide;
    }

    public Map<String, String> getEmit() {
        return emit;
    }

    public Map<String, String> getDest() {
        return dest;
    }

    public Map<String, String> getProt() {
        return prot;
    }

    public Map<String, String> getIcmsTot() {
        return icmsTot;
    }

    public Map<String, String> getIbsCbsTot() {
        return ibsCbsTot;
    }

    public Map<String, String> getIsTot() {
        return isTot;
    }

    public Map<String, String> getIssqnTot() {
        return issqnTot;
    }

    public Map<String, String> getTransp() {
        return transp;
    }

    public Map<String, String> getInfAdic() {
        return infAdic;
    }

    public Map<String, String> getSupl() {
        return supl;
    }

    /** Grupo pag fora do detPag: vTroco. */
    public Map<String, String> getPag() {
        return pag;
    }

    /** Formas de pagamento (pag/detPag), na ordem do XML. */
    public List<Map<String, String>> getPagamentos() {
        return pagamentos;
    }

    public List<Map<String, String>> getDuplicatas() {
        return duplicatas;
    }

    public List<Map<String, String>> getItens() {
        return itens;
    }

    /**
     * tpImp = 2 (DANFE normal, paisagem).
     */
    public boolean isPaisagem() {
        return "2".equals(ide.get("tpImp"));
    }

    /**
     * tpAmb = 2 (homologação) OU nota ainda sem protocolo de autorização (pré-visualização/rascunho,
     * mesmo em ambiente de produção): o DANFE recebe a marca "SEM VALOR FISCAL".
     */
    public boolean isHomologacao() {
        return "2".equals(ide.get("tpAmb")) || prot.get("nProt") == null;
    }

    public boolean isExibirFatura() {
        return !duplicatas.isEmpty();
    }

    /**
     * Duplicatas agrupadas em linhas de {@value #DUPLICATAS_POR_LINHA}, como no quadro "Fatura / Duplicatas".
     * A última linha é completada com null para manter as colunas.
     */
    public List<List<Map<String, String>>> getFaturaLinhas() {
        List<List<Map<String, String>>> linhas = new ArrayList<>();
        for (int i = 0; i < duplicatas.size(); i += DUPLICATAS_POR_LINHA) {
            List<Map<String, String>> linha = new ArrayList<>();
            for (int j = i; j < i + DUPLICATAS_POR_LINHA; j++) {
                linha.add(j < duplicatas.size() ? duplicatas.get(j) : null);
            }
            linhas.add(linha);
        }
        return linhas;
    }

    /**
     * Linha FCP / DIFAL. vFCP e vFCPST são obrigatórias no ICMSTot (vêm como 0.00 em toda NF-e 4.00),
     * então "existir no XML" não basta: a linha só é impressa com algum valor diferente de zero.
     */
    public boolean isExibirLinhaFcp() {
        return algumDiferenteDeZero(icmsTot, "vFCP", "vFCPST", "vICMSUFDest", "vFCPUFDest");
    }

    public boolean isExibirLinhaIcmsMono() {
        return algumDiferenteDeZero(icmsTot, "qBCMono", "vICMSMono", "qBCMonoReten", "vICMSMonoReten");
    }

    /**
     * Quadro "Total do IBS/CBS/IS": exibido quando a nota possui os grupos da Reforma Tributária.
     */
    public boolean isExibirIbsCbsIs() {
        return !ibsCbsTot.isEmpty() || !isTot.isEmpty();
    }

    public boolean isExibirLinhaIbsCbsMono() {
        return algumDiferenteDeZero(ibsCbsTot, "vIBSMono", "vCBSMono", "vIBSMonoReten", "vCBSMonoReten");
    }

    /** Linha do IBS/CBS monofásico retido anteriormente (gMono/vIBSMonoRet e vCBSMonoRet). */
    public boolean isExibirLinhaIbsCbsMonoRet() {
        return algumDiferenteDeZero(ibsCbsTot, "vIBSMonoRet", "vCBSMonoRet");
    }

    public boolean isExibirIssqn() {
        return !issqnTot.isEmpty();
    }

    /** O modFrete é obrigatório no XML: sem transportadora, o quadro sai só com a modalidade do frete. */
    public boolean isExibirTransporte() {
        return !transp.isEmpty();
    }

    public boolean isExibirQrCode() {
        return supl.get("qrCode") != null;
    }

    /** tpImp = 6 (DANFE Simplificado Tipo 2, NT 2026.003). */
    public boolean isSimplificadoTipo2() {
        return "6".equals(ide.get("tpImp"));
    }

    /** mod = 65 (NFC-e). */
    public boolean isNfce() {
        return "65".equals(ide.get("mod"));
    }

    /** DANFE em bobina: o da NFC-e ou o Simplificado Tipo 2 da NF-e. */
    public boolean isBobina() {
        return isNfce() || isSimplificadoTipo2();
    }

    /** tpAmb = 2. Diferente de {@link #isHomologacao()}, não considera a falta de protocolo. */
    public boolean isAmbienteHomologacao() {
        return "2".equals(ide.get("tpAmb"));
    }

    /**
     * Emitida em contingência e ainda sem protocolo de autorização: tpEmis diferente de 1 (normal) e das SEFAZ
     * virtuais (6 e 7, que autorizam on-line). Depois de autorizada (nfeProc com protocolo), deixa de ser pendente.
     */
    public boolean isContingenciaPendente() {
        String tpEmis = ide.get("tpEmis");
        return tpEmis != null && !"1".equals(tpEmis) && !"6".equals(tpEmis) && !"7".equals(tpEmis)
                && prot.get("nProt") == null;
    }

    /** Produção, emissão normal e sem protocolo: pré-visualização, sem valor fiscal. */
    public boolean isPreVisualizacao() {
        return !isAmbienteHomologacao() && !isContingenciaPendente() && prot.get("nProt") == null;
    }

    /** Linhas de frete, seguro, outras despesas e desconto do DANFE Simplificado Tipo 2: só com algum valor. */
    public boolean isExibirAcrescimoDesconto() {
        return algumDiferenteDeZero(icmsTot, "vFrete", "vSeg", "vOutro", "vDesc");
    }

    /** Linha do Imposto Seletivo no DANFE Simplificado Tipo 2: só se houver IS (NT 2026.003 item 3.1.4). */
    public boolean isExibirLinhaIs() {
        return algumDiferenteDeZero(isTot, "vIS");
    }

    /** Tributos totais incidentes (Lei 12.741/2012), do ICMSTot/vTotTrib: só com valor. */
    public boolean isExibirTributosTotais() {
        return algumDiferenteDeZero(icmsTot, "vTotTrib");
    }

    /**
     * CBS da nota para o "(+) CBS R$" do DANFE Simplificado Tipo 2: gCBS/vCBS mais a CBS monofásica (gMono/vCBSMono),
     * como texto do XML (ponto decimal). Null quando a nota não tem nenhum dos dois.
     */
    public String getCbsTotal() {
        return soma(ibsCbsTot, "vCBS", "vCBSMono");
    }

    /** IBS da nota (UF e Município) para o "(+) IBS R$": gIBS/vIBS mais o IBS monofásico (gMono/vIBSMono). */
    public String getIbsTotal() {
        return soma(ibsCbsTot, "vIBS", "vIBSMono");
    }

    private static String soma(Map<String, String> mapa, String... chaves) {
        BigDecimal total = null;
        for (String c : chaves) {
            String valor = mapa.get(c);
            if (valor == null) {
                continue;
            }
            try {
                BigDecimal parcela = new BigDecimal(valor.trim());
                total = total == null ? parcela : total.add(parcela);
            } catch (NumberFormatException e) {
                return valor;
            }
        }
        return total == null ? null : total.toPlainString();
    }

    /** Alguma das chaves tem valor diferente de zero (valor não numérico conta como presente). */
    private static boolean algumDiferenteDeZero(Map<String, String> mapa, String... chaves) {
        for (String c : chaves) {
            String valor = mapa.get(c);
            if (valor == null) {
                continue;
            }
            try {
                if (new BigDecimal(valor.trim()).signum() != 0) {
                    return true;
                }
            } catch (NumberFormatException e) {
                return true;
            }
        }
        return false;
    }
}
