package br.com.xptosystems.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean email(String email) {
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }
}
