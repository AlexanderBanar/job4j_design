package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Pattern pattern = Pattern.compile("\\S+=\\S+");
        for (String arg : args) {
            Matcher matcher = pattern.matcher(arg);
            if (!matcher.find()) {
                throw new IllegalArgumentException();
            }
            String[] split = arg.substring(1).split("=");
            if (split[0] == null || split[1] == null) {
                throw new IllegalArgumentException();
            }
            values.put(split[0], split[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
