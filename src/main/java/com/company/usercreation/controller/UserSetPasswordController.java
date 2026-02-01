package com.company.usercreation.controller;

import com.company.usercreation.model.User;
import com.company.usercreation.repository.UserRepository;
import com.company.usercreation.util.PasswordValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserSetPasswordController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserSetPasswordController(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/set-password")
    public String setPasswordPage(
            @RequestParam String email,
            Model model
    ) {
        model.addAttribute("email", email);
        return "set-password";
    }

    @PostMapping("/set-password")
    public String setPassword(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model
    ) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return "verification-error";

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            model.addAttribute("email", email);
            return "set-password";
        }

        String error = PasswordValidator.validate(password);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("email", email);
            return "set-password";
        }

        user.setPasswordHash(passwordEncoder.encode(password));
        user.setStatus(User.Status.ACTIVE);
        userRepository.save(user);

        return "redirect:/users/login?passwordSet=true";
    }
}
