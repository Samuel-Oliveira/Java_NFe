package br.com.samuelweb.nfe.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String formatDate(LocalDateTime date, ZoneId zoneId) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        return ZonedDateTime.of(date, zoneId).format(dateTimeFormatter);
    }

    public static String formatDate(ZonedDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        return date.format(dateTimeFormatter);
    }
}
