package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analize {
    public static void unavailable(String source, String target) {
        String line;
        boolean isStarted = false;
        Pattern pattern = Pattern.compile("400|500");
        List<String> outList = new ArrayList<>();
        StringBuilder timePair = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            while ((line = in.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find() && !isStarted) {
                    String[] splitLine = line.split(" ");
                    timePair.append(splitLine[1]).append(";");
                    isStarted = true;
                } else if (!matcher.find() && isStarted) {
                    String[] splitLine = line.split(" ");
                    timePair.append(splitLine[1]);
                    outList.add(timePair.toString());
                    isStarted = false;
                    timePair.delete(0, timePair.length());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                )
        )) {
            for (String s : outList) {
                out.write(s);
                out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
