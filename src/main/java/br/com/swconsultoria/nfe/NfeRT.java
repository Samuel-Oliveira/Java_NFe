package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema_rt.nfe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_rt.nfe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_rt.nfe.TRetEnviNFe;
import br.com.swconsultoria.nfe.util.ConfiguracoesUtil;

/**
 * @author Samuel Oliveira - samuel@swconsultoria.com.br - www.swconsultoria.com.br
 */
public class NfeRT {

    /**
     * Construtor privado
     */
    private NfeRT() {
    }

    /**
     * Classe Reponsavel Por Consultar o retorno da NFE na SEFAZ No tipo Informar
     *
     * @param recibo
     * @param tipoDocumento USAR DocumentoEnum.NFE ou DocumentoEnum.NFCE
     * @return
     * @throws NfeException
     */
    public static TRetConsReciNFe consultaRecibo(ConfiguracoesNfe configuracoesNfe, String recibo, DocumentoEnum tipoDocumento) throws NfeException {
        return ConsultaReciboRT.reciboNfe(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe), recibo, tipoDocumento);
    }

    /**
     * Metodo para Montar a NFE
     *
     * @param enviNFe
     * @param valida
     * @return
     * @throws NfeException
     */
    public static TEnviNFe montaNfe(ConfiguracoesNfe configuracoesNfe, TEnviNFe enviNFe, boolean valida) throws NfeException {

        return EnviarRT.montaNfe(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe, enviNFe.getNFe().get(0).getInfNFe().getEmit().getCNPJ()), enviNFe,
                valida);

    }

    /**
     * Metodo para Enviar a NFE
     *
     * @param enviNFe
     * @param tipoDocumento No tipo Informar DocumentoEnum.NFE ou DocumentoEnum.NFCE
     * @return
     * @throws NfeException
     */
    public static TRetEnviNFe enviarNfe(ConfiguracoesNfe configuracoesNfe, TEnviNFe enviNFe, DocumentoEnum tipoDocumento) throws NfeException {

        return EnviarRT.enviaNfe(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe, enviNFe.getNFe().get(0).getInfNFe().getEmit().getCNPJ()), enviNFe,
                tipoDocumento);

    }

}
