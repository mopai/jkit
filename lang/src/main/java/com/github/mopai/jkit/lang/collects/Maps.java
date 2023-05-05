package com.github.mopai.jkit.lang.collects;

import com.github.mopai.jkit.lang.utils.IntegerUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class Maps {
    private Maps() {
    }

    // region [HashMap]
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
        return new HashMap<K, V>(map);
    }

    public static <K, V> HashMap<K, V> newHashMapWithCapacity(int initialCapacity) {
        checkNonnegative(initialCapacity, "initialCapacity");
        return new HashMap<K, V>(initialCapacity);
    }

    public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int expectedSize) {
        checkNonnegative(expectedSize, "expectedSize");
        return new HashMap<K, V>(capacity(expectedSize));
    }
    // endregion

    // region [LinkedHashMap]
    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<K, V>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
        return new LinkedHashMap<K, V>(map);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithCapacity(int initialCapacity) {
        checkNonnegative(initialCapacity, "initialCapacity");
        return new LinkedHashMap<K, V>(initialCapacity);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int expectedSize) {
        checkNonnegative(expectedSize, "expectedSize");
        return new LinkedHashMap<K, V>(capacity(expectedSize));
    }
    // endregion

    // region [ConcurrentMap]
    public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
        return new ConcurrentHashMap<K, V>();
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentMap(Map<? extends K, ? extends V> map) {
        return new ConcurrentHashMap<K, V>(map);
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentMapWithCapacity(int initialCapacity) {
        checkNonnegative(initialCapacity, "initialCapacity");
        return new ConcurrentHashMap<K, V>(initialCapacity);
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentMapWithExpectedSize(int expectedSize) {
        checkNonnegative(expectedSize, "expectedSize");
        return new ConcurrentHashMap<K, V>(capacity(expectedSize));
    }
    // endregion

    // region [synchronizedMap]
    public static <K, V> Map<K, V> s(Map<K, V> map) {
        return Collections.synchronizedMap(map);
    }
    // endregion

    // region [Assistant]
    private static int capacity(int expectedSize) {
        if (expectedSize < 3) {
            return 4;
        }
        if (expectedSize < IntegerUtils.MAX_POWER_OF_TWO) {
            return (int) ((float) expectedSize / 0.75F + 1.0F);
        }
        return Integer.MAX_VALUE;
    }

    private static void checkNonnegative(int value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException(name + "cannot be negative but was: " + value);
        }
    }
    // endregion
}
