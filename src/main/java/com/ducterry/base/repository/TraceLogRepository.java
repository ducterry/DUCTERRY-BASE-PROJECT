package com.ducterry.base.repository;

import com.ducterry.base.entity.log.TraceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceLogRepository extends JpaRepository<TraceLog, Long> {
}
