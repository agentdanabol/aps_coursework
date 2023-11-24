package com.polytech.smo.table;

public class SourceTable {
    private int deviceId;
    private double nextGenerationTime;

    public SourceTable(int deviceId, double nextGenerationTime) {
        this.deviceId = deviceId;
        this.nextGenerationTime = nextGenerationTime;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public double getNextGenerationTime() {
        return nextGenerationTime;
    }

    public void setNextGenerationTime(double nextGenerationTime) {
        this.nextGenerationTime = nextGenerationTime;
    }
}
