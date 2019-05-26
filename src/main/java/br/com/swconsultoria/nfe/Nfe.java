package br.com.swconsultoria.nfe;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.retConsCad.TRetConsCad;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.util.ConfiguracoesUtil;

/**
 * @author Samuel Oliveira - samuel@swconsultoria.com.br - www.swconsultoria.com.br
 */
public class Nfe {

    /**
     * Construtor privado
     */
    private Nfe() {
    }

    /**
     * Classe Reponsavel Por Consultar a Distribuiçao da NFE na SEFAZ
     *
     * @param tipoPessoa   Informar PessoaEnum.CPF ou PessoaEnum.CNPJ
     * @param cpfCnpj
     * @param tipoConsulta Informar ConsultaDFe.NSU ou ConsultaDFe.CHAVE
     * @param nsuChave
     * @return
     * @throws NfeException
     */
    public static RetDistDFeInt distribuicaoDfe(ConfiguracoesNfe configuracoesNfe, PessoaEnum tipoPessoa, String cpfCnpj,
                                                ConsultaDFeEnum tipoConsulta, String nsuChave) throws NfeException {

        return DistribuicaoDFe.consultaNfe(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe, cpfCnpj), tipoPessoa, cpfCnpj, tipoConsulta, nsuChave);

    }

    /**
     * Metodo Responsavel Buscar o Status de Serviço do Servidor da Sefaz
     *
     * @param tipoDocumento informar DocumentoEnum.NFE ou DocumentoEnum.NFCE
     * @return TRetConsStatServ - objeto a mensagem de retorno da
     * transmissão.
     * @throws NfeException
     */
    public static TRetConsStatServ statusServico(ConfiguracoesNfe configuracoesNfe, DocumentoEnum tipoDocumento) throws NfeException {

        return Status.statusServico(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe), tipoDocumento);

    }

    /**
     * Classe Reponsavel Por Consultar o status da NFE na SEFAZ No tipo Informar
     *
     * @param chave
     * @param tipoDocumento USAR DocumentoEnum.NFE ou DocumentoEnum.NFCE
     * @return TRetConsSitNFe
     * @throws NfeException
     */
    public static TRetConsSitNFe consultaXml(ConfiguracoesNfe configuracoesNfe, String chave, DocumentoEnum tipoDocumento) throws NfeException {

        return ConsultaXml.consultaXml(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe), chave, tipoDocumento);

    }

    /**
     * Classe Reponsavel Por Consultar o cadastro do Cnpj/CPF na SEFAZ
     *
     * @param tipoPessoa Usar PessoaEnum.CNPJ ou PessoaEnum.CPF
     * @param cnpjCpf
     * @param estado
     * @return TRetConsCad
     * @throws NfeException
     */
    public static TRetConsCad consultaCadastro(ConfiguracoesNfe configuracoesNfe, PessoaEnum tipoPessoa, String cnpjCpf, EstadosEnum estado) throws NfeException {

        return ConsultaCadastro.consultaCadastro(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe), tipoPessoa, cnpjCpf, estado);

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
        return ConsultaRecibo.reciboNfe(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe), recibo, tipoDocumento);
    }

    /**
     * Classe Reponsavel Por Inutilizar a NFE na SEFAZ
     * No tipo Informar DocumentoEnum.NFE ou DocumentoEnum.NFCE
     *
     * @param tipoDocumento
     * @return
     * @throws NfeException
     */
    public static TRetInutNFe inutilizacao(ConfiguracoesNfe configuracoesNfe, TInutNFe inutNFe, DocumentoEnum tipoDocumento, boolean validar) throws NfeException {
        return Inutilizar.inutiliza(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe,inutNFe.getInfInut().getCNPJ()), inutNFe, tipoDocumento, validar);
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

        return Enviar.montaNfe(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe, enviNFe.getNFe().get(0).getInfNFe().getEmit().getCNPJ()), enviNFe, valida);

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

        return Enviar.enviaNfe(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe, enviNFe.getNFe().get(0).getInfNFe().getEmit().getCNPJ()), enviNFe, tipoDocumento);

    }

    /**
     * Metodo para Cancelar a NFE
     * No tipo Informar DocumentoEnum.NFE ou DocumentoEnum.NFCE
     *
     * @param envEvento
     * @param valida
     * @return
     * @throws NfeException
     */
    public static TRetEnvEvento cancelarNfe(ConfiguracoesNfe configuracoesNfe, TEnvEvento envEvento, boolean valida, DocumentoEnum tipoDocumento) throws NfeException {

        return Cancelar.eventoCancelamento(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe, envEvento.getEvento().get(0).getInfEvento().getCNPJ()), envEvento, valida, tipoDocumento);

    }

    /**
     * Metodo para Cancelar a NFCE em Substituicao
     *
     * @param envEvento
     * @param valida
     * @return
     * @throws NfeException
     */
    public static br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento cancelarSubstituicaoNfe(ConfiguracoesNfe configuracoesNfe, br.com.swconsultoria.nfe.schema.envEventoCancSubst.TEnvEvento envEvento, boolean valida) throws NfeException {

        return Cancelar.eventoCancelamentoSubstituicao(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe, envEvento.getEvento().get(0).getInfEvento().getCNPJ()), envEvento, valida);

    }

    /**
     * Metodo para Enviar o EPEC
     *
     * @param envEvento
     * @return
     * @throws NfeException
     */
    public static br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento enviarEpec(ConfiguracoesNfe configuracoesNfe, br.com.swconsultoria.nfe.schema.envEpec.TEnvEvento envEvento, boolean valida) throws NfeException {

        return Epec.eventoEpec(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe, envEvento.getEvento().get(0).getInfEvento().getCNPJ()), envEvento, valida);

    }

    /**
     * Metodo para Envio da Carta De Correção da NFE.
     *
     * @param evento
     * @param valida
     * @return
     * @throws NfeException
     */
    public static br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento cce(ConfiguracoesNfe configuracoesNfe,
                                                                           br.com.swconsultoria.nfe.schema.envcce.TEnvEvento evento, boolean valida) throws NfeException {

        return CartaCorrecao.eventoCCe(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe, evento.getEvento().get(0).getInfEvento().getCNPJ()), evento, valida);

    }

    /**
     * Metodo para Manifestação da NFE.
     * @param configuracoesNfe
     * @param evento
     * @param valida
     * @return
     * @throws NfeException
     */
    public static br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento manifestacao(ConfiguracoesNfe configuracoesNfe,
                                                                                            br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento evento, boolean valida) throws NfeException {
        return ManifestacaoDestinatario.eventoManifestacao(ConfiguracoesUtil.iniciaConfiguracoes(configuracoesNfe, evento.getEvento().get(0).getInfEvento().getCNPJ()), evento, valida);

    }


}
