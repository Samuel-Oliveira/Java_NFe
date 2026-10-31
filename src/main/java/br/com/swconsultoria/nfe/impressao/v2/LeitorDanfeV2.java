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
 *     <li>item: CST (CST ou CSOSN do ICMS), cstIBSCBS (IBSCBS/CST), vBCICMS, vBCIPI, vBCIBSCBS</li>
 *     <li>item: pIBSUF, pIBSMun e pCBS já trazem a alíquota efetiva (gRed/pAliqEfet) quando informada,
 *     conforme regra da NT 2026.010 item 4.3; a alíquota vigente fica em pIBSUFVigente, pIBSMunVigente e pCBSVigente</li>
 * </ul>
 * O grupo imposto/IBSCBS do item é uma escolha entre gIBSCBS, gIBSCBSMono, gTransfCred e gAjusteCompet
 * (TTribNFe do schema). As chaves do item são as mesmas para os quatro casos, para o template não precisar
 * saber qual grupo veio:
 * <ul>
 *     <li>vBCIBSCBS, pIBSUF, vIBSUF, pIBSMun, vIBSMun, pCBS, vCBS: gIBSCBS, ou monofásico ad valorem
 *     (gMonoPadrao/vBCMono, pAliqMonoUF, vIBSMonoUF, pAliqMonoMun, vIBSMonoMun, pAliqMonoCBS, vCBSMono)</li>
 *     <li>vIBS: valor do IBS do item sem a abertura UF/Município: gIBSCBS/vIBS, vIBSMono, gTransfCred/vIBS ou
 *     gAjusteCompet/vIBS; vCBS também cobre vCBSMono, gTransfCred/vCBS e gAjusteCompet/vCBS</li>
 *     <li>monofásico ad rem: qBCMono (base em quantidade), adRemIBS, adRemCBS, vIBSMono, vCBSMono.
 *     O schema permite IBS e CBS em regimes diferentes no mesmo item (ad rem num, ad valorem no outro), mas o
 *     leitor assume o mesmo regime nos dois, como ocorre na tributação de combustíveis: no caso misto,
 *     vBCIBSCBS vem do ad valorem e qBCMono do ad rem, e o template padrão imprime só a base em R$</li>
 *     <li>monofásico, demais: vIBSMonoReten, vCBSMonoReten (retenção), vIBSMonoRet, vCBSMonoRet (retido
 *     anteriormente), vIBSDiferenca, vCBSDiferenca (biocombustível), vTotIBSMonoItem, vTotCBSMonoItem</li>
 *     <li>ajuste de competência: competApur</li>
 * </ul>
 * Ficam disponíveis para templates próprios, embora o template padrão não os imprima: ibsCbsTot.vIBS
 * (soma de IBS UF e Município, já impressos separados) e supl.urlChave.
 */
public final class LeitorDanfeV2 {

