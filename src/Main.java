//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("*".repeat(43) + "\nWelcome to the Ticketing Management System\n" + "*".repeat(43));

        Scanner input = new Scanner(System.in);
        System.out.print("Are you customer or vendor (customer/ vendor)? ");
        String user = input.next().toLowerCase();

        //if vendor is using the system
        if (user.equals("vendor".toLowerCase())){

            System.out.println("Please continue to add tickets");

            System.out.print("Enter Your name: ");
            String name = input.next();
            System.out.print("Enter Your ID: ");
            String ID = input.next();
            System.out.print("set the maximum ticket capacity: ");
            int maxCapacity = input.nextInt();
            System.out.print("No of tickets you want add: ");
            int ticketCount = input.nextInt();


            //creating an object from ticketpool class
            TicketPool ticketPool = new TicketPool(maxCapacity);
            //creating an object from vendor class
            Vendor vendor = new Vendor(name,ID,ticketPool,ticketCount);

            //creating a thread for vendor
            Thread threadVendor = new Thread(vendor);

            threadVendor.start();


        }



    }
}