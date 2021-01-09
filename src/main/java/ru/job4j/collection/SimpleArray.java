package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data = new Object[10];
    int modCount = 0;
    private int counter = 0;

    public T get(int index) {
        Objects.checkIndex(index, counter);
        return (T) data[index];
    }

    public void add(T model) {
        if (counter >= data.length) {
            extend();
        }
        data[counter++] = model;
        modCount++;
    }

    public Object[] getData() {
        return data;
    }

    private void extend() {
        data = Arrays.copyOf(data, data.length * 2);
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
