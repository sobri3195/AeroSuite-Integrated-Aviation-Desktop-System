package com.aerosuite.view.modules;

import com.aerosuite.dao.AircraftDAO;
import com.aerosuite.dao.PilotDAO;
import com.aerosuite.model.Aircraft;
import com.aerosuite.model.Pilot;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class FlightPlanningController {

    @FXML private TextField planNameField;
    @FXML private ComboBox<Pilot> pilotCombo;
    @FXML private ComboBox<Aircraft> aircraftCombo;
    @FXML private TextField departureField;
    @FXML private TextField arrivalField;
    @FXML private TextField routeField;
    @FXML private TextField altitudeField;
    @FXML private TextField groundSpeedField;
    @FXML private TextField windDirectionField;
    @FXML private TextField windSpeedField;
    @FXML private TextField taxiFuelField;
    @FXML private TextField tripFuelField;
    @FXML private TextField reserveFuelField;
    @FXML private Label totalFuelLabel;
    @FXML private TextField etdField;
    @FXML private Label etaLabel;
    @FXML private Label durationLabel;
    @FXML private TextArea notamArea;
    @FXML private TextArea weatherArea;
    @FXML private CheckBox saveAsTemplateCheck;

    private PilotDAO pilotDAO = new PilotDAO();
    private AircraftDAO aircraftDAO = new AircraftDAO();

    @FXML
    public void initialize() {
        loadData();
        setupListeners();
    }

    private void loadData() {
        try {
            pilotCombo.setItems(FXCollections.observableArrayList(pilotDAO.findAll()));
            aircraftCombo.setItems(FXCollections.observableArrayList(aircraftDAO.findAll()));
        } catch (SQLException e) {
            showError("Failed to load data: " + e.getMessage());
        }
    }

    private void setupListeners() {
        taxiFuelField.textProperty().addListener((obs, oldVal, newVal) -> calculateTotalFuel());
        tripFuelField.textProperty().addListener((obs, oldVal, newVal) -> calculateTotalFuel());
        reserveFuelField.textProperty().addListener((obs, oldVal, newVal) -> calculateTotalFuel());
    }

    @FXML
    private void calculateFlightPlan() {
        try {
            if (groundSpeedField.getText().isEmpty()) {
                showError("Please enter ground speed");
                return;
            }

            double groundSpeed = Double.parseDouble(groundSpeedField.getText());
            double distance = 150.0;
            
            double duration = distance / groundSpeed;
            durationLabel.setText(String.format("%.2f hrs", duration));

            Aircraft aircraft = aircraftCombo.getValue();
            if (aircraft != null && aircraft.getFuelConsumptionRate() != null) {
                double tripFuel = duration * aircraft.getFuelConsumptionRate();
                tripFuelField.setText(String.format("%.1f", tripFuel));
            }

            if (!etdField.getText().isEmpty()) {
                String[] etdParts = etdField.getText().split(":");
                if (etdParts.length == 2) {
                    int etdHour = Integer.parseInt(etdParts[0]);
                    int etdMin = Integer.parseInt(etdParts[1]);
                    
                    int totalMinutes = etdHour * 60 + etdMin + (int)(duration * 60);
                    int etaHour = (totalMinutes / 60) % 24;
                    int etaMin = totalMinutes % 60;
                    
                    etaLabel.setText(String.format("%02d:%02d", etaHour, etaMin));
                }
            }

            showInfo("Flight plan calculated successfully!");

        } catch (NumberFormatException e) {
            showError("Please enter valid numbers");
        }
    }

    private void calculateTotalFuel() {
        try {
            double taxi = taxiFuelField.getText().isEmpty() ? 0 : Double.parseDouble(taxiFuelField.getText());
            double trip = tripFuelField.getText().isEmpty() ? 0 : Double.parseDouble(tripFuelField.getText());
            double reserve = reserveFuelField.getText().isEmpty() ? 0 : Double.parseDouble(reserveFuelField.getText());
            
            double total = taxi + trip + reserve;
            totalFuelLabel.setText(String.format("%.1f", total));
        } catch (NumberFormatException e) {
        }
    }

    @FXML
    private void clearForm() {
        planNameField.clear();
        departureField.clear();
        arrivalField.clear();
        routeField.clear();
        altitudeField.clear();
        groundSpeedField.clear();
        windDirectionField.clear();
        windSpeedField.clear();
        taxiFuelField.clear();
        tripFuelField.clear();
        reserveFuelField.clear();
        etdField.clear();
        notamArea.clear();
        weatherArea.clear();
        etaLabel.setText("--:--");
        durationLabel.setText("0.0 hrs");
        totalFuelLabel.setText("0.0");
    }

    @FXML
    private void savePlan() {
        showInfo("Flight plan save functionality will store to database");
    }

    @FXML
    private void loadTemplate() {
        showInfo("Template loading functionality will retrieve saved templates");
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
