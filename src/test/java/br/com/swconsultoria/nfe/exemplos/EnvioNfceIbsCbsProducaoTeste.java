package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.schemas.TEnviNFe;
import br.com.swconsultoria.nfe.schemas.TNFe;
import br.com.swconsultoria.nfe.schemas.TRetEnviNFe;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.NFCeUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import java.time.LocalDateTime;

/**
 * Reproduz a NFC-e de combustivel que o cliente nao conseguiu autorizar, com a
 * MESMA estrutura de IBS/CBS, para separar duas hipoteses sobre a rejeicao 225
 * (Falha no Schema XML do lote de NF-e):
 *
 * a) a producao da SEFAZ ainda roda o layout monofasico ANTIGO (PL 010e) e
 * recusa o reformulado (gIBSMonoAdValorem / gCBSMonoAdValorem) do PL 010g;
 * b) a estrutura que a lib monta esta errada, e seria recusada em qualquer
 * ambiente.
 *
 * Rode nos dois ambientes e compare:
 * -Dambiente=HOMOLOGACAO e -Dambiente=PRODUCAO
 * Homologacao aceitando e producao devolvendo 225 confirma (a). As duas
 * devolvendo 225 confirma (b).
 *
 * ATENCAO: com o QR-Code v3 (padrao, ver abaixo) este teste AUTORIZA documento
 * fiscal de verdade em producao. Cancele em seguida com o CancelarTeste,
 * passando -Dambiente=PRODUCAO, -Dchave e -Dprotocolo.
 *
 * Resultado medido em 14/11/2026, GO, serie 2, QR-Code v3:
 *
 *   grupo       HOMOLOGACAO            PRODUCAO
 *   mono        104 / nota 373         225 (lote, nem chega na nota)
 *   semgrupo    104 / nota 373         104 / nota 100 AUTORIZADO
 *   nenhum      104 / nota 373         104 / nota 100 AUTORIZADO
 *
 * O 373 e regra so de homologacao (xProd do primeiro item tem de ser o literal
 * "NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL").
 *
 * O QR-Code v3 (NT 2025.001) e so `?p=<chave>|3|<tpAmb>`, sem CSC nem idToken,
 * o que permite testar sem o segredo do contribuinte. Com -Dqrcode=2 a nota
 * para em 464 (Codigo de Hash no QR-Code difere do calculado), porque o CSC
 * embutido aqui e o de homologacao.
 *
 * CUIDADO COM A SERIE: GO devolve 999 (Erro nao catalogado) no lote inteiro
 * para serie 890, em qualquer ambiente e com qualquer conteudo. Series 1 e 2
 * passam. Nao use serie alta aqui achando que e so para evitar colisao de
 * numeracao - use um numero alto e uma serie baixa.
 */
public class EnvioNfceIbsCbsProducaoTeste {

    private static final String CNPJ_EMIT = System.getProperty("cnpjEmit", "10732644000128");
    private static final String IE_EMIT = System.getProperty("ieEmit", "104519304");
    private static final String NOME_EMIT = System.getProperty("nomeEmit", "POSTO PARK XIII LTDA");
    private static final String SERIE = System.getProperty("serie", "890");
    private static final String NUMERO = System.getProperty("numero", "999999");
    private static final String ID_TOKEN = System.getProperty("idToken", "000001");
    private static final String CSC = System.getProperty("csc", "5875f1ced69b589a");

    public static void main(String[] args) {
        try {
            AmbienteEnum ambiente = AmbienteEnum.valueOf(System.getProperty("ambiente", "HOMOLOGACAO"));
            EstadosEnum estado = EstadosEnum.valueOf(System.getProperty("uf", "GO"));
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(estado, ambiente);

            LocalDateTime agora = LocalDateTime.now();
            String cNF = "00000001";
            ChaveUtil chaveUtil = new ChaveUtil(estado, CNPJ_EMIT, DocumentoEnum.NFCE.getModelo(),
                    Integer.parseInt(SERIE), Integer.parseInt(NUMERO), "1", cNF, agora);
            String id = chaveUtil.getChaveNF();

            String xml = montaXml(id, cNF, XmlNfeUtil.dataNfe(agora, null), ambiente.getCodigo(), estado);
            TEnviNFe enviNFe = XmlNfeUtil.xmlToObject(xml, TEnviNFe.class);

            System.out.println("# Ambiente : " + ambiente + " (tpAmb=" + ambiente.getCodigo() + ")");
            System.out.println("# Chave    : " + id.substring(3));

            // Assina e valida contra o schema local antes de sair da maquina.
            enviNFe = Nfe.montaNfe(config, enviNFe, true);
            System.out.println("# Schema local: OK");

            // QR-Code v3 (NT 2025.001): so chave, versao e ambiente, sem CSC nem idToken.
            // O v2 (getCodeQRCode) esta @Deprecated na lib e exige o hash do CSC.
            String urlQr = WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_QRCODE);
            String qrCode = "2".equals(System.getProperty("qrcode", "3"))
                    ? NFCeUtil.getCodeQRCode(id.substring(3), ambiente.getCodigo(), ID_TOKEN, CSC, urlQr)
                    : NFCeUtil.getCodeQRCodeV3(id.substring(3), ambiente.getCodigo(), urlQr);
            System.out.println("# QRCode   : " + qrCode);

            TNFe.InfNFeSupl supl = new TNFe.InfNFeSupl();
            supl.setQrCode(qrCode);
            supl.setUrlChave(WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_CONSULTANFCE));
            enviNFe.getNFe().get(0).setInfNFeSupl(supl);

