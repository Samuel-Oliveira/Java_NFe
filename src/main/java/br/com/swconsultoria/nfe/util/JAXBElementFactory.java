package br.com.swconsultoria.nfe.util;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class JAXBElementFactory {
    public static <T> JAXBElement<T> create(QName qName, Class<T> clazz, T value) {
        return new JAXBElement<>(qName, clazz, clazz, value);
    }
}