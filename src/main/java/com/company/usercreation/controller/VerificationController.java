package com.company.usercreation.controller;

import com.company.usercreation.model.User;
import com.company.usercreation.model.OtpToken;
import com.company.usercreation.repository.UserRepository;
import com.company.usercreation.repository.OtpTokenRepository;
import com.company.usercreation.util.OtpGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/users")
public class VerificationController {

    private final UserRepository userRepository;
    private final OtpTokenRepository otpTokenRepository;

    public VerificationController(
            UserRepository userRepository,
            OtpTokenRepository otpTokenRepository
    ) {
        this.userRepository = userRepository;
        this.otpTokenRepository = otpTokenRepository;
    }


    @GetMapping("/verify/email")
    public String emailVerificationPage(
            @RequestParam String email,
            Model model
    ) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null || user.getStatus() == User.Status.DEACTIVATED) {
            return "verification-error";
        }

        otpTokenRepository.invalidateUnusedOtps(
                user.getId(),
                OtpToken.Purpose.ACTIVATION_EMAIL
        );

        String otp = OtpGenerator.generateOtp();

        OtpToken token = new OtpToken();
        token.setUserId(user.getId());
        token.setOtpCode(otp);
        token.setPurpose(OtpToken.Purpose.ACTIVATION_EMAIL);
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        token.setUsed(false);

        otpTokenRepository.save(token);

        System.out.println("EMAIL OTP = " + otp);

        model.addAttribute("email", email);
        model.addAttribute("title", "Verify Email");
        model.addAttribute("info", "Enter OTP sent to your email");
        model.addAttribute("postUrl", "/users/verify/email");

        return "verify-otp";
    }

    @PostMapping("/verify/email")
    public String verifyEmail(
            @RequestParam String email,
            @RequestParam String otp,
            Model model
    ) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return "verification-error";

        OtpToken token =
                otpTokenRepository.findByUserIdAndOtpCodeAndPurposeAndUsedFalse(
                        user.getId(),
                        otp,
                        OtpToken.Purpose.ACTIVATION_EMAIL
                ).orElse(null);

        if (token == null || LocalDateTime.now().isAfter(token.getExpiresAt())) {
            model.addAttribute("error", "Invalid or expired OTP");
            model.addAttribute("email", email);
            model.addAttribute("title", "Verify Email");
            model.addAttribute("postUrl", "/users/verify/email");
            return "verify-otp";
        }

        user.setEmailVerified(true);
        token.setUsed(true);

        userRepository.save(user);
        otpTokenRepository.save(token);

        return "redirect:/users/verify/mobile?email=" + email;
    }

    @GetMapping("/verify/mobile")
    public String mobileVerificationPage(
            @RequestParam String email,
            Model model
    ) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null || user.getStatus() == User.Status.DEACTIVATED) {
            model.addAttribute("email", user.getEmail());
            return "verification-error";
        }

        otpTokenRepository.invalidateUnusedOtps(
                user.getId(),
                OtpToken.Purpose.ACTIVATION_MOBILE
        );

        String otp = OtpGenerator.generateOtp();

        OtpToken token = new OtpToken();
        token.setUserId(user.getId());
        token.setOtpCode(otp);
        token.setPurpose(OtpToken.Purpose.ACTIVATION_MOBILE);
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        token.setUsed(false);

        otpTokenRepository.save(token);

        System.out.println("MOBILE OTP = " + otp);

        model.addAttribute("email", email);
        model.addAttribute("title", "Verify Mobile");
        model.addAttribute("info", "Enter OTP sent to your mobile");
        model.addAttribute("postUrl", "/users/verify/mobile");

        return "verify-otp";
    }

    @PostMapping("/verify/mobile")
    public String verifyMobile(
            @RequestParam String email,
            @RequestParam String otp
    ) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return "verification-error";
        }

        OtpToken token =
                otpTokenRepository.findByUserIdAndOtpCodeAndPurposeAndUsedFalse(
                        user.getId(),
                        otp,
                        OtpToken.Purpose.ACTIVATION_MOBILE
                ).orElse(null);

        if (token == null || LocalDateTime.now().isAfter(token.getExpiresAt())) {
            return "verification-error";
        }
        //marking mobile verified
        user.setMobileVerified(true);
        token.setUsed(true);

        userRepository.save(user);
        otpTokenRepository.save(token);

        if (user.getPasswordHash() == null) {
            return "redirect:/users/set-password?email=" + email;
        }

        return "redirect:/users/login?verified=true";
    }

}
