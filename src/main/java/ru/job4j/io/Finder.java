package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    private static Path directory;
    private static String name;
    private static String type;
    private static String output;
    private static StringBuilder result = new StringBuilder();
    private static Predicate<Path> predicate;

    public static void main(String[] args) throws IOException {
        if (isNotValid(args)) {
            throw new IllegalArgumentException("You have entered invalid argument(s): "
                    + "Right format: -d=(directory) -n=(name) -t=(type: mask/name/regex) -o=(output file)");
        }
        setPredicate();
        result.append("Your search result:").append(System.lineSeparator());
        Files.walkFileTree(directory, new FileFinder());
        if (result.length() == 21) {
            result.append("no elements found matching your search inputs");
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(output))) {
            out.write(result.toString());
        }
    }

    private static void setPredicate() {
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
    }

    private static boolean isNotValid(String[] args) {
        boolean flag = true;
        if (args[0] != null && args[1] != null
        && args[2] != null && args[3] != null) {
            if (args[0].contains("-d=") && args[0].length() > 3
                    && args[1].contains("-n=") && args[1].length() > 3
                    && args[2].contains("-t=") && args[2].length() > 3
                    && args[3].contains("-o=") && args[3].length() > 3) {
                flag = false;
                directory = Path.of(args[0].replaceAll("-d=", ""));
                name = args[1].replaceAll("-n=", "");
                type = args[2].replaceAll("-t=", "");
                output = args[3].replaceAll("-o=", "");
            }
        }
        if (!directory.toFile().isDirectory()
        && !(type.equals("mask") || type.equals("name") || type.equals("regex"))) {
            flag = true;
        }
        return flag;
    }

    public static class FileFinder extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (predicate.test(file)) {
                result.append(file.toAbsolutePath().toString())
                .append(System.lineSeparator());
            }
            return super.visitFile(file, attrs);
        }
    }
}
