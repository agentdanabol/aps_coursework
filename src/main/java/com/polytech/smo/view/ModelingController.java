package com.polytech.smo.view;

import com.polytech.smo.SMOApplication;
import com.polytech.smo.table.*;
import com.polytech.smo.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModelingController {
    @FXML
    private TableView<TableEvent> eventTable;
    @FXML
    private TableColumn<TableEvent, Double> eventColumn;
    @FXML
    private TableColumn<TableEvent, String> eventTimeColumn;
    @FXML
    private TableView<TableBuffer> bufferTable;
    @FXML
    private TableColumn<TableBuffer, Integer> bufferNum;
    @FXML
    private TableColumn<TableBuffer, String> bufferState;
    @FXML
    private TableView<TableProcessingDevice> processingDeviceTable;
    @FXML
    private TableColumn<TableProcessingDevice, Integer> processingDeviceNum;
    @FXML
    private TableColumn<TableProcessingDevice, String> processingDeviceState;
    @FXML
    private TableColumn<TableProcessingDevice, Double> startTime;
    @FXML
    private TableColumn<TableProcessingDevice, Double> endTime;
    @FXML
    private TableView<TableSource> sourceStatTable;
    @FXML
    private TableColumn<TableSource, Integer> sourceNumColumn;
    @FXML
    private TableColumn<TableSource, Integer> eventCountColumn;
    @FXML
    private TableColumn<TableSource, Double> rejectPercentColumn;
    @FXML
    private TableColumn<TableSource, Double> avgBufferTimeColumn;
    @FXML
    private TableColumn<TableSource, Double> avgFullTimeColumn;
    @FXML
    private TableColumn<TableSource, Double> avgProcessTimeColumn;
    @FXML
    private TableColumn<TableSource, Double> dBufferColumn;
    @FXML
    private TableColumn<TableSource, Double> dProcessingColumn;
    @FXML
    private TableView<TableUsingCoeff> coeffUsingTable;
    @FXML
    private TableColumn<TableUsingCoeff, Integer> processingDeviceNumColumn;
    @FXML
    private TableColumn<TableUsingCoeff, Double> usingCoeffColumn;
    public static final ObservableList<TableEvent> tableEvents = FXCollections.observableArrayList();
    public static final ObservableList<TableBuffer> tableBuffers = FXCollections.observableArrayList();
    public static final ObservableList<TableProcessingDevice> tableProcessingDevices = FXCollections.observableArrayList();
    public static final ObservableList<TableSource> tableSourceStatItems = FXCollections.observableArrayList();
    public static final ObservableList<TableUsingCoeff> tableUsingCoeffItems = FXCollections.observableArrayList();
    public static final ObservableList<SourceTable> tableSources = FXCollections.observableArrayList();
    @FXML
    private TableView<SourceTable> sourceTable;
    @FXML
    private TableColumn<SourceTable, Integer> sourceNum;
    @FXML
    private TableColumn<SourceTable, String> genTime;

    @FXML
    protected void initialize() {
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        eventTimeColumn.setCellValueFactory(new PropertyValueFactory<>("eventTime"));
        eventTable.setItems(tableEvents);

        bufferNum.setCellValueFactory(new PropertyValueFactory<>("deviceId"));
        bufferState.setCellValueFactory(new PropertyValueFactory<>("state"));
        bufferTable.setItems(tableBuffers);

        sourceNum.setCellValueFactory(new PropertyValueFactory<>("deviceId"));
        genTime.setCellValueFactory(new PropertyValueFactory<>("nextGenerationTime"));
        sourceTable.setItems(tableSources);

        processingDeviceNum.setCellValueFactory(new PropertyValueFactory<>("deviceId"));
        processingDeviceState.setCellValueFactory(new PropertyValueFactory<>("state"));
        startTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        processingDeviceTable.setItems(tableProcessingDevices);

        sourceNumColumn.setCellValueFactory(new PropertyValueFactory<>("num"));
        eventCountColumn.setCellValueFactory(new PropertyValueFactory<>("eventCount"));
        rejectPercentColumn.setCellValueFactory(new PropertyValueFactory<>("rejectPercent"));
        avgBufferTimeColumn.setCellValueFactory(new PropertyValueFactory<>("avgBufferTime"));
        avgFullTimeColumn.setCellValueFactory(new PropertyValueFactory<>("avgFullTime"));
        avgProcessTimeColumn.setCellValueFactory(new PropertyValueFactory<>("avgProcessTime"));
        dBufferColumn.setCellValueFactory(new PropertyValueFactory<>("dBufferTime"));
        dProcessingColumn.setCellValueFactory(new PropertyValueFactory<>("dProcessingTime"));
        sourceStatTable.setItems(tableSourceStatItems);

        processingDeviceNumColumn.setCellValueFactory(new PropertyValueFactory<>("deviceNum"));
        usingCoeffColumn.setCellValueFactory(new PropertyValueFactory<>("usingCoeff"));
        coeffUsingTable.setItems(tableUsingCoeffItems);
    }

    @FXML
    protected void onNextStepButtonClick() {
        SMOApplication.systemController.userClickNextButton();
        tableEvents.sort(Utils.tableEventComparator);
        tableBuffers.sort(Utils.tableBufferComparator);
        tableProcessingDevices.sort(Utils.tableProcessingDeviceComparator);

        if (tableEvents.size() >= 15) {
            tableEvents.remove(0, tableEvents.size() - 15 );
        }
    }

    @FXML
    protected void startAutoModeClick() {
        SMOApplication.statisticCollector.reset();
        int eventsCount = SMOApplication.statisticCollector.getEventsCount();

        while (SMOApplication.statisticCollector.getCurrentEventCount() < eventsCount) {
            SMOApplication.systemController.userClickNextButton();
        }

        SMOApplication.statisticCollector.calculateStatistic();
    }
}
