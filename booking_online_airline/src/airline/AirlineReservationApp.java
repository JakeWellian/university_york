package airline;

import java.util.*;
import airline.Passenger;

public class AirlineReservationApp {
	public static void main(String[] args) {
        SeatMap seatMap = new SeatMap();
        Scanner scanner = new Scanner(System.in);
        char choice;
        List<Passenger> passengers = new ArrayList<>();

        do {
        	System.out.print("\nDo you wish to reserve another seat? You are allowed to reserve 3 seats. Select Yes (Y) or No (N): ");
            choice = scanner.nextLine().charAt(0);
            
            if (choice == 'N' || choice == 'n') {
                // Exit the loop when the user enters 'N' or 'n'
                break;
            }
            
            // Check if the user wants to reserve multiple seats
            if (choice == 'Y' || choice == 'y') {
            	
            	// Ask the user for the number of seats required together
                int numSeats;
            	
                // Add the code for reserving multiple seats here
                do {
                    System.out.print("Enter the number of seats required together (up to 3): ");
                    numSeats = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character

                    if (numSeats < 1 || numSeats > 3) {
                        System.out.println("Sorry, you can reserve up to 3 seats together.");
                    }
                } while (numSeats < 1 || numSeats > 3);

                if (!canReserveSeats(numSeats)) {
                    System.out.println("Sorry, you can reserve up to 3 seats together.");
                } else {
                    List<Passenger> passengerDetailsList = new ArrayList<>();

                    for (int seatNumber = 1; seatNumber <= numSeats; seatNumber++) {
                        // Prompt the user and make reservations for each seat in the loop
                        System.out.println("Reserving seat " + seatNumber + " of " + numSeats);
                        boolean isValidSeat = false;
                        
                        // Rest of the seat reservation code (class choice, passenger details) goes here
                        System.out.print("Enter the choice of class (Business, Traveller, First) for seat " + seatNumber + ": ");
                        String passengerClass = scanner.nextLine();
                        
                        int minSeat;
                        int maxSeat;
                        String seatTypeInstructions;
                        

                        if (passengerClass.equalsIgnoreCase("Business")) {
                            minSeat = 4;
                            maxSeat = 18;
                            seatTypeInstructions = "A & H = Window. C & G = Standard. D & E = Aisle";
                        } else if (passengerClass.equalsIgnoreCase("First")) {
                            minSeat = 1;
                            maxSeat = 3;
                            seatTypeInstructions = "A & H = Window. D & E = Aisle";
                        } else if (passengerClass.equalsIgnoreCase("Traveller")) {
                            minSeat = 19;
                            maxSeat = 37;
                            seatTypeInstructions = "A & H = Window. B, C, F & G = Standard. D & E = Aisle";
                        } else {
                            System.out.println("Invalid class choice. Please choose from Business, Traveller, or First.");
                            continue; // Restart the loop to re-enter class choice
                        }

                        System.out.println("Enter a seat number from " + minSeat + " - " + maxSeat + " and your seat type preference. For example, " + minSeat + "A. " + seatTypeInstructions);


                        System.out.print("Enter the choice of seat type (window, standard, aisle) or seat number for seat " + seatNumber + ": ");
                        String seatTypeOrNumber = scanner.nextLine();
                        
                        // Validate seat number based on class choice
                        int seatNumberInt;
                        try {
                            seatNumberInt = Integer.parseInt(seatTypeOrNumber);
                            if (seatNumberInt < minSeat || seatNumberInt > maxSeat) {
                                System.out.println("Invalid seat number for the selected class.");
                                continue; // Restart the loop to re-enter seat number
                            }
                        } catch (NumberFormatException e) {
                            // Handle non-integer input (e.g., "window" or "aisle")
                            // You can add additional validation for seat type here if needed
                        }

                        
                        System.out.print("Enter the passenger's first name for seat " + seatNumber + ": ");
                        String firstName = scanner.nextLine();

                        System.out.print("Enter the passenger's last name for seat " + seatNumber + ": ");
                        String lastName = scanner.nextLine();

                        System.out.print("Enter the passport number for seat " + seatNumber + ": ");
                        String passportNumber = scanner.nextLine();

                        // Create a new Passenger object with the provided details
                        Passenger passenger = new Passenger(firstName, lastName, passportNumber, seatTypeOrNumber, passengerClass, seatTypeOrNumber);

                        // After a passenger reserves a seat, mark it as "X" in the seat map
                        seatMap.markReservedSeat(passengerClass, passenger.getSeatReserved());

                        // Add the passenger to the list
                        passengerDetailsList.add(passenger);
                    }

                    // Display reservation details for multiple passengers
                    System.out.println("Reservation details for " + numSeats + " passengers:");
                    for (Passenger passengerDetails : passengerDetailsList) {
                        // Display passenger details
                    	System.out.println("First Name: " + passengerDetails.getFirstName());
                        System.out.println("Last Name: " + passengerDetails.getLastName());
                        System.out.println("Passport Number: " + passengerDetails.getPassportNumber());
                        System.out.println("Seat Reserved: " + passengerDetails.getSeatReserved());
                        System.out.println("Passenger Class: " + passengerDetails.getPassengerClass());
                        System.out.println("Seat Type: " + passengerDetails.getSeatType());
                        System.out.println("------------------------");
                    	
                    
                    }
                    // Create an instance of the Report class with passenger data
                    Report report = new Report(passengerDetailsList);
                    
                    // Generate and display the cost report
                    System.out.println("Summary Cost Report:");
                    report.generateCostReport();
                    System.out.println("------------------------");
                    
                    // Generate and display the detailed passenger report
                    

                    System.out.println("Summary Passenger Report:");
                    report.generatePassengerReport();
                    System.out.println("-------------------------");
                    
                }                 
            }
            
            // Add the code for passenger search here
            System.out.print("Enter the passenger's last name to search: ");
            String searchLastName = scanner.nextLine();

            List<Passenger> foundPassengers = searchPassengersByLastName(passengers, searchLastName);

            if (!foundPassengers.isEmpty()) {
                System.out.println("Found passengers with last name " + searchLastName + ":");
                for (Passenger foundPassenger : foundPassengers) {
                    System.out.println("First name: " + foundPassenger.getFirstName());
                    System.out.println("Last name: " + foundPassenger.getLastName());
                    System.out.println("Passport number: " + foundPassenger.getPassportNumber());
                    System.out.println("Seat reserved: " + foundPassenger.getSeatReserved());
                    System.out.println("Class: " + foundPassenger.getPassengerClass());
                    System.out.println("Type: " + foundPassenger.getSeatType());
                    System.out.println("------------------------");
                }
            } else {
                System.out.println("No passengers found with last name " + searchLastName);
            }
            
         // Display the passenger information in a tabular form
            System.out.println("Passenger Details Table:");
            System.out.println("-------------------------------------------------------------");
            System.out.println("| First Name      | Last Name       | Passport Number | Seat Reserved   | Passenger Class | Seat Type       |");
            System.out.println("-------------------------------------------------------------");

            for (Passenger passenger : passengers) {
                // Access passenger details using getter methods
                System.out.printf("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |%n",
                        passenger.getFirstName(),
                        passenger.getLastName(),
                        passenger.getPassportNumber(),
                        passenger.getSeatReserved(),
                        passenger.getPassengerClass(),
                        passenger.getSeatType());
            }

            System.out.println("-------------------------------------------------------------");

            // Ask if the passenger wants to cancel their reservation
            System.out.print("\nDo you want to cancel your reservation? Select Yes (Y) or No (N): ");
            char cancelChoice = scanner.nextLine().charAt(0);

            if (cancelChoice == 'Y' || cancelChoice == 'y') {
                System.out.print("Enter your passport number to confirm cancellation: ");
                String passportNumber = scanner.nextLine();

                Passenger foundPassenger = searchPassengerByPassport(passengers, passportNumber);

                if (foundPassenger != null) {
                    String seatReserved = foundPassenger.getSeatReserved();
                    seatMap.markCancelledSeat(seatReserved);
                    passengers.remove(foundPassenger);
                    System.out.println("Your reservation for seat " + seatReserved + " has been canceled.");
                } else {
                    System.out.println("Passport number not found. Cancellation failed.");
                }
            } else if (cancelChoice == 'N' || cancelChoice == 'n') {
                System.out.println("Happy flight!");
            } else {
                System.out.println("Invalid choice. Cancellation failed.");
            }
            // Get user input for seating map choice
            System.out.print("Enter the choice of seating map (Business, Traveller, First) to view: ");
            String className = scanner.nextLine();

            // Display seating map
            System.out.println("Seating Map for " + className + " Class:");
            seatMap.displaySeatMap(className);
            
        } while (choice == 'Y' || choice == 'y');
        

        System.out.println("Thank you for using the Airline Reservation App!");
    }

    // Implement the searchPassengerByPassport method as described in the pseudo code
    private static Passenger searchPassengerByPassport(List<Passenger> passengerList, String passportNumber) {
        for (Passenger passenger : passengerList) {
            if (passenger.getPassportNumber().equals(passportNumber)) {
                return passenger;
            }
        }
        return null;
    }
    
    // Find Passenger by name
    private static List<Passenger> searchPassengersByLastName(List<Passenger> passengerList, String lastName) {
        List<Passenger> foundPassengers = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            if (passenger.getLastName().equalsIgnoreCase(lastName)) {
                foundPassengers.add(passenger);
            }
        }

        return foundPassengers;
    	}
    
    // limit reservation to 3 people per group
    private static boolean canReserveSeats(int numSeats) {
        return numSeats <= 3;
    }
}

