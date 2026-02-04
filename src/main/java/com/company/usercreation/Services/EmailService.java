package com.company.usercreation.Services;

import com.company.usercreation.model.OtpToken.Purpose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String FROM_EMAIL = "info@optotechconsultancy.com";
    private static final String APP_NAME = "Automated Harness Test System";


    @Async
    public void sendOtp(String to, String otp) {

        String subject;
        String body;
        subject = "OTP to reset your password";
        body = "You requested to reset your password for " + APP_NAME + ".\n\n" +
                "Your One-Time Password (OTP) is:\n\n" +
                otp + "\n\n" +
                "This OTP is valid for 10 minutes.\n\n" +
                "Do not share this OTP with anyone.\n\n" +
                "If you did not request this, please ignore this email.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(FROM_EMAIL);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }


    @Async
    public void sendLink(String to, String link, Purpose purpose) {

        String subject;
        String body = switch (purpose) {
            case INVITATION -> {
                subject = "Your account has been created";
                yield "An administrator has created an account for you on " + APP_NAME + ".\n\n" +
                        "To activate your account and set your password, please verify your email address " +
                        "by clicking the link below:\n\n" +
                        link + "\n\n" +
                        "This link will expire in 48 hours for security reasons.\n\n" +
                        "If you were not expecting this email, you can safely ignore it.";
            }
            case EMAIL_VERIFICATION -> {
                subject = "Verify your email address";
                yield "Your email address was recently updated on " + APP_NAME + ".\n\n" +
                        "Please verify this email address by clicking the link below:\n\n" +
                        link + "\n\n" +
                        "This link will expire in 48 hours.\n\n" +
                        "If you did not request this change, please contact your administrator.";
            }
            case MOBILE_VERIFICATION -> {
                subject = "Verify your mobile number";
                yield "Your mobile number was recently updated on " + APP_NAME + ".\n\n" +
                        "Please verify this mobile number by clicking the link below:\n\n" +
                        link + "\n\n" +
                        "This link will expire in 48 hours.\n\n" +
                        "If you did not request this change, please contact your administrator.";
            }
            default -> throw new IllegalArgumentException(
                    "Verification link not supported for purpose: " + purpose
            );
        };

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(FROM_EMAIL);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.println("SENT EMAIL");
    }

    @Async
    public void sendConfirmation(String to, String purpose) {

        String subject;
        String body;
        subject = "Confirmation for " + purpose;
        body = "This is to inform you that your " + purpose + " for " + APP_NAME + ".\n\n" +
                "Has been completed\n\n" +
                "If you did not requested this, please contact your administrator.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(FROM_EMAIL);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

}
