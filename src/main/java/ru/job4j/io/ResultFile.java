package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        for (int x = 0; x <= 9; x++) {
            for (int i = 9; i >= 0 ; i--) {
                int rsl = x * i;
                s.append(x).append(" * ").append(i).append(" = ")
                        .append(rsl).append(System.lineSeparator());
            }
        }
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(s.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
