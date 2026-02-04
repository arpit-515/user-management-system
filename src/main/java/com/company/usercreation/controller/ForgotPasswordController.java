package com.company.usercreation.controller;

import com.company.usercreation.Services.EmailService;
import com.company.usercreation.model.OtpToken;
import com.company.usercreation.model.User;
import com.company.usercreation.repository.OtpTokenRepository;
import com.company.usercreation.repository.UserRepository;
import com.company.usercreation.util.TokenUtil;
import com.company.usercreation.util.PasswordValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/users")
public class ForgotPasswordController {

    private final UserRepository userRepository;
    private final OtpTokenRepository otpTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public ForgotPasswordController(
            UserRepository userRepository,
            OtpTokenRepository otpTokenRepository,
            BCryptPasswordEncoder passwordEncoder,
            EmailService emailService) {
        this.userRepository = userRepository;
        this.otpTokenRepository = otpTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String generateResetOtp(
            @RequestParam String email,
            Model model
    ) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null || user.isDeleted() || user.getStatus() != User.Status.ACTIVE) {
            model.addAttribute("error", "Invalid or inactive account");
            return "forgot-password";
        }

        String otp = TokenUtil.generateOtp();

        OtpToken token = new OtpToken();
        token.setUserId(user.getId());
        token.setOtpCode(otp);
        token.setPurpose(OtpToken.Purpose.PASSWORD_CHANGE);
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        token.setUsed(false);

        otpTokenRepository.save(token);

        emailService.sendOtp(user.getEmail(), otp);

        model.addAttribute("email", email);
        model.addAttribute("info", "An OTP has been sent to your registered email.");
        return "reset-password";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage(
            @RequestParam String email,
            Model model
    ) {
        model.addAttribute("email", email);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String email,
            @RequestParam String otp,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model
    ) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null || user.isDeleted()) {
            model.addAttribute("error", "Invalid request");
            return "reset-password";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            model.addAttribute("email", email);
            return "reset-password";
        }

        String pwdError = PasswordValidator.validate(password);
        if (pwdError != null) {
            model.addAttribute("error", pwdError);
            model.addAttribute("email", email);
            return "reset-password";
        }

        OtpToken token = otpTokenRepository
                .findByUserIdAndOtpCodeAndPurposeAndUsedFalse(
                        user.getId(),
                        otp,
                        OtpToken.Purpose.PASSWORD_CHANGE
                )
                .orElse(null);

        if (token == null || LocalDateTime.now().isAfter(token.getExpiresAt())) {
            model.addAttribute("error", "Invalid or expired OTP");
            model.addAttribute("email", email);
            return "reset-password";
        }

        user.setPasswordHash(passwordEncoder.encode(password));
        userRepository.save(user);
        token.setUsed(true);
        otpTokenRepository.save(token);

        model.addAttribute("success", "Password reset successful. Please login.");
        emailService.sendConfirmation(user.getEmail(), "PASSWORD RESET");
        return "user-login";
    }


}
