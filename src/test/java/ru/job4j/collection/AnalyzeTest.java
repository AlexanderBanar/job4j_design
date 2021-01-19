package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalyzeTest {
    @Test
    public void whenAdded0Changed0Deleted0() {
        List<Analyze.User> previous = new ArrayList<>();
        previous.add(new Analyze.User(1, "Andrey"));
        previous.add(new Analyze.User(2, "Alex"));
        previous.add(new Analyze.User(3, "Anton"));
        previous.add(new Analyze.User(4, "Azat"));
        List<Analyze.User> current = new ArrayList<>();
        current.add(new Analyze.User(1, "Andrey"));
        current.add(new Analyze.User(2, "Alex"));
        current.add(new Analyze.User(3, "Anton"));
        current.add(new Analyze.User(4, "Azat"));
        Analyze.Info rsl = new Analyze().diff(previous, current);
        assertThat(rsl.added, is(0));
        assertThat(rsl.changed, is(0));
        assertThat(rsl.deleted, is(0));
    }

    @Test
    public void whenAdded0Changed1Deleted1() {
        List<Analyze.User> previous = new ArrayList<>();
        previous.add(new Analyze.User(1, "Andrey"));
        previous.add(new Analyze.User(2, "Alex"));
        previous.add(new Analyze.User(3, "Anton"));
        previous.add(new Analyze.User(4, "Azat"));
        List<Analyze.User> current = new ArrayList<>();
        current.add(new Analyze.User(1, "Andrey"));
        current.add(new Analyze.User(2, "Alexander"));
        current.add(new Analyze.User(3, "Anton"));
        Analyze.Info rsl = new Analyze().diff(previous, current);
        assertThat(rsl.added, is(0));
        assertThat(rsl.changed, is(1));
        assertThat(rsl.deleted, is(1));
    }

    @Test
    public void whenAdded1Changed0Deleted1() {
        List<Analyze.User> previous = new ArrayList<>();
        previous.add(new Analyze.User(1, "Andrey"));
        previous.add(new Analyze.User(2, "Alex"));
        previous.add(new Analyze.User(3, "Anton"));
        previous.add(new Analyze.User(4, "Azat"));
        List<Analyze.User> current = new ArrayList<>();
        current.add(new Analyze.User(1, "Andrey"));
        current.add(new Analyze.User(2, "Alex"));
        current.add(new Analyze.User(3, "Anton"));
        current.add(new Analyze.User(5, "Dmitry"));
        Analyze.Info rsl = new Analyze().diff(previous, current);
        assertThat(rsl.added, is(1));
        assertThat(rsl.changed, is(0));
        assertThat(rsl.deleted, is(1));
    }

    @Test
    public void whenAdded1Changed1Deleted1() {
        List<Analyze.User> previous = new ArrayList<>();
        previous.add(new Analyze.User(1, "Andrey"));
        previous.add(new Analyze.User(2, "Alex"));
        previous.add(new Analyze.User(3, "Anton"));
        previous.add(new Analyze.User(4, "Azat"));
        List<Analyze.User> current = new ArrayList<>();
        current.add(new Analyze.User(1, "Andrey"));
        current.add(new Analyze.User(2, "Alexander"));
        current.add(new Analyze.User(3, "Anton"));
        current.add(new Analyze.User(9, "Michael"));
        Analyze.Info rsl = new Analyze().diff(previous, current);
        assertThat(rsl.added, is(1));
        assertThat(rsl.changed, is(1));
        assertThat(rsl.deleted, is(1));
    }
}