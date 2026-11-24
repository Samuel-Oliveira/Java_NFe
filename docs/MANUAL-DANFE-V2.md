# Manual de integração — impressão v2 (DANFE NF-e, DANFE NFC-e e CC-e)

Guia para implementar a impressão da biblioteca `java-nfe` num sistema: o DANFE da NF-e (modelo 55), o DANFE NFC-e
(modelo 65) e a Carta de Correção eletrônica. O DANFE da NF-e segue a **NT 2026.010 — DANFE Reforma Tributária
(IBS/CBS/IS)**, em produção desde **01/12/2026**.

> **A impressão Jasper (v1) foi removida** na versão seguinte à `4.3.1`: `ImpressaoNfeUtil`, `ImpressaoDTO`, `JasperNFeEnum`, os `.jrxml`/`.jasper` e as dependências `jasperreports` e `barbecue`. A v2 é a impressão oficial. A seção 4 e o [MIGRATION.md](../MIGRATION.md) mostram a troca.

> Leia o documento inteiro antes de começar. A seção 9 traz um checklist de implementação e a seção 10 os testes de aceitação.

---

## 1. O que mudou em relação à v1

| | v1 (removida) | v2 (oficial) |
|---|---|---|
| Motor | JasperReports (`.jrxml`/`.jasper`) | Thymeleaf (HTML/CSS) → PDF via openhtmltopdf |
| Documentos | NF-e, NFC-e, CC-e | NF-e, NFC-e, CC-e |
| Leiaute NF-e | Antigo (sem IBS/CBS/IS) | NT 2026.010: retrato e paisagem; NT 2026.003: DANFE Simplificado Tipo 2 em bobina (`tpImp=6`, seção 7.4) |
| Leiaute NFC-e | Bobina de 72 mm | Bobina de 80 mm (ou outra largura), no mesmo template do Simplificado Tipo 2 (seção 7.5) |
| Fonte de dados | XML | XML (sempre; não existe montagem manual de objeto) |
| Pacote | `br.com.swconsultoria.nfe.impressao` | `br.com.swconsultoria.nfe.impressao.v2` |
| Classes | `ImpressaoNfeUtil` + `ImpressaoDTO` | `ImpressaoDanfeV2Util` + `ImpressaoDanfeV2DTO` |
| Exceções | Checked (`JRException`, `IOException`...) | `DanfeException` (RuntimeException) |

Todo o resto do `java-nfe` (envio, consultas, eventos, `IbsCbsUtil`...) não muda.

---

## 2. Dependência

A v2 entra na versão `4.3.*`. Enquanto ela não estiver publicada no Maven Central (a última publicada é a `4.2.0`, que **não** tem a v2), gere-a a partir do código-fonte e instale no repositório Maven local, como descrito em "Gerar a versão local" abaixo.

**Maven**
```xml
<dependency>
    <groupId>br.com.swconsultoria</groupId>
    <artifactId>java-nfe</artifactId>
    <version>4.3.0</version>
</dependency>
```

**Gradle** (o `mavenLocal()` é necessário enquanto a versão vier do repositório local)
```groovy
repositories { mavenLocal(); mavenCentral() }
dependencies { implementation "br.com.swconsultoria:java-nfe:4.3.0" }
```

**Gerar a versão local** (a partir do checkout deste repositório):
```bash
mvn clean install -Dgpg.skip
```
Isso instala `java-nfe-4.3.0.jar`, `-sources.jar`, `-javadoc.jar` e o `.pom` no repositório Maven local. O jar é "fino" (as dependências normais do `java-nfe`, como `java-certificado`, vêm por transitividade pelo `.pom`). `-Dgpg.skip` pula a assinatura GPG da fase `verify`. Os testes (mais de 2.200, incluindo os da v2) rodam no build; `-DskipTests` pula. Para um jar com todas as dependências (projetos sem Maven/Gradle) acrescente `-Passembly`: ele **substitui** o jar fino no `target/` e no repositório local, já com as bibliotecas de impressão da seção 2.1.

### 2.1 Dependências de impressão

As bibliotecas de impressão **vêm por transitividade** com o `java-nfe` (escopo `compile`): o projeto consumidor não declara nada além do próprio `java-nfe`. São elas:

| Biblioteca | Versão | Para quê |
|---|---|---|
| `org.thymeleaf:thymeleaf` | 3.1.2.RELEASE | template HTML |
| `com.openhtmltopdf:openhtmltopdf-pdfbox` | 1.0.10 | HTML para PDF |
| `com.google.zxing:core` / `javase` | 3.2.1 | código de barras e QR Code |

Um sistema que só transmite notas e não quer o peso delas pode excluí-las no próprio `pom.xml`:

```xml
<dependency>
    <groupId>br.com.swconsultoria</groupId>
    <artifactId>java-nfe</artifactId>
    <version>...</version>
    <exclusions>
        <exclusion><groupId>org.thymeleaf</groupId><artifactId>*</artifactId></exclusion>
        <exclusion><groupId>com.openhtmltopdf</groupId><artifactId>*</artifactId></exclusion>
        <exclusion><groupId>com.google.zxing</groupId><artifactId>*</artifactId></exclusion>
    </exclusions>
</dependency>
```

Até a 4.3.1 elas eram `provided` e o consumidor as declarava; essas declarações podem sair (ou ficar, desde que nas mesmas versões). `jasperreports`, `jasperreports-fonts` e `barbecue`, que a v1 exigia, podem sair do projeto se nada mais nele as usar.

### 2.2 Requisitos e conflitos possíveis

- **Java 8 ou superior**. A biblioteca é compilada para 1.8.
- Dependências transitivas das bibliotecas acima:

  | Biblioteca | Versão |
  |---|---|
  | `ognl` | 3.3.4 |
  | `attoparser` | 2.0.7 |
  | `unbescape` | 1.1.6 |
  | `slf4j-api` | 2.0.7 |
  | `org.apache.pdfbox:pdfbox` / `fontbox` / `xmpbox` | 2.0.24 |

- Verifique conflitos no projeto consumidor: o Maven fica com a versão mais próxima da raiz, então uma versão que o projeto já declare (ou herde de um BOM) vence a do `java-nfe`:
  - **Spring Boot 2.x** traz Thymeleaf 3.0.x. A v2 precisa da 3.1.x. Force `thymeleaf` 3.1.2 no `dependencyManagement` ou exclua a versão antiga. Spring Boot 3.x já usa a 3.1.
  - **PDFBox 3.x** no projeto é incompatível com o openhtmltopdf 1.0.10, que precisa da 2.0.x.
- Se o projeto não tiver uma implementação de SLF4J, aparece o aviso `SLF4J: No SLF4J providers were found`. É inofensivo.
- **Log do openhtmltopdf:** a cada PDF ele registra várias linhas INFO pelo `java.util.logging`. A biblioteca **não altera** essa configuração, que pertence à aplicação. Para silenciar, na inicialização do sistema:
  ```java
  for (String logger : com.openhtmltopdf.util.XRLog.listRegisteredLoggers()) {
      com.openhtmltopdf.util.XRLog.setLevel(logger, java.util.logging.Level.WARNING);
  }
  // ou, para desligar de vez: XRLog.setLoggingEnabled(false);
  ```
