package com.test.logparser.model;

/**
 * The Duration.
 */
public enum Duration {

    DAILY("daily"),
    HOURLY("hourly");

    private String value;

    Duration(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Duration getFromValue(final String value){
        for (Duration duration : Duration.values()) {
            if (duration.value.equalsIgnoreCase(value)){
                return duration;
            }
        }
        return null;
    }
}
