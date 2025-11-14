package com.aerosuite.view.modules;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ATCPhraseologyController {

    @FXML private ListView<String> scenariosList;
    @FXML private TextArea scriptArea;
    @FXML private Label completedLabel;

    @FXML
    public void initialize() {
        scenariosList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadScenarioScript(newVal);
            }
        });
    }

    private void loadScenarioScript(String scenario) {
        String script = switch (scenario) {
            case "Taxi Clearance" -> "Pilot: \"Tower, N12345, ready to taxi with information Alpha.\"\n\n" +
                    "ATC: \"N12345, Tower, taxi to runway 27 via Alpha, Bravo.\"\n\n" +
                    "Pilot: \"Taxi to runway 27 via Alpha, Bravo, N12345.\"";
            case "Takeoff Clearance" -> "Pilot: \"Tower, N12345, ready for departure, runway 27.\"\n\n" +
                    "ATC: \"N12345, Tower, runway 27, cleared for takeoff.\"\n\n" +
                    "Pilot: \"Cleared for takeoff, runway 27, N12345.\"";
            case "Emergency" -> "Pilot: \"Mayday, Mayday, Mayday, N12345, engine failure, 10 miles north of KJFK, 3000 feet.\"\n\n" +
                    "ATC: \"N12345, roger, squawk 7700, say souls on board and fuel remaining.\"\n\n" +
                    "Pilot: \"Squawking 7700, 2 souls on board, 1 hour fuel, N12345.\"";
            default -> "Select a scenario to view the script.";
        };
        scriptArea.setText(script);
    }

    @FXML
    private void practiceScenario() {
        String selected = scenariosList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showInfo("Practice mode for: " + selected + "\n\nThis feature will include audio playback and recording capabilities.");
        }
    }

    @FXML
    private void playAudio() {
        showInfo("Audio playback feature (placeholder)");
    }

    @FXML
    private void recordAudio() {
        showInfo("Audio recording feature (placeholder)");
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
