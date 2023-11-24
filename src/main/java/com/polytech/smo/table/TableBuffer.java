package com.polytech.smo.table;

public class TableBuffer {
    private int deviceId;
    private String state;

    public TableBuffer(int deviceId, String state) {
        this.deviceId = deviceId;
        this.state = state;
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
}