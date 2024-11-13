
public class  Vendor implements Runnable {
    private String vendorName;
    private String vendorID;
    private TicketPool ticketPool ;
    private int ticketCount;
    private final int ticketReleaseRate = 1000;

    public Vendor(String vendorName, String vendorID, TicketPool ticketPool,int ticketCount) {
        this.vendorName = vendorName;
        this.vendorID = vendorID;
        this.ticketPool = ticketPool;
        this.ticketCount = ticketCount;
    }


    @Override
    public void run(){
        // Add tickets to the pool
        ticketPool.addTickets(ticketCount);
        System.out.println( vendorName+ " added "+ ticketPool.getTotalTickets().size() + " amount of Tickets.");

        //control release rate
        try{
            Thread.sleep(ticketReleaseRate);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
