package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Converte o XML da NF-e (nfeProc ou NFe) no {@link DanfeV2DTO}.
 * <p>
 * As chaves dos Maps são os nomes das tags do XML. Exceções (nomes derivados, para evitar colisão):
 * <ul>
 *     <li>transp: placaUF (veicTransp/UF); os dados de vol são do primeiro volume</li>
 *     <li>item: CST (CST ou CSOSN do ICMS), vBCICMS, vBCIPI, vBCIBSCBS</li>
 *     <li>item: pIBSUF, pIBSMun e pCBS já trazem a alíquota efetiva (gRed/pAliqEfet) quando informada,
 *     conforme regra da NT 2026.010 item 4.3; a alíquota vigente fica em pIBSUFVigente, pIBSMunVigente e pCBSVigente</li>
 * </ul>
 */
public final class LeitorDanfeV2 {

    private static final String IBSCBS_TOT = "total/IBSCBSTot/";
    private static final String IBSCBS_ITEM = "imposto/IBSCBS/gIBSCBS/";

    private LeitorDanfeV2() {
    }

    public static DanfeV2DTO ler(XmlDanfeV2 xml) {
        Node infNFe = xml.no("//infNFe");
        if (infNFe == null) {
            throw new DanfeException("XML não contém o grupo infNFe.");
        }
        DanfeV2DTO danfe = new DanfeV2DTO();

        copia(xml, infNFe, danfe.getIde(), "ide/", "cUF", "natOp", "mod", "serie", "nNF", "dhEmi", "dhSaiEnt",
                "tpNF", "tpImp", "tpEmis", "tpAmb", "finNFe", "verProc", "dhCont", "xJust");

        copia(xml, infNFe, danfe.getEmit(), "emit/", "CNPJ", "CPF", "xNome", "xFant", "IE", "IEST", "IM", "CRT");
        copia(xml, infNFe, danfe.getEmit(), "emit/enderEmit/", "xLgr", "nro", "xCpl", "xBairro", "xMun", "UF", "CEP", "fone");

        copia(xml, infNFe, danfe.getDest(), "dest/", "CNPJ", "CPF", "idEstrangeiro", "xNome", "IE", "email");
        copia(xml, infNFe, danfe.getDest(), "dest/enderDest/", "xLgr", "nro", "xCpl", "xBairro", "xMun", "UF", "CEP", "fone");

        Node infProt = xml.no("//protNFe/infProt");
        if (infProt != null) {
            copia(xml, infProt, danfe.getProt(), "", "nProt", "dhRecbto", "chNFe", "cStat");
        }
        danfe.setChave(chave(xml, infNFe, danfe.getProt().get("chNFe")));

        copia(xml, infNFe, danfe.getIcmsTot(), "total/ICMSTot/", "vBC", "vICMS", "vICMSDeson", "vFCPUFDest",
                "vICMSUFDest", "vICMSUFRemet", "vFCP", "vBCST", "vST", "vFCPST", "vFCPSTRet", "qBCMono", "vICMSMono",
                "qBCMonoReten", "vICMSMonoReten", "qBCMonoRet", "vICMSMonoRet", "vProd", "vFrete", "vSeg", "vDesc",
                "vII", "vIPI", "vIPIDevol", "vPIS", "vCOFINS", "vOutro", "vNF", "vTotTrib");

        copiaMapeado(xml, infNFe, danfe.getIbsCbsTot(),
                "vBCIBSCBS", IBSCBS_TOT + "vBCIBSCBS",
                "vIBSUF", IBSCBS_TOT + "gIBS/gIBSUF/vIBSUF",
                "vIBSMun", IBSCBS_TOT + "gIBS/gIBSMun/vIBSMun",
                "vIBS", IBSCBS_TOT + "gIBS/vIBS",
                "vCBS", IBSCBS_TOT + "gCBS/vCBS",
                "vIBSMono", IBSCBS_TOT + "gMono/vIBSMono",
                "vCBSMono", IBSCBS_TOT + "gMono/vCBSMono",
                "vIBSMonoReten", IBSCBS_TOT + "gMono/vIBSMonoReten",
                "vCBSMonoReten", IBSCBS_TOT + "gMono/vCBSMonoReten");
        copia(xml, infNFe, danfe.getIsTot(), "total/ISTot/", "vIS");
        copia(xml, infNFe, danfe.getIssqnTot(), "total/ISSQNtot/", "vServ", "vBC", "vISS");

        copia(xml, infNFe, danfe.getTransp(), "transp/", "modFrete");
        copia(xml, infNFe, danfe.getTransp(), "transp/transporta/", "CNPJ", "CPF", "xNome", "IE", "xEnder", "xMun", "UF");
        copiaMapeado(xml, infNFe, danfe.getTransp(),
                "placa", "transp/veicTransp/placa",
                "placaUF", "transp/veicTransp/UF",
                "RNTC", "transp/veicTransp/RNTC");
        copia(xml, infNFe, danfe.getTransp(), "transp/vol[1]/", "qVol", "esp", "marca", "nVol", "pesoL", "pesoB");

        copia(xml, infNFe, danfe.getInfAdic(), "infAdic/", "infCpl", "infAdFisco");
        copiaMapeado(xml, xml.getDocumento(), danfe.getSupl(),
                "qrCode", "//infNFeSupl/qrCode",
                "urlChave", "//infNFeSupl/urlChave");

        for (Node dup : xml.nos(infNFe, "cobr/dup")) {
            Map<String, String> duplicata = new LinkedHashMap<>();
            copia(xml, dup, duplicata, "", "nDup", "dVenc", "vDup");
            danfe.getDuplicatas().add(duplicata);
        }

        for (Node det : xml.nos(infNFe, "det")) {
            danfe.getItens().add(item(xml, det));
        }
        return danfe;
    }

