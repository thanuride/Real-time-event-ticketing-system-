package backend.backendspringboot.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class Log {
    private static final String LogFile = "server.log"; // File to store logs

    // Method to write log messages to the file
    private static void writeLog(String level, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true))) {
            // Get current timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Format and write the log entry
            writer.write("[" + timestamp + "] [" + level + "] " + message);
            writer.newLine(); // Add new line after the log message
        } catch (IOException e) {
            System.err.println("Error writing log: " + e.getMessage());
        }
    }

    // Log Info Level messages
    public static void logInfo(String message) {
        writeLog("INFO", message);
    }

    // Log Warning Level messages
    public static void logWarning(String message) {
        writeLog("WARNING", message);
    }

    // Log Error Level messages
    public static void logError(String message) {
        writeLog("ERROR", message);
    }
}

