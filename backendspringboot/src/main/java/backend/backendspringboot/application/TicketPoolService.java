package backend.backendspringboot.application;

import backend.backendspringboot.domian.TicketConfiguration;
import backend.backendspringboot.domian.TicketPool;
import backend.backendspringboot.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TicketPoolService {
    // Dependency Injection for TicketPool
    private TicketPool ticketPool;
    @Autowired
    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Dependency Injection for TicketConfiguration
    private TicketConfiguration ticketConfiguration;
    @Autowired
    public void setTicketConfiguration(TicketConfiguration ticketConfiguration) {
        this.ticketConfiguration = ticketConfiguration;
    }

    //method to add tickets
    public synchronized void addTickets(int tickets){
        ticketPool.getTotalTickets().add(tickets);
        ticketPool.getAvailableTickets().add(tickets);
        System.out.println(ticketPool.getAvailableTickets().size());
    }

    //method to remove tickets
    public synchronized void removeTickets(int tickets) {
        for (int i = 0; i < tickets; i++) {
            if (!ticketPool.getAvailableTickets().isEmpty()) {
                ticketPool.getAvailableTickets().remove(0); // Remove one ticket from available tickets
                ticketPool.getSoldTickets().add(1); // Add a sold ticket
            } else {
                System.out.println("No more tickets available to remove.");
                Log.logWarning("No more tickets available to remove.");
                break; // If no tickets are available, stop processing
            }
        }

        System.out.println("Sold tickets: " + ticketPool.getSoldTickets().size());
        Log.logInfo("INFO: Sold tickets:"+ ticketPool.getSoldTickets().size());
    }

}
