# Build & regeneração JAXB

A partir da versão **4.1.*** todos os tipos JAXB foram consolidados em **2 packages** (antes ~48):

| Package | Conteúdo |
|---|---|
| `br.com.swconsultoria.nfe.schemas` | NFe v4.00 (TNFe, TInutNFe, TRetConsReciNFe, TRetConsStatServ, TConsCad, TRetConsCad, TResNFe, TRetDistDFeInt, TEndereco completo, TUf, TUfEmi, ...) |
| `br.com.swconsultoria.nfe.schemas_eventos` | Todos os eventos SEFAZ (cancelamento, CCe, EPEC, manifestação, conciliação financeira, insucesso entrega, comprovante entrega, ator interessado, genérico) com classes renomeadas para evitar colisão |

Os packages antigos (`schema/`, `schema_4/`, `comum/`) foram **deletados**, exceto dois subpackages de eventos Suframa que não possuem XSD fonte conhecida (ver seção "Pacotes órfãos Suframa").

## Pré-requisitos

| Ferramenta | Por quê | Onde obter |
|---|---|---|
| **JDK 8** | O binário `xjc` (compilador JAXB) só vem nativo até o JDK 8 — foi removido no Java 9+ | [Adoptium Temurin 8](https://adoptium.net/temurin/releases/?version=8) |
| **PowerShell Core (`pwsh`) 7+** | Script único cross-platform | `winget install Microsoft.PowerShell` (Windows), `brew install powershell` (macOS), repo Microsoft (Linux) |
| **Maven 3.6+** | Build do projeto | qualquer |

### Como o script encontra o JDK 8

Em ordem:

1. Parâmetro `-Jdk8Path <caminho>` na linha de comando
2. Variável de ambiente `JAVA_HOME_8`
3. Caminhos padrão por SO:
   - Windows: `D:\Dev\jdks\8`, `C:\Program Files\Java\jdk1.8.0`, `~\.jdks\corretto-1.8.0`
   - Linux: `/usr/lib/jvm/java-8-openjdk-amd64`, `/usr/lib/jvm/temurin-8-jdk-amd64`, `/opt/jdk-8`
   - macOS: `/Library/Java/JavaVirtualMachines/{zulu-8,temurin-8}.jdk/Contents/Home`

Se nenhum bater, o script falha pedindo o caminho.

## Quando regerar

Sempre que mudar uma ou mais XSDs em [`schemas/`](schemas/) (Sefaz publicou nova versão, p.ex.).

Para mudanças no código de negócio (`src/main/java/**` **fora** dos packages `schemas/` e `schemas_eventos/`), **não** regere.

## Como rodar

Via Maven (recomendado — garante mesmo working dir):

```bash
mvn exec:exec@regenerate-jaxb
```

Ou direto:

```bash
pwsh scripts/regenerate-jaxb.ps1
# Com JDK customizado:
pwsh scripts/regenerate-jaxb.ps1 -Jdk8Path /opt/zulu-8
# Removendo packages órfãos após regenerar:
pwsh scripts/regenerate-jaxb.ps1 -RemoveOrphans
```

Saída esperada: 5 mensagens "schemas pass-N" + 14 mensagens "schemas_eventos pass-NN" + 26 mensagens "extraido: DetEvento<código>.java" (eventos individuais da Reforma Tributária) + "Regeneracao completa". Tempo total ~1-2 min.

## Estratégia de compilação: passes sequenciais

### Package `schemas` — 5 passes

Os 5 passes compilam **para o mesmo diretório de saída** sem limpar entre eles. O último pass a escrever um arquivo vence. **A ordem importa:**

| Pass | Label | XSDs | Binding | Observação |
|---|---|---|---|---|
| 1 | `consSitNFe` | `consSitNFe_v4.00.xsd`, `retConsSitNFe_v4.00.xsd` | `cons-sit-nfe.xjb` | Renomeia `TEvento→TEventoConsSitNFe`, `TRetEnvEvento→TRetEnvEventoConsSitNFe` etc. |
| 2 | `consCad` | `consCad_v2.00.xsd`, `retConsCad_v2.00.xsd` | — | Gera `TEndereco` mínimo (sem UF, sem cPais), será sobrescrita no pass 5 |
| 3 | `distDFeInt` | `distDFeInt_v1.01.xsd`, `retDistDFeInt_v1.01.xsd` | — | `tiposDistDFe` redefine tipos básicos já existentes |
| 4 | `resNFe` | `resNFe_v1.01.xsd` | — | Gera apenas `TResNFe` |
| **5** | **NFe (LAST)** | 11 XSDs NFe principais | — | **Deve ser sempre o último.** Gera o `ObjectFactory` abrangente (50+ métodos) e o `TEndereco` completo (com UF, cPais, xPais, fone). Sobrescreve versões anteriores de tipos compartilhados. |

> **Regra crítica:** se a ordem for alterada e o pass NFe não for o último, os testes falharão com `cannot find symbol` em `ObjectFactory.createTNFeInfNFeDet*()` e `TEndereco.setUF()` / `setFone()`.

### Package `schemas_eventos` — 14 passes

Cada evento define `TEvento`, `TRetEnvEvento`, `TProcEvento` etc. com o mesmo nome mas assinaturas incompatíveis. Solução: um pass por grupo, com binding que renomeia as classes para nomes únicos.

| Pass | Label | Evento | Binding | Classes geradas (exemplos) |
|---|---|---|---|---|
| 01 | `generico` | Evento genérico | `generico.xjb` | `TEventoGenerico`, `TEnvEventoGenerico`, `TRetEnvEventoGenerico` |
| 02 | `cancelamento` | Cancelamento NFe | `cancelamento.xjb` | `TEventoCancelamento`, `TEnvEventoCancelamento`, `TRetEnvEventoCancelamento` |
| 03 | `cancelamento-substituicao` | Cancelamento por Substituição | `cancelamento-substituicao.xjb` | `TEventoCancelamentoSubstituicao`, ... |
| 04 | `carta-correcao` | CCe | `carta-correcao.xjb` | `TEventoCartaCorrecao`, `TEnvEventoCartaCorrecao`, `TRetEnvEventoCartaCorrecao` |
| 05 | `epec` | EPEC | `epec.xjb` | `TEventoEpec`, `TEnvEventoEpec`, `TRetEnvEventoEpec` |
| 06 | `manifestacao` | Manifestação Destinatário | `manifestacao.xjb` | `TEventoManifestacao`, `TEnvEventoManifestacao`, `TRetEnvEventoManifestacao` |
| 07 | `conciliacao-financeira` | ECONF | `conciliacao-financeira.xjb` | `TEventoConciliacaoFinanceira`, `TEnvEventoConciliacaoFinanceira`, ... |
| 08 | `cancelamento-conciliacao-financeira` | Canc. ECONF | `cancelamento-conciliacao-financeira.xjb` | `TEventoCancelamentoConciliacaoFinanceira`, ... |
| 09 | `insucesso-entrega` | Insucesso Entrega | `insucesso-entrega.xjb` | `TEventoInsucessoEntrega`, `TEnvEventoInsucessoEntrega`, ... |
| 10 | `cancelamento-insucesso-entrega` | Canc. Insucesso | `cancelamento-insucesso-entrega.xjb` | `TEventoCancelamentoInsucessoEntrega`, ... |
| 11 | `comprovante-entrega` | Comprovante Entrega | `comprovante-entrega.xjb` | `TEventoComprovanteEntrega`, `TEnvEventoComprovanteEntrega`, ... |
| 12 | `cancelamento-comprovante-entrega` | Canc. Comprovante | `cancelamento-comprovante-entrega.xjb` | `TEventoCancelamentoComprovanteEntrega`, ... |
| 13 | `ator-interessado` | Ator Interessado | `ator-interessado.xjb` | `TEventoAtorInteressado`, `TEnvEventoAtorInteressado`, ... |
| 14 | `res-evento` | Resumo Evento | `res-evento.xjb` | `TResEvento` |

Os arquivos de binding (`.xjb`) ficam em `scripts/bindings/`.

### Passes 15+ — Eventos individuais (Reforma Tributária e legados)

Após os 14 passes principais, o script faz mais um laço sobre 26 XSDs de eventos
individuais (`e110001`, `e110110-140`, `e112110-150`, `e210200-240`, `e211110-150`,
`e212110-120`, `e412120-130`, `110150`).

Cada XSD desses define `<xs:element name="detEvento">` global com `complexType` próprio.
A estratégia: rodar `xjc` em um **pacote temporário** (`br.com.swconsultoria.nfe.tmp_evt_<código>`),
extrair apenas o `DetEvento.java`, renomear para `DetEvento<código>.java` e movê-lo para
`schemas_eventos`. O pacote temporário é deletado após.

Por que pacote temporário em vez de binding com `<jxb:class name="...">` em `xs:complexType`?
Porque renomear o tipo via binding **descarta o `@XmlRootElement`** (o XJC entende que é um
tipo nomeado, não um elemento global). Sem `@XmlRootElement`, o `EventoGenericoUtil.montaEvento`
falha em runtime ao fazer marshal direto (`MarshalException`).

### Por que não usar `-episode`

O mecanismo de JAXB episodes (`.episode` + `-b episode`) exige que os tipos referenciados **existam** no schema do pass consumidor com o mesmo nome. Os schemas SEFAZ redefinem tipos básicos (como `TUf`, `TUfEmi`) em múltiplos XSDs com o mesmo nome de element — o episode causaria "SCD not matched" em pelo menos 3 passes. A estratégia de sobrescrição sequencial é equivalente e mais robusta para este caso.

## DetEvento — agora uma classe por código de evento

Versões anteriores mantinham um arquivo `DetEvento.java` superset escrito à mão (para cobrir os campos de todos os eventos). Essa abordagem era frágil: o `xjc` o sobrescrevia a cada regeneração, exigindo `git checkout` manual.

A partir desta versão **cada evento individual tem sua própria classe** `DetEvento<código>.java` (ex.: `DetEvento110001`, `DetEvento112110`, `DetEvento211110`, etc.), gerada automaticamente pelos passes 15+. Veja a seção "Passes 15+" acima.

Os arquivos top-level `DetEvento.java` (anônimo) **não existem mais** — qualquer arquivo de código deve referenciar a classe específica do evento que está manipulando.

## O que verificar depois de regerar

```bash
# 1. Verificar mudanças
git status        # devem aparecer mudanças em schemas/ e schemas_eventos/
git diff --stat

# 2. Compilar e testar
mvn test-compile  # compilação OK?
mvn test          # testes verdes? (esperado: 2157 testes, 0 falhas)
```

## Pacotes órfãos Suframa

Os packages `schema/eventoSuframaInternaliza` e `schema/eventoSuframaVistoria` não possuem XSD fonte conhecida e não são regerados. O script os **preserva** na etapa de limpeza. Eles aparecerão na seção "Pacotes ORFAOS" de cada execução — comportamento esperado.

Se um dia esses eventos ganharem XSDs, adicionar ao script e ao `$knownPackages`.

## Fluxo no CI (drift check)

O CI **não regera automaticamente** — apenas detecta se alguém esqueceu de regerar:

```yaml
# .github/workflows/jaxb-drift.yml (exemplo)
- name: Setup JDK 8
  uses: actions/setup-java@v4
  with: { distribution: temurin, java-version: 8 }
  id: jdk8
- name: Regenera JAXB
  shell: pwsh
  env:
    JAVA_HOME_8: ${{ steps.jdk8.outputs.path }}
  run: pwsh scripts/regenerate-jaxb.ps1
- name: Detecta drift
  run: git diff --exit-code -- src/main/java/br/com/swconsultoria/nfe/schemas src/main/java/br/com/swconsultoria/nfe/schemas_eventos
```

Se o `git diff --exit-code` retornar diff, a build do CI falha indicando "regere localmente e commite". Os `.java` gerados ficam **commitados** no Git (estratégia híbrida) — isso preserva `git blame` e revisão de PR.

## Estrutura dos packages após a regeneração

```
src/main/java/br/com/swconsultoria/nfe/
├── schema/
│   ├── eventoSuframaInternaliza/   # ORFAO: preservado, sem XSD
│   └── eventoSuframaVistoria/      # ORFAO: preservado, sem XSD
├── schemas/                        # TODOS os tipos nao-evento
│   ├── ObjectFactory.java          # 50+ metodos factory (gerado pelo pass 5 NFe)
│   ├── TEndereco.java              # completo: xLgr, nro, xCpl, xBairro, cMun, xMun, CEP, UF, cPais, xPais, fone
│   ├── TUf.java                    # enum de UFs
│   ├── TUfEmi.java                 # enum de UFs emitente
│   ├── TNFe.java                   # NFe completa
│   ├── TInutNFe.java
│   ├── TRetConsReciNFe.java
│   ├── TRetConsStatServ.java
│   ├── TConsCad.java / TRetConsCad.java
│   ├── TResNFe.java
│   ├── TRetDistDFeInt.java
│   ├── TEventoConsSitNFe.java      # renomeado via binding (era TEvento)
│   └── ...
└── schemas_eventos/                # TODOS os eventos SEFAZ
    ├── TUf.java                    # enum de UFs (separado de schemas.TUf)
    ├── TEventoGenerico.java
    ├── TEnvEventoGenerico.java
    ├── TRetEnvEventoGenerico.java
    ├── TEventoCancelamento.java
    ├── TEnvEventoCancelamento.java
    ├── TRetEnvEventoCancelamento.java
    ├── TEventoCartaCorrecao.java
    ├── TEnvEventoCartaCorrecao.java
    ├── TRetEnvEventoCartaCorrecao.java
    ├── TEventoEpec.java / TEnvEventoEpec.java / TRetEnvEventoEpec.java
    ├── TEventoManifestacao.java / TEnvEventoManifestacao.java / TRetEnvEventoManifestacao.java
    ├── DetEvento110001.java        # eventos individuais com @XmlRootElement
    ├── DetEvento112110.java
    ├── DetEvento211110.java        # ...e assim para os 26 codigos
    └── ... (conciliacao financeira, insucesso, comprovante, ator interessado, resumo)
```

## Não-fazer

- **Nao edite** `.java` dentro de `schemas/` ou `schemas_eventos/` à mão. Próxima regeneração apaga.
- **Nao altere a ordem** dos passes em `scripts/regenerate-jaxb.ps1` sem entender o impacto. O pass NFe DEVE ser o último do grupo `schemas`.
- **Nao adicione `<execution>` extras** ao plugin `exec-maven-plugin`. A tabela canônica vive em `scripts/regenerate-jaxb.ps1`.
- **Nao use** o `xjc` do JDK 11+ (não existe), nem do `jaxb-impl` standalone (gera anotações `jakarta.*`, incompatível com Java 8 + `javax.*`).
- **Nao delete** os packages Suframa órfãos — eles ficam em disco sem regeneração.

## Adicionando um evento/documento novo

Quando a Sefaz publicar um novo evento ou documento:

1. Copie a(s) XSD(s) nova(s) para `schemas/`.
2. Crie um arquivo de binding em `scripts/bindings/meu-evento.xjb` que renomeie as classes conflitantes (TEvento → TMeuEvento, etc.).
3. Edite [`scripts/regenerate-jaxb.ps1`](scripts/regenerate-jaxb.ps1) e adicione um novo `Invoke-XjcPass`:
   ```powershell
   # Para evento (schemas_eventos):
   Invoke-XjcPass `
       -Label "schemas_eventos pass-15: meu-evento" `
       -Package "$BasePkg.schemas_eventos" `
       -XsdFiles @('leiauteMeuEvento_v1.00.xsd', 'envMeuEvento_v1.00.xsd', 'retEnvMeuEvento_v1.00.xsd') `
       -BindingFiles @((Join-Path $BindingsDir 'meu-evento.xjb'))

   # Para documento nao-evento (schemas), inserir ANTES do pass NFe:
   Invoke-XjcPass `
       -Label "schemas pass-X: meu-documento" `
       -Package "$BasePkg.schemas" `
       -XsdFiles @('meuDocumento_v1.00.xsd')
   ```
   - Eventos vao para `schemas_eventos`.
   - Documentos que nao sao eventos (consultas, inutilizacoes) vao para `schemas`.
   - Se for documento no grupo `schemas`, insira **antes** do pass NFe (que deve continuar sendo o ÚLTIMO).
4. Rode `mvn exec:exec@regenerate-jaxb`.
5. Verifique que `DetEvento.java` nao foi alterado (se foi, restaure via git).
6. Commite: XSD(s) nova(s) + `.xjb` novo + linha no script + `.java` gerados.

## Migrando um projeto consumidor de v4.00.* para v4.1.*

Ver [`MIGRATION.md`](MIGRATION.md) e o script automático:

```bash
# Windows / Linux / macOS (PowerShell — completo, recomendado):
pwsh scripts/migrate.ps1 -ProjectRoot . -BumpPom
# Simulacao (sem alterar arquivos):
pwsh scripts/migrate.ps1 -ProjectRoot . -DryRun

# Linux/macOS (bash — limitado):
bash scripts/migrate.sh src/main/java
```

## Troubleshooting

| Sintoma | Causa | Solução |
|---|---|---|
| `xjc do JDK 8 nao encontrado` | JDK 8 ausente nos caminhos padrão | Defina `JAVA_HOME_8` ou passe `-Jdk8Path` |
| `pwsh: command not found` | PowerShell Core não instalado | Ver Pré-requisitos |
| `xjc falhou para <pass> (exit ...)` | XSD inválida ou conflito de tipo | Veja stderr do `xjc` acima da mensagem |
| `cannot find symbol: ObjectFactory.createTNFeInfNFeDet*` | Pass NFe não foi o último | Verifique a ordem dos passes em `regenerate-jaxb.ps1` — NFe deve ser pass 5 |
| `TEndereco.setUF() / setFone() not found` | Pass consCad rodou depois do pass NFe | Mesma causa: restaurar a ordem correta dos passes |
| `MarshalException` em `XmlNfeUtil.objectToElement` para evento individual | Classe `DetEvento<código>` sem `@XmlRootElement` | Confirme que o pass 15+ usa pacote temporário (não binding em `xs:complexType` direto). Veja "Passes 15+" acima. |
| `MarshalException` com namespace `xmldsig` em vez de NFe | `package-info.java` de `schemas_eventos` errado | Confirme que o pós-processamento do `regenerate-jaxb.ps1` corrigiu o namespace. |
| `mvn test-compile` quebra após regerar | Classe renomeada/removida em XSD nova | Atualize import no código de negócio |
| Git diff gigante "do nada" | Versão de `xjc` mudou entre JDKs | Pinne JDK 8 da Temurin nas três máquinas (dev + CI) |
