# AeroSuite Development Guide

## Quick Start

### Running the Application

```bash
# Method 1: Using Maven directly
mvn javafx:run

# Method 2: Using the run script
./run.sh

# Method 3: Compile and run separately
mvn clean compile
mvn javafx:run
```

### Building for Distribution

```bash
# Create executable JAR
mvn clean package

# The JAR will be in: target/aerosuite-aviation-system-1.0.0.jar
```

---

## Project Architecture

### MVVM Pattern

```
View (FXML)
    ↓
ViewModel/Controller (Java)
    ↓
Model (Domain Objects)
    ↓
DAO (Data Access)
    ↓
Database (SQLite)
```

### Package Structure

```
com.aerosuite
├── AeroSuiteApplication     # Main entry point, launches JavaFX
├── model                     # Domain entities (POJOs)
├── dao                       # Database access layer
├── view                      # FXML controllers (UI logic)
│   └── modules               # Module-specific controllers
├── viewmodel                 # MVVM ViewModels (optional layer)
├── service                   # Business logic services
├── util                      # Utility classes
└── database                  # Database connection management
```

---

## Adding a New Module

### Step 1: Create the Model

```java
// src/main/java/com/aerosuite/model/YourModel.java
package com.aerosuite.model;

public class YourModel {
    private Integer id;
    private String name;
    // Add fields, getters, setters
}
```

### Step 2: Update Database Schema

```java
// In DatabaseManager.java, add to initializeDatabase()
stmt.execute("CREATE TABLE IF NOT EXISTS your_table (" +
    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
    "name TEXT NOT NULL," +
    "created_date TEXT DEFAULT CURRENT_TIMESTAMP)");
```

### Step 3: Create DAO

```java
// src/main/java/com/aerosuite/dao/YourDAO.java
package com.aerosuite.dao;

public class YourDAO {
    private final Connection connection;
    
    public YourDAO() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }
    
    public void insert(YourModel model) throws SQLException {
        // Implementation
    }
    
    public List<YourModel> findAll() throws SQLException {
        // Implementation
    }
}
```

### Step 4: Create FXML View

```xml
<!-- src/main/resources/fxml/modules/YourModule.fxml -->
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<VBox xmlns="http://javafx.com/javafx"
      xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.aerosuite.view.modules.YourModuleController"
      spacing="15" styleClass="content-area">
    <!-- Add your UI components -->
</VBox>
```

### Step 5: Create Controller

```java
// src/main/java/com/aerosuite/view/modules/YourModuleController.java
package com.aerosuite.view.modules;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class YourModuleController {
    @FXML private TableView<YourModel> tableView;
    
    private YourDAO dao = new YourDAO();
    
    @FXML
    public void initialize() {
        loadData();
    }
    
    private void loadData() {
        // Implementation
    }
}
```

### Step 6: Add Navigation

In `MainWindow.fxml`, add button:
```xml
<Button text="📋 Your Module" onAction="#loadYourModule" 
        styleClass="sidebar-button" maxWidth="Infinity"/>
```

In `MainWindowController.java`, add method:
```java
@FXML
private void loadYourModule() {
    loadView("/fxml/modules/YourModule.fxml", "Your Module");
}
```

---

## Database Operations

### Connecting to Database

```java
Connection conn = DatabaseManager.getInstance().getConnection();
```

### Common DAO Patterns

#### Insert
```java
public void insert(Model model) throws SQLException {
    String sql = "INSERT INTO table (field1, field2) VALUES (?, ?)";
    try (PreparedStatement pstmt = connection.prepareStatement(sql, 
            Statement.RETURN_GENERATED_KEYS)) {
        pstmt.setString(1, model.getField1());
        pstmt.setString(2, model.getField2());
        pstmt.executeUpdate();
        
        try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) {
                model.setId(rs.getInt(1));
            }
        }
    }
}
```

#### Select All
```java
public List<Model> findAll() throws SQLException {
    List<Model> list = new ArrayList<>();
    String sql = "SELECT * FROM table ORDER BY field1";
    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            list.add(extractFromResultSet(rs));
        }
    }
    return list;
}
```

#### Update
```java
public void update(Model model) throws SQLException {
    String sql = "UPDATE table SET field1 = ?, field2 = ? WHERE id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, model.getField1());
        pstmt.setString(2, model.getField2());
        pstmt.setInt(3, model.getId());
        pstmt.executeUpdate();
    }
}
```

---

## UI Components

### TableView Setup

```java
@FXML private TableView<Model> tableView;
@FXML private TableColumn<Model, String> nameColumn;

private void setupTable() {
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    
    // Or with custom cell value factory
    nameColumn.setCellValueFactory(cellData -> {
        return javafx.beans.binding.Bindings.createStringBinding(
            () -> cellData.getValue().getName()
        );
    });
    
    // Load data
    tableView.setItems(FXCollections.observableArrayList(dao.findAll()));
}
```

### Form Dialog

```java
private void showDialog() {
    Dialog<Model> dialog = new Dialog<>();
    dialog.setTitle("Add New Item");
    
    ButtonType saveButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(saveButton, ButtonType.CANCEL);
    
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    
    TextField nameField = new TextField();
    grid.add(new Label("Name:"), 0, 0);
    grid.add(nameField, 1, 0);
    
    dialog.getDialogPane().setContent(grid);
    
    dialog.setResultConverter(button -> {
        if (button == saveButton) {
            Model model = new Model();
            model.setName(nameField.getText());
            return model;
        }
        return null;
    });
    
    Optional<Model> result = dialog.showAndWait();
    result.ifPresent(model -> {
        // Save to database
    });
}
```

