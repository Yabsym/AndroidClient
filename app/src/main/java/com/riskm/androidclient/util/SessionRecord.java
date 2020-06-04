package com.riskm.androidclient.util;

import java.util.HashMap;
import java.util.Map;

public class SessionRecord {
    private static String cookie;

    public static String getCookie() {
        return cookie;
    }

    public static void setCookie(String cookie) {
        SessionRecord.cookie = cookie;
    }

    public static Map<String, String> getMapCookie() {
        Map<String, String> headMap = new HashMap<>();
        headMap.put("Cookie", SessionRecord.getCookie());
        return headMap;
    }

    public static String getCookieID(RealResponse realResponse) {
        String cookieDat = realResponse.cookies.get("Set-Cookie").toString();
        cookieDat = cookieDat.substring(1, cookieDat.indexOf(";"));
        return cookieDat;

    }
}
