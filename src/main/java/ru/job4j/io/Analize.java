package ru.job4j.io;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analize {
    public static void unavailable(String source, String target) {
        String line;
        boolean started = false;
        Pattern pattern = Pattern.compile("400|500");
        StringBuilder outText = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            while ((line = in.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find() && !started) {
                    String[] temp = line.split(" ");
                    outText.append(temp[1]).append(";");
                    started = true;
                } else if (!matcher.find() && started) {
                    String[] temp = line.split(" ");
                    outText.append(temp[1]).append(System.lineSeparator());
                    started = false;
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
            out.write(outText.toString());
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
