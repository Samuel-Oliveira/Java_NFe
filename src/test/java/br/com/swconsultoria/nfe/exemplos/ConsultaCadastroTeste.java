package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.schema.consCad.TRetConsCad;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

/**
 * @author Samuel Oliveira
 */
public class ConsultaCadastroTeste {

    public static void main(String[] args) {

        try {

            // Inicia As Configurações
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(EstadosEnum.MG, AmbienteEnum.HOMOLOGACAO);

            try {
                //Envia a Consulta
                TRetConsCad retorno = Nfe.consultaCadastro(config, PessoaEnum.JURIDICA, "30081357000617", EstadosEnum.GO);

                //Valida o Retorno da Consulta Cadastro
                RetornoUtil.validaConsultaCadastro(retorno);

                //Resultado
                System.out.println();
                System.out.println("# Status: " + retorno.getInfCons().getCStat() + " - " + retorno.getInfCons().getXMotivo());
                System.out.println();
                retorno.getInfCons().getInfCad().forEach(cadastro -> {
                    System.out.println("# Razão Social: " + cadastro.getXNome());
                    System.out.println("# Cnpj: " + cadastro.getCNPJ());
                    System.out.println("# Ie: " + cadastro.getIE());
                });

                System.out.println(XmlNfeUtil.objectToXml(retorno));

            } catch (Exception a) {
                System.err.println();
                System.err.println(a.getMessage());
            }

            Thread.sleep(1000);

        } catch (Exception e) {
            System.err.println();
            System.err.println(e.getMessage());
        }

    }

}
