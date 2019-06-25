package com.test.logparser.service;

import com.test.logparser.model.LogEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The log entity service.
 */
public interface LogEntityService {

    /**
     * The method to add a log entity list.
     *
     * @param entities the list of log entities
     */
    void saveAllAndFlush(final List<LogEntity> entities);

    /**
     * The method to find request`s ips by parameters
     *
     * @param startDate the request start date
     * @param endDate   the request end date
     * @param threshold the threshold
     * @return the list of log entities
     */
    List<LogEntity> findAllByDateBetweenAndByThreshold(final LocalDateTime startDate, final LocalDateTime endDate, final Long threshold);
}
