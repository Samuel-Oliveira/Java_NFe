package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ServicosEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.exception.NfeValidacaoException;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.StringReader;

class Validar implements ErrorHandler {

    private String listaComErrosDeValidacao = "";

    void validaXml(ConfiguracoesNfe config, String xml, ServicosEnum servico) throws NfeException {

        System.setProperty("jdk.xml.maxOccurLimit", "99999");
        String errosValidacao;
        String xsd = config.getPastaSchemas() + "/" + servico.getXsd();
        if (!new File(xsd).exists()) {
            throw new NfeException("Schema Nfe não Localizado: " + xsd);
        }

        errosValidacao = validateXml(xml, xsd);
        if(ObjetoUtil.verifica(errosValidacao).isPresent())
            throw  new NfeValidacaoException("Erro na validação: " + errosValidacao);

    }

    private String validateXml(String xml, String xsd) throws NfeException {

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setValidating(true);
        docBuilderFactory.setNamespaceAware(true);
        docBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        docBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", xsd);
        DocumentBuilder builder;
        try {
            builder = docBuilderFactory.newDocumentBuilder();
            builder.setErrorHandler(this);
        } catch (ParserConfigurationException ex) {
            throw new NfeException(ex.getMessage());
        }

        try {
            builder.parse(new InputSource(new StringReader(xml)));
        } catch (Exception ex) {
            throw new NfeException(ex.toString());
        }

        return this.getListaComErrosDeValidacao();
    }

    public void error(SAXParseException exception) {

        if (isError(exception)) {
            listaComErrosDeValidacao += tratamentoRetorno(exception.getMessage());
        }
    }

    public void fatalError(SAXParseException exception) {

        listaComErrosDeValidacao += tratamentoRetorno(exception.getMessage());
    }

    public void warning(SAXParseException exception) {

        listaComErrosDeValidacao += tratamentoRetorno(exception.getMessage());
    }

    private String tratamentoRetorno(String message) {

        message = message.replaceAll("cvc-type.3.1.3:", "-");
        message = message.replaceAll("cvc-attribute.3:", "-");
        message = message.replaceAll("cvc-complex-type.2.4.a:", "-");
        message = message.replaceAll("cvc-complex-type.2.4.b:", "-");
        message = message.replaceAll("cvc-complex-type.2.4.c:", "-");
        message = message.replaceAll("cvc-complex-type.2.4.d:", "-");
        message = message.replaceAll("cvc-complex-type.4:", "-");
        message = message.replaceAll("cvc-minLength-valid:", "-");
        message = message.replaceAll("The value", "O valor");
        message = message.replaceAll("Value", "Valor");
        message = message.replaceAll("with length", "com tamanho");
        message = message.replaceAll("is not facet-valid with respect to minLength", "não equivale ao tamanho mínimo");
        message = message.replaceAll("for type", "para o tipo");
        message = message.replaceAll("The content", "O conteúdo");
        message = message.replaceAll("of element", "do campo");
        message = message.replaceAll("is not complete", "não está completo");
        message = message.replaceAll("is not valid", "não é válido");
        message = message.replaceAll("Attribute", "Campo");
        message = message.replaceAll("must appear on element", "precisa estar em");
        message = message.replaceAll("Invalid content was found starting with element", "Conteúdo inválido encontrado iniciando com o campo");
        message = message.replaceAll("One of", "Um dos Campos");
        message = message.replaceAll("is expected", "é esperado");
        message = message.replaceAll("\\{", "");
        message = message.replaceAll("\\}", "");
        message = message.replaceAll("\"", "");
        message = message.replaceAll("http://www.portalfiscal.inf.br/nfe:", "");
        return System.getProperty("line.separator") + message.trim();
    }

    private String getListaComErrosDeValidacao() {

        return listaComErrosDeValidacao;
    }

    private boolean isError(SAXParseException exception) {

        return !exception.getMessage().startsWith("cvc-enumeration-valid")
                && !exception.getMessage().startsWith("cvc-pattern-valid")
                && !exception.getMessage().startsWith("cvc-maxLength-valid")
                && !exception.getMessage().startsWith("cvc-datatype");
    }

}
