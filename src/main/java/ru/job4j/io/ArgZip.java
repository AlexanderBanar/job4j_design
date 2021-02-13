package ru.job4j.io;

import java.io.File;

public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        return directory() != null && exclude() != null
                && output() != null;
    }

    public String directory() {
        String[] split = args[0].split("=");
        String directory = split[1];
        if (new File(directory).isDirectory()) {
            return directory;
        }
        return null;
    }

    public String exclude() {
        String[] split = args[1].split("=");
        String exclude = split[1];
        if (!exclude.isEmpty()) {
            return exclude;
        }
        return null;
    }

    public String output() {
        String[] split = args[2].split("=");
        String output = split[1];
        if (!output.isEmpty()) {
            return output;
        }
        return null;
    }
}
