package br.com.swconsultoria.nfe.impressao.v2;

import br.com.swconsultoria.nfe.exception.DanfeException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Acesso ao XML da NF-e via XPath, para o DANFE v2.
 * O parse é feito sem namespace, então as expressões não usam prefixo (ex.: "//infNFe/ide/nNF").
 * Também é exposto ao template Thymeleaf como "xml", permitindo que templates customizados
 * leiam qualquer campo do XML: ${xml.valor('//infNFe/ide/tpEmis')}
 */
public class XmlDanfeV2 {

    private final Document documento;
    private final XPath xpath;
    private final Map<String, XPathExpression> expressoes = new HashMap<>();

    public XmlDanfeV2(String xml) {
        if (xml == null || xml.trim().isEmpty()) {
            throw new DanfeException("XML não pode ser nulo/vazio.");
        }
        // Arquivos UTF-8 com BOM: XmlNfeUtil.leXml preserva o ﻿ e o parser rejeita ("conteúdo não permitido no prólogo")
        String conteudo = xml.trim();
        if (conteudo.startsWith("﻿")) {
            conteudo = conteudo.substring(1).trim();
        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);
            this.documento = factory.newDocumentBuilder().parse(new InputSource(new StringReader(conteudo)));

            // As expressões são internas (ou de templates próprios, que são confiáveis); defesa em profundidade barata
            XPathFactory xpathFactory = XPathFactory.newInstance();
            xpathFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            this.xpath = xpathFactory.newXPath();
        } catch (Exception e) {
            throw new DanfeException("Erro ao ler o XML da NF-e: " + e.getMessage(), e);
        }
    }

    public Document getDocumento() {
        return documento;
    }

    /**
     * Retorna o texto do primeiro nó da expressão (a partir da raiz) ou null se inexistente/vazio.
     */
    public String valor(String expressao) {
        return valor(documento, expressao);
    }

    /**
     * Retorna o texto do primeiro nó da expressão (relativa ao contexto) ou null se inexistente/vazio.
     */
    public String valor(Object contexto, String expressao) {
        Node no = no(contexto, expressao);
        if (no == null) {
            return null;
        }
        String texto = no.getTextContent();
        return texto == null || texto.trim().isEmpty() ? null : texto.trim();
    }

    public boolean existe(String expressao) {
        return no(documento, expressao) != null;
    }

    public boolean existe(Object contexto, String expressao) {
        return no(contexto, expressao) != null;
    }

    public Node no(String expressao) {
        return no(documento, expressao);
    }

    public Node no(Object contexto, String expressao) {
        try {
            return (Node) compila(expressao).evaluate(contexto, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            throw new DanfeException("Expressão XPath inválida: " + expressao, e);
        }
    }

    public List<Node> nos(String expressao) {
        return nos(documento, expressao);
    }

    public List<Node> nos(Object contexto, String expressao) {
        try {
            NodeList lista = (NodeList) compila(expressao).evaluate(contexto, XPathConstants.NODESET);
            List<Node> nos = new ArrayList<>(lista.getLength());
            for (int i = 0; i < lista.getLength(); i++) {
                nos.add(lista.item(i));
            }
            return nos;
        } catch (XPathExpressionException e) {
            throw new DanfeException("Expressão XPath inválida: " + expressao, e);
        }
    }

    private XPathExpression compila(String expressao) throws XPathExpressionException {
        XPathExpression compilada = expressoes.get(expressao);
        if (compilada == null) {
            compilada = xpath.compile(expressao);
            expressoes.put(expressao, compilada);
        }
        return compilada;
    }
}
