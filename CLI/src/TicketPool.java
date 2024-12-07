import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private List<Integer> totalTickets = Collections.synchronizedList(new ArrayList<>());
    private   List<Integer> soldTickets = Collections.synchronizedList(new ArrayList<>());
    private   List<Integer> availableTickets = Collections.synchronizedList(new ArrayList<>());

    private TicketConfiguration ticketConfiguration;

    public TicketPool(TicketConfiguration ticketConfiguration) {
        this.ticketConfiguration = ticketConfiguration;
    }

    // Method to add tickets, increasing total and available counts
    public synchronized void addTickets(int tickets) {
        for (int i = 0; i < tickets; i++) {
            if (totalTickets.size() < ticketConfiguration.getTicketCapacity()) {
                availableTickets.add(1); // Simulating adding tickets
                totalTickets.add(1);
                System.out.println("\nTicket added to pool.");
                Log.logInfo("Ticket added to pool. Total available tickets: " + availableTickets.size());
                notifyAll(); // Notify waiting threads that tickets are available
            } else {
                Log.logWarning("Maximum ticket capacity reached. Cannot add more tickets.");
                System.out.println("Maximum ticket capacity reached.");
                return;
            }
        }
    }


    public synchronized void removeTickets(int tickets) {
        for (int i = 0; i < tickets; i++) {

            while (availableTickets.isEmpty()) {
                try {
                    wait(ticketConfiguration.getRetrievalRate()*1000L); // Wait until tickets are added
                    Log.logInfo(" Waiting for tickets to be added.");
                    System.out.println("\nNo tickets available. Waiting...");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Log.logWarning("Thread interrupted while waiting for tickets.");
                    return;
                }
            }
            availableTickets.remove(0); // Simulating ticket purchase
            soldTickets.add(1);
        }
    }

    public List<Integer> getAvailableTickets() {
        return availableTickets;
    }

    public List<Integer> getTotalTickets() {
        return totalTickets;
    }

    public List<Integer> getSoldTickets() {
        return soldTickets;
    }
}
