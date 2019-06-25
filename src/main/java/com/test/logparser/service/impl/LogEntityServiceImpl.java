package com.test.logparser.service.impl;

import com.test.logparser.model.LogEntity;
import com.test.logparser.repository.LogEntityRepository;
import com.test.logparser.service.LogEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The blocked ip service implementation.
 */
@Service
public class LogEntityServiceImpl implements LogEntityService {

    @Autowired
    private LogEntityRepository repository;

    /*
     * (non-Javadoc)
     *
     * @see LogEntityRepository#saveAllAndFlush(List<LogEntity>)
     */
    @Override
    @Transactional
    public void saveAllAndFlush(List<LogEntity> entities) {
        repository.saveAll(entities);
        repository.flush();
    }

    /*
     * (non-Javadoc)
     *
     * @see LogEntityRepository#findAllByDateBetweenAndByThreshold(LocalDateTime, LocalDateTime, Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<LogEntity> findAllByDateBetweenAndByThreshold(final LocalDateTime startDate, final LocalDateTime endDate, final Long threshold) {
        return repository.findAllByDateBetweenAndByThreshold(startDate, endDate, threshold);
    }
}
