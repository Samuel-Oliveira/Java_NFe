package br.com.swconsultoria.nfe.util;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 03/03/2019 - 11:34
 */
public class LoggerUtil {

    private static Logger LOGGER = Logger.getLogger("");

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tD-%1$tT][%2$1s]%5$s %n");
    }

    public static void log(Class classe, String msg){
        LOGGER.logp(Level.INFO, classe.getName(), null, msg);
    }


}
