package backend.backendspringboot.API;

import backend.backendspringboot.application.CustomerService;
import backend.backendspringboot.domian.Customer;
import backend.backendspringboot.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
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
        customer.setCustomerName(customerName);
        customer.setNoOfTickets(noOfTickets);

        // Save customer data to the database
        customerService.save(customer);

        Log.logInfo("Customer " + customerName + " requested to purchase " + noOfTickets + " tickets.");
        Log.logInfo("Starting ticket purchase process for " + customerName + ".");

        new Thread(() -> customerService.run()).start();
        return "Customer " + customerName + " is purchasing " + noOfTickets + " tickets.";
    }


}
