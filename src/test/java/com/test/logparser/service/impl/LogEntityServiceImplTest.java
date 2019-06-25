package com.test.logparser.service.impl;

import com.test.logparser.model.LogEntity;
import com.test.logparser.repository.LogEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LogEntityServiceImplTest {

    @Mock
    private LogEntityRepository repository;

    @InjectMocks
    private LogEntityServiceImpl logEntityService;


    @Test
    public void testSaveAllAndFlush() {
        final List<LogEntity> logEntities = createLogEntityList();

        logEntityService.saveAllAndFlush(logEntities);

        verify(repository, times(1)).saveAll(logEntities);
        verify(repository, times(1)).flush();
    }

    @Test
    public void testFindAllByDateBetweenAndByThreshold() {
        final List<LogEntity> expected = createLogEntityList();
        final LocalDateTime startDate = LocalDateTime.now();
        final LocalDateTime endDate = LocalDateTime.now();
        final Long threshold = 123L;

        when(logEntityService.findAllByDateBetweenAndByThreshold(startDate, endDate, threshold)).thenReturn(expected);

        final List<LogEntity> actual = logEntityService.findAllByDateBetweenAndByThreshold(startDate, endDate, threshold);

        verify(repository, times(1)).findAllByDateBetweenAndByThreshold(startDate, endDate, threshold);

        assertEqualsLogEntity(expected, actual);
    }

    private List<LogEntity> createLogEntityList() {
        final LogEntity logEntity = new LogEntity();
        logEntity.setId("id");
        logEntity.setDate(LocalDateTime.now());
        logEntity.setIp("1.1.2");
        logEntity.setRequest("request");
        logEntity.setStatus(200);
        logEntity.setUserAgent("firefox");
        return Collections.singletonList(logEntity);
    }

    private void assertEqualsLogEntity(LogEntity expected, LogEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getIp(), actual.getIp());
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getRequest(), actual.getRequest());
        assertEquals(expected.getUserAgent(), actual.getUserAgent());
        assertEquals(expected.getStatus(), actual.getStatus());
    }

    private void assertEqualsLogEntity(List<LogEntity> expected, List<LogEntity> actual) {
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEqualsLogEntity(expected.get(i), actual.get(i));
        }
    }

}