            TRetEnviNFe retorno = Nfe.enviarNfe(config, enviNFe, DocumentoEnum.NFCE);

            System.out.println("# Lote     : " + retorno.getCStat() + " - " + retorno.getXMotivo());
            if (retorno.getProtNFe() != null) {
                System.out.println("# Nota     : " + retorno.getProtNFe().getInfProt().getCStat()
                        + " - " + retorno.getProtNFe().getInfProt().getXMotivo());
                System.out.println("# Protocolo: " + retorno.getProtNFe().getInfProt().getNProt());
            }

        } catch (Exception e) {
            System.err.println("# Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Item unico de R$ 1,00: 0,1495 L a R$ 6,69. O grupo IBSCBS e copia fiel do
     * XML que o cliente enviou, inclusive os zeros do gMonoRet.
     */
    private static String montaXml(String id, String cNF, String dhEmi, String tpAmb, EstadosEnum estado) {
        return "<enviNFe versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">"
                + "<idLote>1</idLote><indSinc>1</indSinc>"
                + "<NFe><infNFe versao=\"4.00\" Id=\"" + id + "\">"
                + "<ide><cUF>" + estado.getCodigoUF() + "</cUF><cNF>" + cNF + "</cNF>"
                + "<natOp>VENDA DE COMBUSTIVEL OU LUBRIFICANTE</natOp>"
                + "<mod>65</mod><serie>" + SERIE + "</serie><nNF>" + NUMERO + "</nNF>"
                + "<dhEmi>" + dhEmi + "</dhEmi><tpNF>1</tpNF><idDest>1</idDest>"
                + "<cMunFG>5219753</cMunFG><tpImp>4</tpImp><tpEmis>1</tpEmis>"
                + "<cDV>" + id.substring(id.length() - 1) + "</cDV><tpAmb>" + tpAmb + "</tpAmb>"
                + "<finNFe>1</finNFe><indFinal>1</indFinal><indPres>1</indPres>"
                + "<procEmi>0</procEmi><verProc>teste-225</verProc></ide>"
                + "<emit><CNPJ>" + CNPJ_EMIT + "</CNPJ><xNome>" + NOME_EMIT + "</xNome>"
                + "<enderEmit><xLgr>AV SANTO ANTONIO</xLgr><nro>0</nro><xBairro>PQ STO ANTONIO</xBairro>"
                + "<cMun>5219753</cMun><xMun>SANTO ANTONIO DO DESCOBERTO</xMun><UF>" + estado + "</UF>"
                + "<CEP>72900000</CEP><cPais>1058</cPais><xPais>Brasil</xPais></enderEmit>"
                + "<IE>" + IE_EMIT + "</IE><CRT>3</CRT></emit>"
                + "<det nItem=\"1\"><prod>"
                + "<cProd>820101034</cProd><cEAN>SEM GTIN</cEAN><xProd>DIESEL S10 COMUM</xProd>"
                + "<NCM>27101921</NCM><CEST>0600600</CEST>"
                + "<cBenef>" + System.getProperty("cBenef", "GO890004") + "</cBenef><CFOP>5656</CFOP>"
                + "<uCom>LT</uCom><qCom>0.1500</qCom><vUnCom>6.6666666667</vUnCom><vProd>1.00</vProd>"
                + "<cEANTrib>SEM GTIN</cEANTrib><uTrib>L</uTrib><qTrib>0.1500</qTrib>"
                + "<vUnTrib>6.6666666667</vUnTrib><indTot>1</indTot>"
                + "<comb><cProdANP>820101034</cProdANP><descANP>DIESEL S10 COMUM</descANP>"
                + "<UFCons>" + estado + "</UFCons>"
                + "<encerrante><nBico>1</nBico><nTanque>1</nTanque>"
                + "<vEncIni>1000.000</vEncIni><vEncFin>1000.150</vEncFin></encerrante>"
                + "<pBio>27.0000</pBio>"
                + "<origComb><indImport>0</indImport><cUFOrig>" + estado.getCodigoUF() + "</cUFOrig>"
                + "<pOrig>100</pOrig></origComb></comb></prod>"
                + "<imposto><vTotTrib>0.27</vTotTrib>"
                + "<ICMS><ICMS61><orig>0</orig><CST>61</CST><qBCMonoRet>0.1500</qBCMonoRet>"
                + "<adRemICMSRet>1.1700</adRemICMSRet><vICMSMonoRet>0.18</vICMSMonoRet></ICMS61></ICMS>"
                + "<PIS><PISNT><CST>04</CST></PISNT></PIS>"
                + "<COFINS><COFINSNT><CST>04</CST></COFINSNT></COFINS>"
                + grupoIbsCbs() + "</imposto></det>"
                + "<total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vICMSDeson>0.00</vICMSDeson>"
                + "<vFCP>0.00</vFCP><vBCST>0.00</vBCST><vST>0.00</vST><vFCPST>0.00</vFCPST>"
                + "<vFCPSTRet>0.00</vFCPSTRet><qBCMonoRet>0.15</qBCMonoRet><vICMSMonoRet>0.18</vICMSMonoRet>"
                + "<vProd>1.00</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc>"
                + "<vII>0.00</vII><vIPI>0.00</vIPI><vIPIDevol>0.00</vIPIDevol><vPIS>0.00</vPIS>"
                + "<vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>1.00</vNF><vTotTrib>0.27</vTotTrib></ICMSTot>"
                + totaisIbsCbs() + "</total>"
                + "<transp><modFrete>9</modFrete></transp>"
                + "<pag><detPag><tPag>01</tPag><vPag>1.00</vPag></detPag></pag>"
                + "<infRespTec><CNPJ>32330160000195</CNPJ><xContato>Samuel Oliveira</xContato>"
                + "<email>samuel@swconsultoria.com.br</email><fone>62993066546</fone></infRespTec>"
                + "</infNFe></NFe></enviNFe>";
    }

    /**
     * Variantes do grupo IBSCBS no item, para isolar o que a producao recusa.
     * -Dgrupo=mono     : o que a lib monta hoje (PL 010g, ad valorem/ad rem separados)
     * -Dgrupo=semgrupo : so CST e cClassTrib, sem nenhum grupo de calculo
     * -Dgrupo=nenhum   : sem o elemento IBSCBS
     */
    private static String grupoIbsCbs() {
        String variante = System.getProperty("grupo", "mono");
        if ("nenhum".equals(variante)) {
            return "";
        }
        String cabecalho = "<IBSCBS><CST>620</CST><cClassTrib>620006</cClassTrib>";
        if ("semgrupo".equals(variante)) {
            return cabecalho + "</IBSCBS>";
        }
        return cabecalho
                + "<gIBSCBSMono>"
                + "<gIBSMonoAdValorem><gMonoRet><vIBSMonoRet>0.00</vIBSMonoRet></gMonoRet></gIBSMonoAdValorem>"
                + "<gCBSMonoAdValorem><gMonoRet><vCBSMonoRet>0.00</vCBSMonoRet></gMonoRet></gCBSMonoAdValorem>"
                + "<vTotIBSMonoItem>0.00</vTotIBSMonoItem><vTotCBSMonoItem>0.00</vTotCBSMonoItem>"
                + "</gIBSCBSMono></IBSCBS>";
    }

    /** Sem IBSCBS no item nao faz sentido mandar IBSCBSTot nem vNFTot. */
    private static String totaisIbsCbs() {
        if ("nenhum".equals(System.getProperty("grupo", "mono"))) {
            return "";
        }
        return "<IBSCBSTot><vBCIBSCBS>0.00</vBCIBSCBS>"
                + "<gIBS><gIBSUF><vDif>0.00</vDif><vDevTrib>0.00</vDevTrib><vIBSUF>0.00</vIBSUF></gIBSUF>"
                + "<gIBSMun><vDif>0.00</vDif><vDevTrib>0.00</vDevTrib><vIBSMun>0.00</vIBSMun></gIBSMun>"
                + "<vIBS>0.00</vIBS><vCredPres>0.00</vCredPres><vCredPresCondSus>0.00</vCredPresCondSus></gIBS>"
                + "<gCBS><vDif>0.00</vDif><vDevTrib>0.00</vDevTrib><vCBS>0.00</vCBS>"
                + "<vCredPres>0.00</vCredPres><vCredPresCondSus>0.00</vCredPresCondSus></gCBS></IBSCBSTot>"
                + "<vNFTot>1.00</vNFTot>";
    }
}
