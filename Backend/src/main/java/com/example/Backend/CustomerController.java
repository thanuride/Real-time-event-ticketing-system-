package com.example.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private  Customer customer;

    @Autowired
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @PostMapping("/getTickets")
    public String getTickets(@RequestParam String customerName, @RequestParam int noOfTickets){

        // Set vendor details
        customer.setCustomerName(customerName);
        customer.setNoOfTickets(noOfTickets);


        // Run the customer logic in a thread
        Thread customerThread = new Thread(customer);
        customerThread.start();

        return "Customer " + customerName + " purchased " + noOfTickets + " tickets.";
    }
}
