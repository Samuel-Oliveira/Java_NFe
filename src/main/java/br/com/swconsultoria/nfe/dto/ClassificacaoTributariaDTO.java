package br.com.swconsultoria.nfe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ClassificacaoTributariaDTO {

    @JsonProperty("cClassTrib")
    private String cClassTrib;

    @JsonProperty("DescricaoClassTrib")
    private String descricaoClassTrib;

    @JsonProperty("pRedIBS")
    private BigDecimal pRedIBS;

    @JsonProperty("pRedCBS")
    private BigDecimal pRedCBS;

    @JsonProperty("IndRedutorBC")
    private Boolean indRedutorBC;

    @JsonProperty("IndTribRegular")
    private Boolean indTribRegular;

    @JsonProperty("IndCredPresOper")
    private Boolean indCredPresOper;

    @JsonProperty("IndEstornoCred")
    private Boolean indEstornoCred;

    @JsonProperty("TipoAliquota")
    private String tipoAliquota;

    @JsonProperty("IndNFe")
    private Boolean indNFe;

    @JsonProperty("IndNFCe")
    private Boolean indNFCe;

    @JsonProperty("IndCTe")
    private Boolean indCTe;

    @JsonProperty("IndCTeOS")
    private Boolean indCTeOS;

    @JsonProperty("IndBPe")
    private Boolean indBPe;

    @JsonProperty("IndNF3e")
    private Boolean indNF3e;

    @JsonProperty("IndNFCom")
    private Boolean indNFCom;

    @JsonProperty("IndNFSE")
    private Boolean indNFSE;

    @JsonProperty("IndBPeTM")
    private Boolean indBPeTM;

    @JsonProperty("IndBPeTA")
    private Boolean indBPeTA;

    @JsonProperty("IndNFAg")
    private Boolean indNFAg;

    @JsonProperty("IndNFSVIA")
    private Boolean indNFSVIA;

    @JsonProperty("IndNFABI")
    private Boolean indNFABI;

    @JsonProperty("IndNFGas")
    private Boolean indNFGas;

    @JsonProperty("IndDERE")
    private Boolean indDERE;

    @JsonProperty("Anexo")
    private Integer anexo;

    @JsonProperty("Link")
    private String link;

}
