# Guia de migração — impressão Jasper → Thymeleaf

A versão seguinte à `4.3.1` **remove a impressão Jasper**: `ImpressaoNfeUtil`, `ImpressaoDTO`, `JasperNFeEnum`, os
`.jrxml`/`.jasper` e as dependências `jasperreports`, `jasperreports-fonts` e `barbecue`. A impressão oficial é a do
pacote `br.com.swconsultoria.nfe.impressao.v2` (Thymeleaf → PDF), que já cobre os três documentos da v1:

| Antes (Jasper) | Depois (v2) |
|---|---|
| `ImpressaoNfeUtil.impressaoPadraoNFe(xml)` | `ImpressaoDanfeV2Util.impressaoPadraoNFe(xml)` |
| `ImpressaoNfeUtil.impressaoPadraoNFCe(xml, urlConsulta)` | `ImpressaoDanfeV2Util.impressaoPadraoNFCe(xml)` (a URL vem do `infNFeSupl/urlChave`) |
| `ImpressaoNfeUtil.impressaoPadraoCCe(xml)` | `ImpressaoDanfeV2Util.impressaoPadraoCCe(xml)` |
| `ImpressaoDTO` | `ImpressaoDanfeV2DTO` |
| `ImpressaoNfeUtil.impressaoPdfArquivo / impressaoPdfByte / impressaoHtml / impressaoDireta` | os métodos de mesmo nome em `ImpressaoDanfeV2Util` |
| `impressaoPreview` (`JasperViewer`) | não existe: gere o PDF e abra num visualizador |
| `impressaoDireta(..., SimplePrintServiceExporterConfiguration)` | não existe: gere o PDF e imprima com PDFBox/`PrinterJob` |
| `setJasper(...)` / `setPathExpression(...)` | `setTemplate("caminho/meu-template.html")`; um `.jasper` próprio precisa ser refeito em Thymeleaf |
| `getParametros().put("Logo", stream)` | `setLogo(stream)` |
| `throws JRException, ...` | `DanfeException` (unchecked) |

As dependências de impressão (`thymeleaf`, `openhtmltopdf-pdfbox`, `zxing core` e `zxing javase`) deixam de ser
`provided` e vêm por transitividade com o `java-nfe`: o `pom.xml` do projeto consumidor não precisa declará-las, e
`jasperreports`, `jasperreports-fonts` e `barbecue` podem sair. Quem não imprime e não quer o peso pode excluí-las
([docs/MANUAL-DANFE-V2.md](docs/MANUAL-DANFE-V2.md), seção 2.1). Quem depende de um `.jasper` próprio pode ficar na
`4.3.1` até refazer o leiaute. O manual traz os leiautes (DANFE A4, DANFE em bobina da NFC-e e do Simplificado Tipo
2, CC-e), a personalização e os testes de aceitação.

---

# Guia de migração — v4.00.* → v4.1.*

> 💡 **Resumo rápido:** rode `pwsh scripts/migrate.ps1 -ProjectRoot . -BumpPom` (ou o equivalente bash) no seu projeto consumidor da lib, depois `mvn test-compile`. Os detalhes abaixo cobrem o que muda e o que o script não consegue migrar automaticamente.

## O que mudou

A v4.1.* consolidou ~48 sub-packages JAXB em **2 packages**:

| Antes (v4.00.*)                                               | Depois (v4.1.*)                              |
|---------------------------------------------------------------|----------------------------------------------|
| `br.com.swconsultoria.nfe.schema_4.enviNFe.*`                 | `br.com.swconsultoria.nfe.schemas.*`         |
| `br.com.swconsultoria.nfe.schema_4.consReciNFe.*`             | `br.com.swconsultoria.nfe.schemas.*`         |
| `br.com.swconsultoria.nfe.schema_4.consSitNFe.*`              | `br.com.swconsultoria.nfe.schemas.*`         |
| `br.com.swconsultoria.nfe.schema_4.consStatServ.*`            | `br.com.swconsultoria.nfe.schemas.*`         |
| `br.com.swconsultoria.nfe.schema_4.inutNFe.*`                 | `br.com.swconsultoria.nfe.schemas.*`         |
| `br.com.swconsultoria.nfe.schema_4.comum.*`                   | `br.com.swconsultoria.nfe.schemas.*`         |
| `br.com.swconsultoria.nfe.schema.consCad.*`                   | `br.com.swconsultoria.nfe.schemas.*`         |
| `br.com.swconsultoria.nfe.schema.distDFe.*`                   | `br.com.swconsultoria.nfe.schemas.*`         |
| `br.com.swconsultoria.nfe.schema.resNFe.*`                    | `br.com.swconsultoria.nfe.schemas.*`         |
| `br.com.swconsultoria.nfe.schema.comum.*`                     | `br.com.swconsultoria.nfe.schemas.*`         |
| `br.com.swconsultoria.nfe.schema.cce.*`                       | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envcce.*`                    | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envEventoCancNFe.*`          | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.eventoCancNFe.*`             | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envEventoCancSubst.*`        | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envConfRecebto.*`            | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.confRecebto.*`               | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envEpec.*`                   | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.eventoGenerico.*`            | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.*`  | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envEventoEConf.*`            | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envEventoCancEConf.*`        | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envEventoInsucessoNFe.*`     | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envEventoCancInsucessoNFe.*` | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envEventoEntregaNFe.*`       | `br.com.swconsultoria.nfe.schemas_eventos.*` |
| `br.com.swconsultoria.nfe.schema.envEventoCancEntregaNFe.*`   | `br.com.swconsultoria.nfe.schemas_eventos.*` |

