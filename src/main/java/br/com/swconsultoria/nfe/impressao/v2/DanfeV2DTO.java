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
     * tpAmb = 2 (homologação): o DANFE recebe a marca "SEM VALOR FISCAL".
     */
    public boolean isHomologacao() {
        return "2".equals(ide.get("tpAmb"));
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

    public boolean isExibirIssqn() {
        return !issqnTot.isEmpty();
    }

    public boolean isExibirTransporte() {
        for (String chaveTransp : transp.keySet()) {
            if (!"modFrete".equals(chaveTransp)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExibirQrCode() {
        return supl.get("qrCode") != null;
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
