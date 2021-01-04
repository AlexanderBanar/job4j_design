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
        int index = indexOf(id);
        if (index != -1) {
            mem.set(index, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int index = indexOf(id);
        if (index != -1) {
            mem.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        int index = indexOf(id);
        return (index != -1) ? mem.get(index) : null;
    }

    private int indexOf(String id) {
        int rsl = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }
}
