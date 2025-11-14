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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardController {

    @FXML private Label totalFlightsLabel;
    @FXML private Label totalHoursLabel;
    @FXML private Label totalAircraftLabel;
    @FXML private Label totalPilotsLabel;
    @FXML private Label picHoursLabel;
    @FXML private Label dualHoursLabel;
    @FXML private Label nightHoursLabel;
    @FXML private Label xcHoursLabel;
    @FXML private BarChart<String, Number> hoursChart;
    @FXML private TableView<LogbookEntry> recentFlightsTable;
    @FXML private TableColumn<LogbookEntry, String> dateColumn;
    @FXML private TableColumn<LogbookEntry, String> pilotColumn;
    @FXML private TableColumn<LogbookEntry, String> aircraftColumn;
    @FXML private TableColumn<LogbookEntry, String> routeColumn;
    @FXML private TableColumn<LogbookEntry, Double> totalTimeColumn;

    private PilotDAO pilotDAO = new PilotDAO();
    private AircraftDAO aircraftDAO = new AircraftDAO();
    private LogbookDAO logbookDAO = new LogbookDAO();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadDashboardData();
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
        routeColumn.setCellValueFactory(cellData -> {
            LogbookEntry entry = cellData.getValue();
            String route = entry.getDepartureAirport() + " → " + entry.getArrivalAirport();
            return javafx.beans.binding.Bindings.createStringBinding(() -> route);
        });
        totalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
    }

    private void loadDashboardData() {
        try {
            List<Pilot> pilots = pilotDAO.findAll();
            List<Aircraft> aircraft = aircraftDAO.findAll();
            List<LogbookEntry> logbookEntries = logbookDAO.findAll();

            totalPilotsLabel.setText(String.valueOf(pilots.size()));
            totalAircraftLabel.setText(String.valueOf(aircraft.size()));
            totalFlightsLabel.setText(String.valueOf(logbookEntries.size()));

            double totalHours = logbookEntries.stream()
                    .mapToDouble(LogbookEntry::getTotalTime)
                    .sum();
            totalHoursLabel.setText(String.format("%.1f", totalHours));

            double picHours = logbookEntries.stream()
                    .mapToDouble(LogbookEntry::getPicTime)
                    .sum();
            picHoursLabel.setText(String.format("%.1f", picHours));

            double dualHours = logbookEntries.stream()
                    .mapToDouble(LogbookEntry::getDualTime)
                    .sum();
            dualHoursLabel.setText(String.format("%.1f", dualHours));

            double nightHours = logbookEntries.stream()
                    .mapToDouble(LogbookEntry::getNightTime)
                    .sum();
            nightHoursLabel.setText(String.format("%.1f", nightHours));

            double xcHours = logbookEntries.stream()
                    .mapToDouble(LogbookEntry::getCrossCountryTime)
                    .sum();
            xcHoursLabel.setText(String.format("%.1f", xcHours));

            loadHoursChart(picHours, dualHours, nightHours, xcHours);

            ObservableList<LogbookEntry> recentEntries = FXCollections.observableArrayList(
                    logbookEntries.stream().limit(10).toList()
            );
            recentFlightsTable.setItems(recentEntries);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadHoursChart(double pic, double dual, double night, double xc) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("PIC", pic));
        series.getData().add(new XYChart.Data<>("Dual", dual));
        series.getData().add(new XYChart.Data<>("Night", night));
        series.getData().add(new XYChart.Data<>("X-Country", xc));
        hoursChart.getData().clear();
        hoursChart.getData().add(series);
    }

    @FXML
    private void openLogbook() {
        System.out.println("Opening logbook module...");
    }

    @FXML
    private void openFlightPlanning() {
        System.out.println("Opening flight planning module...");
    }
}
