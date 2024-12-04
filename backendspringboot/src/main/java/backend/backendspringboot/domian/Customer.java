package backend.backendspringboot.domian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer {
    private String customerName;
    private  int noOfTickets;

    public Customer() {
    }

    public Customer(String customerName, int noOfTickets) {
        this.customerName = customerName;
        this.noOfTickets = noOfTickets;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }
}
