package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data = new Object[10];
    private int counter = 0;

    public void add(T model) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                data[i] = model;
                counter++;
            }
        }
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, counter);
        data[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, counter);
        if (index == 9) {
            data[9] = null;
        } else {
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
                return point < array.length;
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