- **Thread-safety:** `ImpressaoDanfeV2Util` é stateless e pode ser chamado em paralelo. Crie um `ImpressaoDanfeV2DTO` por impressão.

### 2.3 Teste rápido da dependência

Antes de mexer no sistema, confirme que a versão local e as dependências de impressão chegaram com uma classe descartável:

```java
import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2DTO;
import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2Util;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;

public class TesteDanfeV2 {
    public static void main(String[] args) throws Exception {
        // DanfeV2Rtc.xml fica em src/test/resources deste repositório
        String xml = XmlNfeUtil.leXml("caminho/para/DanfeV2Rtc.xml");
        ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);
        ImpressaoDanfeV2Util.impressaoPdfArquivo(impressao, "danfe-v2.pdf");
    }
}
```

Esperado: um PDF de 1 folha em retrato, com a marca "SEM VALOR FISCAL", o quadro "TOTAL DO IBS / CBS / IS" e o QR Code. Um `NoClassDefFoundError` aqui é dependência de impressão excluída ou trocada no projeto (seção 11).

---

## 3. Uso básico

```java
import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2DTO;
import br.com.swconsultoria.nfe.impressao.v2.ImpressaoDanfeV2Util;

String xml = /* XML autorizado (nfeProc) como String */;

ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);   // logo padrão, orientação pelo tpImp, com canhoto
// ou: ImpressaoDanfeV2Util.impressaoPadraoNFCe(xmlNfce)                          // DANFE NFC-e em bobina, logo da NFC-e (seção 7.5)
// ou: ImpressaoDanfeV2Util.impressaoPadraoCCe(xmlProcEvento)                     // Carta de Correção em A4, sem logo (seção 7.6)

byte[] pdf = ImpressaoDanfeV2Util.impressaoPdfByte(impressao);                 // PDF em memória
ImpressaoDanfeV2Util.impressaoPdfArquivo(impressao, "danfe.pdf");              // PDF em arquivo
ImpressaoDanfeV2Util.impressaoHtml(impressao, "danfe.html");                   // HTML em arquivo
String html = ImpressaoDanfeV2Util.gerarHtml(impressao);                       // HTML em String
ImpressaoDanfeV2Util.impressaoDireta(impressao);                               // impressora padrão
ImpressaoDanfeV2Util.impressaoDireta(impressao, printService);                 // impressora específica (javax.print.PrintService)
```

**XML aceito:** `nfeProc` (com `protNFe`) ou `NFe` sem protocolo. Neste caso a chave vem do `infNFe/@Id` e o protocolo sai em branco. Para a CC-e, `procEventoNFe` ou o `evento` sem o retorno. O template padrão é escolhido pelo XML: modelo 55 no A4 (ou na bobina com `tpImp=6`), modelo 65 na bobina, evento 110110 na CC-e. O XML deve vir como String já decodificada (`XmlNfeUtil.leXml` serve; o BOM de arquivos UTF-8 é descartado). Se você lê arquivos de outro jeito, use o charset correto (normalmente UTF-8).

**Erros:** tudo lança `br.com.swconsultoria.nfe.exception.DanfeException` (a mesma da v1):
- XML vazio ou malformado;
- XML sem `infNFe` nem `evento/infEvento`;
- evento que não é CC-e sem template próprio;
- template próprio inexistente ou inválido;
- falha ao gravar o arquivo;
- impressora não encontrada.

---

## 4. Migração v1 → v2

A v1 não existe mais no jar: o código que a usa não compila depois da atualização. Troque assim:

| v1 | v2 |
|---|---|
| `ImpressaoNfeUtil.impressaoPadraoNFe(xml)` | `ImpressaoDanfeV2Util.impressaoPadraoNFe(xml)` |
| `ImpressaoNfeUtil.impressaoPadraoNFCe(xml, urlConsulta)` | `ImpressaoDanfeV2Util.impressaoPadraoNFCe(xml)`: a URL de consulta vem do `infNFeSupl/urlChave` do XML, obrigatório na NFC-e 4.00 |
| `ImpressaoNfeUtil.impressaoPadraoCCe(xml)` | `ImpressaoDanfeV2Util.impressaoPadraoCCe(xml)` |
| `ImpressaoDTO` | `ImpressaoDanfeV2DTO` |
| `ImpressaoNfeUtil.impressaoPdfArquivo(impressao, destino)` | `ImpressaoDanfeV2Util.impressaoPdfArquivo(impressao, destino)` |
| `ImpressaoNfeUtil.impressaoPdfByte(impressao)` | `ImpressaoDanfeV2Util.impressaoPdfByte(impressao)` |
| `ImpressaoNfeUtil.impressaoHtml(impressao, destino)` | `ImpressaoDanfeV2Util.impressaoHtml(impressao, destino)` |
| `ImpressaoNfeUtil.impressaoDireta(impressao[, impressora])` | `ImpressaoDanfeV2Util.impressaoDireta(impressao[, impressora])` |
| `ImpressaoNfeUtil.impressaoDireta(impressao, impressora, SimplePrintServiceExporterConfiguration)` | Não existe. Para controlar cópias e bandeja, gere o PDF (`impressaoPdfByte`) e imprima com PDFBox/`PrinterJob` no seu código |
| `ImpressaoNfeUtil.impressaoPreview(impressao)` (`JasperViewer`) | **Não existe.** Gere o PDF e abra num visualizador (`Desktop.getDesktop().open(arquivo)`, componente PDF da aplicação, navegador) |
| `impressao.getParametros().put("Logo", inputStream)` | `impressao.setLogo(inputStream)` |
| `impressao.setJasper(meuJasperReport)`, `setPathExpression(...)` | `impressao.setTemplate("caminho/meu-danfe.html")`: um `.jasper` próprio precisa ser refeito como template Thymeleaf (seção 5.1) |
| `ConstantesImpressaoNfeUtil.PATH_NFE`, `PATH_NFCE`, `PATH_CCE`, `PARAM_LOGO_NFE`, `PARAM_LOGO_NFCE` | Removidas (eram do datasource Jasper); `PATH_LOGO_NFE` e `PATH_LOGO_NFCE` continuam |
| `throws JRException, IOException, ...` | `DanfeException` (unchecked) |

Quem precisa manter um `.jasper` próprio por mais tempo pode ficar na `4.3.1`, a última versão com a v1, até refazer o leiaute.

---

## 5. Personalização

