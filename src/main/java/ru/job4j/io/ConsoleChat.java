package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        StringBuilder chronicles = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        chronicles.append(message);
        chronicles.append(System.lineSeparator());
        String reply = "";
        List<String> replies = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.botAnswers))) {
            read.lines().forEach(replies::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean stopped = false;
        while (!message.equals(OUT)) {
            if (stopped) {
                message = scanner.nextLine();
                chronicles.append(message);
                chronicles.append(System.lineSeparator());
                if (message.equals(CONTINUE)) {
                    stopped = false;
                }
            } else {
                reply = replies.get((int) (Math.random() * replies.size()));
                System.out.println(reply);
                chronicles.append(reply);
                chronicles.append(System.lineSeparator());
                message = scanner.nextLine();
                chronicles.append(message);
                chronicles.append(System.lineSeparator());
                if (message.equals(STOP)) {
                    stopped = true;
                }
            }
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(this.path))) {
            out.write(chronicles.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(args[0], args[1]);
        cc.run();
    }
}
