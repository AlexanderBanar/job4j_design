package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenNoDataWritten() {
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        String line;
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("server.txt")
                )
        )) {
            out.write("200 21:16:00");
            out.println();
            out.write("200 21:17:00");
            out.println();
            out.write("300 21:17:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analize.unavailable("server.txt", "unavailable.txt");
        try (BufferedReader in = new BufferedReader(new FileReader("unavailable.txt"))) {
            while ((line = in.readLine()) != null) {
                rsl.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(rsl.toString(), is(""));
    }

    @Test
    public void whenOneChain() {
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        String line;
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("server.txt")
                )
        )) {
            out.write("200 21:16:00");
            out.println();
            out.write("200 21:17:00");
            out.println();
            out.write("300 21:18:00");
            out.println();
            out.write("300 21:19:00");
            out.println();
            out.write("400 21:20:00");
            out.println();
            out.write("500 21:21:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analize.unavailable("server.txt", "unavailable.txt");
        try (BufferedReader in = new BufferedReader(new FileReader("unavailable.txt"))) {
            while ((line = in.readLine()) != null) {
                rsl.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(rsl.toString(), is("21:20:00;21:21:00"));
    }

    @Test
    public void whenTwoChains() {
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        String line;
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("server.txt")
                )
        )) {
            out.write("200 21:16:00");
            out.println();
            out.write("200 21:17:00");
            out.println();
            out.write("300 21:18:00");
            out.println();
            out.write("300 21:19:00");
            out.println();
            out.write("400 21:20:00");
            out.println();
            out.write("500 21:21:00");
            out.println();
            out.write("300 21:22:00");
            out.println();
            out.write("500 21:23:00");
            out.println();
            out.write("500 21:24:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analize.unavailable("server.txt", "unavailable.txt");
        try (BufferedReader in = new BufferedReader(new FileReader("unavailable.txt"))) {
            while ((line = in.readLine()) != null) {
                rsl.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringJoiner expected = new StringJoiner(System.lineSeparator());
        expected.add("21:20:00;21:22:00");
        expected.add("21:23:00;21:24:00");
        assertThat(rsl.toString(), is(expected.toString()));
    }

    @Test
    public void whenOneChainAndTempFolderUsage() throws IOException {
        File source = folder.newFile("server.txt");
        File target = folder.newFile("unavailable.txt");
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        String line;
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(source)
                )
        )) {
            out.write("300 21:16:00");
            out.println();
            out.write("200 21:17:00");
            out.println();
            out.write("500 21:18:00");
            out.println();
            out.write("400 21:19:00");
            out.println();
            out.write("200 21:20:00");
            out.println();
            out.write("200 21:21:00");
        }
        Analize.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            while ((line = in.readLine()) != null) {
                rsl.add(line);
            }
        }
        StringJoiner expected = new StringJoiner(System.lineSeparator());
        expected.add("21:18:00;21:20:00");
        assertThat(rsl.toString(), is(expected.toString()));
    }
}