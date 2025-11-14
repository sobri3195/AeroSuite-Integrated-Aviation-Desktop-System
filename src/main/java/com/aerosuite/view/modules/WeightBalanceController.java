package com.aerosuite.view.modules;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

public class WeightBalanceController {

    @FXML private ComboBox<String> aircraftTypeCombo;
    @FXML private TextField pilotWeightField;
    @FXML private TextField copilotWeightField;
    @FXML private TextField passengerWeightField;
    @FXML private TextField baggageWeightField;
    @FXML private TextField fuelWeightField;
    @FXML private Label totalWeightLabel;
    @FXML private Label cgPositionLabel;
    @FXML private Label statusLabel;
    @FXML private Label maxWeightLabel;
    @FXML private Label weightRemainingLabel;
    @FXML private LineChart<Number, Number> cgChart;

    @FXML
    public void initialize() {
        aircraftTypeCombo.setValue("Cessna 172");
        updateAircraftLimits();
        setupCGChart();
    }

    private void updateAircraftLimits() {
        String aircraftType = aircraftTypeCombo.getValue();
        switch (aircraftType) {
            case "Cessna 172":
                maxWeightLabel.setText("2550 lbs");
                break;
            case "Piper PA-28":
                maxWeightLabel.setText("2440 lbs");
                break;
            case "Diamond DA40":
                maxWeightLabel.setText("2646 lbs");
                break;
            case "Airbus A320 (Training)":
                maxWeightLabel.setText("170000 lbs");
                break;
        }
    }

    private void setupCGChart() {
        XYChart.Series<Number, Number> envelope = new XYChart.Series<>();
        envelope.setName("CG Envelope");
        
        envelope.getData().add(new XYChart.Data<>(35, 1500));
        envelope.getData().add(new XYChart.Data<>(35, 2550));
        envelope.getData().add(new XYChart.Data<>(47, 2550));
        envelope.getData().add(new XYChart.Data<>(47, 1500));
        envelope.getData().add(new XYChart.Data<>(35, 1500));
        
        cgChart.getData().add(envelope);
    }

    @FXML
    private void calculateWeightBalance() {
        try {
            double pilotWeight = Double.parseDouble(pilotWeightField.getText().isEmpty() ? "0" : pilotWeightField.getText());
            double copilotWeight = Double.parseDouble(copilotWeightField.getText().isEmpty() ? "0" : copilotWeightField.getText());
            double passengerWeight = Double.parseDouble(passengerWeightField.getText().isEmpty() ? "0" : passengerWeightField.getText());
            double baggageWeight = Double.parseDouble(baggageWeightField.getText().isEmpty() ? "0" : baggageWeightField.getText());
            double fuelWeight = Double.parseDouble(fuelWeightField.getText().isEmpty() ? "0" : fuelWeightField.getText());

            double emptyWeight = 1680.0;
            double emptyCG = 39.5;
            
            double pilotArm = 37.0;
            double copilotArm = 37.0;
            double passengerArm = 73.0;
            double baggageArm = 95.0;
            double fuelArm = 48.0;

            double totalMoment = (emptyWeight * emptyCG) +
                    (pilotWeight * pilotArm) +
                    (copilotWeight * copilotArm) +
                    (passengerWeight * passengerArm) +
                    (baggageWeight * baggageArm) +
                    (fuelWeight * fuelArm);

            double totalWeight = emptyWeight + pilotWeight + copilotWeight + passengerWeight + baggageWeight + fuelWeight;
            double cgPosition = totalMoment / totalWeight;

            totalWeightLabel.setText(String.format("%.0f lbs", totalWeight));
            cgPositionLabel.setText(String.format("%.2f in", cgPosition));

            double maxWeight = 2550.0;
            double weightRemaining = maxWeight - totalWeight;
            weightRemainingLabel.setText(String.format("%.0f lbs", weightRemaining));

            boolean withinLimits = (totalWeight <= maxWeight) && (cgPosition >= 35.0 && cgPosition <= 47.0);
            
            if (withinLimits) {
                statusLabel.setText("✓ WITHIN LIMITS");
                statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
            } else {
                statusLabel.setText("✗ OUT OF LIMITS");
                statusLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
            }

            XYChart.Series<Number, Number> currentPoint = new XYChart.Series<>();
            currentPoint.setName("Current");
            currentPoint.getData().add(new XYChart.Data<>(cgPosition, totalWeight));
            
            if (cgChart.getData().size() > 1) {
                cgChart.getData().remove(1);
            }
            cgChart.getData().add(currentPoint);

        } catch (NumberFormatException e) {
            showError("Please enter valid numbers");
        }
    }

    @FXML
    private void clearForm() {
        pilotWeightField.clear();
        copilotWeightField.clear();
        passengerWeightField.clear();
        baggageWeightField.clear();
        fuelWeightField.clear();
        totalWeightLabel.setText("0 lbs");
        cgPositionLabel.setText("0.0 in");
        statusLabel.setText("Not Calculated");
        statusLabel.setStyle("");
        
        if (cgChart.getData().size() > 1) {
            cgChart.getData().remove(1);
        }
    }

    @FXML
    private void saveConfiguration() {
        showInfo("Configuration saved to database");
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
