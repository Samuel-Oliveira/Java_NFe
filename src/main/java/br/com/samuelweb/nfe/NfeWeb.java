/**
 * 
 */
package br.com.samuelweb.nfe;

import br.com.samuelweb.nfe.dom.ConfiguracoesWebNfe;
import br.com.samuelweb.nfe.dom.Enum.TipoManifestacao;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.CertificadoUtil;
import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.inf.portalfiscal.nfe.schema.retConsCad.TRetConsCad;
import br.inf.portalfiscal.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.inf.portalfiscal.nfe.schema_4.inutNFe.TInutNFe;
import br.inf.portalfiscal.nfe.schema_4.inutNFe.TRetInutNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.inf.portalfiscal.nfe.schema_4.retConsStatServ.TRetConsStatServ;

import javax.xml.bind.JAXBException;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com - www.samuelweb.com.br
 */
public class NfeWeb {

	/**
	 * Construtor privado
	 */
	private NfeWeb() {
	}

	/**
	 * Classe Reponsavel Por Consultar a Distribuiçao da NFE na SEFAZ
	 *
	 * @param tipoCliente
	 *            Informar DistribuicaoDFe.CPF ou DistribuicaoDFe.CNPJ
	 * @param cpfCnpj
	 * @param tipoConsulta
	 *            Informar DistribuicaoDFe.NSU ou DistribuicaoDFe.CHAVE
	 * @param nsuChave
	 * @return
	 * @throws NfeException
	 */
	public static RetDistDFeInt distribuicaoDfe(ConfiguracoesWebNfe config, String tipoCliente, String cpfCnpj,
			String tipoConsulta, String nsuChave) throws NfeException {

        return DistribuicaoDFe.consultaNfe(CertificadoUtil.iniciaConfiguracoes(config), tipoCliente, cpfCnpj, tipoConsulta, nsuChave);

	}

	/**
	 * Metodo Responsavel Buscar o Status de Serviço do Servidor da Sefaz No tipo
	 * Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 *
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static TRetConsStatServ statusServico(ConfiguracoesWebNfe config, String tipo) throws NfeException {

        return Status.statusServico(CertificadoUtil.iniciaConfiguracoes(config), tipo);

	}

	/**
	 * Classe Reponsavel Por Consultar o status da NFE na SEFAZ No tipo Informar
	 * ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 *
	 * @param chave
	 * @param tipo
	 * @return TRetConsSitNFe
	 * @throws NfeException
	 */
	public static TRetConsSitNFe consultaXml(ConfiguracoesWebNfe config, String chave, String tipo)
			throws NfeException {

        return ConsultaXml.consultaXml(CertificadoUtil.iniciaConfiguracoes(config), chave, tipo);

	}

	/**
	 * Classe Reponsavel Por Consultar o cadastro do Cnpj/CPF na SEFAZ
	 *
	 * @param tipo
	 *            Usar ConsultaCadastro.CNPJ ou ConsultaCadastro.CPF
	 * @param cnpjCpf
	 * @param estado
	 * @return TRetConsCad
	 * @throws NfeException
	 */
	public static TRetConsCad consultaCadastro(ConfiguracoesWebNfe config, String tipo, String cnpjCpf, Estados estado)
			throws NfeException {

        return ConsultaCadastro.consultaCadastro(CertificadoUtil.iniciaConfiguracoes(config), tipo, cnpjCpf, estado);

	}

