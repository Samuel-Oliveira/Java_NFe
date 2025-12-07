/**
 *
 */
package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIBSCBSMonoTot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TTribNFe;
import br.com.swconsultoria.nfe.util.IbsCbsUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.math.BigDecimal;

/**
 * @author Samuel Oliveira
 */
public class CalculosIbsCbsTeste {

    public static void main(String[] args) {

        try {

            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            // Aqui usei vou usar um XML pronto para n precisar montar a nota, mas nao eh necessario!
            String xml = XmlNfeUtil.leXml("d:/teste/envinfe.xml");
            TEnviNFe enviNFe = XmlNfeUtil.xmlToObject(xml, TEnviNFe.class);

            //Carrega Json TabelaCclasstrib
            //Ver metodo ConsultaTributacao Teste
            String json = XmlNfeUtil.leXml("d:/teste/ibscbs.json");

            //Instancia a Classe Util de Calculo
            IbsCbsUtil ibsCbsUtil = new IbsCbsUtil(json, DocumentoEnum.NFE);

            //Chama a classe util para cada item
            for (TNFe.InfNFe.Det det : enviNFe.getNFe().get(0).getInfNFe().getDet()) {

                // Aqui vc deve passar o codigo do cclassTrib para Operacao desse item
                TTribNFe ibsCbs = ibsCbsUtil.montaImpostosDet("000001", det);

                // Exemplo com Trib Regular
                // TTribNFe ibsCbs = ibsCbsUtil.montaImpostosDet("550001", det, "000001");

                JAXBElement<TTribNFe> ibsCbsElement = new JAXBElement<>(new QName("IBSCBS"), TTribNFe.class, ibsCbs);
                det.getImposto().getContent().add(ibsCbsElement);

            }

            //Preenche vNfTot : ATENCAO ALGUMAS SEFAZ RECUSAO O  PREENCHIMENTO
            BigDecimal vNfTot = ibsCbsUtil.calculaVnfTot(enviNFe.getNFe().get(0).getInfNFe().getTotal().getICMSTot().getVNF());
            enviNFe.getNFe().get(0).getInfNFe().getTotal().setVNFTot(ObjetoUtil.getValor2Casas(vNfTot));

            //Por fim monta os calculos
            TIBSCBSMonoTot totaisIbsCsb = ibsCbsUtil.preencheTotaisIbsCsb();
            enviNFe.getNFe().get(0).getInfNFe().getTotal().setIBSCBSTot(totaisIbsCsb);

            // Monta, Assina e Valida o XML
            enviNFe = Nfe.montaNfe(config, enviNFe, true);

            System.out.println("XML COM IBSCBS: "+XmlNfeUtil.objectToXml(enviNFe));

            // Aqui segue com o ENVIO...

        } catch (Exception e) {
            System.err.println();
            System.err.println("# Erro: "+e.getMessage());
        }

    }

}
