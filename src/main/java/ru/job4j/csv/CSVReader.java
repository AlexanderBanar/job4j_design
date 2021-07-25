package ru.job4j.csv;

import java.io.FileNotFoundException;

public class CSVReader {
    public static void main(String[] args) throws FileNotFoundException {
        ReaderHelper helper = new ReaderHelper(args);
        helper.start();
    }
}
