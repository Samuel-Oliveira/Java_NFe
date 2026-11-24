package br.com.swconsultoria.nfe.impressao.v2;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Dados de um evento da NF-e (procEventoNFe ou evento sem retorno) para impressão, hoje a Carta de Correção
 * eletrônica. Como no {@link DanfeV2DTO}, cada grupo é um Map cuja chave é o nome da tag no XML e o valor é o texto
 * bruto; tags inexistentes não estão no Map. Exposto ao template Thymeleaf como "evento".
 * <ul>
 *     <li>evento: evento/infEvento: Id, cOrgao, tpAmb, CNPJ, CPF, chNFe, dhEvento, tpEvento, nSeqEvento, verEvento</li>
 *     <li>detEvento: descEvento, xCorrecao, xCondUso</li>
 *     <li>retEvento: retEvento/infEvento: verAplic, cStat, xMotivo, xEvento, CNPJDest, CPFDest, dhRegEvento, nProt</li>
 * </ul>
 */
public class EventoV2DTO {

    /** Tipo de evento da Carta de Correção eletrônica. */
    public static final String TP_EVENTO_CCE = "110110";

    private final Map<String, String> evento = new LinkedHashMap<>();
    private final Map<String, String> detEvento = new LinkedHashMap<>();
    private final Map<String, String> retEvento = new LinkedHashMap<>();

    public Map<String, String> getEvento() {
        return evento;
    }

    public Map<String, String> getDetEvento() {
        return detEvento;
    }

    public Map<String, String> getRetEvento() {
        return retEvento;
    }

    /** Chave de acesso da NF-e a que o evento se refere. */
    public String getChave() {
        return evento.get("chNFe");
    }

    /** Modelo da NF-e, tirado da chave (posições 21 e 22). */
    public String getModelo() {
        return parteDaChave(20, 22);
    }

    /** Série da NF-e, tirada da chave. */
    public String getSerie() {
        return parteDaChave(22, 25);
    }

    /** Número da NF-e, tirado da chave. */
    public String getNumero() {
        return parteDaChave(25, 34);
    }

    public boolean isCartaCorrecao() {
        return TP_EVENTO_CCE.equals(evento.get("tpEvento"));
    }

    /** tpAmb = 2 OU evento ainda sem protocolo de registro: a impressão recebe a marca "SEM VALOR FISCAL". */
    public boolean isHomologacao() {
        return "2".equals(evento.get("tpAmb")) || retEvento.get("nProt") == null;
    }

    private String parteDaChave(int inicio, int fim) {
        String chave = getChave();
        return chave == null || chave.length() != 44 ? null : chave.substring(inicio, fim);
    }
}
