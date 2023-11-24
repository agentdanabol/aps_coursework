package com.polytech.smo.devices;

import com.polytech.smo.EventInformation;
import com.polytech.smo.SMOApplication;
import com.polytech.smo.events.Event;
import com.polytech.smo.events.EventTypes;
import com.polytech.smo.table.SourceTable;
import com.polytech.smo.table.TableEvent;
import com.polytech.smo.utils.Utils;
import com.polytech.smo.view.ModelingController;

import java.util.List;

public class SourceDevice {
    private final int deviceId;
    private double currentTime;
    private double nextGenerationTime;
    private final List<Event> events;
    private final double lambda;
    private int count;

    public SourceDevice(double lambda, List<Event> events, int deviceId) {
        currentTime = Utils.getPoissonDistributionTime(lambda);
        this.lambda = lambda;
        nextGenerationTime = currentTime + Utils.getPoissonDistributionTime(lambda);
        this.events = events;
        this.deviceId = deviceId;
        this.count = 0;

        ModelingController.tableSources.add(new SourceTable(this.deviceId, currentTime));
    }

    public void generateEvent() {
        count++;
        events.add(new Event(EventTypes.Generation, currentTime, deviceId, count));

        ModelingController.tableSources.removeIf(device -> device.getDeviceId() == deviceId);
        ModelingController.tableSources.add(new SourceTable(deviceId, currentTime));

        SMOApplication.statisticCollector.addEvent(new EventInformation(deviceId, count, currentTime));

        ModelingController.tableEvents.add(new TableEvent(currentTime, "Сгенерирована заявка " + deviceId + "." + count));
        currentTime = nextGenerationTime;
        nextGenerationTime += Utils.getPoissonDistributionTime(lambda);
    }

    public int getDeviceId() {
        return deviceId;
    }
}
