# Configurações

Para executar qualquer função NFe/NFCe, deve-se inicializar as Configuração.

### Certificado Digital
```java 
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;

public ConfiguracoesNfe iniciaConfigurações() throws NfeException {
    Certificado certificado = // Obter certificado (1)
        
    return ConfiguracoesNfe.criarConfiguracoes(
        EstadosEnum.GO, // (2)
        AmbienteEnum.HOMOLOGACAO, // (3)
        certificado, 
        "C:\\SRA\\Nfe\\Schemas" // (4)
    );
}
```

1.  Para obter o código de uso do certificado, você deve acessar em [Java_Certificado](https://github.com/Samuel-Oliveira/Java_Certificado/)
2.  Obtendo estado a partir de String EstadosEnum.valueOf("GO")
3.  Obtendo ambiente a partir de String AmbienteEnum.getByCodigo("2")
4.  Caminho da pasta que se encontra os Schemas Xsd


### Proxy

Para Habilitar o Proxy, ao iniciar as configurações Adicione as Seguintes Linhas:

```java
public static ConfiguracoesIniciaisNfe iniciaConfigurações() throws NfeException {
    // Certificado Arquivo, Parametros: -Caminho Certificado, - Senha
    Certificado certificado = CertificadoService.certificadoPfx("c:/certificado/certificado.pfx", "123456");

    ConfiguracoesIniciaisNfe config = ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.GO , ConstantesUtil.AMBIENTE.HOMOLOGACAO,certificado, "C:\\SRA\\Nfe\\Schemas");

    String ip = "192.168.0.1";
    String porta = "3128";
    String usuario = "";
    String senha = "";

    config.setProxy(ip, porta, usuario , senha);

    return config;
}
```

### Contingência Scan

Para Trabalhar com Contingência Scan, após iniciar as Configurações Set a Propriedade como True:

```java
public static ConfiguracoesIniciaisNfe iniciaConfigurações() throws NfeException {
    // Certificado Arquivo, Parametros: -Caminho Certificado, - Senha
    Certificado certificado = CertificadoService.certificadoPfx(
        "c:/certificado/certificado.pfx", 
        "123456"
    );

    ConfiguracoesIniciaisNfe config = ConfiguracoesIniciaisNfe.iniciaConfiguracoes(
        Estados.GO, 
        ConstantesUtil.AMBIENTE.HOMOLOGACAO,
        certificado, 
        "C:\\SRA\\Nfe\\Schemas"
    );

    config.setContigenciaSCAN(true);

    return config;
}
```

