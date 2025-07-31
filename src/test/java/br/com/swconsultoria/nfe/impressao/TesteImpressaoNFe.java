package br.com.swconsultoria.nfe.impressao;

import br.com.swconsultoria.nfe.util.XmlNfeUtil;

public class TesteImpressaoNFe {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = XmlNfeUtil.leXml("d:/teste/nfe.xml");

            //Aqui está pegando o Layout Padrão
            ImpressaoDTO impressao = ImpressaoNfeUtil.impressaoPadraoNFe(xml);

            //Faz a impressão em pdf File
            ImpressaoNfeUtil.impressaoPdfArquivo(impressao, "d:/teste/teste-nfe.pdf");
            System.out.println("Impressão Pdf Arquivo OK");
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}