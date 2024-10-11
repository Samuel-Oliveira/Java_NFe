package br.com.swconsultoria.nfe.util;

import java.security.InvalidParameterException;
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
     *
     * Função responsável por gerar o hashCSRT conforme definições da NT2018.005 v1.40.
     *
     * @param chave Chave da nota fiscal com 44 caracteres.
     * @param csrt Token/Código de Segurança do Responsável Técnico, fornecido pela Sefaz de da estado.
     * @return bytes conforme definição da NF2018.005 v1.40 sem fazer encode em base64.
     *         Isso porque já será feito ao gerar o xml devido ao tipo no XSD ser xs:base64Binary.
     * @throws NoSuchAlgorithmException caso não encontre suporte para SHA-1.
     */
    public static byte[] geraHashCSRT(String chave, String csrt) throws NoSuchAlgorithmException {
        ObjetoUtil.verifica(chave).orElseThrow(() -> new InvalidParameterException("Chave não deve ser nula ou vazia"));
        ObjetoUtil.verifica(csrt).orElseThrow(() -> new InvalidParameterException("CSRT não deve ser nulo ou vazio"));
        if (chave.length() != 44) {
            throw new InvalidParameterException("Chave deve conter 44 caracteres.");
        }

        return getHash(csrt + chave);
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

