package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CdTest {
    @Test
    public void whenCdBack() {
        Cd shell = new Cd();
        shell.cd("/user/..");
        assertThat(
                shell.pwd(), is("\\")
        );
    }

    @Test
    public void whenCdRoot() {
        Cd shell = new Cd();
        shell.cd("/");
        assertThat(
                shell.pwd(), is("\\")
        );
    }

    @Test
    public void whenCdUserLocal() {
        Cd shell = new Cd();
        shell.cd("user");
        shell.cd("local");
        assertThat(
                shell.pwd(), is("\\user\\local")
        );
    }

    @Test
    public void whenCdUserBack() {
        Cd shell = new Cd();
        shell.cd("user");
        shell.cd("..");
        assertThat(
                shell.pwd(), is("\\")
        );
    }

}