    private static final String IBSCBS_TOT = "total/IBSCBSTot/";
    private static final String IBSCBS_ITEM = "imposto/IBSCBS/gIBSCBS/";
    private static final String IBSCBS_ITEM_MONO = "imposto/IBSCBS/gIBSCBSMono/";
    private static final String IBS_AD_REM = IBSCBS_ITEM_MONO + "gIBSMonoAdRem/";
    private static final String IBS_AD_VALOREM = IBSCBS_ITEM_MONO + "gIBSMonoAdValorem/";
    private static final String CBS_AD_REM = IBSCBS_ITEM_MONO + "gCBSMonoAdRem/";
    private static final String CBS_AD_VALOREM = IBSCBS_ITEM_MONO + "gCBSMonoAdValorem/";
    private static final String TRANSF_CRED = "imposto/IBSCBS/gTransfCred/";
    private static final String AJUSTE_COMPET = "imposto/IBSCBS/gAjusteCompet/";

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
                "vCBSMonoReten", IBSCBS_TOT + "gMono/vCBSMonoReten",
                "vIBSMonoRet", IBSCBS_TOT + "gMono/vIBSMonoRet",
                "vCBSMonoRet", IBSCBS_TOT + "gMono/vCBSMonoRet");
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

        // Nas uniões de XPath ("a | b") vale o primeiro nó na ordem do documento; os grupos são mutuamente exclusivos
        copiaMapeado(xml, det, item,
                "orig", "imposto/ICMS/*/orig",
                "CST", "imposto/ICMS/*/CST | imposto/ICMS/*/CSOSN",
                "vBCICMS", "imposto/ICMS/*/vBC",
                "pICMS", "imposto/ICMS/*/pICMS",
                "vICMS", "imposto/ICMS/*/vICMS",
                "vBCIPI", "imposto/IPI/IPITrib/vBC",
                "pIPI", "imposto/IPI/IPITrib/pIPI",
                "vIPI", "imposto/IPI/IPITrib/vIPI",
                "cstIBSCBS", "imposto/IBSCBS/CST",
                "cClassTrib", "imposto/IBSCBS/cClassTrib",
                "vBCIBSCBS", IBSCBS_ITEM + "vBC | " + IBS_AD_VALOREM + "gMonoPadrao/vBCMono | " + CBS_AD_VALOREM + "gMonoPadrao/vBCMono",
                "pIBSUFVigente", IBSCBS_ITEM + "gIBSUF/pIBSUF | " + IBS_AD_VALOREM + "gMonoPadrao/pAliqMonoUF",
                "vIBSUF", IBSCBS_ITEM + "gIBSUF/vIBSUF | " + IBS_AD_VALOREM + "gMonoPadrao/vIBSMonoUF",
                "pIBSMunVigente", IBSCBS_ITEM + "gIBSMun/pIBSMun | " + IBS_AD_VALOREM + "gMonoPadrao/pAliqMonoMun",
                "vIBSMun", IBSCBS_ITEM + "gIBSMun/vIBSMun | " + IBS_AD_VALOREM + "gMonoPadrao/vIBSMonoMun",
                "vIBS", IBSCBS_ITEM + "vIBS | " + IBS_AD_REM + "gMonoPadrao/vIBSMono | " + IBS_AD_VALOREM + "gMonoPadrao/vIBSMono | "
                        + TRANSF_CRED + "vIBS | " + AJUSTE_COMPET + "vIBS",
                "pCBSVigente", IBSCBS_ITEM + "gCBS/pCBS | " + CBS_AD_VALOREM + "gMonoPadrao/pAliqMonoCBS",
                "vCBS", IBSCBS_ITEM + "gCBS/vCBS | " + CBS_AD_REM + "gMonoPadrao/vCBSMono | " + CBS_AD_VALOREM + "gMonoPadrao/vCBSMono | "
                        + TRANSF_CRED + "vCBS | " + AJUSTE_COMPET + "vCBS",
                // Monofásico ad rem: base em quantidade e alíquota em R$ por unidade (TMonofasia)
                "qBCMono", IBS_AD_REM + "gMonoPadrao/qBCMono | " + CBS_AD_REM + "gMonoPadrao/qBCMono",
                "adRemIBS", IBS_AD_REM + "gMonoPadrao/adRemIBS",
                "adRemCBS", CBS_AD_REM + "gMonoPadrao/adRemCBS",
                "vIBSMono", IBS_AD_REM + "gMonoPadrao/vIBSMono | " + IBS_AD_VALOREM + "gMonoPadrao/vIBSMono",
                "vCBSMono", CBS_AD_REM + "gMonoPadrao/vCBSMono | " + CBS_AD_VALOREM + "gMonoPadrao/vCBSMono",
                "vIBSMonoReten", IBS_AD_REM + "gMonoReten/vIBSMonoReten | " + IBS_AD_VALOREM + "gMonoReten/vIBSMonoReten",
                "vCBSMonoReten", CBS_AD_REM + "gMonoReten/vCBSMonoReten | " + CBS_AD_VALOREM + "gMonoReten/vCBSMonoReten",
                "vIBSMonoRet", IBS_AD_REM + "gMonoRet/vIBSMonoRet | " + IBS_AD_VALOREM + "gMonoRet/vIBSMonoRet",
                "vCBSMonoRet", CBS_AD_REM + "gMonoRet/vCBSMonoRet | " + CBS_AD_VALOREM + "gMonoRet/vCBSMonoRet",
                "vIBSDiferenca", IBS_AD_REM + "gpBioDiferenca/vIBSDiferenca | " + IBS_AD_VALOREM + "gpBioDiferenca/vIBSDiferenca",
                "vCBSDiferenca", CBS_AD_REM + "gpBioDiferenca/vCBSDiferenca | " + CBS_AD_VALOREM + "gpBioDiferenca/vCBSDiferenca",
                "vTotIBSMonoItem", IBSCBS_ITEM_MONO + "vTotIBSMonoItem",
                "vTotCBSMonoItem", IBSCBS_ITEM_MONO + "vTotCBSMonoItem",
                "competApur", AJUSTE_COMPET + "competApur",
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
