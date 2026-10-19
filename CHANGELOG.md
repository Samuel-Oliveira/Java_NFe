# Notas de versão

## v4.1.3
- Correcao de Base de calculo Cclasstrib icms 70
- Correcao com reuso de cclasstrib 
- Correcao proc Eventos sem signature
- Atualizado Schemas: TMonofasia reestruturado pela SEFAZ. IBS e CBS passaram a ter
  grupos proprios (gIBSMono*/gCBSMono*), cada um em uma de duas modalidades
  mutuamente exclusivas: ad rem ou ad valorem
- A modalidade da monofasia passa a ser derivada do ano de emissao, conforme a
  tabela de transicao da NT 2025.002-RTC (regras UB85a e UB95a): em 2026 IBS e CBS
  sao ad valorem; em 2027 e 2028 o IBS continua ad valorem e a CBS passa a ad rem;
  de 2029 ambos sao ad rem. Use IbsCbsUtil.setAnoEmissao quando o dhEmi da nota nao
  for do ano corrente
- Calculado o qBCBioComb do gpBioDiferenca pela formula da NT (UB89a-10/UB94a-10),
  a partir do pBio do item e do pBioObrig informado em IbsCbsUtil.setPBioObrig
- MUDANCA DE VALOR EMITIDO: qBCMono e qBCMonoReten passam a usar a quantidade
  tributavel (qTrib) e nao mais a comercial (qCom). UB86a pede a unidade de medida
  estabelecida na legislacao, e as formulas da NT sao todas sobre qTrib. Quem vende
  em unidade diferente da tributavel emitia imposto errado pelo fator de conversao,
  sem rejeicao
- MUDANCA DE VALOR EMITIDO: vTotIBSMonoItem e vTotCBSMonoItem deixam de incluir o
  retido anteriormente, seguindo UB105a-10 e UB105b-10. Os totais da nota (gMono)
  continuam trazendo as tres parcelas em tags separadas
- MUDANCA DE VALOR EMITIDO: vNFTot deixa de somar IBS e CBS em 2025 e 2026. A NT
  2025.002-RTC define o vNFTot como a soma dos vItem (W60-10), e a Excecao 1 da
  VB01-10 mantem o vItem igual ao valor do item nesses dois anos, sem acrescimo de
  tributo. A partir de 2027 o vNFTot soma IBS, CBS e as parcelas padrao e de
  retencao da monofasia. Quem emitia nota de 2025 ou 2026 informava vNFTot maior
  que o devido. O IS nao entra na soma porque IbsCbsUtil nao o calcula: quem tem IS
  precisa acrescenta-lo por fora
- vIBSDiferenca e vCBSDiferenca passam a exigir setAdRemIBSBioComb/setAdRemCBSBioComb,
  a aliquota do biocombustivel, que e coluna propria da tabela e nao se confunde com
  a do produto (UB89b-10). Sem ela o valor nao e calculado: quem dependia do calculo
  anterior, que usava a aliquota do produto, passa a receber 0,00
- INCOMPATIBILIDADE DE FONTE em gIBSCBSMono: as classes aninhadas
  TMonofasia.GMonoPadrao, .GMonoRet, .GMonoReten e .GMonoDif deixaram de existir.
  Os equivalentes agora sao TMonofasia.G{IBS,CBS}Mono{AdRem,AdValorem}.GMono*.
  Quem le ou constroi esses objetos nao compila sem ajustar
- Aliquotas e valores monofasicos continuam saindo zerados: monofasia tem aliquota
  propria por produto, que nao e a aliquota do regime regular dos setters
  pAliqIbsUf/pAliqIbsMun/pAliqCbs. O preenchimento e do integrador
- O grupo gMonoDif foi removido do Schema, portanto o indicador MonofasiaDiferimento
  do cClassTrib nao tem mais grupo correspondente e nao e mais preenchido
- Corrigido namespace do XMLDSig nos tipos de assinatura de schemas_eventos, que era
  perdido a cada regeneracao e reintroduzia o procEvento com Signature vazia
- Atualizado schemas.zip com os Schemas novos
- Corrigido URL de consulta da NFCe de homologacao do RN, que apontava para producao
