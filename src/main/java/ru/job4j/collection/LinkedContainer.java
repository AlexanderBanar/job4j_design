package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class LinkedContainer<E> implements Iterable<E> {
    private Node<E> node;
    private int size = 0;
    int modCount = 0;

    public void add(E value) {
        if (node == null) {
            node = new Node<>(null, value, null);
        } else {
            Node<E> temp = new Node(node, value, null);
            node = temp;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) {
            return node.item;
        } else {
            Node<E> rsl = node;
            for (int i = 0; i < index; i++) {
                rsl = rsl.next;
            }
            return rsl.item;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Node<E> temp = node;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return temp.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> rsl = temp;
                temp = temp.next;
                return rsl.item;
            }
        };
    }
}
