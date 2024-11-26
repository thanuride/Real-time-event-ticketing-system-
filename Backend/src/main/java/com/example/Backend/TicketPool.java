package com.example.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TicketPool {

    //creating an instance of TicketConfiguration class using setter based injection
    private TicketConfiguration ticketConfiguration;
    @Autowired
    public void setTicketConfiguration(TicketConfiguration ticketConfiguration){
        this.ticketConfiguration = ticketConfiguration;
    }

    private List<Integer> totalTickets = Collections.synchronizedList(new ArrayList<>());
    private   List<Integer> soldTickets = Collections.synchronizedList(new ArrayList<>());
    private   List<Integer> availableTickets = Collections.synchronizedList(new ArrayList<>());
    private   int maxCapacity;
    private int count;


    // Method to add tickets, increasing total and available counts

    public synchronized void addTickets(int tickets ){
        maxCapacity = ticketConfiguration.getTicketCapacity();
//        totalTickets = ticketConfiguration.getTotalTickets();
        for (int i =1; i <= tickets;i++){
            if (availableTickets.size() < maxCapacity){
                count++ ;
                totalTickets.add(count);
                availableTickets.add(count);
            }
            else {
                System.out.println("Ticket pool has reached its maximum capacity");
                break;
            }
        }
//        ticketConfiguration.setTotalTickets(totalTickets);

//        System.out.println("available tickets "+availableTickets.size());
//        System.out.println("total tickets "+ totalTickets.size());
    }

    //methods to remove Tickets
    public synchronized  void removeTickets(int tickets){
        for (int i = 0; i < tickets && !availableTickets.isEmpty(); i++) {
            availableTickets.remove(0);
            soldTickets.add(tickets); // Optionally add to a sold tickets list if needed
        }
        System.out.println("Tickets after purchase: " + availableTickets.size());
    }


    public List<Integer> getSoldTickets() {
        return soldTickets;
    }

    public List<Integer> getAvailableTickets() {
        return availableTickets;
    }

}
