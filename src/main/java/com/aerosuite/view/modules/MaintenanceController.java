package com.aerosuite.view.modules;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MaintenanceController {

    @FXML private ListView aircraftList;
    @FXML private TableView maintenanceTable;
    @FXML private TextArea remindersArea;

    @FXML
    public void initialize() {
    }

    @FXML
    private void addMaintenanceLog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Maintenance Log");
        alert.setContentText("Maintenance log entry dialog will be implemented here");
        alert.showAndWait();
    }
}
