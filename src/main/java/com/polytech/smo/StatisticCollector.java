package com.polytech.smo;

import com.polytech.smo.events.Event;
import com.polytech.smo.table.TableSource;
import com.polytech.smo.table.TableUsingCoeff;
import com.polytech.smo.view.ModelingController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StatisticCollector {
    private final int eventsCount;
    private int currentEventCount;
    private final List<EventInformation> eventInformationList;
    private final List<ProcessingInformation> processingInformationList;

    public StatisticCollector(int eventsCount) {
        this.eventsCount = eventsCount;
        this.currentEventCount = 0;
        this.eventInformationList = new ArrayList<>();
        this.processingInformationList = new ArrayList<>();
    }

    public void reset() {
        this.currentEventCount = 0;
        this.eventInformationList.clear();
        this.processingInformationList.clear();
    }

    public int getEventsCount() {
        return eventsCount;
    }

    public int getCurrentEventCount() {
        return currentEventCount;
    }

    public void calculateStatistic() {
        for (int i = 0; i < SMOApplication.systemController.getSourceDevicesCount(); i++) {
            int currentDevice = i + 1;
            List<EventInformation> list = eventInformationList.stream().filter(info -> info.getDeviceId() == currentDevice).toList();

            double fullSystemTime = 0;
            double processTime = 0;
            double processTimeSquare = 0;
            double bufferTime = 0;
            double bufferTimeSquare = 0;
            double abortCount = 0;
            int processedEventCount = 0;
            int allEventCount = 0;
            for (EventInformation info : list) {
                if (info.getOutFromSystemTime() != 0) {
                    allEventCount++;
                    fullSystemTime += (info.getOutFromSystemTime() - info.getGenerationTime());
                    bufferTime += (info.getOutFromBufferTime() - info.getSetInBufferTime());

                    if (info.isKickedFromSystem()) {
                        abortCount++;
                    } else {
                        processedEventCount++;
                        processTime += (info.getOutFromSystemTime() - info.getSetOnProcessingDeviceTime());
                    }
                }
            }

            double avgBufferTime = bufferTime / allEventCount;
            double avgProcessTime = processTime / processedEventCount;
            for (EventInformation info : list) {
                bufferTimeSquare = Math.pow(avgBufferTime - (info.getOutFromBufferTime() - info.getSetInBufferTime()), 2);
                if (!info.isKickedFromSystem()) {
                    processTimeSquare += Math.pow(avgProcessTime - (info.getOutFromSystemTime() - info.getSetOnProcessingDeviceTime()), 2);
                }
            }

            ModelingController.tableSourceStatItems.add(new TableSource(currentDevice, allEventCount, abortCount / allEventCount * 100, avgBufferTime, fullSystemTime / allEventCount,
                    avgProcessTime, bufferTimeSquare / allEventCount, processTimeSquare / processedEventCount));
        }

        for (int i = 0; i < SMOApplication.systemController.getProcessingDeviceCount(); i++) {
            int currentDevice = i + 1;
            List<ProcessingInformation> list = processingInformationList.stream().filter(info -> info.getDeviceId() == currentDevice).toList();

            double workTime = list.stream().max((Comparator.comparingDouble(ProcessingInformation::getEndTime))).get().getEndTime();
            double processTime = 0;
            for (ProcessingInformation info : list) {
                processTime += (info.getEndTime() - info.getStartTime());
            }

            ModelingController.tableUsingCoeffItems.add(new TableUsingCoeff(currentDevice, processTime / workTime));
        }
    }

    public void addEvent(EventInformation eventInformation) {
        eventInformationList.add(eventInformation);
        currentEventCount++;
    }

    public void addSetInBufferTime(Event event) {
        Optional<EventInformation> eventInformation = eventInformationList.stream().filter(info ->
                info.getDeviceId() == event.getDeviceId() && info.getEventId() == event.getCount()).findFirst();
        eventInformation.ifPresent(info -> info.setSetInBufferTime(event.getEventTime()));
    }

    public void addOutFromBufferTime(Event event, double outTime) {
        Optional<EventInformation> eventInformation = eventInformationList.stream().filter(info ->
                info.getDeviceId() == event.getDeviceId() && info.getEventId() == event.getCount()).findFirst();
        eventInformation.ifPresent(info -> info.setOutFromBufferTime(outTime));
    }

    public void addOutFromBufferTimeAndKick(Event event, double outTime) {
        Optional<EventInformation> eventInformation = eventInformationList.stream().filter(info ->
                info.getDeviceId() == event.getDeviceId() && info.getEventId() == event.getCount()).findFirst();
        eventInformation.ifPresent(info -> {
            info.setOutFromBufferTime(outTime);
            info.setOutFromSystemTime(outTime);
            info.setKickedFromSystem(true);
        });
    }

    public void addProcessingTime(Event event, double startTime, double endProcessingTime) {
        Optional<EventInformation> eventInformation = eventInformationList.stream().filter(info ->
                info.getDeviceId() == event.getDeviceId() && info.getEventId() == event.getCount()).findFirst();
        eventInformation.ifPresent(info -> {
            info.setSetOnProcessingDeviceTime(startTime);
            info.setOutFromSystemTime(endProcessingTime);
        });
    }

    public void addProcessingInfo(ProcessingInformation processingInformation) {
        processingInformationList.add(processingInformation);
    }
}
