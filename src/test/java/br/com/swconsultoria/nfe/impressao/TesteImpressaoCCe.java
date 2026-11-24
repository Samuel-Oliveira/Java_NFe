package br.com.swconsultoria.nfe.impressao;

import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2DTO;
import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2Util;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

/**
 * Carta de Correção eletrônica (Thymeleaf), em A4, a partir do procEventoNFe.
 */
public class TesteImpressaoCCe {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = XmlNfeUtil.leXml("d:/teste/cce.xml");

            //Aqui está pegando o Layout Padrão (sem logo: o XML do evento não traz o nome do emitente)
            ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoCCe(xml);
            //impressao.setLogoArquivo("d:/teste/logo.png");               //Logo próprio

            //Faz a impressão em pdf File
            ImpressaoDanfeV2Util.impressaoPdfArquivo(impressao, "d:/teste/teste-cce.pdf");
            System.out.println("Impressão Pdf Arquivo OK");
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}
