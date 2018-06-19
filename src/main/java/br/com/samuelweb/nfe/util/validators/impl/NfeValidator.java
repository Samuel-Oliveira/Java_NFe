package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.annotation.NfeCampo;
import br.com.samuelweb.nfe.util.consts.DfeConsts;
import br.com.samuelweb.nfe.util.enumeration.EnumNfeValue;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import org.apache.commons.lang3.text.WordUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
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

    public Boolean validarObject(Object obj)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> persistentClass = obj.getClass();
        errosList = new ArrayList<>();

        Boolean result = TRUE;
        for (Field field : persistentClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(NfeCampo.class)) {
                NfeCampo nfeCampo = field.getAnnotation(NfeCampo.class);
                Method method = persistentClass.getDeclaredMethod("get"+ WordUtils.capitalize(field.getName()), null);
                Object objRet = method.invoke(obj, null);

                if (nfeCampo.tipo().equals(String.class)) {
                    result = validaCampoString(nfeCampo, (String) objRet) && result;
                } else if (nfeCampo.tipo().equals(Integer.class)) {
                    result = validaCampoInteger(nfeCampo, (Integer) objRet) && result;
                } else if (nfeCampo.tipo().equals(LocalDateTime.class)) {
                    result = validaCampoLocalDateTime(nfeCampo, (LocalDateTime) objRet) && result;
                } else if (nfeCampo.tipo().equals(BigDecimal.class)) {
                    result = validaCampoBigDecimalValue(nfeCampo, (BigDecimal) objRet) && result;
                } else if (EnumNfeValue.class.isAssignableFrom(nfeCampo.tipo())) {
                    result = validaCampoEnumNfeValue(nfeCampo, (EnumNfeValue) objRet) && result;
                }

                result = executeValidadores(nfeCampo, objRet) && result;
            }
        }
        return result;
    }

    private Boolean validaCampoBigDecimalValue(NfeCampo nfeCampo, BigDecimal value) {
        Boolean result = TRUE;
        if (!nfeCampo.valorDefault().isEmpty() && value == null) {
            value = new BigDecimal(nfeCampo.valorDefault());
        }
        StringBuilder mascara = new StringBuilder();
        for (int i = 0;i < nfeCampo.precisao();i++){
            mascara.append("#");
        }
        mascara.append(".");
        for (int i = 0;i < nfeCampo.decimais();i++){
            mascara.append("0");
        }
        DecimalFormat format = new DecimalFormat(mascara.toString());
        String valueStr = format.format(value == null? BigDecimal.ZERO: value);
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

    private Boolean validaCampoLocalDateTime(NfeCampo nfeCampo, LocalDateTime value) {
        Boolean result = TRUE;
        if (value == null && nfeCampo.ocorrencias() >= 1) {
            result = FALSE;
            errosList.add(new ErrosValidacao(nfeCampo.id(), nfeCampo.tag(), nfeCampo.descricao(), DfeConsts.ERR_MSG_VAZIO));
        }
        return result;
    }

    private Boolean validaCampoInteger(NfeCampo nfeCampo, Integer value) {
        Boolean result = TRUE;
        if (!nfeCampo.valorDefault().isEmpty() && value == null) {
            value = Integer.parseInt(nfeCampo.valorDefault());
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

    private Boolean validaCampoString(NfeCampo nfeCampo, String value) {
        Boolean result = TRUE;
        if (value == null) {
            value = "";
        }
        if (!nfeCampo.valorDefault().isEmpty() && value.isEmpty()) {
            value = nfeCampo.valorDefault();
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
