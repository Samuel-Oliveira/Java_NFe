/**
 *
 */
package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.*;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas.*;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.*;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Det.Imposto;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schemas.TNFe.InfNFe.Total.ICMSTot;
import br.com.swconsultoria.nfe.util.*;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Samuel Oliveira
 */
public class EnvioNfceTeste {

    public static void main(String[] args) {

        try {
            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            LocalDateTime start = LocalDateTime.now();

            // Monta e Assina o XML
            TEnviNFe enviNFe = montaEnviNFe(config);

            //Monta QRCode
            String qrCode = preencheQRCode(enviNFe, config);

            TNFe.InfNFeSupl infNFeSupl = new TNFe.InfNFeSupl();
            infNFeSupl.setQrCode(qrCode);
            infNFeSupl.setUrlChave(WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_CONSULTANFCE));
            enviNFe.getNFe().get(0).setInfNFeSupl(infNFeSupl);

            // Envia a Nfe para a Sefaz
            TRetEnviNFe retorno = Nfe.enviarNfe(config, enviNFe, DocumentoEnum.NFCE);

            //Valida se o Retorno é Assincrono
            if (RetornoUtil.isRetornoAssincrono(retorno)) {
                //Pega o Recibo
                String recibo = retorno.getInfRec().getNRec();
                int tentativa = 0;
                TRetConsReciNFe retornoNfe = null;

                //Define Numero de tentativas que irá tentar a Consulta
                while (tentativa < 15) {
                    retornoNfe = Nfe.consultaRecibo(config, recibo, DocumentoEnum.NFE);
                    if (retornoNfe.getCStat().equals(StatusEnum.LOTE_EM_PROCESSAMENTO.getCodigo())) {
                        System.out.println("INFO: Lote Em Processamento, vai tentar novamente apos 1 Segundo.");
                        Thread.sleep(1000);
                        tentativa++;
                    } else {
                        break;
                    }
                }

                RetornoUtil.validaAssincrono(retornoNfe);
                System.out.println();
                System.out.println("# Status: " + retornoNfe.getProtNFe().get(0).getInfProt().getCStat() + " - " + retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
                System.out.println("# Protocolo: " + retornoNfe.getProtNFe().get(0).getInfProt().getNProt());
                System.out.println("# XML Final: " + XmlNfeUtil.criaNfeProc(enviNFe, retornoNfe.getProtNFe().get(0)));

            } else {
                //Se for else o Retorno é Sincrono

                //Valida Retorno Sincrono
                RetornoUtil.validaSincrono(retorno);
                System.out.println();
                System.out.println("# Status: " + retorno.getProtNFe().getInfProt().getCStat() + " - " + retorno.getProtNFe().getInfProt().getXMotivo());
                System.out.println("# Protocolo: " + retorno.getProtNFe().getInfProt().getNProt());
                System.out.println("# Xml Final :" + XmlNfeUtil.criaNfeProc(enviNFe, retorno.getProtNFe()));
            }

            System.out.println(start.until(LocalDateTime.now(), ChronoUnit.MILLIS));

        } catch (Exception e) {
            System.err.println();
            System.err.println("# Erro: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Preenche o IDE
     *
     * @param config
     * @param cnf
     * @param numeroNFCe
     * @param tipoEmissao
     * @param cDv
     * @param dataEmissao
     * @return
     */
    private static Ide preencheIde(ConfiguracoesNfe config, String cnf, int numeroNFCe, String tipoEmissao, String modelo, int serie, String cDv, LocalDateTime dataEmissao) {
        Ide ide = new Ide();
        ide.setCUF(config.getEstado().getCodigoUF());
        ide.setCNF(cnf);
        ide.setNatOp("VENDA");
        ide.setMod(modelo);
        ide.setSerie(String.valueOf(serie));

        ide.setNNF(String.valueOf(numeroNFCe));
        ide.setDhEmi(XmlNfeUtil.dataNfe(dataEmissao, null));
        ide.setTpNF("1");
        ide.setIdDest("1");
        ide.setCMunFG("5219753");
        ide.setTpImp("4");
        ide.setTpEmis(tipoEmissao);
        ide.setCDV(cDv);
        ide.setTpAmb(config.getAmbiente().getCodigo());
        ide.setFinNFe("1");
        ide.setIndFinal("1");
        ide.setIndPres("1");
        ide.setProcEmi("0");
        ide.setVerProc("1.0");

        return ide;
    }

    /**
     * Preenche o Emitente da NFCe
     *
     * @param config
     * @param cnpj
     * @return
     */
    private static Emit preencheEmitente(ConfiguracoesNfe config, String cnpj) {
        Emit emit = new Emit();
        emit.setCNPJ(cnpj);
        emit.setXNome("POSTO PARK XIII LTDA");

        TEnderEmi enderEmit = new TEnderEmi();
        enderEmit.setXLgr("AV SANTO ANTONIO");
        enderEmit.setNro("0");
        enderEmit.setXCpl("QD 17 LT 01-02-03");
        enderEmit.setXBairro("PQ STO ANTONIO");
        enderEmit.setCMun("5219753");
        enderEmit.setXMun("SANTO ANTONIO DO DESCOBERTO");
        enderEmit.setUF(TUfEmi.valueOf(config.getEstado().toString()));
        enderEmit.setCEP("72900000");
        enderEmit.setCPais("1058");
        enderEmit.setXPais("Brasil");
        enderEmit.setFone("6233215175");
        emit.setEnderEmit(enderEmit);

        emit.setIE("104519304");
        emit.setCRT("3");

        return emit;
    }

    /**
     * Preenche o Destinatario da NFCe
     *
     * @return
     */
    private static Dest preencheDestinatario() {
        Dest dest = new Dest();
        dest.setCNPJ("60628468000157");
        dest.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");

//        TEndereco enderDest = new TEndereco();
//        enderDest.setXLgr("Rua: Teste");
//        enderDest.setNro("0");
//        enderDest.setXBairro("TESTE");
//        enderDest.setCMun("4109708");
//        enderDest.setXMun("IBAITI");
//        enderDest.setUF(TUf.valueOf("PR"));
//        enderDest.setCEP("84900000");
//        enderDest.setCPais("1058");
//        enderDest.setXPais("Brasil");
//        enderDest.setFone("4845454545");
//        dest.setEnderDest(enderDest);
//        dest.setEmail("teste@test");
        dest.setIndIEDest("9");
        return dest;
    }

    /**
     * Preenche Det NFCe
     */
    /**
     * Combustiveis vendidos na NFCe de teste. Os dados de produto (ANP, NCM, CEST,
     * adRemICMSRet, pBio) vieram de NFCe reais autorizadas de um posto em GO.
     *
     * adRemTotal e a aliquota ad rem IBS+CBS da tabela de aliquotas especificas de
     * combustiveis (R$ por unidade de medida). A tabela publica o valor TOTAL; a
     * reparticao entre IBS e CBS usa {@link #PERCENTUAL_IBS}.
     */
    private enum Combustivel {
        //                   ANP           descANP           NCM         CEST       CFOP    vUnCom  qCom      adRemTotal  icmsAdRem  pBio      nBico nTanque vEncIni
        GASOLINA("320102001", "GASOLINA COMUM", "27101259", "0600200", "5656", "6.09", "20.0000", "0.7925", "1.5700", "12.0000", "21", "2", "109813.421", "GO890004"),
        ETANOL("810101001", "ETANOL COMUM", "22071090", "0600101", "5656", "4.98", "30.0000", "0.1922", null, null, "17", "1", "48195.000", null),
        DIESEL("820101012", "OLEO DIESEL B S500-COMUM", "27101921", "0600600", "5656", "7.19", "25.0000", "0.3515", "1.1700", "27.0000", "4", "4", "28474.440", "GO890004");

        final String anp;
        final String descAnp;
        final String ncm;
        final String cest;
        final String cfop;
        final BigDecimal vUnCom;
        final BigDecimal qCom;
        final BigDecimal adRemTotal;
        /** Aliquota ad rem do ICMS retido (ICMS61). Nulo quando o produto usa ICMS60. */
        final String icmsAdRemRet;
        /** Percentual de biocombustivel na mistura. Nulo quando nao se aplica. */
        final String pBio;
        /** Bico, tanque e leitura inicial do encerrante, obrigatorios na NFCe de posto. */
        final String nBico;
        final String nTanque;
        final BigDecimal vEncIni;
        /** Codigo de beneficio fiscal, exigido pela UF quando o CST do ICMS tem beneficio. */
        final String cBenef;

        Combustivel(String anp, String descAnp, String ncm, String cest, String cfop,
                    String vUnCom, String qCom, String adRemTotal, String icmsAdRemRet, String pBio,
                    String nBico, String nTanque, String vEncIni, String cBenef) {
            this.anp = anp;
            this.descAnp = descAnp;
            this.ncm = ncm;
            this.cest = cest;
            this.cfop = cfop;
            this.vUnCom = new BigDecimal(vUnCom);
            this.qCom = new BigDecimal(qCom);
            this.adRemTotal = new BigDecimal(adRemTotal);
            this.icmsAdRemRet = icmsAdRemRet;
            this.pBio = pBio;
            this.nBico = nBico;
            this.nTanque = nTanque;
            this.vEncIni = new BigDecimal(vEncIni);
            this.cBenef = cBenef;
        }

        /** A leitura final do encerrante avanca exatamente a quantidade vendida. */
        BigDecimal vEncFin() {
            return vEncIni.add(qCom).setScale(3, RoundingMode.HALF_UP);
        }

        BigDecimal vProd() {
            return vUnCom.multiply(qCom).setScale(2, RoundingMode.HALF_UP);
        }

        /** Parcela IBS da aliquota ad rem. */
        BigDecimal adRemIBS() {
            return adRemTotal.multiply(PERCENTUAL_IBS).setScale(4, RoundingMode.HALF_UP);
        }

        /** Parcela CBS da aliquota ad rem. */
        BigDecimal adRemCBS() {
            return adRemTotal.subtract(adRemIBS()).setScale(4, RoundingMode.HALF_UP);
        }

        /** Valor do ICMS monofasico retido anteriormente (ICMS61). */
        BigDecimal vIcmsMonoRet() {
            return qCom.multiply(new BigDecimal(icmsAdRemRet)).setScale(2, RoundingMode.HALF_UP);
        }
    }

    /**
     * Fracao da aliquota ad rem que cabe ao IBS; o restante vai para a CBS.
     * 0,10 segue a proporcao das aliquotas de referencia do periodo de teste
     * (IBS 0,1% e CBS 0,9%), que sao tambem os defaults do IbsCbsUtil.
     * Ajuste conforme a reparticao oficial antes de uso fiscal.
     */
    private static final BigDecimal PERCENTUAL_IBS = new BigDecimal("0.10");

    /**
     * cClassTrib da operacao. O padrao 620006 e o do posto revendedor (monofasia
     * retida anteriormente). Pode ser trocado por -DcClassTrib=620001 para testar
     * outras classificacoes contra a SEFAZ.
     */
    private static final String C_CLASS_TRIB = System.getProperty("cClassTrib", "620006");

    /**
     * Ano de emissao, que define a modalidade da monofasia pela NT 2025.002-RTC:
     * 2026 IBS e CBS ad valorem; 2027-2028 IBS ad valorem e CBS ad rem; 2029 ambos
     * ad rem. O -DanoEmissao=2027 move tambem o dhEmi, para que os dois nunca
     * divirjam; serve para inspecionar o XML gerado, nao para enviar a SEFAZ, que
     * rejeitaria pela data de emissao futura.
     */
    private static final int ANO_EMISSAO =
            Integer.parseInt(System.getProperty("anoEmissao", String.valueOf(java.time.LocalDate.now().getYear())));

    private static final String CST_IBSCBS = "620";

    /** Preenchido por {@link #preencheDet(String)} e consumido por {@link #preencheTotal()}. */
    private static IbsCbsUtil ibsCbsUtil;


    /**
     * Monta e assina a NFCe de combustiveis (gasolina, etanol e diesel) com os grupos
     * monofasicos de IBS/CBS. Separado do envio para permitir validar o XML contra o
     * XSD sem precisar de certificado de producao nem de rede.
     */
    static TEnviNFe montaEnviNFe(ConfiguracoesNfe config) throws Exception {
        return montaEnviNFe(config, "10732644000128");
    }

    static TEnviNFe montaEnviNFe(ConfiguracoesNfe config, String cnpj) throws Exception {
        return montaEnviNFe(config, cnpj, LocalDateTime.now().withYear(ANO_EMISSAO));
    }

    /**
     * O CNPJ precisa bater com o do certificado usado na assinatura.
     *
     * A modalidade da monofasia e derivada do ano desta data, e nao de um parametro
     * separado: a SEFAZ valida pelo dhEmi, entao os dois nao podem divergir.
     */
    static TEnviNFe montaEnviNFe(ConfiguracoesNfe config, String cnpj, LocalDateTime dataEmissao) throws Exception {
        //Informe o Numero da NFCe
        int numeroNFCe = Integer.parseInt(System.getProperty("numeroNFCe", "9511"));
        //Informe o cnf da NFCe com 8 digitos
        String cnf = String.format("%08d", new Random().nextInt(99999999));
        //Informe o modelo da NFCe
        String modelo = DocumentoEnum.NFCE.getModelo();
        //Informe a Serie da NFCe
        int serie = 1;
        //Informe o tipo de Emissao da NFCe
        String tipoEmissao = "1";

        // MontaChave a NFCe
        ChaveUtil chaveUtil = new ChaveUtil(config.getEstado(), cnpj, modelo, serie, numeroNFCe, tipoEmissao, cnf, dataEmissao);
        String chave = chaveUtil.getChaveNF();
        String cdv = chaveUtil.getDigitoVerificador();

        InfNFe infNFe = new InfNFe();
        infNFe.setId(chave);
        infNFe.setVersao(ConstantesUtil.VERSAO.NFE);

        //Preenche IDE
        infNFe.setIde(preencheIde(config, cnf, numeroNFCe, tipoEmissao, modelo, serie, cdv, dataEmissao));

        //Preenche Emitente
        infNFe.setEmit(preencheEmitente(config, cnpj));

        //Preenche o Destinatario
        infNFe.setDest(preencheDestinatario());

        //Preenche os dados do Produto da NFCe e adiciona a Lista
        // o dhEmi ja montado no ide e a fonte do ano: um so lugar, sem divergir
        infNFe.getDet().addAll(preencheDet(infNFe.getIde().getDhEmi()));

        //Preenche totais da NFCe
        infNFe.setTotal(preencheTotal());

        //Preenche os dados de Transporte
        infNFe.setTransp(preencheTransporte());

        // Preenche dados Pagamento
        infNFe.setPag(preenchePag());

        TNFe nfe = new TNFe();
        nfe.setInfNFe(infNFe);

        // Monta EnviNfe
        TEnviNFe enviNFe = new TEnviNFe();
        enviNFe.setVersao(ConstantesUtil.VERSAO.NFE);
        enviNFe.setIdLote("1");
        enviNFe.setIndSinc("1");
        enviNFe.getNFe().add(nfe);

        return Nfe.montaNfe(config, enviNFe, true);
    }

    private static List<Det> preencheDet(String dhEmi) throws NfeException, IOException {

        ibsCbsUtil = new IbsCbsUtil(XmlNfeUtil.leXml("src/test/resources/ibscbs.json"),
                DocumentoEnum.NFCE, dhEmi);

        List<Det> itens = new ArrayList<>();
        int nItem = 1;

        for (Combustivel c : Combustivel.values()) {
            Det det = new Det();
            det.setNItem(String.valueOf(nItem++));
            det.setProd(preencheProduto(c));
            det.setImposto(preencheImposto(c));

            // Na monofasia retida anteriormente a SEFAZ calcula o valor por conta
            // propria (regra UB93a-10) e rejeita com cStat 1240 se o informado divergir.
            // Como as tabelas de referencia dessa formula ainda nao foram publicadas
            // ("Implementacao Futura" na NT), o grupo vai zerado no ad valorem.
            ibsCbsUtil.setPAliqMonoUF(BigDecimal.ZERO);
            ibsCbsUtil.setPAliqMonoMun(BigDecimal.ZERO);
            ibsCbsUtil.setPAliqMonoCBS(BigDecimal.ZERO);
            // Ad rem usa a aliquota especifica do combustivel, repartida entre IBS e CBS
            ibsCbsUtil.setAdRemIBS(c.adRemIBS());
            ibsCbsUtil.setAdRemCBS(c.adRemCBS());

            TTribNFe ibsCbs = ibsCbsUtil.montaImpostosDet(C_CLASS_TRIB, det);
            det.getImposto().getContent()
                    .add(new JAXBElement<>(new QName("IBSCBS"), TTribNFe.class, ibsCbs));

            itens.add(det);
        }

        return itens;
    }

    /**
     * Preenche dados do Produto
     *
     * @return
     */
    private static Prod preencheProduto(Combustivel c) {
        Prod prod = new Prod();
        prod.setCProd(c.anp);
        prod.setCEAN("SEM GTIN");
        prod.setXProd("NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        prod.setNCM(c.ncm);
        prod.setCEST(c.cest);
        if (c.cBenef != null) {
            prod.setCBenef(c.cBenef);
        }
        prod.setCFOP(c.cfop);
        prod.setUCom("LT");
        prod.setQCom(ObjetoUtil.getValor4Casas(c.qCom));
        prod.setVUnCom(ObjetoUtil.getValor4Casas(c.vUnCom));
        prod.setVProd(ObjetoUtil.getValor2Casas(c.vProd()));
        prod.setCEANTrib("SEM GTIN");
        prod.setUTrib("L");
        prod.setQTrib(ObjetoUtil.getValor4Casas(c.qCom));
        prod.setVUnTrib(ObjetoUtil.getValor4Casas(c.vUnCom));
        prod.setIndTot("1");

        Prod.Comb comb = new Prod.Comb();
        comb.setCProdANP(c.anp);
        comb.setDescANP(c.descAnp);
        comb.setUFCons(TUf.GO);

        // NFCe de posto e rejeitada (378) sem o encerrante da bomba
        Prod.Comb.Encerrante encerrante = new Prod.Comb.Encerrante();
        encerrante.setNBico(c.nBico);
        encerrante.setNTanque(c.nTanque);
        encerrante.setVEncIni(c.vEncIni.setScale(3, RoundingMode.HALF_UP).toPlainString());
        encerrante.setVEncFin(c.vEncFin().toPlainString());
        comb.setEncerrante(encerrante);

        if (c.pBio != null) {
            comb.setPBio(c.pBio);
        }
        prod.setComb(comb);

        return prod;
    }

    /**
     * Preenche dados do Imposto da NFCe.
     *
     * Combustivel sai com PIS/COFINS nao tributados (CST 04) e ICMS ja retido:
     * ICMS61 (monofasico, com aliquota ad rem) para gasolina e diesel, ICMS60
     * (substituicao tributaria) para o etanol, como nas notas reais do posto.
     */
    private static Imposto preencheImposto(Combustivel c) {
        Imposto imposto = new Imposto();

        ICMS icms = new ICMS();
        if (c.icmsAdRemRet != null) {
            ICMS.ICMS61 icms61 = new ICMS.ICMS61();
            icms61.setOrig("0");
            icms61.setCST("61");
            icms61.setQBCMonoRet(ObjetoUtil.getValor4Casas(c.qCom));
            icms61.setAdRemICMSRet(c.icmsAdRemRet);
            icms61.setVICMSMonoRet(ObjetoUtil.getValor2Casas(c.vIcmsMonoRet()));
            icms.setICMS61(icms61);
        } else {
            ICMS.ICMS60 icms60 = new ICMS.ICMS60();
            icms60.setOrig("0");
            icms60.setCST("60");
            icms60.setVBCSTRet("0.00");
            icms60.setPST("0.00");
            icms60.setVICMSSTRet("0.00");
            icms.setICMS60(icms60);
        }

        PIS pis = new PIS();
        PIS.PISNT pisnt = new PIS.PISNT();
        pisnt.setCST("04");
        pis.setPISNT(pisnt);

        COFINS cofins = new COFINS();
        COFINS.COFINSNT cofinsnt = new COFINS.COFINSNT();
        cofinsnt.setCST("04");
        cofins.setCOFINSNT(cofinsnt);

        imposto.getContent().add(new JAXBElement<>(new QName("ICMS"), ICMS.class, icms));
        imposto.getContent().add(new JAXBElement<>(new QName("PIS"), PIS.class, pis));
        imposto.getContent().add(new JAXBElement<>(new QName("COFINS"), COFINS.class, cofins));

        return imposto;
    }

    /**
     * Prenche Total NFCe
     *
     * @return
     */
    private static Total preencheTotal() {
        BigDecimal vProd = BigDecimal.ZERO;
        BigDecimal vIcmsMonoRet = BigDecimal.ZERO;
        BigDecimal qBCMonoRet = BigDecimal.ZERO;

        for (Combustivel c : Combustivel.values()) {
            vProd = vProd.add(c.vProd());
            if (c.icmsAdRemRet != null) {
                vIcmsMonoRet = vIcmsMonoRet.add(c.vIcmsMonoRet());
                qBCMonoRet = qBCMonoRet.add(c.qCom);
            }
        }

        Total total = new Total();
        ICMSTot icmstot = new ICMSTot();
        icmstot.setVBC("0.00");
        icmstot.setVICMS("0.00");
        icmstot.setVICMSDeson("0.00");
        icmstot.setVFCP("0.00");
        icmstot.setVFCPST("0.00");
        icmstot.setVFCPSTRet("0.00");
        icmstot.setVBCST("0.00");
        icmstot.setVST("0.00");
        // No ICMSTot o qBCMonoRet e TDec_1302 (2 casas); no ICMS61 do item e TDec_1104v (4 casas)
        icmstot.setQBCMonoRet(ObjetoUtil.getValor2Casas(qBCMonoRet));
        icmstot.setVICMSMonoRet(ObjetoUtil.getValor2Casas(vIcmsMonoRet));
        icmstot.setVProd(ObjetoUtil.getValor2Casas(vProd));
        icmstot.setVFrete("0.00");
        icmstot.setVSeg("0.00");
        icmstot.setVDesc("0.00");
        icmstot.setVII("0.00");
        icmstot.setVIPI("0.00");
        icmstot.setVIPIDevol("0.00");
        icmstot.setVPIS("0.00");
        icmstot.setVCOFINS("0.00");
        icmstot.setVOutro("0.00");
        icmstot.setVNF(ObjetoUtil.getValor2Casas(vProd));
        total.setICMSTot(icmstot);

        // Totais IBS/CBS: de 2027 em diante o vNFTot soma o monofasico ao vNF
        // (VB01-10 Excecao 1); em 2025 e 2026 fica igual ao vNF. IBSCBSTot traz o gMono
        total.setVNFTot(ObjetoUtil.getValor2Casas(ibsCbsUtil.calculaVnfTot(icmstot.getVNF())));
        total.setIBSCBSTot(ibsCbsUtil.preencheTotaisIbsCsb());

        return total;
    }

    /** Soma dos itens, usada no pagamento. */
    private static BigDecimal totalNota() {
        BigDecimal vProd = BigDecimal.ZERO;
        for (Combustivel c : Combustivel.values()) {
            vProd = vProd.add(c.vProd());
        }
        return vProd;
    }

    /**
     * Preenche Transporte
     *
     * @return
     */
    private static Transp preencheTransporte() {
        Transp transp = new Transp();
        transp.setModFrete("9");
        return transp;
    }

    /**
     * Preenche dados Pagamento
     *
     * @return
     */
    private static Pag preenchePag() {
        Pag pag = new Pag();
        Pag.DetPag detPag = new Pag.DetPag();
        detPag.setTPag("01");
        detPag.setVPag(ObjetoUtil.getValor2Casas(totalNota()));
        pag.getDetPag().add(detPag);

        return pag;
    }

    /**
     * Preenche QRCode
     *
     * @param enviNFe
     * @param config
     * @param idToken
     * @param csc
     * @return
     * @throws NfeException
     * @throws NoSuchAlgorithmException
     */
    private static String preencheQRCode(TEnviNFe enviNFe, ConfiguracoesNfe config) throws NfeException {

        //QRCODE EMISAO ONLINE
        return NFCeUtil.getCodeQRCodeV3(
                enviNFe.getNFe().get(0).getInfNFe().getId().substring(3),
                config.getAmbiente().getCodigo(),
                WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_QRCODE));

        //QRCODE EMISSAO OFFLINE
//        return NFCeUtil.getCodeQRCodeContingencia(
//                enviNFe.getNFe().get(0).getInfNFe().getId().substring(3),
//                config.getAmbiente().getCodigo(),
//                enviNFe.getNFe().get(0).getInfNFe().getIde().getDhEmi(),
//                enviNFe.getNFe().get(0).getInfNFe().getTotal().getICMSTot().getVNF(),
//                Base64.getEncoder().encodeToString(enviNFe.getNFe().get(0).getSignature().getSignedInfo().getReference().getDigestValue()),
//                idToken,
//                csc,
//                WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_QRCODE));
    }

}
