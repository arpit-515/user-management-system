package com.company.usercreation.controller;

import com.company.usercreation.model.User;
import com.company.usercreation.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserLoginController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserLoginController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String reloadLogin(
            Model model,
            HttpSession session,
            String errorMessage
    ) {
        int a = (int) (Math.random() * 10) + 1;
        int b = (int) (Math.random() * 10) + 1;

        session.setAttribute("LOGIN_CAPTCHA", a + b);
        model.addAttribute("captchaQuestion",
                "What is " + a + " + " + b + " ?");
        model.addAttribute("error", errorMessage);

        return "user-login";
    }


    @GetMapping("/login")
    public String loginPage(HttpSession session, Model model) {

        if (session.getAttribute("USER_ID") != null) {
            return "redirect:/users/dashboard";
        }

        int a = (int) (Math.random() * 10) + 1;
        int b = (int) (Math.random() * 10) + 1;

        session.setAttribute("LOGIN_CAPTCHA", a + b);
        model.addAttribute("captchaQuestion", "What is " + a + " + " + b + " ?");

        return "user-login";
    }


    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String captcha,
            HttpSession session,
            Model model
    ) {
        Integer expectedCaptcha =
                (Integer) session.getAttribute("LOGIN_CAPTCHA");

        session.removeAttribute("LOGIN_CAPTCHA");

        if (expectedCaptcha == null || !captcha.equals(expectedCaptcha.toString())) {
            return reloadLogin(model, session,
                    "Captcha incorrect. Please try again.");
        }

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return reloadLogin(model, session,
                    "Invalid credentials");
        }

        if (user.getStatus() == User.Status.DEACTIVATED) {
            return reloadLogin(model, session, "Your account has been deactivated by administrator.");
        }

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            return reloadLogin(model, session,
                    "Invalid credentials");
        }

        session.setAttribute("USER_ID", user.getId());
        return "redirect:/users/dashboard";
    }



    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        if (session.getAttribute("USER_ID") == null) {
            return "redirect:/users/login";
        }

        return "user-dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }

}
