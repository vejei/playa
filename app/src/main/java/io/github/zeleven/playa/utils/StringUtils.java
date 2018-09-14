package io.github.zeleven.playa.utils;

import android.text.TextUtils;

public class StringUtils {
    public static boolean isEmpty(String text) {
        return text == null || TextUtils.isEmpty(text) || isWhiteSpaces(text)
                || text.equalsIgnoreCase("null");
    }

    private static boolean isWhiteSpaces(String s) {
        return s != null && s.matches("\\s+");
    }
}
