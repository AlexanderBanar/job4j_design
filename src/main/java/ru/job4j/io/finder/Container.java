package ru.job4j.io.finder;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Container {
    private final Path directory;
    private final String output;
    private final Predicate<Path> predicate;
    private final StringBuilder result;

    public Container(String[] args) {
        this.directory = Path.of(args[0].replaceAll("-d=", ""));
        this.output = args[3].replaceAll("-o=", "");
        this.predicate = setPredicate(args[2].replaceAll("-t=", ""),
                args[1].replaceAll("-n=", ""));
        this.result = new StringBuilder();
        result.append("Your search result:").append(System.lineSeparator());
    }

    public Path getDirectory() {
        return directory;
    }

    public String getOutput() {
        return output;
    }

    public Predicate<Path> getPredicate() {
        return predicate;
    }

    public StringBuilder getResult() {
        return result;
    }

    private Predicate<Path> setPredicate(String type, String name) {
        Predicate<Path> predicate = Path::isAbsolute;
        if (type.equals("mask")) {
            predicate = x -> x.getFileName().toString().contains(name);
        }
        if (type.equals("name")) {
            predicate = x -> x.getFileName().toString().equals(name);
        }
        if (type.equals("regex")) {
            Pattern pattern = Pattern.compile(name);
            predicate = x -> {
                Matcher matcher = pattern.matcher(x.getFileName().toString());
                return matcher.find();
            };
        }
        return predicate;
    }
}
