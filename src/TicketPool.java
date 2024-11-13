import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private  List<Integer> totalTickets = Collections.synchronizedList(new ArrayList<>());
    private   List<Integer> soldTickets = Collections.synchronizedList(new ArrayList<>());
    private   List<Integer> availableTickets = Collections.synchronizedList(new ArrayList<>());
    private   int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Method to add tickets, increasing total and available counts
    public synchronized void addTickets(int tickets ){
        for (int i =1; i <= tickets;i++){
            if (availableTickets.size() < maxCapacity){
                totalTickets.add(1);
                availableTickets.add(1);
            }
            else {
                System.out.println("Ticket pool has reached its maximum capacity");
                break;
            }
        }
        System.out.println("available tickets "+availableTickets.size());
        System.out.println("total tickets "+ totalTickets.size());
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

    public List<Integer> getAvailableTickets() {
        return availableTickets;
    }
}
