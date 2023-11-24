package com.polytech.smo.table;

public class TableEvent {
    private double eventTime;
    private String message;

    public TableEvent(double eventTime, String message) {
        this.eventTime = eventTime;
        this.message = message;
    }

    public double getEventTime() {
        return eventTime;
    }

    public String getMessage() {
        return message;
    }

    public void setEventTime(double eventTime) {
        this.eventTime = eventTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
