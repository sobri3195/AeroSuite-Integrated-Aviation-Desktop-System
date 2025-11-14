# AeroSuite - Integrated Aviation Desktop System

A comprehensive JavaFX desktop application for aviation management, training, and operations.

## 🚀 Features

### 1. **Flight Logbook Digital**
- Record flight hours (PIC, SIC, dual, solo, night, cross-country)
- Track aircraft and pilot information
- Automatic calculation of total hours by category
- Multi-pilot support
- Export to PDF (planned)

### 2. **Flight Planning Assistant**
- Route planning with waypoints
- Automatic ETD/ETA calculation
- Comprehensive fuel planning (taxi, trip, reserve)
- NOTAM and weather information storage
- Save and reuse flight plan templates

### 3. **Weight & Balance Simulator**
- Support for multiple aircraft types (C172, PA-28, DA40, A320)
- Real-time CG calculation
- Visual CG envelope chart
- Weight limit checking
- Save configurations as presets

### 4. **Computer Based Training (CBT)**
- Training modules: Aerodynamics, Meteorology, Air Law, Navigation, etc.
- Multiple choice exam system
- Automatic scoring and progress tracking
- Exam history and statistics

### 5. **Aircraft Maintenance Record System**
- Track maintenance logs and history
- Engine and airframe hour tracking
- Inspection reminders (50h, 100h, annual)
- Spare parts management

### 6. **Performance Calculator**
- Takeoff and landing distance calculations
- Density altitude computation
- Climb rate performance
- Support for C172, PA-28, and DA40

### 7. **ATC Phraseology Trainer**
- Practice scenarios: taxi, takeoff, departure, approach, landing, emergency
- Standard phraseology scripts
- Audio playback capability (planned)
- Practice tracking

### 8. **Emergency Checklist Trainer**
- Emergency procedures: engine failure, fire, electrical failure, etc.
- Step-by-step checklist guidance
- Timed challenge mode
- Practice logging

### 9. **Drone Flight Planner & Logger**
- Mission planning with waypoints
- Flight duration and distance estimation
- Drone flight logbook
- Battery cycle tracking and management

### 10. **2D Flight Simulator**
- Basic flight simulation with pitch and roll
- Virtual instruments: airspeed, altitude, VSI, heading
- Training modes: straight & level, climb, descent, slow flight, stall
- Real-time feedback on flight parameters

## 🛠️ Technology Stack

- **Language**: Java 17
- **UI Framework**: JavaFX 21
- **Build Tool**: Maven
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: SQLite (embedded)
- **PDF Generation**: iText7

## 📁 Project Structure

```
src/main/java/com/aerosuite/
├── AeroSuiteApplication.java    # Main application entry point
├── model/                        # Domain models
│   ├── Pilot.java
│   ├── Aircraft.java
│   ├── LogbookEntry.java
│   ├── FlightPlan.java
│   ├── WeightBalanceConfig.java
│   └── Drone.java
├── dao/                          # Data Access Objects
│   ├── PilotDAO.java
│   ├── AircraftDAO.java
│   └── LogbookDAO.java
├── view/                         # Controllers
│   ├── MainWindowController.java
│   └── modules/                  # Module-specific controllers
├── viewmodel/                    # View models (MVVM)
├── service/                      # Business logic layer
├── util/                         # Utility classes
│   └── DummyDataGenerator.java
└── database/                     # Database management
    └── DatabaseManager.java

src/main/resources/
├── fxml/                         # FXML view files
│   ├── MainWindow.fxml
│   └── modules/                  # Module FXML files
├── css/                          # Stylesheets
│   └── styles.css
└── data/                         # Data files
```

## 🚀 How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Running with Maven

```bash
# Compile the project
mvn clean compile

# Run the application
mvn javafx:run

# Or run directly with Java (after compiling)
mvn clean package
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar target/aerosuite-aviation-system-1.0.0.jar
```

### Building an Executable JAR

```bash
mvn clean package
```

The application will be packaged as `target/aerosuite-aviation-system-1.0.0.jar`.

## 📊 Database

