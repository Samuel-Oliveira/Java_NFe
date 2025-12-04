package br.com.swconsultoria.nfe;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.ConfiguracoesUtil;
import br.com.swconsultoria.nfe. util.ObjetoUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import com.fasterxml.jackson.core.type. TypeReference;
import com.fasterxml.jackson.databind. DeserializationFeature;
import com.fasterxml.jackson. databind.ObjectMapper;
import lombok.extern.java.Log;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache. commons.httpclient.methods. GetMethod;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java. io.IOException;
import java. io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por realizar consultas REST a serviços externos com certificado digital.
 *
 * Oferece três modos de uso:
 * 1.  Retorno direto do JSON string (método getJson)
 * 2. Conversão genérica para qualquer tipo de objeto (método get com Class)
 * 3.  Conversão genérica com TypeReference para tipos complexos (método get com TypeReference)
 *
 * Exemplos de uso:
 *
 * // 1. Obter JSON bruto
 * String json = ConsultaTributacao.getJson(config, "CFF", "classTrib");
 *
 * // 2.  Converter para List<MeuDTO>
 * List<MeuDTO> lista = ConsultaTributacao.get(config, "CFF", "classTrib",
 *     new TypeReference<List<MeuDTO>>() {});
 *
 * // 3.  Converter para objeto único
 * MeuDTO objeto = ConsultaTributacao.get(config, "CFF", "classTrib", MeuDTO.class);
 *
 * // 4. Com parâmetros query
 * Map<String, String> params = new HashMap<>();
 * params.put("Cst", "00");
 * String json = ConsultaTributacao. getJson(config, "CFF", "classTrib", params);
 *
 * @author Samuel Oliveira - samuel@swconsultoria.com. br
 */
@Log
public class ConsultaTributacao {

    private ConsultaTributacao() {}

    private static final ObjectMapper MAPPER = createObjectMapper();

    /**
     * Cria ObjectMapper configurado para ser tolerante a mudanças no JSON.
     * Propriedades desconhecidas ou faltantes não causam erro de deserialização.
     */
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature. FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mapper.configure(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return mapper;
    }

    /**
     * Executa requisição HTTP GET e retorna o JSON string bruto.
     *
     * @param config Configurações da NFe contendo certificado digital
     * @param section Seção no arquivo WebServicesNfe.ini (ex: "CFF")
     * @param key Chave da URL na seção (ex: "classTrib")
     * @return String contendo o JSON de resposta
     * @throws NfeException Se houver erro de configuração ou certificado
     * @throws IOException Se houver erro de comunicação HTTP
     */
    public static String getJson(ConfiguracoesNfe config, String section, String key)
            throws NfeException, IOException {
        return getJson(config, section, key, null);
    }

