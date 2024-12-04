package backend.backendspringboot.application;

import org.springframework.stereotype.Component;

import backend.backendspringboot.domian.TicketConfiguration;
//import com.google.gson.Gson;
//import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.IOException;

public class TicketConfigurationService {

    private TicketConfiguration ticketConfiguration;
    @Autowired
    public void setTicketConfiguration(TicketConfiguration ticketConfiguration) {
        this.ticketConfiguration = ticketConfiguration;
    }



//    @PostConstruct
//    public void init() {
//        // Load configuration at startup
//        TicketConfiguration loadedConfig = loadFromJson("");
//        if (loadedConfig != null) {
//            this.ticketConfiguration.setTotalTickets(loadedConfig.getTotalTickets());
//            this.ticketConfiguration.setTicketReleaseRate(loadedConfig.getTicketReleaseRate());
//            this.ticketConfiguration.setCustomerRetrievalRate(loadedConfig.getCustomerRetrievalRate());
//            this.ticketConfiguration.setMaxTicketCapacity(loadedConfig.getMaxTicketCapacity());
//            System.out.println("Ticket configuration successfully loaded.");
//        } else {
//            System.out.println("Failed to load ticket configuration.");
//        }
//    }

//    // Method to load from JSON
//    public TicketConfiguration loadFromJson(String filepath){
//        Gson gson = new Gson();
//        try(FileReader reader = new FileReader(filepath)){
//            return gson.fromJson(reader, TicketConfiguration.class);
//        }catch (IOException e) {
//            System.out.println("Error loading configuration: " + e.getMessage());
//            return null;
//        }
//    }
}

