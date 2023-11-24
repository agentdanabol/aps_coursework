package com.polytech.smo.table;

public class TableSource {
    private final int num;
    private final int eventCount;
    private final double rejectPercent;
    private final double avgBufferTime;
    private final double avgFullTime;
    private final double avgProcessTime;
    private final double dBufferTime;
    private final double dProcessingTime;

    public TableSource(int num, int eventCount, double rejectPercent, double avgBufferTime, double avgFullTime, double avgProcessTime, double dBufferTime, double dProcessingTime) {
        this.num = num;
        this.eventCount = eventCount;
        this.rejectPercent = rejectPercent;
        this.avgBufferTime = avgBufferTime;
        this.avgFullTime = avgFullTime;
        this.avgProcessTime = avgProcessTime;
        this.dBufferTime = dBufferTime;
        this.dProcessingTime = dProcessingTime;
    }

    public int getNum() {
        return num;
    }

    public int getEventCount() {
        return eventCount;
    }

    public double getRejectPercent() {
        return rejectPercent;
    }

    public double getAvgBufferTime() {
        return avgBufferTime;
    }

    public double getAvgFullTime() {
        return avgFullTime;
    }

    public double getAvgProcessTime() {
        return avgProcessTime;
    }

    public double getDBufferTime() {
        return dBufferTime;
    }

    public double getDProcessingTime() {
        return dProcessingTime;
    }
}
