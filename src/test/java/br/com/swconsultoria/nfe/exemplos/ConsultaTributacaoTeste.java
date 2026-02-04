package br.com.swconsultoria.nfe.exemplos;

import br.com.swconsultoria.nfe.ConsultaTributacao;
import br.com.swconsultoria.nfe.ConsultaTributacao.ValidationReport;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dto.CstDTO;
import br.com.swconsultoria.nfe.dto.CstTesteDTO;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Exemplos de uso da classe ConsultaTributacao.
 * Demonstra os principais recursos disponíveis para consulta de classificações tributárias CFF.
 *
 * @author Rodrigo Cananea - rodrigo@rcconsultoria.inf.br
 */
public class ConsultaTributacaoTeste {

    public static void main(String[] args) {
        try {
            ConfiguracoesNfe config = ConfiguracaoTeste.iniciaConfiguracoes(
                    br.com.swconsultoria.nfe.dom.enuns.EstadosEnum.RS,
                    br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum.PRODUCAO
            );

            exemploJsonString(config);
            exemploConversaoDTO(config);
            exemploComFiltro(config);
            exemploValidacao(config);

            System.out.println("\nTodos os exemplos executados com sucesso!");

        } catch (Exception e) {
            System.err.println("Erro durante execucao: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Exemplo 1: Obter JSON string bruto da API.
     */
    private static void exemploJsonString(ConfiguracoesNfe config) throws Exception {
        System.out.println("=== EXEMPLO 1: Obter JSON Bruto ===");

        String json = ConsultaTributacao.getJson(config);

        System.out.println("JSON recebido: " + json.length() + " caracteres");
        System.out.println();
    }

    /**
     * Exemplo 2: Converter automaticamente para objetos DTO.
     */
    private static void exemploConversaoDTO(ConfiguracoesNfe config) throws Exception {
        System.out.println("=== EXEMPLO 2: Converter para DTOs ===");

        List<CstDTO> lista = ConsultaTributacao.get(
                config,
                new TypeReference<List<CstDTO>>() {
                }
        );

        System.out.println("Total de CSTs: " + lista.size());

        if (!lista.isEmpty()) {
            CstDTO primeiro = lista.get(0);
            System.out.println("Primeiro CST: " + primeiro.getCst() + " - " + primeiro.getDescricaoCST());
            System.out.println("Publicacao: " + primeiro.getPublicacao());
            System.out.println("inicioVigencia: " + primeiro.getInicioVigencia());
            System.out.println("fimVigencia: " + primeiro.getFimVigencia());
        }

        System.out.println();
    }

    /**
     * Exemplo 3: Consulta com filtro por CST específico.
     */
    private static void exemploComFiltro(ConfiguracoesNfe config) throws Exception {
        System.out.println("=== EXEMPLO 3: Consulta com Filtro ===");

        Map<String, String> parametros = new HashMap<>();
        parametros.put("Cst", "00");

        List<CstDTO> filtrado = ConsultaTributacao.get(
                config,
                parametros,
                new TypeReference<List<CstDTO>>() {
                }
        );

        System.out.println("CSTs com filtro Cst=00: " + filtrado.size());

        for (CstDTO cst : filtrado) {
            System.out.println("  - " + cst.getCst() + ": " + cst.getDescricaoCST());
        }

        System.out.println();
    }

    /**
     * Exemplo 4: Validar compatibilidade do DTO com o JSON da API.
     */
    private static void exemploValidacao(ConfiguracoesNfe config) throws Exception {
        System.out.println("=== EXEMPLO 4: Validar Estrutura ===");

        ValidationReport report = ConsultaTributacao.validate(config, CstTesteDTO.class);

        if (report.isValid()) {
            System.out.println("DTO esta 100% compativel com a API");
        } else {
            System.out.println("ATENCAO: Inconsistencias detectadas\n");

            if (!report.getExtraFields().isEmpty()) {
                System.out.println("Campos extras no JSON (nao estao no DTO):");
                report.getExtraFields().forEach(field -> System.out.println("  - " + field));
            }

            if (!report.getMissingFields().isEmpty()) {
                System.out.println("\nCampos faltando no JSON (esperados no DTO):");
                report.getMissingFields().forEach(field -> System.out.println("  - " + field));
            }

            if (!report.getTypeErrors().isEmpty()) {
                System.out.println("\nErros de tipo:");
                report.getTypeErrors().forEach((field, error) ->
                        System.out.println("  - " + field + ": " + error));
            }
        }

        System.out.println();
    }
}
