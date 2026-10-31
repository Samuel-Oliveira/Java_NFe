package br.com.swconsultoria.nfe.impressao;

import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2DTO;
import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2Util;
import br.com.swconsultoria.nfe.impressao.v2.OrientacaoDanfeEnum;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

/**
 * DANFE v2 (Thymeleaf, leiaute NT 2026.010 - IBS/CBS/IS). Só NF-e modelo 55; a v1 (Jasper) continua em {@link TesteImpressaoNFe}.
 */
public class TesteImpressaoDanfeV2 {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = XmlNfeUtil.leXml("d:/teste/nfe.xml");

            //Aqui está pegando o Layout Padrão v2 (Thymeleaf - NT 2026.010)
            ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);

            //Customizações opcionais
            //impressao.setLogoArquivo("d:/teste/logo.png");               //Logo próprio (ou setLogo(InputStream / byte[]))
            //impressao.removerLogo();                                      //Imprime sem logo
            //impressao.setOrientacao(OrientacaoDanfeEnum.PAISAGEM);        //Padrão: AUTOMATICA (pelo tpImp do XML)
            //impressao.setExibirCanhoto(false);
            //impressao.setTemplate("d:/teste/meu-danfe.html");             //Template Thymeleaf próprio
            //impressao.getParametros().put("chave", "valor");              //Disponível no template como ${parametros.chave}

            //Faz a impressão em pdf File
            ImpressaoDanfeV2Util.impressaoPdfArquivo(impressao, "d:/teste/teste-nfe-v2.pdf");
            System.out.println("Impressão Pdf Arquivo OK");

            //Ou: ImpressaoDanfeV2Util.impressaoPdfByte(impressao), impressaoHtml(impressao, destino), impressaoDireta(impressao)
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}
