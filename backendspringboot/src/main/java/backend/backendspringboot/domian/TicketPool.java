package backend.backendspringboot.domian;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TicketPool {
    private List<Integer> totalTickets = Collections.synchronizedList(new ArrayList<>());
    private   List<Integer> soldTickets = Collections.synchronizedList(new ArrayList<>());
    private   List<Integer> availableTickets = Collections.synchronizedList(new ArrayList<>());

    public TicketPool() {
    }

    public TicketPool(List<Integer> totalTickets, List<Integer> soldTickets, List<Integer> availableTickets) {
        this.totalTickets = totalTickets;
        this.soldTickets = soldTickets;
        this.availableTickets = availableTickets;
    }

    public List<Integer> getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(List<Integer> totalTickets) {
        this.totalTickets = totalTickets;
    }

    public List<Integer> getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(List<Integer> soldTickets) {
        this.soldTickets = soldTickets;
    }

    public List<Integer> getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(List<Integer> availableTickets) {
        this.availableTickets = availableTickets;
    }

}
