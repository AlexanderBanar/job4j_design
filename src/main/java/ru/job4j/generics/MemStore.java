package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();
    private int index = 0;

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        T item = findById(id);
        if (item != null) {
            mem.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        T item = findById(id);
        if (item != null) {
            mem.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        T item = null;
        for (int i = 0; i < mem.size(); i++) {
            item = mem.get(i);
            if (item.getId().equals(id)) {
                index = i;
                break;
            }
        }
        return item;
    }
}
