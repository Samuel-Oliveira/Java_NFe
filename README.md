# Java-NFe [![Build Status](https://travis-ci.org/Samuel-Oliveira/Java_NFe.svg?branch=master)](https://travis-ci.org/Samuel-Oliveira/Java_NFe)
Projeto Para implementação do Java-Nfe Utilizando JAXB.

Para Iniciar : 
- Baixe o java-nfe-3.10.4.jar (https://github.com/Samuel-Oliveira/Java_NFe/raw/master/java-nfe-3.10.4.jar) e o adicione às bibliotecas de Seu Projeto.
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
			<version>3.10.4</version>
		</dependency>
```

Veja a Wiki https://github.com/Samuel-Oliveira/Java_NFe/wiki, para ter um Tutorial Completo.

________________________________________________________________________________________________

# Historico de Versões

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
