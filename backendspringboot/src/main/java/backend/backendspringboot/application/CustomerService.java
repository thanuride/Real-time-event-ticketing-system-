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

    public void run(){
        int count = 0;
        for (int i = 0; i < customer.getNoOfTickets(); i++){
            if (ticketpool.getAvailableTickets().size() > customer.getNoOfTickets()){
                count ++;
            }else {
                // Not enough tickets available
                System.out.println("Not enough tickets available for " + customer.getCustomerName());
            }
            try{
                Thread.sleep(ticketConfiguration.getCustomerRetrievalRate());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ticketPoolService.removeTickets(count);
    }
}
