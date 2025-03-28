package com.example.Backend;

import org.springframework.stereotype.Component;

@Component
public class TicketConfiguration {
    private int totalTickets;
    private  int ticketReleaseRate;
    private  int retrievalRate;
    private int ticketCapacity;

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getRetrievalRate() {
        return retrievalRate;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getTicketCapacity() {
        return ticketCapacity;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public void setRetrievalRate(int retrievalRate) {
        this.retrievalRate = retrievalRate;
    }

    public void setTicketCapacity(int ticketCapacity) {
        //if max set the macCapacity below 0.
        if (ticketCapacity <0){
            try {
                throw new IllegalAccessException("Max capacity must be greater than zero");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        this.ticketCapacity = ticketCapacity;
    }
}
