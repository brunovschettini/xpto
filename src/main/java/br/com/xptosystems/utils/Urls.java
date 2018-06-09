package br.com.xptosystems.utils;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Urls {

    private static String URL;

    public static String getURL() {
        return URL;
    }
    
    public static String getString(String queryString) {
        return URL + queryString;
    }

}
