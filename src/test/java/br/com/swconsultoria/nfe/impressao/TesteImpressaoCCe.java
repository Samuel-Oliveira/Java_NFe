package br.com.swconsultoria.nfe.impressao;

import br.com.swconsultoria.nfe.util.XmlNfeUtil;

public class TesteImpressaoCCe {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = XmlNfeUtil.leXml("d:/teste/cce.xml");

            //Aqui está pegando o Layout Padrão
            ImpressaoDTO impressao = ImpressaoNfeUtil.impressaoPadraoCCe(xml);

            //Faz a impressão em pdf File
            ImpressaoNfeUtil.impressaoPdfArquivo(impressao, "d:/teste/teste-cce.pdf");
            System.out.println("Impressão Pdf Arquivo OK");
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}