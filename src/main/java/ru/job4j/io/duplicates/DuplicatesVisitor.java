package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<String, FileProperty> registry;
    private Path duplicated;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println(file.toAbsolutePath());
        if (isDuplicate(file)) {
            System.out.print(" The file is duplicate to: ");
            System.out.print(duplicated.toString());
        }
        return super.visitFile(file, attrs);
    }

    private boolean isDuplicate(Path file) {
        String fileName = file.getFileName().toString();
        FileProperty currentFile = new FileProperty(file.toFile().length(), fileName);
        if (registry.containsKey(fileName)) {
            if (currentFile.equals(registry.get(fileName))) {
                duplicated = file;
                return true;
            }
        }
        registry.put(fileName, currentFile);
        return false;
    }
}
