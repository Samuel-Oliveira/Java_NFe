#!/usr/bin/env bash
# =====================================================================
# migrate.sh — Migra imports Java de java-nfe v4.00.* para v4.1.2
#
# USO:
#   bash scripts/migrate.sh [caminho] [--dry-run]
#
# EXEMPLOS:
#   bash scripts/migrate.sh src/main/java
#   bash scripts/migrate.sh --dry-run
#
# A v4.1.2 consolidou ~48 sub-packages em 2 packages:
#   br.com.swconsultoria.nfe.schemas         (tipos nao-evento)
#   br.com.swconsultoria.nfe.schemas_eventos (todos os eventos)
#
# Classes de eventos foram renomeadas para evitar colisao:
#   TEnvEvento (cancelamento) -> TEnvEventoCancelamento
#   TEnvEvento (cce)          -> TEnvEventoCartaCorrecao
#   TEvento (generico)        -> TEventoGenerico
#   ResEvento                 -> ResumoEvento
#   TProcEvento (consSitNFe)  -> TProcEventoConsSitNFe
#   etc.
#
# LIMITACAO: este script NAO bumpa o pom.xml, NAO renomeia o nome simples
# no corpo do arquivo (so o FQN no import) e NAO trata eventos individuais
# da Reforma Tributaria. Para esses casos use scripts/migrate.ps1.
#
# Veja MIGRATION.md para detalhes e o script PowerShell completo.
# =====================================================================

set -euo pipefail

SEARCH_PATH="."
DRY_RUN=false

for arg in "$@"; do
    case "$arg" in
        --dry-run) DRY_RUN=true ;;
        *) SEARCH_PATH="$arg" ;;
    esac
done

echo ""
echo "=== java-nfe v4.00.* -> v4.1.2 Migration Script (bash) ==="
echo ""
if $DRY_RUN; then
    echo "Modo DRY-RUN: nenhum arquivo sera modificado."
    echo ""
fi

# Garante que sed -i funciona tanto em GNU (Linux) quanto em BSD (macOS)
sedi() {
    if sed --version 2>/dev/null | grep -q GNU; then
        sed -i "$@"
    else
        sed -i '' "$@"
    fi
}

total_modified=0

# Encontrar arquivos .java e .kt excluindo os proprios packages gerados e Suframa
mapfile -t FILES < <(
    find "$SEARCH_PATH" \
        -type f \( -name '*.java' -o -name '*.kt' \) \
        ! -path '*/schemas_eventos/*' \
        ! -path '*/schemas/*' \
        ! -path '*/schema/eventoSuframa*' \
    2>/dev/null
)

