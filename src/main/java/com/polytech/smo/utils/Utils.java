package com.polytech.smo.utils;

import com.polytech.smo.SMOApplication;
import com.polytech.smo.devices.BufferDevice;
import com.polytech.smo.devices.ProcessingDevice;
import com.polytech.smo.events.Event;
import com.polytech.smo.table.TableBuffer;
import com.polytech.smo.table.TableEvent;
import com.polytech.smo.table.TableProcessingDevice;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Comparator;

public class Utils {
    public static double getPoissonDistributionTime(double lambda) {
        return (-1 / lambda) * Math.log(Math.random() / 100);
    }

    public static double getNormalDistributionTime(double a, double b) {
        return Math.random() * (b - a) + a;
    }

    public static void openWindow(String view, String title, Stage oldStage, boolean closeCurrentStage,
                                  boolean resizable) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SMOApplication.class.getResource(view));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.setScene(scene);
        if (closeCurrentStage) {
            oldStage.close();
        }
        stage.show();
    }

    public static Stage getStage(Node node) {
        return (Stage) node.getScene().getWindow();
    }

    public static Comparator<Event> eventComparator = Comparator.comparingDouble(Event::getEventTime);

    public static Comparator<ProcessingDevice> processingDevicePriorityComparator =
            Comparator.comparingInt(ProcessingDevice::getDeviceId);

    public static Comparator<BufferDevice> bufferDevicePriorityComparator =
            Comparator.comparingInt(BufferDevice::getDeviceId);

    public static Comparator<BufferDevice> bufferedEventTimeComparator =
            Comparator.comparingDouble(o -> o.getBufferedEvent().getEventTime());

    public static Comparator<TableEvent> tableEventComparator =
            Comparator.comparing(TableEvent::getEventTime);

    public static Comparator<TableBuffer> tableBufferComparator =
            Comparator.comparing(TableBuffer::getDeviceId);

    public static Comparator<TableProcessingDevice> tableProcessingDeviceComparator =
            Comparator.comparing(TableProcessingDevice::getDeviceId);
}
