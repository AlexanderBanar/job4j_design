package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked2<T> implements Iterable<T> {
    private Node<T> head;
    private int size = 0;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public void revert() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        ForwardLinked2<T> temp = new ForwardLinked2<>();
        while (size > 0) {
            T value = deleteLast();
            temp.add(value);
        }
        this.head = temp.head;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T temp;
        if (size > 1) {
            Node<T> rsl = head;
            int del = 0;
            while (del < size - 2) {
                rsl = rsl.next;
                del++;
            }
            temp = rsl.next.value;
            rsl.next = null;
        } else {
            temp = head.value;
            head = null;
        }
        size--;
        return temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
