package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> support = new SimpleArray<>();

    public void add(T element) {
        if (!contains(element)) {
            support.add(element);
        }
    }

    private boolean contains(T element) {
        for (Object n : support.getData()) {
            T temp = (T) n;
            if (Objects.equals(temp, element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return support.iterator();
    }
}
