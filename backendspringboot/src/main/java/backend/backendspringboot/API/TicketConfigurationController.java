package backend.backendspringboot.API;

import backend.backendspringboot.application.TicketConfigurationService;
import backend.backendspringboot.domian.TicketConfiguration;
import backend.backendspringboot.domian.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketConfigurationController {

    private TicketConfiguration ticketConfiguration;
    @Autowired
    public void setTicketConfiguration(TicketConfiguration ticketConfiguration) {
        this.ticketConfiguration = ticketConfiguration;
    }

    private TicketConfigurationService ticketConfigurationService;
    @Autowired
    public void setTicketConfigurationService(TicketConfigurationService ticketConfigurationService) {
        this.ticketConfigurationService = ticketConfigurationService;
    }

    @PostMapping("/setConfigurations")
    public String configure(@RequestBody TicketConfiguration config){

        ticketConfiguration.setTotalTickets(config.getTotalTickets());
        ticketConfiguration.setTicketReleaseRate(config.getTicketReleaseRate());
        ticketConfiguration.setCustomerRetrievalRate(config.getCustomerRetrievalRate());
        ticketConfiguration.setMaxTicketCapacity(config.getMaxTicketCapacity());

        ticketConfigurationService.save(ticketConfiguration);

        return "configured the parameters " ;
    }


}
