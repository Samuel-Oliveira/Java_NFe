package br.com.swconsultoria.nfe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;  
  
/** 
* 
* @author Samuel Oliveira
*/
public class NFCeUtil {

    /**
     *
     * Funcao Responsavel por Devolver o QrCode já no padrão da Nota.
     *
     * @param chave : Chave de Acesso da NFCe
     * @param ambiente : Identificação do Ambiente (1 – Produção, 2 – Homologação)
     * @param idToken : Identificador do CSC – Código de Segurança do Contribuinte no Banco de Dados da SEFAZ
     * @param CSC : Código de Segurança do Contribuinte (antigo Token)
     * @param urlConsulta : Url De Consulta da Nfc-e do Estado
     *
     * @return String do QrCode
     */
    public static String getCodeQRCode(String chave, String ambiente, String idToken, String CSC, String urlConsulta) throws NoSuchAlgorithmException {

        StringBuilder value = new StringBuilder();
        value.append(chave);
        value.append("|").append("2");
        value.append("|").append(ambiente);
        value.append("|").append(Integer.valueOf(idToken));
        String cHashQRCode = getHexa(getHash(value.toString() + CSC)).toUpperCase();

        return urlConsulta + "?p=" + value + "|" + cHashQRCode;
    }

    /**
     * Funcao Responsavel por Devolver o QrCode já no padrão da Nota.
     *
     * @param chave       : Chave de Acesso da NFCe
     * @param ambiente    : Identificação do Ambiente (1 – Produção, 2 – Homologação)
     * @param idToken     : Identificador do CSC – Código de Segurança do Contribuinte no Banco de Dados da SEFAZ
     * @param CSC         : Código de Segurança do Contribuinte (antigo Token)
     * @param urlConsulta : Url De Consulta da Nfc-e do Estado
     * @return String do QrCode
     */
    public static String getCodeQRCodeContingencia(String chave, String ambiente, String dhEmi, String valorNF, String digVal, String idToken, String CSC, String urlConsulta) throws NoSuchAlgorithmException {

        StringBuilder value = new StringBuilder();
        value.append(chave);
        value.append("|").append("2");
        value.append("|").append(ambiente);
        value.append("|").append(dhEmi, 8, 10);
        value.append("|").append(valorNF);
        value.append("|").append(getHexa(digVal));
        value.append("|").append(Integer.valueOf(idToken));
        String cHashQRCode = getHexa(getHash(value.toString() + CSC)).toUpperCase();

        return urlConsulta + "?p=" + value + "|" + cHashQRCode;
    }

 
    /** 
     * @param valor 
     * @return 
     */  
    private static byte[] getHash(String valor) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(valor.getBytes());  
            return md.digest();  
    }
  
    private static String getHexa(String valor) {  
        return getHexa(valor.getBytes());  
    }  
  
    private static String getHexa(byte[] bytes) {  
        StringBuilder s = new StringBuilder();
        for (byte aByte : bytes) {
            int parteAlta = ((aByte >> 4) & 0xf) << 4;
            int parteBaixa = aByte & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }  
        return s.toString();  
    }  

}  

