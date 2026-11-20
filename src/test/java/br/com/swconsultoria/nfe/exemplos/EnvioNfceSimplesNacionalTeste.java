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
import java.util.Arrays;
import java.util.List;

/**
 * Mede o que a SEFAZ aceita de um emitente do SIMPLES NACIONAL (CRT=1), em
 * NFC-e ou NF-e, para responder perguntas de implementacao de um emissor:
 *
 * 1) CRT=1 com grupo de ICMS do regime normal (ICMS00) e recusado? E a
 *    combinacao que sai de um emissor que escolhe o grupo so pelo CST
 *    cadastrado no produto, sem olhar o regime do emitente.
 * 2) Quais grupos ICMSSN* sao aceitos, e em que operacao?
 * 3) O CST 61 (ICMS monofasico de combustivel cobrado anteriormente), que nao
 *    tem CSOSN equivalente, e aceito de emitente do Simples?
 * 4) O ambiente ja aceita os campos da NT 2026.005 (cAtivSN, tpRegimeIBSCBS,
 *    gTribSN no item e no total)? Eles existem no PL 010h, mas a producao foi
 *    adiada de 16/11 para 14/12/2026 pelo aviso do Comite Gestor de 12/11.
 *
 * Um cenario por execucao, escolhido por -Dcenario (cenario fora da lista e
 * recusado). Os demais parametros: -Dnumero e -Dserie (padrao serie 2); -Dmodelo
 * (65 = NFC-e, padrao; 55 = NF-e para destinatario nao contribuinte,
 * indIEDest=9); -Dcfop troca o CFOP do item; -DcBenef troca o do diesel;
 * -Dambiente (padrao HOMOLOGACAO) e -Duf (padrao GO). Os do emitente (-DcnpjEmit
 * ate -DcepEmit) estao no comentario dos campos, logo abaixo da classe.
 *
 *   normal        CRT=3 + ICMS00                          (controle da loja)
 *   normalativ    CRT=3 + ICMS00 + cAtivSN=01
 *   normal102     CRT=3 + ICMSSN102
 *   normal60      CRT=3 + ICMS60 zerado + CFOP 5405       (controle do ST da loja)
 *   normal60e     CRT=3 + ICMS60 zerado, item de etanol   (controle do etanol)
 *   normal61      CRT=3 + ICMS61, item de diesel          (controle do monofasico)
 *   sn00          CRT=1 + ICMS00
 *   sn102         CRT=1 + ICMSSN102
 *   sn102tot      CRT=1 + ICMSSN102, mas vBC e vICMS no ICMSTot
 *   sn101         CRT=1 + ICMSSN101 (-DpCredSN e -DvCredSN trocam o credito)
 *   sn202         CRT=1 + ICMSSN202 com ICMS-ST de 19,00 (-Dcfop=5403 da a 725)
 *   sn500         CRT=1 + ICMSSN500 com ST retido preenchido + CFOP 5405
 *   sn500zero     CRT=1 + ICMSSN500 com ST retido zerado + CFOP 5405
 *   sn60          CRT=1 + ICMS60 zerado + CFOP 5405
 *   sn60e         CRT=1 + ICMS60 zerado, item de etanol
 *   sn61          CRT=1 + ICMS61, item de diesel
 *   sn102ativ     sn102 + cAtivSN=01
 *   sn102regime   sn102ativ + tpRegimeIBSCBS=1 no emit
 *   sn102tribsn   sn102regime + gTribSN no item e no total
 *   icmsvazio     CRT=1 + elemento ICMS sem nenhum grupo dentro
 *
 * Resultado medido em GO, homologacao, 19/11/2026, com dois emitentes: um de
 * regime normal (R) e um optante do Simples (S). Nas linhas medidas com os dois
 * (R+S) o resultado foi identico, o que mostra que a SEFAZ decide por estas
 * regras pelo CONTEUDO do XML e nao pelo cadastro dela; as demais foram medidas
 * com um so, indicado na ultima coluna:
 *
 *   cenario                                    NFC-e (65)   NF-e (55)   emitente
 *   CRT=3 + ICMS00 (controle)                  100          -           R+S
 *   CRT=1 + ICMS00                             590          590         R+S
 *   CRT=1 + ICMSSN102                          100          100         R+S
 *   CRT=1 + ICMSSN500, ST retido preenchido    100          -           R+S
 *   CRT=1 + ICMSSN500, ST retido zerado        100          -           S
 *   CRT=1 + ICMSSN101                          383          600         R+S
 *   CRT=1 + ICMSSN202                          740          -           S
 *   CRT=1 + ICMSSN102 e vBC/vICMS no total     531          -           R+S
 *   CRT=3 + ICMSSN102                          591          -           R+S
 *   CRT=3 + ICMS60 zerado, loja e etanol       100          -           S
 *   CRT=1 + ICMS60 zerado, loja e etanol       590          -           S
 *   CRT=3 + ICMS61 (controle do combustivel)   100          -           S
 *   CRT=1 + ICMS61                             100          -           S
 *   cAtivSN / tpRegimeIBSCBS / gTribSN         100          100         R
 *   ICMS sem filho                             falha no schema local
 *
 * O 383 e o 600 do CSOSN 101 nao dependem do percentual de credito (medido com
 * 19,0000 e com 2,5600): o 101 nao cabe em venda a consumidor final nem a nao
 * contribuinte, periodo.
 *
 * O CST 61 e EXCECAO a regra 590: emitente do Simples vende combustivel
 * monofasico com o CST 61 do regime normal, porque nao ha CSOSN equivalente. O
 * CST 60 NAO e: com CRT=1 da 590 tanto no item de loja quanto no etanol, porque
 * o ICMS cobrado anteriormente por ST tem CSOSN proprio (500).
 *
 * O 740 ("Item com Repasse de ICMS retido por Substituto Tributario") do CSOSN
 * 202 sai igual com CFOP 5102 e 5405: a NFC-e nao aceita ICMS-ST cobrado na
 * propria operacao. Com o CFOP 5403 a nota para antes, na 725 (CFOP invalido
 * para NFC-e).
 *
 * O item de combustivel (cenarios *61) e o mesmo da NFC-e de posto do
 * EnvioNfceIbsCbsProducaoTeste, com o IBSCBS so com CST e cClassTrib, variante
 * que la autorizou em producao.
 *
 * Em HOMOLOGACAO o xProd do item 1 tem de ser o literal exigido, senao tudo
 * para na rejeicao 373 e nada do resto e avaliado; este exemplo ja o manda. Nao
 * ha o que cancelar: homologacao nao gera documento fiscal.
 *
 * Em PRODUCAO este exemplo AUTORIZA nota de verdade (R$ 100,00). Nao rode em
 * producao sem necessidade, e cancele com o CancelarTeste se rodar.
 *
 * Series: GO devolve 999 (erro nao catalogado) no lote inteiro para serie alta
 * (medido com 890). Use serie baixa e numero alto.
 */
