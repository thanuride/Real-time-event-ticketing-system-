public class Customer implements  Runnable {
    private String customerName;
    private  int noOfTickets;
    private  TicketPool ticketPool;
    private TicketConfiguration ticketConfiguration;

    public Customer(String customerName, int noOfTickets, TicketPool ticketPool, TicketConfiguration ticketConfiguration) {
        this.customerName = customerName;
        this.noOfTickets = noOfTickets;
        this.ticketPool = ticketPool;
        this.ticketConfiguration = ticketConfiguration;
    }

    @Override
    public void run() {
        for (int i = 0; i < noOfTickets; i++) {
            ticketPool.removeTickets(1); // Removes one ticket at a time
            System.out.println("\nTicket purchased by " + customerName);
            Log.logInfo("Ticket purchased by " + customerName );
                    // Simulate processing delay
            try {
                Thread.sleep(ticketConfiguration.getRetrievalRate()*1000L); //casted to long as multiplication will be long
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer thread interrupted.");
                Log.logWarning("Customer thread interrupted during sleep. Customer: " + customerName);
            }
        }

        System.out.println("\n"+"*".repeat(5)+"PURCHASE IS FINISHED FOR " + customerName + "*".repeat(5) );
        Log.logInfo("PURCHASE IS FINISHED FOR " + customerName + ". All tickets have been purchased.");

    }

    public String getCustomerName() {
        return customerName;
    }
}
