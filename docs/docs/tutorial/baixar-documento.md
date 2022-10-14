# Baixar Documento (DistDfe)

Função para consulta/baixa de NF-e na Sefaz.

### Exemplo de consulta via NSU e Chave de Acesso: 
```java title="DistribuicaoDFeTeste.java"
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.*;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt.LoteDistDFeInt.DocZip;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

import java.util.List;

/**
 * @author Samuel Oliveira
 *
 */
public class DistribuicaoDFeTeste {

    public static void main(String[] args) {
        try {
            // Inicia As Configurações (1)
            URI uri = Objects.requireNonNull(DistribuicaoDFeTeste.class.getClassLoader().getResource("arquivo-certificado.pfx")).toURI();            
            Certificado certificado = CertificadoService.certificadoPfx(Paths.get(file.toURI()).toString(), "senha-do-seu-certificado");
            ConfiguracoesNfe configuracoesNfe = ConfiguracoesNfe.criarConfiguracoes(EstadosEnum.PR, AmbienteEnum.HOMOLOGACAO, certificado, "");

            // Informe o CNPJ Do Destinatario (Deve ser o Mesmo do Certificado)
            String cnpj = "XXX";

            RetDistDFeInt retorno;

            // Para Consulta Via CHAVE
            // String chave = "35170843283811001202550010046314601229130549";
            // retorno = Nfe.distribuicaoDfe(configuracoesNfe, PessoaEnum.JURIDICA, cnpj, ConsultaDFeEnum.CHAVE, chave);

            // Para Consulta Via NSU
            String nsu = "000000000000000";
            retorno = Nfe.distribuicaoDfe(config, PessoaEnum.JURIDICA, cnpj, ConsultaDFeEnum.NSU, nsu);

            if (StatusEnum.DOC_LOCALIZADO_PARA_DESTINATARIO.getCodigo().equals(retorno.getCStat())) {
                System.out.println();
                System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());
                System.out.println("# NSU Atual: " + retorno.getUltNSU());
                System.out.println("# Max NSU: " + retorno.getMaxNSU());
                System.out.println("# Max NSU: " + retorno.getMaxNSU());

                //Aqui Recebe a Lista De XML (No Maximo 50 por Consulta)
                List<DocZip> listaDoc = retorno.getLoteDistDFeInt().getDocZip();
                for (DocZip docZip : listaDoc) {
                    System.out.println();
                    System.out.println("# Schema: " + docZip.getSchema());
                    switch (docZip.getSchema()) {
                        case "resNFe_v1.01.xsd":
                            System.out.println("# Este é o XML em resumo, deve ser feito a Manifestação para o Objeter o XML Completo.");
                            break;
                        case "procNFe_v4.00.xsd":
                            System.out.println("# XML Completo.");
                            break;
                        case "procEventoNFe_v1.00.xsd":
                            System.out.println("# XML Evento.");
                            break;
                    }
                    //Transforma o GZip em XML
                    String xml = XmlNfeUtil.gZipToXml(docZip.getValue());
                    System.out.println("# XML: " + xml);
                }
            } else {
                System.out.println();
                System.out.println("# Status: " + retorno.getCStat() + " - " + retorno.getXMotivo());
            }
        } catch (Exception e) {
            System.err.println();
            System.err.println("# Erro: "+e.getMessage());
        }
    }
}
```

1.  Acesse o menu [Configurações (Certificado)](./configuracoes.md)
