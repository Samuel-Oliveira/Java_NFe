#Requires -Version 7.0
<#
.SYNOPSIS
    Regenera as classes JAXB do java-nfe v5.0.0 — DOIS packages apenas.

.DESCRIPTION
    Estrategia (v5.0.0):

    Package schemas (5 passes sequenciais, sem limpar entre eles):
      1) consSitNFe + retConsSitNFe    — cons-sit-nfe.xjb renomeia TEvento->TEventoConsSitNFe etc.
      2) nfe_v4.00 + enviNFe + todos os XSDs NFe principais (sobrescreve TProtNFe com versao leiauteNFe)
      3) consCad + retConsCad           — tiposBasico_v1.03 incluido aqui, tipos basicos ja existem
      4) distDFeInt + retDistDFeInt     — tiposDistDFe redefine tipos basicos, mas ja existem
      5) resNFe                         — apenas ResNFe class

    Package schemas_eventos (13 passes sequenciais, sem limpar entre eles):
      Um xjc por grupo de evento (cada leiaute*EventoXxx tem TEvento/TRetEvento conflitantes).
      Cada passe gera/sobrescreve apenas as classes do seu grupo.

    Sem uso de -episode entre passes (causa "SCD not matched" para tipos inexistentes no schema).
    Sem -b episode entre passes de schemas.

    Cross-platform: roda em Windows, Linux e macOS via PowerShell 7+. Requer JDK 8
    (xjc nao existe a partir do JDK 11).

.PARAMETER Jdk8Path
    Caminho do JDK 8. Se omitido, tenta env:JAVA_HOME_8 e caminhos convencionados.

.PARAMETER RemoveOrphans
    Apos regerar, deleta pastas de packages que estao em disco mas nao constam na tabela.

.EXAMPLE
    pwsh scripts/regenerate-jaxb.ps1
    pwsh scripts/regenerate-jaxb.ps1 -Jdk8Path D:\Dev\jdks\8
    pwsh scripts/regenerate-jaxb.ps1 -RemoveOrphans
#>

[CmdletBinding()]
param(
    [string]$Jdk8Path,
    [switch]$RemoveOrphans
)

$ErrorActionPreference = 'Stop'

$ProjectRoot  = (Resolve-Path "$PSScriptRoot/..").Path
$SchemasDir   = Join-Path $ProjectRoot 'schemas'
$JavaSrcDir   = Join-Path $ProjectRoot 'src/main/java'
$BindingsDir  = Join-Path $ProjectRoot 'scripts/bindings'
$BasePkg      = 'br.com.swconsultoria.nfe'

# ---------- Localizar xjc do JDK 8 ----------
function Find-Xjc {
    param([string]$Hint)
    $candidates = New-Object System.Collections.Generic.List[string]
    if ($Hint)            { $candidates.Add($Hint) }
    if ($env:JAVA_HOME_8) { $candidates.Add($env:JAVA_HOME_8) }
    if ($IsWindows) {
        $candidates.Add('D:\Dev\jdks\8')
        $candidates.Add('C:\Program Files\Java\jdk1.8.0')
        if ($env:USERPROFILE) { $candidates.Add((Join-Path $env:USERPROFILE '.jdks\corretto-1.8.0')) }
    } elseif ($IsLinux) {
        $candidates.Add('/usr/lib/jvm/java-8-openjdk-amd64')
        $candidates.Add('/usr/lib/jvm/temurin-8-jdk-amd64')
        $candidates.Add('/opt/jdk-8')
    } elseif ($IsMacOS) {
        $candidates.Add('/Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home')
        $candidates.Add('/Library/Java/JavaVirtualMachines/temurin-8.jdk/Contents/Home')
    }

    foreach ($jdk in $candidates) {
        if (-not $jdk) { continue }
        $bin = if ($IsWindows) { Join-Path $jdk 'bin/xjc.exe' } else { Join-Path $jdk 'bin/xjc' }
        if (Test-Path $bin) { return $bin }
    }
    throw "xjc do JDK 8 nao encontrado. Use -Jdk8Path '<caminho>' ou defina `$env:JAVA_HOME_8."
}