public class EnvioNfceSimplesNacionalTeste {

    // Emitente. Os defaults sao os mesmos dos outros exemplos deste pacote. Para
    // medir com outro emitente, passe -DcnpjEmit, -DieEmit, -DnomeEmit e os de
    // endereco abaixo, junto com -Dcertificado e -DsenhaCertificado do
    // ConfiguracaoTeste, sem editar o fonte. O cMun vale tambem como cMunFG.
    private static final String CNPJ_EMIT = System.getProperty("cnpjEmit", "10732644000128");
    private static final String IE_EMIT = System.getProperty("ieEmit", "104519304");
    private static final String NOME_EMIT = System.getProperty("nomeEmit", "POSTO PARK XIII LTDA");
    private static final String C_MUN_EMIT = System.getProperty("cMunEmit", "5219753");
    private static final String X_MUN_EMIT = System.getProperty("xMunEmit", "SANTO ANTONIO DO DESCOBERTO");
    private static final String X_LGR_EMIT = System.getProperty("xLgrEmit", "AV SANTO ANTONIO");
    private static final String NRO_EMIT = System.getProperty("nroEmit", "0");
    private static final String X_BAIRRO_EMIT = System.getProperty("xBairroEmit", "PQ STO ANTONIO");
    private static final String CEP_EMIT = System.getProperty("cepEmit", "72900000");

    private static final String SERIE = System.getProperty("serie", "2");
    private static final String NUMERO = System.getProperty("numero", "999900");

    /** Valor redondo para os tributos do periodo de teste cairem exatos: 0,1% e 0,9%. */
    private static final String V_PROD = "100.00";

    private static final String CENARIO = System.getProperty("cenario", "normal");

