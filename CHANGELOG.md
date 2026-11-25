# Notas de versão

### Impressao: Jasper removido, Thymeleaf passa a ser a impressao oficial

- **Quebra de compatibilidade.** Saem `ImpressaoNfeUtil`, `ImpressaoDTO`, `JasperNFeEnum`, os
  `.jrxml`/`.jasper` (DANFE da NF-e e sua fatura, DANFE NFC-e e CC-e) e as dependencias
  `jasperreports`, `jasperreports-fonts` e `barbecue`. De `ConstantesImpressaoNfeUtil` ficam so
  `PATH_LOGO_NFE` e `PATH_LOGO_NFCE`. Troca de cada chamada no `MIGRATION.md`; quem tem `.jasper`
  proprio pode ficar na 4.3.1 ate refazer o leiaute.
- **DANFE NFC-e** em Thymeleaf: `ImpressaoDanfeV2Util.impressaoPadraoNFCe(xml)`, em bobina, pelo
  mesmo template do DANFE Simplificado Tipo 2 (`danfe-bobina.html`, `TEMPLATE_BOBINA`), que reproduz
  o leiaute da NFC-e. Mudam o titulo da divisao I e o rotulo "NFC-e". A URL de consulta vem do
  `infNFeSupl/urlChave`; a v1 a recebia por parametro.
- **CC-e** em Thymeleaf: `ImpressaoDanfeV2Util.impressaoPadraoCCe(xml)`, em A4 (`cce.html`,
  `TEMPLATE_CCE`), com o leitor novo `LeitorEventoV2` / `EventoV2DTO`. Imprime tambem o destinatario,
  a data de registro e a situacao do evento, e a chave inteira (o Jasper cortava o ultimo bloco).
  Outros eventos saem com template proprio.
- O `gerarHtml` escolhe o template pelo XML: NF-e no A4 (bobina com `tpImp=6`), NFC-e na bobina,
  evento 110110 na CC-e.
- Codigo de barras da chave pelo zxing (`Code128Writer`, conjunto C, 277 modulos), no lugar do
  barbecue.
- QR Code com a zona de silencio dentro da imagem, sem sobra de arredondamento: 4 modulos no minimo
  (ISO/IEC 18004) e, no QR v3 off-line (com a assinatura), o bastante para a margem somar 10% do lado.
  Na bobina a imagem passa a 30 mm, ocupando o mesmo espaco dos 27 mm com 1,5 mm de margem de antes:
  o codigo fica com 24,7 mm ou mais e a margem atende a NT 2026.003, item 3.4 (25 mm, sendo 22 de
  conteudo; acima disso, margem de 10%). No A4 a imagem segue com 27 mm (17 mm em paisagem) e o codigo
  do QR online encolhe uns 10% para dar lugar a margem. `ImagemDanfeV2Util.qrCode` devolve uma imagem
  com pelo menos o `tamanho` pedido (multiplo exato dos modulos), nao mais exatamente ele, e lanca
  `DanfeException` para tamanho negativo.
- CNPJ alfanumerico na impressao v2: o CNPJ sai com a mascara de CNPJ (`12.ABC.345/0001-95`), e nao
  mais com a de CPF, e a chave de acesso mantem as letras (antes elas eram descartadas e a chave saia
  com menos de 44 caracteres). O `idEstrangeiro` do destinatario sai como veio tambem no DANFE A4, como
  na bobina: antes, um de 11 digitos saia com mascara de CPF e um de 14 com a de CNPJ.
- As dependencias de impressao (`thymeleaf`, `openhtmltopdf-pdfbox`, `zxing core` e `javase`)
  deixam de ser `provided` e vem por transitividade: quem usa o `java-nfe` nao declara mais nada
  para imprimir. O `jcommander`, que o `zxing javase` traria so para a linha de comando, fica
  excluido. Quem nao imprime pode excluir as quatro no proprio `pom.xml`. Com elas vem o
  `slf4j-api` 2.0.7 do Thymeleaf: projeto com binding SLF4J 1.7 deve fixar a 1.7 (manual, 2.2).
