package com.test.logparser.repository;

import com.test.logparser.model.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Log entity repository.
 */
@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, String> {

    @Query(value = "SELECT *, COUNT(ip) AS c FROM log WHERE date BETWEEN :startDate AND :endDate GROUP BY ip HAVING c > :threshold", nativeQuery = true)
    List<LogEntity> findAllByDateBetweenAndByThreshold(LocalDateTime startDate, LocalDateTime endDate, Long threshold);
}
