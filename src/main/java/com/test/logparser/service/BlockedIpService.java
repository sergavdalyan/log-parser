package com.test.logparser.service;

import com.test.logparser.model.BlockedIp;

import java.util.List;

/**
 * The blocked ip service.
 */
public interface BlockedIpService {

    /**
     * The method to add a blocked ip list.
     *
     * @param blockedIps the list of blocked ips
     */
    void saveAll(final List<BlockedIp> blockedIps);
}
