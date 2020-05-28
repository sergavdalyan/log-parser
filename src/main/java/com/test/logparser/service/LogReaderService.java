package com.test.logparser.service;

import com.test.logparser.model.LogEntity;
import com.test.logparser.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The log reader service.
 */
@Component
public class LogReaderService {

    private static final String DELIMITER = "\\|";
    private static final int BATCH_COUNT = 50;

    private Logger logger = LoggerFactory.getLogger(LogReaderService.class);


    @Autowired
    private LogEntityService logEntityService;

    /**
     * The method to read logs from file and persist.
     *
     * @param path log file path
     */
    public void readAndPersistLogs(String path) {
        List<LogEntity> entities = new ArrayList<>();
        logger.debug("Starting: reading and persisting logs");
        logger.debug("Starting to read {} file", path);

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] strings = line.split(DELIMITER);

                LogEntity logEntity = new LogEntity();
                logEntity.setDate(DateUtils.parseDate(strings[0], DateUtils.LOG_DATE_FORMAT));
                logEntity.setIp(strings[1]);
                logEntity.setRequest(strings[2]);
                logEntity.setStatus(Integer.parseInt(strings[3]));
                logEntity.setUserAgent(strings[4]);

                entities.add(logEntity);

                // Persist logs in batch mode
                if ((entities.size() % BATCH_COUNT) == 0) {
                    logEntityService.saveAllAndFlush(entities);
                    entities = new ArrayList<>();
                }
            }
            logger.debug("Finished: readAndPersistLogs");
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file ", e);
        }
    }
}
