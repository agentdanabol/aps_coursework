package com.polytech.smo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SMOApplication extends Application {
    public static SystemController systemController;
    public static StatisticCollector statisticCollector;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SMOApplication.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Настройки");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void initializeSystemController(int sourceDevicesCount, int bufferDevicesCount, int processingDevicesCount,
                                                  double lambda, double a, double b) {
        systemController = new SystemController(sourceDevicesCount, bufferDevicesCount, processingDevicesCount,
                lambda, a, b);
    }

    public static void initializeStatisticController(int eventsCount) {
        statisticCollector = new StatisticCollector(eventsCount);
    }
}

