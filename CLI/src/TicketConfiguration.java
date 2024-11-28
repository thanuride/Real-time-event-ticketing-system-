import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TicketConfiguration {
    private int totalTickets;
    private  int ticketReleaseRate;
    private  int retrievalRate;
    private int ticketCapacity;

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        if (totalTickets < 0 ){
            throw new IllegalArgumentException("Total Tickets Should Be Greater than ZERO");
        }
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        if (ticketReleaseRate < 0 ){
            throw new IllegalArgumentException("Ticket Release Rate Should Be Greater than ZERO");
        }
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }

    public void setRetrievalRate(int retrievalRate) {
        if ( retrievalRate < 0 ){
            throw new IllegalArgumentException("Ticket Retrieval Should Be Greater than ZERO");
        }
        this.retrievalRate = retrievalRate;
    }

    public int getTicketCapacity() {
        return ticketCapacity;
    }

    public void setTicketCapacity(int ticketCapacity) {
        if ( ticketCapacity < 0 ){
            throw new IllegalArgumentException("Ticket Capacity Should Be Greater than ZERO");
        }
        this.ticketCapacity = ticketCapacity;
    }

    // Method to save to JSON
    public void saveToJson(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    // Method to load from JSON
    public static TicketConfiguration loadFromJson(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, TicketConfiguration.class);
        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
            return null;
        }
    }
}