- **Template proprio:** o caminho em `setTemplate` precisa terminar em `.html`, `.htm` ou `.xhtml`.
  Os nomes sem extensao so sao procurados no jar, para que uma pasta ou arquivo `cce` ou `danfe`
  no diretorio de trabalho nao tome o lugar do template padrao.

### DANFE Simplificado Tipo 2 (NT 2026.003)

- A NF-e com `tpImp=6` (Ajuste SINIEF 13/26, NF-e no lugar da NFC-e) passa a sair no
  **DANFE Simplificado Tipo 2**, um cupom em bobina, pelo mesmo `ImpressaoDanfeV2Util`:
  `impressaoPadraoNFe` escolhe o template de bobina (`danfe-bobina.html`) pelo `tpImp`.
  Antes, a nota com `tpImp=6` saia no DANFE A4; para manter o A4, informe
  `setTemplate(ImpressaoDanfeV2Util.TEMPLATE_PADRAO)`.
- Divisoes I a IX da NT: cabecalho, itens, totais (acrescimos e desconto so com valor),
  "(+) CBS / IBS / IS", forma de pagamento e troco, consulta pela chave, QR Code ao lado
  (papel de 72 mm ou mais) ou centralizado, consumidor, identificacao e protocolo, mensagem
  fiscal e Lei 12.741/2012. Em contingencia sem protocolo, "EMITIDA EM CONTINGENCIA /
  Pendente de autorizacao" nos dois lugares exigidos e "Via do Consumidor" ou, com o novo
  `setViaEstabelecimento(true)`, "Via do Estabelecimento".
- O PDF sai numa folha so, da largura do papel (`setLarguraPapelMm`, padrao 80, minimo 56) e
  da altura do conteudo: `htmlParaPdf` mede o documento e diagrama de novo quando o template
  traz o `<style id="pagina-bobina">`.
- `DanfeV2DTO` ganha `pagamentos` (`pag/detPag`), `pag` (`vTroco`), `ide.indPres`,
  `prot.cMsg`/`xMsg` e os metodos de decisao do leiaute novo; `FormatadorDanfeV2` ganha
  `formaPagamento`, `consumidor`, `enderecoCompleto` e `naoZero`.
- Exemplo `EnvioNfeDanfeSimplificadoTipo2Teste`, que mede na SEFAZ a NF-e com `tpImp=6`. Em
  GO homologacao (23/11/2026) ela e autorizada, inclusive em contingencia off-line
  (`tpEmis=9`), e as regras da NT 2026.002 estao ligadas (394, 716, 711), mas o destinatario
  continua obrigatorio (719).

### Correcoes do DANFE v2

- **Modalidade do frete:** o quadro de transporte passa a sair sempre que o XML traz
  `transp/modFrete`, que e obrigatorio. Antes, nota sem transportadora, veiculo e volumes
  saia sem "FRETE POR CONTA". Quem usa `isExibirTransporte` em template proprio passa a
  receber `true` nessas notas.
- **Valor unitario:** `vUnCom` passa a sair com todas as casas do XML (2 a 10, limite do
  Schema), pelo novo `fmt.valorUnitario`. Antes, parava em 4 casas e arredondava:
  `5.893217` saia `5,8932`. `fmt.quantidade` continua com 2 a 4 casas.
- **Tipo de regime de apuracao do IBS e da CBS:** o campo passa a vir de
  `emit/tpRegimeIBSCBS` (NT 2026.005), pelo novo `fmt.tipoRegimeApuracao`, que imprime
  o codigo com uma descricao curta do Schema (1 a 4). Antes, so saia preenchido pelo
  `PARAM_TIPO_REGIME_APURACAO`. Sem a tag e sem o parametro, continua vazio. O parametro,
  quando informado com valor nao nulo, tem prioridade sobre o XML, inclusive `""`.

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
