package br.com.swconsultoria.nfe.impressao;

import br.com.swconsultoria.nfe.util.XmlNfeUtil;

public class TesteImpressaoPdfArquivo {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = XmlNfeUtil.leXml("d:/teste/nfe.xml");

            //Aqui está pegando o Layout Padrão
            ImpressaoDTO impressao = ImpressaoNfeUtil.impressaoPadraoNFe(xml);

            //Faz a impressão em pdf File passando o caminho do Arquivo
            ImpressaoNfeUtil.impressaoPdfArquivo(impressao, "/d/teste/teste-nfe.pdf");

        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}