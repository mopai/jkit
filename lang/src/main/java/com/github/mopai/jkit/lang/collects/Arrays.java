package com.github.mopai.jkit.lang.collects;

import com.github.mopai.jkit.lang.constants.ArrayConstant;

import java.lang.reflect.Array;

public final class Arrays implements ArrayConstant {
    private Arrays() {

    }

    public static boolean isArray(final Object obj) {
        if (null == obj) {
            return false;
        }
        return obj.getClass().isArray();
    }

    public static int getLength(final Object obj) {
        if (null == obj) {
            return 0;
        }
        return Array.getLength(obj);
    }

    public static boolean isEmpty(final Object obj) {
        if (null == obj) {
            return true;
        }
        if (!isArray(obj)) {
            return false;
        }
        return 0 == getLength(obj);
    }
}
