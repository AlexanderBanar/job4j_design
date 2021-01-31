package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("person"),
                is("Alex Banar")
        );
    }

    @Test
    public void whenPairWithComments() {
        String path = "pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("person"),
                is("Ivan Petrov")
        );
    }

}