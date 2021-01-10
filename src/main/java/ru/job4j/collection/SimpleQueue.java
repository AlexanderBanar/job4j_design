package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in.getSize() == 0 && isEmpty()) {
            throw new NoSuchElementException();
        }
        if (isEmpty()) {
            while (in.getSize() != 0) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    private boolean isEmpty() {
        return out.getSize() == 0;
    }

    public void push(T value) {
        in.push(value);
    }
}
