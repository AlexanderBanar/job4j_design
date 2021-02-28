package ru.job4j.serialization;

import org.json.JSONObject;
import org.json.JSONPropertyIgnore;

public class NotationA {
    private NotationB b;

    @JSONPropertyIgnore
    public NotationB getB() {
        return b;
    }

    public void setB(NotationB b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }

    public static void main(String[] args) {
        NotationA a = new NotationA();
        NotationB b = new NotationB();
        a.setB(b);
        b.setA(a);
        System.out.println(new JSONObject(b));
    }
}