$xjc = Find-Xjc -Hint $Jdk8Path
Write-Host "xjc: $xjc" -ForegroundColor DarkGray

# ============================================================
# Pacotes CONHECIDOS (para deteccao de orfaos)
# ============================================================
$knownPackages = @(
    "$BasePkg.schemas"
    "$BasePkg.schemas_eventos"
    # Orfaos preservados (sem XSD, nao regerados)
    "$BasePkg.schema.eventoSuframaInternaliza"
    "$BasePkg.schema.eventoSuframaVistoria"
)

# ============================================================
# FUNCAO: roda xjc (NAO limpa pasta — passes sequenciais)
# ============================================================
function Invoke-XjcPass {
    param(
        [Parameter(Mandatory)][string]$Label,
        [Parameter(Mandatory)][string]$Package,
        [Parameter(Mandatory)][string[]]$XsdFiles,
        [string[]]$BindingFiles = @()
    )

    $pkgDir = Join-Path $JavaSrcDir ($Package.Replace('.', [IO.Path]::DirectorySeparatorChar))
    if (-not (Test-Path $pkgDir)) {
        New-Item -ItemType Directory -Path $pkgDir -Force | Out-Null
    }

    $xjcArgs = @('-extension', '-encoding', 'UTF-8', '-p', $Package, '-d', $JavaSrcDir, '-no-header')

    foreach ($bf in $BindingFiles) {
        if ($bf -and (Test-Path $bf)) {
            $xjcArgs += @('-b', $bf)
        } elseif ($bf) {
            Write-Warning "Binding nao encontrado (ignorando): $bf"
        }
    }

    $existingXsds = @()
    foreach ($xsd in $XsdFiles) {
        $fullPath = Join-Path $SchemasDir $xsd
        if (Test-Path $fullPath) {
            $existingXsds += $fullPath
        } else {
            Write-Host "  AVISO: XSD nao encontrado: $xsd (ignorando)" -ForegroundColor Yellow
        }
    }

    if ($existingXsds.Count -eq 0) {
        Write-Warning "Nenhum XSD valido para $Label — pulando."
        return
    }

    $xjcArgs += $existingXsds

    Write-Host "  -> $Label  ($($existingXsds.Count) XSDs)" -ForegroundColor Cyan
    & $xjc @xjcArgs
    if ($LASTEXITCODE -ne 0) { throw "xjc falhou para $Label (exit $LASTEXITCODE)" }
}

# ============================================================
# ETAPA 0: deletar packages antigos (exceto Suframa)
# ============================================================
Write-Host ""
Write-Host "=== Deletando packages antigos ===" -ForegroundColor Yellow

$oldDomains = @('schema', 'schema_4', 'comum')
foreach ($dom in $oldDomains) {
    $domPath = Join-Path $JavaSrcDir ($BasePkg.Replace('.', [IO.Path]::DirectorySeparatorChar)) $dom
    if (Test-Path $domPath) {
        # Preservar eventoSuframaInternaliza e eventoSuframaVistoria
        Get-ChildItem -Path $domPath -Directory | ForEach-Object {
            if ($_.Name -notin @('eventoSuframaInternaliza','eventoSuframaVistoria')) {
                Remove-Item -Path $_.FullName -Recurse -Force
                Write-Host "  Removido: $($_.FullName)" -ForegroundColor DarkGray
            } else {
                Write-Host "  Preservado (orfao Suframa): $($_.FullName)" -ForegroundColor Yellow
            }
        }
        # Remover arquivos .java na raiz do dominio (package-info etc.)
        Get-ChildItem -Path $domPath -Filter '*.java' -File | Remove-Item -Force
    }
}

# Limpar schemas e schemas_eventos para iniciar limpo
$schemasPath       = Join-Path $JavaSrcDir ($BasePkg.Replace('.', [IO.Path]::DirectorySeparatorChar)) 'schemas'
$schemasEventosPath = Join-Path $JavaSrcDir ($BasePkg.Replace('.', [IO.Path]::DirectorySeparatorChar)) 'schemas_eventos'
foreach ($p in @($schemasPath, $schemasEventosPath)) {
    if (Test-Path $p) {
        Get-ChildItem -Path $p -Filter '*.java' -File | Remove-Item -Force
        Write-Host "  Limpado: $p" -ForegroundColor DarkGray
    }
}

