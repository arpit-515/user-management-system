package com.company.usercreation.controller;

import com.company.usercreation.Services.EmailService;
import com.company.usercreation.model.User;
import com.company.usercreation.model.OtpToken;
import com.company.usercreation.repository.UserRepository;
import com.company.usercreation.repository.OtpTokenRepository;
import com.company.usercreation.util.TokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/users")
public class VerificationController {

    private final UserRepository userRepository;
    private final OtpTokenRepository otpTokenRepository;
    private final EmailService emailService;

    public VerificationController(
            UserRepository userRepository,
            OtpTokenRepository otpTokenRepository,
            EmailService emailService
    ) {
        this.userRepository = userRepository;
        this.otpTokenRepository = otpTokenRepository;
        this.emailService = emailService;
    }

    @GetMapping("/verify/email")
    public String verifyEmail(@RequestParam String token) {

        String tokenHash = TokenUtil.generateHash(token);
        OtpToken otpToken =
                otpTokenRepository
                        .findByOtpCodeAndPurposeAndUsedFalse(
                                tokenHash,
                                OtpToken.Purpose.INVITATION
                        )
                        .orElse(null);

        if (otpToken == null || LocalDateTime.now().isAfter(otpToken.getExpiresAt())) {
            return "verification-error";
        }

        User user = userRepository.findById(otpToken.getUserId()).orElse(null);
        if (user == null || user.getStatus() == User.Status.DEACTIVATED) {
            return "verification-error";
        }

        user.setEmailVerified(true);
        user.setMobileVerified(true);
        user.setStatus(User.Status.ACTIVE);
        otpToken.setUsed(true);

        userRepository.save(user);
        otpTokenRepository.save(otpToken);
        emailService.sendConfirmation(user.getEmail(), "EMAIL VERIFICATION");
        if (user.getPasswordHash() == null) {
            return "redirect:/users/set-password?email=" + user.getEmail();
        }

        return "redirect:/users/login?verified=true";
    }
}
