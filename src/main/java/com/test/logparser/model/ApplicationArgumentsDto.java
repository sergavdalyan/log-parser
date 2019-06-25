package com.test.logparser.model;

/**
 * The application argument dto
 */
public class ApplicationArgumentsDto {

    private final String logPath;

    private final String startDateValue;

    private final String threshold;

    private final Duration duration;

    public ApplicationArgumentsDto(String logPath, String startDateValue, String threshold, Duration duration) {
        this.logPath = logPath;
        this.startDateValue = startDateValue;
        this.threshold = threshold;
        this.duration = duration;
    }

    public String getLogPath() {
        return logPath;
    }

    public String getStartDateValue() {
        return startDateValue;
    }

    public String getThreshold() {
        return threshold;
    }


    public Duration getDuration() {
        return duration;
    }
}
