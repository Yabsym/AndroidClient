package com.riskm.androidclient.util;

public class SessionRecord {
    private static String cookie;

    public static String getCookie() {
        return cookie;
    }

    public static void setCookie(String cookie) {
        SessionRecord.cookie = cookie;
    }

    public static String getCookieID(RealResponse realResponse) {
        String cookieDat = realResponse.cookies.get("Set-Cookie").toString();
        cookieDat = cookieDat.substring(1, cookieDat.indexOf(";"));
        return cookieDat;

    }
}
