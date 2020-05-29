package com.test.logparser.service;

import com.test.logparser.model.ApplicationArgumentsDto;
import com.test.logparser.model.BlockedIp;
import com.test.logparser.model.Duration;
import com.test.logparser.model.LogEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.test.logparser.util.DateUtils.*;


/**
 * The log analyzer.
 */
@Component
public class LogAnalyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAnalyzer.class);

    @Autowired
    private LogReaderService logReaderService;

    @Autowired
    private LogEntityService logEntityService;

    @Autowired
    private BlockedIpService blockedIpService;


    /**
     * The method for persisting logs and finding IPs.
     *
     * @param applicationArgumentsDto the applicationArgumentsDto
     */
    public void analyze(final ApplicationArgumentsDto applicationArgumentsDto) {
        // If log path present in parameters read and persist
        if (!StringUtils.isEmpty(applicationArgumentsDto.getLogPath())) {
            logReaderService.readAndPersistLogs(applicationArgumentsDto.getLogPath());
        }

        LocalDateTime startDate = parseDate(applicationArgumentsDto.getStartDateValue(), PARAMETER_DATE_FORMAT);
        LocalDateTime endDate;
        if (applicationArgumentsDto.getDuration() == Duration.HOURLY) {
            endDate = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startDate.getHour(), 59, 59);
        } else {
            endDate = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), 23, 59, 59);
        }


        final List<LogEntity> logEntityList = findAndSaveIPs(startDate, endDate, applicationArgumentsDto.getDuration(),
                Integer.parseInt(applicationArgumentsDto.getThreshold()));

        printIps(logEntityList);

        // save IPs
        saveBlockedIps(logEntityList, startDate, endDate);
    }

    private void printIps(final List<LogEntity> logs) {
        System.out.println("--------------------------------------------------------------------------------------");
        for (int i = 0; i < logs.size(); i++) {
            System.out.println(String.format("%s: %s", i, logs.get(i).getIp()));
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    private List<LogEntity> findAndSaveIPs(final LocalDateTime startDate, final LocalDateTime endDate, final Duration duration, final int threshold) {

        List<LogEntity> logEntityList = logEntityService.findAllByDateBetweenAndByThreshold(startDate, endDate, (long) threshold);

        LOGGER.debug("Found {} IPs, by parameters from {} to {} threshold {}", logEntityList.size(), startDate, endDate, threshold);


        return logEntityList;

    }


    private void saveBlockedIps(final List<LogEntity> logEntityList, final LocalDateTime startDate, final LocalDateTime endDate) {
        final List<BlockedIp> blockedIpList = logEntityList.stream()
                .map(log -> {
                    BlockedIp blockedIp = new BlockedIp();
                    blockedIp.setIp(log.getIp());
                    blockedIp.setComment(String.format("From %s IP was requests between %s and %s ", log.getIp(), startDate, endDate));
                    return blockedIp;
                })
                .collect(Collectors.toList());

        LOGGER.debug("Save {} blocked IPs", blockedIpList.size());
        blockedIpService.saveAll(blockedIpList);
    }
}
