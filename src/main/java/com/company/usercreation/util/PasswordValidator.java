package com.company.usercreation.util;

public class PasswordValidator {

    public static String validate(String password) {
        if (password == null){
            return "Password can not be empty";
        }
        if (password.length() < 8){
            return "Password can not be smaller than 8 charecters";
        }

        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for(char c : password.toCharArray()){
            if (Character.isLetter(c)) hasLetter = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }

        if (!hasLetter) return "Password must contain at least one letter";
        if (!hasDigit) return "Password must contain at least one digit";
        if (!hasSpecial) return "Password must contain at least one special charecter";

        return null;
    }
}