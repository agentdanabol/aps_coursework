package com.polytech.smo.view;

import com.polytech.smo.SMOApplication;
import com.polytech.smo.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SettingsController {
    @FXML
    private TextField sourceDeviceCountLabel;
    @FXML
    private TextField bufferDeviceCountLabel;
    @FXML
    private TextField processingDeviceCountLabel;
    @FXML
    private TextField lambdaLabel;
    @FXML
    private TextField aLabel;
    @FXML
    private TextField bLabel;
    @FXML
    private TextField autoModeEventsCount;

    @FXML
    protected void onApplyButtonClick() {
        try {
            int sourceDevicesCount = Integer.parseInt(sourceDeviceCountLabel.getText());
            int bufferDevicesCount = Integer.parseInt(bufferDeviceCountLabel.getText());
            int processingDevicesCount = Integer.parseInt(processingDeviceCountLabel.getText());
            double lambda = Double.parseDouble(lambdaLabel.getText());
            double a = Double.parseDouble(aLabel.getText());
            double b = Double.parseDouble(bLabel.getText());
            int eventsCount = Integer.parseInt(autoModeEventsCount.getText());

            SMOApplication.initializeSystemController(sourceDevicesCount, bufferDevicesCount, processingDevicesCount,
                    lambda, a, b);

            SMOApplication.initializeStatisticController(eventsCount);

            Utils.openWindow("modeling-view.fxml", "Моделирование", Utils.getStage(sourceDeviceCountLabel),
                    true, true);
        } catch (NumberFormatException | IOException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage(), ButtonType.OK).showAndWait();
        }
    }
}
