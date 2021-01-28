package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class EmailTest {
    @Test
    public void whenNoChanges() {
        Email.User user1 = new Email.User("user1", new ArrayList<>(List.of("a", "b", "c")));
        Email.User user2 = new Email.User("user2", new ArrayList<>(List.of("d", "e")));
        List<Email.User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        List<Email.User> rsl = new Email().compress(list);
        assertThat(rsl, is(list));
    }

    @Test
    public void when1UserHasSameMails() {
        Email.User user1 = new Email.User("user1", new ArrayList<>(List.of("a", "b", "c")));
        Email.User user2 = new Email.User("user2", new ArrayList<>(List.of("d", "e")));
        Email.User user3 = new Email.User("user3", new ArrayList<>(List.of("c", "n")));
        Email.User user4 = new Email.User("user3", new ArrayList<>(List.of("a", "b", "c", "n")));
        List<Email.User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        List<Email.User> listCompressed = new ArrayList<>();
        listCompressed.add(user2);
        listCompressed.add(user4);
        List<Email.User> rsl = new Email().compress(list);
        assertThat(rsl, is(listCompressed));
    }

    @Test
    public void when2UserHasSameMailsAndDuplicatesMails() {
        Email.User user1 = new Email.User("user1", new ArrayList<>(List.of("a", "b", "c")));
        Email.User user2 = new Email.User("user2", new ArrayList<>(List.of("d", "e")));
        Email.User user3 = new Email.User("user3", new ArrayList<>(List.of("c")));
        Email.User user4 = new Email.User("user4", new ArrayList<>(List.of("k", "n", "e", "j", "e")));
        Email.User user5 = new Email.User("user5", new ArrayList<>(List.of("w", "o")));
        Email.User user44 = new Email.User("user4", new ArrayList<>(List.of("d", "e", "j", "k", "n")));
        Email.User user33 = new Email.User("user3", new ArrayList<>(List.of("a", "b", "c")));
        List<Email.User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        List<Email.User> rsl = new Email().compress(list);
        assertThat(rsl.contains(user1), is(false));
        assertThat(rsl.contains(user44), is(true));
        assertThat(rsl.contains(user2), is(false));
        assertThat(rsl.contains(user5), is(true));
        assertThat(rsl.contains(user33), is(true));
        assertThat(rsl.size(), is(3));
    }

    @Test
    public void whenAllUsersAreOne() {
        Email.User user1 = new Email.User("user1", new ArrayList<>(List.of("a", "b")));
        Email.User user2 = new Email.User("user2", new ArrayList<>(List.of("b", "c")));
        Email.User user3 = new Email.User("user3", new ArrayList<>(List.of("c", "d")));
        Email.User user4 = new Email.User("user4", new ArrayList<>(List.of("d")));
        Email.User user5 = new Email.User("user4", new ArrayList<>(List.of("a", "b", "c", "d")));
        List<Email.User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        List<Email.User> rsl = new Email().compress(list);
        assertThat(rsl.contains(user5), is(true));
        assertThat(rsl.contains(user2), is(false));
        assertThat(rsl.size(), is(1));
    }

    @Test
    public void whenFirstUserIsMainAndOthersToDel() {
        Email.User user1 = new Email.User("user1", new ArrayList<>(List.of("a")));
        Email.User user2 = new Email.User("user2", new ArrayList<>(List.of("a", "b", "c", "d")));
        Email.User user3 = new Email.User("user3", new ArrayList<>(List.of("b")));
        Email.User user4 = new Email.User("user4", new ArrayList<>(List.of("c")));
        Email.User user5 = new Email.User("user1", new ArrayList<>(List.of("d")));
        Email.User userA = new Email.User("user1", new ArrayList<>(List.of("a", "b", "c", "d")));
        List<Email.User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        List<Email.User> rsl = new Email().compress(list);
        assertThat(rsl.contains(userA), is(true));
        assertThat(rsl.contains(user3), is(false));
        assertThat(rsl.size(), is(1));
    }
}