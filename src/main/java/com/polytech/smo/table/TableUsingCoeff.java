package com.polytech.smo.table;

public class TableUsingCoeff {
    private final int deviceNum;
    private final double usingCoeff;

    public TableUsingCoeff(int deviceNum, double usingCoeff) {
        this.deviceNum = deviceNum;
        this.usingCoeff = usingCoeff;
    }

    public int getDeviceNum() {
        return deviceNum;
    }

    public double getUsingCoeff() {
        return usingCoeff;
    }
}
