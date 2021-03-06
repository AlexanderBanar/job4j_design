package ru.job4j.io.finder;

import java.io.IOException;
import java.nio.file.Files;

public class Find {
    public static void main(String[] args) throws IOException {
        Validator validator = new Validator(args);
        if (validator.isNotValid()) {
            throw new IllegalArgumentException("You have entered invalid argument(s): "
                    + "Right format: -d=(directory) -n=(name) -t=(type: mask/name/regex) -o=(output file)");
        }
        Container container = new Container(args);
        FileFinder finder = new FileFinder(container);
        Files.walkFileTree(container.getDirectory(), finder);
        new Writer().writeToFile(finder.getData());
    }
}
