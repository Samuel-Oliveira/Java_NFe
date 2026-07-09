#Requires -Version 7.0
<#
.SYNOPSIS
    Migra um projeto consumidor da estrutura antiga do java-nfe (v4.00.x com
    sub-packages schema.X / schema_4.X) para a nova estrutura consolidada
    (schemas e schemas_eventos) e bumpa a versao no pom.xml.

.DESCRIPTION
    A nova versao consolidou ~48 sub-packages em apenas 2:

        ANTES                                            DEPOIS
        ────────────────────────────────────────────────────────────────────────
        br.com.swconsultoria.nfe.schema_4.X.*            br.com.swconsultoria.nfe.schemas.*
        br.com.swconsultoria.nfe.schema.consCad.*        br.com.swconsultoria.nfe.schemas.*
        br.com.swconsultoria.nfe.schema.resnfe.ResNFe    br.com.swconsultoria.nfe.schemas.ResNFe
        br.com.swconsultoria.nfe.schema.retdistdfeint.*  br.com.swconsultoria.nfe.schemas.*

        br.com.swconsultoria.nfe.schema.envcce.*         br.com.swconsultoria.nfe.schemas_eventos.*
        br.com.swconsultoria.nfe.schema.envEventoCancNFe.* br.com.swconsultoria.nfe.schemas_eventos.*
        ...todos os demais schema.*Evento*               br.com.swconsultoria.nfe.schemas_eventos.*

    Como o pacote unificado evitou colisao renomeando as classes ambiguas,
    o script tambem renomeia esses nomes simples no codigo:

        TEnvEvento (de envEventoCancNFe)    → TEnvEventoCancelamento
        TEnvEvento (de envcce)              → TEnvEventoCartaCorrecao
        TRetEnvEvento (de envConfRecebto)   → TRetEnvEventoManifestacao
        TretEvento  (lowercase em envcce)   → TRetEventoCartaCorrecao
        TProcEvento (de consSitNFe)         → TProcEventoConsSitNFe
        ResEvento (resumo de DFe)           → ResumoEvento
        DetEvento (de evento110001 etc.)    → DetEvento110001 (codigo do evento)
        ...

    O que o script faz:
      1. (Opcional) Bumpa <java-nfe.version> no pom.xml
      2. Renomeia imports e referencias fully-qualified em arquivos .java/.kt
      3. Renomeia usos do nome simples (ex.: `TEnvEvento envio = ...`) baseado
         no import resultante de cada arquivo
      4. Renomeia ocorrencias de classes que mudaram de nome (ResEvento, etc.)

    BREAKING CHANGES de API que o script NAO consegue tratar automaticamente
    (revise apos rodar):

      * TProtNFe.InfProt.getDhRecbto() agora retorna String (era XMLGregorianCalendar).
        Se voce convertia esse valor para LocalDateTime, ajuste o parser.
      * Algumas classes geradas a partir de XSDs de eventos individuais (e110001,
        e112110, e211110 etc.) tem nomes novos no formato DetEvento<codigo>.
        Antes ficavam dispersas em sub-packages distintos.
      * O script NAO usa as classes antigas como wildcard se voce usava
        `import schema.X.*;` — revise esses imports manualmente.

    Idempotente: rodar duas vezes nao causa dano.

.PARAMETER ProjectRoot
    Caminho raiz do projeto a migrar (default: diretorio atual).

.PARAMETER OldVersion
    Versao atual no pom.xml a substituir (default: 4.00.51). Aceita expressao
    regular simples — passe a versao que esta no seu pom.

.PARAMETER NewVersion
    Versao alvo (default: 4.1.1).

.PARAMETER BumpPom
    Tambem bumpa <java-nfe.version>$OldVersion</java-nfe.version> para
    $NewVersion no pom.xml do projeto. Sem essa flag, so reescreve codigo.

.PARAMETER DryRun
    Mostra o que seria modificado sem alterar arquivos.

.EXAMPLE
    # Migracao completa: bumpa pom + reescreve codigo
    pwsh scripts/migrate.ps1 -ProjectRoot . -BumpPom

.EXAMPLE
    # Dry-run: ver o que mudaria
    pwsh scripts/migrate.ps1 -ProjectRoot D:\meu\projeto -DryRun

.EXAMPLE
    # Versao diferente da default
    pwsh scripts/migrate.ps1 -OldVersion 4.00.49 -NewVersion 4.1.1 -BumpPom
#>