## Classes renomeadas em `schemas_eventos`

Como todos os eventos agora vivem no mesmo package, os nomes genéricos colideriam. As classes foram renomeadas por evento:

| Classe v4.00.*                  | Classe v4.1.*                                    | Evento |
|---------------------------------|--------------------------------------------------|---|
| `TEnvEventoCancNFe`             | `TEnvEventoCancelamento`                         | Cancelamento |
| `TRetEnvEventoCancNFe`          | `TRetEnvEventoCancelamento`                      | Cancelamento |
| `TEnvEventoCCe`                 | `TEnvEventoCartaCorrecao`                        | Carta de Correção |
| `TRetEnvEventoCCe`              | `TRetEnvEventoCartaCorrecao`                     | Carta de Correção |
| `TEnvEventoEPEC`                | `TEnvEventoEpec`                                 | EPEC |
| `TRetEnvEventoEPEC`             | `TRetEnvEventoEpec`                              | EPEC |
| `TEnvEventoConfRecebto`         | `TEnvEventoManifestacao`                         | Manifestação Destinatário |
| `TRetEnvEventoConfRecebto`      | `TRetEnvEventoManifestacao`                      | Manifestação Destinatário |
| `TEnvEventoCancSubst`           | `TEnvEventoCancelamentoSubstituicao`             | Cancelamento por Substituição |
| `TRetEnvEventoCancSubst`        | `TRetEnvEventoCancelamentoSubstituicao`          | Cancelamento por Substituição |
| `TEnvEventoAtorInt`             | `TEnvEventoAtorInteressado`                      | Ator Interessado |
| `TRetEnvEventoAtorInt`          | `TRetEnvEventoAtorInteressado`                   | Ator Interessado |
| `TEnvEventoEConf`               | `TEnvEventoConciliacaoFinanceira`                | ECONF (Conciliação Financeira) |
| `TRetEnvEventoEConf`            | `TRetEnvEventoConciliacaoFinanceira`             | ECONF |
| `TEnvEventoCancEConf`           | `TEnvEventoCancelamentoConciliacaoFinanceira`    | Cancelamento ECONF |
| `TRetEnvEventoCancEConf`        | `TRetEnvEventoCancelamentoConciliacaoFinanceira` | Cancelamento ECONF |
| `TEnvEventoInsucessoNFe`        | `TEnvEventoInsucessoEntrega`                     | Insucesso Entrega |
| `TRetEnvEventoInsucessoNFe`     | `TRetEnvEventoInsucessoEntrega`                  | Insucesso Entrega |
| `TEnvEventoCancInsucessoNFe`    | `TEnvEventoCancelamentoInsucessoEntrega`         | Canc. Insucesso Entrega |
| `TRetEnvEventoCancInsucessoNFe` | `TRetEnvEventoCancelamentoInsucessoEntrega`      | Canc. Insucesso Entrega |
| `TEnvEventoEntregaNFe`          | `TEnvEventoComprovanteEntrega`                   | Comprovante Entrega |
| `TRetEnvEventoEntregaNFe`       | `TRetEnvEventoComprovanteEntrega`                | Comprovante Entrega |
| `TEnvEventoCancEntregaNFe`      | `TEnvEventoCancelamentoComprovanteEntrega`       | Canc. Comprovante Entrega |
| `TRetEnvEventoCancEntregaNFe`   | `TRetEnvEventoCancelamentoComprovanteEntrega`    | Canc. Comprovante Entrega |

> Nota: as classes internas (`TEvento.InfEvento`, `TRetEnvEvento.RetEvento`, etc.) seguem o mesmo padrão — o prefixo do nome externo muda.

## Outras classes que mudaram de nome

| Antes (v4.00.*)                                          | Depois (v4.1.*)                                       | Motivo |
|----------------------------------------------------------|-------------------------------------------------------|--------|
| `br.com.swconsultoria.nfe.schema.resevento.ResEvento`    | `br.com.swconsultoria.nfe.schemas_eventos.ResumoEvento` | Nome da classe foi normalizado |
| `br.com.swconsultoria.nfe.schema_4.consSitNFe.TProcEvento` | `br.com.swconsultoria.nfe.schemas.TProcEventoConsSitNFe` | Conflito com `TProcEvento*` de `schemas_eventos` |
| `br.com.swconsultoria.nfe.schema.envcce.TretEvento`      | `br.com.swconsultoria.nfe.schemas_eventos.TRetEventoCartaCorrecao` | Padronização de capitalização + sufixo de evento |
| `br.com.swconsultoria.nfe.schema.envConfRecebto.TretEvento` | `br.com.swconsultoria.nfe.schemas_eventos.TRetEventoManifestacao` | idem |

