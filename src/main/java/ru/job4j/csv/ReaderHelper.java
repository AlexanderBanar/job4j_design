package ru.job4j.csv;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;

public class ReaderHelper {
    ArgsName parameters;
    StringBuilder output = new StringBuilder();

    public ReaderHelper(String[] args) {
        validateArgs(args);
        this.parameters = ArgsName.of(args);
        validatePath();
    }

    public void start() {
        try (Scanner scanner = new Scanner(new BufferedReader(
                new FileReader(parameters.get("path"))))
                .useDelimiter(System.lineSeparator())) {
            String delimiter = parameters.get("delimiter");
            List<Integer> listOfIndexes = getListOfIndexes(scanner, delimiter);
            while (scanner.hasNextLine()) {
                String[] currentLine = scanner.next().split(delimiter);
                for (Integer listOfIndex : listOfIndexes) {
                    output.append(currentLine[listOfIndex])
                            .append(";");
                }
                output.append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        printOutput();
    }

    private void printOutput() {
        if (parameters.get("out").equals("stdout")) {
            System.out.println(output.toString());
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(parameters.get("out")))) {
                bw.write(output.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Integer> getListOfIndexes(Scanner scanner, String delimiter) {
        List<Integer> listOfIndexes = new ArrayList<>();
        List<String> rowsFromFilter = Arrays.asList(parameters.get("filter").split(","));
        String[] heads = null;
        if (scanner.hasNextLine()) {
            heads = scanner.next().split(delimiter);
        }
        for (int i = 0; i < Objects.requireNonNull(heads).length; i++) {
            if (rowsFromFilter.contains(heads[i])) {
                listOfIndexes.add(i);
            }
        }
        return listOfIndexes;
    }

    private void validateArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Arguments quantity is not four");
        }
        if (args[0].length() == 0 || args[1].length() == 0
                || args[2].length() == 0 || args[3].length() == 0) {
            throw new IllegalArgumentException("At least one argument is empty");
        }
    }

    private void validatePath() {
        File csvFile = new File(parameters.get("path"));
        if (!csvFile.exists() || csvFile.isDirectory()) {
            throw new IllegalArgumentException("csv file either not exists or is directory");
        }
    }
}
