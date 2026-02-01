package com.company.usercreation.repository;

import com.company.usercreation.model.OtpToken;
import com.company.usercreation.model.OtpToken.Purpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OtpTokenRepository extends JpaRepository<OtpToken, Long> {

    @Modifying
    @Transactional
    @Query("""
    UPDATE OtpToken o
    SET o.used = true
    WHERE o.userId = :userId
      AND o.purpose = :purpose
      AND o.used = false
""")
    void invalidateUnusedOtps(
            @Param("userId") Long userId,
            @Param("purpose") Purpose purpose
    );


    Optional<OtpToken> findByUserIdAndOtpCodeAndPurposeAndUsedFalse(
            Long userId,
            String otpCode,
            Purpose purpose
    );
}
