package br.com.swconsultoria.nfe;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.ConfiguracoesUtil;
import br.com.swconsultoria.nfe.util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Classe responsável por consultar classificações tributárias para a Reforma Tributária, utiliza o serviço web disponibilizado pelo governo federal.
 * Foi criada para funcionar da forma mais genérica possível, permitindo a consulta e manipulação dos dados retornados em JSON se necessário.
 * <p>
 * Oferece quatro modos de uso:
 * <p>1. Retorno direto do JSON string (método getJson)</p>
 * <p>2. Conversão genérica para qualquer tipo de objeto (método get com Class)</p>
 * <p>3. Conversão genérica com TypeReference para tipos complexos (método get com TypeReference)</p>
 * <p>4. Validação de estrutura JSON vs DTO (método validate)</p>
 * <p>
 * Exemplos de uso:
 * <pre>
 * // Obter JSON bruto
 * String json = ConsultaTributacao.getJson(config);
 *
 * // Converter para List de DTOs
 * List&lt;CstDTO&gt; lista = ConsultaTributacao.get(config, new TypeReference&lt;List&lt;CstDTO&gt;&gt;() {});
 *
 * // Com filtros
 * Map&lt;String, String&gt; params = new HashMap&lt;&gt;();
 * params.put("Cst", "00");
 * List&lt;CstDTO&gt; filtrado = ConsultaTributacao.get(config, params, new TypeReference&lt;List&lt;CstDTO&gt;&gt;() {});
 *
 * // Validar estrutura
 * ValidationReport report = ConsultaTributacao.validate(config, CstDTO.class);
 * </pre>
 *
 * @author Rodrigo Cananea - rodrigo@rcconsultoria.inf.br
 */
@Log
@SuppressWarnings("all")
public class ConsultaTributacao {

    private ConsultaTributacao() {
    }

    private static final String SECTION = "CFF";
    private static final String KEY = "classTrib";

    private static final ObjectMapper MAPPER = createObjectMapper();
    private static final ObjectMapper STRICT_MAPPER = createStrictObjectMapper();