    /** Os cenarios do javadoc da classe. Um erro de digitacao nao pode virar medicao de outro cenario. */
    private static final List<String> CENARIOS = Arrays.asList(
            "normal", "normalativ", "normal102", "normal60", "normal60e", "normal61",
            "sn00", "sn102", "sn102tot", "sn101", "sn202", "sn500", "sn500zero", "sn60", "sn60e", "sn61",
            "sn102ativ", "sn102regime", "sn102tribsn", "icmsvazio");

    /** 65 = NFC-e (padrao), 55 = NF-e. A regra do CRT e a mesma, mas vale medir as duas. */
    private static final boolean NFE = "55".equals(System.getProperty("modelo", "65"));

    public static void main(String[] args) {
        if (!CENARIOS.contains(CENARIO)) {
            throw new IllegalArgumentException("Cenario desconhecido: " + CENARIO + ". Use um de " + CENARIOS);
        }
        try {
            AmbienteEnum ambiente = AmbienteEnum.valueOf(System.getProperty("ambiente", "HOMOLOGACAO"));
            EstadosEnum estado = EstadosEnum.valueOf(System.getProperty("uf", "GO"));
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(estado, ambiente);
            DocumentoEnum documento = NFE ? DocumentoEnum.NFE : DocumentoEnum.NFCE;

            LocalDateTime agora = LocalDateTime.now();
            String cNF = "00000002";
            ChaveUtil chaveUtil = new ChaveUtil(estado, CNPJ_EMIT, documento.getModelo(),
                    Integer.parseInt(SERIE), Integer.parseInt(NUMERO), "1", cNF, agora);
            String id = chaveUtil.getChaveNF();

            String xml = montaXml(id, cNF, XmlNfeUtil.dataNfe(agora, null), ambiente.getCodigo(), estado);
            TEnviNFe enviNFe = XmlNfeUtil.xmlToObject(xml, TEnviNFe.class);

            System.out.println("# Cenario  : " + CENARIO + " (modelo " + documento.getModelo() + ")");
            System.out.println("# Ambiente : " + ambiente + " (tpAmb=" + ambiente.getCodigo() + ")");
            System.out.println("# Numero   : serie " + SERIE + " nNF " + NUMERO);
            System.out.println("# Chave    : " + id.substring(3));

            enviNFe = Nfe.montaNfe(config, enviNFe, true);
            System.out.println("# Schema local: OK");

            if (!NFE) {
                String urlQr = WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_QRCODE);
                String qrCode = NFCeUtil.getCodeQRCodeV3(id.substring(3), ambiente.getCodigo(), urlQr);

                TNFe.InfNFeSupl supl = new TNFe.InfNFeSupl();
                supl.setQrCode(qrCode);
                supl.setUrlChave(WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_CONSULTANFCE));
                enviNFe.getNFe().get(0).setInfNFeSupl(supl);
            }

            TRetEnviNFe retorno = Nfe.enviarNfe(config, enviNFe, documento);

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

    private static boolean cenarioSimples() {
        return !CENARIO.startsWith("normal");
    }

    /** Diesel S10 com ICMS monofasico (CST 61). */
    private static boolean diesel() {
        return CENARIO.endsWith("61");
    }

    /** Etanol aditivado com ICMS60 zerado, fora do monofasico do ICMS. */
    private static boolean etanol() {
        return CENARIO.endsWith("60e");
    }

    /** Item de pista: PIS/COFINS 04 e IBSCBS so com CST 620 e cClassTrib. */
    private static boolean combustivel() {
        return diesel() || etanol();
    }

    /** ICMS60 zerado, como o emissor monta para o ICMS cobrado anteriormente por ST. */
    private static boolean icms60() {
        return CENARIO.endsWith("60") || etanol();
    }

    /** ICMS-ST da propria operacao (o emitente e o substituto): so o CSOSN 202. */
    private static boolean stDaOperacao() {
        return "sn202".equals(CENARIO);
    }

    private static boolean temAtivSN() {
        return CENARIO.endsWith("ativ") || temRegime();
    }

    private static boolean temRegime() {
        return "sn102regime".equals(CENARIO) || temTribSN();
    }

    private static boolean temTribSN() {
        return "sn102tribsn".equals(CENARIO);
    }

    /** vNF = vProd + vST. Os demais componentes sao zero em todos os cenarios. */
    private static String vNF() {
        return stDaOperacao() ? "119.00" : V_PROD;
    }

