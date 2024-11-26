public class Customer implements Runnable{
    private String customerName;
    private TicketPool ticketPool;

    private int noOfTickets ;
    private final int customerRetrievalRate = 1000;

    public Customer(String customerName, TicketPool ticketPool, int noOfTickets) {
        this.customerName = customerName;
        this.ticketPool = ticketPool;
        this.noOfTickets = noOfTickets;
    }

    @Override
    public void run(){
        int availableTickets = ticketPool.getAvailableTickets().size();
        System.out.println("Available Tickets: " + availableTickets);

        if (availableTickets >= noOfTickets) {
            // Enough tickets are available, proceed with purchase
            ticketPool.removeTickets(noOfTickets);
            System.out.println(customerName + " successfully purchased " + noOfTickets + " ticket(s).");
        }
        else {
            // Not enough tickets available
            System.out.println("Not enough tickets available for " + customerName);
        }

        try{
            Thread.sleep(customerRetrievalRate);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
