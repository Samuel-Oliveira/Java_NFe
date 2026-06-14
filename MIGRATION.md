# Guia de migração — v4.00.* → v4.1.1

## O que mudou

A versão 4.1.0 consolidou ~48 sub-packages JAXB em **2 packages**:

| Antes (v4.00.*)                                               | Depois (v4.1.1)                              |
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

| Classe v4.00.*                  | Classe v4.1.1                                    | Evento |
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

## Como migrar automaticamente

Faça backup (commit ou branch) antes:

```bash
git checkout -b migra
```

### Windows (PowerShell):

```powershell
# Simulacao (sem alterar arquivos):
pwsh scripts/migrate.ps1 -DryRun

# Aplicar nas fontes:
pwsh scripts/migrate.ps1 -Path src/main/java
pwsh scripts/migrate.ps1 -Path src/test/java
```

### Linux / macOS (bash):

```bash
# Simulacao:
bash scripts/migrate.sh src/main/java --dry-run

# Aplicar:
bash scripts/migrate.sh src/main/java
bash scripts/migrate.sh src/test/java
```

## O que o script NÃO faz

Os scripts de migração fazem substituições de texto simples. Os seguintes casos precisam de revisão manual:

1. **Usos de `TEnvEvento` genérico** — se o código armazenava instâncias do tipo genérico `TEnvEvento` para enviar diferentes tipos de evento, cada chamada precisa ser especializada para a classe correta:
   ```java
   // ANTES:
   TEnvEvento env = cancelamentoUtil.montaEvento(...);
   Nfe.cancelarNfe(config, env);

   // DEPOIS:
   TEnvEventoCancelamento env = cancelamentoUtil.montaEvento(...);
   Nfe.cancelarNfe(config, env);
   ```

2. **`XmlNfeUtil.xmlToObject(xml, Classe.class)`** — certifique-se de passar a classe correta do novo package:
   ```java
   // ANTES:
   TRetEnvEvento ret = XmlNfeUtil.xmlToObject(xml, TRetEnvEvento.class);

   // DEPOIS (cancelamento):
   TRetEnvEventoCancelamento ret = XmlNfeUtil.xmlToObject(xml, TRetEnvEventoCancelamento.class);
   ```

3. **Imports wildcard `import br.com.swconsultoria.nfe.schema_4.*;`** — o script substitui o prefixo do package mas mantém o `*`. Se o wildcard importava de um único package e agora há ambiguidade, resolva os imports individualmente.

4. **`TUf` em `schemas_eventos`** — o package `schemas_eventos` tem seu próprio `TUf` (para eventos). O package `schemas` também tem `TUf` (para NFe). Se o código usava `TUf` de um package de evento, verifique que o import aponta para `schemas_eventos.TUf`.

## Verificação pós-migração

```bash
mvn test-compile   # deve compilar sem erros
mvn test           # deve passar todos os testes (2157/2157)
```

Se `mvn test-compile` falhar, os erros `cannot find symbol` indicarão exatamente qual classe/método precisa de ajuste manual.
