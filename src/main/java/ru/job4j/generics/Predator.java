package ru.job4j.generics;

public class Predator extends Animal {
    @Override
    public int living() {
        return 2;
    }

    public String describe() {
        return "hunting";
    }
}
