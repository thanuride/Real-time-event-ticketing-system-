package backend.backendspringboot.application;

import backend.backendspringboot.domian.TicketConfiguration;
import backend.backendspringboot.domian.TicketPool;
import backend.backendspringboot.domian.Vendor;
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

    private Vendor vendor;
    @Autowired
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    private TicketPool ticketPool;
    @Autowired
    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    private boolean isThreadRunning = false;

    public boolean isThreadRunning() {
        return isThreadRunning;
    }

    public void setThreadRunning(boolean threadRunning) {
        isThreadRunning = threadRunning;
    }

    public void run() {
        isThreadRunning = true; // Thread starts
        int totalTickets = ticketConfiguration.getTotalTickets();
        for (int i = 0; i < totalTickets; i++) {
            if ( ticketPool.getAvailableTickets().size() < ticketConfiguration.getMaxTicketCapacity()){
                ticketPoolService.addTickets(1); // Adds one ticket at a time
                System.out.println(vendor.getVendorName()+" Ticket added");
                try {
                    Thread.sleep(ticketConfiguration.getTicketReleaseRate() * 1000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread interrupted while adding tickets", e);
                }
            }else {
                System.out.println("Maximum Capacity reaches");
                break;
            }

        }
        isThreadRunning = false; // Thread completes

        System.out.println(ticketPool.getAvailableTickets().size());
    }


}