[CmdletBinding()]
param(
    [string]$ProjectRoot = '.',
    [string]$OldVersion = '4.00.51',
    [string]$NewVersion = '4.1.1',
    [switch]$BumpPom,
    [switch]$DryRun
)

$ErrorActionPreference = 'Stop'
$ProjectRoot = (Resolve-Path $ProjectRoot).Path

if (-not (Test-Path $ProjectRoot)) { throw "ProjectRoot nao existe: $ProjectRoot" }

Write-Host ""
Write-Host "=== java-nfe consumer migration ===" -ForegroundColor Cyan
Write-Host "  Project : $ProjectRoot"
Write-Host "  Versao  : $OldVersion -> $NewVersion"
if ($DryRun) { Write-Host "  DRY-RUN : nenhum arquivo sera modificado" -ForegroundColor Yellow }
Write-Host ""

# ─────────────────────────────────────────────────────────
# Mapeamento explicito de imports: oldFQN -> newFQN
# (aplicado antes do fallback por regex; cobre os casos onde o nome simples
# precisa mudar — pois o catch-all generico nao saberia disso)
# ─────────────────────────────────────────────────────────
$ImportMap = [ordered]@{
    # Manifestacao do destinatario
    'br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento'       = 'br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoManifestacao'
    'br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento'    = 'br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoManifestacao'
    'br.com.swconsultoria.nfe.schema.envConfRecebto.TretEvento'       = 'br.com.swconsultoria.nfe.schemas_eventos.TRetEventoManifestacao'
    # Cancelamento de NFe
    'br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento'     = 'br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoCancelamento'
    'br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento'  = 'br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoCancelamento'
    'br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEvento'     = 'br.com.swconsultoria.nfe.schemas_eventos.TRetEventoCancelamento'
    # Carta de correcao (envcce)
    'br.com.swconsultoria.nfe.schema.envcce.TEnvEvento'               = 'br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoCartaCorrecao'
    'br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento'            = 'br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoCartaCorrecao'
    'br.com.swconsultoria.nfe.schema.envcce.TretEvento'               = 'br.com.swconsultoria.nfe.schemas_eventos.TRetEventoCartaCorrecao'
    # Evento generico
    'br.com.swconsultoria.nfe.schema.eventoGenerico.TEnvEvento'       = 'br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoGenerico'
    'br.com.swconsultoria.nfe.schema.eventoGenerico.TRetEnvEvento'    = 'br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoGenerico'
    # Resumo de DFe — nome da classe MUDOU
    'br.com.swconsultoria.nfe.schema.resevento.ResEvento'             = 'br.com.swconsultoria.nfe.schemas_eventos.ResumoEvento'
    # Resnfe, retdistdfeint
    'br.com.swconsultoria.nfe.schema.resnfe.ResNFe'                   = 'br.com.swconsultoria.nfe.schemas.ResNFe'
    'br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt'     = 'br.com.swconsultoria.nfe.schemas.RetDistDFeInt'
    # ConsSitNFe — TProcEvento renomeado para evitar colisao
    'br.com.swconsultoria.nfe.schema_4.consSitNFe.TProcEvento'        = 'br.com.swconsultoria.nfe.schemas.TProcEventoConsSitNFe'
    # Eventos individuais (codigo do evento como sufixo)
    'br.com.swconsultoria.nfe.schema.evento110001.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento110001'
    'br.com.swconsultoria.nfe.schema.evento112110.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento112110'
    'br.com.swconsultoria.nfe.schema.evento112120.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento112120'
    'br.com.swconsultoria.nfe.schema.evento112130.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento112130'
    'br.com.swconsultoria.nfe.schema.evento112140.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento112140'
    'br.com.swconsultoria.nfe.schema.evento112150.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento112150'
    'br.com.swconsultoria.nfe.schema.evento211110.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento211110'
    'br.com.swconsultoria.nfe.schema.evento211120.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento211120'
    'br.com.swconsultoria.nfe.schema.evento211124.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento211124'
    'br.com.swconsultoria.nfe.schema.evento211128.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento211128'
    'br.com.swconsultoria.nfe.schema.evento211130.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento211130'
    'br.com.swconsultoria.nfe.schema.evento211140.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento211140'
    'br.com.swconsultoria.nfe.schema.evento211150.DetEvento'          = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento211150'
}

