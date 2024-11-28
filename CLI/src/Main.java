import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //creating an TicketConfiguration object
        TicketConfiguration ticketConfiguration = new TicketConfiguration();

        boolean continueConfiguration = true;

        System.out.println("*".repeat(43) + "\nWelcome to the Ticketing Management System\n" + "*".repeat(43));

        while (continueConfiguration){

            System.out.println("Configure the System");

            while (true){
                try {
                    System.out.print("Enter Total Number of Tickets:");
                    int totalTickets = input.nextInt();
                    ticketConfiguration.setTotalTickets(totalTickets);
                    break;
                }catch (InputMismatchException e) {
                    System.out.println("Invalid input. Only integers are allowed.");
                    input.nextLine();  // Clear the invalid input
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true){
                try{
                    System.out.print("Enter Ticket Releasing Rate:");
                    int ticketReleaseRate = input.nextInt();
                    ticketConfiguration.setTicketReleaseRate(ticketReleaseRate);
                    break;
                }catch (InputMismatchException e) {
                    System.out.println("Invalid input. Only integers are allowed.");
                    input.nextLine();  // Clear the invalid input
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true){
                try {
                    System.out.print("Enter Ticket Retrieving Rate:");
                    int ticketRetrievalRate = input.nextInt();
                    ticketConfiguration.setRetrievalRate(ticketRetrievalRate);
                    break;
                }catch (InputMismatchException e) {
                    System.out.println("Invalid input. Only integers are allowed.");
                    input.nextLine();  // Clear the invalid input
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }


            while (true){
                try {
                    System.out.print("Enter Maximum Ticket Capacity:");
                    int maxCapacity = input.nextInt();
                    ticketConfiguration.setTicketCapacity(maxCapacity);
                    break;
                }catch (InputMismatchException e) {
                    System.out.println("Invalid input. Only integers are allowed.");
                    input.nextLine();  // Clear the invalid input
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("\n------------Configured Value------------");
            System.out.println("Total Tickets: "+ticketConfiguration.getTotalTickets());
            System.out.println("Ticket Release Rate:"+ ticketConfiguration.getTicketReleaseRate());
            System.out.println("Ticket Retrieval Rate:" + ticketConfiguration.getRetrievalRate());
            System.out.println("Maximum Ticket Capacity:" + ticketConfiguration.getTicketCapacity());

            // Save configuration to a JSON file
            ticketConfiguration.saveToJson("config.json");
            System.out.println("Configuration saved to config.json");

            String userResponse ;

            while (true){
                System.out.print("\nDo you want to configure again? (yes/no): ");
                userResponse = input.next().trim().toLowerCase(); //used trim to remove white space

                if (userResponse.equals("yes") || userResponse.equals("no")) {
                    break;
                } else {
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            }
            if (!userResponse.equals("yes")) {
                continueConfiguration = false;
            }
        }
    }
}