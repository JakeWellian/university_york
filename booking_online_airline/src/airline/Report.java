package airline;

import java.util.*;

public class Report {
    private List<Passenger> passengerData;

    // Constructor to initialize the passenger data
    public Report(List<Passenger> passengerData) {
        this.passengerData = passengerData;
    }

    // Method to generate the cost report
    public void generateCostReport() {
        // Initialize variables to count passengers and calculate the total cost
        int firstClassCount = 0;
        int businessClassCount = 0;
        int travellerClassCount = 0;
        int totalCost = 0;

        // Iterate through the passenger data
        for (Passenger passenger : passengerData) {
            String passengerClass = passenger.getPassengerClass();

            if (passengerClass.equalsIgnoreCase("First")) {
                firstClassCount++;
                totalCost += 1800;
            } else if (passengerClass.equalsIgnoreCase("Business")) {
                businessClassCount++;
                totalCost += 1200;
            } else if (passengerClass.equalsIgnoreCase("Traveller")) {
                travellerClassCount++;
                totalCost += 1000;
            }
        }

        // Print the report header
        System.out.println("Class      Count      Cost");

        // Print the report rows
        System.out.println("First      " + firstClassCount + "         1800");
        System.out.println("Business   " + businessClassCount + "         1200");
        System.out.println("Traveller  " + travellerClassCount + "         1000");
        System.out.println("Total Cost: " + totalCost);
    }
    
    // Method to generate the detailed passenger report
    public void generatePassengerReport() {
        // Print the header for the detailed report
    	System.out.println("Summary Passenger Report:");
        System.out.println("-------------------------------------------------------------");
        System.out.println("| First Name  | Last Name   | Passport Number | Seat Reserved | Class  | Seat Type |");
        System.out.println("-------------------------------------------------------------");

        // Iterate through the passenger data and print passenger details in columns
        for (Passenger passenger : passengerData) {
        	System.out.printf("| %-12s | %-11s | %-15s | %-13s | %-6s | %-9s |%n",
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getPassportNumber(),
                passenger.getSeatReserved(),
                passenger.getPassengerClass(),
                passenger.getSeatType()
            );
        }
    }
}

