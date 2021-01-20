package ru.job4j.collection;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {
    public Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info(0, 0, 0);
        Map<Integer, User> prev = previous.stream()
                .collect(Collectors.toMap(
                        x -> x.id,
                        x -> x
                ));
        Map<Integer, User> curr = current.stream()
                .collect(Collectors.toMap(
                        x -> x.id,
                        x -> x
                ));
        Set<Integer> prevSet = prev.keySet();
        for (Integer temp : prevSet) {
            if (curr.containsKey(temp)) {
                User p = prev.get(temp);
                User c = curr.get(temp);
                if (!p.name.equals(c.name)) {
                    rsl.changed++;
                }
            } else {
                rsl.deleted++;
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
