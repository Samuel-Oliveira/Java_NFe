package br.com.samuelweb.nfe.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NfeObjetoList {
    String id();
    String tag();
    String descricao();
    int ocorrenciaMinima() default 0;
    int ocorrenciaMaxima() default 0;
}
