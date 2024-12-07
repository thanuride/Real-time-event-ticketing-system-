public class Vendor implements Runnable{
    private String vendorName;
    private String vendorId;
    private TicketPool ticketPool;
    private  TicketConfiguration ticketConfiguration;

    public Vendor(String vendorName, String vendorId, TicketPool ticketPool,TicketConfiguration ticketConfiguration) {
        this.vendorName = vendorName;
        this.vendorId = vendorId;
        this.ticketPool = ticketPool;
        this.ticketConfiguration= ticketConfiguration;
    }

    @Override
    public void run() {
        for (int i = 0; i < ticketConfiguration.getTotalTickets(); i++) {
            ticketPool.addTickets(1); // Adds one ticket at a time
            Log.logInfo(vendorName + " added a ticket." );
            System.out.println(vendorName+" added Tickets");
            try {
                Thread.sleep(ticketConfiguration.getTicketReleaseRate() * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Log.logWarning("Thread interrupted while adding tickets. Vendor: " + vendorName);
                throw new RuntimeException("Thread interrupted while adding tickets", e);
            }

        }

    }

}
