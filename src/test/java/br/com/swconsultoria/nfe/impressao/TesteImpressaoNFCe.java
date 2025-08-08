package br.com.swconsultoria.nfe.impressao;

import br.com.swconsultoria.nfe.util.XmlNfeUtil;

public class TesteImpressaoNFCe {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = XmlNfeUtil.leXml("d:/teste/nfce.xml");

            //Informe a Url de Consulta do NFCe de seu Estado
            String urlConsulta = "www.sefaznet.ac.gov.br/nfce/consulta";
            //Aqui está pegando o Layout Padrão
            ImpressaoDTO impressao = ImpressaoNfeUtil.impressaoPadraoNFCe(xml, urlConsulta);

            //Faz a impressão em pdf File
            ImpressaoNfeUtil.impressaoPdfArquivo(impressao, "d:/teste/teste-nfce.pdf");
            System.out.println("Impressão Pdf Arquivo OK");
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }

}