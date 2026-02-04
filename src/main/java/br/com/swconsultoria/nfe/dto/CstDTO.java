package br.com.swconsultoria.nfe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO representando um CST (Código de Situação Tributária) do CFF.
 * Tolerante a mudanças: campos desconhecidos são ignorados automaticamente.
 *
 * @author Rodrigo Cananea - rodrigo@rcconsultoria.inf.br
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CstDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("CST")
    private String cst;

    @JsonProperty("DescricaoCST")
    private String descricaoCST;

    @JsonProperty("IndIBSCBS")
    private Boolean indIBSCBS;

    @JsonProperty("IndRedBC")
    private Boolean indRedBC;

    @JsonProperty("IndRedAliq")
    private Boolean indRedAliq;

    @JsonProperty("IndTransfCred")
    private Boolean indTransfCred;

    @JsonProperty("IndDif")
    private Boolean indDif;

    @JsonProperty("IndAjusteCompet")
    private Boolean indAjusteCompet;

    @JsonProperty("IndIBSCBSMono")
    private Boolean indIBSCBSMono;

    @JsonProperty("IndCredPresIBSZFM")
    private Boolean indCredPresIBSZFM;

    @JsonProperty("Publicacao")
    private String publicacao;

    @JsonProperty("InicioVigencia")
    private String inicioVigencia;

    @JsonProperty("FimVigencia")
    private String fimVigencia;

    @JsonProperty("classificacoesTributarias")
    private List<ClassificacaoTributariaDTO> classificacoesTributarias;
}
