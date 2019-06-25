package com.test.logparser.service.impl;

import com.test.logparser.model.BlockedIp;
import com.test.logparser.repository.BlockedIpRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BlockedServiceImplTest {


    @Mock
    private BlockedIpRepository repository;

    @InjectMocks
    private BlockedIpServiceImpl blockedService;

    @Test
    public void testSaveAll() {
        final List<BlockedIp> list = createBlockedIpList();

        blockedService.saveAll(list);

        verify(repository, times(1)).saveAll(list);
    }

    private List<BlockedIp> createBlockedIpList() {
        final BlockedIp blockedIp = new BlockedIp();
        blockedIp.setComment("comment");
        blockedIp.setId("id");
        blockedIp.setId("id");

        return Collections.singletonList(blockedIp);
    }
}
