package com.example.matthewtucker.ist412application.Models;

import java.util.ArrayList;

public class RunDataModel {
    private static RunDataModel instance = null;
    private ArrayList<DataPacket> packets;

    protected RunDataModel(){

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

}
