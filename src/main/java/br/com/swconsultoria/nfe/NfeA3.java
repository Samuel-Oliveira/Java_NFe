package br.com.swconsultoria.nfe;

import javax.xml.bind.JAXBException;

import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TRetInutNFe;
import br.com.swconsultoria.nfe.util.ConfiguracoesUtil;


public class NfeA3 {
	/**
	 * Monta e retorna o XML da NFe sem assinar
	 * 
	 * @author Hugo
	 * @since 25/06/2018	-- NFE 4.0
	 * 
	 * @param enviNFe
	 * @return
	 * @throws NfeException
	 */
	public static String montaXmlA3(TEnviNFe enviNFe) throws NfeException {
        return EnviarA3.montaXmlA3(enviNFe);
    }

    /**
     * Monta a NFe recebendo como parametro o XML assinado
     * 
	 * @author Hugo
	 * @since 25/06/2018	-- NFE 4.0
	 * 
     * @param xmlAssinado
     * @param valida
     * @return
     * @throws NfeException
     */
    public static TEnviNFe montaNfeA3(ConfiguracoesNfe config, String xmlAssinado, boolean valida) throws NfeException {
        return EnviarA3.montaNfeA3(ConfiguracoesUtil.iniciaConfiguracoes(config), xmlAssinado, valida);
    }
    
    /**
     * Metodo para Enviar a NFE assinado com o certificado A3
     * 
     * No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
     * 
	 * @author Hugo
	 * @since 25/06/2018	-- NFE 4.0
     *
     * @param enviNFe
     * @param tipo
     * @return
     * @throws NfeException
     */
    public static TRetEnviNFe enviarNfeA3(ConfiguracoesNfe config, TEnviNFe enviNFe, DocumentoEnum tipoDocumento) throws NfeException {
        return Enviar.enviaNfe(ConfiguracoesUtil.iniciaConfiguracoes(config), enviNFe, tipoDocumento);
    }
    
	/**
	 * Metodo retorna o XML de cancelamento sem assinatura
	 * 
	 * @author Hugo
	 * @since 26/06/2018	-- NFE 4.0
	 * 
	 * @param envEvento
	 * @return
	 * @throws NfeException
	 */
    public static String montaXmleventoCancelamento(TEnvEvento enviEvento) throws NfeException {
		return CancelarA3.montaXmleventoCancelamento(enviEvento);
	}

	/**
	 * Metodo para Cancelar a NFE. com certificado A3 No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @author Hugo
	 * @since 26/06/2018	-- NFE 4.0
	 * 
	 * @param envEvento
	 * @return
	 * @throws NfeException
	 */
	public static TRetEnvEvento cancelarNfeA3(ConfiguracoesNfe config, String xlmAssinado, boolean valida, DocumentoEnum tipoDocumento) throws NfeException {
		return CancelarA3.eventoCancelamento(ConfiguracoesUtil.iniciaConfiguracoes(config), valida, tipoDocumento, xlmAssinado);
	}
	
	
	/**
	 * Metodo para Inutlizar a NFE. com certificado A3 No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @author Hugo
	 * @since 04/08/2018	-- NFE 4.0
	 * 
	 * @param config
	 * @param tipo
	 * @param validar
	 * @param xmlAssinado
	 * @return
	 * @throws NfeException
	 */
	public static TRetInutNFe inutilizacaoA3(ConfiguracoesNfe config, DocumentoEnum tipoDocumento, boolean validar, String xmlAssinado) throws NfeException {
		return InutilizarA3.inutiliza(ConfiguracoesUtil.iniciaConfiguracoes(config), tipoDocumento, validar, xmlAssinado);
	}
	
	/**
	 * Metodo retorna o XML de inutlizacao sem assinatura
	 * 
	 * @author Hugo
	 * @since 04/08/2018	-- NFE 4.0
	 * 
	 * @param config
	 * @param id
	 * @param motivo
	 * @param tipo
	 * @param validar
	 * @return
	 * @throws NfeException
	 */
	public static String montaXmlInutilizaco(ConfiguracoesNfe config, TInutNFe inutNFe, DocumentoEnum tipoDocumento) throws  NfeException {
		return InutilizarA3.montaXmlInutlizacao(ConfiguracoesUtil.iniciaConfiguracoes(config), inutNFe, tipoDocumento);
	}
	

