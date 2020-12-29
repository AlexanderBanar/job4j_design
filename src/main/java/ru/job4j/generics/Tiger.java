package ru.job4j.generics;

public class Tiger extends Predator {
    @Override
    public int living() {
        return 3;
    }

    @Override
    public String describe() {
        return "having stripes";
    }

    public boolean lasting() {
        return true;
    }
}
