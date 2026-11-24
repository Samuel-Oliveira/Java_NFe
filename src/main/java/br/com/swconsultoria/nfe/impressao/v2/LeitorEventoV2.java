package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import org.w3c.dom.Node;

import static br.com.swconsultoria.nfe.impressao.v2.LeitorDanfeV2.copia;
import static br.com.swconsultoria.nfe.impressao.v2.LeitorDanfeV2.coloca;

/**
 * Converte o XML de um evento da NF-e (procEventoNFe, ou evento sem o retorno da SEFAZ) no {@link EventoV2DTO}.
 */
public final class LeitorEventoV2 {

    private LeitorEventoV2() {
    }

    public static EventoV2DTO ler(XmlDanfeV2 xml) {
        // "evento/infEvento" não casa com o retEvento/infEvento do procEventoNFe
        Node infEvento = xml.no("//evento/infEvento");
        if (infEvento == null) {
            throw new DanfeException("XML não contém o grupo evento/infEvento.");
        }
        EventoV2DTO evento = new EventoV2DTO();
        coloca(evento.getEvento(), "Id", xml.valor(infEvento, "@Id"));
        copia(xml, infEvento, evento.getEvento(), "", "cOrgao", "tpAmb", "CNPJ", "CPF", "chNFe", "dhEvento",
                "tpEvento", "nSeqEvento", "verEvento");
        copia(xml, infEvento, evento.getDetEvento(), "detEvento/", "descEvento", "xCorrecao", "xCondUso");

        Node retorno = xml.no("//retEvento/infEvento");
        if (retorno != null) {
            copia(xml, retorno, evento.getRetEvento(), "", "verAplic", "cStat", "xMotivo", "xEvento", "CNPJDest",
                    "CPFDest", "dhRegEvento", "nProt");
        }
        return evento;
    }
}
