package com.example.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Vendor implements Runnable {
    private String vendorName;
    private String vendorID;
    private int ticketCount;

    private int ticketCapacity;


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

    public Vendor() {
    }

    //constructor
    public Vendor(String vendorName, String vendorID) {
        this.vendorName = vendorName;
        this.vendorID = vendorID;

    }

    @Override
    public  void run(){

//        ticketCount = ticketConfiguration.getTotalTickets().size();

        //add tickets
        ticketpool.addTickets(ticketCount);
        System.out.println(vendorName + " added " + ticketpool.getAvailableTickets().size() +" amount of tickets.");

        try {
            Thread.sleep(ticketConfiguration.getTicketReleaseRate());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public void setTicketCapacity(int ticketCapacity) {
        this.ticketCapacity = ticketCapacity;
        ticketConfiguration.setTicketCapacity(ticketCapacity);
    }
}
