package br.com.swconsultoria.nfe.util;

import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 02/03/2019 - 23:05
 */
public class RetornoUtil {

    /**
     * Valida o Retorno Do Cancelamento
     *
     * @param retorno
     * @throws NfeException
     */
    public static void validaCancelamento(TRetEnvEvento retorno) throws NfeException {
        if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(retorno.getCStat())) {
            throw new NfeException(retorno.getCStat() + " - " + retorno.getXMotivo());
        }

        final String[] erro = {""};
        retorno.getRetEvento().forEach( retEvento -> {
            if (!StatusEnum.EVENTO_VINCULADO.getCodigo().equals(retEvento.getInfEvento().getCStat())) {
                erro[0] += retEvento.getInfEvento().getChNFe() + " - " +retEvento.getInfEvento().getCStat() + " - " + retEvento.getInfEvento().getXMotivo() + System.lineSeparator();
            }
        });

        if(ObjetoUtil.verifica(erro[0]).isPresent()){
            throw new NfeException(erro[0]);
        }
    }

    /**
     * Valida o Retorno Do Cancelamento Substituicao
     *
     * @param retorno
     * @throws NfeException
     */
    public static void validaCancelamentoSubstituicao(br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento retorno) throws NfeException {
        if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(retorno.getCStat())) {
            throw new NfeException(retorno.getCStat() + " - " + retorno.getXMotivo());
        }

        final String[] erro = {""};
        retorno.getRetEvento().forEach( retEvento -> {
            if (!StatusEnum.EVENTO_VINCULADO.getCodigo().equals(retEvento.getInfEvento().getCStat())) {
                erro[0] += retEvento.getInfEvento().getChNFe() + " - " +retEvento.getInfEvento().getCStat() + " - " + retEvento.getInfEvento().getXMotivo() + System.lineSeparator();
            }
        });

        if(ObjetoUtil.verifica(erro[0]).isPresent()){
            throw new NfeException(erro[0]);
        }
    }

    /**
     * Valida o Retorno Da Manifestação
     *
     * @param retorno
     * @throws NfeException
     */
    public static void validaManifestacao(br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento retorno) throws NfeException {
        if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(retorno.getCStat())) {
            throw new NfeException(retorno.getCStat() + " - " + retorno.getXMotivo());
        }

        final String[] erro = {""};
        retorno.getRetEvento().forEach( retEvento -> {
            if (!StatusEnum.EVENTO_VINCULADO.getCodigo().equals(retEvento.getInfEvento().getCStat())) {
                erro[0] += retEvento.getInfEvento().getChNFe() + " - " +retEvento.getInfEvento().getCStat() + " - " + retEvento.getInfEvento().getXMotivo() + System.lineSeparator();
            }
        });

        if(ObjetoUtil.verifica(erro[0]).isPresent()){
            throw new NfeException(erro[0]);
        }

    }

    /**
     * Valida o Retorno Da Carta de Correção
     *
     * @param retorno
     * @throws NfeException
     */
    public static void validaCartaCorrecao(br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento retorno) throws NfeException {
        if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(retorno.getCStat())) {
            throw new NfeException(retorno.getCStat() + " - " + retorno.getXMotivo());
        }
        final String[] erro = {""};
        retorno.getRetEvento().forEach( retEvento -> {
            if (!StatusEnum.EVENTO_VINCULADO.getCodigo().equals(retEvento.getInfEvento().getCStat())) {
                erro[0] += retEvento.getInfEvento().getChNFe() + " - " +retEvento.getInfEvento().getCStat() + " - " + retEvento.getInfEvento().getXMotivo() + System.lineSeparator();
            }
        });

        if(ObjetoUtil.verifica(erro[0]).isPresent()){
            throw new NfeException(erro[0]);
        }
    }

    /**
     * Valida o Retorno Do EPEC
     *
     * @param retorno
     * @throws NfeException
     */
    public static void validaEpec(br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento retorno) throws NfeException {
        if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(retorno.getCStat())) {
            throw new NfeException(retorno.getCStat() + " - " + retorno.getXMotivo());

        }

        final String[] erro = {""};
        retorno.getRetEvento().forEach( retEvento -> {
            if (!StatusEnum.EVENTO_VINCULADO.getCodigo().equals(retEvento.getInfEvento().getCStat())) {
                erro[0] += retEvento.getInfEvento().getChNFe() + " - " +retEvento.getInfEvento().getCStat() + " - " + retEvento.getInfEvento().getXMotivo() + System.lineSeparator();
            }
        });

        if(ObjetoUtil.verifica(erro[0]).isPresent()){
            throw new NfeException(erro[0]);
        }
    }

    /**
     * Valida o Retorno Da Consulta Cadastro
     *
     * @param retorno
     * @throws NfeException
     */
    public static void validaConsultaCadastro(br.com.swconsultoria.nfe.schema.retConsCad.TRetConsCad retorno) throws NfeException {
        if (!retorno.getInfCons().getCStat().equals(StatusEnum.CADASTRO_ENCONTRADO.getCodigo())) {
            throw new NfeException(retorno.getInfCons().getCStat() + " - " + retorno.getInfCons().getXMotivo());
        }
    }

    /**
     * Valida o Retorno Da Inutilização
     *
     * @param retorno
     * @throws NfeException
     */
    public static void validaInutilizacao(br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe retorno) throws NfeException {
        if (!retorno.getInfInut().getCStat().equals(StatusEnum.INUTILIZADO.getCodigo())) {
            throw new NfeException(retorno.getInfInut().getCStat() + " - " + retorno.getInfInut().getXMotivo());
        }
    }

    /**
     * Verifica se Restorno é Sincrono ou Assincrono
     *
     * @param retorno
     * @return
     */
    public static boolean isRetornoAssincrono(TRetEnviNFe retorno) throws NfeException {
        if (!retorno.getCStat().equals(StatusEnum.LOTE_RECEBIDO.getCodigo()) && !retorno.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
            throw new NfeException(retorno.getCStat() + " - " + retorno.getXMotivo());
        }

        return retorno.getCStat().equals(StatusEnum.LOTE_RECEBIDO.getCodigo());
    }

    /**
     * Valida Retorno Assincrono
     *
     * @param retorno
     * @throws NfeException
     */
    public static void validaAssincrono(TRetConsReciNFe retorno) throws NfeException {

        if (!retorno.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
            throw new NfeException(retorno.getCStat() + " - " + retorno.getXMotivo());
        }

        final String[] erro = {""};
        retorno.getProtNFe().forEach( protNFe -> {
            if (!StatusEnum.AUTORIZADO.getCodigo().equals(protNFe.getInfProt().getCStat())) {
                erro[0] += protNFe.getInfProt().getChNFe() + " - " +protNFe.getInfProt().getCStat() + " - " + protNFe.getInfProt().getXMotivo() + System.lineSeparator();
            }
        });

        if(ObjetoUtil.verifica(erro[0]).isPresent()){
            throw new NfeException(erro[0]);
        }

    }

    /**
     * Valida Retorno Assincrono
     *
     * @param retorno
     * @throws NfeException
     */
    public static void validaSincrono(TRetEnviNFe retorno) throws NfeException {

        if (!retorno.getCStat().equals(StatusEnum.LOTE_RECEBIDO.getCodigo()) && !retorno.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
            throw new NfeException(retorno.getCStat() + " - " + retorno.getXMotivo());
        }

        if (!retorno.getProtNFe().getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo())) {
            throw new NfeException(retorno.getProtNFe().getInfProt().getCStat() + " - " + retorno.getProtNFe().getInfProt().getXMotivo());
        }
    }

}