    /** -Dcfop troca o CFOP do item, para separar rejeicao de CFOP de rejeicao de CSOSN. */
    private static String cfop() {
        String informado = System.getProperty("cfop");
        if (informado != null) {
            return informado;
        }
        if (combustivel()) {
            return "5656";
        }
        // ST retido anteriormente (contribuinte substituido). O sn202 fica no 5102: com o 5403,
        // que seria o do substituto, a NFC-e para antes, na 725 do CFOP.
        return CENARIO.startsWith("sn500") || icms60() ? "5405" : "5102";
    }

    private static String montaXml(String id, String cNF, String dhEmi, String tpAmb, EstadosEnum estado) {
        return "<enviNFe versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">"
                + "<idLote>1</idLote><indSinc>1</indSinc>"
                + "<NFe><infNFe versao=\"4.00\" Id=\"" + id + "\">"
                + "<ide><cUF>" + estado.getCodigoUF() + "</cUF><cNF>" + cNF + "</cNF>"
                + "<natOp>VENDA DE MERCADORIA</natOp>"
                + "<mod>" + (NFE ? "55" : "65") + "</mod><serie>" + SERIE + "</serie><nNF>" + NUMERO + "</nNF>"
                + "<dhEmi>" + dhEmi + "</dhEmi><tpNF>1</tpNF><idDest>1</idDest>"
                + "<cMunFG>" + C_MUN_EMIT + "</cMunFG><tpImp>" + (NFE ? "1" : "4") + "</tpImp><tpEmis>1</tpEmis>"
                + "<cDV>" + id.substring(id.length() - 1) + "</cDV><tpAmb>" + tpAmb + "</tpAmb>"
                + "<finNFe>1</finNFe><indFinal>1</indFinal><indPres>1</indPres>"
                + "<procEmi>0</procEmi><verProc>teste-sn</verProc></ide>"
                + "<emit><CNPJ>" + CNPJ_EMIT + "</CNPJ><xNome>" + NOME_EMIT + "</xNome>"
                + "<enderEmit><xLgr>" + X_LGR_EMIT + "</xLgr><nro>" + NRO_EMIT + "</nro>"
                + "<xBairro>" + X_BAIRRO_EMIT + "</xBairro>"
                + "<cMun>" + C_MUN_EMIT + "</cMun><xMun>" + X_MUN_EMIT + "</xMun><UF>" + estado + "</UF>"
                + "<CEP>" + CEP_EMIT + "</CEP><cPais>1058</cPais><xPais>Brasil</xPais></enderEmit>"
                + "<IE>" + IE_EMIT + "</IE>"
                + "<CRT>" + (cenarioSimples() ? "1" : "3") + "</CRT>"
                + (temRegime() ? "<tpRegimeIBSCBS>1</tpRegimeIBSCBS>" : "")
                + "</emit>"
                + destinatario()
                + "<det nItem=\"1\">" + (diesel() ? prodDiesel(estado) : etanol() ? prodEtanol(estado) : prodLoja())
                + "<imposto><vTotTrib>0.00</vTotTrib>"
                + "<ICMS>" + grupoIcms() + "</ICMS>"
                + pisCofins()
                + grupoIbsCbs() + "</imposto></det>"
                + "<total><ICMSTot>"
                + "<vBC>" + (tributadoNoIcms() ? V_PROD : "0.00") + "</vBC>"
                + "<vICMS>" + (tributadoNoIcms() ? "19.00" : "0.00") + "</vICMS>"
                + "<vICMSDeson>0.00</vICMSDeson>"
                + "<vFCP>0.00</vFCP>"
                + "<vBCST>" + (stDaOperacao() ? V_PROD : "0.00") + "</vBCST>"
                + "<vST>" + (stDaOperacao() ? "19.00" : "0.00") + "</vST>"
                + "<vFCPST>0.00</vFCPST><vFCPSTRet>0.00</vFCPSTRet>"
                + (diesel() ? "<qBCMonoRet>16.00</qBCMonoRet><vICMSMonoRet>18.72</vICMSMonoRet>" : "")
                + "<vProd>" + V_PROD + "</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc>"
                + "<vII>0.00</vII><vIPI>0.00</vIPI><vIPIDevol>0.00</vIPIDevol><vPIS>0.00</vPIS>"
                + "<vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>" + vNF() + "</vNF>"
                + "<vTotTrib>0.00</vTotTrib></ICMSTot>"
                + totaisIbsCbs() + "</total>"
                + "<transp><modFrete>9</modFrete></transp>"
                + "<pag><detPag><tPag>01</tPag><vPag>" + vNF() + "</vPag></detPag></pag>"
                + "<infRespTec><CNPJ>32330160000195</CNPJ><xContato>Samuel Oliveira</xContato>"
                + "<email>samuel@swconsultoria.com.br</email><fone>62993066546</fone></infRespTec>"
                + "</infNFe></NFe></enviNFe>";
    }

