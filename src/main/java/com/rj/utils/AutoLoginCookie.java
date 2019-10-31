package com.rj.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class AutoLoginCookie {
    public static void loginCookie(String key, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
    }

    public static void logoutCookie(String key, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
