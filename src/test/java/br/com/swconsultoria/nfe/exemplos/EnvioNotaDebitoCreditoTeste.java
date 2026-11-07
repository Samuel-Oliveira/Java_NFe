package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schemas.TEnderEmi;
import br.com.swconsultoria.nfe.schemas.TEndereco;
import br.com.swconsultoria.nfe.schemas.TEnviNFe;
import br.com.swconsultoria.nfe.schemas.TNFe;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Det.Imposto;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Total;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Total.ICMSTot;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Transp;
import br.com.swconsultoria.nfe.schemas.TRetEnviNFe;
import br.com.swconsultoria.nfe.schemas.TTribNFe;
import br.com.swconsultoria.nfe.schemas.TUf;
import br.com.swconsultoria.nfe.schemas.TUfEmi;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.IbsCbsUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Nota de Debito (finNFe=6) e Nota de Credito (finNFe=5) da NF-e modelo 55,
 * criadas pela NT 2025.002-RTC. Emite as duas em homologacao em Goias.
 *
 * <p>O sentido das palavras segue sempre o ponto de vista do EMITENTE: a nota de
 * debito registra um aumento no imposto devido pelo emitente; a nota de credito
 * registra uma reducao. As finalidades que ja existiam, 2-Complementar e
 * 3-Ajuste, sao casos particulares de nota de debito.
 *
 * <p><b>A particularidade que mais derruba nota: elas sao documentos de IBS/CBS
 * e nada mais.</b> Com finNFe 5 ou 6 e proibido informar ICMS, ISSQN, IPI, II,
 * PIS, PISST, COFINS, COFINSST, ICMSUFDest e impostoDevol - rejeicao
 * <b>1001 (NF-e com finalidade de debito ou credito somente para IBS/CBS)</b>.
 * Por isso o grupo imposto destes exemplos carrega so o IBSCBS. O schema
 * permite: a escolha ICMS/ISSQN e minOccurs=0 e o IBSCBS e irmao dela.
 *
 * <p>O caminho inverso tambem existe: para finNFe diferente de 5 e 6 o ICMS ou o
 * ISSQN continuam obrigatorios (rejeicao 1002). E as regras que exigiam os
 * grupos PIS (745) e COFINS (748) em toda NF-e 55 passaram a ter excecao para
 * finNFe 5 e 6 - sem essa excecao a nota seria impossivel de montar.
 *
 * <p>Excecoes a 1001, previstas na propria NT, que NAO sao usadas aqui:
 * tpNFCredito 03, 04 e 06 e tpNFDebito 07 aceitam os grupos antigos;
 * tpNFDebito 06-Pagamento antecipado aceita PIS/COFINS em 2026 e aceita IPI.
 *
 * <p>Outras regras gerais que ganharam excecao para finNFe 5 e 6, e que explicam
 * por que a nota passa mesmo parecendo incompleta para os olhos antigos: origem
 * da mercadoria (966), CST de monofasia de combustivel (960) e a exigencia de
 * ICMS ST por UF de destino.
 *
 * <p>Sobre o cClassTrib: a NT amarra cada tipo de nota a um codigo especifico
 * (rejeicoes 1200, 1201 e 1202). 800002 para tpNFDebito=01, 811001 para 02,
 * 811002 para 03, 800001 para 05 nos dois lados, 410030 para tpNFDebito=07,
 * 811003 para 08 e 810001 para tpNFCredito=02. Os tipos escolhidos nestes dois
 * exemplos - debito 04 e credito 01, Multa e juros - estao na coluna
 * "Nao limitar" da tabela, entao aceitam o 000001 de tributacao integral e
 * dispensam os grupos especiais gTransfCred e gEstornoCred.
 *
 * <p>As duas foram autorizadas em homologacao em Goias com este codigo, em
 * 07/11/2026: a nota de debito com protocolo 152260027993410 e a nota de credito
 * com protocolo 152260027993411, as duas com cStat 100.
 *
 * <p>Para rodar com outro emitente e outro certificado, sem editar o fonte:
 *
 * <pre>
 * java -Dcertificado=caminho.pfx -DsenhaCertificado=senha \
 *      -DcnpjEmit=... -DieEmit=... -DnomeEmit=... \
 *      -DnumeroNfe=1 -DchaveRef=chave44digitos \
 *      br.com.swconsultoria.nfe.exemplos.EnvioNotaDebitoCreditoTeste
 * </pre>
 *
 * @author Samuel Oliveira
 */
