package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.schemas.TEnviNFe;
import br.com.swconsultoria.nfe.schemas.TNFe;
import br.com.swconsultoria.nfe.schemas.TProtNFe;
import br.com.swconsultoria.nfe.schemas.TRetEnviNFe;
import br.com.swconsultoria.nfe.util.ChaveUtil;
import br.com.swconsultoria.nfe.util.NFCeUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import javax.xml.bind.JAXBElement;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Mede se a SEFAZ ja aceita a NF-e (modelo 55) com DANFE Simplificado Tipo 2
 * (tpImp=6), da NT 2026.002 (regras de validacao, contingencia off-line da
 * NF-e) e da NT 2026.003 (leiaute do DANFE e QR Code). Pelo cronograma da NT
 * 2026.002, as regras do tpImp=6 entraram em homologacao em 01/07/2026 e em
 * producao em 03/08/2026.
 *
 * Um cenario por execucao, escolhido por -Dcenario (cenario fora da lista e
 * recusado). Os demais parametros: -Dnumero e -Dserie (padrao serie 3 e numero
 * tirado do relogio, para cada execucao ter numero proprio e nao cair na 539),
 * -Dambiente (padrao HOMOLOGACAO), -Duf (padrao GO) e -Dsaida (arquivo onde
 * gravar o nfeProc autorizado, para conferir o DANFE). O emitente e o mesmo do
 * EnvioNfceSimplesNacionalTeste, com os mesmos -DcnpjEmit ate -DcepEmit.
 *
 * Todos com destinatario CNPJ nao contribuinte, menos o tipo2semdest.
 *
 *   normal        tpImp=1, emissao normal                      (controle)
 *   tipo2         tpImp=6, emissao normal, QR Code v3
 *   tipo2semdest  tpImp=6 sem destinatario
 *   tipo2semqr    tpImp=6 sem o grupo infNFeSupl         (esperado 394, ZX02-10)
 *   tipo2final0   tpImp=6 com indFinal=0                 (esperado 716, B25a-10)
 *   offline       tpImp=6, tpEmis=9, QR Code v3 de contingencia assinado
 *   offline1      tpImp=1, tpEmis=9                      (esperado 711, B22-10)
 *
 * Os "esperado" servem de prova: se a SEFAZ autoriza o tipo2 e recusa os
 * outros com os codigos da NT, as regras do tpImp=6 estao ligadas, e nao so o
 * valor 6 aceito pelo schema.
 *
 * Resultado medido em GO, homologacao, 23/11/2026:
 *
 *   cenario        cStat
 *   normal         100
 *   tipo2          100
 *   tipo2semdest   719  NF-e sem a identificacao do destinatario
 *   tipo2semqr     394  Nota Fiscal sem a informacao do QR-Code
 *   tipo2final0    716  NFC-e ou NF-e com DANFE Simplificado Tipo 2 em operacao
 *                       nao destinada a consumidor final
 *   offline        100
 *   offline1       711  NF-e com contingencia off-line em operacao nao permitida
 *
 * Ou seja: o tpImp=6 esta ativo em GO homologacao, com as regras da NT 2026.002
 * e a contingencia off-line (tpEmis=9) da NF-e, mas o destinatario continua
 * obrigatorio (719), ao contrario do que a W16-40 e a E01-20 da NT sugerem.
 *
 * O QR Code da NF-e usa a mesma URL da NFC-e (ZX02-20, observacao 3), versao 3
 * (ZX02-220). Em homologacao o xProd do item 1 e o xNome do destinatario tem
 * de ser os literais da SEFAZ (I04-10). Nao ha o que cancelar: homologacao nao
 * gera documento fiscal.
 *
 * Em PRODUCAO este exemplo AUTORIZA nota de verdade (R$ 100,00). Nao rode em
 * producao sem necessidade, e cancele com o CancelarTeste se rodar.
 */
public class EnvioNfeDanfeSimplificadoTipo2Teste {

    private static final String CNPJ_EMIT = System.getProperty("cnpjEmit", "10732644000128");
    private static final String IE_EMIT = System.getProperty("ieEmit", "104519304");
    private static final String NOME_EMIT = System.getProperty("nomeEmit", "POSTO PARK XIII LTDA");
    private static final String C_MUN_EMIT = System.getProperty("cMunEmit", "5219753");
    private static final String X_MUN_EMIT = System.getProperty("xMunEmit", "SANTO ANTONIO DO DESCOBERTO");
    private static final String X_LGR_EMIT = System.getProperty("xLgrEmit", "AV SANTO ANTONIO");
    private static final String NRO_EMIT = System.getProperty("nroEmit", "0");
    private static final String X_BAIRRO_EMIT = System.getProperty("xBairroEmit", "PQ STO ANTONIO");
    private static final String CEP_EMIT = System.getProperty("cepEmit", "72900000");

