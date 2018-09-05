package br.com.samuelweb.nfe.util.validators.impl;

import br.com.samuelweb.nfe.util.Estados;
import br.com.samuelweb.nfe.util.model.Ide;
import br.com.samuelweb.nfe.util.model.RefNf;
import br.com.samuelweb.nfe.util.model.RefNfp;
import br.com.samuelweb.nfe.util.model.Veiculo;
import br.com.samuelweb.nfe.util.validators.RetornoValidar;
import br.com.samuelweb.nfe.util.validators.ValidadorCampo;

import java.util.HashMap;
import java.util.Map;

public class ValidarCodigoUf implements ValidadorCampo<Integer, Object> {

    private static Map<String, Estados> estadosMap =  new HashMap<>();
    static {
        for (Estados estado : Estados.values()) {
            estadosMap.put(estado.getCodigoIbge(), estado);
        }
    }

    public RetornoValidar validar(Integer valor, Object parent) {
        String codigoStr = String.valueOf(valor == null? "": valor);
        if (estadosMap.get(codigoStr) != null) {
            return new RetornoValidarImpl(true);
        }
        if (parent.getClass().equals(RefNfp.class)) {
            return new RetornoValidarImpl(false, String.format(
                    "Código do IBGE da UF da NF de produtor referenciada não é valido. Código informado %d", valor));
        }
        if (parent.getClass().equals(RefNf.class)) {
            return new RetornoValidarImpl(false, String.format(
                    "Código do IBGE da UF Da NF referenciada não é valido. Código informado %d", valor));
        }
        if (parent.getClass().equals(Ide.class)) {
            return new RetornoValidarImpl(false, String.format(
                    "Código do IBGE da UF informado na Empresa não é valido. Código informado %d", valor));
        }
        if (parent.getClass().equals(Veiculo.class)) {
            return new RetornoValidarImpl(false, String.format(
                    "Código do IBGE da UF da placa do veículo %s não é valido. Código informado %d"
                    , ((Veiculo) parent).getPlaca(), valor));
        }
        return new RetornoValidarImpl(false, String.format(
                "Código do IBGE da UF informado não é valido. Código informado %d", valor));
    }
}
