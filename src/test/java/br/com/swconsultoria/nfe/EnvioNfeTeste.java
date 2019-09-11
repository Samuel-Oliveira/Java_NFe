package br.com.swconsultoria.nfe;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnderEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEndereco;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Dest;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Emit;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Pag;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUf;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUfEmi;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.ConstantesUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

/**
 * @author Samuel Oliveira
 *
 */
public class EnvioNfeTeste {

    public static TEnviNFe getEnviNFe(ConfiguracoesNfe config) throws Exception {

        try {
            //Informe o Numero da NFe
            int numeroNfe = 100;
            //Informe o CNPJ do Emitente da NFe
            String cnpj = "99999999999999";
            //Informe a data de Emissao da NFe
            LocalDateTime dataEmissao = LocalDateTime.now();
            //Informe o cnf da NFCe com 8 digitos
            String cnf = ChaveUtil.completarComZerosAEsquerda(String.valueOf(numeroNfe), 8);
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

            //Preenche os dados do Produto da Nfe e adiciona a Lista
            infNFe.getDet().addAll(preencheDet());

            //Preenche totais da NFe
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
            enviNFe = Nfe.montaNfe(config, enviNFe, false);

            return enviNFe;

        } catch (Exception e) {
            System.err.println();
            System.err.println("# Erro: " + e.getMessage());
            throw e;
        }

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
    private static Ide preencheIde(ConfiguracoesNfe config, String cnf, int numeroNfe, String tipoEmissao, String modelo, int serie, String cDv, LocalDateTime dataEmissao) throws NfeException {
        Ide ide = new Ide();
        ide.setCUF(config.getEstado().getCodigoUF());
        ide.setCNF(cnf);
        ide.setNatOp("NOTA FISCAL CONSUMIDOR ELETRONICA");
        ide.setMod(modelo);
        ide.setSerie(String.valueOf(serie));

        ide.setNNF(String.valueOf(numeroNfe));
        ide.setDhEmi(XmlNfeUtil.dataNfe(dataEmissao));
        ide.setTpNF("1");
        ide.setIdDest("1");
        ide.setCMunFG("5219753");
        ide.setTpImp("1");
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
     * Preenche o Emitente da Nfe
     * @param config
     * @param cnpj
     * @return
     */
    private static Emit preencheEmitente(ConfiguracoesNfe config, String cnpj) {
        Emit emit = new Emit();
        emit.setCNPJ(cnpj);
        emit.setXNome("XXXX");

        TEnderEmi enderEmit = new TEnderEmi();
        enderEmit.setXLgr("AV SANTO ANTONIO & cia");
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

        emit.setIE("XXX");
        emit.setCRT("3");

        return emit;
    }

    /**
     * Preenche o Destinatario da NFe
     * @return
     */
    private static Dest preencheDestinatario() {
        Dest dest = new Dest();
        dest.setCNPJ("XXX");
        dest.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");

        TEndereco enderDest = new TEndereco();
        enderDest.setXLgr("Rua: Teste");
        enderDest.setNro("0");
        enderDest.setXBairro("TESTE");
        enderDest.setCMun("4109708");
        enderDest.setXMun("IBAITI");
        enderDest.setUF(TUf.valueOf("PR"));
        enderDest.setCEP("84900000");
        enderDest.setCPais("1058");
        enderDest.setXPais("Brasil");
        enderDest.setFone("4845454545");
        dest.setEnderDest(enderDest);
        dest.setEmail("teste@test");
        dest.setIndIEDest("9");
        return dest;
    }

    /**
     * Preenche Det Nfe
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
     * @return
     */
    private static Transp preencheTransporte(){
        Transp transp = new Transp();
        transp.setModFrete("9");
        return transp;
    }

    /**
     * Preenche dados Pagamento
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

}