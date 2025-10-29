package br.com.swconsultoria.nfe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CstDTO {

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

    @JsonProperty("classificacoesTributarias")
    private List<ClassificacaoTributariaDTO> classificacoesTributarias;
}
