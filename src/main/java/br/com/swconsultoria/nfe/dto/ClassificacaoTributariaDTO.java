package br.com.swconsultoria.nfe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO representando uma Classificação Tributária do CFF.
 * Tolerante a mudanças: campos desconhecidos são ignorados automaticamente.
 *
 * @author Rodrigo Cananea - rodrigo@rcconsultoria. inf.br
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassificacaoTributariaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @JsonProperty("MonofasiaSujeitaRetencao")
    private Boolean monofasiaSujeitaRetencao;

    @JsonProperty("MonofasiaRetidaAnt")
    private Boolean monofasiaRetidaAnt;

    @JsonProperty("MonofasiaDiferimento")
    private Boolean monofasiaDiferimento;

    @JsonProperty("MonofasiaPadrao")
    private Boolean monofasiaPadrao;

    @JsonProperty("Publicacao")
    private String publicacao;

    @JsonProperty("InicioVigencia")
    private String inicioVigencia;

    @JsonProperty("FimVigencia")
    private String fimVigencia;

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
