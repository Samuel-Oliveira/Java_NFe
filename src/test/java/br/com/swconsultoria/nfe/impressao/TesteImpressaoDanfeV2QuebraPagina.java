package br.com.swconsultoria.nfe.impressao;

import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2DTO;
import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2Util;
import br.com.swconsultoria.nfe.impressao.v2.OrientacaoDanfeEnum;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

/**
 * Teste da quebra de página do DANFE v2 com uma NF-e de 50 itens.
 * Conferir no PDF: o cabeçalho e o título da tabela de itens se repetem nas folhas seguintes,
 * a numeração "FOLHA x/y" e se nenhuma linha de item ficou cortada entre duas folhas.
 * Execute a partir da raiz do projeto.
 */
public class TesteImpressaoDanfeV2QuebraPagina {

    public static void main(String[] args) {
        try {
            //NF-e fictícia de homologação com 50 itens (gerada a partir do DanfeV2Rtc.xml)
            String xml = XmlNfeUtil.leXml("src/test/resources/DanfeV2Rtc50Itens.xml");

            //Retrato
            ImpressaoDanfeV2DTO retrato = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
            retrato.setOrientacao(OrientacaoDanfeEnum.RETRATO);
            ImpressaoDanfeV2Util.impressaoPdfArquivo(retrato, "d:/teste/teste-nfe-v2-50-itens-retrato.pdf");
            System.out.println("Impressão Pdf Retrato OK");

            //Paisagem
            ImpressaoDanfeV2DTO paisagem = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
            paisagem.setOrientacao(OrientacaoDanfeEnum.PAISAGEM);
            ImpressaoDanfeV2Util.impressaoPdfArquivo(paisagem, "d:/teste/teste-nfe-v2-50-itens-paisagem.pdf");
            System.out.println("Impressão Pdf Paisagem OK");
        } catch (Exception e) {
            //Trate seus erros aqui
            e.printStackTrace();
        }
    }
}
