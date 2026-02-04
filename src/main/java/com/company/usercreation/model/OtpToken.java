package com.company.usercreation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_tokens")
public class OtpToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "otp_code", nullable = false)
    private String otpCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "purpose", length = 50, nullable = false)
    private Purpose purpose;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private boolean used;

    public enum Purpose {
        INVITATION,
        PASSWORD_CHANGE,
        EMAIL_VERIFICATION,
        MOBILE_VERIFICATION
    }

    //getters

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    //setters

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
