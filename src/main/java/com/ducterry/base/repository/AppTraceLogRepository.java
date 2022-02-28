package com.ducterry.base.repository;

import com.ducterry.base.entity.log.AppTraceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppTraceLogRepository extends JpaRepository<AppTraceLog, Long> {
}
