package ru.job4j.generics;

public class User extends Base {
    private String id;
    private String name;
    private String surname;

    protected User(String id, String name, String surname) {
        super(id);
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
