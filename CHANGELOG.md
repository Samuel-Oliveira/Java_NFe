# Notas de versão

## v4.1.1

**Breaking changes** (ver [MIGRATION.md](MIGRATION.md) para script automático):

- Consolidação dos ~48 sub-packages JAXB em apenas 2: `schemas` (NFe e consultas) e `schemas_eventos` (todos os eventos SEFAZ).
- Classes de evento ambíguas (`TEnvEvento`, `TRetEnvEvento`, `TEvento`, `TProcEvento`) renomeadas por sufixo do tipo de evento (`TEnvEventoCancelamento`, `TEnvEventoCartaCorrecao`, etc.).
- `TProtNFe.InfProt.getDhRecbto()` agora retorna `String` (era `XMLGregorianCalendar`).
- `ResEvento` renomeada para `ResumoEvento`.
- Eventos individuais da Reforma Tributária (`e110001`, `e112110-150`, `e211110-150`, `e212110-120`, `e412120-130`, `e210200-240`, `110150`) agora geram classes top-level `DetEvento<código>` com `@XmlRootElement` (permite marshalling direto via JAXB).

**Correções:**

- Corrigido bug do `regenerate-jaxb` em que o `DetEvento` anônimo de cada evento sobrescrevia o anterior entre passes sequenciais.
- `package-info.java` de `schemas_eventos` agora aponta para o namespace correto (`http://www.portalfiscal.inf.br/nfe`) em vez de `xmldsig`.
- Calculos IBSCBS para Diferimento.
- Cacerts atualizados.
- Monofasia retida.
- Informações Fisco na impressão DANFE.

**Outras melhorias:**

- Script de migração automática para projetos consumidores: `scripts/migrate.ps1` (PowerShell, cross-platform) e `scripts/migrate.sh` (bash, limitado).
- 2157 testes unitários, 0 falhas.
