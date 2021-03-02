package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    private boolean permission;
    private int experience;
    private String name;
    private Account account;
    private String[] data;

    public Student() {

    }

    public Student(boolean permission, int experience, String name, Account account, String[] data) {
        this.permission = permission;
        this.experience = experience;
        this.name = name;
        this.account = account;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Student{"
                + "permission=" + permission
                + ", experience=" + experience
                + ", name='" + name + '\''
                + ", account=" + account
                + ", data=" + Arrays.toString(data)
                + '}';
    }

    public boolean isPermission() {
        return permission;
    }

    public int getExperience() {
        return experience;
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    public String[] getData() {
        return data;
    }

    public static void main(String[] args) {
        JSONObject jsonCard = new JSONObject("{\"number\":\"687467\", "
                + "\"year\":\"2021\"}");
        List<String> data = new ArrayList<>();
        data.add("data1");
        data.add("data2");
        JSONArray jsonData = new JSONArray(data);
        final Student student = new Student(true, 10, "Alex",
                new Account("687467", 2021),
                new String[]{"data1", "data2"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("permission", student.isPermission());
        jsonObject.put("experience", student.getExperience());
        jsonObject.put("name", student.getName());
        jsonObject.put("account", jsonCard);
        jsonObject.put("data", jsonData);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(student).toString());
    }
}
