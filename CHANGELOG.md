# Notas de versão

## v4.2.0 -- nao publicada

> **Numero a confirmar.** A 4.1.3 ja esta publicada no Maven Central (latest e release
> = 4.1.3) com apenas as tres correcoes da secao seguinte. Todo o resto desta secao veio
> depois e nunca foi publicado. Subir como 4.1.3 e rejeitado: o Central nao aceita
> sobrescrever versao. Escolhido 4.2.0 porque ha quebra de compilacao, o que passa de
> patch.

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
