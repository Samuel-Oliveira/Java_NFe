package br.com.swconsultoria.nfe.impressao;

import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2DTO;
import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2Util;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

/**
 * DANFE NFC-e (Thymeleaf), em bobina. A URL de consulta pela chave vem do infNFeSupl/urlChave do XML.
 */
public class TesteImpressaoNFCe {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = XmlNfeUtil.leXml("d:/teste/nfce.xml");

            //Aqui está pegando o Layout Padrão (bobina de 80 mm, logo da NFC-e)
            ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFCe(xml);

            //Customizações opcionais
            //impressao.setLarguraPapelMm(58);                             //Bobina de 58 mm: QR Code centralizado
            //impressao.setViaEstabelecimento(true);                        //Contingência: segunda via, que fica no estabelecimento
            //impressao.setLogoArquivo("d:/teste/logo.png");               //Logo próprio (ou removerLogo())

            //Faz a impressão em pdf File
            ImpressaoDanfeV2Util.impressaoPdfArquivo(impressao, "d:/teste/teste-nfce.pdf");
            System.out.println("Impressão Pdf Arquivo OK");

            //Ou: impressaoPdfByte(impressao), impressaoHtml(impressao, destino), impressaoDireta(impressao)
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }

}
