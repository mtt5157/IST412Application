package com.example.matthewtucker.ist412application;

import java.util.Arrays;

public class RunDataParser {

    private String sampleData = "8401beffa30ffbffd5fff4ffa7ff0000";
    private byte[] sampleArray = sampleData.getBytes();
    private byte[] accelerometerData;

    public RunDataParser(){

    }

    public void parseData(){
        accelerometerData = Arrays.copyOfRange(sampleArray, 0,5);
    }

    public void printData(){
        String accX = accelerometerData[1]+""+accelerometerData[0];
        int decimal = Integer.parseInt(accX, 16);
        double gX = (decimal/32788)*8;
        System.out.println(gX);
    }


}
