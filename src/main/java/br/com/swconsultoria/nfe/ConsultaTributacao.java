package br.com.swconsultoria.nfe;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.util.ConfiguracoesUtil;
import br.com.swconsultoria.nfe.util.WebServiceUtil;
import br.com.swconsultoria.nfe.dto.CstDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Serviço para consulta de classTrib (CFF).
 *
 * Nesta versão: o método getClassTrib(ConfiguracoesNfe) aceita somente a ConfiguracoesNfe.
 * Internamente tenta, na ordem:
 *  1) obter um HttpClient via CertificadoService.getHttpsClient(...) e usá-lo (reaproveita StubUtil/CertificadoService);
 *  2) tentar obter SSLSocketFactory/SSLContext via reflexão em CertificadoService (se disponível);
 *  3) tentar obter SSLSocketFactory/SSLContext do próprio objeto Certificado (se disponível);
 *  4) se nada for possível, lançar IllegalStateException explicando o que chamar.
 *
 * Objetivo: o usuário só precisa passar a ConfiguracoesNfe (como em outras APIs da lib).
 */
public class ConsultaTributacao {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Conveniência: o usuário passa apenas ConfiguracoesNfe.
     * Tenta primeiro criar e usar o HttpClient via CertificadoService (mais robusto e consistente com StubUtil),
     * fazendo fallback para SSLSocketFactory quando necessário.
     */
    public static List<CstDTO> getClassTrib(ConfiguracoesNfe config) throws NfeException, IOException {
        // inicializa configurações e certificado (lança NfeException se algo inválido)
        ConfiguracoesUtil.iniciaConfiguracoes(config);

        String url = WebServiceUtil.getCustomUrl(config, "CFF", "classTrib");
        Certificado certificado = config.getCertificado();

        // 1) Tentar criar HttpClient via CertificadoService (recomendado — igual ao StubUtil)
        if (certificado != null) {
            try {
                HttpClient httpClient;
                InputStream cacertStream = config.getCacert();
                if (cacertStream != null) {
                    // usa overload que aceita InputStream (se disponível na Java_Certificado)
                    httpClient = CertificadoService.getHttpsClient(certificado, url, cacertStream);
                } else {
                    httpClient = CertificadoService.getHttpsClient(certificado, url);
                }
                if (httpClient != null) {
                    return getClassTrib(config, httpClient);
                }
            } catch (CertificadoException ce) {
                // falha ao montar HttpClient — tentaremos fallback abaixo
            } catch (Throwable t) {
                // caso CertificadoService não tenha a assinatura esperada ou outra falha, tentar fallback
            }
        }

        // 2) Tentar obter SSLSocketFactory via CertificadoService (reflexão) ou via Certificado
        SSLSocketFactory sslFactory = tryResolveSslSocketFactory(config);
        if (sslFactory != null) {
            return getClassTrib(config, sslFactory);
        }

        // 3) Não foi possível resolver automaticamente: instruir usuário a passar HttpClient ou habilitar multithreading
        throw new IllegalStateException("Não foi possível resolver mecanismo SSL automaticamente. " +
                "Passe um HttpClient criado por CertificadoService.getHttpsClient(certificado,url) e chame getClassTrib(config, httpClient), " +
                "ou habilite modo multithreading no certificado se preferir que a biblioteca gere o HttpClient automaticamente.");
    }

    /**
     * Implementação usando Apache HttpClient (reaproveita CertificadoService/StubUtil).
     */
    public static List<CstDTO> getClassTrib(ConfiguracoesNfe config, HttpClient httpClient) throws IOException, NfeException {
        String url = WebServiceUtil.getCustomUrl(config, "CFF", "classTrib");

        GetMethod get = new GetMethod(url);
        get.addRequestHeader("Accept", "application/json");
        try {
            int status = httpClient.executeMethod(get);
            if (status != 200) {
                InputStream err = get.getResponseBodyAsStream();
                String body = toString(err);
                throw new IOException("HTTP " + status + " -> " + body);
            }
            try (InputStream responseStream = new BufferedInputStream(get.getResponseBodyAsStream())) {
                return MAPPER.readValue(responseStream, new TypeReference<List<CstDTO>>() {
                });
            }
        } finally {
            get.releaseConnection();
        }
    }

    /**
     * Implementação usando SSLSocketFactory (fallback).
     */
    public static List<CstDTO> getClassTrib(ConfiguracoesNfe config, SSLSocketFactory sslFactory) throws IOException, NfeException {
        String url = WebServiceUtil.getCustomUrl(config, "CFF", "classTrib");

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

            int code = conn.getResponseCode();
            if (code != 200) {
                InputStream err = conn.getErrorStream();
                String body = toString(err);
                throw new IOException("HTTP " + code + " -> " + body);
            }

            is = new BufferedInputStream(conn.getInputStream());
            return MAPPER.readValue(is, new TypeReference<List<CstDTO>>() {});
        } finally {
            if (is != null) {
                try { is.close(); } catch (IOException ignored) {}
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * Tenta resolver SSLSocketFactory via reflexão a partir da infra de certificado existente.
     * Primeira tentativa: métodos estáticos em CertificadoService (getSSLSocketFactory/getSslContext).
     * Segunda tentativa: métodos públicos no próprio objeto Certificado.
     */
    private static SSLSocketFactory tryResolveSslSocketFactory(ConfiguracoesNfe config) {
        // 1) tenta métodos estáticos em CertificadoService
        try {
            Class<?> svc = Class.forName("br.com.swconsultoria.certificado.CertificadoService");
            try {
                java.lang.reflect.Method m = svc.getMethod("getSSLSocketFactory", Class.forName("br.com.swconsultoria.certificado.Certificado"));
                Object res = m.invoke(null, config.getCertificado());
                if (res instanceof SSLSocketFactory) {
                    return (SSLSocketFactory) res;
                }
            } catch (NoSuchMethodException ignored) {}
            try {
                java.lang.reflect.Method m2 = svc.getMethod("getSslContext", Class.forName("br.com.swconsultoria.certificado.Certificado"));
                Object res2 = m2.invoke(null, config.getCertificado());
                if (res2 instanceof SSLContext) {
                    return ((SSLContext) res2).getSocketFactory();
                }
            } catch (NoSuchMethodException ignored) {}
        } catch (Throwable ignored) {}

        // 2) tenta métodos no próprio objeto Certificado (getSSLSocketFactory / getSslContext / getSocketFactory)
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
            try {
                java.lang.reflect.Method m3 = cert.getClass().getMethod("getSocketFactory");
                Object res3 = m3.invoke(cert);
                if (res3 instanceof SSLSocketFactory) {
                    return (SSLSocketFactory) res3;
                }
            } catch (Throwable ignored) {}
        }

        return null;
    }

    private static String toString(InputStream is) throws IOException {
        if (is == null) {
            return "";
        }
        // leitura compatível com Java 8
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read;
        while ((read = is.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        return new String(baos.toByteArray(), StandardCharsets.UTF_8);
    }
}
