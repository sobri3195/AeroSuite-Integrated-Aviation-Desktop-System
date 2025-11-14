package com.aerosuite.view.modules;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DroneController {

    @FXML private ComboBox droneCombo;
    @FXML private ComboBox missionTypeCombo;
    @FXML private TextField locationField;
    @FXML private TextField altitudeField;
    @FXML private TextField speedField;
    @FXML private TextArea waypointsArea;
    @FXML private Label durationLabel;
    @FXML private Label distanceLabel;
    @FXML private TableView flightLogTable;
    @FXML private TableView batteryTable;

    @FXML
    public void initialize() {
        missionTypeCombo.setValue("Survey");
    }

    @FXML
    private void newFlightPlan() {
        showInfo("Create new drone flight plan");
    }

    @FXML
    private void calculateMission() {
        try {
            String[] waypoints = waypointsArea.getText().split("\n");
            int waypointCount = waypoints.length;
            
            double speed = Double.parseDouble(speedField.getText().isEmpty() ? "5" : speedField.getText());
            double distance = waypointCount * 100;
            double duration = distance / speed / 60;
            
            distanceLabel.setText(String.format("%.0f m", distance));
            durationLabel.setText(String.format("%.1f min", duration));
            
        } catch (NumberFormatException e) {
            showError("Please enter valid numbers");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
