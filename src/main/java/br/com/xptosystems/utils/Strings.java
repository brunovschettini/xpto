package br.com.xptosystems.utils;

public class Strings {

    public static String converterNullToString(Object object) {
        if (object == null) {
            return "";
        } else {
            return String.valueOf(object);
        }
    }

    public static String extractNumbers(String string) {
        String documento = "";
        int i = 0;
        while (i < string.length()) {
            String as = string.substring(i, i + 1);
            if (!as.equals(".") && !as.equals("-") && !as.equals("/")) {
                documento = documento + as;
            }
            i++;
        }
        return documento;
    }
}
