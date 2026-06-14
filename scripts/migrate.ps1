#Requires -Version 7.0
<#
.SYNOPSIS
    Migra imports de código Java da estrutura v4.x para v5.0.0 do java-nfe.

.DESCRIPTION
    A versão 5.0.0 consolidou ~48 sub-packages em apenas 2 packages:

        ANTES (v4.x)                                DEPOIS (v5.0.0)
        ──────────────────────────────────────────────────────────────────────
        br.com.swconsultoria.nfe.schema_4.enviNFe    br.com.swconsultoria.nfe.schemas
        br.com.swconsultoria.nfe.schema_4.consSitNFe  br.com.swconsultoria.nfe.schemas
        br.com.swconsultoria.nfe.schema_4.inutNFe     br.com.swconsultoria.nfe.schemas
        br.com.swconsultoria.nfe.schema_4.consReciNFe br.com.swconsultoria.nfe.schemas
        br.com.swconsultoria.nfe.schema_4.consStatServ br.com.swconsultoria.nfe.schemas
        br.com.swconsultoria.nfe.schema.consCad       br.com.swconsultoria.nfe.schemas
        ...demais schema_4/* e schema/* (exceto Suframa)

        br.com.swconsultoria.nfe.schema.envcce.*      br.com.swconsultoria.nfe.schemas_eventos
        br.com.swconsultoria.nfe.schema.envEventoCancNFe.* br.com.swconsultoria.nfe.schemas_eventos
        ...demais eventos

    Classes de eventos foram renomeadas para evitar colisão. Exemplos:
        TEnvEvento  (cancelamento)  → TEnvEventoCancelamento
        TRetEnvEvento (cce)         → TRetEnvEventoCartaCorrecao
        TEvento (generico)          → TEventoGenerico
        etc.

    Este script faz substituições de texto em arquivos .java e .kt.
    Recomenda-se fazer backup (git) antes de rodar.

.PARAMETER Path
    Pasta raiz dos fontes a migrar. Default: diretório atual.

.PARAMETER DryRun
    Apenas lista os arquivos que seriam modificados, sem alterar.

.EXAMPLE
    pwsh scripts/migrate-to-v5.ps1 -Path src/main/java
    pwsh scripts/migrate-to-v5.ps1 -DryRun
#>

[CmdletBinding()]
param(
    [string]$Path = ".",
    [switch]$DryRun
)

$ErrorActionPreference = 'Stop'

Write-Host ""
Write-Host "=== java-nfe v4 → v5 Migration Script ===" -ForegroundColor Cyan
Write-Host ""
if ($DryRun) {
    Write-Host "Modo DRY-RUN: nenhum arquivo sera modificado." -ForegroundColor Yellow
    Write-Host ""
}

# ─────────────────────────────────────────────────────────
# Mapeamento de pacotes (ordem importa: mais especifico primeiro)
# ─────────────────────────────────────────────────────────
$packageMap = [ordered]@{
    # schema_4 → schemas
    'br\.com\.swconsultoria\.nfe\.schema_4\.enviNFe\.'        = 'br.com.swconsultoria.nfe.schemas.'
    'br\.com\.swconsultoria\.nfe\.schema_4\.consReciNFe\.'    = 'br.com.swconsultoria.nfe.schemas.'
    'br\.com\.swconsultoria\.nfe\.schema_4\.consSitNFe\.'     = 'br.com.swconsultoria.nfe.schemas.'
    'br\.com\.swconsultoria\.nfe\.schema_4\.consStatServ\.'   = 'br.com.swconsultoria.nfe.schemas.'
    'br\.com\.swconsultoria\.nfe\.schema_4\.inutNFe\.'        = 'br.com.swconsultoria.nfe.schemas.'
    'br\.com\.swconsultoria\.nfe\.schema_4\.comune?\.'        = 'br.com.swconsultoria.nfe.schemas.'
    'br\.com\.swconsultoria\.nfe\.schema_4\.[^.]+\.'          = 'br.com.swconsultoria.nfe.schemas.'
    # schema.consCad / distDFe / resNFe → schemas
    'br\.com\.swconsultoria\.nfe\.schema\.consCad\.'          = 'br.com.swconsultoria.nfe.schemas.'
    'br\.com\.swconsultoria\.nfe\.schema\.distDFe\.'          = 'br.com.swconsultoria.nfe.schemas.'
    'br\.com\.swconsultoria\.nfe\.schema\.resNFe\.'           = 'br.com.swconsultoria.nfe.schemas.'
    'br\.com\.swconsultoria\.nfe\.schema\.comum\.'            = 'br.com.swconsultoria.nfe.schemas.'
    # schema.cce → schemas_eventos  (carta correcao)
    'br\.com\.swconsultoria\.nfe\.schema\.envcce\.'           = 'br.com.swconsultoria.nfe.schemas_eventos.'
    'br\.com\.swconsultoria\.nfe\.schema\.cce\.'              = 'br.com.swconsultoria.nfe.schemas_eventos.'
    # schema.envEventoCancNFe → schemas_eventos
    'br\.com\.swconsultoria\.nfe\.schema\.envEventoCancNFe\.' = 'br.com.swconsultoria.nfe.schemas_eventos.'
    'br\.com\.swconsultoria\.nfe\.schema\.eventoCancNFe\.'    = 'br.com.swconsultoria.nfe.schemas_eventos.'
    # schema.envEventoCancSubst → schemas_eventos
    'br\.com\.swconsultoria\.nfe\.schema\.envEventoCancSubst\.' = 'br.com.swconsultoria.nfe.schemas_eventos.'
    # schema.envConfRecebto → schemas_eventos (manifestacao)
    'br\.com\.swconsultoria\.nfe\.schema\.envConfRecebto\.'   = 'br.com.swconsultoria.nfe.schemas_eventos.'
    'br\.com\.swconsultoria\.nfe\.schema\.confRecebto\.'      = 'br.com.swconsultoria.nfe.schemas_eventos.'
    # schema.envEpec → schemas_eventos
    'br\.com\.swconsultoria\.nfe\.schema\.envEpec\.'          = 'br.com.swconsultoria.nfe.schemas_eventos.'
    'br\.com\.swconsultoria\.nfe\.schema\.epec\.'             = 'br.com.swconsultoria.nfe.schemas_eventos.'
    # schema.envEventoAtorInteressado → schemas_eventos
    'br\.com\.swconsultoria\.nfe\.schema\.envEventoAtorInteressado\.' = 'br.com.swconsultoria.nfe.schemas_eventos.'
    # schema.eventoGenerico → schemas_eventos
    'br\.com\.swconsultoria\.nfe\.schema\.eventoGenerico\.'   = 'br.com.swconsultoria.nfe.schemas_eventos.'
    # schema.envEventoEConf → schemas_eventos
    'br\.com\.swconsultoria\.nfe\.schema\.envEventoEConf\.'   = 'br.com.swconsultoria.nfe.schemas_eventos.'
    'br\.com\.swconsultoria\.nfe\.schema\.envEventoCancEConf\.' = 'br.com.swconsultoria.nfe.schemas_eventos.'
    # schema.envEventoInsucessoNFe → schemas_eventos
    'br\.com\.swconsultoria\.nfe\.schema\.envEventoInsucessoNFe\.'    = 'br.com.swconsultoria.nfe.schemas_eventos.'
    'br\.com\.swconsultoria\.nfe\.schema\.envEventoCancInsucessoNFe\.' = 'br.com.swconsultoria.nfe.schemas_eventos.'
    # schema.envEventoEntregaNFe → schemas_eventos
    'br\.com\.swconsultoria\.nfe\.schema\.envEventoEntregaNFe\.'     = 'br.com.swconsultoria.nfe.schemas_eventos.'
    'br\.com\.swconsultoria\.nfe\.schema\.envEventoCancEntregaNFe\.' = 'br.com.swconsultoria.nfe.schemas_eventos.'
    # qualquer schema.* restante → schemas_eventos (catch-all para outros eventos)
    'br\.com\.swconsultoria\.nfe\.schema\.[^.]+\.'            = 'br.com.swconsultoria.nfe.schemas_eventos.'
}

# ─────────────────────────────────────────────────────────
# Mapeamento de classes renomeadas em schemas_eventos
# (TEnvEvento, TEvento, TRetEnvEvento, TRetEvento, TProcEvento — todos ambiguos)
# ─────────────────────────────────────────────────────────
$classRenames = [ordered]@{
    # Cancelamento
    'TEnvEventoCancNFe'            = 'TEnvEventoCancelamento'
    'TRetEnvEventoCancNFe'         = 'TRetEnvEventoCancelamento'
    # Carta de Correcao (CCe)
    'TEnvEventoCCe'                = 'TEnvEventoCartaCorrecao'
    'TRetEnvEventoCCe'             = 'TRetEnvEventoCartaCorrecao'
    # EPEC
    'TEnvEventoEPEC'               = 'TEnvEventoEpec'
    'TRetEnvEventoEPEC'            = 'TRetEnvEventoEpec'
    # Manifestacao (confRecebto)
    'TEnvEventoConfRecebto'        = 'TEnvEventoManifestacao'
    'TRetEnvEventoConfRecebto'     = 'TRetEnvEventoManifestacao'
    # Cancelamento Substituicao
    'TEnvEventoCancSubst'          = 'TEnvEventoCancelamentoSubstituicao'
    'TRetEnvEventoCancSubst'       = 'TRetEnvEventoCancelamentoSubstituicao'
    # Ator Interessado
    'TEnvEventoAtorInt'            = 'TEnvEventoAtorInteressado'
    'TRetEnvEventoAtorInt'         = 'TRetEnvEventoAtorInteressado'
    # ECONF
    'TEnvEventoEConf'              = 'TEnvEventoConciliacaoFinanceira'
    'TRetEnvEventoEConf'           = 'TRetEnvEventoConciliacaoFinanceira'
    'TEnvEventoCancEConf'          = 'TEnvEventoCancelamentoConciliacaoFinanceira'
    'TRetEnvEventoCancEConf'       = 'TRetEnvEventoCancelamentoConciliacaoFinanceira'
    # Insucesso entrega
    'TEnvEventoInsucessoNFe'       = 'TEnvEventoInsucessoEntrega'
    'TRetEnvEventoInsucessoNFe'    = 'TRetEnvEventoInsucessoEntrega'
    'TEnvEventoCancInsucessoNFe'   = 'TEnvEventoCancelamentoInsucessoEntrega'
    'TRetEnvEventoCancInsucessoNFe'= 'TRetEnvEventoCancelamentoInsucessoEntrega'
    # Comprovante entrega
    'TEnvEventoEntregaNFe'         = 'TEnvEventoComprovanteEntrega'
    'TRetEnvEventoEntregaNFe'      = 'TRetEnvEventoComprovanteEntrega'
    'TEnvEventoCancEntregaNFe'     = 'TEnvEventoCancelamentoComprovanteEntrega'
    'TRetEnvEventoCancEntregaNFe'  = 'TRetEnvEventoCancelamentoComprovanteEntrega'
}

# ─────────────────────────────────────────────────────────
# Processar arquivos
# ─────────────────────────────────────────────────────────
$files = Get-ChildItem -Path $Path -Recurse -Include '*.java','*.kt' -File |
    Where-Object { $_.FullName -notmatch '[\\/]schemas_?eventos?[\\/]|[\\/]schemas[\\/]|[\\/]schema[\\/]eventoSuframa' }

$totalModified = 0

foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw -Encoding UTF8
    $modified = $content

    # 1) Substituir packages
    foreach ($pattern in $packageMap.Keys) {
        $replacement = $packageMap[$pattern]
        $modified = $modified -replace $pattern, $replacement
    }

    # 2) Substituir nomes de classes renomeadas (só em linhas de import ou uso)
    foreach ($oldName in $classRenames.Keys) {
        $newName = $classRenames[$oldName]
        $modified = $modified -replace "\b$oldName\b", $newName
    }

    if ($modified -ne $content) {
        $totalModified++
        if ($DryRun) {
            Write-Host "  [DRY] $($file.FullName)" -ForegroundColor Yellow
        } else {
            Set-Content $file.FullName -Value $modified -Encoding UTF8 -NoNewline
            Write-Host "  [OK]  $($file.FullName)" -ForegroundColor Green
        }
    }
}

Write-Host ""
if ($DryRun) {
    Write-Host "$totalModified arquivo(s) seriam modificados." -ForegroundColor Yellow
} else {
    Write-Host "$totalModified arquivo(s) modificados." -ForegroundColor Green
}
Write-Host ""
Write-Host "IMPORTANTE: Revise manualmente os seguintes casos:" -ForegroundColor Cyan
Write-Host "  - Usos de TEnvEvento/TRetEnvEvento/TEvento/TRetEvento sem prefixo de evento"
Write-Host "    (agora cada evento tem sua propria classe: TEnvEventoCancelamento, etc.)"
Write-Host "  - Chamadas de Nfe.cancelarNfe(TEnvEvento) → Nfe.cancelarNfe(TEnvEventoCancelamento)"
Write-Host "  - Chamadas de Nfe.cce(TEnvEvento) → Nfe.cce(TEnvEventoCartaCorrecao)"
Write-Host "  - XmlNfeUtil.xmlToObject(xml, T*.class) — certifique-se de usar a classe correta"
Write-Host ""
Write-Host "Apos migrar, execute:" -ForegroundColor DarkGray
Write-Host "  mvn test-compile   # verificar compilacao"
Write-Host "  mvn test           # verificar testes"
