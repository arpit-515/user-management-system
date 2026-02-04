package com.company.usercreation.util;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Base64;

public class TokenUtil{
    private static final SecureRandom random = new SecureRandom();
    private static final Base64.Encoder base64Encoder =
            Base64.getUrlEncoder().withoutPadding();

    public static String generateToken() {
        byte[] randomBytes = new byte[32];
        random.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public static String generateOtp() {
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static String generateHash(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}