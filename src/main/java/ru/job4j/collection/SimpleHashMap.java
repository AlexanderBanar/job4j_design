package ru.job4j.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<Map.Entry<K, V>> {
    private int size;
    private float load;
    private int counter = 0;
    private Node[] data = new Node[size];

    public SimpleHashMap() {
        this.size = 16;
        this.load = 0.75F;
    }

    public SimpleHashMap(int size) {
        this.size = size;
    }

    public SimpleHashMap(int size, float load) {
        this.size = size;
        this.load = load;
    }

    public boolean insert(K key, V value) {
        checkSize();
        int index = getIndex(key);
        if (data[index] != null) {
            return false;
        }
        data[index] = new Node<K, V>(key, value);
        counter++;
        return true;
    }

    public V get(K key) {
        Node<K, V> rsl = data[getIndex(key)];
        return (rsl == null) ? null : rsl.value;
    }

    public boolean delete(K key) {
        if (data[getIndex(key)] == null) {
            return false;
        }
        if (!data[getIndex(key)].key.equals(key)) {
            return false;
        }
        data[getIndex(key)] = null;
        counter--;
        return true;
    }

    private static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int getIndex(K key) {
        return key.hashCode() % size;
    }

    private void checkSize() {
        if (size * load > counter) {
            return;
        }
        size *= 1.5;
        SimpleHashMap<K, V> temp = new SimpleHashMap<>(size);
        for (Node n : data) {
            if (n != null) {
                K key = (K) n.key;
                V value = (V) n.value;
                temp.insert(key, value);
            }
        }
        this.data = temp.data;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<Map.Entry<K, V>>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (index < size) {
                    for (int i = index; i < size; i++) {
                        if (data[i] != null) {
                            break;
                        }
                        index++;
                    }
                }
                return index < size;
            }

            @Override
            public Map.Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<K, V> result = data[index];
                return new Map.Entry<K, V>() {
                    @Override
                    public K getKey() {
                        return result.key;
                    }

                    @Override
                    public V getValue() {
                        return result.value;
                    }

                    @Override
                    public V setValue(V value) {
                        return result.value;
                    }
                };
            }
        };
    }
}
