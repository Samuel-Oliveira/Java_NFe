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
