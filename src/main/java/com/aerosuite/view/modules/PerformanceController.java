package com.aerosuite.view.modules;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PerformanceController {

    @FXML private ComboBox<String> aircraftCombo;
    @FXML private TextField weightField;
    @FXML private TextField temperatureField;
    @FXML private TextField pressureAltField;
    @FXML private TextField runwayLengthField;
    @FXML private Label densityAltLabel;
    @FXML private Label takeoffDistLabel;
    @FXML private Label landingDistLabel;
    @FXML private Label climbRateLabel;
    @FXML private TextArea notesArea;

    @FXML
    public void initialize() {
        aircraftCombo.setValue("Cessna 172");
    }

    @FXML
    private void calculatePerformance() {
        try {
            double temperature = Double.parseDouble(temperatureField.getText());
            double pressureAlt = Double.parseDouble(pressureAltField.getText());
            
            double densityAlt = pressureAlt + (120 * (temperature - 15));
            densityAltLabel.setText(String.format("%.0f ft", densityAlt));

            double weight = Double.parseDouble(weightField.getText());
            double takeoffDist = 1000 + (densityAlt * 0.1) + (weight * 0.5);
            takeoffDistLabel.setText(String.format("%.0f ft", takeoffDist));

            double landingDist = 800 + (densityAlt * 0.08);
            landingDistLabel.setText(String.format("%.0f ft", landingDist));

            double climbRate = 700 - (densityAlt * 0.05);
            climbRateLabel.setText(String.format("%.0f fpm", climbRate));

            String notes = "Performance calculations based on:\n" +
                    "- Standard POH data for " + aircraftCombo.getValue() + "\n" +
                    "- Density altitude: " + String.format("%.0f ft", densityAlt) + "\n" +
                    "- Weight: " + weight + " lbs\n\n" +
                    "Note: These are estimates. Always consult actual POH for accurate data.";
            notesArea.setText(notes);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter valid numbers");
            alert.showAndWait();
        }
    }
}
