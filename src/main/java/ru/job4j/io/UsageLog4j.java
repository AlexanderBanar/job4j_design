package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Alex Banar";
        int age = 33;
        char grade = 'A';
        boolean activity = true;
        long account = 686678737756871L;
        float completion = 0.4F;
        double height = 1.78;
        byte course = 2;
        short days = 212;


 //       LOG.trace("trace message");
        LOG.debug("User info name : {}, age : {}, grade : {}, activity : {}," +
                " account : {}, completion : {}, height : {}, course : {}, days : {}",
                name, age, grade, activity, account, completion, height, course, days);
 //       LOG.info("info message");
 //       LOG.warn("warn message");
 //       LOG.error("error message");
    }
}
