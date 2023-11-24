module com.polytech.smo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.polytech.smo to javafx.fxml;
    opens com.polytech.smo.view to javafx.fxml;


    exports com.polytech.smo;
    exports com.polytech.smo.view;
    exports com.polytech.smo.table;
    exports com.polytech.smo.events;
    exports com.polytech.smo.devices;
}
