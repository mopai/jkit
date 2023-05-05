package com.github.mopai.jkit.lang.utils;

public final class Utf8Utils {
    private static final int LAST_BMP = 0xFFFF;

    private Utf8Utils() {

    }

    public static String filterMb4(final String str) {
        StringBuilder builder = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            int codePoint = str.codePointAt(i);
            if (codePoint < LAST_BMP) {
                builder.appendCodePoint(codePoint);
            } else {
                i++;
            }
        }
        return builder.toString();
    }
}
