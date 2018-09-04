package br.com.samuelweb.nfe.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NfeCampo {
    String id();
    String tag();
    String descricao();
    Class<?> tipo() default String.class;
    int tamanhoMinimo() default -1;
    int tamanhoMaximo() default -1;
    int precisao() default 0;
    int decimais() default 2;
    int ocorrencias() default 0;
    Class[] validadores() default {};
    String valorDefault() default "";
}
