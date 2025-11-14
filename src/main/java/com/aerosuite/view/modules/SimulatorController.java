package com.aerosuite.view.modules;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class SimulatorController {

    @FXML private Slider throttleSlider;
    @FXML private Slider pitchSlider;
    @FXML private Slider rollSlider;
    @FXML private ComboBox<String> modeCombo;
    @FXML private Canvas displayCanvas;
    @FXML private Label airspeedLabel;
    @FXML private Label altitudeLabel;
    @FXML private Label verticalSpeedLabel;
    @FXML private Label headingLabel;
    @FXML private Label pitchLabel;
    @FXML private Label rollLabel;
    @FXML private Label feedbackLabel;

    private AnimationTimer simulator;
    private double airspeed = 80;
    private double altitude = 3000;
    private double heading = 0;
    private boolean isRunning = false;

    @FXML
    public void initialize() {
        modeCombo.setValue("Straight & Level");
        
        throttleSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateInstruments());
        pitchSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateInstruments());
        rollSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateInstruments());
        
        drawHorizon();
    }

    @FXML
    private void startSimulation() {
        if (!isRunning) {
            isRunning = true;
            simulator = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    updateSimulation();
                }
            };
            simulator.start();
            feedbackLabel.setText("Simulation running...");
            feedbackLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
        }
    }

    @FXML
    private void resetSimulation() {
        if (simulator != null) {
            simulator.stop();
        }
        isRunning = false;
        throttleSlider.setValue(50);
        pitchSlider.setValue(0);
        rollSlider.setValue(0);
        airspeed = 80;
        altitude = 3000;
        heading = 0;
        updateInstruments();
        drawHorizon();
        feedbackLabel.setText("Ready to fly");
        feedbackLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
    }

    private void updateSimulation() {
        double throttle = throttleSlider.getValue();
        double pitch = pitchSlider.getValue();
        double roll = rollSlider.getValue();

        airspeed = 60 + (throttle * 0.8);
        double verticalSpeed = pitch * 100;
        altitude += verticalSpeed / 60;
        heading += roll * 0.1;
        if (heading < 0) heading += 360;
        if (heading >= 360) heading -= 360;

        updateInstruments();
        drawHorizon();
        provideFeedback();
    }

    private void updateInstruments() {
        double throttle = throttleSlider.getValue();
        double pitch = pitchSlider.getValue();
        double roll = rollSlider.getValue();

        airspeed = 60 + (throttle * 0.8);
        double verticalSpeed = pitch * 100;

        airspeedLabel.setText(String.format("%.0f kts", airspeed));
        altitudeLabel.setText(String.format("%.0f ft", altitude));
        verticalSpeedLabel.setText(String.format("%.0f fpm", verticalSpeed));
        headingLabel.setText(String.format("%03.0f°", heading));
        pitchLabel.setText(String.format("%.1f°", pitch));
        rollLabel.setText(String.format("%.1f°", roll));
    }

    private void drawHorizon() {
        GraphicsContext gc = displayCanvas.getGraphicsContext2D();
        double width = displayCanvas.getWidth();
        double height = displayCanvas.getHeight();
        double centerX = width / 2;
        double centerY = height / 2;

        gc.clearRect(0, 0, width, height);

        double pitch = pitchSlider.getValue();
        double roll = rollSlider.getValue();

        double horizonY = centerY + (pitch * 5);

        gc.setFill(Color.SKYBLUE);
        gc.fillRect(0, 0, width, horizonY);

        gc.setFill(Color.BROWN);
        gc.fillRect(0, horizonY, width, height - horizonY);

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3);
        gc.strokeLine(0, horizonY, width, horizonY);

        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(2);
        gc.strokeOval(centerX - 5, centerY - 5, 10, 10);
        gc.strokeLine(centerX - 50, centerY, centerX - 20, centerY);
        gc.strokeLine(centerX + 20, centerY, centerX + 50, centerY);
    }

    private void provideFeedback() {
        double pitch = pitchSlider.getValue();
        double roll = rollSlider.getValue();
        String mode = modeCombo.getValue();

        if (altitude < 1000) {
            feedbackLabel.setText("⚠ LOW ALTITUDE WARNING");
            feedbackLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        } else if (airspeed < 55) {
            feedbackLabel.setText("⚠ STALL WARNING - INCREASE AIRSPEED");
            feedbackLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        } else if (Math.abs(pitch) > 20) {
            feedbackLabel.setText("⚠ Excessive pitch angle");
            feedbackLabel.setStyle("-fx-text-fill: #f39c12; -fx-font-weight: bold;");
        } else if (Math.abs(roll) > 30) {
            feedbackLabel.setText("⚠ Excessive bank angle");
            feedbackLabel.setStyle("-fx-text-fill: #f39c12; -fx-font-weight: bold;");
        } else {
            feedbackLabel.setText("✓ Parameters within safe range");
            feedbackLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
        }
    }
}
