package ru.job4j.io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {
    @Test
    public void whenNoDataWritten() {
        StringBuilder rsl = new StringBuilder();
        String line;
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("server.txt")
                )
        )) {
            out.write("200 21:16:00" + System.lineSeparator()
                    + "200 21:17:00" + System.lineSeparator()
                    + "300 21:17:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analize.unavailable("server.txt", "unavailable.txt");
        try (BufferedReader in = new BufferedReader(new FileReader("unavailable.txt"))) {
            while ((line = in.readLine()) != null) {
                rsl.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(rsl.toString(), is(""));
    }

    @Test
    public void whenOneChain() {
        StringBuilder rsl = new StringBuilder();
        String line;
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("server.txt")
                )
        )) {
            out.write("200 21:16:00" + System.lineSeparator()
                    + "200 21:17:00" + System.lineSeparator()
                    + "300 21:18:00" + System.lineSeparator()
                    + "300 21:19:00" + System.lineSeparator()
                    + "400 21:20:00" + System.lineSeparator()
                    + "500 21:21:00"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analize.unavailable("server.txt", "unavailable.txt");
        try (BufferedReader in = new BufferedReader(new FileReader("unavailable.txt"))) {
            while ((line = in.readLine()) != null) {
                rsl.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(rsl.toString(), is("21:20:00;21:21:00"));
    }

    @Test
    public void whenTwoChains() {
        StringBuilder rsl = new StringBuilder();
        String line;
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("server.txt")
                )
        )) {
            out.write("200 21:16:00" + System.lineSeparator()
                    + "200 21:17:00" + System.lineSeparator()
                    + "300 21:18:00" + System.lineSeparator()
                    + "300 21:19:00" + System.lineSeparator()
                    + "400 21:20:00" + System.lineSeparator()
                    + "500 21:21:00" + System.lineSeparator()
                    + "300 21:22:00" + System.lineSeparator()
                    + "500 21:23:00" + System.lineSeparator()
                    + "500 21:24:00" + System.lineSeparator()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analize.unavailable("server.txt", "unavailable.txt");
        try (BufferedReader in = new BufferedReader(new FileReader("unavailable.txt"))) {
            while ((line = in.readLine()) != null) {
                rsl.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder result = new StringBuilder("21:20:00;21:21:00")
                .append("21:23:00;21:24:00");
        assertThat(rsl.toString(), is(result.toString()));
    }

}