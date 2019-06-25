package com.test.logparser.repository;

import com.test.logparser.model.BlockedIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The blocked ip repository.
 */
@Repository
public interface BlockedIpRepository extends JpaRepository<BlockedIp, String> {
}
