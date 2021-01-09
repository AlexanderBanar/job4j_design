package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    SimpleArray<T> support = new SimpleArray<>();

    public void add(T element) {
        for (Object n : support.getData()) {
            T temp = (T) n;
            if (temp.equals(element)) {
                return;
            }
        }
        support.add(element);
    }

    @Override
    public Iterator<T> iterator() {
        return support.iterator();
    }
}
