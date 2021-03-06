package ru.job4j.io.finder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public void writeToFile(Container data) throws IOException {
        if (data.getResult().length() == 21) {
            data.getResult().append("no elements found matching your search inputs");
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(data.getOutput()))) {
            out.write(data.getResult().toString());
        }
    }
}
