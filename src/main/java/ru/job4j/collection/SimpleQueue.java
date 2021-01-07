package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T value;
        while (in.getSize() > 1) {
            out.push(in.pop());
        }
        value = in.pop();
        while (out.getSize() > 0) {
            in.push(out.pop());
        }
        return value;
    }

    public void push(T value) {
        in.push(value);
    }
}
