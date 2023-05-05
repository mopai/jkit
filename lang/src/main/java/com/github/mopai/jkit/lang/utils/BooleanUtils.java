package com.github.mopai.jkit.lang.utils;

import com.github.mopai.jkit.lang.constants.BooleanConstant;

public final class BooleanUtils implements BooleanConstant {
    private BooleanUtils() {
    }

    public static boolean isTrue(Boolean b) {
        return Boolean.TRUE.equals(b);
    }

    public static boolean isFalse(Boolean b) {
        return Boolean.FALSE.equals(b);
    }

    public static boolean and(Boolean... bs) {
        for (Boolean b : bs) {
            if (!isTrue(b)) {
                return false;
            }
        }
        return true;
    }

    public static boolean or(Boolean... bs) {
        for (Boolean b : bs) {
            if (isTrue(b)) {
                return true;
            }
        }
        return false;
    }

    public static boolean valueOf(String str) {
        if (StringUtils.isNotBlank(str)) {
            return TRUE_SET.contains(str.trim().toLowerCase());
        }
        return false;
    }
}
