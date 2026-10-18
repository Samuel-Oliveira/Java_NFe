# Notas de versão

## v4.1.3
- Correcao de Base de calculo Cclasstrib icms 70
- Correcao com reuso de cclasstrib 
- Correcao proc Eventos sem signature
- Atualizado Schemas: TMonofasia reestruturado pela SEFAZ. IBS e CBS passaram a ter
  grupos proprios (gIBSMono*/gCBSMono*), cada um em uma de duas modalidades
  mutuamente exclusivas: ad rem ou ad valorem. Quem consome gIBSCBSMono precisa
  ajustar a leitura. A modalidade e escolhida por IbsCbsUtil.setMonofasiaAdValorem,
  com padrao ad rem (comportamento anterior)
- O grupo gMonoDif foi removido do Schema, portanto o indicador MonofasiaDiferimento
  do cClassTrib nao tem mais grupo correspondente e nao e mais preenchido
- Corrigido namespace do XMLDSig nos tipos de assinatura de schemas_eventos, que era
  perdido a cada regeneracao e reintroduzia o procEvento com Signature vazia
- Atualizado schemas.zip com os Schemas novos
- Corrigido URL de consulta da NFCe de homologacao do RN, que apontava para producao