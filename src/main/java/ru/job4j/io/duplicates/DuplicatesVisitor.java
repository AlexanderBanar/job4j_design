package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<FileProperty> registry = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        FileProperty currentFile = new FileProperty(file.toFile().length(), fileName);
        if (!registry.add(currentFile)) {
            System.out.print(fileName);
            System.out.println(" is duplicate");
        }
        return super.visitFile(file, attrs);
    }
}