```java
ImpressaoDanfeV2DTO impressao = ImpressaoDanfeV2Util.impressaoPadraoNFe(xml);

// Logo (PNG, JPEG, GIF ou BMP). SVG não é suportado.
impressao.setLogo(inputStream);            // lê e FECHA o stream
impressao.setLogo(bytes);
impressao.setLogoArquivo("c:/logos/empresa.png");
impressao.removerLogo();                   // sem logo (setLogo(null) não compila: há duas sobrecargas)

// Orientação: AUTOMATICA (padrão, usa ide/tpImp: 2 = paisagem), RETRATO ou PAISAGEM
impressao.setOrientacao(OrientacaoDanfeEnum.PAISAGEM);

// Canhoto (NT 2026.010 permite suprimir quando o emitente não usa)
impressao.setExibirCanhoto(false);

// DANFE Simplificado Tipo 2 (tpImp=6, seção 7.4): largura da bobina e segunda via da contingência
impressao.setLarguraPapelMm(58);           // padrão 80; mínimo 56 (NT 2026.003 item 3.3)
impressao.setViaEstabelecimento(true);     // "Via do Estabelecimento" em vez de "Via do Consumidor"

// Parâmetros livres, disponíveis no template como ${parametros.nome}
impressao.getParametros().put(ImpressaoDanfeV2Util.PARAM_TIPO_REGIME_APURACAO, "...");   // usado pelo template padrão; tem prioridade sobre o XML (ver 7.3)

// Template próprio (arquivo .html Thymeleaf)
impressao.setTemplate("c:/templates/meu-danfe.html");
```

> **Caminhos são configuração do servidor, nunca entrada do usuário final.** `setTemplate` e `setLogoArquivo` abrem o arquivo informado onde ele estiver (o template é procurado primeiro como caminho no sistema de arquivos e só depois no classpath), do mesmo modo que o `setJasper` da v1. Um caminho vindo de um campo de tela ou de uma requisição permitiria ler ou executar como template qualquer arquivo que a aplicação enxergue.

### 5.1 Template próprio

Use quando o leiaute padrão não atender, por exemplo para um bloco extra ou uma marca do cliente. O ponto de partida recomendado é copiar o template padrão, que está dentro do jar em `danfe/v2/danfe.html`, e alterá-lo. O código-fonte fica em `src/main/resources/danfe/v2/`.

**Variáveis disponíveis no template:**

| Variável | Tipo | Uso |
|---|---|---|
| `danfe` | `DanfeV2DTO` | Dados da nota (ver seção 6); não existe no template de evento |
| `evento` | `EventoV2DTO` | Dados do evento (seção 7.6); só no template de evento |
| `fmt` | `FormatadorDanfeV2` | Formatação: `${fmt.valor(danfe.icmsTot.vNF)}` |
| `xml` | `XmlDanfeV2` | Qualquer campo do XML via XPath: `${xml.valor('//infNFe/ide/tpEmis')}` |
| `logo` | String (data URI) ou null | `<img th:if="${logo != null}" th:src="${logo}" />` |
| `codigoBarras` | String (data URI) | Code-128C da chave (zxing) |
| `qrCode` | String (data URI) ou null | QR Code do `infNFeSupl/qrCode` (zxing) |
| `paisagem` | boolean | Orientação resolvida (DANFE A4) |
| `exibirCanhoto` | boolean | (DANFE A4) |
| `larguraPapelMm`, `alturaPaginaMm`, `qrCodeLateral`, `viaEstabelecimento` | int / boolean | DANFE em bobina (seção 7.4) |
| `parametros` | `Map<String,Object>` | Valores livres de `getParametros()` |

**Fragmentos reaproveitáveis** (arquivo `fragmentos.html` do jar). Um template próprio pode referenciá-los, porque os nomes que não existem como arquivo são buscados no classpath:
```html
<td th:replace="~{fragmentos :: campo('RÓTULO', ${valor}, '25%')}"></td>
<div th:replace="~{fragmentos :: cabecalho}"></div>
<div th:replace="~{fragmentos :: canhoto}"></div>
```

**Métodos de `fmt`:**
- valores: `valor`, `quantidade`, `valorUnitario` (2 a 10 casas, sem arredondar o XML), `aliquota` (já com `%`), `adRem` (R$ por unidade, 4 casas), `peso`;
- documentos e contato: `documento` (CNPJ/CPF), `cep`, `fone`, `chave` (grupos de 4);
- numeração: `numeroNota` (000.000.000), `serie` (000);
- datas: `data`, `hora`, `dataHora` (a partir de ISO), `competencia` (aaaa-mm para mm/aaaa);
- códigos: `modFrete`, `crt`, `tipoRegimeApuracao`, `cst(orig, cst)`;
- textos: `endereco(grupo)`, `primeiro(a, b)`, `complementoIbsCbs(item)` (retenção, retido, diferença e competência do IBS/CBS numa linha).

Todos aceitam null e devolvem `""`. Os números arredondam HALF_UP. Ao concatenar valores do XML com `+`, passe-os por `fmt.primeiro(valor, '')` ou por outro método do `fmt`: o OGNL imprime `null` como texto quando a tag não existe.

### 5.2 Regras obrigatórias para templates (openhtmltopdf + Thymeleaf)

1. **O HTML precisa ser XHTML bem formado:**
   - tags fechadas: `<br />`, `<img ... />`, `<meta ... />`;
   - atributos entre aspas;
   - sem `&nbsp;` (use `&#160;`).
2. **Use CSS 2.1 + paged media.**
   - Não há flexbox nem grid. Faça o layout com `<table>` e `table-layout: fixed`.
   - Funcionam: `@page { size: A4 landscape; }`, `counter(page)`, `counter(pages)`, `position: running(...)`, `transform: rotate()` e `position: fixed`, que repete o elemento em todas as folhas.
3. **Não use `+` para juntar códigos numéricos.** O Thymeleaf soma strings que parecem números: `'0' + '00'` resulta em `0`. Use um método do `fmt` (como `fmt.cst`) ou literal com texto (`'Nº ' + ...`).
4. **Para repetir o `<thead>` dos itens em cada folha:**
   - a tabela precisa de `-fs-table-paginate: paginate;` e `border-collapse: separate;`;
   - **não** use `overflow: hidden` em `td`/`th` dessa tabela.
5. **`th:if` junto com `th:replace` no mesmo elemento não funciona:** o `th:replace` roda primeiro. Envolva com `<th:block th:if="...">`.
6. **Fontes:** só as padrão do PDF (Helvetica). Acentos funcionam.
7. **Nada pode passar da área da página.** Um elemento posicionado que ultrapassa a altura da folha gera uma folha em branco.
8. **O quadro do emitente tem altura fixa** (`.emitente-caixa`, 25 mm): o cabeçalho repetido nas folhas 2+ vive na margem superior fixa de 43 mm e não pode crescer com o texto. Nome com mais de 40 caracteres sai em fonte menor; o excedente é cortado.
9. **Na paisagem, a faixa de 25 mm do canhoto só existe com `exibirCanhoto`** (está num `<style th:if="${paisagem and exibirCanhoto}">` separado). Sem canhoto o conteúdo usa a largura toda.
10. **Para o bloco de dados adicionais encostar no rodapé**, o template precisa de um `<div id="espaco-dados-adicionais"></div>` vazio imediatamente antes do bloco com `id="dados-adicionais"`, e o bloco não pode ter margem superior (o respiro fica na altura padrão do espaçador, no CSS). `ImpressaoDanfeV2Util.htmlParaPdf` diagrama o documento, mede quanto sobra na folha em que o bloco caiu, acrescenta essa altura ao `style` do espaçador (um `style` inline que o template já tenha é preservado, mas o `height` calculado prevalece) e diagrama de novo. Sem os dois marcadores o bloco sai logo após os itens. Os ids estão nas constantes `ID_ESPACO_DADOS_ADICIONAIS` e `ID_DADOS_ADICIONAIS`.