    /** Oleo lubrificante, 1 unidade de R$ 100,00. */
    private static String prodLoja() {
        return "<prod><cProd>1001</cProd><cEAN>SEM GTIN</cEAN>"
                + "<xProd>NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL</xProd>"
                + "<NCM>27101932</NCM>"
                + "<CFOP>" + cfop() + "</CFOP>"
                + "<uCom>UN</uCom><qCom>1.0000</qCom><vUnCom>" + V_PROD + "</vUnCom><vProd>" + V_PROD + "</vProd>"
                + "<cEANTrib>SEM GTIN</cEANTrib><uTrib>UN</uTrib><qTrib>1.0000</qTrib>"
                + "<vUnTrib>" + V_PROD + "</vUnTrib><indTot>1</indTot>"
                + (temAtivSN() ? "<cAtivSN>01</cAtivSN>" : "")
                + "</prod>";
    }

    /**
     * Etanol aditivado, 22,272 L a R$ 4,49 = R$ 100,00128, que vai como vProd
     * 100,00 dentro da tolerancia de arredondamento. Mesma estrutura de item
     * de NFC-e de posto ja aceita pela SEFAZ-GO: NCM, CEST e encerrante, sem pBio,
     * sem origComb e sem cBenef.
     */
    private static String prodEtanol(EstadosEnum estado) {
        return "<prod><cProd>810101002</cProd><cEAN>SEM GTIN</cEAN>"
                + "<xProd>NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL</xProd>"
                + "<NCM>22071090</NCM><CEST>0600101</CEST>"
                + "<CFOP>" + cfop() + "</CFOP>"
                + "<uCom>LT</uCom><qCom>22.2720</qCom><vUnCom>4.49</vUnCom><vProd>" + V_PROD + "</vProd>"
                + "<cEANTrib>SEM GTIN</cEANTrib><uTrib>LT</uTrib><qTrib>22.2720</qTrib>"
                + "<vUnTrib>4.4900</vUnTrib><indTot>1</indTot>"
                + "<comb><cProdANP>810101002</cProdANP><descANP>ETANOL ADITIVADO</descANP>"
                + "<UFCons>" + estado + "</UFCons>"
                + "<encerrante><nBico>1</nBico><nTanque>1</nTanque>"
                + "<vEncIni>1000.000</vEncIni><vEncFin>1022.272</vEncFin></encerrante></comb></prod>";
    }

    /** Diesel S10, 16 L a R$ 6,25 = R$ 100,00; ICMS monofasico retido de 1,17/L = 18,72. */
    private static String prodDiesel(EstadosEnum estado) {
        return "<prod><cProd>820101034</cProd><cEAN>SEM GTIN</cEAN>"
                + "<xProd>NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL</xProd>"
                + "<NCM>27101921</NCM><CEST>0600600</CEST>"
                + "<cBenef>" + System.getProperty("cBenef", "GO890004") + "</cBenef>"
                + "<CFOP>" + cfop() + "</CFOP>"
                + "<uCom>LT</uCom><qCom>16.0000</qCom><vUnCom>6.25</vUnCom><vProd>" + V_PROD + "</vProd>"
                + "<cEANTrib>SEM GTIN</cEANTrib><uTrib>L</uTrib><qTrib>16.0000</qTrib>"
                + "<vUnTrib>6.25</vUnTrib><indTot>1</indTot>"
                + "<comb><cProdANP>820101034</cProdANP><descANP>DIESEL S10 COMUM</descANP>"
                + "<UFCons>" + estado + "</UFCons>"
                + "<encerrante><nBico>1</nBico><nTanque>1</nTanque>"
                + "<vEncIni>1000.000</vEncIni><vEncFin>1016.000</vEncFin></encerrante>"
                + "<pBio>27.0000</pBio>"
                + "<origComb><indImport>0</indImport><cUFOrig>" + estado.getCodigoUF() + "</cUFOrig>"
                + "<pOrig>100</pOrig></origComb></comb></prod>";
    }

