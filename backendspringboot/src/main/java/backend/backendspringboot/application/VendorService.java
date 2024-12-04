package backend.backendspringboot.application;

import backend.backendspringboot.domian.TicketConfiguration;
import backend.backendspringboot.domian.TicketPool;
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

    private TicketPool ticketPool;
    @Autowired
    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public void run() {
        int totalTickets = ticketConfiguration.getTotalTickets();
        for (int i = 0; i < totalTickets; i++) {
            ticketPoolService.addTickets(1); // Adds one ticket at a time
            try {
                Thread.sleep(ticketConfiguration.getTicketReleaseRate() * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while adding tickets", e);
            }
        }

//        System.out.println(ticketPool.getAvailableTickets().size());
    }


}
