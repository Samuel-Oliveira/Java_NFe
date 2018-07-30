package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.XmlUtil;
import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.annotation.NfeObjetoList;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.consts.NfeConsts;
import br.com.samuelweb.nfe.util.enumeration.EnumNfeValue;
import br.com.samuelweb.nfe.util.model.InfNFe;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class NfeValidator {

    private List<ErrosValidacao> errosList;

    public NfeValidator() {
        errosList = new ArrayList<>();
    }

    public List<ErrosValidacao> getErrosList() {
        return errosList;
    }

    public Boolean validarInfNfe(InfNFe infNFe) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        errosList = new ArrayList<>();
        infNFe.validarRegraNegocio();
        return validarObjetoCompleto(infNFe, NfeConsts.DSC_INFNFE);
    }

    private Boolean validarObjetoCompleto(Object obj, String descricaoGrupo)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (obj == null) {
            return TRUE;
        }
        Boolean result = TRUE;
        Class<?> persistentClass = obj.getClass();
        do {
            for (Field field : persistentClass.getDeclaredFields()) {
                result = result
                        & validarCampo(obj, field, descricaoGrupo)
                        & validarObjeto(obj, field, descricaoGrupo)
                        & validarObjetoList(obj, field, descricaoGrupo);
            }
            persistentClass = persistentClass.getSuperclass();
        } while (!persistentClass.getName().equals(Object.class.getName()));
        return result;
    }

    private Boolean validarObjetoList(Object obj, Field field, String descricaoGrupo) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (field.isAnnotationPresent(NfeObjetoList.class)) {
            NfeObjetoList nfeObjetoList = field.getAnnotation(NfeObjetoList.class);
            List<Object> objListRet = (List<Object>) executarMetodoGet(obj, field);
            Boolean result = TRUE;
            if (nfeObjetoList.ocorrenciaMinima() > 0 && (objListRet == null || objListRet.isEmpty())) {
                errosList.add(
                        new ErrosValidacao(nfeObjetoList.id()
                                , nfeObjetoList.tag()
                                , nfeObjetoList.descricao()
                                , DfeConsts.ERR_MSG_MENOR
                                , descricaoGrupo));
            }
            if (nfeObjetoList.ocorrenciaMaxima() > 0 && objListRet != null && objListRet.size() > nfeObjetoList.ocorrenciaMaxima()) {
                errosList.add(
                        new ErrosValidacao(nfeObjetoList.id()
                                , nfeObjetoList.tag()
                                , nfeObjetoList.descricao()
                                , DfeConsts.ERR_MSG_MAIOR
                                , descricaoGrupo));
            }
            if (objListRet != null){
                for (Object objRet : objListRet) {
                    result = result & validarObjetoCompleto(objRet, nfeObjetoList.descricao());
                }
            }
            return result;
        }
        return TRUE;
    }

    private Boolean validarObjeto(Object obj, Field field, String descricaoGrupo) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (field.isAnnotationPresent(NfeObjeto.class)) {
            NfeObjeto nfeObjeto = field.getAnnotation(NfeObjeto.class);
            Object objRet = executarMetodoGet(obj, field);
            Boolean result = TRUE;
            if (nfeObjeto.ocorrencias() == 1 && objRet == null) {
                errosList.add(
                        new ErrosValidacao(nfeObjeto.id()
                                , nfeObjeto.tag()
                                , nfeObjeto.descricao()
                                , DfeConsts.ERR_MSG_VAZIO
                                , descricaoGrupo));
                result = FALSE;
            }
            return result & validarObjetoCompleto(objRet, nfeObjeto.descricao());
        }
        return TRUE;
    }

    private Boolean validarCampo(Object obj, Field field, String descricaoGrupo) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Boolean result = TRUE;
        if (field.isAnnotationPresent(NfeCampo.class)) {
            NfeCampo nfeCampo = field.getAnnotation(NfeCampo.class);
            Object objRet = executarMetodoGet(obj, field);

            if (nfeCampo.tipo().equals(String.class)) {
                result = validaCampoString(nfeCampo, (String) objRet, obj, field, descricaoGrupo);
            } else if (nfeCampo.tipo().equals(Integer.class)) {
                result = validaCampoInteger(nfeCampo, (Integer) objRet, obj, field, descricaoGrupo);
            } else if (nfeCampo.tipo().equals(ZonedDateTime.class)) {
                result = validaCampoZonedDateTime(nfeCampo, (ZonedDateTime) objRet, descricaoGrupo);
            } else if (nfeCampo.tipo().equals(BigDecimal.class)) {
                result = validaCampoBigDecimalValue(nfeCampo, (BigDecimal) objRet, obj, field, descricaoGrupo);
            } else if (EnumNfeValue.class.isAssignableFrom(nfeCampo.tipo())) {
                result = validaCampoEnumNfeValue(nfeCampo, (EnumNfeValue) objRet, descricaoGrupo);
            }

            result = executeValidadores(nfeCampo, obj, objRet, descricaoGrupo) && result;
        }
        return result;
    }

    private Method getMethod(Class<?> persistentClass, String prefixo, String fieldName, Class<?>... parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String methodName;
        try {
            methodName = prefixo+ WordUtils.capitalize(fieldName);
            return persistentClass.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            methodName = prefixo+ fieldName;
            return persistentClass.getDeclaredMethod(methodName, parameterTypes);
        }
    }

    private Object executarMetodoGet(Object obj, Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> persistentClass = obj.getClass();
        Method method = null;
        do {
            try {
                method = this.getMethod(persistentClass, "get", field.getName(), null);
            } catch (NoSuchMethodException e) {

            }
            persistentClass = persistentClass.getSuperclass();
        } while (method == null && !persistentClass.getName().equals(Object.class.getName()));
        return method.invoke(obj, null);
    }

    private Object executarMetodoSet(Object obj, Field field, Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> persistentClass = obj.getClass();
        Method method = null;
        do {
            try{
                if (value != null) {
                    method = this.getMethod(persistentClass, "set", field.getName(), value.getClass());
                } else {
                    method = this.getMethod(persistentClass, "set", field.getName(), null);
                }
            } catch (NoSuchMethodException e) {

            }
            persistentClass = persistentClass.getSuperclass();
        } while (method == null && !persistentClass.getName().equals(Object.class.getName()));
        return method.invoke(obj, value);
    }

    private Boolean validaCampoBigDecimalValue(NfeCampo nfeCampo, BigDecimal value, Object obj, Field field, String descricaoGrupo)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Boolean result = TRUE;
        if (!nfeCampo.valorDefault().isEmpty() && value == null) {
            value = new BigDecimal(nfeCampo.valorDefault());
        }
        if (value != null) {
            value = value.setScale(nfeCampo.decimais(), RoundingMode.HALF_UP);
            try {
                executarMetodoSet(obj, field, value);
            } catch (NullPointerException e) {
                field.setAccessible(TRUE);
                field.set(obj, value);
            }
        }
        String valueStr = (value == null? BigDecimal.ZERO.toString(): value.toString());
        if (value == null && nfeCampo.ocorrencias() >= 1) {
            result = FALSE;
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_VAZIO
                            , descricaoGrupo));
        }
        //valida tamanho campo Mínimo
        if (valueStr.length() > 1 && nfeCampo.tamanhoMinimo() > 0 && valueStr.length() < nfeCampo.tamanhoMinimo()) {
            result = FALSE;
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_MENOR
                            , descricaoGrupo));
        }
        //valida tamanho campo máximo
        if (valueStr.length() > 0 && nfeCampo.tamanhoMaximo() > 0 && valueStr.length() > nfeCampo.tamanhoMaximo())  {
            result = FALSE;
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_MAIOR
                            , descricaoGrupo));
        }
        //Se o valor do campo é zero e o mesmo não obrigatório envia null para não gerar,
        //ocorria no campo vDesc do produto o problema.
        if (value != null && value.compareTo(BigDecimal.ZERO) == 0 && nfeCampo.ocorrencias() == 0){
            try {
                executarMetodoSet(obj, field, null);
            } catch (NullPointerException e) {
                field.setAccessible(TRUE);
                field.set(obj, null);
            }
        }
        return result;
    }

    private Boolean executeValidadores(NfeCampo nfeCampo, Object obj, Object objRet, String descricaoGrupo) throws IllegalAccessException {
        try {
            for (Class<?> validador : nfeCampo.validadores()) {
                ValidadorCampo<Object, Object> val = (ValidadorCampo<Object, Object>) validador.newInstance();
                RetornoValidar retorno = val.validar(objRet, obj);
                if (!retorno.getValido()) {
                    errosList.add(
                            new ErrosValidacao(nfeCampo.id()
                                    , nfeCampo.tag()
                                    , nfeCampo.descricao()
                                    , retorno.getMensagem()
                                    , descricaoGrupo));
                    return FALSE;
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return TRUE;
    }

    private Boolean validaCampoEnumNfeValue(NfeCampo nfeCampo, EnumNfeValue value, String descricaoGrupo) {
        if (value == null && nfeCampo.ocorrencias() >= 1) {
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_VAZIO
                            , descricaoGrupo));
            return FALSE;
        }
        return TRUE;
    }

    private Boolean validaCampoZonedDateTime(NfeCampo nfeCampo, ZonedDateTime value, String descricaoGrupo) {
        Boolean result = TRUE;
        if (value == null && nfeCampo.ocorrencias() >= 1) {
            result = FALSE;
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_VAZIO
                            , descricaoGrupo));
        }
        return result;
    }

    private Boolean validaCampoInteger(NfeCampo nfeCampo, Integer value, Object obj, Field field, String descricaoGrupo)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Boolean result = TRUE;
        if (!nfeCampo.valorDefault().isEmpty() && value == null) {
            value = Integer.parseInt(nfeCampo.valorDefault());
            try {
                executarMetodoSet(obj, field, value);
            } catch (NullPointerException e) {
                field.setAccessible(TRUE);
                field.set(obj, value);
            }
        }
        String valueStr = String.valueOf(value == null? 0: value);
        if (value == null && nfeCampo.ocorrencias() >= 1) {
            result = FALSE;
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_VAZIO
                            , descricaoGrupo));
        }
        //valida tamanho campo Mínimo
        if (valueStr.length() > 1 && nfeCampo.tamanhoMinimo() > 0 && valueStr.length() < nfeCampo.tamanhoMinimo()) {
            result = FALSE;
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_MENOR
                            , descricaoGrupo));
        }
        //valida tamanho campo máximo
        if (valueStr.length() > 0 && nfeCampo.tamanhoMaximo() > 0 && valueStr.length() > nfeCampo.tamanhoMaximo())  {
            result = FALSE;
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_MAIOR
                            , descricaoGrupo));
        }
        return result;
    }

    private Boolean validaCampoString(NfeCampo nfeCampo, String value, Object obj, Field field, String descricaoGrupo)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Boolean result = TRUE;
        if (value == null && nfeCampo.ocorrencias() == 0) {
            return result;
        }
        if (value == null) {
            value = "";
        }
        if (!nfeCampo.valorDefault().isEmpty() && value.isEmpty()) {
            value = nfeCampo.valorDefault();
            try {
                executarMetodoSet(obj, field, value);
            } catch (NullPointerException e) {
                field.setAccessible(TRUE);
                field.set(obj, value);
            }
        }
        //(Existem tags obrigatórias que podem ser nulas ex. cEAN)  if (ocorrencias = 1) and (EstaVazio) then
        if (nfeCampo.ocorrencias() == 1 && value.length() ==0 && nfeCampo.tamanhoMinimo() > 0) {
            result = FALSE;
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_VAZIO
                            , descricaoGrupo));
        }
        //valida tamanho campo Mínimo
        if (value.length() > 0 && nfeCampo.tamanhoMinimo() > 0 && value.length() < nfeCampo.tamanhoMinimo()) {
            result = FALSE;
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_MENOR
                            , descricaoGrupo));
        }
        //valida tamanho campo máximo
        if (value.length() > 0 && nfeCampo.tamanhoMaximo() > 0 && value.length() > nfeCampo.tamanhoMaximo())  {
            result = FALSE;
            errosList.add(
                    new ErrosValidacao(nfeCampo.id()
                            , nfeCampo.tag()
                            , nfeCampo.descricao()
                            , DfeConsts.ERR_MSG_MAIOR
                            , descricaoGrupo));
        }

        value = XmlUtil.removeAcentos(value);
        value = StringEscapeUtils.escapeHtml4(value);
        if (value.length() > 0 && nfeCampo.tamanhoMaximo() > 0 && value.length() > nfeCampo.tamanhoMaximo())  {
            value = value.substring(1, nfeCampo.tamanhoMaximo());
        }
        try {
            executarMetodoSet(obj, field, value);
        } catch (NullPointerException e) {
            field.setAccessible(TRUE);
            field.set(obj, value);
        }
        return result;
    }
}