public class EnvioNotaDebitoCreditoTeste {

    /** Identidade do emitente. O certificado tem de ser desta empresa. */
    private static final String CNPJ_EMIT = System.getProperty("cnpjEmit", "10732644000128");
    private static final String IE_EMIT = System.getProperty("ieEmit", "104519304");
    private static final String NOME_EMIT = System.getProperty("nomeEmit", "POSTO PARK XIII LTDA");

    /**
     * Regime do emitente. A NT trata as notas de debito e credito como
     * instrumento do regime regular; o tpNFDebito=08 existe justamente para
     * documentar o desenquadramento do Simples.
     */
    private static final String CRT_EMIT = System.getProperty("crtEmit", "3");

    /** 000001 e tributacao integral; a tabela vem do mesmo ibscbs.json da SEFAZ. */
    private static final String C_CLASS_TRIB = System.getProperty("cClassTrib", "000001");

    /** Numero da primeira nota; a segunda sai com numero+1. */
    private static final int NUMERO_NFE = Integer.parseInt(System.getProperty("numeroNfe", "92800"));

    /** Valor do item que esta sendo debitado ou creditado. */
    private static final String V_PROD = "100.00";

    /** Preenchido por montaDet e consumido por preencheTotal. */
    private static IbsCbsUtil ibsCbsUtil;

    public static void main(String[] args) throws Exception {

        ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

        // So os 44 digitos: o refNFe e o chaveAcesso nao levam o prefixo "NFe"
        // que o ChaveUtil devolve para o atributo Id do infNFe.
        String chaveRef = System.getProperty("chaveRef", "").replaceFirst("^NFe", "");

        // Os dois lados referenciam o documento original, mas a SEFAZ os cobra de
        // forma diferente, e isso so aparece emitindo:
        //
        //   - o DFeReferenciado da nota de debito NAO e conferido contra a base.
        //     Uma chave montada na hora, com digito verificador correto, autoriza;
        //   - o NFref da nota de credito E conferido. Chave que nao existe cai em
        //     rejeicao 267 (Chave de Acesso referenciada inexistente).
        //
        // Por isso, sem -DchaveRef, o debito usa uma chave sintetica e o credito
        // usa a chave da nota de debito que acabou de ser autorizada - so para o
        // exemplo rodar ponta a ponta. Na pratica o documento referenciado pela
        // nota de credito e a nota original do fornecedor.
        System.out.println("=========== NOTA DE DEBITO (finNFe=6) ===========");
        String chaveDebito = chaveRef.isEmpty() ? chaveSinteticaDeReferencia(config) : chaveRef;
        System.out.println("### DFeReferenciado: " + chaveDebito);
        String autorizadaDebito = envia(config, montaNotaDebito(config, NUMERO_NFE, chaveDebito));

        String chaveCredito = chaveRef.isEmpty() ? autorizadaDebito : chaveRef;
        System.out.println();
        System.out.println("=========== NOTA DE CREDITO (finNFe=5) ==========");
        if (chaveCredito == null) {
            System.out.println("# Pulada: sem chave autorizada para o NFref. Informe -DchaveRef.");
            return;
        }
        System.out.println("### NFref: " + chaveCredito);
        envia(config, montaNotaCredito(config, NUMERO_NFE + 1, chaveCredito));
    }