The application uses SQLite as an embedded database. On first run, it automatically:
- Creates the database file `aerosuite.db` in the application directory
- Initializes all required tables
- Populates sample data for testing

### Database Schema

The database includes tables for:
- Pilots and aircraft
- Logbook entries
- Flight plans
- Weight & balance configurations
- CBT modules and exam results
- Maintenance logs
- ATC and emergency practice logs
- Drone flights and batteries
- Simulator sessions

## 🎨 User Interface

The application features:
- Modern, responsive JavaFX interface
- Sidebar navigation for easy module switching
- Professional color scheme and styling
- Charts and graphs for data visualization
- Form-based data entry with validation

## 🔧 Development

### Adding New Features

1. Create model class in `model/` package
2. Create DAO in `dao/` package for database operations
3. Create FXML view in `resources/fxml/modules/`
4. Create controller in `view/modules/` package
5. Add navigation button in `MainWindow.fxml`

### Coding Conventions

- Follow MVVM architecture pattern
- Use JavaFX properties for data binding
- Implement proper error handling
- Add comments for complex logic
- Keep controllers thin, move business logic to services

## 📝 Sample Data

The application includes dummy data on first run:
- 3 sample pilots (ATP, CPL, PPL holders)
- 3 aircraft (Cessna 172, Piper PA-28, Diamond DA40)
- Several logbook entries
- Various flight scenarios

## 🔮 Future Enhancements

- PDF export for logbook entries
- Real audio support for ATC trainer
- Advanced 3D flight simulator
- Import/export flight plans
- Cloud synchronization
- Mobile companion app
- Integration with real weather APIs
- Advanced performance charts

## 📄 License

This is a demonstration project for aviation management systems.

## 👨‍💻 Author

**Lettu Kes dr. Muhammad Sobri Maulana, S.Kom, CEH, OSCP, OSCE**

- 🔗 GitHub: [github.com/sobri3195](https://github.com/sobri3195)
- 📧 Email: [muhammadsobrimaulana31@gmail.com](mailto:muhammadsobrimaulana31@gmail.com)
- 🌐 Website: [muhammadsobrimaulana.netlify.app](https://muhammadsobrimaulana.netlify.app)
- 🌐 Portfolio: [muhammad-sobri-maulana-kvr6a.sevalla.page](https://muhammad-sobri-maulana-kvr6a.sevalla.page/)

### 📱 Social Media
- 📺 YouTube: [@muhammadsobrimaulana6013](https://www.youtube.com/@muhammadsobrimaulana6013)
- 📱 TikTok: [@dr.sobri](https://www.tiktok.com/@dr.sobri)
- 💬 Telegram: [winlin_exploit](https://t.me/winlin_exploit)
- 💬 WhatsApp Group: [Join Community](https://chat.whatsapp.com/B8nwRZOBMo64GjTwdXV8Bl)

## 💖 Support & Donation

If you find this project helpful, consider supporting the development:

- ☕ **Trakteer**: [trakteer.id/g9mkave5gauns962u07t](https://trakteer.id/g9mkave5gauns962u07t)
- 💳 **Lynk.id**: [lynk.id/muhsobrimaulana](https://lynk.id/muhsobrimaulana)
- 🛒 **Gumroad**: [maulanasobri.gumroad.com](https://maulanasobri.gumroad.com/)
- 🎨 **Karya Karsa**: [karyakarsa.com/muhammadsobrimaulana](https://karyakarsa.com/muhammadsobrimaulana)
- 💰 **Nyawer**: [nyawer.co/MuhammadSobriMaulana](https://nyawer.co/MuhammadSobriMaulana)

Your support helps maintain and improve this project. Thank you! 🙏

## 👥 Contact & Support

For questions, issues, or collaboration:
- 📧 Email: muhammadsobrimaulana31@gmail.com
- 💬 Telegram: [t.me/winlin_exploit](https://t.me/winlin_exploit)
- 💬 WhatsApp Community: [Join Here](https://chat.whatsapp.com/B8nwRZOBMo64GjTwdXV8Bl)

---

**AeroSuite** - Your complete aviation management solution

*Developed with ❤️ by Dr. Muhammad Sobri Maulana*