---

## 6. Modelo de dados (`DanfeV2DTO`)

Cada grupo é um `Map<String,String>` cuja **chave é o nome da tag no XML** e o valor é o texto bruto, sem formatação. Uma tag ausente no XML não aparece no Map (`get` retorna null).

| Grupo | Origem (relativa a `infNFe`) | Chaves |
|---|---|---|
| `chave` | `protNFe/infProt/chNFe` ou `@Id` | (String) |
| `ide` | `ide/` | cUF, natOp, mod, serie, nNF, dhEmi, dhSaiEnt, tpNF, tpImp, tpEmis, tpAmb, finNFe, indPres, verProc, dhCont, xJust |
| `emit` | `emit/` + `emit/enderEmit/` | CNPJ, CPF, xNome, xFant, IE, IEST, IM, CRT, tpRegimeIBSCBS, xLgr, nro, xCpl, xBairro, xMun, UF, CEP, fone |
| `dest` | `dest/` + `dest/enderDest/` | CNPJ, CPF, idEstrangeiro, xNome, IE, email, xLgr, nro, xCpl, xBairro, xMun, UF, CEP, fone |
| `prot` | `//protNFe/infProt/` | nProt, dhRecbto, chNFe, cStat, cMsg, xMsg (o primeiro par) |
| `icmsTot` | `total/ICMSTot/` | vBC, vICMS, vICMSDeson, vFCPUFDest, vICMSUFDest, vICMSUFRemet, vFCP, vBCST, vST, vFCPST, vFCPSTRet, qBCMono, vICMSMono, qBCMonoReten, vICMSMonoReten, qBCMonoRet, vICMSMonoRet, vProd, vFrete, vSeg, vDesc, vII, vIPI, vIPIDevol, vPIS, vCOFINS, vOutro, vNF, vTotTrib |
| `ibsCbsTot` | `total/IBSCBSTot/` | vBCIBSCBS, vIBSUF (`gIBS/gIBSUF`), vIBSMun (`gIBS/gIBSMun`), vIBS, vCBS (`gCBS`), vIBSMono, vCBSMono, vIBSMonoReten, vCBSMonoReten, vIBSMonoRet, vCBSMonoRet (`gMono`) |
| `isTot` | `total/ISTot/` | vIS |
| `issqnTot` | `total/ISSQNtot/` | vServ, vBC, vISS |
| `transp` | `transp/` | modFrete; da transportadora: CNPJ, CPF, xNome, IE, xEnder, xMun, UF; do veículo: placa, **placaUF**, RNTC; do **primeiro** `vol`: qVol, esp, marca, nVol, pesoL, pesoB |
| `infAdic` | `infAdic/` | infCpl, infAdFisco |
| `supl` | `//infNFeSupl/` | qrCode, urlChave |
| `duplicatas` | `cobr/dup` | lista de Maps: nDup, dVenc, vDup |
| `pagamentos` | `pag/detPag` | lista de Maps: indPag, tPag, xPag, vPag |
| `pag` | `pag/` | vTroco |
| `itens` | `det` | lista de Maps (abaixo) |

**Chaves de cada item:**
- nItem, cProd, cEAN, xProd, NCM, CEST, CFOP, uCom, qCom, vUnCom, vProd, infAdProd, orig.
- **CST** (CST ou CSOSN do ICMS).
- **vBCICMS**, pICMS, vICMS.
- **vBCIPI**, pIPI, vIPI.
- **cstIBSCBS** (`IBSCBS/CST`), cClassTrib, **vBCIBSCBS**.
- pIBSUF, vIBSUF, pIBSMun, vIBSMun, vIBS, pCBS, vCBS.
- **pIBSUFVigente**, **pIBSMunVigente**, **pCBSVigente**.
- monofásico (`gIBSCBSMono`): qBCMono, adRemIBS, adRemCBS, vIBSMono, vCBSMono, vIBSMonoReten, vCBSMonoReten, vIBSMonoRet, vCBSMonoRet, vIBSDiferenca, vCBSDiferenca, vTotIBSMonoItem, vTotCBSMonoItem.
- ajuste de competência (`gAjusteCompet`): competApur.
- vBCIS, pIS, vIS.

Os nomes em **negrito** são derivados, criados para evitar colisão entre tags de mesmo nome.

O grupo `imposto/IBSCBS` do item é uma escolha entre `gIBSCBS`, `gIBSCBSMono`, `gTransfCred` e `gAjusteCompet` (`TTribNFe` do schema). As chaves são as mesmas nos quatro casos, para o template não precisar saber de onde vieram:

| Grupo do item | vBCIBSCBS | pIBSUF / pIBSMun / pCBS | vIBSUF / vIBSMun | vIBS / vCBS |
|---|---|---|---|---|
| `gIBSCBS` | `vBC` | `pIBSUF`... (ou `gRed/pAliqEfet`) | `vIBSUF`, `vIBSMun` | `vIBS`, `gCBS/vCBS` |
| monofásico ad valorem | `gMonoPadrao/vBCMono` | `pAliqMonoUF`, `pAliqMonoMun`, `pAliqMonoCBS` | `vIBSMonoUF`, `vIBSMonoMun` | `vIBSMono`, `vCBSMono` |
| monofásico ad rem | (vazio; a base é `qBCMono`, em quantidade) | (vazio; a alíquota é `adRemIBS` / `adRemCBS`, em R$ por unidade) | (vazio) | `vIBSMono`, `vCBSMono` |
| `gTransfCred` / `gAjusteCompet` | (vazio) | (vazio) | (vazio) | `vIBS`, `vCBS` |

O leitor assume que IBS e CBS do mesmo item usam o mesmo regime monofásico (ambos ad rem ou ambos ad valorem), como na tributação de combustíveis. O schema permite misturar; nesse caso `vBCIBSCBS` vem do ad valorem, `qBCMono` do ad rem e o template padrão imprime só a base em R$.

`ibsCbsTot.vIBS` (soma de IBS UF e Município) e `supl.urlChave` são lidos e ficam disponíveis para templates próprios, mas o template padrão não os imprime.

