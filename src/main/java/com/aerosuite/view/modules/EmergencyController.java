package com.aerosuite.view.modules;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmergencyController {

    @FXML private ListView<String> emergencyList;
    @FXML private TextArea checklistArea;
    @FXML private Label timerLabel;

    @FXML
    public void initialize() {
        emergencyList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadChecklist(newVal);
            }
        });
    }

    private void loadChecklist(String emergency) {
        String checklist = switch (emergency) {
            case "Engine Failure" -> """
                    ENGINE FAILURE CHECKLIST
                    
                    1. Airspeed - BEST GLIDE (68 KIAS for C172)
                    2. Fuel Selector - BOTH
                    3. Mixture - RICH
                    4. Carb Heat - ON
                    5. Primer - IN and LOCKED
                    6. Magnetos - BOTH or START
                    7. Select suitable landing area
                    8. Flaps - AS REQUIRED
                    9. Master Switch - OFF (when landing assured)
                    10. Doors - UNLATCH
                    11. Touchdown - SLIGHTLY TAIL LOW
                    12. Brakes - APPLY HEAVILY
                    """;
            case "Engine Fire" -> """
                    ENGINE FIRE CHECKLIST
                    
                    1. Mixture - IDLE CUT OFF
                    2. Fuel Selector - OFF
                    3. Master Switch - OFF
                    4. Cabin Heat/Air - OFF
                    5. Airspeed - 100 KIAS (if fire not extinguished)
                    6. Forced Landing - EXECUTE
                    """;
            case "Electrical Failure" -> """
                    ELECTRICAL FAILURE CHECKLIST
                    
                    1. Master Switch - OFF
                    2. All electrical switches - OFF
                    3. Master Switch - ON
                    4. Circuit breakers - CHECK
                    5. If problem persists:
                       - Master Switch - OFF
                       - Essential equipment only - ON
                    6. Land as soon as practical
                    """;
            default -> "Select an emergency scenario";
        };
        checklistArea.setText(checklist);
    }

    @FXML
    private void startTraining() {
        String selected = emergencyList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showInfo("Training mode started for: " + selected + "\n\nProceed through checklist at your own pace.");
        }
    }

    @FXML
    private void startTimedChallenge() {
        String selected = emergencyList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showInfo("Timed challenge started for: " + selected + "\n\nTimer will track completion time.");
        }
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
