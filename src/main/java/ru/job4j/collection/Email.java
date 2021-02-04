package ru.job4j.collection;

import java.util.*;

public class Email {
    public List<User> compress(List<User> initial) {
        Map<String, String> map = new HashMap<>(); // Map <Email, Username>
        Map<String, User> rsl = new HashMap<>(); // Map <Username, User>
        for (User current : initial) {
            String username = null;
            for (String email : current.addresses) {
                if (map.containsKey(email)) {
                    username = map.get(email);
                    break;
                }
            }
            if (username == null) {
                rsl.put(current.name, current);
            } else {
                Set<String> mergedEmails = rsl.get(username).addresses;
                mergedEmails.addAll(current.addresses);
                rsl.put(username, new User(username, mergedEmails));
            }
            username = (username == null) ? current.name : username;
            for (String email : current.addresses) {
                map.putIfAbsent(email, username);
            }
        }
        return new ArrayList<>(rsl.values());
    }

    public static class User {
        String name;
        Set<String> addresses;

        public User(String name, Set<String> addresses) {
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
