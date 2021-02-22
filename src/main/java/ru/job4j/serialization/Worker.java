package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Worker {
    private final boolean permission;
    private final int experience;
    private final String name;
    private final Card card;
    private final String[] data;

    public Worker(boolean permission, int experience, String name, Card card, String[] data) {
        this.permission = permission;
        this.experience = experience;
        this.name = name;
        this.card = card;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Worker{"
                + "permission=" + permission
                + ", experience=" + experience
                + ", name='" + name + '\''
                + ", card=" + card
                + ", data=" + Arrays.toString(data)
                + '}';
    }

    private static class Card {
        String number;
        int year;

        public Card(String number, int year) {
            this.number = number;
            this.year = year;
        }

        @Override
        public String toString() {
            return "Card{"
                    + "number='" + number + '\''
                    + ", year=" + year
                    + '}';
        }
    }

    public static void main(String[] args) {
        final Worker worker = new Worker(true, 15, "Ivan",
                new Card("3466F-0025-L27", 2021),
                new String[]{"some data1", "some data2"});
        final Gson gson = new GsonBuilder().create();
        String toJson = gson.toJson(worker);
        System.out.println(toJson);
        final Worker workerReborn = gson.fromJson(toJson, Worker.class);
        System.out.println(workerReborn);
    }
}