    /**
     * NOTA DE DEBITO - finNFe=6, tpNFDebito=04-Multa e juros.
     *
     * <p>Cenario da NT: o fornecedor cobrou multa e juros do adquirente e emite a
     * nota de debito para que o adquirente do regime regular possa se creditar do
     * valor pago. Se o fornecedor nao emitir, o adquirente tem a alternativa de
     * emitir uma nota de credito do tipo 01 - que e o segundo exemplo aqui.
     *
     * <p>Particularidades:
     *
     * <ul>
     * <li><b>tpNF=1-Saida e obrigatorio.</b> Nota de debito com tpNF=0 cai em
     * rejeicao 1162 (Tipo de Operacao incompativel com NF-e de Debito). E o
     * espelho da nota de credito, que exige tpNF=0.</li>
     * <li><b>tpNFDebito e obrigatorio</b> quando finNFe=6 (rejeicao 1009), e
     * proibido quando finNFe e outra coisa (rejeicao 1139).</li>
     * <li><b>O referenciamento e por ITEM, no grupo DFeReferenciado</b>, nao no
     * cabecalho. Os tipos 03-Debitos de notas fiscais nao processadas na
     * apuracao, 04-Multa e juros e o credito 06-Retorno por recusa parcial
     * exigem o DFeReferenciado (rejeicao 1038). Informar NFref no cabecalho
     * junto com o DFeReferenciado no item derruba a nota com 1010
     * (referenciamento de documento a nivel de nota e a nivel de item).</li>
     * <li><b>O nItem do DFeReferenciado</b> aponta o item da nota original. Para
     * tpNFDebito=03 ele e proibido (1039); para os outros tipos e exigido
     * (1048), regra marcada como implementacao futura. Informar desde ja deixa a
     * nota pronta para quando entrar.</li>
     * <li>Todos os itens tem de apontar a MESMA chave de acesso (rejeicao 1130),
     * com excecao de tpNFDebito 03 e 07.</li>
     * </ul>
     */
    private static TEnviNFe montaNotaDebito(ConfiguracoesNfe config, int numeroNfe, String chaveReferenciada)
            throws Exception {

        InfNFe infNFe = esqueleto(config, numeroNfe, "NOTA DE DEBITO - MULTA E JUROS");

        infNFe.getIde().setTpNF("1");
        infNFe.getIde().setFinNFe("6");
        infNFe.getIde().setTpNFDebito("04");

        // 5949 - outra saida nao especificada. Coerente com tpNF=1-Saida.
        InfNFe.Det det = montaDet(infNFe, "5949");

        InfNFe.Det.DFeReferenciado referenciado = new InfNFe.Det.DFeReferenciado();
        referenciado.setChaveAcesso(chaveReferenciada);
        referenciado.setNItem("1");
        det.setDFeReferenciado(referenciado);

        return fecha(infNFe, det);
    }