Métodos de decisão (usados pelo template para exibir ou ocultar blocos):
- `isPaisagem`, `isHomologacao`;
- `isExibirFatura`, `getFaturaLinhas` (duplicatas em linhas de 3);
- `isExibirLinhaFcp`, `isExibirLinhaIcmsMono`;
- `isExibirIbsCbsIs`, `isExibirLinhaIbsCbsMono`, `isExibirLinhaIbsCbsMonoRet`;
- `isExibirIssqn`, `isExibirTransporte`, `isExibirQrCode`;
- do DANFE Simplificado Tipo 2: `isSimplificadoTipo2`, `isAmbienteHomologacao` (só `tpAmb=2`), `isContingenciaPendente`, `isPreVisualizacao`, `isExibirAcrescimoDesconto`, `isExibirLinhaIs`, `isExibirTributosTotais`, `getCbsTotal` e `getIbsTotal` (gCBS/gIBS mais o monofásico de `gMono`).

---

## 7. Leiautes e regras implementados

### 7.1 Leiaute

- **Ordem dos quadros:**
  1. Canhoto.
  2. Emitente/DANFE/chave, com CRT e "Tipo de Regime de Apuração do IBS e da CBS".
  3. Destinatário.
  4. Fatura/duplicatas.
  5. Total dos produtos e total da nota.
  6. Total do ICMS/IPI, com as linhas de FCP/DIFAL e ICMS monofásico.
  7. **Total do IBS/CBS/IS**.
  8. ISSQN.
  9. Transporte.
  10. Produtos.
  11. Dados adicionais com QR Code.
- **Itens:** colunas Descrição (com `[NCM] [cClassTrib]`), CST/CFOP (com o CST do IBS/CBS numa segunda linha), QTD/UN, VLR UNIT, VLR TOTAL, **Bases de cálculo** (ICMS, IBS/CBS, IS, IPI), **Alíquotas** (ICMS/CBS, IBS UF/IPI, IBS MUN/IS) e **Valor dos tributos**. Num item monofásico ad rem a linha de base vira "IBS/CBS QTD" (quantidade), as alíquotas saem em R$ por unidade e o valor do IBS sai numa linha única "IBS", sem abertura UF/Município; o mesmo vale para o valor de transferência de crédito e de ajuste de competência. Retenção, retido anteriormente, diferença de biocombustível e competência do ajuste saem numa linha complementar sob a descrição (`fmt.complementoIbsCbs`).
- **Dados adicionais encostados no rodapé da última folha**, como no DANFE Jasper: quando os itens não enchem a folha, o espaço que sobra fica em branco entre a tabela de itens e o bloco. Numa nota de várias folhas o bloco fica no pé da última (no Jasper ele fica sempre no pé da folha 1). O PDF é diagramado duas vezes para isso (ver 5.2, regra 10).
- **Retrato** (`tpImp=1`): canhoto horizontal no topo.
- **Paisagem** (`tpImp=2`): canhoto vertical à direita, na faixa de 25 mm.
- **Folhas 2 em diante:** repetem o quadro do emitente e o cabeçalho da tabela de itens. A numeração "FOLHA n/total" é automática.
- **Homologação** (`tpAmb=2`): marca d'água "SEM VALOR FISCAL" em todas as folhas.

### 7.2 Regra da alíquota (item 4.3)

Quando o item tem o grupo `gRed` (redução de alíquota ou compra governamental), o DANFE imprime a **alíquota efetiva** `gRed/pAliqEfet`. Sem `gRed`, imprime a alíquota vigente. Isso já vem resolvido em `item.pIBSUF`, `item.pIBSMun` e `item.pCBS`. A alíquota vigente fica disponível em `*Vigente`. O IS sempre usa `pIS`.

### 7.3 Campos facultativos (item 4.4)

Um quadro ou campo cuja informação não existe no XML **não é impresso**, e o DANFE nunca inventa valor.

| Quadro | Quando aparece |
|---|---|
| Fatura | Existe `cobr/dup` |
| Linha FCP/DIFAL | Algum valor diferente de zero entre vFCP, vFCPST, vICMSUFDest, vFCPUFDest (vFCP e vFCPST são obrigatórias e vêm como 0.00 em toda NF-e 4.00, por isso presença não basta) |
| Linha ICMS monofásico | Algum valor diferente de zero entre qBCMono, vICMSMono, qBCMonoReten, vICMSMonoReten |
| Total IBS/CBS/IS | Existe `IBSCBSTot` ou `ISTot` (notas antigas não mostram o quadro) |
| Linha IBS/CBS monofásico | Algum valor diferente de zero entre vIBSMono, vCBSMono, vIBSMonoReten, vCBSMonoReten (`gMono`) |
| Linha IBS/CBS monofásico retido anteriormente | Algum valor diferente de zero entre vIBSMonoRet, vCBSMonoRet (`gMono`) |
| ISSQN | Existe `ISSQNtot` |
| Transporte | Existe `transp/modFrete`, obrigatório no XML: sem transportadora, o quadro sai só com "FRETE POR CONTA" preenchido |
| QR Code | Existe `infNFeSupl/qrCode` |
| Canhoto | `setExibirCanhoto(true)` (padrão) |

O campo "Tipo de Regime de Apuração do IBS e da CBS" vem de `emit/tpRegimeIBSCBS` (NT 2026.005), impresso por `fmt.tipoRegimeApuracao`, e sai vazio sem a tag. `PARAM_TIPO_REGIME_APURACAO`, quando informado com valor não nulo, tem prioridade sobre o XML, inclusive `""`, que deixa o campo em branco. Para usar o XML, não informe a chave.

### 7.4 DANFE Simplificado Tipo 2 (NT 2026.003, `tpImp=6`)

A NF-e emitida no lugar de uma NFC-e (Ajuste SINIEF 13/26, regras na NT 2026.002) usa o **DANFE Simplificado Tipo 2**, um cupom em bobina. Com `tpImp=6` no XML, `impressaoPadraoNFe` já sai nesse leiaute, pelo template `danfe/v2/danfe-bobina.html` (`TEMPLATE_BOBINA`), o mesmo do DANFE NFC-e (seção 7.5); o resto da API é o mesmo (`impressaoPdfByte`, `impressaoDireta` etc.). Para imprimir o DANFE A4 de uma nota com `tpImp=6`, informe `setTemplate(ImpressaoDanfeV2Util.TEMPLATE_PADRAO)`.

