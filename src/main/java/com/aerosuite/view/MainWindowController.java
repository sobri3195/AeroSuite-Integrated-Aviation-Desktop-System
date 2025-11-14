package com.aerosuite.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private VBox sidebarMenu;

    @FXML
    private Label titleLabel;

    @FXML
    public void initialize() {
        loadDashboard();
    }

    @FXML
    private void loadDashboard() {
        loadView("/fxml/modules/Dashboard.fxml", "Dashboard");
    }

    @FXML
    private void loadLogbook() {
        loadView("/fxml/modules/Logbook.fxml", "Flight Logbook Digital");
    }

    @FXML
    private void loadFlightPlanning() {
        loadView("/fxml/modules/FlightPlanning.fxml", "Flight Planning Assistant");
    }

    @FXML
    private void loadWeightBalance() {
        loadView("/fxml/modules/WeightBalance.fxml", "Weight & Balance Simulator");
    }

    @FXML
    private void loadCBT() {
        loadView("/fxml/modules/CBT.fxml", "Computer Based Training");
    }

    @FXML
    private void loadMaintenance() {
        loadView("/fxml/modules/Maintenance.fxml", "Aircraft Maintenance");
    }

    @FXML
    private void loadPerformance() {
        loadView("/fxml/modules/Performance.fxml", "Performance Calculator");
    }

    @FXML
    private void loadATCPhraseology() {
        loadView("/fxml/modules/ATCPhraseology.fxml", "ATC Phraseology Trainer");
    }

    @FXML
    private void loadEmergency() {
        loadView("/fxml/modules/Emergency.fxml", "Emergency Checklist Trainer");
    }

    @FXML
    private void loadDrone() {
        loadView("/fxml/modules/Drone.fxml", "Drone Flight Planner");
    }

    @FXML
    private void loadSimulator() {
        loadView("/fxml/modules/Simulator.fxml", "2D Flight Simulator");
    }

    private void loadView(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            mainBorderPane.setCenter(view);
            titleLabel.setText(title);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorAlert("Error loading view: " + e.getMessage());
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
