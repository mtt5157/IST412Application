package com.example.matthewtucker.ist412application;

import java.sql.Time;
import java.util.Random;

public class RunData {

    private final Random random = new Random();
    private int[] speeds;
    private int maxSpeed;
    private int averageSpeed;
    private int maxHeight;
    private int time;

    public RunData()
    {
        generateRunData();
    }


    public void generateRunData()
    {
        //random time
        int millisInDay = 24 * 60 * 60 * 1000;
        Time time = new Time((long) random.nextInt(millisInDay));

        //random speeds
        speeds = new int[5];
        maxSpeed= 0;
        int totalSpeed = 0;
        for (int j = 0; j < 5; j++) {
            getSpeeds()[j] = random.nextInt(50);
            if (getSpeeds()[j]> getMaxSpeed())
                maxSpeed = getSpeeds()[j];
            totalSpeed+=getSpeeds()[j];
        }
        averageSpeed = totalSpeed/5;

        //random height
        maxHeight = random.nextInt(15);
    }


    public int[] getSpeeds() {
        return speeds;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getTime() {
        return time;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getAverageSpeed() {
        return averageSpeed;
    }
}
