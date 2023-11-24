package com.polytech.smo;

public class ProcessingInformation {
    private final int deviceId;
    private final double startTime;
    private final double endTime;

    public ProcessingInformation(int deviceId, double startTime, double endTime) {
        this.deviceId = deviceId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }
}
