package ru.job4j.collection;

import java.util.*;

public class Email {
    public List<User> compress(List<User> initial) {
        Map<String, String> map = new HashMap<>();
        Map<String, User> rsl = new HashMap<>();
        for (int i = 0; i < initial.size(); i++) {
            User user = initial.get(i);
            List<String> mails = user.addresses;
            List<String> newMails = mails;
            for (int k = 0; k < mails.size(); k++) {
                String n = mails.get(k);
                if (!map.containsKey(n)) {
                    map.put(n, user.name);
                } else {
                    String usernameToDel = map.get(n);
                    if (rsl.containsKey(usernameToDel)) {
                        User toDel = rsl.get(usernameToDel);
                        List<String> mailsToDel = toDel.addresses;
                        for (String mail : mailsToDel) {
                            map.put(mail, user.name);
                            if (!mail.equals(n)) {
                                newMails.add(mail);
                            }
                        }
                    }
                    rsl.remove(usernameToDel);
                }
            }
            rsl.put(user.name, new User(user.name, newMails));
        }
        return new ArrayList<>(rsl.values());
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
