package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in.getSize() == 0) {
            throw new NoSuchElementException();
        }
        T value = null;
        int reduced = in.getSize() - 1;
        while (in.getSize() != reduced) {
            if (in.getSize() > 1) {
                out.push(in.pop());
            }
            if (in.getSize() == 1) {
                value = in.pop();
            }
            if (out.getSize() > 0) {
                in.push(out.pop());
            }
        }
        return value;
    }

    public void push(T value) {
        in.push(value);
    }
}