    private static final String SERIE = System.getProperty("serie", "3");
    /** Segundos desde 1970 com 9 digitos: unico por execucao, sem precisar lembrar o ultimo numero usado. */
    private static final String NUMERO = System.getProperty("numero",
            String.valueOf(System.currentTimeMillis() / 1000 % 1_000_000_000L));

    private static final String V_PROD = "100.00";

    private static final String CENARIO = System.getProperty("cenario", "tipo2");

    private static final List<String> CENARIOS = Arrays.asList(
            "normal", "tipo2", "tipo2semdest", "tipo2semqr", "tipo2final0", "offline", "offline1");

    public static void main(String[] args) {
        if (!CENARIOS.contains(CENARIO)) {
            throw new IllegalArgumentException("Cenario desconhecido: " + CENARIO + ". Use um de " + CENARIOS);
        }
        try {
            AmbienteEnum ambiente = AmbienteEnum.valueOf(System.getProperty("ambiente", "HOMOLOGACAO"));
            EstadosEnum estado = EstadosEnum.valueOf(System.getProperty("uf", "GO"));
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(estado, ambiente);

            LocalDateTime agora = LocalDateTime.now();
            String cNF = "00000006";
            ChaveUtil chaveUtil = new ChaveUtil(estado, CNPJ_EMIT, DocumentoEnum.NFE.getModelo(),
                    Integer.parseInt(SERIE), Integer.parseInt(NUMERO), tpEmis(), cNF, agora);
            String id = chaveUtil.getChaveNF();
            String chave = id.substring(3);
            String dhEmi = XmlNfeUtil.dataNfe(agora, null);

            String xml = montaXml(id, cNF, dhEmi, ambiente.getCodigo(), estado);
            TEnviNFe enviNFe = XmlNfeUtil.xmlToObject(xml, TEnviNFe.class);

            System.out.println("# Cenario  : " + CENARIO + " (tpImp=" + tpImp() + ", tpEmis=" + tpEmis() + ")");
            System.out.println("# Ambiente : " + ambiente + " (tpAmb=" + ambiente.getCodigo() + ")");
            System.out.println("# Numero   : serie " + SERIE + " nNF " + NUMERO);
            System.out.println("# Chave    : " + chave);

            enviNFe = Nfe.montaNfe(config, enviNFe, true);
            System.out.println("# Schema local: OK");

            if (tipo2() && !"tipo2semqr".equals(CENARIO)) {
                // NF-e com DANFE Simplificado Tipo 2 usa a URL de QR Code da NFC-e (ZX02-20, observacao 3)
                String urlQr = WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_QRCODE);
                String qrCode = offline()
                        ? NFCeUtil.getCodeQRCodeContingenciaV3(chave, ambiente.getCodigo(), dhEmi, vNF(),
                        destinatario() ? "1" : null, destinatario() ? CNPJ_DEST : null, urlQr, config.getCertificado())
                        : NFCeUtil.getCodeQRCodeV3(chave, ambiente.getCodigo(), urlQr);
                TNFe.InfNFeSupl supl = new TNFe.InfNFeSupl();
                supl.setQrCode(qrCode);
                supl.setUrlChave(WebServiceUtil.getUrl(config, DocumentoEnum.NFCE, ServicosEnum.URL_CONSULTANFCE));
                enviNFe.getNFe().get(0).setInfNFeSupl(supl);
                System.out.println("# QR Code  : " + qrCode);
            }

            TRetEnviNFe retorno = Nfe.enviarNfe(config, enviNFe, DocumentoEnum.NFE);

            System.out.println("# Lote     : " + retorno.getCStat() + " - " + retorno.getXMotivo());
            if (retorno.getProtNFe() != null) {
                TProtNFe.InfProt infProt = retorno.getProtNFe().getInfProt();
                System.out.println("# Nota     : " + infProt.getCStat() + " - " + infProt.getXMotivo());
                System.out.println("# Protocolo: " + infProt.getNProt());
                // Alertas da autorizacao com alerta (NT 2026.002, cStat 120): pares cMsg/xMsg do grupo PR13
                for (JAXBElement<String> msg : infProt.getCMsgAndXMsg()) {
                    System.out.println("# " + msg.getName().getLocalPart() + "     : " + msg.getValue());
                }
                String saida = System.getProperty("saida");
                if (saida != null && infProt.getNProt() != null) {
                    Files.write(Paths.get(saida), XmlNfeUtil.criaNfeProc(enviNFe, retorno.getProtNFe())
                            .getBytes(StandardCharsets.UTF_8));
                    System.out.println("# nfeProc  : " + saida);
                }
            }

        } catch (Exception e) {
            System.err.println("# Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static final String CNPJ_DEST = "32330160000195";

    private static boolean tipo2() {
        return CENARIO.startsWith("tipo2") || "offline".equals(CENARIO);
    }

    private static boolean offline() {
        return CENARIO.startsWith("offline");
    }

    /**
     * Pela NT 2026.002 (W16-40 e E01-20) o destinatario seria opcional no tpImp=6, como na NFC-e, mas GO
     * homologacao recusa a NF-e sem ele com 719 (medido em 23/11/2026): so o tipo2semdest vai sem.
     */
    private static boolean destinatario() {
        return !"tipo2semdest".equals(CENARIO);
    }

    private static String tpImp() {
        return tipo2() ? "6" : "1";
    }

    private static String tpEmis() {
        return offline() ? "9" : "1";
    }

    private static String vNF() {
        return V_PROD;
    }

    private static String montaXml(String id, String cNF, String dhEmi, String tpAmb, EstadosEnum estado) {
        return "<enviNFe versao=\"4.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">"
                + "<idLote>1</idLote><indSinc>1</indSinc>"
                + "<NFe><infNFe versao=\"4.00\" Id=\"" + id + "\">"
                + "<ide><cUF>" + estado.getCodigoUF() + "</cUF><cNF>" + cNF + "</cNF>"
                + "<natOp>VENDA DE MERCADORIA</natOp>"
                + "<mod>55</mod><serie>" + SERIE + "</serie><nNF>" + NUMERO + "</nNF>"
                + "<dhEmi>" + dhEmi + "</dhEmi><tpNF>1</tpNF><idDest>1</idDest>"
                + "<cMunFG>" + C_MUN_EMIT + "</cMunFG><tpImp>" + tpImp() + "</tpImp><tpEmis>" + tpEmis() + "</tpEmis>"
                + "<cDV>" + id.substring(id.length() - 1) + "</cDV><tpAmb>" + tpAmb + "</tpAmb>"
                + "<finNFe>1</finNFe><indFinal>" + ("tipo2final0".equals(CENARIO) ? "0" : "1") + "</indFinal>"
                + "<indPres>1</indPres>"
                + "<procEmi>0</procEmi><verProc>teste-danfe-tipo2</verProc>"
                + (offline() ? "<dhCont>" + dhEmi + "</dhCont>"
                + "<xJust>SEM CONEXAO COM A SEFAZ AUTORIZADORA - TESTE</xJust>" : "")
                + "</ide>"
                + "<emit><CNPJ>" + CNPJ_EMIT + "</CNPJ><xNome>" + NOME_EMIT + "</xNome>"
                + "<enderEmit><xLgr>" + X_LGR_EMIT + "</xLgr><nro>" + NRO_EMIT + "</nro>"
                + "<xBairro>" + X_BAIRRO_EMIT + "</xBairro>"
                + "<cMun>" + C_MUN_EMIT + "</cMun><xMun>" + X_MUN_EMIT + "</xMun><UF>" + estado + "</UF>"
                + "<CEP>" + CEP_EMIT + "</CEP><cPais>1058</cPais><xPais>Brasil</xPais></enderEmit>"
                + "<IE>" + IE_EMIT + "</IE><CRT>3</CRT></emit>"
                + (destinatario() ? "<dest><CNPJ>" + CNPJ_DEST + "</CNPJ>"
                + "<xNome>NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL</xNome>"
                + "<enderDest><xLgr>RUA 1</xLgr><nro>1</nro><xBairro>CENTRO</xBairro>"
                + "<cMun>5208707</cMun><xMun>GOIANIA</xMun><UF>GO</UF><CEP>74000000</CEP>"
                + "<cPais>1058</cPais><xPais>Brasil</xPais></enderDest>"
                + "<indIEDest>9</indIEDest></dest>" : "")
                + "<det nItem=\"1\"><prod><cProd>1001</cProd><cEAN>SEM GTIN</cEAN>"
                + "<xProd>NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL</xProd>"
                + "<NCM>27101932</NCM><CFOP>5102</CFOP>"
                + "<uCom>UN</uCom><qCom>1.0000</qCom><vUnCom>" + V_PROD + "</vUnCom><vProd>" + V_PROD + "</vProd>"
                + "<cEANTrib>SEM GTIN</cEANTrib><uTrib>UN</uTrib><qTrib>1.0000</qTrib>"
                + "<vUnTrib>" + V_PROD + "</vUnTrib><indTot>1</indTot></prod>"
                + "<imposto><vTotTrib>0.00</vTotTrib>"
                + "<ICMS><ICMS00><orig>0</orig><CST>00</CST><modBC>3</modBC>"
                + "<vBC>" + V_PROD + "</vBC><pICMS>19.0000</pICMS><vICMS>19.00</vICMS></ICMS00></ICMS>"
                + "<PIS><PISOutr><CST>49</CST><vBC>0.00</vBC><pPIS>0.00</pPIS><vPIS>0.00</vPIS></PISOutr></PIS>"
                + "<COFINS><COFINSOutr><CST>49</CST><vBC>0.00</vBC><pCOFINS>0.00</pCOFINS>"
                + "<vCOFINS>0.00</vCOFINS></COFINSOutr></COFINS>"
                + "<IBSCBS><CST>000</CST><cClassTrib>000001</cClassTrib>"
                + "<gIBSCBS><vBC>" + V_PROD + "</vBC>"
                + "<gIBSUF><pIBSUF>0.1000</pIBSUF><vIBSUF>0.10</vIBSUF></gIBSUF>"
                + "<gIBSMun><pIBSMun>0.0000</pIBSMun><vIBSMun>0.00</vIBSMun></gIBSMun>"
                + "<vIBS>0.10</vIBS>"
                + "<gCBS><pCBS>0.9000</pCBS><vCBS>0.90</vCBS></gCBS>"
                + "</gIBSCBS></IBSCBS></imposto></det>"
                + "<total><ICMSTot>"
                + "<vBC>" + V_PROD + "</vBC><vICMS>19.00</vICMS><vICMSDeson>0.00</vICMSDeson><vFCP>0.00</vFCP>"
                + "<vBCST>0.00</vBCST><vST>0.00</vST><vFCPST>0.00</vFCPST><vFCPSTRet>0.00</vFCPSTRet>"
                + "<vProd>" + V_PROD + "</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc>"
                + "<vII>0.00</vII><vIPI>0.00</vIPI><vIPIDevol>0.00</vIPIDevol><vPIS>0.00</vPIS>"
                + "<vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>" + vNF() + "</vNF>"
                + "<vTotTrib>0.00</vTotTrib></ICMSTot>"
                + "<IBSCBSTot><vBCIBSCBS>" + V_PROD + "</vBCIBSCBS>"
                + "<gIBS><gIBSUF><vDif>0.00</vDif><vDevTrib>0.00</vDevTrib><vIBSUF>0.10</vIBSUF></gIBSUF>"
                + "<gIBSMun><vDif>0.00</vDif><vDevTrib>0.00</vDevTrib><vIBSMun>0.00</vIBSMun></gIBSMun>"
                + "<vIBS>0.10</vIBS><vCredPres>0.00</vCredPres><vCredPresCondSus>0.00</vCredPresCondSus></gIBS>"
                + "<gCBS><vDif>0.00</vDif><vDevTrib>0.00</vDevTrib><vCBS>0.90</vCBS>"
                + "<vCredPres>0.00</vCredPres><vCredPresCondSus>0.00</vCredPresCondSus></gCBS>"
                + "</IBSCBSTot><vNFTot>" + vNF() + "</vNFTot></total>"
                + "<transp><modFrete>9</modFrete></transp>"
                // Dinheiro com troco: o DANFE Simplificado Tipo 2 imprime o troco (Divisao III)
                + "<pag><detPag><tPag>01</tPag><vPag>150.00</vPag></detPag><vTroco>50.00</vTroco></pag>"
                + "<infAdic><infCpl>TESTE DO DANFE SIMPLIFICADO TIPO 2</infCpl></infAdic>"
                + "<infRespTec><CNPJ>32330160000195</CNPJ><xContato>Samuel Oliveira</xContato>"
                + "<email>samuel@swconsultoria.com.br</email><fone>62993066546</fone></infRespTec>"
                + "</infNFe></NFe></enviNFe>";
    }
}
