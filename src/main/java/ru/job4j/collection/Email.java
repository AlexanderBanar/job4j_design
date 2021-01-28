package ru.job4j.collection;

import java.util.*;

public class Email {
    public List<User> compress(List<User> initial) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> removals = new ArrayList<>();
        boolean toMerge = false;
        boolean reDo = false;
        int index = 0;
        for (int i = 0; i < initial.size(); i++) {
            List<String> tempEmails = initial.get(i).addresses;
            for (int k = 0; k < tempEmails.size(); k++) {
                String n = tempEmails.get(k);
                if (!reDo) {
                    if (map.containsKey(n)) {
                        toMerge = true;
                        index = map.get(n);
                        k = 0;
                        reDo = true;
                        continue;
                    } else {
                        map.put(n, i);
                    }
                } else {
                    map.put(n, index);
                }
            }
            reDo = false;
            if (toMerge) {
                User existing = initial.get(index);
                Set<String> modifiedSet = new HashSet<>();
                modifiedSet.addAll(existing.addresses);
                modifiedSet.addAll(tempEmails);
                List<String> modifiedList = new ArrayList<>(modifiedSet);
                initial.set(index, new User(existing.name, modifiedList));
                removals.add(i);
                toMerge = false;
            }
        }
        int r = 0;
        for (Integer i : removals) {
            initial.remove((r + i));
            r--;
        }
        return initial;
    }


    public static class User {
        String name;
        List<String> addresses;

        public User(String name, List<String> addresses) {
            this.name = name;
            this.addresses = addresses;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ru.job4j.collection.Email.User user = (ru.job4j.collection.Email.User) o;
            return Objects.equals(name, user.name)
                    && Objects.equals(addresses, user.addresses);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, addresses);
        }
    }
}
