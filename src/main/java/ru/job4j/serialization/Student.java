package ru.job4j.serialization;


import com.sun.xml.txw2.annotation.XmlElement;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    private boolean permission;
    @XmlAttribute
    private int experience;
    @XmlAttribute
    private String name;
    private Student.Card card;
    private String[] data;

    public Student() {

    }

    public Student(boolean permission, int experience, String name, Card card, String[] data) {
        this.permission = permission;
        this.experience = experience;
        this.name = name;
        this.card = card;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Student{"
                + "permission=" + permission
                + ", experience=" + experience
                + ", name='" + name + '\''
                + ", card=" + card
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

    public Card getCard() {
        return card;
    }

    public String[] getData() {
        return data;
    }

    @XmlElement(value = "card")
    private static class Card {
        @XmlAttribute
        String number;
        @XmlAttribute
        int year;

        public Card() {

        }

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

        public String getNumber() {
            return number;
        }

        public int getYear() {
            return year;
        }
    }

    public static void main(String[] args) {
        JSONObject jsonCard = new JSONObject("{\"number\":\"687467\", " +
                "\"year\":\"2021\"}");
        List<String> data = new ArrayList<>();
        data.add("data1");
        data.add("data2");
        JSONArray jsonData = new JSONArray(data);
        final Student student = new Student(true, 10, "Alex",
                new Card("687467", 2021),
                new String[]{"data1", "data2"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("permission", student.isPermission());
        jsonObject.put("experience", student.getExperience());
        jsonObject.put("name", student.getName());
        jsonObject.put("card", jsonCard);
        jsonObject.put("data", jsonData);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(student).toString());
    }
}
