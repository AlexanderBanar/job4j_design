package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analize {
    public static void unavailable(String source, String target) {
        String line;
        String time = null;
        boolean isStarted = false;
        boolean isFound = false;
        Pattern pattern = Pattern.compile("400|500");
        List<String> outList = new ArrayList<>();
        StringBuilder timePair = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            while ((line = in.readLine()) != null) {
                String[] splitLine = line.split(" ");
                time = splitLine[1];
                Matcher matcher = pattern.matcher(line);
                isFound = matcher.find();
                if (isFound && !isStarted) {
                    timePair.append(time).append(";");
                    isStarted = true;
                } else if (!isFound && isStarted) {
                    timePair.append(time);
                    outList.add(timePair.toString());
                    isStarted = false;
                    timePair.delete(0, timePair.length());
                }
            }
            if (isFound && isStarted) {
                if (timePair.length() == 0) {
                    timePair.append(time).append(";").append(time);
                } else {
                    timePair.append(time);
                }
                outList.add(timePair.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analize.writing(outList, target);
    }

    private static void writing(List<String> list, String target) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                )
        )) {
            for (String s : list) {
                out.write(s);
                out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
