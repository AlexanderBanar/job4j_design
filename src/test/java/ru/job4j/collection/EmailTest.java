package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class EmailTest {
    @Test
    public void whenNoChanges() {
        Email.User user1 = new Email.User("user1", new HashSet<>(List.of("a", "b", "c")));
        Email.User user2 = new Email.User("user2", new HashSet<>(List.of("d", "e")));
        List<Email.User> list = new ArrayList<>(List.of(user1, user2));
        List<Email.User> rsl = new Email().compress(list);
        assertThat(rsl, is(list));
    }

    @Test
    public void when1UserHasSameMails() {
        Email.User user1 = new Email.User("user1", new HashSet<>(List.of("a", "b", "c")));
        Email.User user2 = new Email.User("user2", new HashSet<>(List.of("d", "e")));
        Email.User user3 = new Email.User("user3", new HashSet<>(List.of("c", "n")));
        Email.User user4 = new Email.User("user1", new HashSet<>(List.of("a", "b", "c", "n")));
        List<Email.User> list = new ArrayList<>(List.of(user1, user2, user3));
        List<Email.User> listCompressed = new ArrayList<>(List.of(user4, user2));
        List<Email.User> rsl = new Email().compress(list);
        assertThat(rsl, is(listCompressed));
    }

    @Test
    public void when2UserHasSameMailsAndDuplicatesMails() {
        Email.User user1 = new Email.User("user1", new HashSet<>(List.of("a", "b", "c")));
        Email.User user2 = new Email.User("user2", new HashSet<>(List.of("d", "e")));
        Email.User user3 = new Email.User("user3", new HashSet<>(List.of("c")));
        Email.User user4 = new Email.User("user4", new HashSet<>(List.of("k", "n", "e", "j")));
        Email.User user5 = new Email.User("user5", new HashSet<>(List.of("w", "o")));
        Email.User user22 = new Email.User("user2", new HashSet<>(List.of("d", "e", "j", "k", "n")));
        List<Email.User> list = new ArrayList<>(List.of(user1, user2, user3, user4, user5));
        List<Email.User> rsl = new Email().compress(list);
        assertThat(rsl.contains(user1), is(true));
        assertThat(rsl.contains(user22), is(true));
        assertThat(rsl.contains(user5), is(true));
        assertThat(rsl.size(), is(3));
    }

    @Test
    public void whenAllUsersAreOne() {
        Email.User user1 = new Email.User("user1", new HashSet<>(List.of("a", "b")));
        Email.User user2 = new Email.User("user2", new HashSet<>(List.of("b", "c")));
        Email.User user3 = new Email.User("user3", new HashSet<>(List.of("c", "d")));
        Email.User user4 = new Email.User("user4", new HashSet<>(List.of("d")));
        Email.User user5 = new Email.User("user1", new HashSet<>(List.of("a", "b", "c", "d")));
        List<Email.User> list = new ArrayList<>(List.of(user1, user2, user3, user4));
        List<Email.User> rsl = new Email().compress(list);
        assertThat(rsl.contains(user5), is(true));
        assertThat(rsl.contains(user2), is(false));
        assertThat(rsl.size(), is(1));
    }

    @Test
    public void whenFirstUserIsMainAndOthersToDel() {
        Email.User user1 = new Email.User("user1", new HashSet<>(List.of("a")));
        Email.User user2 = new Email.User("user2", new HashSet<>(List.of("a", "b", "c", "d")));
        Email.User user3 = new Email.User("user3", new HashSet<>(List.of("b")));
        Email.User user4 = new Email.User("user4", new HashSet<>(List.of("c")));
        Email.User user5 = new Email.User("user1", new HashSet<>(List.of("d")));
        Email.User userA = new Email.User("user1", new HashSet<>(List.of("a", "b", "c", "d")));
        List<Email.User> list = new ArrayList<>(List.of(user1, user2, user3, user4, user5));
        List<Email.User> rsl = new Email().compress(list);
        assertThat(rsl.contains(userA), is(true));
        assertThat(rsl.contains(user3), is(false));
        assertThat(rsl.size(), is(1));
    }
}