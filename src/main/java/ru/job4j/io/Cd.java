package ru.job4j.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cd {
    private Path currentPath = Paths.get(File.separator);

    public void cd(String path) {
        Pattern pattern = Pattern.compile("\\w");
        Matcher matcher = pattern.matcher(path);
        if (path.equals("..")) {
            currentPath = currentPath.getParent();
            if (currentPath == null) {
                currentPath = Paths.get(File.separator);
            }
            return;
        }
        if (path.contains("..")) {
            Path temp = Paths.get(path);
            temp = temp.getParent();
            temp = temp.getParent();
            currentPath = Objects.requireNonNullElseGet(temp, () -> Paths.get(File.separator));
        } else if (matcher.find()) {
            currentPath = currentPath.resolve(path);
        }
    }

    public String pwd() {
        return currentPath.toString();
    }
}
