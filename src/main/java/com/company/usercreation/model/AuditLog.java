package com.company.usercreation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_id", nullable = false)
    private Long adminId;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "target_user_id", nullable = false)
    private Long targetUserId;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    // GETTERS AND SETTERS
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public String getAction() {
        return action;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public LocalDateTime getCreatedAt() {
        return timestamp;
    }
}