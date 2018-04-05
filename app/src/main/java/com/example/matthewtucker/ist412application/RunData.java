package com.example.matthewtucker.ist412application;

public class RunData {

    private final Random random = new Random();

    public int getMillisInDay() {

        int millisInDay = 24 * 60 * 60 * 1000;
        Time time = new Time((long) random.nextInt(millisInDay));

        return millisInDay;
    }

    public void setMillisInDay() {

    }

    public int[] getSpeeds() {

        int[] speeds = new int[5];
        int maxSpeed = 0;
        for (int j = 0; j < 5; j++) {
            speeds[j] = random.nextInt(50);
            if (speeds[j] > maxSpeed)
                maxSpeed = speeds[j];
        }

        return speeds;
    }

    public void setSpeeds() {

    }

    public int getTotalSpeed() {

        int totalSpeed = 0;
        for (int k = 0; k < 5; k++) {
            totalSpeed += speeds[k];
        }
        int avgSpeed = totalSpeed / 5;

        return avgSpeed;
    }

    public int getMaxHeight() {

        int maxHeight = random.nextInt(15);

        return maxHeight;
    }

    public void setMaxHeight(){

    }
}
