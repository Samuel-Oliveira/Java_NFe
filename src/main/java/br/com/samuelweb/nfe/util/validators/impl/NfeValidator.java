package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.annotation.NfeObjeto;
import br.com.samuelweb.nfe.util.annotation.NfeObjetoList;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.enumeration.EnumNfeValue;
import br.com.samuelweb.nfe.util.model.InfNFe;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;
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
        return validarObjetoCompleto(infNFe);
    }

    private Boolean validarObjetoCompleto(Object obj)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (obj == null) {
            return TRUE;
        }
        Boolean result = TRUE;
        Class<?> persistentClass = obj.getClass();
        for (Field field : persistentClass.getDeclaredFields()) {
            result = result
                    & validarCampo(obj, field)
                    & validarObjeto(obj, field)
                    & validarObjetoList(obj, field);
        }
        return result;
    }

    private Boolean validarObjetoList(Object obj, Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (field.isAnnotationPresent(NfeObjetoList.class)) {
            Class<?> persistentClass = obj.getClass();
            NfeObjetoList nfeObjetoList = field.getAnnotation(NfeObjetoList.class);
            List<Object> objListRet = (List<Object>) executarMetodoGet(obj, field);
            Boolean result = TRUE;
            if (nfeObjetoList.ocorrenciaMinima() > 0 && (objListRet == null || objListRet.isEmpty())) {
                errosList.add(new ErrosValidacao(nfeObjetoList.id(), nfeObjetoList.tag(), nfeObjetoList.descricao(), DfeConsts.ERR_MSG_MENOR));
            }
            if (nfeObjetoList.ocorrenciaMaxima() > 0 && objListRet != null && objListRet.size() > nfeObjetoList.ocorrenciaMaxima()) {
                errosList.add(new ErrosValidacao(nfeObjetoList.id(), nfeObjetoList.tag(), nfeObjetoList.descricao(), DfeConsts.ERR_MSG_MAIOR));
            }
            if (objListRet != null){
                for (Object objRet : objListRet) {
                    result = result & validarObjetoCompleto(objRet);
                }
            }
            return result;
        }
        return TRUE;
    }

    private Boolean validarObjeto(Object obj, Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (field.isAnnotationPresent(NfeObjeto.class)) {
            Class<?> persistentClass = obj.getClass();
            NfeObjeto nfeObjeto = field.getAnnotation(NfeObjeto.class);
            Object objRet = executarMetodoGet(obj, field);
            Boolean result = TRUE;
            if (nfeObjeto.ocorrencias() == 1 && objRet == null) {
                errosList.add(new ErrosValidacao(nfeObjeto.id(), nfeObjeto.tag(), nfeObjeto.descricao(), DfeConsts.ERR_MSG_VAZIO));
                result = FALSE;
            }
            return result & validarObjetoCompleto(objRet);
        }
        return TRUE;
    }

    private Boolean validarCampo(Object obj, Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Boolean result = TRUE;
        if (field.isAnnotationPresent(NfeCampo.class)) {
            Class<?> persistentClass = obj.getClass();
            NfeCampo nfeCampo = field.getAnnotation(NfeCampo.class);
            Object objRet = executarMetodoGet(obj, field);

            if (nfeCampo.tipo().equals(String.class)) {
                result = validaCampoString(nfeCampo, (String) objRet, obj, field);
            } else if (nfeCampo.tipo().equals(Integer.class)) {
                result = validaCampoInteger(nfeCampo, (Integer) objRet, obj, field);
            } else if (nfeCampo.tipo().equals(ZonedDateTime.class)) {
                result = validaCampoZonedDateTime(nfeCampo, (ZonedDateTime) objRet);
            } else if (nfeCampo.tipo().equals(BigDecimal.class)) {
                result = validaCampoBigDecimalValue(nfeCampo, (BigDecimal) objRet, obj, field);
            } else if (EnumNfeValue.class.isAssignableFrom(nfeCampo.tipo())) {
                result = validaCampoEnumNfeValue(nfeCampo, (EnumNfeValue) objRet);
            }

            result = executeValidadores(nfeCampo, objRet) && result;
        }
        return result;
    }

    private Object executarMetodoGet(Object obj, Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> persistentClass = obj.getClass();
        Method method;
        try {
            method = persistentClass.getDeclaredMethod("get"+ WordUtils.capitalize(field.getName()), null);
        } catch (NoSuchMethodException e) {
            method = persistentClass.getDeclaredMethod("get"+ field.getName(), null);
        }
        return method.invoke(obj, null);
    }

    private Object executarMetodoSet(Object obj, Field field, Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> persistentClass = obj.getClass();
        Method method;
        try {
            method = persistentClass.getDeclaredMethod("set"+ WordUtils.capitalize(field.getName()), value.getClass());
        } catch (NoSuchMethodException e) {
            method = persistentClass.getDeclaredMethod("set"+ field.getName(), value.getClass());
        }
        return method.invoke(obj, value);
    }

    private Boolean validaCampoBigDecimalValue(NfeCampo nfeCampo, BigDecimal value, Object obj, Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Boolean result = TRUE;
        if (!nfeCampo.valorDefault().isEmpty() && value == null) {
            value = new BigDecimal(nfeCampo.valorDefault());
        }
        if (value != null) {
            value = value.setScale(nfeCampo.decimais(), RoundingMode.HALF_UP);
            executarMetodoSet(obj, field, value);
        }
        String valueStr = (value == null? BigDecimal.ZERO.toString(): value.toString());
        if (value == null && nfeCampo.ocorrencias() >= 1) {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_VAZIO));
        }
        //valida tamanho campo Mínimo
        if (valueStr.length() > 1 && nfeCampo.tamanhoMinimo() > 0 && valueStr.length() < nfeCampo.tamanhoMinimo()) {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_MENOR));
        }
        //valida tamanho campo máximo
        if (valueStr.length() > 0 && nfeCampo.tamanhoMaximo() > 0 && valueStr.length() > nfeCampo.tamanhoMaximo())  {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_MAIOR));
        }
        return result;
    }

    private Boolean executeValidadores(NfeCampo nfeCampo, Object objRet) throws IllegalAccessException {
        try {
            for (Class<?> validador : nfeCampo.validadores()) {
                ValidadorCampo<Object> val = (ValidadorCampo<Object>) validador.newInstance();
                RetornoValidar retorno = val.validar(objRet);
                if (!retorno.getValido()) {
                    errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), retorno.getMensagem()));
                    return FALSE;
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return TRUE;
    }

    private Boolean validaCampoEnumNfeValue(NfeCampo nfeCampo, EnumNfeValue value) {
        if (value == null && nfeCampo.ocorrencias() >= 1) {
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_VAZIO));
            return FALSE;
        }
        return TRUE;
    }

    private Boolean validaCampoZonedDateTime(NfeCampo nfeCampo, ZonedDateTime value) {
        Boolean result = TRUE;
        if (value == null && nfeCampo.ocorrencias() >= 1) {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_VAZIO));
        }
        return result;
    }

    private Boolean validaCampoInteger(NfeCampo nfeCampo, Integer value, Object obj, Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Boolean result = TRUE;
        if (!nfeCampo.valorDefault().isEmpty() && value == null) {
            value = Integer.parseInt(nfeCampo.valorDefault());
            executarMetodoSet(obj, field, value);
        }
        String valueStr = String.valueOf(value == null? 0: value);
        if (value == null && nfeCampo.ocorrencias() >= 1) {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_VAZIO));
        }
        //valida tamanho campo Mínimo
        if (valueStr.length() > 1 && nfeCampo.tamanhoMinimo() > 0 && valueStr.length() < nfeCampo.tamanhoMinimo()) {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_MENOR));
        }
        //valida tamanho campo máximo
        if (valueStr.length() > 0 && nfeCampo.tamanhoMaximo() > 0 && valueStr.length() > nfeCampo.tamanhoMaximo())  {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_MAIOR));
        }
        return result;
    }

    private Boolean validaCampoString(NfeCampo nfeCampo, String value, Object obj, Field field) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Boolean result = TRUE;
        if (value == null) {
            value = "";
        }
        if (!nfeCampo.valorDefault().isEmpty() && value.isEmpty()) {
            value = nfeCampo.valorDefault();
            executarMetodoSet(obj, field, value);
        }
        //(Existem tags obrigatórias que podem ser nulas ex. cEAN)  if (ocorrencias = 1) and (EstaVazio) then
        if (nfeCampo.ocorrencias() == 1 && value.length() ==0 && nfeCampo.tamanhoMinimo() > 0) {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_VAZIO));
        }
        //valida tamanho campo Mínimo
        if (value.length() > 0 && nfeCampo.tamanhoMinimo() > 0 && value.length() < nfeCampo.tamanhoMinimo()) {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_MENOR));
        }
        //valida tamanho campo máximo
        if (value.length() > 0 && nfeCampo.tamanhoMaximo() > 0 && value.length() > nfeCampo.tamanhoMaximo())  {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_MAIOR));
        }
        return result;
    }
}
