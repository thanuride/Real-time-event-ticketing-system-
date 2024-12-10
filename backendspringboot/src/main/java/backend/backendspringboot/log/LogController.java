package backend.backendspringboot.log;

import backend.backendspringboot.log.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/log")
@CrossOrigin(origins = "http://localhost:3000")
public class LogController {

    // Endpoint to fetch the log file content
    @GetMapping("/getLogs")
    public String getLogs() {
        StringBuilder logContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("server.log"))) {
            String line;
            while ((line = br.readLine()) != null) {
                logContent.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading the log file.";
        }
        return logContent.toString(); // Return the log content as plain text
    }

}
