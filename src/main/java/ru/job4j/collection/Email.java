package ru.job4j.collection;

import java.util.*;

/**
 * Создал мапу для того, чтобы в нее закидывать все эл. адреса как ключи со значением индекса юзера из входящего листа
 *
 * лист removals хранит индексы всех юзеров, которые были соединены с исходным юзером - для последующего удаления
 *
 * перем. toMerge становится true, когда выпадает истина из проверки содержит ли мапа уже данный адрес
 * далее по циклу toMerge перекидывает все адреса исходного и дублирующего пользователя в Сет, чтобы удалить дубли
 * и сливает в один список данные адреса, затем создает пользователя и переписывает им исходного, также заносит
 * в лист removals индекс юзера, который далее удаляется из возвращаемого списка в методе
 *
 * перем. reDo начинает заново цикл уже со "знанием", что исходный пользователь уже имеется и нужно адреса
 * текущего закидывать в мапу с индексом исходного, а не текущего
 */

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

//        Map<String, User> map = new HashMap<>();
//
//        for (int i = 0; i < initial.size(); i++) {
//            User user = initial.get(i);
//            List<String> tempEmails = user.addresses;
//
//            for (int k = 0; k < tempEmails.size(); k++) {
//                String n = tempEmails.get(k);
//
//                if (!map.containsKey(n)) {
//                    map.put(n, user);
//                } else {
//                    user = map.get(n);
//                    k = 0;
//                    continue;
//                }
//
//
//
//            }
//
//
//        }

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
