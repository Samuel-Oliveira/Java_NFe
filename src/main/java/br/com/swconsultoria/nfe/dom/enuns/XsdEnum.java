package br.com.swconsultoria.nfe.dom.enuns;

import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.consCad.TConsCad;
import br.com.swconsultoria.nfe.schema.distdfeint.DistDFeInt;
import br.com.swconsultoria.nfe.schema.retConsCad.TRetConsCad;
import br.com.swconsultoria.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.com.swconsultoria.nfe.schema_4.consReciNFe.TConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.consSitNFe.TConsSitNFe;
import br.com.swconsultoria.nfe.schema_4.consStatServ.TConsStatServ;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TInutNFe;
import br.com.swconsultoria.nfe.schema_4.inutNFe.TProcInutNFe;
import br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.schema_4.retEnviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.retInutNFe.TRetInutNFe;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum XsdEnum {

    //Consulta Cadastro
    CONS_CAD(TConsCad.class, "ConsCad"),
    RET_CONS_CAD(TRetConsCad.class, "retConsCad"),

    //Consulta Status Serviço
    CONS_STAT_SERV(TConsStatServ.class, "consStatServ"),
    RET_STAT_SERV(TRetConsStatServ.class, "retConsStatServ"),

    //Consulta Recibo
    CONS_RECI_NFE(TConsReciNFe.class, "consReciNFe"),
    RET_CONS_RECI_NFE(TRetConsReciNFe.class, "retConsReciNFe"),

    //Consulta Situacao
    CONS_SIT_NFE(TConsSitNFe.class, "consSitNFe"),
    RET_CONS_SIT_NFE(TRetConsSitNFe.class, "retConsSitNFe"),

    //NFe
    NFE(TNFe.class, "NFe"),
    ENVI_NFE(TEnviNFe.class, "enviNFe"),
    RET_ENVI_NFE(TRetEnviNFe.class, "retEnviNFe"),
    NFE_PROC(TNfeProc.class, "nfeProc"),

    //Inutilização
    INUT_NFE(TInutNFe.class, "inutNFe"),
    PROC_INUT_NFE(TProcInutNFe.class, "procInutNFe"),
    RET_INUT_NFE(TRetInutNFe.class, "retInutNFe"),

    //Prot
    ENVI_PROT_NFE(br.com.swconsultoria.nfe.schema_4.enviNFe.TProtNFe.class, "protNFe"),
    CONS_PROT_NFE(br.com.swconsultoria.nfe.schema_4.consSitNFe.TProtNFe.class, "protNFe"),
    CONS_RECI_PROT_NFE(br.com.swconsultoria.nfe.schema_4.consReciNFe.TProtNFe.class, "protNFe"),

    //Cancelamento
    CANC_PROC_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancNFe.TProcEvento.class, "procEventoNFe"),
    CANC_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento.class, "procEventoNFe"),
    CANC_RET_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento.class, "procEventoNFe"),

    //Cancelamento Substituicao
    CANC_SUBS_PROC_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancSubst.TProcEvento.class, "procEvento"),
    CANC_SUBS_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancSubst.TEnvEvento.class, "envEvento"),
    CANC_SUBS_RET_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancSubst.TRetEnvEvento.class, "retEnvEvento"),

    //Ator Interessado
    ATOR_INTER_PROC_EVENTO(br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TProcEvento.class, "procEvento"),
    ATOR_INTER_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TEnvEvento.class, "envEvento"),
    ATOR_INTER_RET_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoAtorInteressado.TRetEnvEvento.class, "retEnvEvento"),

    //Carta Correcao
    CCE_PROC_EVENTO(br.com.swconsultoria.nfe.schema.envcce.TProcEvento.class, "procEvento"),
    CCE_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envcce.TEnvEvento.class, "envEvento"),
    CCE_RET_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envcce.TRetEnvEvento.class, "retEnvEvento"),

    //EPEC
    EPEC_PROC_EVENTO(br.com.swconsultoria.nfe.schema.envEpec.TProcEvento.class, "procEvento"),
    EPEC_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEpec.TEnvEvento.class, "envEvento"),
    EPEC_RET_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEpec.TRetEnvEvento.class, "retEnvEvento"),

    //MANIFESTACAO
    MAN_PROC_EVENTO(br.com.swconsultoria.nfe.schema.envConfRecebto.TProcEvento.class, "procEvento"),
    MAN_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envConfRecebto.TEnvEvento.class, "envEvento"),
    MAN_RET_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envConfRecebto.TRetEnvEvento.class, "retEnvEvento"),

    //INSUCESSO
    INS_PROC_EVENTO(br.com.swconsultoria.nfe.schema.envEventoInsucessoNFe.TProcEvento.class, "procEvento"),
    INS_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoInsucessoNFe.TEnvEvento.class, "envEvento"),
    INS_RET_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoInsucessoNFe.TRetEnvEvento.class, "retEnvEvento"),

    //CANC INSUCESSO
    _PROC_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancInsucessoNFe.TProcEvento.class, "procEvento"),
    _ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancInsucessoNFe.TEnvEvento.class, "envEvento"),
    _RET_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancInsucessoNFe.TRetEnvEvento.class, "retEnvEvento"),

    //ECONF
    ECONF_PROC_EVENTO(br.com.swconsultoria.nfe.schema.envEventoEConf.TProcEvento.class, "procEvento"),
    ECONF_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoEConf.TEnvEvento.class, "envEvento"),
    ECONF_RET_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoEConf.TRetEnvEvento.class, "retEnvEvento"),

    //CANC ECONF
    CANC_ECONF_PROC_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancEConf.TProcEvento.class, "procEvento"),
    CANC_ECONF_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancEConf.TEnvEvento.class, "envEvento"),
    CANC_ECONF_RET_ENV_EVENTO(br.com.swconsultoria.nfe.schema.envEventoCancEConf.TRetEnvEvento.class, "retEnvEvento"),

    //DistDfe
    DIST_DFE_INT(DistDFeInt.class, "distDFeInt"),
    RET_DIST_DFE_INT(RetDistDFeInt.class, "retDistDFeInt");

    private final Class<?> clazz;
    private final String name;

    public static XsdEnum getByClassName(String simpleClassName) throws NfeException {
        for (XsdEnum e : values()) {
            if (e.clazz.getName().equals(simpleClassName)) return e;
        }
        throw new NfeException("Xsd Não mapeado: " + simpleClassName);
    }
}