## Eventos da Reforma Tributária (e similares)

Cada XSD individual de evento (`e110001`, `e112110-150`, `e211110-150`, `e212110-120`, `e412120-130`, `e210200-240`, `110150`) agora gera uma classe `DetEvento<código>` top-level em `schemas_eventos`. Cada uma preserva `@XmlRootElement` (suporta marshalling direto via JAXB).

| Antes                                                                    | Depois                                                              |
|--------------------------------------------------------------------------|---------------------------------------------------------------------|
| `br.com.swconsultoria.nfe.schema.evento110001.DetEvento`                | `br.com.swconsultoria.nfe.schemas_eventos.DetEvento110001`         |
| `br.com.swconsultoria.nfe.schema.evento112110.DetEvento`                | `br.com.swconsultoria.nfe.schemas_eventos.DetEvento112110`         |
| `br.com.swconsultoria.nfe.schema.evento112110.DetEvento.GConsumo`       | `br.com.swconsultoria.nfe.schemas_eventos.DetEvento112110.GConsumo` |
| `br.com.swconsultoria.nfe.schema.evento211110.DetEvento.GCredPres`      | `br.com.swconsultoria.nfe.schemas_eventos.DetEvento211110.GCredPres` |
| ...e assim por diante para todos os códigos                             | ...                                                                  |

> O script de migração trata automaticamente esses casos via regex (`schema.eventoXXXXXX.DetEvento(.G<sub>)?` → `schemas_eventos.DetEvento<XXXXXX>(.G<sub>)?`).

## Como migrar automaticamente

Faça backup (commit ou branch) antes:

```bash
git checkout -b migra
```

### Windows / Linux / macOS (PowerShell — recomendado):

```powershell
# Simulacao (sem alterar arquivos):
pwsh scripts/migrate.ps1 -ProjectRoot . -DryRun

# Aplicar nas fontes + bumpa <java-nfe.version> no pom.xml:
pwsh scripts/migrate.ps1 -ProjectRoot . -BumpPom

# Se a sua versao atual nao e' 4.00.51:
pwsh scripts/migrate.ps1 -ProjectRoot . -OldVersion 4.00.49 -BumpPom
```

### Linux / macOS (bash — alternativa):

```bash
# Simulacao (so reescreve codigo, nao bumpa pom):
bash scripts/migrate.sh src/main/java --dry-run

# Aplicar:
bash scripts/migrate.sh src/main/java
bash scripts/migrate.sh src/test/java
```

> O `migrate.sh` é mais limitado: não bumpa o pom, não renomeia o nome simples no corpo (`TEnvEvento` → `TEnvEventoCancelamento` baseado no import) e não trata eventos individuais por código. Para projetos grandes, prefira o `migrate.ps1` (PowerShell 7+ roda em Linux e macOS via `pwsh`).

## ⚠️ Breaking change de API

| API | Antes (v4.00.51)                 | Depois (v4.1.*) | O que fazer |
|---|----------------------------------|---|---|
| `TProtNFe.InfProt.getDhRecbto()` | retornava `XMLGregorianCalendar` | retorna **`String`** (ISO 8601 com offset, ex. `"2024-01-15T10:30:00-03:00"`) | Atualize o caller. Para converter para `LocalDateTime`: `OffsetDateTime.parse(value).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()` |

> Outras assinaturas podem ter mudado em casos isolados. Após `mvn test-compile`, qualquer "incompatible types" indica diferença de assinatura.

## O que o script NÃO faz

Os scripts de migração fazem substituições de texto e regex. Os seguintes casos precisam de revisão manual:

1. **Imports wildcard `import br.com.swconsultoria.nfe.schema_4.*;`** — o script substitui o prefixo do package mas mantém o `*`. Se o wildcard importava de um único package e agora há ambiguidade, resolva os imports individualmente.

2. **`TUf` em `schemas_eventos`** — o package `schemas_eventos` tem seu próprio `TUf` (para eventos). O package `schemas` também tem `TUf` (para NFe). Se o código usava `TUf` de um package de evento, verifique que o import aponta para `schemas_eventos.TUf`.

3. **Reflection / `Class.forName(...)` / `getClass().getName()`** — se o código compara FQNs como string, atualize as strings.

4. **APIs que mudaram de tipo** — ver tabela "Breaking change de API" acima.

> Observação: o `migrate.ps1` JÁ trata automaticamente os usos do nome simples no corpo do arquivo. Ou seja, se um arquivo importava `TEnvEvento` de `schema.envEventoCancNFe`, o script reescreve `import` E todos os usos de `TEnvEvento` no arquivo para `TEnvEventoCancelamento`.

## Verificação pós-migração

```bash
mvn test-compile   # deve compilar sem erros
mvn test           # deve passar todos os testes (2157/2157)
```

Se `mvn test-compile` falhar, os erros `cannot find symbol` indicarão exatamente qual classe/método precisa de ajuste manual.
