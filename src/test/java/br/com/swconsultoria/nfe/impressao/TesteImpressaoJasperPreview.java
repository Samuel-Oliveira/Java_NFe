package br.com.swconsultoria.nfe.impressao;

import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import net.sf.jasperreports.view.JasperViewer;

public class TesteImpressaoJasperPreview {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo
            String xml = XmlNfeUtil.leXml("D:\\teste\\nfe.xml");

            //Aqui está pegando o Layout Padrão
            ImpressaoDTO impressao = ImpressaoNfeUtil.impressaoPadraoNFe(xml);

            //Faz a impressão e retorna um Jasper Preview
            JasperViewer jasperViewer = ImpressaoNfeUtil.impressaoPreview(impressao);

            //PAra mostrar o Preview
            jasperViewer.setVisible(true);
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}