	/**
	 * Metodo para Enviar carta de correção para NFE. com certificado A3 No tipo Informar ConstantesUtil.NFE ou ConstantesUtil.NFCE
	 * 
	 * @author Hugo
	 * @since 04/08/2018	-- NFE 4.0
	 * 
	 * @param config
	 * @param valida
	 * @param tipo
	 * @param xmlAssinado
	 * @return
	 * @throws NfeException
	 */
	public static br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento cceA3(ConfiguracoesNfe config, boolean valida, String xmlAssinado) throws NfeException {
        return CartaCorrecaoA3.eventoCCe(ConfiguracoesUtil.iniciaConfiguracoes(config), valida, xmlAssinado);

	}

	/**
	 * Metodo retorna o XML de Carta de Correção sem assinatura
	 * 
	 * @author Hugo
	 * @since 04/08/2018	-- NFE 4.0
	 * 
	 * 
	 * @param evento
	 * @return
	 * @throws NfeException
	 */
	public static String montaXmlCce(br.com.swconsultoria.nfe.schema.envcce.TEnvEvento evento) throws NfeException {
        return CartaCorrecaoA3.montaXmlCartaCorrecao(evento);

	}

	/**
	 * Reponsavel Por Consultar as NFE na SEFAZ com A3
	 * 
	 * @author Hugo
	 * @since 04/09/2018	-- NFE 4.0
	 * 
	 * @param xmlAssinado
	 * @return
	 * @throws NfeException
	 */
	public static RetDistDFeInt distribuicaoDfeA3(ConfiguracoesNfe config,  String xmlAssinado) throws NfeException {
        return DistribuicaoDFeA3.consultaNfe(ConfiguracoesUtil.iniciaConfiguracoes(config), xmlAssinado);
	}
	
	/**
	 * Retorna o Xml para Consultar as NFE na SEFAZ com A3
	 * 
	 * @author Hugo
	 * @since 04/09/2018	-- NFE 4.0
	 * 
	 * @param config
	 * @param tipoCliente
	 * @param cpfCnpj
	 * @param tipoConsulta
	 * @param nsuChave
	 * @return
	 * @throws NfeException
	 * @throws JAXBException 
	 */
	public static String distribuicaoDfeMontaXmlA3(ConfiguracoesNfe config, PessoaEnum tipoPessoa, String cpfCnpj,
			ConsultaDFeEnum tipoConsulta, String nsuChave) throws NfeException, JAXBException {
        return DistribuicaoDFeA3.montarXML(ConfiguracoesUtil.iniciaConfiguracoes(config), tipoPessoa, cpfCnpj, tipoConsulta, nsuChave);
	}
	
	/**
	 * Metodo para Manifestação da NFE na SEFAZ com A3.
	 * 
	 * @author Hugo
	 * @since 04/09/2018	-- NFE 4.0	 
	 * 
	 * @param envEvento
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	public static br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento manifestacaoA3(ConfiguracoesNfe config, boolean valida, String xmlAssinado) throws NfeException {
        return ManifestacaoDestinatarioA3.eventoManifestacao(ConfiguracoesUtil.iniciaConfiguracoes(config), valida, xmlAssinado);
	}
	
	/**
	 * Retorna o Xml para Manifestação da NFE na SEFAZ com A3.
	 * 
	 * @author Hugo
	 * @since 04/09/2018	-- NFE 4.0	 
	 *
	 * @param envEvento
	 * @param valida
	 * @return
	 * @throws NfeException
	 */
	public static String manifestacaoMontaXmlA3(ConfiguracoesNfe config,
            br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento evento) throws NfeException {
        return ManifestacaoDestinatarioA3.montarXML(ConfiguracoesUtil.iniciaConfiguracoes(config), evento);
	}
	
}
