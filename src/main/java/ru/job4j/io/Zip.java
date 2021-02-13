package ru.job4j.io;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Zip {
    public void packFiles(List<File> sources, File target) {
        for (File file : sources) {
            packSingleFile(file, target);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("At least one of 3 required parameters is missing or abundant");
        }
        ArgZip argZip = new ArgZip(args);
        if (!argZip.valid()) {
            throw new IllegalArgumentException("At least one of 3 parameters is incorrect");
        }
        List<Path> paths = Searcher.search(Path.of(argZip.directory()), argZip.exclude());
        List<File> files = paths.stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
        Zip doer = new Zip();
        doer.packFiles(files, Path.of(argZip.output()).toFile());
    }

    private static class Searcher extends Search {
        public static List<Path> search(Path root, String ext) throws IOException {
            SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(ext));
            Files.walkFileTree(root, searcher);
            return searcher.getPaths();
        }
    }

    private static class SearchFiles implements FileVisitor<Path> {
        private final Predicate<Path> predicate;
        private final List<Path> paths;

        private SearchFiles(Predicate<Path> predicate) {
            this.predicate = predicate;
            this.paths = new ArrayList<>();
        }

        private List<Path> getPaths() {
            return paths;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (predicate.test(file)) {
                paths.add(file);
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return CONTINUE;
        }
    }
}
