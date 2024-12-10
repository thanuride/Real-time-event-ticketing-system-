import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // Create and store threads
        List<Thread> threads = new ArrayList<>();


        boolean continueConfiguration = true;

        Log.logInfo("\nTicketing Management System Started");
        System.out.println("*".repeat(43) + "\nWelcome to the Ticketing Management System\n" + "*".repeat(43));
        while (continueConfiguration) {
            System.out.println("Configure the System");
            int totalTickets;
            int ticketReleaseRate;
            int ticketRetrievalRate;
            int maxCapacity;

            while (true) {
                try {
                    System.out.print("Enter Total Number of Tickets:");
                    totalTickets = input.nextInt();
                    if (totalTickets < 0){
                        // Log an error if the input is invalid
                        Log.logWarning("Invalid input: Total tickets cannot be less than zero.");
                        throw new IllegalArgumentException("Total Tickets Should Be Greater than ZERO");
                    }
                    break;
                } catch (InputMismatchException e) {
                    // Log the error for invalid input type
                    Log.logWarning("Invalid input type: Only integers are allowed.");
                    System.out.println("Invalid input. Only integers are allowed.");
                    input.nextLine();  // Clear the invalid input
                } catch (IllegalArgumentException e) {
                    Log.logWarning("Input validation failed: " + e.getMessage());
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Enter Ticket Releasing Rate:");
                    ticketReleaseRate = input.nextInt();
                    if (ticketReleaseRate < 0){
                        Log.logWarning("Invalid input: Ticket release rate cannot be less than zero.");
                        throw new IllegalArgumentException("Ticket release rate Should Be Greater than ZERO");
                    }
                    break;
                } catch (InputMismatchException e) {

                    Log.logWarning("Invalid input type: Only integers are allowed.");
                    System.out.println("Invalid input. Only integers are allowed.");
                    input.nextLine();  // Clear the invalid input
                } catch (IllegalArgumentException e) {

                    Log.logWarning("Input validation failed: " + e.getMessage());
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Enter Ticket Retrieving Rate:");
                    ticketRetrievalRate = input.nextInt();
                    if (ticketRetrievalRate < 0){

                        Log.logWarning("Invalid input: Ticket retrieval rate cannot be less than zero.");
                        throw new IllegalArgumentException("Ticket retrieval rate Should Be Greater than ZERO");
                    }
                    break;
                } catch (InputMismatchException e) {

                    Log.logWarning("Invalid input type: Only integers are allowed.");
                    System.out.println("Invalid input. Only integers are allowed.");
                    input.nextLine();  // Clear the invalid input
                } catch (IllegalArgumentException e) {

                    Log.logWarning("Input validation failed: " + e.getMessage());
                    System.out.println(e.getMessage());
                }
            }


            while (true) {
                try {
                    System.out.print("Enter Maximum Ticket Capacity:");
                    maxCapacity = input.nextInt();
                    if (maxCapacity < 0){

                        Log.logWarning("Invalid input: Max capacity rate cannot be less than zero.");
                        throw new IllegalArgumentException("Max capacity Should Be Greater than ZERO");
                    }
                    break;
                } catch (InputMismatchException e) {

                    Log.logWarning("Invalid input type: Only integers are allowed.");
                    System.out.println("Invalid input. Only integers are allowed.");
                    input.nextLine();  // Clear the invalid input
                } catch (IllegalArgumentException e) {

                    Log.logWarning("Input validation failed: " + e.getMessage());
                    System.out.println(e.getMessage());
                }
            }

            TicketConfiguration ticketConfiguration = new TicketConfiguration(totalTickets,ticketReleaseRate,ticketRetrievalRate,maxCapacity);
            TicketPool ticketPool = new TicketPool(ticketConfiguration);

            System.out.println("\n------------Configured Value------------");
            System.out.println("Total Tickets: " + ticketConfiguration.getTotalTickets());
            System.out.println("Ticket Release Rate:" + ticketConfiguration.getTicketReleaseRate());
            System.out.println("Ticket Retrieval Rate:" + ticketConfiguration.getRetrievalRate());
            System.out.println("Maximum Ticket Capacity:" + ticketConfiguration.getTicketCapacity());
            System.out.println("-".repeat(40));

            //Logging
            Log.logInfo("\n------------Configured Value------------");
            Log.logInfo("Total Tickets: " + ticketConfiguration.getTotalTickets());
            Log.logInfo("Ticket Release Rate:" + ticketConfiguration.getTicketReleaseRate());
            Log.logInfo("Ticket Retrieval Rate:" + ticketConfiguration.getRetrievalRate());
            Log.logInfo("Maximum Ticket Capacity:" + ticketConfiguration.getTicketCapacity());
            Log.logInfo("-".repeat(40));

            String userResponse;

            while (true) {
                System.out.print("\nDo you want to configure again? (yes/no): ");
                userResponse = input.next().trim().toLowerCase(); //used trim to remove white space

                if (userResponse.equals("yes") || userResponse.equals("no")) {
                    Log.logInfo("\nUser response received: " + userResponse);
                    break;
                } else {
                    Log.logWarning("\nInvalid user response: " + userResponse);
                    System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                }
            }


            if (!userResponse.equals("yes")) {
                continueConfiguration = false;
                Log.logInfo("\nUser chose not to configure again. Exiting configuration.");
            }


            // Save configuration to a JSON file
            ticketConfiguration.saveToJson("config.json");
            System.out.println("\nConfiguration saved to config.json");
            Log.logInfo("Configuration saved to config.json");


            // Collect number of vendors
            int numVendors ;
            while (true) {
                try {
                    System.out.print("\nEnter the number of Vendors: ");
                    numVendors = input.nextInt();
                    if (numVendors <= 0) {
                        Log.logWarning("Invalid input: Number of vendors must be greater than zero. User entered: " + numVendors);
                        throw new IllegalArgumentException("Number of vendors must be greater than ZERO.");
                    }
                    Log.logInfo("\nNumber of vendors entered: " + numVendors);
                    break;
                } catch (InputMismatchException e) {
                    Log.logWarning("Invalid input type: Only integers are allowed. User input: " + input.nextLine());
                    System.out.println("Invalid input. Only integers are allowed.");
                    input.nextLine();
                } catch (IllegalArgumentException e) {
                    Log.logWarning("Invalid number of vendors: " + e.getMessage());
                    System.out.println(e.getMessage());
                }
            }

            // Collect number of customers
            int numCustomers ;
            while (true) {
                try {
                    System.out.print("\nEnter the number of Customers: ");
                    numCustomers = input.nextInt();
                    if (numCustomers <= 0) {
                        Log.logWarning("Invalid input: Number of customers must be greater than zero. User entered: " + numCustomers);
                        throw new IllegalArgumentException("Number of customers must be greater than ZERO.");
                    }
                    break;
                } catch (InputMismatchException e) {
                    Log.logWarning("Invalid input type: Only integers are allowed. User input: " + input.nextLine());
                    System.out.println("Invalid input. Only integers are allowed.");
                    input.nextLine();
                } catch (IllegalArgumentException e) {
                    Log.logWarning("Invalid number of vendors: " + e.getMessage());
                    System.out.println(e.getMessage());
                }
            }


            // Create Vendor Threads
            for (int i = 1; i <= numVendors; i++) {
                Vendor vendor = new Vendor("Vendor " + i, "V" + i, ticketPool, ticketConfiguration);
                threads.add(new Thread(vendor));
            }

            int customerTicket = 3;
            // Create Customer Threads
            for (int i = 1; i <= numCustomers; i++) {
                Customer customer = new Customer("Customer " + i,customerTicket, ticketPool, ticketConfiguration);
                threads.add(new Thread(customer));

                System.out.println("customer name:" + customer.getCustomerName());
            }


            // Start all threads
            for (Thread thread : threads) {
                thread.start();
            }

            // Wait for all threads to finish
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted.");
                }
            }


            System.out.println("+".repeat(40));
            System.out.println("Available Tickets: "+ticketPool.getAvailableTickets().size());
            System.out.println("Sold Tickets: "+ ticketPool.getSoldTickets().size());
            System.out.println("Total Tickets: "+ ticketPool.getTotalTickets().size());
            System.out.println("+".repeat(40));

            //logging
            Log.logInfo("+".repeat(40));
            Log.logInfo("Available Tickets: "+ticketPool.getAvailableTickets().size());
            Log.logInfo("Sold Tickets: "+ ticketPool.getSoldTickets().size());
            Log.logInfo("Total Tickets: "+ ticketPool.getTotalTickets().size());
            Log.logInfo("+".repeat(40));
        }
    }
}