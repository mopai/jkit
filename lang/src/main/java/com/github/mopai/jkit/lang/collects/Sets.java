package com.github.mopai.jkit.lang.collects;

import com.github.mopai.jkit.lang.utils.IntegerUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public final class Sets {
    private Sets() {
    }

    // region [HashSet]
    public static <E> HashSet<E> newHashSet() {
        return new HashSet<E>();
    }

    public static <E> HashSet<E> newHashSet(E... elements) {
        HashSet<E> set = newHashSetWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> HashSet<E> newHashSet(Iterator<? extends E> iterator) {
        HashSet<E> set = newHashSet();
        Iterators.addAll(set, iterator);
        return set;
    }

    public static <E> HashSet<E> HashSet(Iterable<? extends E> iterable) {
        if (null == iterable) {
            return newHashSet();
        }
        if (iterable instanceof Collection) {
            return new HashSet<E>((Collection) iterable);
        } else {
            return newHashSet(iterable.iterator());
        }
    }

    public static <E> HashSet<E> newHashSetWithCapacity(int initialCapacity) {
        checkNonnegative(initialCapacity, "initialCapacity");
        return new HashSet<E>(initialCapacity);
    }

    public static <E> HashSet<E> newHashSetWithExpectedSize(int expectedSize) {
        checkNonnegative(expectedSize, "expectedSize");
        return new HashSet<E>(capacity(expectedSize));
    }
    // endregion

    // region [LinkedHashSet]
    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<E>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(E... elements) {
        LinkedHashSet<E> set = newLinkedHashSetWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterator<? extends E> iterator) {
        LinkedHashSet<E> set = newLinkedHashSet();
        Iterators.addAll(set, iterator);
        return set;
    }

    public static <E> LinkedHashSet<E> LinkedHashSet(Iterable<? extends E> iterable) {
        if (null == iterable) {
            return newLinkedHashSet();
        }
        if (iterable instanceof Collection) {
            return new LinkedHashSet<E>((Collection) iterable);
        } else {
            return newLinkedHashSet(iterable.iterator());
        }
    }

    public static <E> LinkedHashSet<E> newLinkedHashSetWithCapacity(int initialCapacity) {
        checkNonnegative(initialCapacity, "initialCapacity");
        return new LinkedHashSet<E>(initialCapacity);
    }

    public static <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int expectedSize) {
        checkNonnegative(expectedSize, "expectedSize");
        return new LinkedHashSet<E>(capacity(expectedSize));
    }
    // endregion

    // region [ConcurrentSet]
    public static <E> Set<E> newConcurrentSet() {
        return Collections.newSetFromMap(Maps.newConcurrentMap());
    }

    public static <E> Set<E> newConcurrentSet(E... elements) {
        Set<E> set = newConcurrentHashSetWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> Set<E> newConcurrentSet(Iterator<? extends E> iterator) {
        Set<E> set = newConcurrentSet();
        Iterators.addAll(set, iterator);
        return set;
    }

    public static <E> Set<E> newConcurrentSet(Iterable<? extends E> iterable) {
        if (null == iterable) {
            return newConcurrentSet();
        }
        return newConcurrentSet(iterable.iterator());
    }

    public static <E> Set<E> newConcurrentHashSetWithCapacity(int initialCapacity) {
        checkNonnegative(initialCapacity, "initialCapacity");
        return Collections.newSetFromMap(Maps.newConcurrentMapWithCapacity(initialCapacity));
    }

    public static <E> Set<E> newConcurrentHashSetWithExpectedSize(int expectedSize) {
        checkNonnegative(expectedSize, "expectedSize");
        return Collections.newSetFromMap(Maps.newConcurrentMapWithExpectedSize(expectedSize));
    }
    // endregion

    // region [TreeSet]
    public static <E extends Comparable<?>> TreeSet<E> newTreeSet() {
        return new TreeSet<E>();
    }

    public static <E extends Comparable<?>> TreeSet<E> newTreeSet(E... elements) {
        TreeSet<E> set = newTreeSet();
        Collections.addAll(set, elements);
        return set;
    }

    public static <E extends Comparable<?>> TreeSet<E> newTreeSet(Iterator<? extends E> iterator) {
        TreeSet<E> set = newTreeSet();
        Iterators.addAll(set, iterator);
        return set;
    }

    public static <E extends Comparable<?>> TreeSet<E> newTreeSet(Iterable<? extends E> iterable) {
        if (null == iterable) {
            return newTreeSet();
        }
        if (iterable instanceof Collection) {
            return new TreeSet<E>((Collection) iterable);
        } else {
            return newTreeSet(iterable.iterator());
        }
    }


    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator) {
        assert comparator != null;
        return new TreeSet<E>(comparator);
    }

    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator, E... elements) {
        TreeSet<E> set = newTreeSet(comparator);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator, Iterator<? extends E> iterator) {
        TreeSet<E> set = newTreeSet(comparator);
        Iterators.addAll(set, iterator);
        return set;
    }

    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator, Iterable<? extends E> iterable) {
        TreeSet<E> set = newTreeSet(comparator);
        Iterables.addAll(set, iterable);
        return set;
    }


    // endregion

    // region [CopyOnWriteArraySet]
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<E>();
    }

    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet(E... elements) {
        CopyOnWriteArraySet<E> set = newCopyOnWriteArraySet();
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet(Iterator<? extends E> iterator) {
        CopyOnWriteArraySet<E> set = newCopyOnWriteArraySet();
        Iterators.addAll(set, iterator);
        return set;
    }

    public static <E> CopyOnWriteArraySet<E> CopyOnWriteArraySet(Iterable<? extends E> iterable) {
        if (null == iterable) {
            return newCopyOnWriteArraySet();
        }
        if (iterable instanceof Collection) {
            return new CopyOnWriteArraySet<E>((Collection) iterable);
        } else {
            return newCopyOnWriteArraySet(iterable.iterator());
        }
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