    /**
     * A NF-e exige destinatario; a NFC-e de consumidor nao. Em homologacao o
     * xNome do destinatario tem de ser o literal, senao a nota para na 554/594.
     */
    private static String destinatario() {
        if (!NFE) {
            return "";
        }
        return "<dest><CNPJ>32330160000195</CNPJ>"
                + "<xNome>NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL</xNome>"
                + "<enderDest><xLgr>RUA 1</xLgr><nro>1</nro><xBairro>CENTRO</xBairro>"
                + "<cMun>5208707</cMun><xMun>GOIANIA</xMun><UF>GO</UF><CEP>74000000</CEP>"
                + "<cPais>1058</cPais><xPais>Brasil</xPais></enderDest>"
                + "<indIEDest>9</indIEDest></dest>";
    }

    /**
     * Somente o ICMS00 debita ICMS proprio no total. O sn102tot tambem manda
     * vBC e vICMS, mas com o grupo do Simples no item: e o total de um emissor
     * que soma o ICMS cadastrado no item sem olhar o grupo que saiu no XML.
     */
    private static boolean tributadoNoIcms() {
        return "normal".equals(CENARIO) || "normalativ".equals(CENARIO) || "sn00".equals(CENARIO)
                || "sn102tot".equals(CENARIO);
    }

    private static String grupoIcms() {
        switch (CENARIO) {
            case "sn500":
                return "<ICMSSN500><orig>0</orig><CSOSN>500</CSOSN>"
                        + "<vBCSTRet>" + V_PROD + "</vBCSTRet><pST>19.0000</pST>"
                        + "<vICMSSTRet>19.00</vICMSSTRet></ICMSSN500>";
            case "sn500zero":
                // ST retido zerado: o que sai de um emissor que zera os valores de ICMS do
                // item quando a aliquota cadastrada e de substituicao tributaria.
                return "<ICMSSN500><orig>0</orig><CSOSN>500</CSOSN>"
                        + "<vBCSTRet>0.00</vBCSTRet><pST>0.0000</pST>"
                        + "<vICMSSTRet>0.00</vICMSSTRet></ICMSSN500>";
            case "sn202":
                return "<ICMSSN202><orig>0</orig><CSOSN>202</CSOSN><modBCST>5</modBCST>"
                        + "<vBCST>" + V_PROD + "</vBCST><pICMSST>19.0000</pICMSST>"
                        + "<vICMSST>19.00</vICMSST></ICMSSN202>";
            case "sn101":
                // pCredSN padrao com uma aliquota ESTADUAL (19%), no lugar do percentual de
                // credito do Simples, para medir se a SEFAZ critica o valor. -DpCredSN=2.5600
                // -DvCredSN=2.56 troca por um percentual plausivel de credito do Simples, para
                // separar "percentual errado" de "CSOSN errado".
                return "<ICMSSN101><orig>0</orig><CSOSN>101</CSOSN>"
                        + "<pCredSN>" + System.getProperty("pCredSN", "19.0000") + "</pCredSN>"
                        + "<vCredICMSSN>" + System.getProperty("vCredSN", "19.00") + "</vCredICMSSN></ICMSSN101>";
            case "sn60":
            case "normal60":
            case "sn60e":
            case "normal60e":
                // Valores zerados, que e o que o emissor manda no ICMS cobrado anteriormente por ST.
                return "<ICMS60><orig>0</orig><CST>60</CST><vBCSTRet>0.00</vBCSTRet><pST>0.00</pST>"
                        + "<vICMSSTRet>0.00</vICMSSTRet></ICMS60>";
            case "sn61":
            case "normal61":
                return "<ICMS61><orig>0</orig><CST>61</CST><qBCMonoRet>16.0000</qBCMonoRet>"
                        + "<adRemICMSRet>1.1700</adRemICMSRet><vICMSMonoRet>18.72</vICMSMonoRet></ICMS61>";
            case "normal":
            case "normalativ":
            case "sn00":
                return "<ICMS00><orig>0</orig><CST>00</CST><modBC>3</modBC>"
                        + "<vBC>" + V_PROD + "</vBC><pICMS>19.0000</pICMS><vICMS>19.00</vICMS></ICMS00>";
            case "icmsvazio":
                // Um emissor que escolhe o grupo pelo substring(1) do codigo cadastrado: com um
                // CSOSN de 3 digitos (101/102/201/202) o resto ("01"/"02") nao casa com nenhum
                // CST e o ICMS sai sem filho.
                return "";
            default:
                // sn102, sn102tot, sn102ativ, sn102regime, sn102tribsn e normal102
                return "<ICMSSN102><orig>0</orig><CSOSN>102</CSOSN></ICMSSN102>";
        }
    }

