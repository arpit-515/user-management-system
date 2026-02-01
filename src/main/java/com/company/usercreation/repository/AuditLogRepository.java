package com.company.usercreation.repository;

import com.company.usercreation.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}
