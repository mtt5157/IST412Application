package com.example.matthewtucker.ist412application.Util;

import com.example.matthewtucker.ist412application.Models.DataPacket;
import com.example.matthewtucker.ist412application.Models.RunDataModel;

import java.util.Arrays;

public class RunDataParser {

    private String sampleData = "8401beffa30ffbffd5fff4ffa7ff0000";
    private byte[] sampleArray = sampleData.getBytes();
    private byte[] accelerometerData;
    private double accX;
    private double accY;
    private double accZ;
    private double gyroX;
    private double gyroY;
    private double gyroZ;
    private double temp;
    private RunDataModel model;

    public RunDataParser(){
        model = RunDataModel.getInstance();
    }

    public void parseData(byte[] bytes){
        String bytesToString = bytes.toString();
        accX = convertAcc(bytesToString.substring(2,4)+""+bytesToString.substring(0,2));
        accY = convertAcc(bytesToString.substring(6,8)+""+bytesToString.substring(4,6));
        accZ = convertAcc(bytesToString.substring(10,12)+""+bytesToString.substring(8,10));

        gyroX =  convertGyro(bytesToString.substring(14,16)+""+bytesToString.substring(12, 14));
        gyroY = convertGyro(bytesToString.substring(18,20)+""+bytesToString.substring(16, 18));
        gyroZ = convertGyro(bytesToString.substring(22,24)+""+bytesToString.substring(20,22));

        temp = convertTemp(bytesToString.substring(26,28)+""+bytesToString.substring(24,26));

        model.addToPackets(getData());


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

    public DataPacket getData(){
        return new DataPacket(accX,accY,accZ,gyroX,gyroY,gyroZ,temp);
    }

    public double convertAcc(String hex){
        double acc = Integer.parseInt(hex, 16);
        double g = (acc/32788)*8.0;
        return g;
    }

    public double convertGyro(String hex){
        double gyro = Integer.parseInt(hex,16);
        double degs = (gyro/32768)*2000.0;
        return degs;
    }

    public double convertTemp(String hex){
        double temp = Integer.parseInt(hex,16);
        return temp;
    }


}
