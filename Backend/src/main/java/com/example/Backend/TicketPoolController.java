package com.example.Backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticketpool")
public class TicketPoolController {

    private final TicketPool ticketPool;

    public TicketPoolController(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @GetMapping("/soldTickets")
    public String soldTickets(){
        return "Sold Tickets:" + ticketPool.getSoldTickets().size();
    }

    @GetMapping("/availableTickets")
    public String availableTickets(){
        return "Availble Tickets:" + ticketPool.getAvailableTickets().size();
    }
}
