package ru.job4j.serialization;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement(name = "worker")
@XmlAccessorType(XmlAccessType.FIELD)
public class Worker {
    @XmlAttribute
    private boolean permission;
    @XmlAttribute
    private int experience;
    @XmlAttribute
    private String name;
    private Card card;
    private String[] data;

    public Worker() {

    }

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
    }

    public static void main(String[] args) throws JAXBException, IOException {
        final Worker worker = new Worker(true, 15, "Ivan",
                new Card("3466F-0025-L27", 2021),
                new String[]{"some data1", "some data2"});
        //final Gson gson = new GsonBuilder().create();
        //String toJson = gson.toJson(worker);
        //System.out.println(toJson);
        //final Worker workerReborn = gson.fromJson(toJson, Worker.class);
        //System.out.println(workerReborn);
        JAXBContext context = JAXBContext.newInstance(Worker.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String result = "";
        try (StringWriter writer = new StringWriter()) {
            //serializing
            marshaller.marshal(worker, writer);
            result = writer.getBuffer().toString();
            System.out.println(result);
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream("worker.xml")
                    )
            )) {
                out.write(result);
            }
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (BufferedReader reader = new BufferedReader(new FileReader("worker.xml"))) {
            // deserializing
            Worker workerDeSerialized = (Worker) unmarshaller.unmarshal(reader);
            System.out.println();
            System.out.println(workerDeSerialized);
        }
    }
}