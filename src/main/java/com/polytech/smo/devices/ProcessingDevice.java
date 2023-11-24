package com.polytech.smo.devices;

import com.polytech.smo.ProcessingInformation;
import com.polytech.smo.SMOApplication;
import com.polytech.smo.events.Event;
import com.polytech.smo.events.EventTypes;
import com.polytech.smo.table.TableEvent;
import com.polytech.smo.table.TableProcessingDevice;
import com.polytech.smo.utils.Utils;
import com.polytech.smo.view.ModelingController;

import java.util.List;

public class ProcessingDevice {
    private final int deviceId;
    private Event processingEvent;
    private double endProcessingTime;
    private final List<Event> events;
    private boolean isProcessing;
    private final double a;
    private final double b;

    public ProcessingDevice(int deviceId, double a, double b, List<Event> events) {
        this.deviceId = deviceId;
        this.isProcessing = false;
        this.events = events;
        this.a = a;
        this.b = b;
        this.processingEvent = null;
        this.endProcessingTime = 0;
    }

    public void setEventOnProcess(Event event, double startTime) {
        isProcessing = true;
        processingEvent = event;
        this.endProcessingTime = startTime + Utils.getNormalDistributionTime(a, b);
        events.add(new Event(EventTypes.EndProcessing, endProcessingTime, event.getDeviceId(), event.getCount()));
        ModelingController.tableEvents.add(new TableEvent(startTime, "Постановка заявки " +
                event.getDeviceId() + "." + event.getCount() + " на прибор " + deviceId));

        ModelingController.tableProcessingDevices.removeIf(device -> device.getDeviceId() == deviceId);
        ModelingController.tableProcessingDevices.add(new TableProcessingDevice(deviceId, "Выполняет заявку " +
                event.getDeviceId() + "." + event.getCount(), startTime, endProcessingTime));

        SMOApplication.statisticCollector.addProcessingTime(event, startTime, endProcessingTime);
        SMOApplication.statisticCollector.addProcessingInfo(new ProcessingInformation(deviceId, startTime, endProcessingTime));
    }

    public void free() {
        ModelingController.tableProcessingDevices.removeIf(device -> device.getDeviceId() == deviceId);
        ModelingController.tableProcessingDevices.add(new TableProcessingDevice(deviceId, "Простой",
                0.0, 0.0));
        ModelingController.tableEvents.add(new TableEvent(endProcessingTime,
                "Освобождение прибора " + deviceId));
        isProcessing = false;
        processingEvent = null;
    }

    public boolean isFree() {
        return !isProcessing;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public Event getProcessingEvent() {
        return processingEvent;
    }
}
