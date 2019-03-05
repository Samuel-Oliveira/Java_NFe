package br.com.swconsultoria.nfe.util;

import java.util.Collection;
import java.util.Optional;

public final class ObjetoUtil {

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
            return ((Collection<?>) obj).size() == 0 ? Optional.empty() : Optional.of(obj);

        final String s = String.valueOf(obj).trim();

        return s.length() == 0 || s.equalsIgnoreCase("null") ? Optional.empty() : Optional.of(obj);
    }

}
