# Java_NFe

Biblioteca Java para consumo dos web services da NF-e e da NFC-e (SEFAZ), com geração dos tipos JAXB a partir dos schemas, assinatura, validação e impressão (DANFE).

## Ambiente

- Java 8 (`java.version` 1.8 no `pom.xml`). Mantenha o código compatível com Java 8.
- Maven, sem wrapper. Biblioteca publicada como jar (`java-nfe`).
- Os tipos JAXB (`schemas` e `schemas_eventos`) são gerados a partir de `schemas/`. A regeneração exige JDK 8 (`xjc`); o procedimento está em `BUILD.md`. Não regere para mudança de código de negócio.
- `MIGRATION.md`: migração de 4.00.* para 4.1.*. `README.md`: uso, dependência Maven e histórico. `docs/`: manual do DANFE v2 e site de documentação.

## Build e testes

```
mvn -q test-compile --no-transfer-progress
mvn test --no-transfer-progress
mvn test --no-transfer-progress "-Dtest=NomeDaClasseTest"
```

- `mvn test` roda os `*Test.java` (JUnit 5, surefire com JMockit como javaagent). Dois deles ficam em `exemplos/` (`IbsCbsTest`, `EnvioNfceCombustivelTest`) e usam HOMOLOGACAO. `util/WebServiceUtilTest` usa `AmbienteEnum.PRODUCAO` só para montar URL, sem chamar serviço.
- Os `*Teste.java` de `exemplos/` e os `TesteImpressao*.java` de `impressao/` são programas com `main`, sem `@Test`. Os de `exemplos/` falam com a SEFAZ usando certificado real. O padrão `Test*` do surefire casa com `TesteImpressao*`, mas sem `@Test` o JUnit não executa nada.

## Segurança dos programas com `main`

- Nunca execute uma classe `*Teste` (`main`) sem pedido explícito, e confira o `AmbienteEnum` antes.
- Apontam para PRODUCAO: `ConsultaTributacaoTeste`, `EConfTeste` (GO) e `StatusServicoTeste` (MG).
- Escolhem o ambiente por `-Dambiente` (padrão HOMOLOGACAO): `CancelarTeste`, `EnvioNfceIbsCbsProducaoTeste`, `EnvioNfceSimplesNacionalTeste` e `EnvioNfeDanfeSimplificadoTipo2Teste`. Em PRODUCAO, os três últimos AUTORIZAM nota fiscal de verdade.
- Os demais de `exemplos/` usam HOMOLOGACAO. Ainda assim não os rode sem necessidade.
- Série 890 em GO devolve rejeição 999 em qualquer ambiente; para teste, use número alto com série baixa.

## Branches

- Integração: `develop`. Produção: `master`.
- Trabalho novo nasce de `develop`, com nome `feat/<assunto>` ou `fix/<assunto>` em kebab-case, e entra por PR.
