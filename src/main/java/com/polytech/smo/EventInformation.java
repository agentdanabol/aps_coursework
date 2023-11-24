package com.polytech.smo;

public class EventInformation {
    private int deviceId;
    private int eventId;
    private double generationTime;
    private double setInBufferTime;
    private double outFromBufferTime;
    private double setOnProcessingDeviceTime;
    private double outFromSystemTime;
    private boolean kickedFromSystem;

    public EventInformation(int deviceId, int eventId, double generationTime) {
        this.deviceId = deviceId;
        this.eventId = eventId;
        this.generationTime = generationTime;
        this.setInBufferTime = 0;
        this.outFromBufferTime = 0;
        this.setOnProcessingDeviceTime = 0;
        this.outFromSystemTime = 0;
        this.kickedFromSystem = false;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public double getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(double generationTime) {
        this.generationTime = generationTime;
    }

    public double getSetInBufferTime() {
        return setInBufferTime;
    }

    public void setSetInBufferTime(double setInBufferTime) {
        this.setInBufferTime = setInBufferTime;
    }

    public double getOutFromBufferTime() {
        return outFromBufferTime;
    }

    public void setOutFromBufferTime(double outFromBufferTime) {
        this.outFromBufferTime = outFromBufferTime;
    }

    public double getSetOnProcessingDeviceTime() {
        return setOnProcessingDeviceTime;
    }

    public void setSetOnProcessingDeviceTime(double setOnProcessingDeviceTime) {
        this.setOnProcessingDeviceTime = setOnProcessingDeviceTime;
    }

    public double getOutFromSystemTime() {
        return outFromSystemTime;
    }

    public void setOutFromSystemTime(double outFromSystemTime) {
        this.outFromSystemTime = outFromSystemTime;
    }

    public boolean isKickedFromSystem() {
        return kickedFromSystem;
    }

    public void setKickedFromSystem(boolean kickedFromSystem) {
        this.kickedFromSystem = kickedFromSystem;
    }
}
