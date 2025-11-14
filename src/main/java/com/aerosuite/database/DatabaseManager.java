package com.aerosuite.database;

import java.sql.*;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;
    private static final String DB_URL = "jdbc:sqlite:aerosuite.db";

    private DatabaseManager() {
        connect();
        initializeDatabase();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to SQLite database successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            System.err.println("Error checking connection: " + e.getMessage());
        }
        return connection;
    }

    private void initializeDatabase() {
        try {
            Statement stmt = connection.createStatement();
            
            stmt.execute("CREATE TABLE IF NOT EXISTS pilots (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "license_number TEXT," +
                    "license_type TEXT," +
                    "email TEXT," +
                    "phone TEXT," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP)");

            stmt.execute("CREATE TABLE IF NOT EXISTS aircraft (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "registration TEXT NOT NULL UNIQUE," +
                    "type TEXT NOT NULL," +
                    "model TEXT," +
                    "serial_number TEXT," +
                    "engine_hours REAL DEFAULT 0," +
                    "airframe_hours REAL DEFAULT 0," +
                    "status TEXT DEFAULT 'serviceable'," +
                    "fuel_capacity REAL," +
                    "fuel_consumption_rate REAL," +
                    "max_takeoff_weight REAL," +
                    "empty_weight REAL," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP)");

            stmt.execute("CREATE TABLE IF NOT EXISTS logbook_entries (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pilot_id INTEGER NOT NULL," +
                    "aircraft_id INTEGER NOT NULL," +
                    "flight_date TEXT NOT NULL," +
                    "departure_airport TEXT," +
                    "arrival_airport TEXT," +
                    "pic_time REAL DEFAULT 0," +
                    "sic_time REAL DEFAULT 0," +
                    "dual_time REAL DEFAULT 0," +
                    "solo_time REAL DEFAULT 0," +
                    "night_time REAL DEFAULT 0," +
                    "cross_country_time REAL DEFAULT 0," +
                    "instrument_time REAL DEFAULT 0," +
                    "total_time REAL DEFAULT 0," +
                    "landings_day INTEGER DEFAULT 0," +
                    "landings_night INTEGER DEFAULT 0," +
                    "remarks TEXT," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY(pilot_id) REFERENCES pilots(id)," +
                    "FOREIGN KEY(aircraft_id) REFERENCES aircraft(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS flight_plans (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pilot_id INTEGER NOT NULL," +
                    "aircraft_id INTEGER NOT NULL," +
                    "plan_name TEXT," +
                    "departure_airport TEXT NOT NULL," +
                    "arrival_airport TEXT NOT NULL," +
                    "route TEXT," +
                    "altitude INTEGER," +
                    "ground_speed REAL," +
                    "wind_direction INTEGER," +
                    "wind_speed REAL," +
                    "etd TEXT," +
                    "eta TEXT," +
                    "estimated_duration REAL," +
                    "taxi_fuel REAL," +
                    "trip_fuel REAL," +
                    "reserve_fuel REAL," +
                    "total_fuel REAL," +
                    "notam TEXT," +
                    "weather TEXT," +
                    "is_template INTEGER DEFAULT 0," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY(pilot_id) REFERENCES pilots(id)," +
                    "FOREIGN KEY(aircraft_id) REFERENCES aircraft(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS weight_balance_configs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "aircraft_id INTEGER NOT NULL," +
                    "config_name TEXT," +
                    "pilot_weight REAL," +
                    "copilot_weight REAL," +
                    "passenger_weight REAL," +
                    "baggage_weight REAL," +
                    "fuel_weight REAL," +
                    "total_weight REAL," +
                    "cg_position REAL," +
                    "is_within_limits INTEGER," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY(aircraft_id) REFERENCES aircraft(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS aircraft_limits (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "aircraft_type TEXT NOT NULL UNIQUE," +
                    "empty_weight REAL," +
                    "empty_cg REAL," +
                    "max_weight REAL," +
                    "cg_forward_limit REAL," +
                    "cg_aft_limit REAL," +
                    "pilot_arm REAL," +
                    "copilot_arm REAL," +
                    "passenger_arm REAL," +
                    "baggage_arm REAL," +
                    "fuel_arm REAL)");

            stmt.execute("CREATE TABLE IF NOT EXISTS cbt_modules (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "module_name TEXT NOT NULL," +
                    "category TEXT," +
                    "description TEXT," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP)");

            stmt.execute("CREATE TABLE IF NOT EXISTS cbt_questions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "module_id INTEGER NOT NULL," +
                    "question_text TEXT NOT NULL," +
                    "option_a TEXT," +
                    "option_b TEXT," +
                    "option_c TEXT," +
                    "option_d TEXT," +
                    "correct_answer TEXT," +
                    "explanation TEXT," +
                    "FOREIGN KEY(module_id) REFERENCES cbt_modules(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS cbt_exam_results (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pilot_id INTEGER NOT NULL," +
                    "module_id INTEGER NOT NULL," +
                    "score REAL," +
                    "total_questions INTEGER," +
                    "correct_answers INTEGER," +
                    "exam_duration INTEGER," +
                    "exam_date TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY(pilot_id) REFERENCES pilots(id)," +
                    "FOREIGN KEY(module_id) REFERENCES cbt_modules(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS maintenance_logs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "aircraft_id INTEGER NOT NULL," +
                    "maintenance_date TEXT NOT NULL," +
                    "maintenance_type TEXT," +
                    "description TEXT," +
                    "technician_name TEXT," +
                    "engine_hours_at_maintenance REAL," +
                    "airframe_hours_at_maintenance REAL," +
                    "cost REAL," +
                    "status TEXT DEFAULT 'completed'," +
                    "next_due_hours REAL," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY(aircraft_id) REFERENCES aircraft(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS spare_parts (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "part_name TEXT NOT NULL," +
                    "part_number TEXT," +
                    "serial_number TEXT," +
                    "stock_quantity INTEGER DEFAULT 0," +
                    "status TEXT DEFAULT 'serviceable'," +
                    "aircraft_type TEXT," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP)");

            stmt.execute("CREATE TABLE IF NOT EXISTS performance_calculations (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "aircraft_type TEXT NOT NULL," +
                    "weight REAL," +
                    "temperature REAL," +
                    "pressure_altitude INTEGER," +
                    "runway_length INTEGER," +
                    "density_altitude INTEGER," +
                    "takeoff_distance REAL," +
                    "landing_distance REAL," +
                    "climb_rate REAL," +
                    "calculation_date TEXT DEFAULT CURRENT_TIMESTAMP)");

            stmt.execute("CREATE TABLE IF NOT EXISTS atc_scenarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "scenario_name TEXT NOT NULL," +
                    "scenario_type TEXT," +
                    "phraseology_text TEXT," +
                    "audio_file_path TEXT," +
                    "difficulty_level TEXT)");

            stmt.execute("CREATE TABLE IF NOT EXISTS atc_practice_logs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pilot_id INTEGER NOT NULL," +
                    "scenario_id INTEGER NOT NULL," +
                    "practice_date TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "completion_status TEXT," +
                    "notes TEXT," +
                    "FOREIGN KEY(pilot_id) REFERENCES pilots(id)," +
                    "FOREIGN KEY(scenario_id) REFERENCES atc_scenarios(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS emergency_checklists (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "checklist_name TEXT NOT NULL," +
                    "emergency_type TEXT," +
                    "aircraft_type TEXT," +
                    "steps TEXT," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP)");

            stmt.execute("CREATE TABLE IF NOT EXISTS emergency_practice_logs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pilot_id INTEGER NOT NULL," +
                    "checklist_id INTEGER NOT NULL," +
                    "practice_date TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "completion_time INTEGER," +
                    "success_status INTEGER," +
                    "notes TEXT," +
                    "FOREIGN KEY(pilot_id) REFERENCES pilots(id)," +
                    "FOREIGN KEY(checklist_id) REFERENCES emergency_checklists(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS drones (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "drone_name TEXT NOT NULL," +
                    "model TEXT," +
                    "serial_number TEXT," +
                    "registration TEXT," +
                    "max_flight_time INTEGER," +
                    "max_speed REAL," +
                    "status TEXT DEFAULT 'operational'," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP)");

            stmt.execute("CREATE TABLE IF NOT EXISTS drone_batteries (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "drone_id INTEGER NOT NULL," +
                    "battery_name TEXT," +
                    "serial_number TEXT," +
                    "cycle_count INTEGER DEFAULT 0," +
                    "status TEXT DEFAULT 'good'," +
                    "notes TEXT," +
                    "FOREIGN KEY(drone_id) REFERENCES drones(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS drone_flight_logs (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "drone_id INTEGER NOT NULL," +
                    "battery_id INTEGER," +
                    "flight_date TEXT NOT NULL," +
                    "location TEXT," +
                    "mission_type TEXT," +
                    "waypoints TEXT," +
                    "planned_altitude REAL," +
                    "planned_distance REAL," +
                    "estimated_duration INTEGER," +
                    "actual_duration INTEGER," +
                    "notes TEXT," +
                    "created_date TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY(drone_id) REFERENCES drones(id)," +
                    "FOREIGN KEY(battery_id) REFERENCES drone_batteries(id))");

            stmt.execute("CREATE TABLE IF NOT EXISTS simulator_sessions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "pilot_id INTEGER NOT NULL," +
                    "session_date TEXT DEFAULT CURRENT_TIMESTAMP," +
                    "training_mode TEXT," +
                    "duration INTEGER," +
                    "performance_score REAL," +
                    "notes TEXT," +
                    "FOREIGN KEY(pilot_id) REFERENCES pilots(id))");

            System.out.println("Database schema initialized successfully.");
            
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
