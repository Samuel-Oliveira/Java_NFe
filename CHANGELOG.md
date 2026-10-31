# Notas de versão

## Nao lancado

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

## v4.2.0

Primeira versao com os grupos da reforma tributaria (IBS/CBS/IS) conforme a NT
2025.002-RTC. Vindo da 4.1.3, leia as duas primeiras secoes: ha quebra de compilacao, e
ha mudanca de valor emitido que **nao** aparece como erro de compilacao.

### Quebra a compilacao

- `TMonofasia` foi reestruturado pela SEFAZ. IBS e CBS passaram a ter grupos proprios,
  cada um em uma de duas modalidades mutuamente exclusivas (ad rem ou ad valorem), e os
  grupos de valores desceram um nivel:

  | Na 4.1.3 | Agora |
  |---|---|
  | `TMonofasia.getGMonoPadrao()` | `TMonofasia.getG{IBS,CBS}Mono{AdRem,AdValorem}().getGMonoPadrao()` |
  | `TMonofasia.getGMonoReten()` | idem, um nivel abaixo |
  | `TMonofasia.getGMonoRet()` | idem, um nivel abaixo |
  | `TMonofasia.GMonoPadrao` e irmas, como tipo | `TMonofasia.GIBSMonoAdRem.GMonoPadrao` e os outros tres grupos |
  | `TMonofasia.getGMonoDif()` e `TMonofasia.GMonoDif` | removidos do Schema, sem equivalente |

  Classes novas: `GIBSMonoAdRem`, `GIBSMonoAdValorem`, `GCBSMonoAdRem`,
  `GCBSMonoAdValorem` e `GpBioDiferenca`. Quem le ou constroi esses objetos nao compila
  sem ajustar.

  Fora disso nada quebra: nenhum outro tipo publico de `schemas` ou `schemas_eventos`
  foi removido ou renomeado, e nenhum metodo publico de `IbsCbsUtil` deixou de existir.

### Muda valor emitido sem quebrar a compilacao

Estas passam calado: compilam, rodam, e emitem XML diferente do que a 4.1.3 emitia.
Quem gera monofasia deve comparar um XML antes e depois antes de subir.

- `qBCMono` e `qBCMonoReten` passam a usar a quantidade tributavel (`qTrib`) e nao mais
  a comercial (`qCom`). UB86a pede a unidade de medida estabelecida na legislacao, e as
  formulas da NT sao todas sobre `qTrib`. Quem vende em unidade diferente da tributavel
  emitia imposto errado pelo fator de conversao, sem rejeicao.
- `vTotIBSMonoItem` e `vTotCBSMonoItem` deixam de incluir o retido anteriormente,
  seguindo UB105a-10 e UB105b-10. Os totais da nota continuam trazendo as tres parcelas
  em tags separadas.
- `vNFTot` deixa de somar IBS e CBS em 2025 e 2026. W60-10 define o `vNFTot` como a soma
  dos `vItem`, e a Excecao 1 da VB01-10 mantem o `vItem` igual ao valor do item nesses
  dois anos. Quem emitia nota de 2025 ou 2026 informava `vNFTot` maior que o devido. De
  2027 em diante o `vNFTot` passa a somar tambem o `vIS` e as parcelas padrao e de
  retencao da monofasia, que antes ficavam de fora.
- A modalidade da monofasia passa a ser derivada do ano de emissao (UB85a e UB95a): em
  2026 IBS e CBS sao ad valorem; em 2027 e 2028 o IBS continua ad valorem e a CBS passa
  a ad rem; de 2029 ambos sao ad rem. Muda qual grupo sai no XML. Informar a modalidade
  errada e rejeitado: cStat 1219, 1220, 1245, 1246 e 1259.
- `vIBSDiferenca` e `vCBSDiferenca` passam a exigir `setAdRemIBSBioComb` e
  `setAdRemCBSBioComb`, a aliquota do biocombustivel, que e coluna propria da tabela e
  nao se confunde com a do produto (UB89b-10). Sem informa-la o valor nao e calculado:
  quem dependia do calculo anterior, que usava a aliquota do produto, passa a receber
  0,00.
