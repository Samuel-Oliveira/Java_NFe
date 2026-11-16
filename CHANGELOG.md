# Notas de versão

### Correcoes do DANFE v2

- **Modalidade do frete:** o quadro de transporte passa a sair sempre que o XML traz
  `transp/modFrete`, que e obrigatorio. Antes, nota sem transportadora, veiculo e volumes
  saia sem "FRETE POR CONTA". Quem usa `isExibirTransporte` em template proprio passa a
  receber `true` nessas notas.
- **Valor unitario:** `vUnCom` passa a sair com todas as casas do XML (2 a 10, limite do
  Schema), pelo novo `fmt.valorUnitario`. Antes, parava em 4 casas e arredondava:
  `5.893217` saia `5,8932`. `fmt.quantidade` continua com 2 a 4 casas.

### Schemas: PL 010h v1.03

- Schemas atualizados para o **PL 010h v1.03**: `leiauteNFe_v4.00` e `DFeTiposBasicos_v1.00`,
  mais o `e412140_v1.00` novo. `schemas.zip` regerado. Os demais arquivos do pacote
  (`nfe_v4.00`, `tiposBasico_v4.00`, `xmldsig-core-schema_v1.01`, `e110001_v1.00`,
  `e110300_v1.00` e `tiposBasico_v1.03`) nao mudaram em relacao ao PL 010g.
- **NT 2026.005 (Simples Nacional)**, que na v4.3.0 ficou sem implementacao por falta de
  Schema: agora existe por inteiro. No leiaute entraram o `tpRegimeIBSCBS` (C21a) e o
  `cAtivSN` (I17d); nos tipos basicos, o grupo `gTribSN` com o tipo `TTribSN`
  (`gIBSSN`, `gCBSSN`, `pIBSSN`, `pCBSSN`, `vIBSSN`, `vCBSSN`), presente no `TTribNFe`,
  no `TTribNFCe` e nos totais do `TIBSCBSMonoTot`; e o evento **412140** (Tributacao do
  Simples Nacional), com as classes `DetEvento412140`, `TTotalSN` e `TTribItemSN`.
  Implantacao em producao adiada de 16/11/2026 para 14/12/2026 pelo Comite Gestor do IBS.
- O `xs:selector` das constraints de unicidade do grupo YC (Vinculacao de Pagamento,
  NT 2026.006) passou de `gPgto` para `./*`. Sem prefixo de namespace o seletor nao casava
  em documento com namespace, entao a unicidade de `nPag` e `idTransacao` nao era checada.
  Correcao do Schema, sem efeito no modelo.
- O `regenerate-jaxb.ps1` passa a gerar o `e412140`.

### Correcoes

- O **Jackson** passa a ser declarado no `pom.xml` (`jackson-databind` 2.14.1, que traz o
  `jackson-core` e o `jackson-annotations`). O `ConsultaTributacao` e o `IbsCbsUtil` usam
  `ObjectMapper` e `TypeReference` para ler o `ibscbs.json`, entao o Jackson sempre foi
  dependencia de runtime da biblioteca; ate a v4.3.0 ele nao era declarado e so entrava de
  carona no `jasperreports`, que e `provided`. Quem usa a lib sem o Jasper recebia
  `NoClassDefFoundError: com/fasterxml/jackson/core/type/TypeReference` ao construir o
  `IbsCbsUtil`, e por ser `Error` e nao `Exception` escapava de `catch (Exception)` e
  derrubava a emissao.

### Exemplos

- `EnvioNfceIbsCbsProducaoTeste` novo: emite a mesma NFC-e de combustivel em homologacao e
  em producao, com `-Dambiente`, `-Dgrupo` (`mono`, `semgrupo`, `nenhum`), `-Dserie`,
  `-Dnumero` e `-Dqrcode`. Serve para medir, numa UF, se a producao ja aceita o layout
  monofasico reformulado. Com o QR-Code v3 ele dispensa o CSC, e por isso **autoriza
  documento fiscal de verdade**: cancele em seguida com o `CancelarTeste`.
- `CancelarTeste` ganhou `-Dambiente` e `-Dmotivo`, para cancelar em producao sem editar o
  fonte.

### Atencao: implantacao do monofasico nao e simultanea entre UFs

A reformulacao do layout monofasico (NT 2025.002-RTC v1.50) tem producao em 03/11/2026,
mas cada UF sobe no seu tempo. Medido em Goias com o `EnvioNfceIbsCbsProducaoTeste`,
NFC-e modelo 65, mesmo XML, so mudando a data:

| grupo IBSCBS no item | 14/11/2026 | 16/11/2026 |
|---|---|---|
| com `gIBSCBSMono` (`gIBSMonoAdValorem` / `gCBSMonoAdValorem`) | **225** | 100 autorizado |
| so `CST` + `cClassTrib` | 100 autorizado | 100 autorizado |

Ate 14/11 a producao de GO ainda rodava o layout antigo e devolvia **225 - Falha no Schema
XML do lote de NFe**, derrubando o lote inteiro antes de avaliar a nota; homologacao, no
mesmo dia e com os mesmos bytes, aceitava. Em 16/11 GO subiu o layout novo. As duas formas
autorizam hoje, ou seja, o grupo passou a ser aceito mas ainda nao e exigido, coerente com
a UB12-10 seguir como implementacao futura.

Se a sua UF ainda devolver 225 na monofasia, e isso: a UF nao subiu o Schema. Nao ha o que
corrigir na biblioteca.
