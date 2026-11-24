package br.com.swconsultoria.nfe.impressao;

import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2DTO;
import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2Util;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

/**
 * DANFE Simplificado Tipo 2 (NF-e com tpImp=6, leiaute NT 2026.003), em bobina. Sai pelo mesmo
 * ImpressaoDanfeV2Util do DANFE v2: o template é escolhido pelo tpImp do XML.
 * -Dxml, -Dpdf e -Dlargura trocam o arquivo da nota, o PDF gerado e a largura do papel em mm;
 * -DviaEstabelecimento=true imprime a segunda via da contingência.
 */
public class TesteImpressaoDanfeSimplificadoTipo2 {

    public static void main(String[] args) {
        try {
            //Faça a leitura do Arquivo (nfeProc autorizado, ou NFe sem protocolo em contingência)
            String xml = XmlNfeUtil.leXml(System.getProperty("xml", "d:/teste/nfe-tipo2.xml"));

            //Com tpImp=6 no XML, o Layout Padrão v2 já é o DANFE Simplificado Tipo 2 (bobina de 80 mm)
            ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);

            //Customizações opcionais
            impressao.setLarguraPapelMm(Integer.parseInt(System.getProperty("largura", "80"))); //58 mm: QR Code centralizado
            impressao.setViaEstabelecimento(Boolean.getBoolean("viaEstabelecimento")); //Contingência: segunda via, que fica no estabelecimento
            //impressao.setLogoArquivo("d:/teste/logo.png");               //Logo próprio (ou removerLogo())

            //Faz a impressão em pdf File
            ImpressaoDanfeV2Util.impressaoPdfArquivo(impressao, System.getProperty("pdf", "d:/teste/teste-nfe-tipo2.pdf"));
            System.out.println("Impressão Pdf Arquivo OK");

            //Ou: impressaoDireta(impressao) na impressora térmica padrão
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}
