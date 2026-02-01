package com.company.usercreation.controller;

import com.company.usercreation.model.OtpToken;
import com.company.usercreation.model.User;
import com.company.usercreation.repository.OtpTokenRepository;
import com.company.usercreation.repository.UserRepository;
import com.company.usercreation.util.OtpGenerator;
import com.company.usercreation.util.PasswordValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/users")
public class UserAccountController {

    private final UserRepository userRepository;
    private final OtpTokenRepository otpTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserAccountController(
            UserRepository userRepository,
            OtpTokenRepository otpTokenRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.otpTokenRepository = otpTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/change-password")
    public String changePasswordPage(HttpSession session) {
        if (session.getAttribute("USER_ID") == null) {
            return "redirect:/users/login";
        }
        return "user-change-password";
    }

    @PostMapping("/change-password")
    public String requestPasswordChange(
            @RequestParam String oldPassword,
            HttpSession session,
            Model model
    ) {
        Long userId = (Long) session.getAttribute("USER_ID");
        if (userId == null) {
            return "redirect:/users/login";
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/users/login";
        }

        if (!passwordEncoder.matches(oldPassword, user.getPasswordHash())) {
            model.addAttribute("error", "Incorrect current password");
            return "user-change-password";
        }

        String otp = OtpGenerator.generateOtp();

        OtpToken token = new OtpToken();
        token.setUserId(userId);
        token.setOtpCode(otp);
        token.setPurpose(OtpToken.Purpose.PASSWORD_CHANGE);
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        token.setUsed(false);

        otpTokenRepository.save(token);

        System.out.println("[DEV] Password change OTP for " + user.getEmail() + ": " + otp);

        model.addAttribute("info", "OTP sent to your registered email");
        return "user-change-password-verify";
    }

    @PostMapping("/change-password/verify")
    public String confirmPasswordChange(
            @RequestParam String otp,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            HttpSession session,
            Model model
    ) {
        Long userId = (Long) session.getAttribute("USER_ID");
        if (userId == null) {
            return "redirect:/users/login";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "user-change-password-verify";
        }

        String pwdError = PasswordValidator.validate(newPassword);
        if (pwdError != null) {
            model.addAttribute("error", pwdError);
            return "user-change-password-verify";
        }

        OtpToken token = otpTokenRepository
                .findByUserIdAndOtpCodeAndPurposeAndUsedFalse(
                        userId,
                        otp,
                        OtpToken.Purpose.PASSWORD_CHANGE
                )
                .orElse(null);

        if (token == null || LocalDateTime.now().isAfter(token.getExpiresAt())) {
            model.addAttribute("error", "Invalid or expired OTP");
            return "user-change-password-verify";
        }

        User user = userRepository.findById(userId).orElse(null);
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        token.setUsed(true);
        otpTokenRepository.save(token);

        session.invalidate();

        return "redirect:/users/login?passwordChanged=true";
    }

    @GetMapping("/profile")
    public String userProfile(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("USER_ID");
        if (userId == null) {
            return "redirect:/users/login";
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/users/login";
        }

        model.addAttribute("user", user);
        return "user-profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @RequestParam String fullName,
            HttpSession session,
            Model model
    ) {
        Long userId = (Long) session.getAttribute("USER_ID");
        if (userId == null) {
            return "redirect:/users/login";
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "redirect:/users/login";
        }

        user.setFullName(fullName);
        userRepository.save(user);
        model.addAttribute("user", user);
        model.addAttribute("success", "Profile updated successfully");
        return "user-profile";
    }


}

