package br.com.samuelweb.nfe.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import br.com.samuelweb.nfe.Certificado;
import br.com.samuelweb.nfe.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;

/**
 * Classe Responsavel Por Carregar os Certificados Do Repositorio do Windows
 * 
 * @author SaMuK
 * 
 */
public class CertificadoUtil {
	
	private static ConfiguracoesIniciaisNfe configuracoesNfe;
	
	//Construtor
	public CertificadoUtil() throws NfeException{
		configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();
	}

	// Procedimento de listagem dos certificados digitais
	public static List<Certificado> listaCertificados() throws NfeException  {

		// Estou setando a variavel para 20 dispositivos no maximo
		List<Certificado> listaCert = new ArrayList<>(20);

		try {
			KeyStore ks = KeyStore.getInstance("Windows-MY", "SunMSCAPI");

			ks.load(null, null);

			Enumeration<String> aliasEnum = ks.aliases();

			while (aliasEnum.hasMoreElements()) {
				Certificado cert;
				String aliasKey = (String) aliasEnum.nextElement();

				if (ObjetoUtil.differentNull(aliasKey)) {
					cert = new Certificado(aliasKey, DataValidade(aliasKey).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), diasRestantes(aliasKey) , valido(aliasKey));
					listaCert.add(cert);
				}

			}

		} catch (KeyStoreException | NoSuchProviderException | NoSuchAlgorithmException | CertificateException | IOException ex) {
			throw new NfeException("Erro ao Carregar Certificados:"+ex.getMessage());
		}

		return listaCert;

	}

	// Procedimento que retorna a Data De Validade Do Certificado Digital

	private static Date DataValidade(String certificado) throws NfeException {

		Date data = new Date();
		
		try {
			X509Certificate cert = null;
			KeyStore.PrivateKeyEntry pkEntry = null;
			@SuppressWarnings("unused")
			PrivateKey privateKey;
	
			KeyStore ks = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
			ks.load(null, null);
			if (ks.isKeyEntry(certificado)) {
				pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(certificado, new KeyStore.PasswordProtection("".toCharArray()));
				privateKey = pkEntry.getPrivateKey();

			}
	
			cert = (X509Certificate) pkEntry.getCertificate();
	
			if (cert == null) {
				return null;
			}
			data = cert.getNotAfter();
		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException | NoSuchProviderException | CertificateException | IOException e) {
			throw new NfeException("Erro ao Pegar Data Certificado:"+e.getMessage());
		}
		
		return data;

	}

	// Retorna Os dias Restantes do Certificado Digital
	private static Long diasRestantes(String certificado) throws NfeException {

		Date data = DataValidade(certificado);
		if ( data == null) {
			return null;
		}
		long differenceMilliSeconds = data.getTime() - new Date().getTime();
		return differenceMilliSeconds / 1000 / 60 / 60 / 24;
	}

	// retorna True se o Certificado for validao
	public static boolean valido(String certificado) throws NfeException {

		if (DataValidade(certificado) != null && DataValidade(certificado).after(new Date())) {
			return true;
		} else {
			return false;
		}

	}

	@SuppressWarnings("restriction")
	public void iniciaConfiguracoes() throws NfeException {

		System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");  
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		System.clearProperty("javax.net.ssl.keyStore");
		System.clearProperty("javax.net.ssl.keyStorePassword");
		System.clearProperty("javax.net.ssl.trustStore");
		
		System.setProperty("jdk.tls.client.protocols", "TLSv1"); // Servidor do Sefaz RS 

		System.setProperty("javax.net.ssl.keyStoreProvider", "SunMSCAPI");
		System.setProperty("javax.net.ssl.keyStoreType", "Windows-MY");

		System.setProperty("javax.net.ssl.keyStoreAlias", configuracoesNfe.getCertificado());
		System.setProperty("javax.net.ssl.keyStorePassword", "");

		System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		
		//Extrair Cacert do Jar
		String cacert = "";
        try {
            InputStream input = getClass().getResourceAsStream("/NFeCacerts");
            File file = File.createTempFile("tempfile", ".tmp");
            OutputStream out = new FileOutputStream(file);
            int read;
            byte[] bytes = new byte[1024];

            while ((read = input.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.close();
            cacert = file.getAbsolutePath();
            file.deleteOnExit();
        } catch (IOException ex) {
            throw new NfeException(ex.getMessage());
        }
	   
		System.setProperty("javax.net.ssl.trustStore", cacert);

	}

}