# ============================================================
# ETAPA 1: schemas — 5 passes sequenciais (sem episode)
# ============================================================
Write-Host ""
Write-Host "=== Gerando package: schemas ===" -ForegroundColor Yellow

# Pass 1: consSitNFe (renomeia TEvento->TEventoConsSitNFe via binding)
Invoke-XjcPass `
    -Label "schemas pass-1: consSitNFe" `
    -Package "$BasePkg.schemas" `
    -XsdFiles @('consSitNFe_v4.00.xsd', 'retConsSitNFe_v4.00.xsd') `
    -BindingFiles @((Join-Path $BindingsDir 'cons-sit-nfe.xjb'))

# Pass 2: consCad (TEndereco minimo — sera sobrescrita pelo pass NFe)
Invoke-XjcPass `
    -Label "schemas pass-2: consCad" `
    -Package "$BasePkg.schemas" `
    -XsdFiles @('consCad_v2.00.xsd', 'retConsCad_v2.00.xsd')

# Pass 3: distDFeInt (tiposDistDFe redefine tipos basicos — ja existem)
Invoke-XjcPass `
    -Label "schemas pass-3: distDFeInt" `
    -Package "$BasePkg.schemas" `
    -XsdFiles @('distDFeInt_v1.01.xsd', 'retDistDFeInt_v1.01.xsd')

# Pass 4: resNFe
Invoke-XjcPass `
    -Label "schemas pass-4: resNFe" `
    -Package "$BasePkg.schemas" `
    -XsdFiles @('resNFe_v1.01.xsd')

# Pass 5: NFe principal LAST — gera ObjectFactory abrangente e TEndereco completo
# (UF, cPais, xPais, fone) sobrescrevendo versoes anteriores de tipos compartilhados.
Invoke-XjcPass `
    -Label "schemas pass-5: NFe" `
    -Package "$BasePkg.schemas" `
    -XsdFiles @(
        'nfe_v4.00.xsd'
        'enviNFe_v4.00.xsd'
        'retEnviNFe_v4.00.xsd'
        'procNFe_v4.00.xsd'
        'consReciNFe_v4.00.xsd'
        'retConsReciNFe_v4.00.xsd'
        'consStatServ_v4.00.xsd'
        'retConsStatServ_v4.00.xsd'
        'inutNFe_v4.00.xsd'
        'retInutNFe_v4.00.xsd'
        'procInutNFe_v4.00.xsd'
    )


Write-Host "  schemas: OK" -ForegroundColor Green

# ============================================================
# ETAPA 2: schemas_eventos — 1 pass por grupo
# Cada leiauteEvento* define TEvento/TRetEvento conflitantes.
# Solucao: compilar cada grupo isoladamente (sequential overwrite).
# Nao usar -episode entre passes (causa SCD not matched).
# ============================================================
Write-Host ""
Write-Host "=== Gerando package: schemas_eventos ===" -ForegroundColor Yellow

# Grupo 1: Generico (leiauteEvento_v1.00.xsd)
Invoke-XjcPass `
    -Label "schemas_eventos pass-01: generico" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEvento_v1.00.xsd'
        'envEvento_v1.00.xsd'
        'retEnvEvento_v1.00.xsd'
        'procEventoNFe_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'generico.xjb'))

# Grupo 2: Cancelamento NFe (leiauteEventoCancNFe_v1.00.xsd)
Invoke-XjcPass `
    -Label "schemas_eventos pass-02: cancelamento" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEventoCancNFe_v1.00.xsd'
        'eventoCancNFe_v1.00.xsd'
        'envEventoCancNFe_v1.00.xsd'
        'retEnvEventoCancNFe_v1.00.xsd'
        'procEventoCancNFe_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'cancelamento.xjb'))