    /**
     * NOTA DE CREDITO - finNFe=5, tpNFCredito=01-Multa e juros.
     *
     * <p>Cenario da NT: o adquirente pagou multa e juros, o fornecedor nao emitiu
     * a nota de debito, e o adquirente emite a nota de credito para lancar o
     * valor na apuracao assistida. O fornecedor concorda - ou nao - pelo evento
     * <b>211128 - Aceite de debito na apuracao por emissao de nota de credito</b>,
     * com a tag indAceitacao, tambem criado por esta NT.
     *
     * <p>Particularidades:
     *
     * <ul>
     * <li><b>tpNF=0-Entrada e obrigatorio</b> (rejeicao 1161). Quem emite a nota
     * de credito esta reduzindo o proprio imposto, entao o documento entra no seu
     * estabelecimento. Por isso o CFOP e de entrada.</li>
     * <li><b>tpNFCredito e obrigatorio</b> quando finNFe=5 (rejeicao 1164), e
     * proibido nas outras finalidades (rejeicao 1163).</li>
     * <li><b>O referenciamento e no CABECALHO, no NFref</b>, exatamente o
     * contrario da nota de debito deste arquivo. Os tipos 01-Multa e juros,
     * 03-Retorno e 04-Reducao de valores exigem o NFref (rejeicao 254) e aceitam
     * uma unica ocorrencia (rejeicao 255). Referenciamento por item e proibido em
     * finNFe=5 (rejeicao 1042), com uma unica excecao: tpNFCredito=06-Retorno por
     * recusa parcial, que exige o item.</li>
     * <li><b>So NF-e modelo 55 pode ser referenciada</b> (rejeicao 1003). A
     * excecao e tpNFCredito=03-Retorno, que aceita tambem NFC-e modelo 65 - faz
     * sentido: a recusa pode ser de uma venda a consumidor.</li>
     * <li>Para os tipos 03 e 04 a NF-e referenciada tem de ser do mesmo CNPJ
     * emitente (rejeicao 269) e da mesma UF (rejeicao 678). Para o tipo 01, usado
     * aqui, nao ha essa amarra - e coerente, porque a nota original e do
     * fornecedor, nao de quem emite o credito.</li>
     * <li>tpNFCredito=02-Apropriacao de credito presumido de IBS na ZFM e o unico
     * que PROIBE o NFref (rejeicao 1027) e que ainda nao pode ser usado: ano de
     * emissao menor que 2029 cai em rejeicao 1145.</li>
     * <li><b>A chave do NFref tem de existir de verdade</b>, e isso nao esta na
     * NT: chave sintetica, com digito verificador correto, foi recusada com
     * <b>267 (Chave de Acesso referenciada inexistente)</b> na homologacao de GO.
     * O DFeReferenciado da nota de debito, no mesmo lote, passou com a mesma
     * chave inexistente - a conferencia contra a base e so do NFref.</li>
     * </ul>
     */
    private static TEnviNFe montaNotaCredito(ConfiguracoesNfe config, int numeroNfe, String chaveReferenciada)
            throws Exception {

        InfNFe infNFe = esqueleto(config, numeroNfe, "NOTA DE CREDITO - MULTA E JUROS");

        infNFe.getIde().setTpNF("0");
        infNFe.getIde().setFinNFe("5");
        infNFe.getIde().setTpNFCredito("01");

        InfNFe.Ide.NFref nFref = new InfNFe.Ide.NFref();
        nFref.setRefNFe(chaveReferenciada);
        infNFe.getIde().getNFref().add(nFref);

        // 1949 - outra entrada nao especificada. Coerente com tpNF=0-Entrada e nao
        // e CFOP de devolucao, que seria recusado com 328 neste tipo de nota.
        return fecha(infNFe, montaDet(infNFe, "1949"));
    }

    /**
     * Cabecalho comum as duas notas.
     *
     * <p>indPres=0 (nao se aplica) e indFinal=0: nota de debito e de credito nao
     * documentam venda, nao tem comprador presente nem consumidor final. Com
     * indPres=0 tambem nao se informa indIntermed nem o grupo infIntermed.
     */
    private static InfNFe esqueleto(ConfiguracoesNfe config, int numeroNfe, String natOp) {

        String cnf = String.format("%08d", new Random().nextInt(99999999));
        String modelo = DocumentoEnum.NFE.getModelo();
        int serie = 1;
        String tipoEmissao = "1";
        LocalDateTime dataEmissao = LocalDateTime.now();

        ChaveUtil chaveUtil = new ChaveUtil(config.getEstado(), CNPJ_EMIT, modelo, serie, numeroNfe,
                tipoEmissao, cnf, dataEmissao);

        InfNFe infNFe = new InfNFe();
        infNFe.setId(chaveUtil.getChaveNF());
        infNFe.setVersao(ConstantesUtil.VERSAO.NFE);

        InfNFe.Ide ide = new InfNFe.Ide();
        ide.setCUF(config.getEstado().getCodigoUF());
        ide.setCNF(cnf);
        ide.setNatOp(natOp);
        ide.setMod(modelo);
        ide.setSerie(String.valueOf(serie));
        ide.setNNF(String.valueOf(numeroNfe));
        ide.setDhEmi(XmlNfeUtil.dataNfe(dataEmissao, null));
        ide.setIdDest("1");
        ide.setCMunFG("5219753");
        ide.setTpImp("1");
        ide.setTpEmis(tipoEmissao);
        ide.setCDV(chaveUtil.getDigitoVerificador());
        ide.setTpAmb(config.getAmbiente().getCodigo());
        ide.setIndFinal("0");
        ide.setIndPres("0");
        ide.setProcEmi("0");
        ide.setVerProc("NT2025.002-DEB-CRED");
        infNFe.setIde(ide);

        infNFe.setEmit(preencheEmitente(config));
        infNFe.setDest(preencheDestinatario());

        return infNFe;
    }