- `qBCBioComb` do `gpBioDiferenca` passa a ser calculado pela formula da NT (UB89a-10 e
  UB94a-10), a partir do `pBio` do item e do `pBioObrig` informado em `setPBioObrig`.
- O grupo `gMonoDif` nao e mais preenchido, porque saiu do Schema. O indicador
  `MonofasiaDiferimento` do `cClassTrib` deixou de ter grupo correspondente.
- **NFCe do RN:** o dominio mudou de `set.rn.gov.br` para `sefaz.rn.gov.br`, e as URLs
  passaram a https, nos dois ambientes. Isso entra no XML, na URL do QR Code. Em
  homologacao a `URL-ConsultaNFCe` apontava para producao e passou a apontar para
  `hom.nfce.sefaz.rn.gov.br`.

### Adicoes

- Valores monofasicos de IBS/CBS passam a ser calculados a partir das aliquotas
  informadas, como ja acontecia nos outros grupos; na 4.1.3 saiam fixos em `0.00`.
  Setters: `setPAliqMonoUF`, `setPAliqMonoMun`, `setPAliqMonoCBS`,
  `setPAliqMonoRetenIBS`, `setPAliqMonoRetenCBS`, `setAdRemIBS`, `setAdRemCBS`,
  `setAdRemIBSBioComb`, `setAdRemCBSBioComb`.
- `IbsCbsUtil.setDhEmi` e construtores de tres argumentos derivam o ano de emissao do
  `dhEmi` da nota. O ano governa a modalidade da monofasia e o conteudo do `vNFTot`, e a
  SEFAZ valida os dois pelo `dhEmi`. A forma mais segura recebe a propria
  `TNFe.InfNFe` e le o `ide/dhEmi` por dentro, sem deixar passar data que nao seja a do
  documento. O valor e validado contra o formato `TDateTimeUTC` do Schema -- ano 20XX,
  segundos obrigatorios, deslocamento de fuso explicito de -11:00 a +12:00 -- e nulo,
  vazio ou fora do formato lanca `NfeException` em vez de cair no relogio da maquina.
  `setAnoEmissao(int)` continua valendo, com default pelo ano corrente, que erra a nota
  montada do outro lado da virada do ano (dhEmi de 31/12 montado em 01/01).
  `setDhEmi(null)` literal nao compila, por ambiguidade entre as sobrecargas.
- `setPBioObrig`, para o percentual obrigatorio de biocombustivel na mistura.
- Campos novos em `ClassificacaoTributariaDTO`: `IndDIR`, `IndDUIMP`,
  `IndPercentualDiferencaBioCombustivel`, `TipoDoacao` e `TipoReceitaBrutaSN`.

### Correcoes que preservam comportamento

- O namespace do XMLDSig nos tipos de assinatura de `schemas_eventos` passa a sobreviver
  a regeneracao dos Schemas. Sem isso, a correcao do `procEvento` com `Signature` vazia
  feita na 4.1.3 voltava a se perder a cada regeneracao.

### Limitacoes conhecidas

- Aliquotas e valores monofasicos so saem preenchidos se o integrador informar as
  aliquotas: a monofasia tem aliquota propria por produto, que nao e a do regime regular
  dos setters `pAliqIbsUf`, `pAliqIbsMun` e `pAliqCbs`.
- `vIBSMonoRet` (UB93a-10) nao e calculado: depende de tabelas de referencia que a SEFAZ
  ainda nao publicou.

### Schemas e build

- Schemas atualizados (`leiauteNFe_v4.00`, `nfe_v4.00`, `DFeTiposBasicos_v1.00`) e
  `schemas.zip` regerado.
- Regeneracao das classes JAXB documentada em `BUILD.md`, com o script
  `scripts/regenerate-jaxb.ps1`.

## v4.1.3

- Correcao de Base de calculo Cclasstrib icms 70
- Correcao com reuso de cclasstrib
- Correcao proc Eventos sem signature