# Grupo 3: Cancelamento Substituicao (leiauteEventoCancSubst_v1.00.xsd)
Invoke-XjcPass `
    -Label "schemas_eventos pass-03: cancelamento-substituicao" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEventoCancSubst_v1.00.xsd'
        'eventoCancSubst_v1.00.xsd'
        'envEventoCancSubst_v1.00.xsd'
        'retEnvEventoCancSubst_v1.00.xsd'
        'procEventoCancSubst_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'cancelamento-substituicao.xjb'))

# Grupo 4: Carta de Correcao (leiauteCCe_v1.00.xsd — usa TretEvento lowercase)
Invoke-XjcPass `
    -Label "schemas_eventos pass-04: carta-correcao" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteCCe_v1.00.xsd'
        'CCe_v1.00.xsd'
        'envCCe_v1.00.xsd'
        'retEnvCCe_v1.00.xsd'
        'procCCeNFe_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'carta-correcao.xjb'))

# Grupo 5: EPEC (leiauteEPEC_v1.00.xsd)
Invoke-XjcPass `
    -Label "schemas_eventos pass-05: epec" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEPEC_v1.00.xsd'
        'EPEC_v1.00.xsd'
        'envEPEC_v1.00.xsd'
        'retEnvEPEC_v1.00.xsd'
        'procEPEC_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'epec.xjb'))

# Grupo 6: Manifestacao Destinatario (leiauteConfRecebto — usa TretEvento lowercase)
Invoke-XjcPass `
    -Label "schemas_eventos pass-06: manifestacao" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteConfRecebto_v1.00.xsd'
        'confRecebto_v1.00.xsd'
        'envConfRecebto_v1.00.xsd'
        'retEnvConfRecebto_v1.00.xsd'
        'procConfRecebtoNFe_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'manifestacao.xjb'))

# Grupo 7: Conciliacao Financeira (leiauteEventoEConf — usa TretEvento lowercase)
Invoke-XjcPass `
    -Label "schemas_eventos pass-07: conciliacao-financeira" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEventoEConf_v1.00.xsd'
        'EventoEConf_v1.00.xsd'
        'envEventoEConf_v1.00.xsd'
        'procEventoEConf_v1.00.xsd'
        'retEventoEConf_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'conciliacao-financeira.xjb'))

# Grupo 8: Cancelamento Conciliacao Financeira (leiauteEventoCancEConf — usa TretEvento lowercase)
Invoke-XjcPass `
    -Label "schemas_eventos pass-08: cancelamento-conciliacao-financeira" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEventoCancEConf_v1.00.xsd'
        'EventoCancEConf_v1.00.xsd'
        'envEventoCancEConf_v1.00.xsd'
        'procEventoCancEConf_v1.00.xsd'
        'retEventoCancEConf_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'cancelamento-conciliacao-financeira.xjb'))

# Grupo 9: Insucesso Entrega (leiauteEventoInsucessoNFe — usa TretEvento lowercase)
Invoke-XjcPass `
    -Label "schemas_eventos pass-09: insucesso-entrega" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEventoInsucessoNFe_v1.00.xsd'
        'EventoInsucessoNFe_v1.00.xsd'
        'envEventoInsucessoNFe_v1.00.xsd'
        'procEventoInsucessoNFe_v1.00.xsd'
        'retEventoInsucessoNFe_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'insucesso-entrega.xjb'))

# Grupo 10: Cancelamento Insucesso Entrega (leiauteEventoCancInsucessoNFe — usa TretEvento lowercase)
# NOTA: retEventoCancInsucessoNFe_v1.00.xsd inclui leiauteEventoInsucessoNFe (nao-Canc) causando
# conflito de tipos com leiauteEventoCancInsucessoNFe. Excluido desta compilacao.
Invoke-XjcPass `
    -Label "schemas_eventos pass-10: cancelamento-insucesso-entrega" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEventoCancInsucessoNFe_v1.00.xsd'
        'EventoCancInsucessoNFe_v1.00.xsd'
        'envEventoCancInsucessoNFe_v1.00.xsd'
        'procEventoCancInsucessoNFe_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'cancelamento-insucesso-entrega.xjb'))

