package ru.job4j.collection;

public class Node<E> {
    public E item;
    public Node<E> next;
    public Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
