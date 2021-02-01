package ru.job4j.io;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String temp;
        Reader inputString = new StringReader(toString());
        try (BufferedReader in = new BufferedReader(inputString)) {
            while ((temp = in.readLine()) != null) {
                if (!temp.startsWith("#") && temp.contains("=")) {
                    String[] pair = temp.trim().split("=");
                    values.put(pair[0], pair[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new UnsupportedOperationException("The key is missing");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
