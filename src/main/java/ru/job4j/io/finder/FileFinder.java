package ru.job4j.io.finder;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileFinder extends SimpleFileVisitor<Path> {
    private final Container data;

    public FileFinder(Container data) {
        this.data = data;
    }

    public Container getData() {
        return data;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (data.getPredicate().test(file)) {
            data.getResult().append(file.toAbsolutePath().toString())
                    .append(System.lineSeparator());
        }
        return super.visitFile(file, attrs);
    }
}
