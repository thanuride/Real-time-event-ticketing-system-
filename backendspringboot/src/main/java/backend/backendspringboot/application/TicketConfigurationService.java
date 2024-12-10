package backend.backendspringboot.application;

import backend.backendspringboot.domian.TicketConfigRepository;
import backend.backendspringboot.domian.Vendor;
import org.springframework.stereotype.Component;

import backend.backendspringboot.domian.TicketConfiguration;
//import com.google.gson.Gson;
//import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.IOException;

@Component
public class TicketConfigurationService {

    private TicketConfiguration ticketConfiguration;
    @Autowired
    public void setTicketConfiguration(TicketConfiguration ticketConfiguration) {
        this.ticketConfiguration = ticketConfiguration;
    }

    private TicketConfigRepository ticketConfigRepository;
    @Autowired
    public void setTicketConfigRepository(TicketConfigRepository ticketConfigRepository) {
        this.ticketConfigRepository = ticketConfigRepository;
    }

    //to save in the database
    public void save(TicketConfiguration ticketConfiguration){
        ticketConfigRepository.save(ticketConfiguration);
    }

}