    /** Fecha a nota: item, totais, transporte, pagamento e o envelope do lote. */
    private static TEnviNFe fecha(InfNFe infNFe, InfNFe.Det det) {

        infNFe.getDet().add(det);
        infNFe.setTotal(preencheTotal());
        infNFe.setTransp(preencheTransporte());
        infNFe.setPag(preenchePag());

        TNFe nfe = new TNFe();
        nfe.setInfNFe(infNFe);

        TEnviNFe enviNFe = new TEnviNFe();
        enviNFe.setVersao(ConstantesUtil.VERSAO.NFE);
        enviNFe.setIdLote("1");
        enviNFe.setIndSinc("1");
        enviNFe.getNFe().add(nfe);

        return enviNFe;
    }

    /**
     * Item da nota. O grupo imposto leva SO o IBSCBS: e esta a diferenca visivel
     * entre uma nota de debito/credito e qualquer outra NF-e 55.
     */
    private static InfNFe.Det montaDet(InfNFe infNFe, String cfop) throws Exception {

        ibsCbsUtil = new IbsCbsUtil(XmlNfeUtil.leXml("src/test/resources/ibscbs.json"),
                DocumentoEnum.NFE, infNFe);

        InfNFe.Det det = new InfNFe.Det();
        det.setNItem("1");
        det.setProd(preencheProduto(cfop));

        // Grupo imposto sem ICMS, sem PIS e sem COFINS - ver o javadoc da classe.
        det.setImposto(new Imposto());

        TTribNFe ibsCbs = ibsCbsUtil.montaImpostosDet(C_CLASS_TRIB, det);
        det.getImposto().getContent()
                .add(new JAXBElement<>(new QName("IBSCBS"), TTribNFe.class, ibsCbs));

        return det;
    }

