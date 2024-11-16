//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //creating an object from ticketpool class
        TicketPool ticketPool = new TicketPool();

        String userOption = "";
        while (!userOption.equals("yes".toLowerCase())) {
            System.out.println("*".repeat(43) + "\nWelcome to the Ticketing Management System\n" + "*".repeat(43));

            System.out.print("Are you customer or vendor (customer/ vendor)? ");
            String user = input.next().toLowerCase();

            //if vendor is using the system
            if (user.equals("vendor".toLowerCase())) {

                boolean vendorExit = false;
                while (!vendorExit) {
                    System.out.println("\nVendor Menu:");
                    System.out.println("1. Add Tickets");
                    System.out.println("2. View Available Tickets");
                    System.out.println("3. View Sold Tickets");
                    System.out.println("4. Exit");
                    System.out.print("Choose an option (1, 2, 3, 4): ");
                    int vendorChoice = 0;
                    try {
                        vendorChoice = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        input.nextLine(); // Clear the buffer
                        continue;
                    }

                    switch (vendorChoice) {
                        case 1:
                            // Add tickets
                            System.out.print("\nEnter Your name: ");
                            String vendorName = input.next();
                            System.out.print("Enter Your ID: ");
                            String ID = input.next();

                            System.out.print("Set the maximum ticket capacity: ");
                            int maxCapacity = 0;
                            try {
                                maxCapacity = input.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input for capacity. Please enter a valid number.");
                                input.nextLine(); // Clear the buffer
                                continue;
                            }

                            System.out.print("Number of tickets you want to add: ");
                            int ticketCount = 0;
                            try {
                                ticketCount = input.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input for ticket count. Please enter a valid number.");
                                input.nextLine(); // Clear the buffer
                                continue;
                            }

                            ticketPool.setMaxCapacity(maxCapacity);
                            Vendor vendor = new Vendor(vendorName, ID, ticketPool, ticketCount);

                            // Start vendor thread
                            Thread threadVendor = new Thread(vendor);
                            threadVendor.start();

                            // Wait for vendor thread to finish before showing available tickets
                            try {
                                threadVendor.join();  // Ensure vendor thread completes first
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;

                        case 2:
                            // View available tickets
                            System.out.println("Available Tickets: " + ticketPool.getAvailableTickets().size());
                            break;

                        case 3:
                            // View sold tickets
                            System.out.println("Sold Tickets: " + ticketPool.getSoldTickets().size());
                            break;
                        case 4:
                            // Exit Vendor Loop
                            vendorExit = true;
                            break;


                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                }

            } else if (user.equals("customer".toLowerCase())) {

                boolean customerExit = false;
                while (!customerExit) {

                    System.out.println("\nCustomer Menu:");
                    System.out.println("1. View Available Tickets");
                    System.out.println("2. Buy Tickets");
                    System.out.println("3. Exit");
                    System.out.print("Choose an option (1, 2, 3): ");
                    int customerChoice = input.nextInt();

                    switch (customerChoice) {
                        case 1:
                            // View available tickets
                            System.out.println("Available Tickets: " + ticketPool.getAvailableTickets().size());
                            break;

                        case 2:
                            // Proceed to buy tickets
                            System.out.print("\nEnter your name: ");
                            String customerName = input.next();

                            System.out.print("Number of tickets you want to purchase: ");
                            int noOfTickets = input.nextInt();

                            // Creating an object from customer class
                            Customer customer = new Customer(customerName, ticketPool, noOfTickets);

                            // Creating a thread for customer
                            Thread threadCustomer = new Thread(customer);

                            threadCustomer.start();

                            // Wait for customer thread to complete
                            try {
                                threadCustomer.join();  // Ensure customer thread completes
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;

                        case 3:
                            // Exit Customer Loop
                            customerExit = true;
                            break;


                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                }
            }

            System.out.print("Do You Want To Exit? (yes/no)");
            userOption = input.next();

            if (!userOption.equalsIgnoreCase("yes") && !userOption.equalsIgnoreCase("no")) {
                System.out.println("WRONG INPUT. Please enter 'yes' to exit or 'no' to continue.");
            }
        }

    }
}