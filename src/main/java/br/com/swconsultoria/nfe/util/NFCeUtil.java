package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.exception.NfeException;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.Optional;

/**
 * @author Samuel Oliveira
 */
public class NFCeUtil {

    /**
     * Funcao Responsavel por Devolver o QrCode já no padrão da Nota.
     *
     * @param chave       : Chave de Acesso da NFCe
     * @param ambiente    : Identificação do Ambiente (1 – Produção, 2 – Homologação)
     * @param idToken     : Identificador do CSC – Código de Segurança do Contribuinte no Banco de Dados da SEFAZ
     * @param CSC         : Código de Segurança do Contribuinte (antigo Token)
     * @param urlConsulta : Url De Consulta da Nfc-e do Estado
     *                    <p>
     *                    A NT 2025-001 define que deve ser utilizado o padrao v3 para o QrCode da NFC-e.
     *                    Essa funcao será deletada em breve, e a nova funcao getCodeQRCodeV3 deve ser utilizada.
     * @return String do QrCode
     */
    @Deprecated
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
     * Funcao Responsavel por Devolver o QrCode V3 já no padrão da Nota.
     *
     * @param chave       : Chave de Acesso da NFCe
     * @param ambiente    : Identificação do Ambiente (1 – Produção, 2 – Homologação)
     * @param urlConsulta : Url De Consulta da Nfc-e do Estado
     *
     * Para NFC-e emitida “on-line”: https://endereco-consulta-QRCode?p=<chave_acesso>|<versao_qrcode>|<tpAmb>
     *
     * @return String do QrCode
     */
    public static String getCodeQRCodeV3(String chave, String ambiente, String urlConsulta) {
        return String.format("%s?p=%s|3|%s", urlConsulta, chave, ambiente);
    }

    /**
     * Funcao Responsavel por Devolver o QrCode já no padrão da Nota.
     *
     * @param chave       : Chave de Acesso da NFCe
     * @param ambiente    : Identificação do Ambiente (1 – Produção, 2 – Homologação)
     * @param idToken     : Identificador do CSC – Código de Segurança do Contribuinte no Banco de Dados da SEFAZ
     * @param CSC         : Código de Segurança do Contribuinte (antigo Token)
     * @param urlConsulta : Url De Consulta da Nfc-e do Estado
     *                    <p>
     *  A NT 2025-001 define que deve ser utilizado o padrao v3 para o QrCode da NFC-e.
     *  Essa funcao será deletada em breve, e a nova funcao getCodeQRCodeV3 deve ser utilizada.
     *
     * @return String do QrCode
     */
    @Deprecated
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
     * Funcao Responsavel por Devolver o QrCode V3 de contingencia já no padrão da Nota.
     *
     * @param chave          : Chave de Acesso da NFCe
     * @param ambiente       : Identificação do Ambiente (1 – Produção, 2 – Homologação)
     * @param dhEmi          : Campo dhEmi (B09) da NFCe
     * @param valorNF        : Campo de Valor da Nota (W16)
     * @param tpDestinatario : 1=CNPJ; 2=CPF; 3=idEstrangeiro; Caso Destinatário estrangeiro ou não identificado, informar apenas nulo ou vazio
     * @param identDest      : Identificação do Destinatário CPF ou CNPJ na NFC-e.; Caso Destinatário estrangeiro ou não identificado, informar apenas nulo ou vazio
     * @param urlConsulta    : Url De Consulta da Nfc-e do Estado
     *
     * Para NFC-e emitida em contingência “off-line”:
     * https://endereco-consultaQRCode?p=<chave_acesso>|<versao_qrcode>|<tpAmb>|<dia_data_emissao>|<vNF>|<tp_idDest>|<idDest>|<assinatura>
     *
     * @return String do QrCode
     */
    public static String getCodeQRCodeContingenciaV3(String chave, String ambiente, String dhEmi, String valorNF,
                                                     String tpDestinatario, String identDest, String urlConsulta,
                                                     Certificado certificado) throws NfeException {

        String valor = String.format("%s|3|%s|%s|%s|%s|%s",
                chave, ambiente, dhEmi.substring(8, 10), valorNF,
                Optional.ofNullable(tpDestinatario).orElse(""),
                Optional.ofNullable(identDest).orElse(""));

        String assinatura = assinarQrCodeV3(valor, certificado);

        return urlConsulta + "?p=" + valor + "|" + assinatura;
    }

    /**
     * Função responsável por gerar o hashCSRT conforme definições da NT2018.005 v1.40.
     *
     * @param chave Chave da nota fiscal com 44 caracteres.
     * @param csrt  Token/Código de Segurança do Responsável Técnico, fornecido pela Sefaz de da estado.
     * @return bytes conforme definição da NF2018.005 v1.40 sem fazer encode em base64.
     * Isso porque já será feito ao gerar o xml devido ao tipo no XSD ser xs:base64Binary.
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

    private static String assinarQrCodeV3(String dados, Certificado certificado) throws NfeException {
        try {
            KeyStore keyStore = CertificadoService.getKeyStore(certificado);

            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(certificado.getNome(),
                    new KeyStore.PasswordProtection(ObjetoUtil.verifica(certificado.getSenha()).orElse("").toCharArray()));

            PrivateKey privateKey = pkEntry.getPrivateKey();
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            signature.update(dados.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signature.sign();
            return Base64.getEncoder().encodeToString(signed);
        } catch (Exception e) {
            throw new NfeException("Erro ao gerar assinatura do QRCode V3: " + e.getMessage(), e);
        }
    }

}  