    /**
     * Cria ObjectMapper configurado para ser tolerante a mudanças no JSON.
     */
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return mapper;
    }

    /**
     * Cria ObjectMapper rigoroso para detecção de inconsistências.
     */
    private static ObjectMapper createStrictObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
        return mapper;
    }

    /**
     * Executa requisição HTTP GET e retorna o JSON string bruto.
     *
     * @param config Configurações da NFe contendo certificado digital
     * @return String contendo o JSON de resposta
     * @throws NfeException Se houver erro de configuração ou certificado
     * @throws IOException  Se houver erro de comunicação HTTP
     */
    public static String getJson(ConfiguracoesNfe config) throws NfeException, IOException {
        return getJson(config, null);
    }

    /**
     * Executa requisição HTTP GET com parâmetros de query e retorna o JSON string bruto.
     *
     * @param config      Configurações da NFe contendo certificado digital
     * @param queryParams Mapa com parâmetros de query (ex: {"Cst": "00"})
     * @return String contendo o JSON de resposta
     * @throws NfeException Se houver erro de configuração ou certificado
     * @throws IOException  Se houver erro de comunicação HTTP
     */
    public static String getJson(ConfiguracoesNfe config, Map<String, String> queryParams)
            throws NfeException, IOException {
        ConfiguracoesUtil.iniciaConfiguracoes(config);

        String urlBase = WebServiceUtil.getCustomUrl(config, SECTION, KEY);
        String url = buildUrlWithParams(urlBase, queryParams);

        Certificado certificado = config.getCertificado();
        if (certificado == null) {
            throw new NfeException("Certificado digital não configurado");
        }

        try {
            HttpClient httpClient = createHttpClient(config, certificado, url);
            if (httpClient != null) {
                return executeRequestWithHttpClient(httpClient, url);
            }
        } catch (CertificadoException e) {
            log.warning("[ConsultaTributacao] Falha ao criar HttpClient, tentando fallback: " + e.getMessage());
        }

        SSLSocketFactory sslFactory = tryResolveSslSocketFactory(config);
        if (sslFactory != null) {
            return executeRequestWithSslFactory(sslFactory, url);
        }

        throw new NfeException(
                "Não foi possível configurar SSL/TLS para a requisição."
        );
    }

    /**
     * Executa requisição HTTP GET e converte o JSON para o tipo especificado.
     * Método genérico que aceita qualquer classe DTO.
     *
     * @param <T>    Tipo do objeto de retorno
     * @param config Configurações da NFe contendo certificado digital
     * @param clazz  Classe do objeto de destino
     * @return Objeto do tipo T com os dados deserializados do JSON
     * @throws NfeException Se houver erro de configuração, certificado ou conversão
     * @throws IOException  Se houver erro de comunicação HTTP
     */
    public static <T> T get(ConfiguracoesNfe config, Class<T> clazz)
            throws NfeException, IOException {
        String json = getJson(config);
        return convertJsonToObject(json, clazz);
    }

    /**
     * Executa requisição HTTP GET com parâmetros e converte o JSON para o tipo especificado.
     *
     * @param <T>         Tipo do objeto de retorno
     * @param config      Configurações da NFe
     * @param queryParams Parâmetros de query
     * @param clazz       Classe do objeto de destino
     * @return Objeto do tipo T deserializado
     * @throws NfeException Se houver erro
     * @throws IOException  Se houver erro de I/O
     */
    public static <T> T get(ConfiguracoesNfe config, Map<String, String> queryParams, Class<T> clazz)
            throws NfeException, IOException {
        String json = getJson(config, queryParams);
        return convertJsonToObject(json, clazz);
    }

    /**
     * Executa requisição HTTP GET e converte o JSON para tipos complexos (ex: List, Map).
     * Usa TypeReference do Jackson para preservar informações de tipo genérico.
     *
     * @param <T>     Tipo do objeto de retorno
     * @param config  Configurações da NFe
     * @param typeRef TypeReference com o tipo genérico desejado
     * @return Objeto do tipo T deserializado
     * @throws NfeException Se houver erro
     * @throws IOException  Se houver erro de I/O
     */
    public static <T> T get(ConfiguracoesNfe config, TypeReference<T> typeRef)
            throws NfeException, IOException {
        String json = getJson(config);
        return convertJsonToObject(json, typeRef);
    }

    /**
     * Executa requisição HTTP GET com parâmetros e converte para tipos complexos.
     *
     * @param <T>         Tipo do objeto de retorno
     * @param config      Configurações da NFe
     * @param queryParams Parâmetros de query
     * @param typeRef     TypeReference com o tipo genérico
     * @return Objeto do tipo T deserializado
     * @throws NfeException Se houver erro
     * @throws IOException  Se houver erro de I/O
     */
    public static <T> T get(ConfiguracoesNfe config, Map<String, String> queryParams, TypeReference<T> typeRef)
            throws NfeException, IOException {
        String json = getJson(config, queryParams);
        return convertJsonToObject(json, typeRef);
    }

    /**
     * Valida a estrutura do JSON retornado pela API contra um DTO.
     * Retorna um relatório detalhado de inconsistências.
     * <p>
     * Exemplo de uso:
     * <pre>
     * ValidationReport report = ConsultaTributacao.validate(config, CstDTO.class);
     *
     * if (report.hasIssues()) {
     *     System.out.println("Campos extras no JSON: " + report.getExtraFields());
     *     System.out.println("Campos faltando no JSON: " + report.getMissingFields());
     *     System.out.println("Erros de tipo: " + report.getTypeErrors());
     * }
     * </pre>
     *
     * @param config Configurações da NFe
     * @param clazz  Classe do DTO a ser validada
     * @return ValidationReport com detalhes das inconsistências
     * @throws NfeException Se houver erro na consulta
     * @throws IOException  Se houver erro de I/O
     */
    public static ValidationReport validate(ConfiguracoesNfe config, Class<?> clazz)
            throws NfeException, IOException {
        String json = getJson(config);
        return validateJsonStructure(json, clazz);
    }

    /**
     * Valida JSON contra DTO com parâmetros de query.
     *
     * @param config      Configurações
     * @param queryParams Parâmetros
     * @param clazz       Classe do DTO
     * @return ValidationReport
     * @throws NfeException Se houver erro
     * @throws IOException  Se houver erro de I/O
     */
    public static ValidationReport validate(ConfiguracoesNfe config, Map<String, String> queryParams, Class<?> clazz)
            throws NfeException, IOException {
        String json = getJson(config, queryParams);
        return validateJsonStructure(json, clazz);
    }

    /**
     * Valida JSON contra TypeReference (para List, Map, etc).
     * <p>
     * Exemplo:
     * <pre>
     * ValidationReport report = ConsultaTributacao.validate(
     *     config,
     *     new TypeReference&lt;List&lt;CstDTO&gt;&gt;() {},
     *     CstDTO. class  // classe interna para validar
     * );
     * </pre>
     *
     * @param config     Configurações
     * @param typeRef    TypeReference do tipo externo (ex: List)
     * @param innerClass Classe interna para validar (ex: CstDTO dentro de List&lt;CstDTO&gt;)
     * @return ValidationReport
     * @throws NfeException Se houver erro
     * @throws IOException  Se houver erro de I/O
     */
    public static ValidationReport validate(ConfiguracoesNfe config, TypeReference<?> typeRef, Class<?> innerClass)
            throws NfeException, IOException {
        String json = getJson(config);
        return validateJsonStructure(json, innerClass);
    }

    /**
     * Testa se o JSON pode ser convertido sem erros (modo rigoroso).
     * Retorna true se a conversão for bem-sucedida, false caso contrário.
     *
     * @param config Configurações
     * @param clazz  Classe do DTO
     * @return true se compatível, false se houver problemas
     */
    public static boolean isCompatible(ConfiguracoesNfe config, Class<?> clazz) {
        try {
            String json = getJson(config);
            STRICT_MAPPER.readValue(json, clazz);
            return true;
        } catch (Exception e) {
            log.warning("[ConsultaTributacao] Incompatibilidade detectada: " + e.getMessage());
            return false;
        }
    }

    /**
     * Valida estrutura do JSON contra uma classe DTO.
     * Identifica campos extras, faltantes e problemas de tipo.
     */
    private static ValidationReport validateJsonStructure(String json, Class<?> clazz) throws NfeException {
        ValidationReport report = new ValidationReport();

        try {
            JsonNode rootNode = MAPPER.readTree(json);

            // Se o JSON é um array, valida o primeiro elemento
            JsonNode nodeToValidate = rootNode;
            if (rootNode.isArray() && rootNode.size() > 0) {
                nodeToValidate = rootNode.get(0);
                report.setArrayDetected(true);
                report.setArraySize(rootNode.size());
            }

            // Obtém campos esperados do DTO
            Map<String, Class<?>> expectedFields = extractDtoFields(clazz);

            // Obtém campos presentes no JSON
            Set<String> jsonFields = new HashSet<>();
            Iterator<String> fieldNames = nodeToValidate.fieldNames();
            while (fieldNames.hasNext()) {
                jsonFields.add(fieldNames.next());
            }

            // Identifica campos extras no JSON
            Set<String> extraFields = new HashSet<>(jsonFields);
            extraFields.removeAll(expectedFields.keySet());
            report.setExtraFields(extraFields);

            // Identifica campos faltando no JSON
            Set<String> missingFields = new HashSet<>(expectedFields.keySet());
            missingFields.removeAll(jsonFields);
            report.setMissingFields(missingFields);

            // Valida tipos dos campos presentes
            Map<String, String> typeErrors = new HashMap<>();
            for (Map.Entry<String, Class<?>> entry : expectedFields.entrySet()) {
                String fieldName = entry.getKey();
                Class<?> expectedType = entry.getValue();

                if (jsonFields.contains(fieldName)) {
                    JsonNode fieldNode = nodeToValidate.get(fieldName);
                    if (!isTypeCompatible(fieldNode, expectedType)) {
                        typeErrors.put(fieldName,
                                "Esperado: " + expectedType.getSimpleName() +
                                        ", Encontrado: " + getJsonNodeType(fieldNode));
                    }
                }
            }
            report.setTypeErrors(typeErrors);

            // Tenta conversão estrita para detectar outros problemas
            try {
                STRICT_MAPPER.readValue(json, clazz);
                report.setStrictConversionSuccess(true);
            } catch (Exception e) {
                report.setStrictConversionSuccess(false);
                report.setStrictConversionError(e.getMessage());
            }

        } catch (IOException e) {
            throw new NfeException("Erro ao validar JSON: " + e.getMessage(), e);
        }

        return report;
    }

    /**
     * Extrai campos do DTO com suas anotações @JsonProperty.
     */
    private static Map<String, Class<?>> extractDtoFields(Class<?> clazz) {
        Map<String, Class<?>> fields = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            JsonProperty annotation = field.getAnnotation(JsonProperty.class);
            String jsonFieldName;

            if (annotation != null && !annotation.value().isEmpty()) {
                jsonFieldName = annotation.value();
            } else {
                jsonFieldName = field.getName();
            }

            fields.put(jsonFieldName, field.getType());
        }

        return fields;
    }

    /**
     * Verifica se o tipo do JsonNode é compatível com o tipo esperado.
     */
    private static boolean isTypeCompatible(JsonNode node, Class<?> expectedType) {
        if (node.isNull()) {
            return true;
        }

        if (expectedType == String.class) {
            return node.isTextual();
        }

        if (expectedType == Integer.class || expectedType == int.class ||
                expectedType == Long.class || expectedType == long.class ||
                expectedType == Short.class || expectedType == short.class ||
                expectedType == Byte.class || expectedType == byte.class) {
            return node.isIntegralNumber();
        }

        if (expectedType == Double.class || expectedType == double.class ||
                expectedType == Float.class || expectedType == float.class ||
                expectedType == java.math.BigDecimal.class) {
            return node.isNumber();
        }

        if (expectedType == Boolean.class || expectedType == boolean.class) {
            return node.isBoolean();
        }

        if (List.class.isAssignableFrom(expectedType) ||
                Collection.class.isAssignableFrom(expectedType)) {
            return node.isArray();
        }

        if (Map.class.isAssignableFrom(expectedType)) {
            return node.isObject();
        }

        return node.isObject();
    }

    /**
     * Retorna o tipo do JsonNode como string descritiva.
     */
    private static String getJsonNodeType(JsonNode node) {
        if (node.isNull()) return "null";
        if (node.isTextual()) return "String";
        if (node.isIntegralNumber()) return "Integer";
        if (node.isFloatingPointNumber()) return "Decimal";
        if (node.isBoolean()) return "Boolean";
        if (node.isArray()) return "Array";
        if (node.isObject()) return "Object";
        return "Unknown";
    }

    private static <T> T convertJsonToObject(String json, Class<T> clazz) throws NfeException {
        try {
            log.info("[ConsultaTributacao] Convertendo JSON para " + clazz.getSimpleName());
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            log.severe("[ConsultaTributacao] Erro ao converter JSON: " + e.getMessage());
            throw new NfeException("Erro ao processar resposta JSON: " + e.getMessage(), e);
        }
    }

    private static <T> T convertJsonToObject(String json, TypeReference<T> typeRef) throws NfeException {
        try {
            log.info("[ConsultaTributacao] Convertendo JSON para tipo complexo");
            return MAPPER.readValue(json, typeRef);
        } catch (IOException e) {
            log.severe("[ConsultaTributacao] Erro ao converter JSON: " + e.getMessage());
            throw new NfeException("Erro ao processar resposta JSON: " + e.getMessage(), e);
        }
    }

    private static String buildUrlWithParams(String baseUrl, Map<String, String> queryParams) {
        if (queryParams == null || queryParams.isEmpty()) {
            return baseUrl;
        }

        StringBuilder url = new StringBuilder(baseUrl);
        boolean first = !baseUrl.contains("?");

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().trim().isEmpty()) {
                url.append(first ? "?" : "&");
                url.append(entry.getKey()).append("=").append(entry.getValue());
                first = false;
            }
        }

        return url.toString();
    }

    private static HttpClient createHttpClient(ConfiguracoesNfe config, Certificado certificado, String url)
            throws CertificadoException {
        if (ObjetoUtil.verifica(config.getCacert()).isPresent()) {
            return CertificadoService.getHttpsClient(certificado, url, config.getCacert());
        } else {
            return CertificadoService.getHttpsClient(certificado, url);
        }
    }

    private static String executeRequestWithHttpClient(HttpClient httpClient, String url) throws IOException {
        GetMethod getMethod = new GetMethod(url);

        try {
            getMethod.setRequestHeader("Accept", "application/json");
            getMethod.setRequestHeader("Content-Type", "application/json; charset=UTF-8");

            int statusCode = httpClient.executeMethod(getMethod);
            log.info("[ConsultaTributacao] Status HTTP: " + statusCode);

            if (statusCode != HttpStatus.SC_OK) {
                String errorBody = inputStreamToString(getMethod.getResponseBodyAsStream());
                log.severe("[ConsultaTributacao] Erro HTTP " + statusCode + ": " + errorBody);
                throw new IOException("Erro HTTP " + statusCode + ": " + errorBody);
            }

            try (InputStream responseStream = new BufferedInputStream(getMethod.getResponseBodyAsStream())) {
                return inputStreamToString(responseStream);
            }

        } finally {
            getMethod.releaseConnection();
        }
    }

    private static String executeRequestWithSslFactory(SSLSocketFactory sslFactory, String url) throws IOException {
        HttpsURLConnection conn = null;
        InputStream is = null;

        try {
            URL u = new URL(url);
            conn = (HttpsURLConnection) u.openConnection();
            conn.setSSLSocketFactory(sslFactory);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(30000);
            conn.setDoInput(true);

            int statusCode = conn.getResponseCode();
            log.info("[ConsultaTributacao] Status HTTP: " + statusCode);

            if (statusCode != HttpStatus.SC_OK) {
                String errorBody = inputStreamToString(conn.getErrorStream());
                log.severe("[ConsultaTributacao] Erro HTTP " + statusCode + ": " + errorBody);
                throw new IOException("Erro HTTP " + statusCode + ": " + errorBody);
            }

            is = new BufferedInputStream(conn.getInputStream());
            return inputStreamToString(is);

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private static SSLSocketFactory tryResolveSslSocketFactory(ConfiguracoesNfe config) {
        try {
            Class<?> svc = Class.forName("br.com.swconsultoria.certificado.CertificadoService");

            try {
                java.lang.reflect.Method m = svc.getMethod("getSSLSocketFactory",
                        Class.forName("br.com.swconsultoria.certificado. Certificado"));
                Object res = m.invoke(null, config.getCertificado());
                if (res instanceof SSLSocketFactory) {
                    return (SSLSocketFactory) res;
                }
            } catch (NoSuchMethodException ignored) {
            }

            try {
                java.lang.reflect.Method m2 = svc.getMethod("getSslContext",
                        Class.forName("br.com.swconsultoria.certificado.Certificado"));
                Object res2 = m2.invoke(null, config.getCertificado());
                if (res2 instanceof SSLContext) {
                    return ((SSLContext) res2).getSocketFactory();
                }
            } catch (NoSuchMethodException ignored) {
            }

        } catch (Throwable ignored) {
        }

        Object cert = config.getCertificado();
        if (cert != null) {
            try {
                java.lang.reflect.Method m = cert.getClass().getMethod("getSSLSocketFactory");
                Object res = m.invoke(cert);
                if (res instanceof SSLSocketFactory) {
                    return (SSLSocketFactory) res;
                }
            } catch (Throwable ignored) {
            }

            try {
                java.lang.reflect.Method m2 = cert.getClass().getMethod("getSslContext");
                Object res2 = m2.invoke(cert);
                if (res2 instanceof SSLContext) {
                    return ((SSLContext) res2).getSocketFactory();
                }
            } catch (Throwable ignored) {
            }
        }

        return null;
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        if (is == null) {
            return "";
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = is.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }

        return new String(baos.toByteArray(), StandardCharsets.UTF_8);
    }

    /**
     * Classe que representa o relatório de validação da estrutura JSON.
     */
    @Setter
    @Getter
    public static class ValidationReport {
        private Set<String> extraFields = new HashSet<>();
        private Set<String> missingFields = new HashSet<>();
        private Map<String, String> typeErrors = new HashMap<>();
        private boolean strictConversionSuccess;
        private String strictConversionError;
        private boolean arrayDetected;
        private int arraySize;

        /**
         * Retorna true se houver qualquer inconsistência detectada.
         */
        public boolean hasIssues() {
            return !extraFields.isEmpty() ||
                    !missingFields.isEmpty() ||
                    !typeErrors.isEmpty() ||
                    !strictConversionSuccess;
        }

        /**
         * Retorna true se não houver nenhum problema.
         */
        public boolean isValid() {
            return !hasIssues();
        }

        /**
         * Gera relatório formatado em texto.
         */
        public String generateReport() {
            StringBuilder sb = new StringBuilder();
            sb.append("=== RELATORIO DE VALIDACAO ===\n\n");

            if (arrayDetected) {
                sb.append("Array detectado com ").append(arraySize).append(" elemento(s)\n");
                sb.append("Validacao realizada no primeiro elemento\n\n");
            }

            if (extraFields.isEmpty() && missingFields.isEmpty() && typeErrors.isEmpty()) {
                sb.append("ESTRUTURA 100% COMPATIVEL\n");
                sb.append("  - Nenhum campo extra no JSON\n");
                sb.append("  - Nenhum campo faltando\n");
                sb.append("  - Todos os tipos estao corretos\n");
            } else {
                if (!extraFields.isEmpty()) {
                    sb.append("ATENCAO: Campos EXTRAS no JSON (nao mapeados no DTO):\n");
                    for (String field : extraFields) {
                        sb.append("  - ").append(field).append("\n");
                    }
                    sb.append("\n");
                }

                if (!missingFields.isEmpty()) {
                    sb.append("ATENCAO: Campos FALTANDO no JSON (esperados no DTO):\n");
                    for (String field : missingFields) {
                        sb.append("  - ").append(field).append("\n");
                    }
                    sb.append("\n");
                }

                if (!typeErrors.isEmpty()) {
                    sb.append("ERRO: Incompatibilidade de tipos:\n");
                    for (Map.Entry<String, String> entry : typeErrors.entrySet()) {
                        sb.append("  - ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                    }
                    sb.append("\n");
                }
            }

            sb.append("Conversao estrita: ").append(strictConversionSuccess ? "SUCESSO" : "FALHOU").append("\n");
            if (!strictConversionSuccess && strictConversionError != null) {
                sb.append("  Erro: ").append(strictConversionError).append("\n");
            }

            return sb.toString();
        }

        @Override
        public String toString() {
            return generateReport();
        }
    }
}