for file in "${FILES[@]}"; do
    original=$(cat "$file")
    modified="$original"

    # ──────────────────────────────────────────────────────
    # 1) Substituir packages (mais especifico primeiro)
    # ──────────────────────────────────────────────────────

    # schema_4.* → schemas
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema_4\.[a-zA-Z0-9_]*\./br.com.swconsultoria.nfe.schemas./g')

    # schema.consCad, schema.distDFe, schema.resNFe, schema.comum → schemas
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.consCad\./br.com.swconsultoria.nfe.schemas./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.distDFe\./br.com.swconsultoria.nfe.schemas./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.resNFe\./br.com.swconsultoria.nfe.schemas./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.comum\./br.com.swconsultoria.nfe.schemas./g')

    # schema.envcce / schema.cce → schemas_eventos
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envcce\./br.com.swconsultoria.nfe.schemas_eventos./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.cce\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # schema.envEventoCancNFe, schema.eventoCancNFe → schemas_eventos
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envEventoCancNFe\./br.com.swconsultoria.nfe.schemas_eventos./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.eventoCancNFe\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # schema.envEventoCancSubst → schemas_eventos
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envEventoCancSubst\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # schema.envConfRecebto, schema.confRecebto → schemas_eventos (manifestacao)
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envConfRecebto\./br.com.swconsultoria.nfe.schemas_eventos./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.confRecebto\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # schema.envEpec, schema.epec → schemas_eventos
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envEpec\./br.com.swconsultoria.nfe.schemas_eventos./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.epec\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # schema.envEventoAtorInteressado → schemas_eventos
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envEventoAtorInteressado\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # schema.eventoGenerico → schemas_eventos
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.eventoGenerico\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # schema.envEventoEConf, schema.envEventoCancEConf → schemas_eventos
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envEventoEConf\./br.com.swconsultoria.nfe.schemas_eventos./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envEventoCancEConf\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # schema.envEventoInsucessoNFe, schema.envEventoCancInsucessoNFe → schemas_eventos
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envEventoInsucessoNFe\./br.com.swconsultoria.nfe.schemas_eventos./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envEventoCancInsucessoNFe\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # schema.envEventoEntregaNFe, schema.envEventoCancEntregaNFe → schemas_eventos
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envEventoEntregaNFe\./br.com.swconsultoria.nfe.schemas_eventos./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.envEventoCancEntregaNFe\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # schema.resnfe.ResNFe / schema.retdistdfeint.* → schemas (nao schemas_eventos)
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.resnfe\./br.com.swconsultoria.nfe.schemas./g' \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.retdistdfeint\./br.com.swconsultoria.nfe.schemas./g')

    # schema.eventoXXXXXX.DetEvento (Reforma Tributaria) → schemas_eventos.DetEvento<XXXXXX>
    # (inner classes preservadas: schema.evento112110.DetEvento.GConsumo → schemas_eventos.DetEvento112110.GConsumo)
    modified=$(echo "$modified" | sed -E \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.evento([0-9]+)\.DetEvento\b/br.com.swconsultoria.nfe.schemas_eventos.DetEvento\1/g')

    # qualquer schema.* restante → schemas_eventos (catch-all para outros eventos)
    modified=$(echo "$modified" | sed \
        -e 's/br\.com\.swconsultoria\.nfe\.schema\.[a-zA-Z0-9_]*\./br.com.swconsultoria.nfe.schemas_eventos./g')

    # ──────────────────────────────────────────────────────
    # 2) Substituir nomes de classes renomeadas
    # ──────────────────────────────────────────────────────
    modified=$(echo "$modified" | sed \
        -e 's/\bTEnvEventoCancNFe\b/TEnvEventoCancelamento/g' \
        -e 's/\bTRetEnvEventoCancNFe\b/TRetEnvEventoCancelamento/g' \
        -e 's/\bTEnvEventoCCe\b/TEnvEventoCartaCorrecao/g' \
        -e 's/\bTRetEnvEventoCCe\b/TRetEnvEventoCartaCorrecao/g' \
        -e 's/\bTEnvEventoEPEC\b/TEnvEventoEpec/g' \
        -e 's/\bTRetEnvEventoEPEC\b/TRetEnvEventoEpec/g' \
        -e 's/\bTEnvEventoConfRecebto\b/TEnvEventoManifestacao/g' \
        -e 's/\bTRetEnvEventoConfRecebto\b/TRetEnvEventoManifestacao/g' \
        -e 's/\bTEnvEventoCancSubst\b/TEnvEventoCancelamentoSubstituicao/g' \
        -e 's/\bTRetEnvEventoCancSubst\b/TRetEnvEventoCancelamentoSubstituicao/g' \
        -e 's/\bTEnvEventoAtorInt\b/TEnvEventoAtorInteressado/g' \
        -e 's/\bTRetEnvEventoAtorInt\b/TRetEnvEventoAtorInteressado/g' \
        -e 's/\bTEnvEventoEConf\b/TEnvEventoConciliacaoFinanceira/g' \
        -e 's/\bTRetEnvEventoEConf\b/TRetEnvEventoConciliacaoFinanceira/g' \
        -e 's/\bTEnvEventoCancEConf\b/TEnvEventoCancelamentoConciliacaoFinanceira/g' \
        -e 's/\bTRetEnvEventoCancEConf\b/TRetEnvEventoCancelamentoConciliacaoFinanceira/g' \
        -e 's/\bTEnvEventoInsucessoNFe\b/TEnvEventoInsucessoEntrega/g' \
        -e 's/\bTRetEnvEventoInsucessoNFe\b/TRetEnvEventoInsucessoEntrega/g' \
        -e 's/\bTEnvEventoCancInsucessoNFe\b/TEnvEventoCancelamentoInsucessoEntrega/g' \
        -e 's/\bTRetEnvEventoCancInsucessoNFe\b/TRetEnvEventoCancelamentoInsucessoEntrega/g' \
        -e 's/\bTEnvEventoEntregaNFe\b/TEnvEventoComprovanteEntrega/g' \
        -e 's/\bTRetEnvEventoEntregaNFe\b/TRetEnvEventoComprovanteEntrega/g' \
        -e 's/\bTEnvEventoCancEntregaNFe\b/TEnvEventoCancelamentoComprovanteEntrega/g' \
        -e 's/\bTRetEnvEventoCancEntregaNFe\b/TRetEnvEventoCancelamentoComprovanteEntrega/g' \
        -e 's/\bResEvento\b/ResumoEvento/g')

    if [ "$modified" != "$original" ]; then
        total_modified=$((total_modified + 1))
        if $DRY_RUN; then
            echo "  [DRY] $file"
        else
            printf '%s' "$modified" > "$file"
            echo "  [OK]  $file"
        fi
    fi
done

echo ""
if $DRY_RUN; then
    echo "$total_modified arquivo(s) seriam modificados."
else
    echo "$total_modified arquivo(s) modificados."
fi

echo ""
echo "IMPORTANTE: Revise manualmente os seguintes casos:"
echo "  - Usos do nome simples TEnvEvento/TRetEnvEvento/TEvento/TRetEvento no corpo"
echo "    (apos o import ser corrigido, o nome simples PRECISA virar TEnvEventoCancelamento,"
echo "    TEnvEventoCartaCorrecao etc. baseado em qual evento o arquivo manipula)."
echo "  - TProtNFe.InfProt.getDhRecbto() agora retorna String (era XMLGregorianCalendar)."
echo "  - XmlNfeUtil.xmlToObject(xml, T*.class) — certifique-se de usar a classe correta"
echo ""
echo "Para tratamento automatico do nome simples no corpo do arquivo + bump do pom,"
echo "use scripts/migrate.ps1 (PowerShell 7+ roda em Linux/macOS via pwsh)."
echo ""
echo "Apos migrar, execute:"
echo "  mvn test-compile   # verificar compilacao"
echo "  mvn test           # verificar testes"
