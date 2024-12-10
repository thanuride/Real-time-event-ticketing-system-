package backend.backendspringboot.application;

import backend.backendspringboot.domian.TicketConfiguration;
import backend.backendspringboot.domian.TicketPool;
import backend.backendspringboot.domian.Vendor;
import backend.backendspringboot.domian.VendorRepository;
import backend.backendspringboot.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorService implements  Runnable{
    private volatile boolean stopRequested = false; // Control flag to stop the thread
    private boolean isThreadRunning = false;
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

    private VendorRepository vendorRepository;
    @Autowired
    public void setVendorRepository(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public VendorService(TicketConfiguration ticketConfiguration, Vendor vendor, TicketPool ticketPool,TicketPoolService ticketPoolService) {
        this.ticketConfiguration = ticketConfiguration;
        this.vendor = vendor;
        this.ticketPool = ticketPool;
        this.ticketPoolService=ticketPoolService;
    }

    //to save in the database
    public void save(Vendor vendor){
        vendorRepository.save(vendor);
    }


    // Method to request stopping the thread
    public void stopRequest() {
        stopRequested = true;
        Log.logInfo("Stop requested for vendor: " + vendor.getVendorName());
    }

    public void run() {

        // Thread starts
        isThreadRunning = true;
        int totalTickets = ticketConfiguration.getTotalTickets();
        for (int i = 0; i < totalTickets; i++) {
            if (stopRequested) {
                Log.logInfo("Vendor thread stopped by request for vendor: " + vendor.getVendorName());
                break;
            }

            if (ticketPool.getAvailableTickets().size() < ticketConfiguration.getMaxTicketCapacity()) {
                ticketPoolService.addTickets(1);
                System.out.println(vendor.getVendorName() + " added a ticket.");
                Log.logInfo("Vendor " + vendor.getVendorName() + " added a ticket.");
                try {
                    Thread.sleep(ticketConfiguration.getTicketReleaseRate() * 1000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Log.logWarning("Thread interrupted for vendor: " + vendor.getVendorName());
                }
            } else {
                System.out.println("Maximum capacity reached for vendor: " + vendor.getVendorName());
                Log.logWarning("Maximum capacity reached for vendor: " + vendor.getVendorName());
                break;
            }
        }

        Log.logInfo("Vendor thread finished for: " + vendor.getVendorName());
        isThreadRunning = false; // Thread completes
        Log.logInfo("Vendor thread finished for " + vendor.getVendorName());

    }



}
