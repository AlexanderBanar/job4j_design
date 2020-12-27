package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class EvenIterator implements Iterator<Integer> {
    private int[] data;
    private int point = 0;

    public EvenIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return nextPoint() != -1;
    }

    @Override
    public Integer next() {
        if (nextPoint() == -1) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private int nextPoint() {
        for (int i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                break;
            } else {
                point++;
            }
        }
        return (point == data.length) ? -1 : point;
    }
}
