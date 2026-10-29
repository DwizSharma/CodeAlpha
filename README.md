# CodeAlpha Java Projects

Four beginner-friendly Java console applications. They use only the Java standard library and need no external packages. Java 17 or newer is recommended.

## Run a project

Open a terminal in the project folder and compile and run its main class:

```bash
javac StudentGradeTracker.java
java StudentGradeTracker
```

For each project, use the matching source and run class name below:

| Project | Source file | Run command |
| --- | --- | --- |
| Student Grade Tracker | `StudentGradeTracker.java` | `java StudentGradeTracker` |
| Stock Trading Platform | `StockTradingPlatform.java` | `java StockTradingPlatform` |
| AI Chatbot | `AIChatbot.java` | `java AIChatbot` |
| Hotel Reservation System | `HotelReservationSystem.java` | `java HotelReservationSystem` |

Compile the matching source first with `javac <SourceFile.java>`. On Windows, the same commands work in Command Prompt or PowerShell if Java is on PATH.

## Project notes

- **Student Grade Tracker:** add, list, and remove grades; see average, letter grade, and highest and lowest subjects.
- **Stock Trading Platform:** paper trading with sample prices, a simulated cash balance, and a portfolio. No real trades are made.
- **AI Chatbot:** offline, rule-based replies for greetings, date, time, jokes, and a few common prompts. It does not connect to an AI service.
- **Hotel Reservation System:** create, list, and cancel sample bookings with date validation and estimated room costs. It does not contact a real hotel, and reservations exist only until the program exits.
