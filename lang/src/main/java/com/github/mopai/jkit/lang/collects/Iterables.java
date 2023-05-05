package com.github.mopai.jkit.lang.collects;

import java.util.Collection;

public final class Iterables {
    private Iterables() {
    }

    public static <T> boolean addAll(Collection<T> addTo, Iterable<? extends T> iterable) {
        assert addTo != null;
        assert iterable != null;
        if (iterable instanceof Collection) {
            Collection<T> collection = (Collection<T>) iterable;
            return addTo.addAll(collection);
        }
        return Iterators.addAll(addTo, iterable.iterator());
    }
}
