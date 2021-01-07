package ru.job4j.collection;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();
    private int size = 0;

    public T pop() {
        size--;
        return linked.deleteLast();
    }

    public T poll() {
        size--;
        return linked.deleteFirst();
    }

    public void push(T value) {
        size++;
        linked.add(value);
    }

    public int getSize() {
        return size;
    }
}
