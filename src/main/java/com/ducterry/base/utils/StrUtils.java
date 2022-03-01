package com.ducterry.base.utils;

import com.ducterry.base.commons.constant.AuthConstants;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Random;
import java.util.UUID;

public class StrUtils {
    public static String randomString() {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(alphaNumeric.length());
            char randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String UUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return org.apache.commons.lang3.StringUtils.upperCase(uuid);
    }


    public static String passWord(String passWord) {
        return BCrypt.hashpw(passWord, BCrypt.gensalt(AuthConstants.LOG_ROUNDS));
    }

    public static boolean checkPass(String rqPass,String userPass) {
        return BCrypt.checkpw(rqPass, userPass);
    }
}
