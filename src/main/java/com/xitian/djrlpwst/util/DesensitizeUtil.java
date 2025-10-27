package com.xitian.djrlpwst.util;

public class DesensitizeUtil {

    private DesensitizeUtil() {}

    /** 手机号: 132****5678 */
    public static String phone(String src) {
        if (src == null || src.length() != 11) return src;
        return src.substring(0, 3) + "****" + src.substring(7);
    }

    /** 邮箱: a***b@qq.com */
    public static String email(String src) {
        if (src == null) return null;
        int at = src.indexOf('@');
        if (at <= 1) return src;
        return src.charAt(0) + "***" + src.substring(at - 1);
    }
}