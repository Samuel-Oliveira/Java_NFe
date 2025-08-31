package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.*;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt.LoteDistDFeInt.DocZip;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import java.util.List;

/**
 * @author Samuel Oliveira
 */
public class DistribuicaoDFeTeste {

    public static void main(String[] args) {
        try {

            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.GO, AmbienteEnum.HOMOLOGACAO);

            //Informe o CNPJ Do Destinatario (Deve ser o Mesmo do Certificado)
            String cnpj = "10732644000128";

            RetDistDFeInt retorno;
//
//            //Para Consulta Via NSU
            String nsu = "000000000000000";
            while (true) {

                retorno = Nfe.distribuicaoDfe(config, PessoaEnum.JURIDICA, cnpj, ConsultaDFeEnum.NSU, nsu);

                if (StatusEnum.DOC_LOCALIZADO_PARA_DESTINATARIO.getCodigo().equals(retorno.getCStat())) {
                    System.out.println();
                    System.out.println("# Status: " +
                            retorno.getCStat() +
                            " - " +
                            retorno.getXMotivo());
                    System.out.println("# NSU Atual: " +
                            retorno.getUltNSU());
                    System.out.println("# Max NSU: " +
                            retorno.getMaxNSU());

                    //Aqui Recebe a Lista De XML (No Maximo 50 por Consulta)
                    List<DocZip> listaDoc = retorno.getLoteDistDFeInt().getDocZip();
                    for (DocZip docZip : listaDoc) {

                        String texto;
                        switch (docZip.getSchema()) {
                            case "resNFe_v1.01.xsd":
                                texto = "# Este é o XML em resumo, deve ser feito a Manifestação para o Objeter o XML Completo.";
                                break;
                            case "procNFe_v4.00.xsd":
                                texto = "# XML Completo.";
                                break;
                            case "procEventoNFe_v1.00.xsd":
                            texto = "# XML Evento.";
                                break;
                            default:
                                texto = "# Schema não configurado";
                        }
                        System.out.println(texto);
                        System.out.println("# XML: " +
                                XmlNfeUtil.gZipToXml(docZip.getValue()));

                    }

                } else {
                    System.out.println();
                    System.out.println("# Status: " +
                            retorno.getCStat() +
                            " - " +
                            retorno.getXMotivo());
                }

                if (retorno.getUltNSU().equals(retorno.getMaxNSU())) {
                    break;
                }
                nsu = retorno.getUltNSU();
            }

        } catch (Exception e) {
            System.err.println();
            System.err.println("# Erro: " +
                    e.getMessage());
        }
    }
}