# ─────────────────────────────────────────────────────────
# Fallback por regex (aplicado APOS o mapa explicito)
# ─────────────────────────────────────────────────────────
$FallbackPatterns = @(
    # schema_4.<subpacote>.<Classe> → schemas.<Classe>
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema_4\.consReciNFe\.';                Replacement = 'br.com.swconsultoria.nfe.schemas.' }
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema_4\.consSitNFe\.';                 Replacement = 'br.com.swconsultoria.nfe.schemas.' }
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema_4\.consStatServ\.';               Replacement = 'br.com.swconsultoria.nfe.schemas.' }
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema_4\.enviNFe\.';                    Replacement = 'br.com.swconsultoria.nfe.schemas.' }
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema_4\.inutNFe\.';                    Replacement = 'br.com.swconsultoria.nfe.schemas.' }
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema_4\.[^.]+\.';                      Replacement = 'br.com.swconsultoria.nfe.schemas.' }
    # schema.* base (consCad, distDFe, comum etc.) → schemas
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema\.consCad\.';                      Replacement = 'br.com.swconsultoria.nfe.schemas.' }
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema\.distDFe\.';                      Replacement = 'br.com.swconsultoria.nfe.schemas.' }
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema\.comum\.';                        Replacement = 'br.com.swconsultoria.nfe.schemas.' }
    # Eventos individuais por codigo: schema.eventoXXXXXX.DetEvento(.G<algo>)?
    # -> schemas_eventos.DetEvento<XXXXXX>(.G<algo>)? (inner classes preservadas)
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema\.evento(\d+)\.DetEvento\b';       Replacement = 'br.com.swconsultoria.nfe.schemas_eventos.DetEvento$1' }
    # Catch-all para schema.*Evento* (cobre eventos sem renomeio especifico)
    @{ Pattern = '\bbr\.com\.swconsultoria\.nfe\.schema\.[^.]+\.';                        Replacement = 'br.com.swconsultoria.nfe.schemas_eventos.' }
)

# Helpers
function Get-SimpleName([string]$fqn) { ($fqn -split '\.')[-1] }

# ─────────────────────────────────────────────────────────
# 1) Bump pom.xml (opcional)
# ─────────────────────────────────────────────────────────
if ($BumpPom) {
    $pomPath = Join-Path $ProjectRoot 'pom.xml'
    if (Test-Path $pomPath) {
        $pomContent = Get-Content -Path $pomPath -Raw -Encoding UTF8
        $oldTag = "<java-nfe.version>$OldVersion</java-nfe.version>"
        $newTag = "<java-nfe.version>$NewVersion</java-nfe.version>"
        if ($pomContent.Contains($oldTag)) {
            $pomContent = $pomContent -replace [regex]::Escape($oldTag), $newTag
            if (-not $DryRun) {
                Set-Content -Path $pomPath -Value $pomContent -Encoding UTF8 -NoNewline
            }
            Write-Host "  pom.xml : <java-nfe.version> $OldVersion -> $NewVersion" -ForegroundColor Green
        } elseif ($pomContent.Contains($newTag)) {
            Write-Host "  pom.xml : ja em $NewVersion (skip)" -ForegroundColor DarkGray
        } else {
            Write-Warning "pom.xml nao contem <java-nfe.version>$OldVersion</java-nfe.version> — bump pulado"
        }
    } else {
        Write-Warning "pom.xml nao encontrado em $ProjectRoot — bump pulado"
    }
}

# ─────────────────────────────────────────────────────────
# 2) Processar arquivos .java e .kt
# ─────────────────────────────────────────────────────────
$srcRoot = Join-Path $ProjectRoot 'src'
$rootForScan = if (Test-Path $srcRoot) { $srcRoot } else { $ProjectRoot }

$files = Get-ChildItem -Path $rootForScan -Recurse -Include '*.java','*.kt' -File -ErrorAction SilentlyContinue |
    # Excluir pacotes da propria lib (caso o usuario tenha clonado o java-nfe)
    Where-Object { $_.FullName -notmatch '[\\/]br[\\/]com[\\/]swconsultoria[\\/]nfe[\\/](schema|schemas|schemas_eventos|schemas_eventos)[\\/]' }

if (-not $files) {
    Write-Warning "Nenhum arquivo .java ou .kt encontrado em $rootForScan"
    return
}

$filesModified  = 0
$importsRewrite = 0
$simpleRenames  = 0
$resEventoRen   = 0

