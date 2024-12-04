package backend.backendspringboot.API;

import backend.backendspringboot.application.CustomerService;
import backend.backendspringboot.domian.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private Customer customer;
    @Autowired
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private CustomerService customerService;
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/getTickets")
    public String getTickets(@RequestParam String customerName, @RequestParam int noOfTickets) {
        Customer customer = new Customer(customerName, noOfTickets);
        new Thread(() -> customerService.run()).start();
        return "Customer " + customerName + " is purchasing " + noOfTickets + " tickets.";
    }


}
