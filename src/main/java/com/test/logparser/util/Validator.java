package com.test.logparser.util;

import org.springframework.util.StringUtils;

public class Validator {

    public static void validateArgument(final String value, final String key) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException(String.format("'%s' parameter is mandatory", key));
        }
    }
}
