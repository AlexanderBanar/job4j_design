package ru.job4j.collection;

import java.util.List;

public class Analyze {
    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info(0, 0, 0);
        for (User prev : previous) {
            for (int i = 0; i < current.size(); i++) {
                User curr = current.get(i);
                if (prev.id == curr.id) {
                    if (!prev.name.equals(curr.name)) {
                        rsl.changed++;
                    }
                    break;
                }
                if (i == current.size() - 1) {
                    rsl.deleted++;
                }
            }
        }
        rsl.added = current.size() - previous.size() + rsl.deleted;
        return rsl;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }
}