# Grupo 11: Comprovante Entrega (leiauteEventoEntregaNFe — usa TretEvento lowercase)
Invoke-XjcPass `
    -Label "schemas_eventos pass-11: comprovante-entrega" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEventoEntregaNFe_v1.00.xsd'
        'EventoEntregaNFe_v1.00.xsd'
        'envEventoEntregaNFe_v1.00.xsd'
        'procEventoEntregaNFe_v1.00.xsd'
        'retEventoEntregaNFe_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'comprovante-entrega.xjb'))

# Grupo 12: Cancelamento Comprovante Entrega (leiauteEventoCancEntregaNFe — usa TretEvento lowercase)
Invoke-XjcPass `
    -Label "schemas_eventos pass-12: cancelamento-comprovante-entrega" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEventoCancEntregaNFe_v1.00.xsd'
        'EventoCancEntregaNFe_v1.00.xsd'
        'envEventoCancEntregaNFe_v1.00.xsd'
        'procEventoCancEntregaNFe_v1.00.xsd'
        'retEventoCancEntregaNFe_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'cancelamento-comprovante-entrega.xjb'))

# Grupo 13: Ator Interessado (leiauteEventoAtorInteressado_v1.00.xsd)
Invoke-XjcPass `
    -Label "schemas_eventos pass-13: ator-interessado" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @(
        'leiauteEventoAtorInteressado_v1.00.xsd'
        'eventoAtorInteressado_v1.00.xsd'
        'envEventoAtorInteressado_v1.00.xsd'
        'retEnvEventoAtorInteressado_v1.00.xsd'
        'procEventoAtorInteressado_v1.00.xsd'
    ) `
    -BindingFiles @((Join-Path $BindingsDir 'ator-interessado.xjb'))

# Grupo 14: Resumo Evento (resEvento_v1.01.xsd — standalone)
Invoke-XjcPass `
    -Label "schemas_eventos pass-14: res-evento" `
    -Package "$BasePkg.schemas_eventos" `
    -XsdFiles @('resEvento_v1.01.xsd') `
    -BindingFiles @((Join-Path $BindingsDir 'res-evento.xjb'))

Write-Host "  schemas_eventos: OK" -ForegroundColor Green

# ============================================================
# DETECCAO DE PACKAGES ORFAOS
# ============================================================
Write-Host ""
Write-Host "=== Deteccao de packages orfaos ===" -ForegroundColor Yellow

$basePkgPath = Join-Path $JavaSrcDir ($BasePkg.Replace('.', [IO.Path]::DirectorySeparatorChar))
$orphans     = New-Object 'System.Collections.Generic.List[string]'

# Verificar dominios antigos (que devem estar vazios ou apenas com Suframa)
foreach ($dom in @('schema', 'schema_4', 'comum')) {
    $domPath = Join-Path $basePkgPath $dom
    if (-not (Test-Path $domPath)) { continue }
    Get-ChildItem -Path $domPath -Directory | ForEach-Object {
        $pkgFqn = "$BasePkg.$dom.$($_.Name)"
        if (-not ($knownPackages -contains $pkgFqn)) {
            $orphans.Add($_.FullName)
        }
    }
}

if ($orphans.Count -gt 0) {
    Write-Host ""
    Write-Host "Pacotes ORFAOS (em disco, fora da tabela):" -ForegroundColor Yellow
    foreach ($o in $orphans) { Write-Host "  $o" -ForegroundColor Yellow }
    if ($RemoveOrphans) {
        Write-Host "Removendo..." -ForegroundColor DarkYellow
        foreach ($o in $orphans) { Remove-Item -Path $o -Recurse -Force }
        Write-Host "$($orphans.Count) pacote(s) orfao(s) removido(s)." -ForegroundColor DarkYellow
    } else {
        Write-Host "Para remover automaticamente: re-rode com -RemoveOrphans" -ForegroundColor DarkGray
    }
} else {
    Write-Host "Nenhum pacote orfao detectado." -ForegroundColor Green
}

Write-Host ""
Write-Host "Regeneracao v5.0.0 completa." -ForegroundColor Green
Write-Host "Proximo passo: mvn test-compile" -ForegroundColor DarkGray
