package backend.backendspringboot.application;


import backend.backendspringboot.domian.Customer;
import backend.backendspringboot.domian.TicketConfiguration;
import backend.backendspringboot.domian.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService implements Runnable {
    private Customer customer;
    @Autowired
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Autowired
    private TicketPoolService ticketPoolService;

    public void setTicketPoolService(TicketPoolService ticketPoolService) {
        this.ticketPoolService = ticketPoolService;
    }

    @Autowired
    private TicketPool ticketpool;

    public void setTicketpool(TicketPool ticketpool) {
        this.ticketpool = ticketpool;
    }
    @Autowired
    private TicketConfiguration ticketConfiguration;

    public void setTicketConfiguration(TicketConfiguration ticketConfiguration) {
        this.ticketConfiguration = ticketConfiguration;
    }

    public CustomerService() {
    }

    public void run() {
        if (ticketpool.getAvailableTickets().isEmpty()) {
            System.out.println("No tickets available for purchase.");
            return;
        }

        int ticketsToPurchase = customer.getNoOfTickets();

        if (ticketpool.getAvailableTickets().size() < ticketsToPurchase) {
            System.out.println("Not enough tickets available for " + customer.getCustomerName());
            return;
        }

        // Remove tickets one by one, simulating delay
        for (int i = 0; i < ticketsToPurchase; i++) {
            ticketPoolService.removeTickets(1); // Removes one ticket at a time
            System.out.println("Ticket purchased by " + customer.getCustomerName());

            // Simulate processing delay
            try {
                Thread.sleep(ticketConfiguration.getCustomerRetrievalRate() * 1000L); // Casted to long as multiplication will be long
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Propagate the interrupt
                System.out.println("Customer thread interrupted.");
            }
        }

        System.out.println("Purchase complete for " + customer.getCustomerName());
        System.out.println("Available Tickets: " + ticketpool.getAvailableTickets().size());
        System.out.println("Sold Tickets: " + ticketpool.getSoldTickets().size());
    }


//    public void run(){
////        if (ticketpool.getAvailableTickets().isEmpty()) {
////            System.out.println("No tickets available for purchase.");
////            return;
////        }
////        for (int i = 0; i < customer.getNoOfTickets(); i++){
////            if (ticketpool.getAvailableTickets().size() > customer.getNoOfTickets()){
////                ticketPoolService.removeTickets(1);
////                System.out.println("Customer Purchased Ticket");
////            }else {
////                // Not enough tickets available
////                System.out.println("Not enough tickets available for " + customer.getCustomerName());
////            }try{
////                Thread.sleep(ticketConfiguration.getCustomerRetrievalRate());
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////
////            System.out.println(ticketpool.getAvailableTickets());
////            System.out.println(ticketpool.getSoldTickets());
////        }
//
//        if (ticketpool.getAvailableTickets().isEmpty()) {
//            System.out.println("No tickets available for purchase.");
//            return;
//        }
//
//        int ticketsToPurchase = customer.getNoOfTickets();
//
//        if (ticketpool.getAvailableTickets().size() < ticketsToPurchase) {
//            System.out.println("Not enough tickets available for " + customer.getCustomerName());
//            return;
//        }
//
//        // Remove tickets one by one, simulating delay
//        for (int i = 0; i < ticketsToPurchase; i++) {
//            ticketPoolService.removeTickets(1); // Removes one ticket at a time
//            System.out.println("Ticket purchased by " + customer.getCustomerName());
//
//            // Simulate processing delay
//            try {
//                Thread.sleep(ticketConfiguration.getCustomerRetrievalRate());
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.out.println("Customer thread interrupted.");
//            }
//        }
//
//        System.out.println("Purchase complete for " + customer.getCustomerName());
//        System.out.println("Available Tickets: " + ticketpool.getAvailableTickets().size());
//        System.out.println("Sold Tickets: " + ticketpool.getSoldTickets().size());

}
