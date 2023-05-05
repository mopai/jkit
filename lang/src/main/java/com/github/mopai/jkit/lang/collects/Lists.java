package com.github.mopai.jkit.lang.collects;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Lists {
    private Lists() {
    }

    // region [ArrayList]
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    public static <E> ArrayList<E> newArrayList(E... elements) {
        if (null == elements) {
            return newArrayList();
        }
        ArrayList<E> list = newArrayListWithExpectedSize(elements.length);
        Collections.addAll(list, elements);
        return list;
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> iterator) {
        ArrayList<E> list = newArrayList();
        Iterators.addAll(list, iterator);
        return list;
    }

    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> iterable) {
        if (null == iterable) {
            return newArrayList();
        }
        if (iterable instanceof Collection) {
            return new ArrayList<E>((Collection) iterable);
        } else {
            return newArrayList(iterable.iterator());
        }
    }

    public static <E> ArrayList<E> newArrayListWithCapacity(int initialCapacity) {
        checkNonnegative(initialCapacity, "initialCapacity");
        return new ArrayList<>(initialCapacity);
    }

    public static <E> ArrayList<E> newArrayListWithExpectedSize(int expectedSize) {
        checkNonnegative(expectedSize, "expectedSize");
        return new ArrayList<>(capacity(expectedSize));
    }
    // endregion

    // region [LinkedList]
    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    public static <E> LinkedList<E> newLinkedList(E... elements) {
        if (null == elements) {
            return newLinkedList();
        }
        LinkedList<E> list = newLinkedList();
        Collections.addAll(list, elements);
        return list;
    }

    public static <E> LinkedList<E> newLinkedList(Iterator<? extends E> iterator) {
        LinkedList<E> list = newLinkedList();
        Iterators.addAll(list, iterator);
        return list;
    }

    public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> iterable) {
        if (null == iterable) {
            return newLinkedList();
        }
        if (iterable instanceof Collection) {
            return new LinkedList<E>((Collection) iterable);
        } else {
            return newLinkedList(iterable.iterator());
        }
    }
    // endregion

    // region [CopyOnWriteArrayList]
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(E... elements) {
        if (null == elements) {
            return newCopyOnWriteArrayList();
        }
        CopyOnWriteArrayList<E> list = newCopyOnWriteArrayList();
        Collections.addAll(list, elements);
        return list;
    }

    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterator<? extends E> iterator) {
        CopyOnWriteArrayList<E> list = newCopyOnWriteArrayList();
        Iterators.addAll(list, iterator);
        return list;
    }

    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterable<? extends E> iterable) {
        if (null == iterable) {
            return newCopyOnWriteArrayList();
        }
        if (iterable instanceof Collection) {
            return new CopyOnWriteArrayList<E>((Collection) iterable);
        } else {
            return newCopyOnWriteArrayList(iterable.iterator());
        }
    }
    // endregion

    // region [SynchronizedList]
    public static <T> List<T> synchronizedList(List<T> list) {
        return Collections.synchronizedList(list);
    }
    // endregion

    // region [Assistant]
    private static int capacity(int expectedSize) {
        long capacity = 5L + expectedSize + (expectedSize / 10);
        if (capacity > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) capacity;
    }

    private static void checkNonnegative(int value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException(name + "cannot be negative but was: " + value);
        }
    }
    // endregion
}
