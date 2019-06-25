package com.test.logparser.util;

import java.util.List;
import java.util.Optional;

public class CollectionUtil {

    private static final String EMPTY = "";


    public static String getFirstValueFromList(final List<String> list) {
        return Optional.ofNullable(list).filter(l -> !l.isEmpty())
                .flatMap(l -> Optional.of(l.get(0))).orElse(EMPTY);

    }
}
