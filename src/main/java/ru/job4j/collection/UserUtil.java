package ru.job4j.collection;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserUtil {
    public static void main(String[] args) {
        User first = new User("Ivan", 2, new GregorianCalendar(1987, 2, 2));
        User second = new User("Ivan", 3, new GregorianCalendar(1987, 2, 2));
        Map<User, Object> tester = new HashMap<>(Map.of(first, "First", second, "Second"));
        System.out.println(tester);
    }
}
