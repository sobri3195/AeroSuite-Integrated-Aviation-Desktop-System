# AeroSuite - Quick Start Guide

## 🚀 Get Started in 3 Steps

### 1️⃣ Prerequisites
```bash
# Check Java version (need 17+)
java -version

# Check Maven (need 3.6+)
mvn -version
```

### 2️⃣ Run the Application
```bash
# Simple method
./run.sh

# Or use Maven directly
mvn javafx:run
```

### 3️⃣ Explore the Modules

When the application starts, you'll see the **Dashboard** with:
- Flight statistics
- Recent logbook entries
- Quick access buttons

Use the **sidebar menu** to navigate between modules:

---

## 📋 Module Overview

### 1. 📊 Dashboard
- View overall statistics
- See recent flight activity
- Quick access to main features

### 2. 📔 Flight Logbook
- Click "**+ New Entry**" to add a flight
- Select pilot and aircraft
- Enter flight times (PIC, Dual, Night, etc.)
- View all logbook entries in the table
- Filter by pilot
- Export to PDF (coming soon)

### 3. ✈️ Flight Planning
- Enter departure and arrival airports
- Add route and waypoints
- Input altitude and speed
- Calculate ETD/ETA automatically
- Plan fuel requirements
- Add NOTAM and weather info

### 4. ⚖️ Weight & Balance
- Select aircraft type (C172, PA-28, DA40, A320)
- Enter weights for all stations
- Click "**Calculate**" to compute CG
- View CG position on envelope chart
- Check if within limits

### 5. 🎓 CBT (Training)
- Browse training modules
- Select a module to view content
- Click "**Start Exam**" to practice
- Track your progress and scores

### 6. 🔧 Maintenance
- View aircraft maintenance status
- Add maintenance logs
- Track engine/airframe hours
- Check inspection reminders

### 7. 📈 Performance Calculator
- Select aircraft type
- Enter weight, temperature, altitude
- Click "**Calculate**" for:
  - Takeoff distance
  - Landing distance
  - Climb rate
  - Density altitude

### 8. 📻 ATC Phraseology
- Choose a scenario (taxi, takeoff, landing, etc.)
- Read standard phraseology
- Practice communication
- Track scenarios completed

### 9. 🚨 Emergency Checklist
- Select emergency type
- View step-by-step checklist
- Practice procedures
- Try timed challenge mode

### 10. 🚁 Drone Planner
- Plan drone missions
- Add waypoints
- Calculate flight time and distance
- Log flights
- Track battery cycles

### 11. 🎮 2D Simulator
- Control throttle, pitch, and roll with sliders
- Watch instruments update in real-time
- Select training mode
- Get feedback on parameters
- Click "**Start Simulation**" to begin

---

## 💡 Tips

### First Time Users
1. **Explore the Dashboard** - Get familiar with the layout
2. **Check Sample Data** - The app includes demo pilots and aircraft
3. **Try the Simulator** - No risk, great for testing controls
4. **Add a Flight Log** - Start recording your flights

### Power Users
1. **Use Filters** - Filter logbook by pilot
2. **Save Configurations** - Save W&B configs for reuse
3. **Track Totals** - Monitor total hours by category
4. **Plan Ahead** - Use templates for recurring flight plans

---

## 🔧 Common Tasks

### Add a New Pilot
1. Go to "**Flight Logbook**"
2. Click "**+ New Entry**"
3. In the pilot dropdown, you'll see existing pilots
4. (To add new pilot, use database or wait for user management feature)

### Record a Flight
1. Go to "**Flight Logbook**"
2. Click "**+ New Entry**"
3. Fill in all fields:
   - Select pilot and aircraft
   - Enter date
   - Add departure/arrival airports
   - Enter flight times
   - Add remarks
4. Click "**Save**"

### Calculate Weight & Balance
1. Go to "**Weight & Balance**"
2. Select aircraft type
3. Enter weights:
   - Pilot
   - Co-pilot
   - Passengers
   - Baggage
   - Fuel
4. Click "**Calculate**"
5. Check if "**WITHIN LIMITS**" shows green

### Plan a Flight
1. Go to "**Flight Planning**"
2. Select pilot and aircraft
3. Enter departure and arrival
4. Add altitude and speed
5. Enter wind data
6. Add taxi, reserve fuel
7. Enter ETD
8. Click "**Calculate**"
9. Review ETA and fuel requirements

---

## 🗃️ Database

The application stores all data in `aerosuite.db` (SQLite database).

**Location**: Same directory as the application

**Backup**: Simply copy the `aerosuite.db` file

**Reset**: Delete `aerosuite.db` and restart (will regenerate with sample data)

---

## 🎨 Interface Guide

### Navigation
- **Sidebar**: Click any module button to switch views
- **Header**: Shows current module name
- **Content Area**: Main working area for each module

### Color Coding
- 🔵 **Blue buttons** - Primary actions
- 🟢 **Green buttons** - Success/Add actions
- 🟠 **Orange buttons** - Warning/Special actions
- 🔴 **Red buttons** - Delete/Danger actions

### Tables
- **Click** on rows to select
- **Double-click** to edit (where available)
- **Scroll** horizontally for more columns

---

## ❓ Troubleshooting

### Application won't start
```bash
# Check Java version
java -version  # Should be 17 or higher

# Recompile
mvn clean compile
mvn javafx:run
```

### Data not saving
- Check if `aerosuite.db` exists
- Check file permissions
- Look for error messages in console

### UI looks broken
- Ensure `styles.css` exists in `src/main/resources/css/`
- Restart the application

### Module not loading
- Check console for errors
- Verify FXML file exists
- Check controller class name matches

---

## 📊 Sample Data

The application includes sample data:

**Pilots**:
- Captain John Smith (ATPL)
- Sarah Johnson (CPL)
- Mike Anderson (PPL)

**Aircraft**:
- N12345 - Cessna 172S
- N67890 - Piper PA-28
- N24680 - Diamond DA40

**Flights**: Several sample logbook entries

---

## 🚀 Next Steps

1. ✅ **Learn the basics** - Explore all modules
2. ✅ **Add your data** - Replace sample data with real info
3. ✅ **Use regularly** - Log every flight
4. ✅ **Track progress** - Monitor your hours
5. ✅ **Maintain aircraft** - Record maintenance
6. ✅ **Plan flights** - Use the planning tools
7. ✅ **Train** - Practice with CBT and simulator

---

## 📞 Need Help?

- Check `README.md` for detailed info
- See `DEVELOPMENT_GUIDE.md` for technical details
- Read `PROJECT_SUMMARY.md` for feature list

---

## ⭐ Key Features to Try First

1. **Dashboard** - See your statistics
2. **Flight Logbook** - Add your first flight
3. **2D Simulator** - Try the flight simulator
4. **Weight & Balance** - Calculate CG for your aircraft
5. **Emergency Checklist** - Review emergency procedures

---

**Happy Flying! ✈️**

*AeroSuite - Your Complete Aviation Management System*