    private static Prod preencheProduto(String cfop) {
        Prod prod = new Prod();
        prod.setCProd("MULTAJUROS");
        prod.setCEAN("SEM GTIN");
        prod.setXProd("NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        prod.setNCM("84835090");
        prod.setCFOP(cfop);
        prod.setUCom("UN");
        prod.setQCom("1.0000");
        prod.setVUnCom("100.0000");
        prod.setVProd(V_PROD);
        prod.setCEANTrib("SEM GTIN");
        prod.setUTrib("UN");
        prod.setQTrib("1.0000");
        prod.setVUnTrib("100.0000");
        prod.setIndTot("1");
        return prod;
    }

    /**
     * Totais. O ICMSTot continua obrigatorio pelo schema, mas vai todo zerado:
     * nao ha ICMS, PIS nem COFINS para somar. O que da conteudo a nota e o
     * IBSCBSTot, e o vNFTot e o total do documento com os novos tributos.
     */
    private static Total preencheTotal() {
        Total total = new Total();

        ICMSTot icmstot = new ICMSTot();
        icmstot.setVBC("0.00");
        icmstot.setVICMS("0.00");
        icmstot.setVICMSDeson("0.00");
        icmstot.setVFCP("0.00");
        icmstot.setVBCST("0.00");
        icmstot.setVST("0.00");
        icmstot.setVFCPST("0.00");
        icmstot.setVFCPSTRet("0.00");
        icmstot.setVProd(V_PROD);
        icmstot.setVFrete("0.00");
        icmstot.setVSeg("0.00");
        icmstot.setVDesc("0.00");
        icmstot.setVII("0.00");
        icmstot.setVIPI("0.00");
        icmstot.setVIPIDevol("0.00");
        icmstot.setVPIS("0.00");
        icmstot.setVCOFINS("0.00");
        icmstot.setVOutro("0.00");
        icmstot.setVNF(V_PROD);
        total.setICMSTot(icmstot);

        total.setIBSCBSTot(ibsCbsUtil.preencheTotaisIbsCsb());
        BigDecimal vNfTot = ibsCbsUtil.calculaVnfTot(icmstot.getVNF());
        total.setVNFTot(ObjetoUtil.getValor2Casas(vNfTot));

        return total;
    }

    private static InfNFe.Emit preencheEmitente(ConfiguracoesNfe config) {
        InfNFe.Emit emit = new InfNFe.Emit();
        emit.setCNPJ(CNPJ_EMIT);
        emit.setXNome(NOME_EMIT);

        TEnderEmi enderEmit = new TEnderEmi();
        enderEmit.setXLgr("AV SANTO ANTONIO");
        enderEmit.setNro("0");
        enderEmit.setXBairro("PQ STO ANTONIO");
        enderEmit.setCMun("5219753");
        enderEmit.setXMun("SANTO ANTONIO DO DESCOBERTO");
        enderEmit.setUF(TUfEmi.valueOf(config.getEstado().toString()));
        enderEmit.setCEP("72900000");
        enderEmit.setCPais("1058");
        enderEmit.setXPais("Brasil");
        emit.setEnderEmit(enderEmit);

        emit.setIE(IE_EMIT);
        emit.setCRT(CRT_EMIT);

        return emit;
    }

    private static InfNFe.Dest preencheDestinatario() {
        InfNFe.Dest dest = new InfNFe.Dest();
        dest.setCNPJ("47966252000133");
        dest.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");

        TEndereco enderDest = new TEndereco();
        enderDest.setXLgr("Rua: Teste");
        enderDest.setNro("0");
        enderDest.setXBairro("TESTE");
        enderDest.setCMun("5202809");
        enderDest.setXMun("AVELINOPOLIS");
        enderDest.setUF(TUf.GO);
        enderDest.setCEP("74430130");
        enderDest.setCPais("1058");
        enderDest.setXPais("Brasil");
        dest.setEnderDest(enderDest);
        dest.setIE("109684036");
        dest.setIndIEDest("1");
        return dest;
    }

    private static Transp preencheTransporte() {
        Transp transp = new Transp();
        // 9 - sem ocorrencia de transporte: nao ha mercadoria circulando.
        transp.setModFrete("9");
        return transp;
    }

    /**
     * Pagamento. tPag=90-Sem pagamento com vPag zerado: a nota lanca imposto na
     * apuracao, nao cobra nada. O grupo em si continua obrigatorio pelo schema.
     */
    private static InfNFe.Pag preenchePag() {
        InfNFe.Pag pag = new InfNFe.Pag();
        InfNFe.Pag.DetPag detPag = new InfNFe.Pag.DetPag();
        detPag.setTPag("90");
        detPag.setVPag("0.00");
        pag.getDetPag().add(detPag);
        return pag;
    }

    /**
     * Monta uma chave de acesso valida do proprio emitente, so para o exemplo
     * rodar sem depender de uma nota real. Em producao a chave referenciada e a
     * de um documento autorizado.
     */
    private static String chaveSinteticaDeReferencia(ConfiguracoesNfe config) {
        LocalDateTime mesPassado = LocalDateTime.now().minusMonths(1);
        return new ChaveUtil(config.getEstado(), CNPJ_EMIT, DocumentoEnum.NFE.getModelo(), 1,
                NUMERO_NFE - 1, "1", "00000001", mesPassado)
                .getChaveNF().replaceFirst("^NFe", "");
    }

    /** @return a chave autorizada, ou null se a SEFAZ nao autorizou. */
    private static String envia(ConfiguracoesNfe config, TEnviNFe enviNFe) {
        try {
            System.out.println(XmlNfeUtil.objectToXml(enviNFe));
            enviNFe = Nfe.montaNfe(config, enviNFe, true);

            TRetEnviNFe retorno = Nfe.enviarNfe(config, enviNFe, DocumentoEnum.NFE);
            RetornoUtil.validaSincrono(retorno);

            System.out.println("# Status: " + retorno.getProtNFe().getInfProt().getCStat()
                    + " - " + retorno.getProtNFe().getInfProt().getXMotivo());
            System.out.println("# Protocolo: " + retorno.getProtNFe().getInfProt().getNProt());
            System.out.println("# Chave: " + retorno.getProtNFe().getInfProt().getChNFe());
            return retorno.getProtNFe().getInfProt().getChNFe();
        } catch (Exception e) {
            System.err.println("# Erro: " + e.getMessage());
            return null;
        }
    }
}
