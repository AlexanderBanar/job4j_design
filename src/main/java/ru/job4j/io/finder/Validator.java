package ru.job4j.io.finder;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {
    private final String[] args;

    public Validator(String[] args) {
        this.args = args;
    }

    public boolean isNotValid() {
        boolean flag = true;
        if (argsNotNull()) {
            if (argsInFormat() && argsContentsRight()) {
                flag = false;
            }
        }
        return flag;
    }

    private boolean argsNotNull() {
        return this.args[0] != null && this.args[1] != null
                && this.args[2] != null && this.args[3] != null;
    }

    private boolean argsInFormat() {
        return this.args[0].contains("-d=") && this.args[0].length() > 3
                && this.args[1].contains("-n=") && this.args[1].length() > 3
                && this.args[2].contains("-t=") && this.args[2].length() > 3
                && this.args[3].contains("-o=") && this.args[3].length() > 3;
    }

    private boolean argsContentsRight() {
        Path directory = Paths.get(this.args[0].replaceAll("-d=", ""));
        String type = this.args[2].replaceAll("-t=", "");
        return (directory.toFile().isDirectory()
                && (type.equals("mask") || type.equals("name")
                || type.equals("regex")));
    }


}
