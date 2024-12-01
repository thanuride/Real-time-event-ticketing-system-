package backend.backendspringboot.application;

import backend.backendspringboot.domian.TicketConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorService implements  Runnable{

    private TicketConfiguration ticketConfiguration;
    @Autowired
    public void setTicketConfiguration(TicketConfiguration ticketConfiguration) {
        this.ticketConfiguration = ticketConfiguration;
    }

    private TicketPoolService ticketPoolService;
    @Autowired
    public void setTicketPoolService(TicketPoolService ticketPoolService) {
        this.ticketPoolService = ticketPoolService;
    }


    public void run(){
        int count = 0;
        for (int i = 0 ;i < ticketConfiguration.getTotalTickets(); i++){
            count++;
            ticketPoolService.addTickets(count);
        }try {
            Thread.sleep(ticketConfiguration.getTicketReleaseRate() * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
