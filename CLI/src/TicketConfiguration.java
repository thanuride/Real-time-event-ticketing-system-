import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class TicketConfiguration {
    private int totalTickets;
    private  int ticketReleaseRate;
    private  int retrievalRate;
    private int ticketCapacity;

    public TicketConfiguration(int totalTickets, int ticketReleaseRate, int retrievalRate, int ticketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.retrievalRate = retrievalRate;
        this.ticketCapacity = ticketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }


    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }


    public int getRetrievalRate() {
        return retrievalRate;
    }

    public int getTicketCapacity() {
        return ticketCapacity;
    }

    // Method to save to JSON
    public void saveToJson(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
            Log.logError("Error saving configuration:" + e.getMessage());
        }
    }

}
