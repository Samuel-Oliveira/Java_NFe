/**
 *
 */
package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.*;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.consReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.*;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.*;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import br.com.swconsultoria.nfe.util.*;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
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

            //Informe o Numero da NFCe
            int numeroNFCe = 9407;
            //Informe o CNPJ do Emitente da NFCe
            String cnpj = "10732644000128";
            //Informe a data de Emissao da NFCe
            LocalDateTime dataEmissao = LocalDateTime.now();
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

//            TInfRespTec respTec = new TInfRespTec();
//            respTec.setCNPJ("99999999999999");
//            respTec.setEmail("email@dominio.com.br");
//            respTec.setFone("99999999999");
//            respTec.setXContato("Nome do Resposavel");
//            respTec.setIdCSRT("01");
//            respTec.setHashCSRT(NFCeUtil.geraHashCSRT("Chave Da Nota", "CSRT fornecido pela SEFAZ"));
//            infNFe.setInfRespTec(respTec);

            //Preenche IDE
            infNFe.setIde(preencheIde(config, cnf, numeroNFCe, tipoEmissao, modelo, serie, cdv, dataEmissao));

            //Preenche Emitente
            infNFe.setEmit(preencheEmitente(config, cnpj));

            //Preenche o Destinatario
            infNFe.setDest(preencheDestinatario());

            //Preenche os dados do Produto da NFCe e adiciona a Lista
            infNFe.getDet().addAll(preencheDet());

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

            // Monta e Assina o XML
            enviNFe = Nfe.montaNfe(config, enviNFe, true);

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
    private static List<Det> preencheDet() {

        //O Preenchimento deve ser feito por produto, Então deve ocorrer uma LIsta
        Det det = new Det();
        //O numero do Item deve seguir uma sequencia
        det.setNItem("1");

        // Preenche dados do Produto
        det.setProd(preencheProduto());

        //Preenche dados do Imposto
        det.setImposto(preencheImposto());

        //Retorna a Lista de Det
        return Collections.singletonList(det);
    }

    /**
     * Preenche dados do Produto
     *
     * @return
     */
    private static Prod preencheProduto() {
        Prod prod = new Prod();
        prod.setCProd("7898480650104");
        prod.setCEAN("7898480650104");
        prod.setXProd("NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        prod.setNCM("27101932");
        prod.setCEST("0600500");
        prod.setIndEscala("S");
        prod.setCFOP("5102");
        prod.setUCom("UN");
        prod.setQCom("1.0000");
        prod.setVUnCom("13.0000");
        prod.setVProd("13.00");
        prod.setCEANTrib("7898480650104");
        prod.setUTrib("UN");
        prod.setQTrib("1.0000");
        prod.setVUnTrib("13.0000");
        prod.setIndTot("1");

        return prod;
    }

    /**
     * Preenche dados do Imposto da NFCe
     *
     * @return
     */
    private static Imposto preencheImposto() {
        Imposto imposto = new Imposto();

        ICMS icms = new ICMS();

        ICMS.ICMS00 icms00 = new ICMS.ICMS00();
        icms00.setOrig("0");
        icms00.setCST("00");
        icms00.setModBC("0");
        icms00.setVBC("13.00");
        icms00.setPICMS("7.00");
        icms00.setVICMS("0.91");

        icms.setICMS00(icms00);

        PIS pis = new PIS();
        PISAliq pisAliq = new PISAliq();
        pisAliq.setCST("01");
        pisAliq.setVBC("13.00");
        pisAliq.setPPIS("1.65");
        pisAliq.setVPIS("0.21");
        pis.setPISAliq(pisAliq);

        COFINS cofins = new COFINS();
        COFINSAliq cofinsAliq = new COFINSAliq();
        cofinsAliq.setCST("01");
        cofinsAliq.setVBC("13.00");
        cofinsAliq.setPCOFINS("7.60");
        cofinsAliq.setVCOFINS("0.99");
        cofins.setCOFINSAliq(cofinsAliq);

        JAXBElement<ICMS> icmsElement = new JAXBElement<ICMS>(new QName("ICMS"), ICMS.class, icms);
        imposto.getContent().add(icmsElement);

        JAXBElement<PIS> pisElement = new JAXBElement<PIS>(new QName("PIS"), PIS.class, pis);
        imposto.getContent().add(pisElement);

        JAXBElement<COFINS> cofinsElement = new JAXBElement<COFINS>(new QName("COFINS"), COFINS.class, cofins);
        imposto.getContent().add(cofinsElement);

        return imposto;
    }

    /**
     * Prenche Total NFCe
     *
     * @return
     */
    private static Total preencheTotal() {
        Total total = new Total();
        ICMSTot icmstot = new ICMSTot();
        icmstot.setVBC("13.00");
        icmstot.setVICMS("0.91");
        icmstot.setVICMSDeson("0.00");
        icmstot.setVFCP("0.00");
        icmstot.setVFCPST("0.00");
        icmstot.setVFCPSTRet("0.00");
        icmstot.setVBCST("0.00");
        icmstot.setVST("0.00");
        icmstot.setVProd("13.00");
        icmstot.setVFrete("0.00");
        icmstot.setVSeg("0.00");
        icmstot.setVDesc("0.00");
        icmstot.setVII("0.00");
        icmstot.setVIPI("0.00");
        icmstot.setVIPIDevol("0.00");
        icmstot.setVPIS("0.21");
        icmstot.setVCOFINS("0.99");
        icmstot.setVOutro("0.00");
        icmstot.setVNF("13.00");
        total.setICMSTot(icmstot);

        return total;
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
        detPag.setVPag("13.00");
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
