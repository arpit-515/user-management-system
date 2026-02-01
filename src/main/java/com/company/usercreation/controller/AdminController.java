package com.company.usercreation.controller;

import com.company.usercreation.model.Admin;
import com.company.usercreation.model.AuditLog;
import com.company.usercreation.model.User;
import com.company.usercreation.repository.AdminRepository;
import com.company.usercreation.repository.AuditLogRepository;
import com.company.usercreation.repository.OtpTokenRepository;
import com.company.usercreation.repository.UserRepository;
import com.company.usercreation.util.PasswordValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController{
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuditLogRepository auditLogRepository;

    public AdminController(AdminRepository adminRepository,
                           BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, OtpTokenRepository otpTokenRepository, AuditLogRepository auditLogRepository) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.auditLogRepository = auditLogRepository;
    }

    //method for adding logs
    private void logAudit(
            HttpSession session,
            String action,
            Long targetUserId
    ) {
        Long adminId = (Long) session.getAttribute("ADMIN_ID");
        AuditLog log = new AuditLog();
        log.setAdminId(adminId);
        log.setAction(action);
        log.setTargetUserId(targetUserId);
        auditLogRepository.save(log);
    }

    // admin/login get and post
    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        Long adminId = (Long) session.getAttribute("ADMIN_ID");

        if (adminId != null) {
            return "redirect:/admin/dashboard";
        }

        return "admin-login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        Admin admin = adminRepository.findByUsername(username).orElse(null);

        if (admin == null || !admin.isActive()) {
            model.addAttribute("error", "Invalid Credentials");
            return "admin-login";
        }

        if (!passwordEncoder.matches(password, admin.getPasswordHash())) {
            model.addAttribute("error", "Invalid Credentials");
            return "admin-login";
        }

        session.setAttribute("ADMIN_ID", admin.getId());
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if(session.getAttribute("ADMIN_ID") == null) {
            return "redirect:/admin/login";
        }
        return "admin-dashboard";
    }

    @GetMapping("")
    public String adminRoot(HttpSession session) {
        if (session.getAttribute("ADMIN_ID") != null) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/login";
    }

    @GetMapping("/change-name")
    public String changeName(HttpSession session){
        if(session.getAttribute("ADMIN_ID") == null){
            return "redirect:/admin/login";
        }
        return "admin-change-name";
    }

    @PostMapping("/change-name")
    public String changeName(
            @RequestParam String newName,
            HttpSession session,
            Model model
    ) {
        Long adminId = (Long) session.getAttribute("ADMIN_ID");
        if(adminId == null){
            return "redirect:/admin/login";
        }
        if (newName == null || newName.trim().isEmpty()){
            model.addAttribute("error", "Name can not be empty");
            return "admin-change-name";
        }

        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin == null) {
            session.invalidate();
            return "redirect:/admin/login";
        }

        admin.setUsername(newName.trim());
        adminRepository.save(admin);

        model.addAttribute("success", "Admin name updated successfully");
        return "admin-change-name";
    }

    @GetMapping("change-password")
    public String changePassword(HttpSession session){
        if(session.getAttribute("ADMIN_ID") == null) {
            return "redirect:/admin/login";
        }
        return "admin-change-password";
    }

    @PostMapping("change-password")
    public String changePassword(
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            HttpSession session,
            Model model
    ) {
        Long adminId = (Long) session.getAttribute("ADMIN_ID");
        if (adminId == null){
            return "redirect:/admin/login";
        }

        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin == null){
            session.invalidate();
            return "redirect:/admin/login";
        }

        if (!passwordEncoder.matches(currentPassword, admin.getPasswordHash())) {
            model.addAttribute("error", "Current password is incorrect.");
            return "admin-change-password";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "New passwords does not match");
            return "admin-change-password";
        }

        String passwordError = PasswordValidator.validate(newPassword);
        if (passwordError != null) {
            model.addAttribute("error", passwordError);
            return "admin-change-password";
        }

        String hashedPassword = passwordEncoder.encode(newPassword);
        admin.setPasswordHash(hashedPassword);
        adminRepository.save(admin);

        session.invalidate();
        return "redirect:/admin/login?passwordChanged=true";
    }

    @GetMapping("/status")
    public String adminStatus(HttpSession session, Model model) {
        Long adminId = (Long) session.getAttribute("ADMIN_ID");
        if (adminId == null) {
            return "redirect:/admin/login";
        }
        Admin admin = adminRepository.findById(adminId).orElse(null);
        if(admin == null) {
            session.invalidate();
            return "redirect:/admin/login";
        }
        model.addAttribute("active", admin.isActive());
        return "admin-status";
    }

    @PostMapping("/status")
    public String toggleAdminStatus(HttpSession session) {
        Long adminId = (Long) session.getAttribute("ADMIN_ID");
        if(adminId == null) {
            return "redirect:/admin/login";
        }

        Admin admin = adminRepository.findById(adminId).orElse(null);
        if (admin == null) {
            session.invalidate();
            return "redirect:/admin/login";
        }

        admin.setStatus(!admin.isActive());
        adminRepository.save(admin);

        if (!admin.isActive()) {
            session.invalidate();
            return "redirect:/admin/login?deactivated=true";
        }

        return "redirect:/admin/status";
    }

    @GetMapping("/users/create")
    public String createUsers(HttpSession session){
        if (session.getAttribute("ADMIN_ID") == null) {
            return "redirect:/admin/login";
        }
        return "admin-create-user";
    }

    @PostMapping("/users/create")
    public String createUsers(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String mobile,
            Model model,
            HttpSession session
    ) {
        Long adminId = (Long) session.getAttribute("ADMIN_ID");
        if (adminId == null) return "redirect:/admin/login";

        if (fullName.trim().isEmpty() || email.trim().isEmpty() || mobile.trim().isEmpty()) {
            model.addAttribute("error", "All fields are required");
            return "admin-create-user";
        }

        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "User with this email already exists.");
            return "admin-create-user";
        }

        if (userRepository.existsByMobile(mobile)) {
            model.addAttribute("error", "User with this mobile already exists.");
            return "admin-create-user";
        }

        User user = new User();
        user.setFullName(fullName.trim());
        user.setEmail(email.trim());
        user.setCreatedByAdminId(adminId);
        user.setStatus(User.Status.INACTIVE);
        user.setMobile(mobile.trim());
        user.setEmailVerified(false);
        user.setMobileVerified(false);
        userRepository.save(user);
        logAudit(session, "CREATED_USER", user.getId());
        model.addAttribute("success", "User created successfully, Verification pending");
        String verificationLink = "http://localhost:8080/users/verify/email?email=" + user.getEmail();

        System.out.println("\nUser verification link:");
        System.out.println(verificationLink + "\n");


        return "admin-create-user";
    }

    @GetMapping("/users")
    public String viewUsers(HttpSession session, Model model) {
        if (session.getAttribute("ADMIN_ID") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("users", userRepository.findByDeletedFalse());
        return "admin-users";
    }

    @PostMapping("/users/{id}/activate")
    public String activateUser(
            @PathVariable Long id,
            HttpSession session
    ) {
        if (session.getAttribute("ADMIN_ID") == null) {
            return "redirect:/admin/login";
        }

        userRepository.findById(id).ifPresent(user -> {
            if (user.getStatus() == User.Status.INACTIVE
                    && user.isEmailVerified()
                    && user.isMobileVerified()) {
                user.setStatus(User.Status.ACTIVE);
                logAudit(session, "ACTIVATE_USER", user.getId());
            }
            else if (user.getStatus() == User.Status.DEACTIVATED) {
                user.setStatus(User.Status.ACTIVE);
                logAudit(session, "REACTIVATE_USER", user.getId());
            }
            userRepository.save(user);
        });

        return "redirect:/admin/users";
    }



    @PostMapping("/users/{id}/deactivate")
    public String deactivateUser(
            @PathVariable Long id,
            HttpSession session
    ) {
        if (session.getAttribute("ADMIN_ID") == null) {
            return "redirect:/admin/login";
        }

        userRepository.findById(id).ifPresent(user -> {
            user.setStatus(User.Status.DEACTIVATED);
            logAudit(session, "DEACTIVATE_USER", user.getId());
            userRepository.save(user);
        });


        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id, HttpSession session) {

        if (session.getAttribute("ADMIN_ID") == null) {
            return "redirect:/admin/login";
        }

        userRepository.findById(id).ifPresent(user -> {
            user.setDeleted(true);
            user.setStatus(User.Status.DEACTIVATED);
            userRepository.save(user);

            logAudit(session, "DELETE_USER", user.getId());
        });
        return "redirect:/admin/users";
    }

    @GetMapping("/audit-logs")
    public String viewAuditLogs(HttpSession session, Model model) {
        if (session.getAttribute("ADMIN_ID") == null) {
            return "redirect:/admin/login";
        }
        model.addAttribute("logs", auditLogRepository.findAll());
        return "admin-audit-logs";
    }

    @PostMapping("/users/{id}/update")
    public String updateUser(
            @PathVariable Long id,
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String mobile,
            HttpSession session
    ) {
        Long adminId = (Long) session.getAttribute("ADMIN_ID");
        if (adminId == null) {
            return "redirect:/admin/login";
        }

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "redirect:/admin/users";
        }

        boolean identityChanged =
                !user.getEmail().equals(email) ||
                        !user.getMobile().equals(mobile);

        user.setFullName(fullName);
        user.setEmail(email);
        user.setMobile(mobile);

        if (identityChanged) {
            user.setStatus(User.Status.INACTIVE);
            String verificationLink = "http://localhost:8080/users/verify/email?email=" + user.getEmail();
            System.out.println("Link for user to verify: " + verificationLink);
            logAudit(session,
                    "UPDATED_USER_EMAIL_OR_MOBILE",
                    user.getId());
        } else {
            logAudit(session,
                    "UPDATED_USER_NAME",
                    user.getId());
        }

        userRepository.save(user);
        return "redirect:/admin/users";
    }



    //fallback for every other route
    @GetMapping("/**")
    public String handleAllOtherUrls(HttpSession session) {
        return "redirect:/admin/login";
    }

}
