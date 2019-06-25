package com.test.logparser;

import com.test.logparser.model.ApplicationArgumentsDto;
import com.test.logparser.model.BlockedIp;
import com.test.logparser.model.Duration;
import com.test.logparser.model.LogEntity;
import com.test.logparser.service.LogAnalyzer;
import com.test.logparser.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.test.logparser.util.CollectionUtil.getFirstValueFromList;
import static com.test.logparser.util.DateUtils.parseDate;
import static com.test.logparser.util.Validator.validateArgument;

@SpringBootApplication
public class LogParserApplication implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogParserApplication.class);

    private static final String ACCESS_LOG_KEY = "accesslog";
    private static final String START_DATE_KEY = "startDate";
    private static final String DURATION_KEY = "duration";
    private static final String THRESHOLD_KEY = "threshold";

    @Autowired
    private LogAnalyzer logAnalyzer;

    public static void main(String[] args) {
        SpringApplication.run(LogParserApplication.class, args);
    }

    @Override
    public void run(final ApplicationArguments args) {

        final String logPath = getFirstValueFromList(args.getOptionValues(ACCESS_LOG_KEY));
        final String startDateValue = getFirstValueFromList(args.getOptionValues(START_DATE_KEY));
        final String threshold = getFirstValueFromList(args.getOptionValues(THRESHOLD_KEY));
        final String durationValue = getFirstValueFromList(args.getOptionValues(DURATION_KEY));
        final Duration duration = Duration.getFromValue(durationValue);

        LOGGER.debug("Application started with following parameters accesslog: {}, startDate: {}, duration: {}, threshold: {}", logPath, startDateValue, duration, threshold);

        validateArgument(startDateValue, START_DATE_KEY);
        validateArgument(threshold, THRESHOLD_KEY);
        validateArgument(durationValue, DURATION_KEY);

        logAnalyzer.analyze(new ApplicationArgumentsDto(logPath, startDateValue, threshold, duration));
    }


}