    /** Combustivel monofasico: PIS/COFINS 04. Loja do Simples: 49 com tudo zero. */
    private static String pisCofins() {
        if (combustivel()) {
            return "<PIS><PISNT><CST>04</CST></PISNT></PIS><COFINS><COFINSNT><CST>04</CST></COFINSNT></COFINS>";
        }
        return "<PIS><PISOutr><CST>49</CST><vBC>0.00</vBC><pPIS>0.00</pPIS><vPIS>0.00</vPIS></PISOutr></PIS>"
                + "<COFINS><COFINSOutr><CST>49</CST><vBC>0.00</vBC><pCOFINS>0.00</pCOFINS>"
                + "<vCOFINS>0.00</vCOFINS></COFINSOutr></COFINS>";
    }

    /**
     * Loja: IBS/CBS igual em todos os cenarios (CST 000, tributacao integral, ad
     * valorem de 2026), para que a unica variavel seja o ICMS e os campos novos
     * do Simples. Em 100,00: IBS UF 0,1% = 0,10 e CBS 0,9% = 0,90.
     *
     * Combustivel: so CST 620 e cClassTrib, sem grupo de calculo, a variante que
     * autorizou em producao no EnvioNfceIbsCbsProducaoTeste.
     */
    private static String grupoIbsCbs() {
        if (combustivel()) {
            return "<IBSCBS><CST>620</CST><cClassTrib>620006</cClassTrib></IBSCBS>";
        }
        return "<IBSCBS><CST>000</CST><cClassTrib>000001</cClassTrib>"
                + "<gIBSCBS><vBC>" + V_PROD + "</vBC>"
                + "<gIBSUF><pIBSUF>0.1000</pIBSUF><vIBSUF>0.10</vIBSUF></gIBSUF>"
                + "<gIBSMun><pIBSMun>0.0000</pIBSMun><vIBSMun>0.00</vIBSMun></gIBSMun>"
                + "<vIBS>0.10</vIBS>"
                + "<gCBS><pCBS>0.9000</pCBS><vCBS>0.90</vCBS></gCBS>"
                + "</gIBSCBS>"
                + (temTribSN() ? "<gTribSN><gIBSSN><pIBSSN>0.1000</pIBSSN><vIBSSN>0.10</vIBSSN></gIBSSN>"
                        + "<gCBSSN><pCBSSN>0.9000</pCBSSN><vCBSSN>0.90</vCBSSN></gCBSSN></gTribSN>" : "")
                + "</IBSCBS>";
    }

    private static String totaisIbsCbs() {
        String base = combustivel() ? "0.00" : V_PROD;
        String ibs = combustivel() ? "0.00" : "0.10";
        String cbs = combustivel() ? "0.00" : "0.90";
        return "<IBSCBSTot><vBCIBSCBS>" + base + "</vBCIBSCBS>"
                + "<gIBS><gIBSUF><vDif>0.00</vDif><vDevTrib>0.00</vDevTrib><vIBSUF>" + ibs + "</vIBSUF></gIBSUF>"
                + "<gIBSMun><vDif>0.00</vDif><vDevTrib>0.00</vDevTrib><vIBSMun>0.00</vIBSMun></gIBSMun>"
                + "<vIBS>" + ibs + "</vIBS><vCredPres>0.00</vCredPres><vCredPresCondSus>0.00</vCredPresCondSus></gIBS>"
                + "<gCBS><vDif>0.00</vDif><vDevTrib>0.00</vDevTrib><vCBS>" + cbs + "</vCBS>"
                + "<vCredPres>0.00</vCredPres><vCredPresCondSus>0.00</vCredPresCondSus></gCBS>"
                + (temTribSN() ? "<gTribSN><vIBSSN>0.10</vIBSSN><vCBSSN>0.90</vCBSSN></gTribSN>" : "")
                + "</IBSCBSTot>"
                + "<vNFTot>" + vNF() + "</vNFTot>";
    }
}