### Charts

```java
@FXML private BarChart<String, Number> barChart;

private void loadChart() {
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.getData().add(new XYChart.Data<>("Category 1", 100));
    series.getData().add(new XYChart.Data<>("Category 2", 200));
    barChart.getData().add(series);
}
```

---

## Styling

### Using CSS Classes

In FXML:
```xml
<Button text="Save" styleClass="button-success"/>
<VBox styleClass="card"/>
<Label text="Title" styleClass="card-title"/>
```

### Available Styles

- `.button-success` - Green button
- `.button-danger` - Red button
- `.button-warning` - Orange button
- `.card` - Card container with shadow
- `.card-title` - Card title text
- `.section-title` - Section header
- `.label-bold` - Bold label
- `.stat-card` - Statistics card with gradient

### Custom Inline Styles

```xml
<Label text="Custom" style="-fx-text-fill: red; -fx-font-size: 16px;"/>
```

---

## Error Handling

### User-Friendly Alerts

```java
private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("An error occurred");
    alert.setContentText(message);
    alert.showAndWait();
}

private void showInfo(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setContentText(message);
    alert.showAndWait();
}

private void showConfirm(String message, Runnable onConfirm) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm");
    alert.setContentText(message);
    alert.showAndWait().ifPresent(response -> {
        if (response == ButtonType.OK) {
            onConfirm.run();
        }
    });
}
```

### Try-Catch Pattern

```java
try {
    // Database operation
    dao.insert(model);
    showInfo("Saved successfully!");
} catch (SQLException e) {
    showError("Failed to save: " + e.getMessage());
    e.printStackTrace();
}
```

---

## Testing

### Manual Testing Checklist

For each module:
- [ ] UI loads without errors
- [ ] All buttons are functional
- [ ] Forms validate input
- [ ] Data saves to database
- [ ] Data loads from database
- [ ] Error messages display properly
- [ ] Navigation works correctly

### Database Testing

```bash
# View database
sqlite3 aerosuite.db

# List tables
.tables

# View data
SELECT * FROM pilots;
SELECT * FROM logbook_entries;

# Exit
.quit
```

---

## Common Issues & Solutions

### Issue: JavaFX not found
**Solution**: Ensure JavaFX is in Maven dependencies and javafx-maven-plugin is configured

### Issue: Database locked
**Solution**: Close all connections properly:
```java
DatabaseManager.getInstance().close();
```

### Issue: FXML not loading
**Solution**: Check resource path starts with `/`:
```java
FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Module.fxml"));
```

### Issue: CSS not applying
**Solution**: Ensure CSS is loaded in scene:
```java
scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
```

---

## Best Practices

### Code Style
1. Use meaningful variable names
2. Keep methods under 50 lines
3. Extract complex logic to separate methods
4. Use JavaFX properties for bindings
5. Handle all exceptions properly

### Database
1. Always use PreparedStatement for SQL
2. Close resources in try-with-resources
3. Use transactions for multiple operations
4. Add indexes for frequently queried columns
5. Validate data before insertion

### UI
1. Keep controllers thin
2. Move business logic to services
3. Use FXML for layouts, not Java code
4. Provide user feedback for all actions
5. Disable buttons during operations

### Git
1. Commit frequently with clear messages
2. Don't commit generated files (target/, *.db)
3. Use .gitignore properly
4. Create branches for new features

---

## Performance Tips

1. **Lazy Loading**: Load data only when needed
2. **Pagination**: Use LIMIT/OFFSET for large tables
3. **Caching**: Cache frequently accessed data
4. **Async Operations**: Use JavaFX Tasks for long operations
5. **Connection Pool**: Reuse database connections

---

## Debugging

### Enable Detailed Logging

In DatabaseManager.java:
```java
System.out.println("Executing query: " + sql);
```

### JavaFX Debugging

```java
// Print node hierarchy
System.out.println(node.getScene().getRoot());

// Check if FXML loaded
System.out.println("Controller initialized");
```

### Database Debugging

```bash
# Enable SQL logging
sqlite3 aerosuite.db
.headers on
.mode column
SELECT * FROM table_name;
```

---

## Deployment

### Creating Standalone JAR

```bash
mvn clean package
```

### Running Standalone

```bash
java -jar target/aerosuite-aviation-system-1.0.0.jar
```

### System Requirements

- Java 17 or higher
- 512MB RAM minimum
- 100MB disk space
- Display resolution 1280x720 or higher

---

## Resources

### Official Documentation
- [JavaFX Documentation](https://openjfx.io/)
- [SQLite Documentation](https://www.sqlite.org/docs.html)
- [Maven Guide](https://maven.apache.org/guides/)

### Useful Tools
- **Scene Builder**: Visual FXML editor
- **DB Browser for SQLite**: Database viewer
- **IntelliJ IDEA**: IDE with JavaFX support

---

## Support & Contribution

### Reporting Issues
Include:
1. Steps to reproduce
2. Expected behavior
3. Actual behavior
4. Error messages/logs
5. Environment details

### Feature Requests
Describe:
1. Feature purpose
2. Use case scenario
3. Proposed implementation
4. UI mockups (if applicable)

---

*Last Updated: November 2024*
*Version: 1.0.0*
