package br.com.swconsultoria.nfe;

import javax.xml.bind.JAXBException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.ConsultaDFeEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.PessoaEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoCancelamento;
import br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoCancelamento;
import br.com.swconsultoria.nfe.schemas.RetDistDFeInt;
import br.com.swconsultoria.nfe.schemas.TEnviNFe;
import br.com.swconsultoria.nfe.schemas.TRetEnviNFe;
import br.com.swconsultoria.nfe.schemas.TInutNFe;
import br.com.swconsultoria.nfe.schemas.TRetInutNFe;
import br.com.swconsultoria.nfe.util.ConfiguracoesUtil;


public class NfeA3 {

    public static String montaXmlA3(ConfiguracoesNfe config, TEnviNFe enviNFe) throws NfeException {
        return EnviarA3.montaXmlA3(ConfiguracoesUtil.iniciaConfiguracoes(config), enviNFe);
    }

    public static TEnviNFe montaNfeA3(ConfiguracoesNfe config, String xmlAssinado, boolean valida) throws NfeException {
        return EnviarA3.montaNfeA3(ConfiguracoesUtil.iniciaConfiguracoes(config), xmlAssinado, valida);
    }
    
    public static TRetEnviNFe enviarNfeA3(ConfiguracoesNfe config, TEnviNFe enviNFe, DocumentoEnum tipoDocumento) throws NfeException {
        return Enviar.enviaNfe(ConfiguracoesUtil.iniciaConfiguracoes(config), enviNFe, tipoDocumento);
    }
    
    public static String montaXmleventoCancelamento(ConfiguracoesNfe config, TEnvEventoCancelamento enviEvento) throws NfeException {
        return CancelarA3.montaXmleventoCancelamento(ConfiguracoesUtil.iniciaConfiguracoes(config), enviEvento);
    }

    public static TRetEnvEventoCancelamento cancelarNfeA3(ConfiguracoesNfe config, String xlmAssinado, boolean valida, DocumentoEnum tipoDocumento) throws NfeException {
        return CancelarA3.eventoCancelamento(ConfiguracoesUtil.iniciaConfiguracoes(config), valida, tipoDocumento, xlmAssinado);
    }

    public static String montaXmleventoCancelamentoSubstituicao(ConfiguracoesNfe config, br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoCancelamentoSubstituicao enviEvento) throws NfeException {
        return CancelarA3.montaXmleventoCancelamentoSubstituicao(ConfiguracoesUtil.iniciaConfiguracoes(config), enviEvento);
    }

    public static  br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoCancelamentoSubstituicao cancelarSubstituicaoNfeA3(ConfiguracoesNfe config, String xlmAssinado, boolean valida, DocumentoEnum tipoDocumento) throws NfeException {
        return CancelarA3.eventoCancelamentoSubstituicao(ConfiguracoesUtil.iniciaConfiguracoes(config), valida, tipoDocumento, xlmAssinado);
    }

    public static TRetInutNFe inutilizacaoA3(ConfiguracoesNfe config, DocumentoEnum tipoDocumento, boolean validar, String xmlAssinado) throws NfeException {
        return InutilizarA3.inutiliza(ConfiguracoesUtil.iniciaConfiguracoes(config), tipoDocumento, validar, xmlAssinado);
    }

    public static String montaXmlInutilizaco(ConfiguracoesNfe config, TInutNFe inutNFe) throws  NfeException {
        return InutilizarA3.montaXmlInutlizacao(ConfiguracoesUtil.iniciaConfiguracoes(config), inutNFe);
    }

    public static br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoCartaCorrecao cceA3(ConfiguracoesNfe config, boolean valida, String xmlAssinado) throws NfeException {
        return CartaCorrecaoA3.eventoCCe(ConfiguracoesUtil.iniciaConfiguracoes(config), valida, xmlAssinado);

    }

    public static String montaXmlCce(ConfiguracoesNfe config, br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoCartaCorrecao evento) throws NfeException {
        return CartaCorrecaoA3.montaXmlCartaCorrecao(ConfiguracoesUtil.iniciaConfiguracoes(config), evento);
    }

    public static RetDistDFeInt distribuicaoDfeA3(ConfiguracoesNfe config,  String xmlAssinado) throws NfeException {
        return DistribuicaoDFeA3.consultaNfe(ConfiguracoesUtil.iniciaConfiguracoes(config), xmlAssinado);
    }

    public static String distribuicaoDfeMontaXmlA3(ConfiguracoesNfe config, PessoaEnum tipoPessoa, String cpfCnpj,
            ConsultaDFeEnum tipoConsulta, String nsuChave) throws NfeException, JAXBException {
        return DistribuicaoDFeA3.montarXML(ConfiguracoesUtil.iniciaConfiguracoes(config), tipoPessoa, cpfCnpj, tipoConsulta, nsuChave);
    }

    public static br.com.swconsultoria.nfe.schemas_eventos.TRetEnvEventoManifestacao manifestacaoA3(ConfiguracoesNfe config, boolean valida, String xmlAssinado) throws NfeException {
        return ManifestacaoDestinatarioA3.eventoManifestacao(ConfiguracoesUtil.iniciaConfiguracoes(config), valida, xmlAssinado);
    }

    public static String manifestacaoMontaXmlA3(ConfiguracoesNfe config,
            br.com.swconsultoria.nfe.schemas_eventos.TEnvEventoManifestacao evento) throws NfeException {
        return ManifestacaoDestinatarioA3.montarXML(ConfiguracoesUtil.iniciaConfiguracoes(config), evento);
    }

}
