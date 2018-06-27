package tests;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.NfeWeb;
import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.enumeration.*;
import br.com.samuelweb.nfe.util.model.*;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.impl.ErrosValidacao;
import br.com.samuelweb.nfe.util.validators.impl.NfeValidator;
import br.com.samuelweb.nfe.util.validators.impl.ValidarMunicipio;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TNFe;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NfeApplicationTests {

    public void contextLoads() {
    }

    //@Test
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Iniciando teste Objeto IDE");
        System.out.println("--------------------------------------------------------------------------");

        InfNFe infNFe = new InfNFe();
        infNFe.getIde().setCuf(41);
        infNFe.getIde().setNatOp("Venda de mercadoria adquirida ou recebida de terceiros");
        infNFe.getIde().setMod(ModeloDocumento.NFE.getValue());
        infNFe.getIde().setSerie(500);
        infNFe.getIde().setNnf(4);

        infNFe.getIde().setDhEmi(ZonedDateTime.parse("2018-05-28T00:00:00-03:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        infNFe.getIde().setDhSaiEnt(ZonedDateTime.parse("2018-05-28T17:19:20-03:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        infNFe.getIde().setTpNF(1);
        infNFe.getIde().setIdDest(1);
        infNFe.getIde().setcMunFG(4106902);
        infNFe.getIde().setTpImp(1);
        infNFe.getIde().setTpEmis(1);
        infNFe.getIde().setTpAmb(2);
        infNFe.getIde().setFinNFe(1);
        infNFe.getIde().setIndFinal(0);
        infNFe.getIde().setIndPres(1);
        infNFe.getIde().setProcEmi(0);
        infNFe.getIde().setVerProc("1.0.0.0");
        infNFe.getIde().setCnf("78501789");

        infNFe.getEmit().setCnpjCpf("04686827000151");
        infNFe.getEmit().setxNome("FADALEAL SUPERMERCADOS LTDA teste");
        infNFe.getEmit().setxFant("CASA FIESTA XV DE NOVEMBRO");
        infNFe.getEmit().getEnderEmit().setxLgr("XV DE NOVEMBRO");
        infNFe.getEmit().getEnderEmit().setNro("2357");
        infNFe.getEmit().getEnderEmit().setxBairro("ALTO DA RUA XV");
        infNFe.getEmit().getEnderEmit().setcMun(4106902);
        infNFe.getEmit().getEnderEmit().setxMun("Curitiba");
        infNFe.getEmit().getEnderEmit().setUf("PR");
        infNFe.getEmit().getEnderEmit().setCep(80045270);
        infNFe.getEmit().getEnderEmit().setcPais(1058);
        infNFe.getEmit().getEnderEmit().setxPais("BRASIL");
        infNFe.getEmit().getEnderEmit().setFone(30877001);
        infNFe.getEmit().setIe("9025008550");
        infNFe.getEmit().setCrt("3");

        infNFe.setDest(new Dest());
        infNFe.getDest().setCnpjCpf("04686827000232");
        infNFe.getDest().setxNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
        infNFe.getDest().getEnderDest().setxLgr("IGUACU");
        infNFe.getDest().getEnderDest().setNro("3534");
        infNFe.getDest().getEnderDest().setxBairro("AGUA VERDE");
        infNFe.getDest().getEnderDest().setcMun(4106902);
        infNFe.getDest().getEnderDest().setxMun("Curitiba");
        infNFe.getDest().getEnderDest().setUf("PR");
        infNFe.getDest().getEnderDest().setCep(80240031);
        infNFe.getDest().getEnderDest().setcPais(1058);
        infNFe.getDest().getEnderDest().setxPais("BRASIL");
        infNFe.getDest().getEnderDest().setFone("30877001");
        infNFe.getDest().setIndIEDest(1);
        infNFe.getDest().setIe("9025143253");


        Det det = new Det();
        det.getProd().setcProd("862875");
        det.getProd().setCean("5601010111240");
        det.getProd().setxProd("SARDINHA RAMIREZ EM TOMATE 125G");
        det.getProd().setNcm("16041310");
        det.getProd().setCest("1708000");
        det.getProd().setCfop("5102");
        det.getProd().setuCom("UN");
        det.getProd().setqCom(new BigDecimal("5.4564564564"));
        det.getProd().setvUnCom(new BigDecimal("4.97"));
        det.getProd().setvProd(new BigDecimal("24.85"));
        det.getProd().setCeanTrib("5601010111240");
        det.getProd().setuTrib("UN");
        det.getProd().setqTrib(new BigDecimal("5"));
        det.getProd().setvUnTrib(new BigDecimal("4.97"));
        det.getProd().setIndTot(1);

        det.getImposto().setIcms(new ICMS());
        det.getImposto().getIcms().setOrig(OrigemMercadoria.NACIONAL.getValue());
        det.getImposto().getIcms().setCST(CSTIcms.valueOf("CST_00"));
        det.getImposto().getIcms().setModBC(DeterminacaoBaseIcms.VALOR_OPERACAO);
        det.getImposto().getIcms().setvBC(new BigDecimal("24.85"));
        det.getImposto().getIcms().setpICMS(new BigDecimal("7.00"));
        det.getImposto().getIcms().setvICMS(new BigDecimal("1.74"));

        det.getImposto().setIpi(new IPI());
        det.getImposto().getIpi().setcEnq("999");
        det.getImposto().getIpi().setCst(CSTIpi.valueOf("CST53"));

        det.getImposto().setPis(new PIS());
        det.getImposto().getPis().setCST(CSTPis.valueOf("PIS01"));
        det.getImposto().getPis().setvBC(new BigDecimal("24.85"));
        det.getImposto().getPis().setpPIS(new BigDecimal("0.0"));
        det.getImposto().getPis().setvPIS(new BigDecimal("0.41"));

        det.getImposto().setCofins(new COFINS());
        det.getImposto().getCofins().setCst(CSTCofins.valueOf("CST01"));
        det.getImposto().getCofins().setvBC(new BigDecimal("24.85"));
        det.getImposto().getCofins().setpCOFINS(new BigDecimal("0.0"));
        det.getImposto().getCofins().setvCOFINS(new BigDecimal("1.89"));

        infNFe.getDetList().add(det);

        infNFe.getTotal().setIcmsTot(new ICMSTot());
        infNFe.getTotal().getIcmsTot().setVbc(new BigDecimal("24.85"));
        infNFe.getTotal().getIcmsTot().setVicms(new BigDecimal("1.74"));
        infNFe.getTotal().getIcmsTot().setVicmsDeson(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setVfcp(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setVbcst(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setVst(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setVfcpst(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setVfcpstRet(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setvProd(new BigDecimal("24.85"));
        infNFe.getTotal().getIcmsTot().setvFrete(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setvSeg(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setvDesc(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setVii(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setVipi(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setVipiDevol(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setVpis(new BigDecimal("0.41"));
        infNFe.getTotal().getIcmsTot().setVcofins(new BigDecimal("1.89"));
        infNFe.getTotal().getIcmsTot().setvOutro(new BigDecimal("0.00"));
        infNFe.getTotal().getIcmsTot().setVnf(new BigDecimal("24.85"));

        infNFe.getTotal().setRetTrib(new RetTrib());
        infNFe.getTotal().getRetTrib().setVbcirrf(new BigDecimal("24.85"));

        infNFe.getTransp().setModFrete(ModalidadeFrete.SEM_OCORRENCIA_DE_TRANSPORTE);
        infNFe.getTransp().setVol(new ArrayList<>());
        Vol vol = new Vol();
        vol.setqVol(5);
        vol.setnVol("5");
        vol.setPesoL(new BigDecimal("0.625"));
        infNFe.getTransp().getVol().add(vol);

        infNFe.setPag(new Pag());
        DetPag detPag = new DetPag();
        detPag.settPag(TipoPagamento.DINHEIRO);
        detPag.setvPag(new BigDecimal("24.85"));
        infNFe.getPag().getDetPag().add(detPag);

        infNFe.setInfAdic(new InfAdic());
        infNFe.getInfAdic().setInfAdFisco("-");

        NfeValidator validator = new NfeValidator();
        try {
            if (!validator.validarInfNfe(infNFe)) {
                List<ErrosValidacao> errosValidacaos = validator.getErrosList();
                for (ErrosValidacao errosValidacao : errosValidacaos) {
                    System.out.println(errosValidacao.toString());
                }
            }

            //Converte os dados para o objeto NFe
            TNFe nfe = new TNFe();
            nfe.setInfNFe(infNFe.build());

            // Monta EnviNfe
            TEnviNFe enviNFe = new TEnviNFe();
            enviNFe.setVersao("4.00");
            enviNFe.setIdLote("1");
            enviNFe.setIndSinc("1");
            enviNFe.getNFe().add(nfe);

            // Inicia As Certificado
            Certificado certificado = CertificadoService.certificadoPfx("/home/dalbosco/certificado/certificado.pfx", "1234");
            //Esse Objeto Você pode guardar em uma Session.
            ConfiguracoesWebNfe config = ConfiguracoesWebNfe.iniciaConfiguracoes(Estados.PR,
                    ConstantesUtil.AMBIENTE.HOMOLOGACAO,
                    certificado,
                    MethodHandles.lookup().lookupClass().getResource("/schemas").getPath(), //PEGAR SCHEMAS EM AMBIENTE WEB ESTA PASTA ESTA DENTRO DE RESOURCES
                    true);

            // Monta e Assina o XML
            enviNFe = NfeWeb.montaNfe(config, enviNFe, true);

        } catch (NfeException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (CertificadoException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Final do teste Objeto IDE");
        System.out.println("--------------------------------------------------------------------------");
    }


    //@Test
    public void testarCodigoMunicipio() {
        ValidarMunicipio validarMunicipio = new ValidarMunicipio();
        RetornoValidar retornoValidar;

        retornoValidar = validarMunicipio.validar(9999999);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(5203962);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(9999999);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(320000);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(4123456);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(5200050);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());

        retornoValidar = validarMunicipio.validar(4118501);
        System.out.println(retornoValidar.getValido() ? "valido": "Inválido:"+retornoValidar.getMensagem());
    }


}
