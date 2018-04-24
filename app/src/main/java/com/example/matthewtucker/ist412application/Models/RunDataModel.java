package com.example.matthewtucker.ist412application.Models;

import java.util.ArrayList;

public class RunDataModel {
    private static RunDataModel instance = null;
    private ArrayList<DataPacket> packets;
    private double[] speeds;

    protected RunDataModel(){
    speeds = new double[5];
    }

    public static RunDataModel getInstance(){
        if(instance == null){
            instance = new RunDataModel();
        }
        return instance;
    }

    public void addToPackets(DataPacket packet){
        packets.add(packet);
    }

    public void addSpeed(int index, double speed){
        speeds[index] = speed;
    }

    public double[] getSpeeds(){
        return this.speeds;
    }

}
