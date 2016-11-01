package br.com.samuelweb.nfe.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
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

	// Construtor
	public CertificadoUtil() throws NfeException {
		configuracoesNfe = ConfiguracoesIniciaisNfe.getInstance();
	}
	
	public static Certificado certificadoPfx(String caminhoCertificado, String senha) throws NfeException{
		
		Certificado certificado = new Certificado();
		try{
			certificado.setArquivo(caminhoCertificado);
			certificado.setSenha(senha);
			
			KeyStore ks = getKeyStore(certificado);
			ks.load(null, null);
	
			Enumeration<String> aliasEnum = ks.aliases();
			String aliasKey = (String) aliasEnum.nextElement();
			certificado.setNome(aliasKey);
			certificado.setTipo(Certificado.ARQUIVO);
			certificado.setVencimento(DataValidade(certificado).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			certificado.setDiasRestantes(diasRestantes(certificado));
			certificado.setValido(valido(certificado));
		}catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException e){
			throw new NfeException("Erro ao carregar informações do certificado:"+e.getMessage());
		}
			
		return certificado;
			
	}

	/**
	 * Retorna a Lista de Certificados do Repositorio do Windows
	 * 
	 */ 
	public static List<Certificado> listaCertificadosWindows() throws NfeException {

		// Estou setando a variavel para 20 dispositivos no maximo
		List<Certificado> listaCert = new ArrayList<>(20);

		try {
			KeyStore ks = KeyStore.getInstance("Windows-MY", "SunMSCAPI");

			ks.load(null, null);

			Enumeration<String> aliasEnum = ks.aliases();

			while (aliasEnum.hasMoreElements()) {
				String aliasKey = (String) aliasEnum.nextElement();

				if (ObjetoUtil.differentNull(aliasKey)) {
					Certificado cert = new Certificado();
					cert.setNome(aliasKey);
					cert.setTipo(Certificado.WINDOWS);
					cert.setSenha("");
					Date dataValidade = DataValidade(cert);
					if(dataValidade == null){
						cert.setNome("(INVÁLIDO)"+aliasKey);
						cert.setVencimento(LocalDate.of(2000, 1, 1));
						cert.setDiasRestantes(0L);
						cert.setValido(false);
					}else{
						cert.setVencimento(dataValidade.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
						cert.setDiasRestantes(diasRestantes(cert)); 
						cert.setValido(valido(cert));
					}
					
					listaCert.add(cert);
				}

			}

		} catch (KeyStoreException | NoSuchProviderException | NoSuchAlgorithmException | CertificateException
				| IOException ex) {
			throw new NfeException("Erro ao Carregar Certificados:" + ex.getMessage());
		}

		return listaCert;

	}

	// Procedimento que retorna a Data De Validade Do Certificado Digital

	private static Date DataValidade(Certificado certificado) throws NfeException {

		Date data = new Date();
		
		try {
			X509Certificate cert = null;
			KeyStore.PrivateKeyEntry pkEntry = null;
			KeyStore ks = null;
			if(certificado.getTipo().equals(Certificado.WINDOWS)){
				ks = KeyStore.getInstance("Windows-MY", "SunMSCAPI");
			}else if(certificado.getTipo().equals(Certificado.ARQUIVO)){
				ks = getKeyStore(certificado);
			}
			
			ks.load(null, null);
			if (ks.isKeyEntry(certificado.getNome())) {
				pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(certificado.getNome(), new KeyStore.PasswordProtection(certificado.getSenha().toCharArray()));
			}else{
				return null;
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
	private static Long diasRestantes(Certificado certificado) throws NfeException {

		Date data = DataValidade(certificado);
		if (data == null) {
			return null;
		}
		long differenceMilliSeconds = data.getTime() - new Date().getTime();
		return differenceMilliSeconds / 1000 / 60 / 60 / 24;
	}

	// retorna True se o Certificado for validao
	public static boolean valido(Certificado certificado) throws NfeException {

		if (DataValidade(certificado) != null && DataValidade(certificado).after(new Date())) {
			return true;
		} else {
			return false;
		}

	}

	public void iniciaConfiguracoes() throws NfeException {

		System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		System.clearProperty("javax.net.ssl.keyStore");
		System.clearProperty("javax.net.ssl.keyStorePassword");
		System.clearProperty("javax.net.ssl.trustStore");
		
		if(configuracoesNfe.getProxy()!=null){
			System.setProperty("http.proxyHost", configuracoesNfe.getProxy().getProxyHostName());
			System.setProperty("http.proxyPort", configuracoesNfe.getProxy().getProxyPort());
			System.setProperty("http.proxyUser", configuracoesNfe.getProxy().getProxyUserName()); 
			System.setProperty("http.proxyPassword", configuracoesNfe.getProxy().getProxyPassWord()); 
		}

		System.setProperty("jdk.tls.client.protocols", "TLSv1"); // Servidor do	Sefaz RS

		if(configuracoesNfe.getCertificado().getTipo().equals(Certificado.WINDOWS)){
			System.setProperty("javax.net.ssl.keyStoreProvider", "SunMSCAPI");
			System.setProperty("javax.net.ssl.keyStoreType", "Windows-MY");
			System.setProperty("javax.net.ssl.keyStoreAlias", configuracoesNfe.getCertificado().getNome());
		}else if(configuracoesNfe.getCertificado().getTipo().equals(Certificado.ARQUIVO)){
			System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");  
			System.setProperty("javax.net.ssl.keyStore", configuracoesNfe.getCertificado().getArquivo());  
		}

		System.setProperty("javax.net.ssl.keyStorePassword", configuracoesNfe.getCertificado().getSenha());

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

	public static KeyStore getKeyStore(Certificado certificado) throws NfeException{
		try {
	        File file = new File(certificado.getArquivo());
	        if(!file.exists()){
				throw new NfeException("Certificado Digital não Encontrado");
	        }
	        
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new ByteArrayInputStream(getBytesFromInputStream(new FileInputStream(file))), certificado.getSenha().toCharArray());
			return keyStore;
		} catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException e) {
			throw new NfeException("Erro Ao Ler Certificado: "+e.getMessage());
		}
		
	}
	
	public static byte[] getBytesFromInputStream(InputStream is) throws IOException
	{
	    try (ByteArrayOutputStream os = new ByteArrayOutputStream();)
	    {
	        byte[] buffer = new byte[0xFFFF];

	        for (int len; (len = is.read(buffer)) != -1;)
	            os.write(buffer, 0, len);

	        os.flush();

	        return os.toByteArray();
	    }
	}

}