- **Papel:** bobina de 80 mm por padrão (`setLarguraPapelMm`, mínimo 56 mm). A altura da folha acompanha o conteúdo: o PDF sai numa folha só, do tamanho do cupom, e a nota não quebra em folhas. Margem lateral de 3 mm (a NT pede 2 mm no mínimo).
- **QR Code:** a partir de 72 mm de papel, ao lado do consumidor e da identificação (figura 1A da NT); abaixo disso, centralizado (figura 1B). Sai com 27 mm mais 1,5 mm de margem segura de cada lado (a NT pede 25 mm no mínimo).
- **Divisões, na ordem da NT:**
  1. Cabeçalho: logo (opcional), CNPJ ou CPF, razão social, endereço sem o país e "DANFE Simplificado Tipo 2".
  2. Itens: código e descrição, quantidade e unidade, valor unitário e valor total.
  3. Totais: quantidade de itens distintos, valor total, frete, seguro, outras despesas e desconto (cada linha só com valor).
  4. Novos tributos, quando existe `IBSCBSTot` ou `ISTot`: "(+) CBS R$", "(+) IBS R$" (UF e Município) e "(+) IS R$" (só se houver IS). CBS e IBS somam o monofásico de `gMono`.
  5. "Valor a Pagar R$" (`vNF`), só com acréscimo ou desconto; forma de pagamento (`tPag` por extenso, `xPag` no 99) com o valor pago; troco, sempre (0,00 sem `vTroco`).
  6. "Consulte pela Chave de Acesso em", `infNFeSupl/urlChave` e a chave em 11 blocos.
  7. QR Code, consumidor ("CONSUMIDOR CNPJ:", "CPF:", "ID. ESTRANGEIRO:" ou "NÃO IDENTIFICADO", com nome e endereço quando há) e identificação: número, série, data e hora de emissão, protocolo e data de autorização.
  8. Mensagem fiscal: "EMITIDA EM AMBIENTE DE HOMOLOGAÇÃO - SEM VALOR FISCAL" (`tpAmb=2`), "PRÉ-VISUALIZAÇÃO - SEM VALOR FISCAL" (produção sem protocolo e sem contingência) e `infAdFisco`.
  9. Contribuinte: "Tributos Totais Incidentes (Lei Federal 12.741/2012)" com `vTotTrib` (só com valor) e `infCpl`.
- **Contingência off-line** (`tpEmis` diferente de 1, 6 e 7, ainda sem protocolo): "EMITIDA EM CONTINGÊNCIA / Pendente de autorização" abaixo do cabeçalho, entre linhas, e abaixo da identificação; o protocolo é suprimido. A NT exige uma **segunda via**, que fica no estabelecimento até a autorização: imprima duas vezes, a segunda com `setViaEstabelecimento(true)`. Ao lado da data de emissão sai "Via do Consumidor" ou "Via do Estabelecimento". Depois de autorizada (nfeProc com protocolo), a nota volta a sair sem a contingência e com o protocolo.
- **PDF em bobina num template próprio:** declare a página num `<style id="pagina-bobina">` com `@page { size: <largura>mm <altura>mm; margin: 0; }` e ponha o respiro no `padding` do `body`. `htmlParaPdf` diagrama com essa altura (o template padrão usa `ALTURA_MAXIMA_BOBINA_MM`), mede o conteúdo e diagrama de novo com a altura dele. Sem o `<style>` com esse id, a página fica com a altura declarada.

**Situação na SEFAZ (medida em GO, homologação, 23/11/2026, com o exemplo `EnvioNfeDanfeSimplificadoTipo2Teste`):** a NF-e com `tpImp=6` e QR Code v3 é autorizada (100), também em contingência off-line (`tpEmis=9`, QR Code v3 assinado). As regras da NT 2026.002 estão ligadas: sem QR Code dá 394, com `indFinal=0` dá 716 e `tpEmis=9` com `tpImp=1` dá 711. Mas o destinatário continua obrigatório: sem ele dá 719, embora a W16-40 e a E01-20 da NT sugiram que seria opcional como na NFC-e.

### 7.5 DANFE NFC-e (modelo 65)

O DANFE NFC-e sai pelo mesmo template de bobina do Simplificado Tipo 2, porque a NT 2026.003 reproduz o leiaute do Manual do DANFE NFC-e. Tudo da seção 7.4 vale para ele: papel, QR Code, divisões, contingência off-line com as duas vias e o PDF numa folha da altura do cupom. Mudam o título da divisão I ("Documento Auxiliar da Nota Fiscal de Consumidor Eletrônica") e o rótulo da identificação ("NFC-e nº").

- `impressaoPadraoNFCe(xml)` põe o logo da NFC-e (`/img/nfce.png`); o template é escolhido pelo `mod=65` do XML, então `impressaoPadraoNFe` também imprime a NFC-e, só que com o logo da NF-e.
- A URL de consulta pela chave vem do `infNFeSupl/urlChave`, obrigatório na NFC-e 4.00. A v1 a recebia por parâmetro.
- Diferenças em relação ao Jasper: bobina de 80 mm em vez de 72 mm; endereço completo em vez de logradouro e número; IE do emitente e "Total formas de pagamento" não saem (o Manual do DANFE NFC-e não os pede); entram o bloco "(+) CBS / IBS / IS", a contingência e a mensagem de homologação.

### 7.6 Carta de Correção eletrônica (evento 110110)

`impressaoPadraoCCe(xml)` imprime a CC-e em A4 a partir do `procEventoNFe` (ou do `evento` ainda sem o retorno), pelo template `danfe/v2/cce.html` (`TEMPLATE_CCE`). O leitor é o `LeitorEventoV2`, que monta o `EventoV2DTO`, exposto ao template como `evento`:

| Grupo | Origem | Chaves |
|---|---|---|
| `evento` | `evento/infEvento` | Id, cOrgao, tpAmb, CNPJ, CPF, chNFe, dhEvento, tpEvento, nSeqEvento, verEvento |
| `detEvento` | `evento/infEvento/detEvento` | descEvento, xCorrecao, xCondUso |
| `retEvento` | `retEvento/infEvento` | verAplic, cStat, xMotivo, xEvento, CNPJDest, CPFDest, dhRegEvento, nProt |

`getChave`, `getModelo`, `getSerie` e `getNumero` (os três últimos tirados da chave), `isCartaCorrecao` e `isHomologacao` (`tpAmb=2` ou sem protocolo de registro, que imprime a marca "SEM VALOR FISCAL").

- **Leiaute:** "CC-e / Carta de Correção Eletrônica" com o número e a série da NF-e; código de barras e chave da NF-e; CNPJ/CPF do emitente e do destinatário; data do evento; sequência; protocolo e data do registro; situação (`cStat - xMotivo`); o texto da correção; as condições de uso do XML.
- **Logo:** sai sem, porque o XML do evento não traz o nome do emitente. Um logo próprio entra com `setLogo`.
- **Outros eventos:** sem template próprio, um evento que não é CC-e lança `DanfeException`. Com `setTemplate`, qualquer evento da NF-e pode ser impresso com as mesmas variáveis.

---

## 8. Pendências e limitações conhecidas

