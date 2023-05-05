package com.github.mopai.jkit.lang.collects;

import java.util.Collection;
import java.util.Iterator;

public final class Iterators {
    private Iterators() {
    }

    public static <T> boolean addAll(Collection<T> addTo, Iterator<? extends T> iterator) {
        assert addTo != null;
        assert iterator != null;
        boolean wasModified = false;
        while (iterator.hasNext()) {
            wasModified |= addTo.add(iterator.next());
        }
        return wasModified;
    }
}
