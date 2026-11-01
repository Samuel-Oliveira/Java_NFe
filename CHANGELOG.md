# Notas de versão

### Schemas: PL 010g v1.03

- Schemas atualizados para o **PL 010g v1.03**: `leiauteNFe_v4.00`, `DFeTiposBasicos_v1.00`,
  `tiposBasico_v1.03` e `e110001_v1.00`, mais o `e110300_v1.00` novo. `schemas.zip` regerado.
- **NT 2026.006 (Vinculacao de Pagamento)**: grupo `gPgtoVinc` com o tipo `TPgtoVinc`, e o
  evento **110300** (Vinculacao da Transacao de Pagamento), com as classes `DetEvento110300`
  e `TEventoPgto`.
- **NT 2026.008 (Valor Liquido)**: campos `vUnComLiq` e `vProdLiq` no item, `vProdLiqTot` no
  total, e o grupo `ICMSPrevistoPagtoAntecip` com `vICMSPrevisto`.
- **NT 2026.007 (NF-e sem IE)** e **NT 2025.002-RTC v1.53** nao pedem mudanca de leiaute: a
  `IE` do emitente ja era opcional, e as versoes 1.51 a 1.53 da 2025.002 alteram apenas regras
  de validacao (UB12-10, VC02-*, 3BA02-*, B25-40).
- O `tiposBasico_v1.03` do PL 010g devolveu os seis tipos que a SEFAZ havia removido e que
  estavam restaurados a mao desde a v4.2.0: o arquivo volta a ser o oficial, sem remendo.
- O script `regenerate-jaxb.ps1` passa a extrair tambem os tipos proprios do XSD de cada
  evento, e nao so o `DetEvento`. Sem isso o `e110300` nao compilava, porque define o
  `TEventoPgto` dentro do proprio arquivo.

### Nao coberto: NT 2026.005 (Simples Nacional)

- Os campos `tpRegimeIBSCBS` (C21a) e `cAtivSN` (I17d) e o evento de Tributacao do Simples
  Nacional **nao existem no PL 010g**. Enquanto a SEFAZ nao publicar o Schema, essa NT fica
  sem implementacao.

### Correcoes

- `XmlNfeUtil.objectToElement` passa a marshallar tambem as classes de evento sem
  `@XmlRootElement`, informando o elemento raiz explicitamente com o namespace lido do
  `@XmlSchema` do package. O `detEvento` e anonimo no Schema do EConf, entao o
  `DetEventoConciliacaoFinanceira` nao recebe a anotacao na geracao e o evento generico
  lancava "Erro Ao Converter Objeto em Elemento" antes mesmo de sair da maquina.

### Exemplos

- `EnvioNfeTeste` passa a montar o grupo `IBSCBS` por item e os totais `IBSCBSTot` e
  `vNFTot`. A homologacao ja rejeita a NF-e modelo 55 sem o grupo, com **1115 - Grupo
  IBSCBS nao informado**; o exemplo da NFC-e tinha sido atualizado na reforma e o da
  NF-e nao.
- `EnvioNfeTeste` emite NF-e 55 **monofasica** com `-DcClassTrib=620006`: passa a montar o
  grupo `comb` (rejeicao 1106 sem ele), ICMS 61 em vez de 60 (858), `cBenef` (930) e unidade
  em litro (854). O grupo `gMonoRet` so carrega o valor, sem base nem aliquota, e a SEFAZ o
  confere pela tabela por produto, que ainda nao foi publicada: hoje ela calcula zero e
  recusa qualquer outro valor com **1240**, entao o exemplo informa zero e documenta onde
  trocar quando a tabela sair.
- `EventoGenericoTeste` usava o payload do EConf (110750) para o evento 112110, o que
  rendia **493 - Evento nao atende o Schema XML especifico**. Passa a usar
  `DetEvento112110`.
- `EpecTeste` tinha `"X"` literal em sete campos e chave fixa; passa a gerar a chave com
  `tpEmis=4`, que o EPEC exige (rejeicao 484), e a usar dados validos.
- `ConsultaCadastroTeste` consultava um CNPJ nao cadastrado em GO (rejeicao 259); passa a
  consultar o proprio emitente.
- Numero, chave, protocolo, sequencia, faixa de inutilizacao e caminho do XML viraram
  propriedades de sistema, com os valores atuais como padrao. Era o dado fixo que garantia
  as rejeicoes 539, 573 e 563 a cada execucao, e e o que permite encadear os exemplos.

### Quebra a compilacao

- `TTribNFCe` perdeu `getGIBSCBSMono()` e `setGIBSCBSMono()`: o PL 010g removeu o grupo
  monofasico desse tipo. Quem montava NFC-e com monofasia por essa classe precisa ajustar.
  Nenhum outro tipo publico foi removido ou renomeado.

### Adicoes

- **DANFE v2 da NF-e (modelo 55)** no leiaute da NT 2026.010 (DANFE Reforma Tributaria:
  IBS/CBS/IS, producao em 01/12/2026), em Thymeleaf + openhtmltopdf em vez de Jasper.
  Pacote `br.com.swconsultoria.nfe.impressao.v2`: `ImpressaoDanfeV2Util.impressaoPadraoNFe(xml)`
  e `impressaoPdfArquivo`, `impressaoPdfByte`, `impressaoHtml`, `gerarHtml` e `impressaoDireta`,
  com `ImpressaoDanfeV2DTO` para logo, template proprio, orientacao (automatica pelo `tpImp`,
  retrato ou paisagem) e canhoto. Imprime CRT, os totais de IBS/CBS/IS (com a base de calculo),
  o CST do IBS/CBS e as colunas de bases, aliquotas (efetiva quando ha `gRed`) e valores por item
  para as quatro formas do grupo `IBSCBS` (`gIBSCBS`, monofasico ad rem e ad valorem,
  transferencia de credito e ajuste de competencia), o QR Code de `infNFeSupl` e so os quadros
  facultativos presentes no XML; o bloco de dados adicionais fica encostado no rodape da ultima
  folha, como no DANFE Jasper. Erros saem como `DanfeException`. A biblioteca nao altera o log
  do openhtmltopdf na aplicacao. A impressao Jasper (`ImpressaoNfeUtil`) nao muda; NFC-e e CC-e
  continuam nela.
- Dependencias novas `org.thymeleaf:thymeleaf` 3.1.2.RELEASE e
  `com.openhtmltopdf:openhtmltopdf-pdfbox` 1.0.10, em escopo `provided` como as do Jasper: o
  projeto consumidor as declara (junto com barbecue e zxing, usados no codigo de barras e no QR
  Code). Manual de integracao em `docs/MANUAL-DANFE-V2.md`.