1. **XPaths do grupo IBSCBS conferidos contra o schema, não contra nota real.** Os caminhos de item (`gIBSCBS`, `gIBSCBSMono`, `gTransfCred`, `gAjusteCompet`) e de totais (`IBSCBSTot`, `ISTot`) batem com `schemas/DFeTiposBasicos_v1.00.xsd` (`TTribNFe`, `TCIBS_NFe`, `TMonofasia`, `TIBSCBSMonoTot`, `TISTot`). Falta conferir com um XML real autorizado. Todos os caminhos estão em `LeitorDanfeV2`. A compra governamental pode usar `gTribCompraGov` em vez de `gRed`: confira.
2. **Código do produto:** o modelo da NT não mostra a coluna CÓDIGO. A v2 imprime `cProd - xProd` na descrição para manter a informação exigida pelo Manual do DANFE. Confirme com o fiscal ou contador do cliente.
3. **QR Code no DANFE 55:** só aparece se o XML trouxer `infNFeSupl/qrCode`. As regras de formação serão definidas em NT futura (item 4.5).
4. **Não há preview em janela** (ver seção 4).
5. **Impressão direta** foi compilada, mas **não foi testada numa impressora real**, nem A4 nem térmica. Teste no ambiente do cliente.
6. **Contingência e cancelamento:** o DANFE A4 não imprime tarjas de contingência (`tpEmis` diferente de 1) nem de nota cancelada/denegada; o DANFE em bobina (NFC-e e Simplificado Tipo 2) imprime a de contingência (seção 7.4). Se o sistema precisar disso no A4, peça a inclusão na biblioteca ou faça num template próprio usando `danfe.ide.tpEmis` ou `xml.valor(...)`.
7. **Volumes:** só o primeiro `transp/vol` é impresso.
8. **DANFE simplificado** (`tpImp=3`) não existe: cai no retrato. O DANFE Simplificado **Tipo 2** (`tpImp=6`) existe (seção 7.4).
9. **Desempenho** (medido em Java 8):
   - a primeira geração na JVM leva cerca de 2 s (aquecimento de Thymeleaf e PDFBox);
   - depois, notas pequenas levam de 100 a 300 ms;
   - uma nota com cerca de 50 itens (3 a 4 folhas) leva de 2 a 2,5 s.

   Esses tempos já incluem a segunda diagramação que encosta os dados adicionais no rodapé. Para lotes grandes, gere em paralelo: a classe é thread-safe.

---

## 9. Checklist de implementação (projeto consumidor)

1. Apontar para a versão nova do `java-nfe` (seção 2), tirar do projeto as declarações de Jasper e barbecue que só a v1 usava (2.1), resolver conflitos de Thymeleaf e PDFBox (2.2) e rodar o teste rápido (2.3).
2. Localizar no sistema todos os pontos que imprimem com a v1. Procure por `ImpressaoNfeUtil.`, `ImpressaoDTO` e `JasperNFeEnum`: NF-e, NFC-e e CC-e. Depois da atualização eles não compilam.
3. Criar um serviço próprio, por exemplo `ImpressaoFiscalService`, que:
   - recebe o XML autorizado (String);
   - monta `impressaoPadraoNFe`, `impressaoPadraoNFCe` ou `impressaoPadraoCCe`;
   - aplica a configuração do emitente: logo cadastrado (`setLogo`), uso de canhoto e orientação (A4), largura da bobina (NFC-e e Simplificado Tipo 2);
   - na contingência off-line da NFC-e, imprime a segunda via com `setViaEstabelecimento(true)`;
   - devolve `byte[]` do PDF e expõe a impressão direta.
4. Trocar as chamadas v1 pelo novo serviço (seção 4). Um `.jasper` próprio precisa virar template Thymeleaf; até lá, fique na `4.3.1`.
5. Substituir o preview do Jasper (se houver) pela abertura do PDF.
6. Tratar `DanfeException`: registrar o erro em log e mostrar uma mensagem amigável.
7. Garantir que o XML é lido com o charset correto, normalmente UTF-8.
8. Executar os testes da seção 10.

---

## 10. Testes de aceitação

Use o XML fictício de exemplo da biblioteca (`src/test/resources/DanfeV2Rtc.xml` neste repositório). Ele traz IBSCBS com e sem `gRed`, IS, quatro duplicatas, transporte, QR Code e é de homologação. Para a quebra de página use o `DanfeV2Rtc50Itens.xml`, que fica na mesma pasta: 50 itens que alternam entre três combinações (ICMS00 com IPI, ICMS60 com IS e `gRed`, e ICMS00 sem IPI), alguns com descrição longa e `infAdProd`, com totais que fecham. Teste também com XMLs reais do sistema.

Na biblioteca, `ImpressaoDanfeV2UtilTest` (JUnit, roda no `mvn test`) cobre os cenários 1, 2, 3, 4, 4a, 5, 6, 8 e 9 abaixo; os exemplos `TesteImpressaoDanfeV2` e `TesteImpressaoDanfeV2QuebraPagina` (em `src/test/java/.../nfe/impressao`) geram os PDFs em `d:/teste` para conferência visual.

| # | Cenário | Esperado |
|---|---|---|
| 1 | XML de exemplo, padrão | 1 folha retrato; quadro "TOTAL DO IBS / CBS / IS" com CBS 14,06, IBS UF 1,56, IS 3,12; marca "SEM VALOR FISCAL"; bloco "DADOS ADICIONAIS" no pé da folha, com o espaço livre entre os itens e ele |
| 2 | Item 2 do exemplo (tem `gRed`) | Alíquotas CBS 0,36% e IBS UF 0,04% (efetivas), não 0,90% e 0,10% |
| 3 | `setOrientacao(PAISAGEM)` | 1 folha paisagem com o canhoto vertical à direita |
| 4 | `DanfeV2Rtc50Itens.xml` | Várias folhas (com o CSS atual: 3 em retrato, itens 1–11, 12–34, 35–50 + dados adicionais; 4 em paisagem, 1–5, 6–21, 22–37, 38–50 + dados adicionais; a contagem muda com ajustes de CSS e não é critério). Cada folha repete o quadro do emitente e o cabeçalho dos itens; "FOLHA n/total" correto; nenhum item cortado entre folhas; o bloco de dados adicionais nunca é dividido (pode ir inteiro para uma folha nova) |
| 4a | `DanfeV2Mono.xml` (combustíveis) | Item 1 ad rem: base "IBS/CBS QTD 1.000,00", alíquotas IBS 0,0500 e CBS 0,2000, valores IBS 50,00 e CBS 200,00, linha "Retenção monofásica IBS 10,00 CBS 30,00". Item 2 ad valorem: base 5.000,00, IBS UF 0,10%, CBS 0,90%. Itens 3 e 4 só com valores de IBS/CBS e "Ajuste de competência 11/2026". Quadro de totais com base, CBS e IBS zerados (a nota só tem monofásico), a linha "VALOR DO IBS MONOFÁSICO 55,00 / CBS 245,00" e a linha de retido anteriormente "1,00 / 2,00" |
| 5 | XML real antigo (sem IBSCBS), ou `DanfeV2SemRtc.xml` | Sem quadro IBS/CBS/IS; colunas IBS/CBS/IS vazias; nenhum erro; sem `protNFe` a chave vem do `Id` e o protocolo sai em branco |
| 6 | Nota sem `cobr`, sem transportadora, sem QR Code | Fatura e QR Code não aparecem; o quadro de transporte sai só com "FRETE POR CONTA" |
| 7 | `setExibirCanhoto(false)` | Sem canhoto; na paisagem o conteúdo ocupa a largura toda |
| 8 | Logo do cliente (PNG/JPEG) | Logo no quadro do emitente sem distorção; `removerLogo()` imprime sem logo |
| 8a | Emitente com `xNome` de 60 caracteres e endereço longo | O quadro do emitente não cresce (altura fixa, nome em fonte menor); nas folhas 2+ o cabeçalho não invade os itens |
| 9 | XML inválido ou sem `infNFe` | `DanfeException` com mensagem clara |
| 10 | NF-e real de produção (`tpAmb=1`) | Sem marca d'água; protocolo e chave corretos; código de barras legível por leitor |
| 11 | Impressão direta na impressora do cliente | Imprime em A4 na orientação correta |
| 12 | `DanfeSimplificadoTipo2.xml` (`tpImp=6`) | Cupom de 80 mm numa folha da altura do conteúdo, QR Code ao lado da identificação, outras despesas 2,00, desconto -8,00, (+) CBS 1,75, (+) IBS 0,20, (+) IS 0,78, valor a pagar 194,28, troco 5,72; com `setLarguraPapelMm(58)`, QR Code centralizado e a chave numa linha (`ImpressaoDanfeSimplificadoTipo2Test`; o exemplo `TesteImpressaoDanfeSimplificadoTipo2` gera o PDF) |
| 13 | O mesmo com `tpEmis=9` e sem `protNFe` | "EMITIDA EM CONTINGÊNCIA / Pendente de autorização" duas vezes, sem protocolo, "Via do Consumidor"; com `setViaEstabelecimento(true)`, "Via do Estabelecimento" |
| 14 | `DanfeNfce.xml` com `impressaoPadraoNFCe` | Cupom de 80 mm com o logo da NFC-e, "Documento Auxiliar da Nota Fiscal de Consumidor Eletrônica", "CONSUMIDOR CPF: 123.456.789-09", PIX 100,00 e "NFC-e nº 000.000.321" (`ImpressaoNfceECceTest`; o exemplo `TesteImpressaoNFCe` gera o PDF) |
| 15 | `CartaCorrecao.xml` com `impressaoPadraoCCe` | 1 folha A4 com "CARTA DE CORREÇÃO ELETRÔNICA", NF-e nº 000.000.123 série 001, a chave inteira, emitente e destinatário, sequência 2, protocolo, "135 - Evento registrado e vinculado a NF-e", a correção e as condições de uso; marca "SEM VALOR FISCAL" (`ImpressaoNfceECceTest`; o exemplo `TesteImpressaoCCe` gera o PDF) |

