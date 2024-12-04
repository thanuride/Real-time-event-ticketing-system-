package backend.backendspringboot.application;

import backend.backendspringboot.domian.TicketConfiguration;
import backend.backendspringboot.domian.TicketPool;
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
        int count = 0;
        for (int i = 1; i <= tickets;i++){
            if (ticketPool.getAvailableTickets().size() < ticketConfiguration.getMaxTicketCapacity() ){
                count ++;
            } else {
                System.out.println("Ticket pool has reached its maximum capacity");
                break;
            }
        }
        ticketPool.getTotalTickets().add(count);
        ticketPool.getAvailableTickets().add(count);
    }

    //method to remove tickets
    public  synchronized  void removeTickets(int tickets){
        int count = 0;
        for (int i = 1; i <= tickets && !ticketPool.getAvailableTickets().isEmpty(); i++ ){
            count ++;
        }
        ticketPool.getAvailableTickets().remove(count);
        ticketPool.getSoldTickets().add(count);
        System.out.println("Tickets after purchase: " + ticketPool.getAvailableTickets().size());
    }

}
