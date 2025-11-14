package com.aerosuite;

import com.aerosuite.database.DatabaseManager;
import com.aerosuite.util.DummyDataGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AeroSuiteApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            DatabaseManager.getInstance();
            DummyDataGenerator.generateDummyData();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            
            primaryStage.setTitle("AeroSuite - Integrated Aviation Desktop System");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.setOnCloseRequest(event -> {
                DatabaseManager.getInstance().close();
                System.exit(0);
            });
            
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