---

## 11. Diagnóstico rápido

| Sintoma | Causa provável |
|---|---|
| `NoClassDefFoundError` de `org.thymeleaf`, `com.openhtmltopdf` ou `com.google.zxing` | Dependência de impressão excluída no `pom.xml` do projeto consumidor, ou classpath montado à mão sem ela (seção 2.1) |
| `ImpressaoNfeUtil`, `ImpressaoDTO` ou `JasperNFeEnum` não compila | A impressão Jasper foi removida; troque pela v2 (seção 4) |
| `ClassNotFoundException: br.com.swconsultoria.nfe.impressao.v2...` | O projeto ainda aponta para a `4.2.0` (sem a v2) ou resolveu a dependência de outro repositório; confira a versão e o repositório Maven local (seção 2) |
| `NoSuchMethodError` do Thymeleaf ou PDFBox | Conflito de versão (seção 2.2) |
| `DanfeException: Erro ao processar o template ...` | Caminho do arquivo errado, HTML malformado ou expressão inválida no template próprio (seção 5.2); a causa original do Thymeleaf vem em `getCause()` |
| Cabeçalho das folhas 2+ em cima dos itens | Template próprio cujo quadro do emitente cresce com o texto; mantenha a altura fixa de `.emitente-caixa` (o cabeçalho repetido vive na margem superior de 43mm) |
| Faixa em branco à direita na paisagem | Template próprio aplicando a faixa do canhoto sem testar `exibirCanhoto` |
| Bloco de dados adicionais logo após os itens, no meio da folha | Template próprio sem os marcadores da regra 10 da seção 5.2, ou com o bloco maior que a folha |
| PDF com folha em branco no final | Elemento posicionado que ultrapassa a altura da página |
| Cabeçalho dos itens não repete | `overflow: hidden` em `td/th` ou ausência de `border-collapse: separate` |
| CST impresso como "0" em vez de "000" | Concatenação com `+` no template (use `fmt.cst`) |
| Valores do IBS/CBS vazios num XML que os tem | XPath diferente do schema real (pendência 8.1); confira em qual dos quatro grupos de `imposto/IBSCBS` o item está (seção 6) |
| Texto `null` impresso | Template próprio concatenando um valor ausente com `+`; use `fmt.primeiro(valor, '')` (seção 5.1) |
| Muitas linhas INFO do openhtmltopdf no log | Comportamento padrão da biblioteca de PDF; silencie na aplicação (seção 2.2) |
| Acentos trocados | XML lido com o charset errado antes de chegar à biblioteca |

---

## 12. Onde está o código da biblioteca

Pacote `br.com.swconsultoria.nfe.impressao.v2` deste repositório (este manual fica em `docs/MANUAL-DANFE-V2.md`)

| Arquivo | Função |
|---|---|
| `ImpressaoDanfeV2Util.java` | API pública (`impressaoPadraoNFe`, `impressaoPadraoNFCe`, `impressaoPadraoCCe`, HTML, PDF, impressão direta), escolha do template, Thymeleaf e openhtmltopdf |
| `ImpressaoDanfeV2DTO.java` | Configuração (logo, template, orientação, canhoto, parâmetros; largura do papel e via do DANFE em bobina) |
| `OrientacaoDanfeEnum.java` | AUTOMATICA / RETRATO / PAISAGEM |
| `LeitorDanfeV2.java` | XPaths da NF-e/NFC-e e regras da NT (alíquota efetiva) |
| `DanfeV2DTO.java` | Dados e regras de exibição do DANFE |
| `LeitorEventoV2.java` / `EventoV2DTO.java` | XPaths e dados do evento (CC-e) |
| `XmlDanfeV2.java` | DOM sem namespace + XPath (protegido contra XXE), exposto como `xml` |
| `FormatadorDanfeV2.java` | Formatação (`fmt`) |
| `ImagemDanfeV2Util.java` | Code-128 e QR Code (zxing) e data URI |
| `src/main/resources/danfe/v2/danfe.html` / `danfe-bobina.html` / `cce.html` / `fragmentos.html` | Templates padrão: DANFE A4, DANFE em bobina (NFC-e e Simplificado Tipo 2), CC-e e fragmentos |
| `src/test/java/.../nfe/impressao/v2/*Test.java` | Testes JUnit |
| `src/test/java/.../nfe/impressao/TesteImpressao*.java` | Exemplos de uso |

Se precisar de uma mudança na biblioteca, altere-a, regere a versão local (seção 2) e recompile o projeto consumidor. O `mvn install` substitui o jar da `4.3.0` no repositório local, então a dependência do consumidor não muda.
