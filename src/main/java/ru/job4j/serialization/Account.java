package ru.job4j.serialization;

public class Account {
    String number;
    int year;

    public Account() {

    }

    public Account(String number, int year) {
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

    public String getNumber() {
        return number;
    }

    public int getYear() {
        return year;
    }
}
