package backend.backendspringboot.API;

import backend.backendspringboot.domian.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticketpool")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketPoolController {

    private TicketPool ticketPool;
    @Autowired
    public void setTicketPool(TicketPool ticketPool) {
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
