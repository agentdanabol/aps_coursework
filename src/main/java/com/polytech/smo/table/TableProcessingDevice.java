package com.polytech.smo.table;

public class TableProcessingDevice {
    private int deviceId;
    private String state;
    private double startTime;
    private double endTime;

    public TableProcessingDevice(int deviceId, String state, double startTime, double endTime) {
        this.deviceId = deviceId;
        this.state = state;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }
}
