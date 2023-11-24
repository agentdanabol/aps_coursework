package com.polytech.smo.events;

public class Event {
    private final EventTypes eventType;
    private final double eventTime;
    private final int deviceId;
    private final int count;

    public Event(EventTypes eventType, double eventTime, int deviceId, int count) {
        this.eventType = eventType;
        this.eventTime = eventTime;
        this.deviceId = deviceId;
        this.count = count;
    }

    public EventTypes getEventType() {
        return eventType;
    }

    public double getEventTime() {
        return eventTime;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public int getCount() {
        return count;
    }
}