    /**
     * Executa requisição HTTP GET com parâmetros de query e retorna o JSON string bruto.
     *
     * @param config Configurações da NFe contendo certificado digital
     * @param section Seção no arquivo WebServicesNfe. ini (ex: "CFF")
     * @param key Chave da URL na seção (ex: "classTrib")
     * @param queryParams Mapa com parâmetros de query (ex: {"Cst": "00"})
     * @return String contendo o JSON de resposta
     * @throws NfeException Se houver erro de configuração ou certificado
     * @throws IOException Se houver erro de comunicação HTTP
     */
    public static String getJson(ConfiguracoesNfe config, String section, String key, Map<String, String> queryParams)
            throws NfeException, IOException {
        ConfiguracoesUtil.iniciaConfiguracoes(config);

        String urlBase = WebServiceUtil.getCustomUrl(config, section, key);
        String url = buildUrlWithParams(urlBase, queryParams);

        Certificado certificado = config.getCertificado();
        if (certificado == null) {
            throw new NfeException("Certificado digital não configurado");
        }

        log.info("[ConsultaTributacao] Requisitando: " + url);

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
                "Não foi possível configurar SSL/TLS para a requisição.  " +
                        "Verifique se o certificado está corretamente configurado."
        );
    }

    /**
     * Executa requisição HTTP GET e converte o JSON para o tipo especificado.
     * Método genérico que aceita qualquer classe DTO.
     *
     * Exemplo de uso:
     * <pre>
     * MeuDTO objeto = ConsultaTributacao.get(config, "CFF", "classTrib", MeuDTO.class);
     * </pre>
     *
     * @param <T> Tipo do objeto de retorno
     * @param config Configurações da NFe contendo certificado digital
     * @param section Seção no arquivo WebServicesNfe.ini
     * @param key Chave da URL na seção
     * @param clazz Classe do objeto de destino
     * @return Objeto do tipo T com os dados deserializados do JSON
     * @throws NfeException Se houver erro de configuração, certificado ou conversão
     * @throws IOException Se houver erro de comunicação HTTP
     */
    public static <T> T get(ConfiguracoesNfe config, String section, String key, Class<T> clazz)
            throws NfeException, IOException {
        String json = getJson(config, section, key);
        return convertJsonToObject(json, clazz);
    }

    /**
     * Executa requisição HTTP GET com parâmetros e converte o JSON para o tipo especificado.
     *
     * Exemplo de uso:
     * <pre>
     * Map<String, String> params = new HashMap<>();
     * params.put("Cst", "00");
     * MeuDTO objeto = ConsultaTributacao.get(config, "CFF", "classTrib", params, MeuDTO.class);
     * </pre>
     *
     * @param <T> Tipo do objeto de retorno
     * @param config Configurações da NFe
     * @param section Seção no arquivo INI
     * @param key Chave da URL
     * @param queryParams Parâmetros de query
     * @param clazz Classe do objeto de destino
     * @return Objeto do tipo T deserializado
     * @throws NfeException Se houver erro
     * @throws IOException Se houver erro de I/O
     */
    public static <T> T get(ConfiguracoesNfe config, String section, String key,
                     Map<String, String> queryParams, Class<T> clazz)
            throws NfeException, IOException {
        String json = getJson(config, section, key, queryParams);
        return convertJsonToObject(json, clazz);
    }

    /**
     * Executa requisição HTTP GET e converte o JSON para tipos complexos (ex: List, Map).
     * Usa TypeReference do Jackson para preservar informações de tipo genérico.
     *
     * Exemplo de uso:
     * <pre>
     * List<MeuDTO> lista = ConsultaTributacao.get(config, "CFF", "classTrib",
     *     new TypeReference<List<MeuDTO>>() {});
     *
     * Map<String, MeuDTO> mapa = ConsultaTributacao.get(config, "CFF", "classTrib",
     *     new TypeReference<Map<String, MeuDTO>>() {});
     * </pre>
     *
     * @param <T> Tipo do objeto de retorno
     * @param config Configurações da NFe
     * @param section Seção no arquivo INI
     * @param key Chave da URL
     * @param typeRef TypeReference com o tipo genérico desejado
     * @return Objeto do tipo T deserializado
     * @throws NfeException Se houver erro
     * @throws IOException Se houver erro de I/O
     */
    public static <T> T get(ConfiguracoesNfe config, String section, String key, TypeReference<T> typeRef)
            throws NfeException, IOException {
        String json = getJson(config, section, key);
        return convertJsonToObject(json, typeRef);
    }

    /**
     * Executa requisição HTTP GET com parâmetros e converte para tipos complexos.
     *
     * @param <T> Tipo do objeto de retorno
     * @param config Configurações da NFe
     * @param section Seção no arquivo INI
     * @param key Chave da URL
     * @param queryParams Parâmetros de query
     * @param typeRef TypeReference com o tipo genérico
     * @return Objeto do tipo T deserializado
     * @throws NfeException Se houver erro
     * @throws IOException Se houver erro de I/O
     */
    public static <T> T get(ConfiguracoesNfe config, String section, String key,
                     Map<String, String> queryParams, TypeReference<T> typeRef)
            throws NfeException, IOException {
        String json = getJson(config, section, key, queryParams);
        return convertJsonToObject(json, typeRef);
    }

    /**
     * Converte JSON string para objeto do tipo especificado.
     * Propriedades desconhecidas no JSON são ignoradas automaticamente.
     *
     * @param <T> Tipo do objeto de retorno
     * @param json String JSON a ser convertida
     * @param clazz Classe do objeto de destino
     * @return Objeto deserializado
     * @throws NfeException Se houver erro na conversão
     */
    private static <T> T convertJsonToObject(String json, Class<T> clazz) throws NfeException {
        try {
            log.info("[ConsultaTributacao] Convertendo JSON para " + clazz.getSimpleName());
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            log.severe("[ConsultaTributacao] Erro ao converter JSON: " + e.getMessage());
            throw new NfeException("Erro ao processar resposta JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Converte JSON string para tipo complexo usando TypeReference.
     *
     * @param <T> Tipo do objeto de retorno
     * @param json String JSON a ser convertida
     * @param typeRef TypeReference com informações de tipo genérico
     * @return Objeto deserializado
     * @throws NfeException Se houver erro na conversão
     */
    private static <T> T convertJsonToObject(String json, TypeReference<T> typeRef) throws NfeException {
        try {
            log.info("[ConsultaTributacao] Convertendo JSON para tipo complexo");
            return MAPPER.readValue(json, typeRef);
        } catch (IOException e) {
            log.severe("[ConsultaTributacao] Erro ao converter JSON: " + e. getMessage());
            throw new NfeException("Erro ao processar resposta JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Constrói URL completa com parâmetros de query.
     *
     * @param baseUrl URL base
     * @param queryParams Mapa de parâmetros (pode ser null)
     * @return URL completa com query string
     */
    private static String buildUrlWithParams(String baseUrl, Map<String, String> queryParams) {
        if (queryParams == null || queryParams.isEmpty()) {
            return baseUrl;
        }

        StringBuilder url = new StringBuilder(baseUrl);
        boolean first = ! baseUrl.contains("?");

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().trim().isEmpty()) {
                url.append(first ? "?" : "&");
                url.append(entry.getKey()).append("=").append(entry.getValue());
                first = false;
            }
        }

        return url.toString();
    }

    /**
     * Cria HttpClient configurado com certificado digital.
     */
    private static HttpClient createHttpClient(ConfiguracoesNfe config, Certificado certificado, String url)
            throws CertificadoException {
        if (ObjetoUtil.verifica(config.getCacert()). isPresent()) {
            return CertificadoService.getHttpsClient(certificado, url, config.getCacert());
        } else {
            return CertificadoService.getHttpsClient(certificado, url);
        }
    }

    /**
     * Executa requisição HTTP GET usando Apache HttpClient.
     */
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

    /**
     * Executa requisição usando SSLSocketFactory (fallback).
     */
    private static String executeRequestWithSslFactory(SSLSocketFactory sslFactory, String url) throws IOException {
        HttpsURLConnection conn = null;
        InputStream is = null;

        try {
            URL u = new URL(url);
            conn = (HttpsURLConnection) u.openConnection();
            conn.setSSLSocketFactory(sslFactory);
            conn.setRequestMethod("GET");
            conn. setRequestProperty("Accept", "application/json");
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
                try { is.close(); } catch (IOException ignored) {}
            }
            if (conn != null) {
                conn. disconnect();
            }
        }
    }

    /**
     * Tenta resolver SSLSocketFactory via reflexão (fallback).
     */
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
            } catch (NoSuchMethodException ignored) {}

            try {
                java.lang.reflect.Method m2 = svc.getMethod("getSslContext",
                        Class.forName("br.com.swconsultoria.certificado.Certificado"));
                Object res2 = m2.invoke(null, config.getCertificado());
                if (res2 instanceof SSLContext) {
                    return ((SSLContext) res2).getSocketFactory();
                }
            } catch (NoSuchMethodException ignored) {}

        } catch (Throwable ignored) {}

        Object cert = config.getCertificado();
        if (cert != null) {
            try {
                java.lang.reflect.Method m = cert.getClass().getMethod("getSSLSocketFactory");
                Object res = m.invoke(cert);
                if (res instanceof SSLSocketFactory) {
                    return (SSLSocketFactory) res;
                }
            } catch (Throwable ignored) {}

            try {
                java.lang.reflect.Method m2 = cert.getClass().getMethod("getSslContext");
                Object res2 = m2.invoke(cert);
                if (res2 instanceof SSLContext) {
                    return ((SSLContext) res2).getSocketFactory();
                }
            } catch (Throwable ignored) {}
        }

        return null;
    }

    /**
     * Converte InputStream para String (compatível Java 8).
     */
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
}