    private static Map<String, String> item(XmlDanfeV2 xml, Node det) {
        Map<String, String> item = new LinkedHashMap<>();
        coloca(item, "nItem", ((Element) det).getAttribute("nItem"));
        copia(xml, det, item, "prod/", "cProd", "cEAN", "xProd", "NCM", "CEST", "CFOP", "uCom", "qCom", "vUnCom", "vProd");
        copia(xml, det, item, "", "infAdProd");

        copiaMapeado(xml, det, item,
                "orig", "imposto/ICMS/*/orig",
                "CST", "imposto/ICMS/*/CST | imposto/ICMS/*/CSOSN",
                "vBCICMS", "imposto/ICMS/*/vBC",
                "pICMS", "imposto/ICMS/*/pICMS",
                "vICMS", "imposto/ICMS/*/vICMS",
                "vBCIPI", "imposto/IPI/IPITrib/vBC",
                "pIPI", "imposto/IPI/IPITrib/pIPI",
                "vIPI", "imposto/IPI/IPITrib/vIPI",
                "cClassTrib", "imposto/IBSCBS/cClassTrib",
                "vBCIBSCBS", IBSCBS_ITEM + "vBC",
                "pIBSUFVigente", IBSCBS_ITEM + "gIBSUF/pIBSUF",
                "vIBSUF", IBSCBS_ITEM + "gIBSUF/vIBSUF",
                "pIBSMunVigente", IBSCBS_ITEM + "gIBSMun/pIBSMun",
                "vIBSMun", IBSCBS_ITEM + "gIBSMun/vIBSMun",
                "pCBSVigente", IBSCBS_ITEM + "gCBS/pCBS",
                "vCBS", IBSCBS_ITEM + "gCBS/vCBS",
                "vBCIS", "imposto/IS/vBCIS",
                "pIS", "imposto/IS/pIS",
                "vIS", "imposto/IS/vIS");

        // NT 2026.010 item 4.3: com gRed (redução de alíquota ou compra governamental) imprime-se a alíquota efetiva
        coloca(item, "pIBSUF", primeiro(xml.valor(det, IBSCBS_ITEM + "gIBSUF/gRed/pAliqEfet"), item.get("pIBSUFVigente")));
        coloca(item, "pIBSMun", primeiro(xml.valor(det, IBSCBS_ITEM + "gIBSMun/gRed/pAliqEfet"), item.get("pIBSMunVigente")));
        coloca(item, "pCBS", primeiro(xml.valor(det, IBSCBS_ITEM + "gCBS/gRed/pAliqEfet"), item.get("pCBSVigente")));
        return item;
    }

    private static String chave(XmlDanfeV2 xml, Node infNFe, String chaveProtocolo) {
        if (chaveProtocolo != null) {
            return chaveProtocolo;
        }
        String id = xml.valor(infNFe, "@Id");
        return id != null && id.startsWith("NFe") ? id.substring(3) : id;
    }

    private static void copia(XmlDanfeV2 xml, Object contexto, Map<String, String> destino, String prefixo, String... tags) {
        for (String tag : tags) {
            coloca(destino, tag, xml.valor(contexto, prefixo + tag));
        }
    }

    /**
     * Pares chave, expressão XPath.
     */
    private static void copiaMapeado(XmlDanfeV2 xml, Object contexto, Map<String, String> destino, String... chaveExpressao) {
        for (int i = 0; i < chaveExpressao.length; i += 2) {
            coloca(destino, chaveExpressao[i], xml.valor(contexto, chaveExpressao[i + 1]));
        }
    }

    private static void coloca(Map<String, String> destino, String chave, String valor) {
        if (valor != null && !valor.trim().isEmpty()) {
            destino.put(chave, valor);
        }
    }

    private static String primeiro(String a, String b) {
        return a != null ? a : b;
    }
}
