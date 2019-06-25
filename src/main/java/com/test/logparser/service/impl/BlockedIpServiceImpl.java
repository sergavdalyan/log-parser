package com.test.logparser.service.impl;

import com.test.logparser.model.BlockedIp;
import com.test.logparser.repository.BlockedIpRepository;
import com.test.logparser.service.BlockedIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The blocked ip service implementation.
 */
@Service
public class BlockedIpServiceImpl implements BlockedIpService {

    @Autowired
    private BlockedIpRepository repository;


    /*
     * (non-Javadoc)
     *
     * @see BlockedIpService#saveAll(List<BlockedIp>)
     */
    @Override
    @Transactional
    public void saveAll(final List<BlockedIp> blockedIps) {
        repository.saveAll(blockedIps);
    }
}
