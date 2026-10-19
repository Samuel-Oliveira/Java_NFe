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
  de 2029 ambos sao ad rem. O ano sai do dhEmi da nota: ver IbsCbsUtil.setDhEmi
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
  que o devido
- O vIS passa a entrar no vNFTot de 2027 em diante, lido do grupo IS do proprio
  item. O ICMSTot nao tem campo de Imposto Seletivo, que tem grupo de total
  proprio (ISTot, W33-10), portanto o vNF nunca o inclui
- IbsCbsUtil passa a derivar o ano de emissao do dhEmi da propria nota, por
  setDhEmi(String) ou pelos construtores que recebem o dhEmi. O ano governa a
  modalidade da monofasia e o conteudo do vNFTot, e a SEFAZ valida os dois pelo
  dhEmi: derivando dele nao ha como divergir. O default pelo relogio da maquina
  continua, mas erra a nota montada do outro lado da virada do ano (dhEmi de
  31/12 montado em 01/01). O valor e validado contra o formato TDateTimeUTC do
  Schema -- ano 20XX, segundos obrigatorios, deslocamento de fuso explicito de
  -11:00 a +12:00 -- e nulo, vazio ou fora do formato lanca NfeException em vez de
  cair no relogio. Mudanca aditiva: setAnoEmissao(int) continua valendo
- setDhEmi e os construtores aceitam tambem a propria TNFe.InfNFe, lendo o
  ide/dhEmi por dentro: e a forma mais segura, porque nao da para passar uma
  data que nao seja a do documento. infNFe, ide ou dhEmi nulos lancam
  NfeException dizendo qual nivel falta. Nota: setDhEmi(null) literal nao
  compila, por ambiguidade entre as sobrecargas -- a chamada sempre estaria
  errada, e falhar na compilacao e melhor que em execucao
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