	/**
	 * Classe Reponsavel Por Consultar o retorno da NFE na SEFAZ No tipo Informar
	 * ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 *
	 * @param recibo
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static TRetConsReciNFe consultaRecibo(ConfiguracoesWebNfe config, String recibo, String tipo)
			throws NfeException {

        return ConsultaRecibo.reciboNfe(CertificadoUtil.iniciaConfiguracoes(config), recibo, tipo);

	}

	/**
	 * Classe Reponsavel Por Inutilizar a NFE na SEFAZ No tipo Informar
	 * ConstantesUtil.NFE ou ConstantesUtil.NFCE Id = Código da UF + Ano (2
	 * posições) + CNPJ + modelo + série + número inicial e número final precedida
	 * do literal “ID”
	 *
	 * @param id
	 * @param valida
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static TRetInutNFe inutilizacao(ConfiguracoesWebNfe config, String id, String motivo, String tipo,
			boolean validar) throws NfeException {

        return Inutilizar.inutiliza(CertificadoUtil.iniciaConfiguracoes(config), id, motivo, tipo, validar);

	}

	/**
	 * Classe Reponsavel Por criar o Objeto de Inutilização No tipo Informar
	 * ConstantesUtil.NFE ou ConstantesUtil.NFCE Id = Código da UF + Ano (2
	 * posições) + CNPJ + modelo + série + número inicial e número final precedida
	 * do literal “ID”
	 *
	 * @param id
	 * @param valida
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static TInutNFe criaObjetoInutilizacao(ConfiguracoesWebNfe config, String id, String motivo, String tipo)
			throws NfeException, JAXBException {

        TInutNFe inutNFe = Inutilizar.criaObjetoInutiliza(CertificadoUtil.iniciaConfiguracoes(config), id, motivo, tipo);

		String xml = XmlUtil.objectToXml(inutNFe);
		xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");

        return XmlUtil.xmlToObject(Assinar.assinaNfe(CertificadoUtil.iniciaConfiguracoes(config), xml, Assinar.INFINUT), TInutNFe.class);

	}

	/**
	 * Metodo para Montar a NFE.
	 *
	 * @param enviNFe
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	public static TEnviNFe montaNfe(ConfiguracoesWebNfe config, TEnviNFe enviNFe, boolean valida) throws NfeException {

        return Enviar.montaNfe(CertificadoUtil.iniciaConfiguracoes(config), enviNFe, valida);

	}

	/**
	 * Metodo para Enviar a NFE. No tipo Informar ConstantesUtil.NFE ou
	 * ConstantesUtil.NFCE
	 *
	 * @param enviNFe
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static TRetEnviNFe enviarNfe(ConfiguracoesWebNfe config, TEnviNFe enviNFe, String tipo) throws NfeException {

        return Enviar.enviaNfe(CertificadoUtil.iniciaConfiguracoes(config), enviNFe, tipo);

	}

	/**
	 * * Metodo para Cancelar a NFE. No tipo Informar ConstantesUtil.NFE ou
	 * ConstantesUtil.NFCE
	 *
	 * @param envEvento
	 * @return
	 * @throws NfeException
	 */
	public static TRetEnvEvento cancelarNfe(ConfiguracoesWebNfe config, TEnvEvento envEvento, boolean valida,
			String tipo) throws NfeException {

        return Cancelar.eventoCancelamento(CertificadoUtil.iniciaConfiguracoes(config), envEvento, valida, tipo);

	}

	/**
	 * * Metodo para Envio da Carta De Correção da NFE. No tipo Informar
	 * ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 *
	 * @param evento
	 * @param valida
	 * @param tipo
	 * @return
	 * @throws NfeException
	 */
	public static br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento cce(ConfiguracoesWebNfe config,
			br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento evento, boolean valida, String tipo) throws NfeException {

        return CartaCorrecao.eventoCCe(CertificadoUtil.iniciaConfiguracoes(config), evento, valida, tipo);

	}

	/**
	 * Metodo para Manifestação da NFE.
	 *
	 * @param envEvento
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	public static br.inf.portalfiscal.nfe.schema.envConfRecebto.TRetEnvEvento manifestacao(ConfiguracoesWebNfe config,
			String chave, TipoManifestacao manifestacao, String cnpj, String motivo, String data) throws NfeException {

        return ManifestacaoDestinatario.eventoManifestacao(CertificadoUtil.iniciaConfiguracoes(config), chave, manifestacao, cnpj, data, motivo);

	}

    /**
     * * Metodo para Enviar o EPEC.
     * No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
     *
     * @param envEvento
     * @return
     * @throws NfeException
     */
    public static br.inf.portalfiscal.nfe.schema.envEpec.TRetEnvEvento enviarEpec(ConfiguracoesWebNfe config, br.inf.portalfiscal.nfe.schema.envEpec.TEnvEvento envEvento, boolean valida, String tipo) throws NfeException {

        return Epec.eventoEpec(CertificadoUtil.iniciaConfiguracoes(config), envEvento, valida, tipo);

    }

}
