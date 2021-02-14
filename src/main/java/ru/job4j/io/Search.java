package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Arguments quantity is not two");
        }
        if (args[0].length() == 0 || args[1].length() == 0) {
            throw new IllegalArgumentException("At least one argument is empty");
        }
        List<Path> list = search(Path.of(args[0]), "txt");
        list.forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
