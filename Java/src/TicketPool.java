import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private  List<Integer> totalTickets = Collections.synchronizedList(new ArrayList<>());
    private   List<Integer> soldTickets = Collections.synchronizedList(new ArrayList<>());
    private   List<Integer> availableTickets = Collections.synchronizedList(new ArrayList<>());
    private   int maxCapacity;

    private int count;


    // Method to add tickets, increasing total and available counts
    public synchronized void addTickets(int tickets ){
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
        System.out.println("available tickets "+availableTickets.size());
        System.out.println("total tickets "+ totalTickets.size());
    }

    public synchronized  void removeTickets(int tickets){
        for (int i = 0; i < tickets && !availableTickets.isEmpty(); i++) {
            availableTickets.remove(0);
            soldTickets.add(1); // Optionally add to a sold tickets list if needed
        }
        System.out.println("Tickets after purchase: " + availableTickets.size());
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

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
