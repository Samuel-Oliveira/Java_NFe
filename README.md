# Java-NFe [![Build Status](https://travis-ci.org/Samuel-Oliveira/Java_NFe.svg?branch=master)](https://travis-ci.org/Samuel-Oliveira/Java_NFe)
Projeto Para implementação do Java-Nfe Utilizando JAXB.

## Gostou do Projeto? Dê sua colaboração: [![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=TX9K693QQYA6W)

Para Iniciar : 
- Baixe o java-nfe-3.10.7.jar (https://github.com/Samuel-Oliveira/Java_NFe/raw/master/java-nfe-3.10.7.jar) e o adicione às bibliotecas de Seu Projeto.
- Baixe o Schemas.rar (https://github.com/Samuel-Oliveira/Java_NFe/raw/master/Schemas.rar) e extraia na sua Máquina.

- Maven :
```
	    <repository>
			<name>Repositorio Autocom SnapShot</name>
			<id>Snapshot</id>
			<url>http://www.autocomsistemas.com.br:8081/nexus/content/repositories/autocom/</url>
		</repository>
		<dependency>
			<groupId>br.com.samuelweb</groupId>
			<artifactId>java-nfe</artifactId>
			<version>3.10.7</version>
		</dependency>
```

Veja a Wiki https://github.com/Samuel-Oliveira/Java_NFe/wiki, para ter um Tutorial Completo.

________________________________________________________________________________________________

# Historico de Versões

## v3.10.7 - 
- Issues: 
- Correção Endereço WebService MT.
- Adicionado Cacert com Alterações do AM
- Adicionado Cacert com Alteracao da BA
- Removido Protocol
- Alterado Configurações de Certificado SSL para SocketDinamico 
- Schema Atualizado com XSD NfeDistribuicao 1.01

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

Outras Duvidas Entrar em Contato samuk.exe@hotmail.com
