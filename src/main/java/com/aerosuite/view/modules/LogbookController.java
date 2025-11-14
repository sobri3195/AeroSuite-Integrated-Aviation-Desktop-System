package com.aerosuite.view.modules;

import com.aerosuite.dao.AircraftDAO;
import com.aerosuite.dao.LogbookDAO;
import com.aerosuite.dao.PilotDAO;
import com.aerosuite.model.Aircraft;
import com.aerosuite.model.LogbookEntry;
import com.aerosuite.model.Pilot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class LogbookController {

    @FXML private ComboBox<Pilot> pilotFilterCombo;
    @FXML private Label totalHoursFilterLabel;
    @FXML private TableView<LogbookEntry> logbookTable;
    @FXML private TableColumn<LogbookEntry, String> dateColumn;
    @FXML private TableColumn<LogbookEntry, String> pilotColumn;
    @FXML private TableColumn<LogbookEntry, String> aircraftColumn;
    @FXML private TableColumn<LogbookEntry, String> departureColumn;
    @FXML private TableColumn<LogbookEntry, String> arrivalColumn;
    @FXML private TableColumn<LogbookEntry, Double> picTimeColumn;
    @FXML private TableColumn<LogbookEntry, Double> sicTimeColumn;
    @FXML private TableColumn<LogbookEntry, Double> dualTimeColumn;
    @FXML private TableColumn<LogbookEntry, Double> soloTimeColumn;
    @FXML private TableColumn<LogbookEntry, Double> nightTimeColumn;
    @FXML private TableColumn<LogbookEntry, Double> xcTimeColumn;
    @FXML private TableColumn<LogbookEntry, Double> totalTimeColumn;
    @FXML private TableColumn<LogbookEntry, Integer> landingsDayColumn;
    @FXML private TableColumn<LogbookEntry, Integer> landingsNightColumn;
    @FXML private Label totalFlightsLabel;
    @FXML private Label picTotalLabel;
    @FXML private Label dualTotalLabel;
    @FXML private Label nightTotalLabel;
    @FXML private Label xcTotalLabel;
    @FXML private Label totalTimeLabel;

    private PilotDAO pilotDAO = new PilotDAO();
    private LogbookDAO logbookDAO = new LogbookDAO();
    private AircraftDAO aircraftDAO = new AircraftDAO();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadPilots();
        loadLogbookEntries();
    }

    private void setupTableColumns() {
        dateColumn.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return javafx.beans.binding.Bindings.createStringBinding(
                    () -> cellData.getValue().getFlightDate().format(formatter)
            );
        });
        pilotColumn.setCellValueFactory(new PropertyValueFactory<>("pilotName"));
        aircraftColumn.setCellValueFactory(new PropertyValueFactory<>("aircraftRegistration"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("departureAirport"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));
        picTimeColumn.setCellValueFactory(new PropertyValueFactory<>("picTime"));
        sicTimeColumn.setCellValueFactory(new PropertyValueFactory<>("sicTime"));
        dualTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dualTime"));
        soloTimeColumn.setCellValueFactory(new PropertyValueFactory<>("soloTime"));
        nightTimeColumn.setCellValueFactory(new PropertyValueFactory<>("nightTime"));
        xcTimeColumn.setCellValueFactory(new PropertyValueFactory<>("crossCountryTime"));
        totalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        landingsDayColumn.setCellValueFactory(new PropertyValueFactory<>("landingsDay"));
        landingsNightColumn.setCellValueFactory(new PropertyValueFactory<>("landingsNight"));
    }

    private void loadPilots() {
        try {
            List<Pilot> pilots = pilotDAO.findAll();
            pilotFilterCombo.setItems(FXCollections.observableArrayList(pilots));
            pilotFilterCombo.setOnAction(e -> filterByPilot());
        } catch (SQLException e) {
            showError("Failed to load pilots: " + e.getMessage());
        }
    }

    private void loadLogbookEntries() {
        try {
            List<LogbookEntry> entries = logbookDAO.findAll();
            logbookTable.setItems(FXCollections.observableArrayList(entries));
            updateStatistics(entries);
        } catch (SQLException e) {
            showError("Failed to load logbook entries: " + e.getMessage());
        }
    }

    private void filterByPilot() {
        Pilot selectedPilot = pilotFilterCombo.getValue();
        if (selectedPilot != null) {
            try {
                List<LogbookEntry> entries = logbookDAO.findByPilotId(selectedPilot.getId());
                logbookTable.setItems(FXCollections.observableArrayList(entries));
                updateStatistics(entries);
            } catch (SQLException e) {
                showError("Failed to filter entries: " + e.getMessage());
            }
        }
    }

    @FXML
    private void showAllEntries() {
        pilotFilterCombo.setValue(null);
        loadLogbookEntries();
    }

    private void updateStatistics(List<LogbookEntry> entries) {
        totalFlightsLabel.setText(String.valueOf(entries.size()));
        
        double picTotal = entries.stream().mapToDouble(LogbookEntry::getPicTime).sum();
        picTotalLabel.setText(String.format("%.1f", picTotal));
        
        double dualTotal = entries.stream().mapToDouble(LogbookEntry::getDualTime).sum();
        dualTotalLabel.setText(String.format("%.1f", dualTotal));
        
        double nightTotal = entries.stream().mapToDouble(LogbookEntry::getNightTime).sum();
        nightTotalLabel.setText(String.format("%.1f", nightTotal));
        
        double xcTotal = entries.stream().mapToDouble(LogbookEntry::getCrossCountryTime).sum();
        xcTotalLabel.setText(String.format("%.1f", xcTotal));
        
        double totalTime = entries.stream().mapToDouble(LogbookEntry::getTotalTime).sum();
        totalTimeLabel.setText(String.format("%.1f", totalTime));
        totalHoursFilterLabel.setText(String.format("%.1f", totalTime));
    }

    @FXML
    private void showNewEntryDialog() {
        Dialog<LogbookEntry> dialog = new Dialog<>();
        dialog.setTitle("New Logbook Entry");
        dialog.setHeaderText("Add a new flight logbook entry");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Pilot> pilotCombo = new ComboBox<>();
        ComboBox<Aircraft> aircraftCombo = new ComboBox<>();
        DatePicker datePicker = new DatePicker(LocalDate.now());
        TextField depField = new TextField();
        TextField arrField = new TextField();
        TextField picField = new TextField("0.0");
        TextField dualField = new TextField("0.0");
        TextField nightField = new TextField("0.0");
        TextField xcField = new TextField("0.0");
        TextField totalField = new TextField("0.0");

        try {
            pilotCombo.setItems(FXCollections.observableArrayList(pilotDAO.findAll()));
            aircraftCombo.setItems(FXCollections.observableArrayList(aircraftDAO.findAll()));
        } catch (SQLException e) {
            showError("Failed to load data: " + e.getMessage());
        }

        grid.add(new Label("Pilot:"), 0, 0);
        grid.add(pilotCombo, 1, 0);
        grid.add(new Label("Aircraft:"), 0, 1);
        grid.add(aircraftCombo, 1, 1);
        grid.add(new Label("Date:"), 0, 2);
        grid.add(datePicker, 1, 2);
        grid.add(new Label("From:"), 0, 3);
        grid.add(depField, 1, 3);
        grid.add(new Label("To:"), 0, 4);
        grid.add(arrField, 1, 4);
        grid.add(new Label("PIC Time:"), 0, 5);
        grid.add(picField, 1, 5);
        grid.add(new Label("Dual Time:"), 0, 6);
        grid.add(dualField, 1, 6);
        grid.add(new Label("Night Time:"), 0, 7);
        grid.add(nightField, 1, 7);
        grid.add(new Label("X-Country:"), 0, 8);
        grid.add(xcField, 1, 8);
        grid.add(new Label("Total Time:"), 0, 9);
        grid.add(totalField, 1, 9);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                LogbookEntry entry = new LogbookEntry();
                entry.setPilotId(pilotCombo.getValue().getId());
                entry.setAircraftId(aircraftCombo.getValue().getId());
                entry.setFlightDate(datePicker.getValue());
                entry.setDepartureAirport(depField.getText());
                entry.setArrivalAirport(arrField.getText());
                entry.setPicTime(Double.parseDouble(picField.getText()));
                entry.setDualTime(Double.parseDouble(dualField.getText()));
                entry.setNightTime(Double.parseDouble(nightField.getText()));
                entry.setCrossCountryTime(Double.parseDouble(xcField.getText()));
                entry.setTotalTime(Double.parseDouble(totalField.getText()));
                return entry;
            }
            return null;
        });

        Optional<LogbookEntry> result = dialog.showAndWait();
        result.ifPresent(entry -> {
            try {
                logbookDAO.insert(entry);
                loadLogbookEntries();
                showInfo("Logbook entry added successfully!");
            } catch (SQLException e) {
                showError("Failed to save entry: " + e.getMessage());
            }
        });
    }

    @FXML
    private void exportToPDF() {
        showInfo("PDF export feature will be implemented with iText library.");
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
