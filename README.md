# Java-NFe [![Build Status](https://travis-ci.org/Samuel-Oliveira/Java_NFe.svg?branch=master)](https://travis-ci.org/Samuel-Oliveira/Java_NFe) [![MIT License](https://img.shields.io/github/license/Samuel-Oliveira/Java_NFe.svg) ](https://github.com/Samuel-Oliveira/Java_NFe/blob/master/LICENSE) [![Maven Central](https://img.shields.io/maven-central/v/br.com.swconsultoria/java-nfe.svg?label=Maven%20Central)](https://search.maven.org/artifact/br.com.swconsultoria/java-nfe/4.00.11/jar) [![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/Samuel-Oliveira/Java_NFe.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/Samuel-Oliveira/Java_NFe/context:java) [![Total alerts](https://img.shields.io/lgtm/alerts/g/Samuel-Oliveira/Java_NFe.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/Samuel-Oliveira/Java_NFe/alerts/)
Biblioteca Java para consumo do WebService de NFe/NFCe

## Dúvidas, Sugestões ou Consultoria
Entre no Discord do Projeto: https://discord.gg/ZXpqnaV

## Gostou do Projeto? Dê sua colaboração: [![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=TX9K693QQYA6W)

Para Iniciar : 
- Caso use Libs baixe o java-nfe-4.00.11.jar (https://github.com/Samuel-Oliveira/Java_NFe/raw/master/java-nfe-4.00.11.jar) e o adicione às bibliotecas de Seu Projeto.

- Baixe o Schemas.rar (https://github.com/Samuel-Oliveira/Java_NFe/raw/master/Schemas.rar) e extraia na sua Máquina.

- Maven :
```
<dependency>
    <groupId>br.com.swconsultoria</groupId>
    <artifactId>java-nfe</artifactId>
    <version>4.00.11</version>
</dependency>
```

Veja a Wiki https://github.com/Samuel-Oliveira/Java_NFe/wiki, para ter um Tutorial Completo.

________________________________________________________________________________________________

# Historico de Versões
<!-- 
## v4.00.X - SNAPSHOT
Snapshot é a versão que se encontra em teste, só use em caso de extrema necessidade.
Para usar, adicione o repositorio de Snapshot ao Maven:
```
<repositories>
    <repository>
        <id>sonatype-nexus-snapshots</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </repository>
</repositories>
<dependency>
    <groupId>br.com.swconsultoria</groupId>
    <artifactId>java-nfe</artifactId>
    <version>4.00.X-SNAPSHOT</version>
</dependency>
```
Ou baixe o Jar aqui: https://github.com/Samuel-Oliveira/Java_NFe/raw/master/java-nfe-4.00.11-SNAPSHOT.jar
-->

## v4.00.11 - 26/05/2019
- Retirado Exception Genérica
- Retirado Schemas da Compilação para diminuir tamanho da biblioteca
- Corrigido erro de NFe sem namespace PR
- Adicionado regra de Produtor rural para Chave Da Nfe
- Atualizado Cacert
- Adicionado Opção de Informar o ZoneID na montagem dos Eventos
- Adicionado validação de certificado com documento correto.
- Corrigido Consulta Cadastro MT
- Corrigido URL Consulta NFCe: AC, AL, AP, DF, ES, MA, PA, PB, PE, PI, RJ, RN, RO, RS, RR, SE, TO.
- Alterado Validação de CNPJ com certificado, apenas para o CNPj Raiz (8 primeiras posições)
- Correção de algumas Exceptions
- Refatoração de algumas classes
- Inicio testes Unitários.
- Adicionado parametro arquivoWebService em ConfiguracoesNfe para informar Arquivo de WebService manualmente.

## v4.00.10 - 20/03/2019
   **Guia de Migração: https://gist.github.com/Samuel-Oliveira/c547decad469f21ff99de1c766bdc75d**
 - Adicionado Compatibilidade para Java 11
 - Adicionado URL NFCe MG Produção e Homologação
 - Adicionado Conversao para TRetConsReciNFe
 - Adicionado Conversao para TRetEnvEvento para todos os eventos
 - Adicionado Conversao para TRetInut
 - Retirado XsdUtil do Nfe-Xsd e adicionado ao projeto principal
 - Adicionado JavaDoc Em algumas classes(Agradecimento ao Cristofer)
 - Corrigido erro SVC-AN
 - Corrigido URL consulta QrCode MG
 - Agora aceita Acentos na Emissão
 - Verificação de Certificado Vencido
 - Removido as Contanstes e Substituidos por Enums
 - Adicionado Envio de Eventos por Lote
 - Corrigigido erro 411 MG
 - Adicionado Cancelamento por Substituição (NFCe)
 - Atualizado Schemas com as Notas tecnicas 2018.005 v1.00, v1.10 e v1.20
  
## v4.00.9 - 16/12/2018
 - Corrigido URLs PE
 - Adicionado metodo Que remove Acentos Automaticamente.
 - Corrigido URL Consulta Csdastro MG
 - Corrigido URL Qrcode TO
 - Atualizado Cacert
 
## v4.00.8 - 11/10/2018
 - Adicionado Melhorias
 - Corrigo erros acentos
 - Corrigido Urls MT NFe Homologação
 - Corrigido Urls PE NFCe Homologação/Produção
 - Corrigido Erro Pontuação na Validação
 - Adicionado URLs MG Nfce
 - Atualizado Cacert

## v4.00.7 - 03/09/2018 
- Alterado URL MS
- Adicionado URLS AM
- Corrigido erro Consulta Cadastro SVRS
- Agora são aceitos caracteres especiais no XML.
- Criado Metódo para criação do ProcEvento do cancelamento.
- Corrigido ProcInut
- Alterado QrCode para versão 2
** Leia para mais informações https://gist.github.com/Samuel-Oliveira/72f88a91c72465e038dbecd065fe1e12 ** 

## v4.00.6 - 03/07/2018
- Alterado schemas para versão 1.50
- Alterado URLs NFCe MT
- Removido Urls Versão 1.00, 2.00 e 3.10
- Alterado schemas para versão 1.60
- Adicionado Classe para Gerar ID NFE;
- Alterado schemas para versão 1.60b
- **Atenção: Atualizado Schemas faça o download Novamente.** 

## v4.00.5 - 17-06-2018
- Corrigido URLs QrCode Bahia
- Adicionado URLs NFe Produção Amazonas
- Alterado Urls NFce Homologação Ms
- Alterado Urls Consulta NFce GO
- Adicionado Schemas Versão v1_51
- Adicionado Evento Epec
- Atualizado Cacert
- Atualizado Implementação para Ambiente Web
- **Atenção: Atualizado Schemas faça o download Novamente.** 

## v4.00.4 - 10-05-2018
- Adicionado URLS CE Nfe Produção
- Adicionado URLS PE Nfe Produção
- Adicionado URLS RS Nfce Produção
- Adicionado URLS BA Nfce/Nfe Produção e Homologação
- Adicionado URLS SP NFCe Produção
- Adicionado URLS SVRS NFCe Produção
- Adicionado URLS MG NFC Produção
- Adicionado Compatibilidade de Doias A3 conectados na Mesma Maquina!
- Adicionado Possibilidade de pegar o Certificado(Windows) pelo CNPJ 
- Adicionado Consulta Cadastro 4.00 a todos os Estados
- Adicionado WSDL Consulta Cadastro Exclusivo para o RS
- Adicionado parametro para passar o estado da Consulta, no Consulta Cadastro.

## v4.00.3 - 12-03-2018
- Adicionado Conversão de TRetEnviNFe para XML.
- Adicionado Consumo Indevido ao StatusEnum.
- Corrigido Erro de Inicialização de Certificado nos Eventos
- Adicionado opção para validar ou não a Inutilização
- Corrigido Erro de Unknow CA
- Atualizado Cacert
- Adicioando Conversão de TRetConsSitNFe
- Adicionado URLS MT Nfce Homologação
- Corrigido URL de Consulta NFCe SP,PR e PA
- Tratamento para Adicionar justificativa de Manifestção somente no evento de Operação não Realizada.

## v4.00.2 - 13-02-2018
- Adicionado Metodo Para Gerar ProcInutilização (Para Armazenamento)
- Adicionado Diversas URL de WS
- Corrigido Schemas de Cancelamento
- Corrigido Erro De Integração com CTE.
- Adicionado Parametro para TimeOut Especifico

## v4.00.1 - 28-01-2018
- Versão Inicial 4.00 

## v3.10.9 - 26-01-2018
- Atualziado Cacert
- Corrigido URL WS de PE
- Corrigido URL WS de ES
- Ultima Versão 3.10

## v3.10.8 - 04-07-2017
- Corrigido Erro ao Gerar XML de NFC-e sem CDATA no QrCode
- Removido WebService especial da Bahia Para Nfce
- Adicionado Compatibilidade com Certificado A3 fora do Repositorio de Windows
- Adicionado Exception Espeficifica para Erros de Validação
- Adicionado Java_Docs
- Corrigido erros ao Consultar Cadastro de Outro Estado diferente da Configuração
- Adicionado Exception ao não encontrar WebService.
- Removido Metodos e Constantes de Download e Consulta Destinatario 
- Removido a Parte de Certificado Digitais para outro Projeto.
- Melhorado Classe Socket DInamico
- **Atenção: Alterado o caminho do Objeto Certificado, refaça os Imports.** 

## v3.10.7 - 23-05-2017
- Correção Endereço WebService MT.
- Adicionado Cacert com Alterações do AM
- Adicionado Cacert com Alteracao da BA
- Removido Protocol
- Alterado Configurações de Certificado SSL para SocketDinamico 
- Adicionado Serviço De Consulta Cadastro.
- Adicionado Schemas de Consulta Cadastro.
- Alterado Projeto XSD para versão 8.i.2_DFe1.02a

## v3.10.6 - 21-02-2017
- Issues: # 21, #22 e #23.
- Adicionado Metodo para Ler Arquivo XML.
- Adicionado Stubs da Bahia :
   - Status Serviço
   - Consulta Xml
   - Inutilização
- Arquivo Cacert Atualizado
- Correção Endereço WebService MG.
- Adicionado XSD DistribuicaoDFe 1.01
- Adicionado Função PAra Download de Nfe Por Chave.
   
## v3.10.5 - 21-11-2016
- Issues: #15, #16, #17 e #18.
- Alterado Estrutura para as todas as funções que necessitam de Endereço WebService
- Adicionado Constantes
- Adicionado Endereços ConsultaQrcode
- Corrigido Erro De QrCode não vir no XML FInal
- Modo Assincorno Implementado.
- Modo De Contingencia Implementado.
- **Atenção: Alterado a chamada dos metodos: statusServico, consultaXml, consultaXml, inutilizacao, enviarNfe, cancelarNfe, cce
  Todos eles devem adicionar o parametro tipo, ondeve informar se é NFE(ConstantesUtil.NFE) ou NFC-e(ConstantesUtil.NFCE)** 
   
## v3.10.4 - 01-11-2016
- Issues: #10, #11 e #12.
- Corrigido erro especifico do WebServices SVRS, referente ao nameSpace.
- Melhorado Lógica de Replace para os "Lixos" gerados ao Converter Objeto pra Xml
- Adicionado Proxy

## v3.10.3 - 17-10-2016
- Issues: #5, #6, #7 e #8.
- Corrigido erro especifico da Sefaz de PE, referente ao cabeçalho SOAP.
- Corrigido erro de QrCode Vazio.
- Corrigido erro que acontece ao tentar Manifestar a Nfe, sem fazer a validação.
- Adicionado função que remove Acentos no XML antes de assinar a Nota.

## v3.10.2 - 07-10-2016
- Adicionado Compatibilidade com NFC-e 
- Adicionado Método de geração de qrCode : NFCeUtil.getCodeQRCode
- Adicionado Possibilidade de Usar certificado Digital Fisico (Arquivo .pfx)
- Adicionado possibilidade de escolher entre Validar ou não o XMl Antes do Envio de todas as Funções;
- Disponibilizado projeto para download via Maven

## v3.10.1 - 05-10-2016
- Correção de Erros ao listar Certificados Windows Certificados digitais A3 Ausentes.

## v3.10.0 - 26-09-2016
- Versão Inicial Do Sistema