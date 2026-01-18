/**
 *
 */
package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.*;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Samuel Oliveira
 *
 */
public class EnvioNfeTeste {

    public static void main(String[] args) {
        Envia();
    }

    private static void Envia() {

        try {
            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            //Informe o Numero da NFe
            int numeroNfe = 92756;
            //Informe o CNPJ do Emitente da NFe
            String cnpj = "10732644000128";
            //Informe a data de Emissao da NFe
            LocalDateTime dataEmissao = LocalDateTime.now();
            //Informe o cnf da NFCe com 8 digitos
            String cnf = String.format("%08d", new Random().nextInt(99999999));
            //Informe o modelo da NFe
            String modelo = DocumentoEnum.NFE.getModelo();
            //Informe a Serie da NFe
            int serie = 1;
            //Informe o tipo de Emissao da NFe
            String tipoEmissao = "1";

            // MontaChave a NFe
            ChaveUtil chaveUtil = new ChaveUtil(config.getEstado(), cnpj, modelo, serie, numeroNfe, tipoEmissao, cnf, dataEmissao);
            String chave = chaveUtil.getChaveNF();
            String cdv = chaveUtil.getDigitoVerificador();

            InfNFe infNFe = new InfNFe();
            infNFe.setId(chave);
            infNFe.setVersao(ConstantesUtil.VERSAO.NFE);

            //Preenche IDE
            infNFe.setIde(preencheIde(config, cnf, numeroNfe, tipoEmissao, modelo, serie, cdv, dataEmissao));

            //Preenche Emitente
            infNFe.setEmit(preencheEmitente(config, cnpj));

            //Preenche o Destinatario
            infNFe.setDest(preencheDestinatario());
            infNFe.setEntrega(dadosEntrega(infNFe));

            //Preenche os dados do Produto da Nfe e adiciona a Lista
            infNFe.getDet().addAll(preencheDet());



            //Preenche totais da NFe
            infNFe.setTotal(preencheTotal());

            //Preenche os dados de Transporte
            infNFe.setTransp(preencheTransporte());

            // Preenche dados Pagamento
            infNFe.setPag(preenchePag());

            infNFe.setInfAdic(montaInfAdic());

            //Preenche as Informações de Intermediador
            infNFe.setInfIntermed(montaInfInterm());

            TNFe nfe = new TNFe();
            nfe.setInfNFe(infNFe);

            System.out.println(XmlNfeUtil.objectToXml(nfe));

            //Monta EnviNfe
            TEnviNFe enviNFe = new TEnviNFe();
            enviNFe.setVersao(ConstantesUtil.VERSAO.NFE);
            enviNFe.setIdLote("1");
            enviNFe.setIndSinc("1");
            enviNFe.getNFe().add(nfe);

            // Monta e Assina o XML
            enviNFe = Nfe.montaNfe(config, enviNFe, true);

            // Envia a Nfe para a Sefaz
            TRetEnviNFe retorno = Nfe.enviarNfe(config, enviNFe, DocumentoEnum.NFE);

            //Valida se o Retorno é Assincrono
            if (RetornoUtil.isRetornoAssincrono(retorno)) {
                //Pega o Recibo
                String recibo = retorno.getInfRec().getNRec();
                int tentativa = 0;
                br.com.swconsultoria.nfe.schema_4.consReciNFe.TRetConsReciNFe retornoNfe = null;

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

        } catch (Exception e) {

            System.err.println();
            System.err.println("# Erro: " + e.getMessage());
        }

    }

    private static InfNFe.InfIntermed montaInfInterm() {
        InfNFe.InfIntermed infIntermed = new InfNFe.InfIntermed();
        infIntermed.setCNPJ("46971895000102");
        infIntermed.setIdCadIntTran("JOao Intermediarios SA");
        return infIntermed;
    }

    private static InfNFe.InfAdic montaInfAdic() {
        InfNFe.InfAdic infAdic = new InfNFe.InfAdic();
        infAdic.setInfCpl("Observacao teste");

        return infAdic;
    }

    /**
     * Preenche o IDE
     * @param config
     * @param cnf
     * @param numeroNfe
     * @param tipoEmissao
     * @param cDv
     * @param dataEmissao
     * @return
     * @throws NfeException
     */
    private static InfNFe.Ide preencheIde(ConfiguracoesNfe config, String cnf, int numeroNfe, String tipoEmissao, String modelo, int serie, String cDv, LocalDateTime dataEmissao) {
        InfNFe.Ide ide = new InfNFe.Ide();
        ide.setCUF(config.getEstado().getCodigoUF());
        ide.setCNF(cnf);
        ide.setNatOp("NOTA FISCAL CONSUMIDOR ELETRONICA");
        ide.setMod(modelo);
        ide.setSerie(String.valueOf(serie));

        ide.setNNF(String.valueOf(numeroNfe));
        ide.setDhEmi(XmlNfeUtil.dataNfe(dataEmissao, null));
        ide.setTpNF("1");
        ide.setIdDest("1");
        ide.setCMunFG("5219753");
        ide.setTpImp("1");
        ide.setTpEmis(tipoEmissao);
        ide.setCDV(cDv);
        ide.setTpAmb(config.getAmbiente().getCodigo());
        ide.setFinNFe("1");
        ide.setIndFinal("1");
        ide.setIndPres("2");
        ide.setProcEmi("0");
        ide.setVerProc("1.0");
        ide.setIndIntermed("1");

        return ide;
    }

    /**
     * Preenche o Emitente da Nfe
     * @param config
     * @param cnpj
     * @return
     */
    private static InfNFe.Emit preencheEmitente(ConfiguracoesNfe config, String cnpj) {
        InfNFe.Emit emit = new InfNFe.Emit();
        emit.setCNPJ(cnpj);
        emit.setXNome("POSTO PARK XIII LTDA");

        TEnderEmi enderEmit = new TEnderEmi();
        enderEmit.setXLgr("AV SANTO ANTONIO cia");
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
     * Preenche o Destinatario da NFe
     * @return
     */
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
        enderDest.setUF(TUf.valueOf("GO"));
        enderDest.setCEP("74430130");
        enderDest.setCPais("1058");
        enderDest.setXPais("Brasil");
        enderDest.setFone("4845454545");
        dest.setEnderDest(enderDest);
        dest.setEmail("teste@test");
        dest.setIE("109684036");
        dest.setIndIEDest("1");
        return dest;
    }

    /**
     * Preenche Det Nfe
     */
    private static List<InfNFe.Det> preencheDet() {

        //O Preenchimento deve ser feito por produto, Então deve ocorrer uma LIsta
        InfNFe.Det det = new InfNFe.Det();
        //O numero do Item deve seguir uma sequencia
        det.setNItem("1");

        // Preenche dados do Produto
        det.setProd(preencheProduto());

        //Preenche dados do Imposto
        det.setImposto(preencheImposto());

        det.setInfAdProd("Informações Adicionais do Produto");

        //Retorna a Lista de Det
        return Collections.singletonList(det);
    }

    /**
     * Preenche dados do Produto
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
        prod.setCFOP("5405");
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
     * Preenche dados do Imposto da Nfe
     * @return
     */
    private static Imposto preencheImposto() {
        Imposto imposto = new Imposto();

        Imposto.ICMS icms = new Imposto.ICMS();

        Imposto.ICMS.ICMS60 icms60 = new Imposto.ICMS.ICMS60();
        icms60.setOrig("0");
        icms60.setCST("60");
        icms60.setVBCSTRet("0.00");
        icms60.setPST("0.00");
        icms60.setVICMSSTRet("0.00");
        icms60.setVICMSSubstituto("0.00");

        icms.setICMS60(icms60);

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

        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoVTotTrib("5.00"));
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(pis));
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(cofins));

        return imposto;
    }

    /**
     * Prenche Total NFe
     * @return
     */
    private static Total preencheTotal() {
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
        icmstot.setVTotTrib("5.00");
        total.setICMSTot(icmstot);

        return total;
    }

    /**
     * Preenche Transporte
     * @return
     */
    private static Transp preencheTransporte() {
        Transp transp = new Transp();
        transp.setModFrete("9");
        return transp;
    }

    /**
     * Preenche dados Pagamento
     * @return
     */
    private static InfNFe.Pag preenchePag() {
        InfNFe.Pag pag = new InfNFe.Pag();
        InfNFe.Pag.DetPag detPag = new InfNFe.Pag.DetPag();
        detPag.setTPag("01");
        detPag.setVPag("13.00");
        pag.getDetPag().add(detPag);

        return pag;
    }

    // Criado para evitar UFIdDest
    private static TLocal dadosEntrega(InfNFe infNFe) {
        TLocal entrega = new TLocal();

        entrega.setCNPJ(infNFe.getDest().getCNPJ());

        entrega.setXLgr(infNFe.getEmit().getEnderEmit().getXLgr());
        entrega.setNro(infNFe.getEmit().getEnderEmit().getNro());
        entrega.setXBairro(infNFe.getEmit().getEnderEmit().getXBairro());
        entrega.setCMun(infNFe.getEmit().getEnderEmit().getCMun());
        entrega.setXMun(infNFe.getEmit().getEnderEmit().getXMun());
        entrega.setUF(TUf.valueOf(infNFe.getEmit().getEnderEmit().getUF().toString()));
        return entrega;
    }

}
