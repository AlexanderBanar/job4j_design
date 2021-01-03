package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private int size = 10;
    private Object[] data = new Object[size];
    int modCount = 0;
    private int counter = 0;

    public T get(int index) {
        Objects.checkIndex(index, counter);
        return (T) data[index];
    }

    public void add(T model) {
        if (counter >= data.length) {
            size += 5;
            data = new Object[size];
        }
        data[counter++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Object[] array = data;
            private int point = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < counter;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[point++];
            }
        };
    }
}
