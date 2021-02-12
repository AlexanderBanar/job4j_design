package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        if (args[0] == null || args[0].isEmpty()) {
            throw new IllegalArgumentException("Directory is not specified!");
        }
        Files.walkFileTree(Path.of(args[0]), new DuplicatesVisitor());
    }
}
