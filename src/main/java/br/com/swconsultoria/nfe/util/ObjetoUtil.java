package br.com.swconsultoria.nfe.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Optional;

public final class ObjetoUtil {

    private ObjetoUtil() {}

    /**
     * Verifica se um objeto é vazio.
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> Optional<T> verifica(T obj) {
        if (obj == null)
            return Optional.empty();
        if (obj instanceof Collection)
            return ((Collection<?>) obj).isEmpty() ? Optional.empty() : Optional.of(obj);

        final String s = String.valueOf(obj).trim();

        return s.isEmpty() || s.equalsIgnoreCase("null") ? Optional.empty() : Optional.of(obj);
    }

    public static BigDecimal getBigDecimalOrZero(String value) {
        try {
            return value != null ? new BigDecimal(value) : BigDecimal.ZERO;
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public static BigDecimal getOrZero(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }

    public static String getValor2Casas(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            return "0.00";
        }
        return valor.setScale(2, RoundingMode.HALF_UP).toString();
    }

    public static String getValor4Casas(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            return "0.0000";
        }
        return valor.setScale(4, RoundingMode.HALF_UP).toString();
    }

}
