package com.test.logparser.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The date utils.
 */
public class DateUtils {

    public static final String LOG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String PARAMETER_DATE_FORMAT = "yyyy-MM-dd.HH:mm:ss";

    /**
     * The method to parse date.
     *
     * @param value  the date value
     * @param format the format
     * @return parsed date
     */
    public static LocalDateTime parseDate(String value, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(value, formatter);
    }
}
