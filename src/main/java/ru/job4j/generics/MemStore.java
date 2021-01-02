package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        T item = findById(id);
        if (item != null) {
            mem.set(mem.indexOf(item), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        T item = findById(id);
        if (item != null) {
            return mem.remove(item);
        }
        return false;
    }

    @Override
    public T findById(String id) {
        return mem.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
