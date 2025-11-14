package com.aerosuite.view.modules;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CBTController {

    @FXML private ListView<String> modulesList;
    @FXML private Label moduleDescriptionLabel;
    @FXML private Label completedModulesLabel;
    @FXML private Label averageScoreLabel;
    @FXML private Label lastExamLabel;
    @FXML private TableView examResultsTable;

    @FXML
    public void initialize() {
        modulesList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                updateModuleDescription(newVal);
            }
        });
    }

    private void updateModuleDescription(String module) {
        String description = switch (module) {
            case "Aerodynamics" -> "Study of forces and motion of aircraft through air";
            case "Meteorology" -> "Weather patterns, forecasting, and aviation weather";
            case "Air Law" -> "Aviation regulations, airspace, and legal requirements";
            case "Navigation" -> "Flight navigation techniques and procedures";
            case "Performance" -> "Aircraft performance calculations and limitations";
            case "Flight Planning" -> "Creating and filing flight plans";
            case "Aircraft Systems" -> "Understanding aircraft systems and operations";
            case "Human Factors" -> "Human performance and limitations in aviation";
            default -> "Select a module";
        };
        moduleDescriptionLabel.setText(description);
    }

    @FXML
    private void startExam() {
        String selectedModule = modulesList.getSelectionModel().getSelectedItem();
        if (selectedModule != null) {
            showInfo("Starting exam for: " + selectedModule + "\nExam functionality will include multiple choice questions with timer.");
        } else {
            showError("Please select a module first");
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
