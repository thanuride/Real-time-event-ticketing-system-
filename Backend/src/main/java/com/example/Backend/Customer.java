package com.example.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer implements Runnable{

    private String customerName;
    private  int noOfTickets;

    private TicketPool ticketpool;
    //creating an instance of TicketPool class using setter based injection
    @Autowired
    public void setTicketpool(TicketPool ticketpool){
        this.ticketpool = ticketpool;
    }
    //creating an instance of TicketConfiguration class using setter based injection
    private TicketConfiguration ticketConfiguration;
    @Autowired
    public void setTicketConfiguration(TicketConfiguration ticketConfiguration){
        this.ticketConfiguration = ticketConfiguration;
    }

    public Customer() {
    }

    public Customer(String customerName, int noOfTickets, TicketPool ticketpool, TicketConfiguration ticketConfiguration) {
        this.customerName = customerName;
        this.noOfTickets = noOfTickets;
        this.ticketpool = ticketpool;
        this.ticketConfiguration = ticketConfiguration;
    }

    public void run(){
        int availableTickets = ticketpool.getAvailableTickets().size();
        System.out.println("Available Tickets: " + availableTickets);

        if (availableTickets >= noOfTickets) {
            // Enough tickets are available, proceed with purchase
            ticketpool.removeTickets(noOfTickets);
            System.out.println(customerName + " successfully purchased " + noOfTickets + " ticket(s).");
        }
        else {
            // Not enough tickets available
            System.out.println("Not enough tickets available for " + customerName);
        }

        try{
            Thread.sleep(ticketConfiguration.getRetrievalRate());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }
}
