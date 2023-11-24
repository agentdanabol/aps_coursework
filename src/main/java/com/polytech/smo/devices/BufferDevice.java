package com.polytech.smo.devices;

import com.polytech.smo.SMOApplication;
import com.polytech.smo.events.Event;
import com.polytech.smo.table.TableBuffer;
import com.polytech.smo.table.TableEvent;
import com.polytech.smo.view.ModelingController;

public class BufferDevice {
    private final int deviceId;
    private Event bufferedEvent;
    private boolean isBuffered;

    public BufferDevice(int deviceId) {
        this.deviceId = deviceId;
        this.bufferedEvent = null;
        this.isBuffered = false;
    }

    public void bufferEvent(Event event) {
        this.isBuffered = true;
        this.bufferedEvent = event;
        ModelingController.tableEvents.add(new TableEvent(event.getEventTime(),
                "Заявка " + event.getDeviceId() + "." + event.getCount() + " отправлена в буфер " + deviceId));
        ModelingController.tableBuffers.removeIf(device -> device.getDeviceId() == deviceId);
        ModelingController.tableBuffers.add(new TableBuffer(deviceId, "Ожидание заявки " +
                event.getDeviceId() + "." + event.getCount()));

        SMOApplication.statisticCollector.addSetInBufferTime(event);
    }

    public void freeAndBufferNewEvent(Event event) {
        ModelingController.tableBuffers.removeIf(device -> device.getDeviceId() == deviceId);
        ModelingController.tableBuffers.add(new TableBuffer(deviceId, "Свободен"));
        ModelingController.tableEvents.add(new TableEvent(event.getEventTime(),
                "Заявка " + bufferedEvent.getDeviceId() + "." + bufferedEvent.getCount() + " ушла в отказ"));

        SMOApplication.statisticCollector.addOutFromBufferTimeAndKick(bufferedEvent, event.getEventTime());

        bufferEvent(event);
    }

    public Event getEventAndFreeDevice(double currentTime) {
        ModelingController.tableBuffers.removeIf(device -> device.getDeviceId() == deviceId);
        ModelingController.tableBuffers.add(new TableBuffer(deviceId, "Свободен"));
        ModelingController.tableEvents.add(new TableEvent(currentTime, "Освобождение буфера " + deviceId));

        SMOApplication.statisticCollector.addOutFromBufferTime(bufferedEvent, currentTime);

        this.isBuffered = false;
        Event event = this.bufferedEvent;
        this.bufferedEvent = null;
        return event;
    }

    public Event getBufferedEvent() {
        return bufferedEvent;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public boolean isFree() {
        return !isBuffered;
    }

    public boolean isBuffered_() {
        return isBuffered;
    }
}