foreach ($file in $files) {
    $content   = Get-Content -Path $file.FullName -Raw -Encoding UTF8
    $original  = $content

    # Rastreia renomeacoes de nome simples para aplicar no corpo apos.
    $localRenames = @{}

    # 2a) Imports explicitos do mapa.
    foreach ($oldFqn in $ImportMap.Keys) {
        $newFqn = $ImportMap[$oldFqn]
        $oldImport = "import $oldFqn;"
        $newImport = "import $newFqn;"
        if ($content.Contains($oldImport)) {
            $content = $content.Replace($oldImport, $newImport)
            $importsRewrite++
        }
        # Mesmo se nao houve troca nesta execucao, registra rename simples se o
        # import novo ja esta no arquivo (idempotente: cobre re-execucao).
        $oldSimple = Get-SimpleName $oldFqn
        $newSimple = Get-SimpleName $newFqn
        if ($oldSimple -ne $newSimple -and $content.Contains("import $newFqn;")) {
            $localRenames[$oldSimple] = $newSimple
        }
    }

    # 2b) Fallback regex (schema_4.* / schema.X.* / eventoXXXXXX).
    foreach ($p in $FallbackPatterns) {
        $matches = [regex]::Matches($content, $p.Pattern)
        if ($matches.Count -gt 0) {
            $content = [regex]::Replace($content, $p.Pattern, $p.Replacement)
            $importsRewrite += $matches.Count
        }
    }

    # 2c) Substituir FQN inline para cada entry do mapa explicito
    #     (testes/exemplos as vezes usam FQN sem import).
    foreach ($oldFqn in $ImportMap.Keys) {
        $newFqn = $ImportMap[$oldFqn]
        $fqnPattern = "(?<![\w.])$([regex]::Escape($oldFqn))(?![\w])"
        $matches = [regex]::Matches($content, $fqnPattern)
        if ($matches.Count -gt 0) {
            $content = [regex]::Replace($content, $fqnPattern, $newFqn)
            $importsRewrite += $matches.Count
        }
    }

    # 2d) Renomeio do nome de classe ResEvento -> ResumoEvento (palavra inteira).
    $resPattern = '\bResEvento\b'
    $resCount   = ([regex]::Matches($content, $resPattern)).Count
    if ($resCount -gt 0) {
        $content = [regex]::Replace($content, $resPattern, 'ResumoEvento')
        $resEventoRen += $resCount
    }

    # 2e) Renomear usos do nome simples ja "presentes" no arquivo (baseado em
    #     imports atuais). Ordena por tamanho desc para evitar conflito de prefixo.
    $sortedSimple = $localRenames.Keys | Sort-Object -Property Length -Descending
    foreach ($oldSimple in $sortedSimple) {
        $newSimple = $localRenames[$oldSimple]
        # Lookbehind impede casar campos qualificados como `Foo.TEnvEvento`.
        $simplePattern = "(?<![.\w])$([regex]::Escape($oldSimple))\b"
        $simpleCount = ([regex]::Matches($content, $simplePattern)).Count
        if ($simpleCount -gt 0) {
            $content = [regex]::Replace($content, $simplePattern, $newSimple)
            $simpleRenames += $simpleCount
        }
    }

    if ($content -ne $original) {
        $filesModified++
        if ($DryRun) {
            Write-Host "  [DRY] $($file.FullName)" -ForegroundColor Yellow
        } else {
            Set-Content -Path $file.FullName -Value $content -Encoding UTF8 -NoNewline
        }
    }
}

# ─────────────────────────────────────────────────────────
# Sumario
# ─────────────────────────────────────────────────────────
Write-Host ""
Write-Host "  Arquivos modificados      : $filesModified"
Write-Host "  Imports/FQN reescritos     : $importsRewrite"
Write-Host "  Nomes simples renomeados   : $simpleRenames"
Write-Host "  ResEvento -> ResumoEvento  : $resEventoRen"
Write-Host ""

# Avisos sobre o que o script NAO cobre — usuario precisa revisar manualmente.
Write-Host "Revise manualmente:" -ForegroundColor Cyan
Write-Host "  - TProtNFe.InfProt.getDhRecbto() agora retorna String (era XMLGregorianCalendar)."
Write-Host "  - Imports do tipo `import schema.X.*;` (wildcard) — o script nao trata."
Write-Host "  - Codigo que comparava classes via Class.getName() ou reflection — os FQN mudaram."
Write-Host ""
Write-Host "Apos migrar:" -ForegroundColor DarkGray
Write-Host "  mvn test-compile     # verificar compilacao"
Write-Host "  mvn test             # verificar testes"
