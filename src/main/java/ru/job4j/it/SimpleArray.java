package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data = new Object[10];
    private int counter = 0;

    public void add(T model) {
        if (counter < data.length) {
            data[counter++] = model;
        }
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, counter);
        data[index] = model;
    }

    public void remove(int index) {
        if (Objects.checkIndex(index, counter) == index) {
            data[index] = null;
        }
        if (index != counter - 1) {
            Object[] temp = data;
            System.arraycopy(data, index + 1, temp, index, counter - 1 - index);
            data = temp;
        }
    }

    public Object get(int index) {
        Objects.checkIndex(index, counter);
        return data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Object[] array = data;
            private int point = 0;

            @Override
            public boolean hasNext() {
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
