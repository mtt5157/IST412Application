package com.example.matthewtucker.ist412application.Models;

public class DataPacket {
    private double accX;
    private double accY;
    private double accZ;
    private double gyroX;
    private double gyroY;
    private double gyroZ;
    private double temp;

    public DataPacket(double accX, double accY, double accZ, double gyroX, double gyroY, double gyroZ, double temp){
        this.accX = accX;
        this.accY = accY;
        this.accZ = accY;

        this.gyroX = gyroX;
        this.gyroY = gyroY;
        this.gyroZ = gyroZ;

        this.temp = temp;
    }

    public double getAccX(){
        return this.accX;
    }

    public double getAccY(){
        return this.accY;
    }

    public double getAccZ(){
        return this.accZ;
    }

    public double getGyroX(){
        return this.gyroX;
    }

    public double getGyroY(){
        return this.gyroY;
    }

    public double getGyroZ(){
        return this.gyroZ;
    }

    public double getTemp(){
        return this.temp;
    }
}
