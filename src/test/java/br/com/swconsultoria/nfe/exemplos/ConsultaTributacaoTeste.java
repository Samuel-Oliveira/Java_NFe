package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.ConsultaTributacao;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dto.ClassificacaoTributariaDTO;
import br.com.swconsultoria.nfe.dto.CstDTO;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.ConfiguracoesUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Teste de exemplo para executar a consulta classTrib (CFF) e imprimir o resultado.
 *
 * Observações:
 * - Certifique-se de que ConfiguracaoTeste.iniciaConfiguracoes(...) retorna ConfiguracoesNfe com certificado válido
 *   (o mesmo usado nas outras chamadas da biblioteca).
 * - Garanta que o arquivo WebServicesNfe.ini contenha a seção [CFF] com a chave classTrib apontando para:
 *     https://cff.svrs.rs.gov.br/api/v1/consultas/classTrib
 *
 * Execução (IDE ou linha de comando):
 *   rode como uma aplicação Java normal; o método main inicializa config e executa a consulta.
 */
public class ConsultaTributacaoTeste {

    public static void main(String[] args) {

        try {
            // Inicializa configurações (usa o helper de teste que já existe no projeto)
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(
                    br.com.swconsultoria.nfe.dom.enuns.EstadosEnum.MG,
                    br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum.HOMOLOGACAO
            );

            // (opcional) valida se a URL está presente (ajuda a detectar INI não configurado)
            try {
                String url = WebServiceUtil.getCustomUrl(config, "CFF", "classTrib");
                System.out.println("URL classTrib: " + url);
            } catch (Exception e) {
                System.err.println("Não foi possível localizar a URL classTrib no WebServicesNfe.ini: " + e.getMessage());
                throw e;
            }

            // Executa a consulta (reaproveita a infra de certificado via ConfiguracoesUtil e CertificadoService)
            List<CstDTO> lista = ConsultaTributacao.getClassTrib(config);

            // Imprime resumo
            System.out.println();
            System.out.println("Quantidade de CSTs retornados: " + (lista == null ? 0 : lista.size()));
            System.out.println();

            if (lista != null) {
                for (CstDTO cst : lista) {
                    System.out.println("=== CST: " + cst.getCst() + " - " + cst.getDescricaoCST() + " ===");
                    System.out.println("IndIBSCBS: " + cst.getIndIBSCBS()
                            + " | IndRedBC: " + cst.getIndRedBC()
                            + " | IndRedAliq: " + cst.getIndRedAliq());
                    System.out.println("Classificações:");

                    List<ClassificacaoTributariaDTO> classs = cst.getClassificacoesTributarias();
                    if (classs != null) {
                        for (ClassificacaoTributariaDTO ct : classs) {
                            System.out.println("  - cClassTrib: " + ct.getCClassTrib());
                            System.out.println("    Descrição: " + ct.getDescricaoClassTrib());
                            System.out.println("    pRedIBS: " + ct.getPRedIBS() + " | pRedCBS: " + ct.getPRedCBS());
                            System.out.println("    TipoAliquota: " + ct.getTipoAliquota() + " | Anexo: " + ct.getAnexo());
                            System.out.println("    Link: " + ct.getLink());
                            System.out.println();
                        }
                    } else {
                        System.out.println("  (nenhuma classificação)");
                    }
                    System.out.println();
                }
            }

        } catch (NfeException | IOException | CertificadoException e) {
            System.err.println();
            System.err.println("Erro ao executar consulta classTrib: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println();
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
