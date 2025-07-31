package airline;

import java.util.*;

public class SeatMap {
    // Define seat maps for First, Business, and Traveller classes
	public Map<Integer, String[]> firstClassSeatMap = new HashMap<>();
    public Map<Integer, String[]> businessClassSeatMap = new HashMap<>();
    public Map<Integer, String[]> travellerClassSeatMap = new HashMap<>();

    // Constructor to initialize seat maps
    public SeatMap() {
        initializeFirstClassSeatMap();
        initializeBusinessClassSeatMap();
        initializeTravellerClassSeatMap();
    }

    // Initialize seat map for First Class
    public void initializeFirstClassSeatMap() {
        for (int row = 1; row <= 3; row++) {
            String[] seatRow = {"Window", "Aisle", "Aisle", "Window"};
            firstClassSeatMap.put(row, seatRow);
        }
    }

    // Initialize seat map for Business Class
    public void initializeBusinessClassSeatMap() {
        for (int row = 4; row <= 18; row++) {
            String[] seatRow = {"Window", "Standard", "Aisle", "Aisle", "Standard", "Window"};
            businessClassSeatMap.put(row, seatRow);
        }
    }

    // Initialize seat map for Traveller Class
    public void initializeTravellerClassSeatMap() {
        for (int row = 19; row <= 37; row++) {
            String[] seatRow = {"Window", "Standard", "Standard", "Aisle", "Aisle", "Standard", "Standard", "Window"};
            travellerClassSeatMap.put(row, seatRow);
        }
    }

    
 // Mark a seat as reserved with "X" and display the updated seat map
    public void markReservedSeat(String className, int seatNumber, String seatType) {
        Map<Integer, String[]> seatMap = null;

        if (className.equals("First")) {
            seatMap = firstClassSeatMap;
        } else if (className.equals("Business")) {
            seatMap = businessClassSeatMap;
        } else if (className.equals("Traveller")) {
            seatMap = travellerClassSeatMap;
        }

        if (seatMap != null) {
            int rowIndex = calculateRowIndex(seatNumber);
            int columnIndex = calculateColumnIndex(seatType);

            if (seatMap.containsKey(rowIndex)) {
                String[] row = seatMap.get(rowIndex);
                if (columnIndex >= 0 && columnIndex < row.length) {
                    row[columnIndex] = "X";
                }
            }

            displaySeatMap(className);
        } else {
            System.out.println("Invalid class name");
        }
    }

    // Calculate the row index based on the seat number
    private int calculateRowIndex(int seatNumber) {
        // Implement your logic here to map seatNumber to rowIndex
        return seatNumber;
    }

    // Calculate the column index based on the seat type
    private int calculateColumnIndex(String seatType) {
        // Implement your logic here to map seatType to columnIndex
        return 0;
    }
    
    
     
    // Display seat map for a specific class
    public void displaySeatMap(String className) {
        Map<Integer, String[]> seatMap = null;
        String[] columns = null;
        if (className.equals("First")) {
            seatMap = firstClassSeatMap;
            columns = new String[]{"A", "D", "E", "H"};
        } else if (className.equals("Business")) {
            seatMap = businessClassSeatMap;
            columns = new String[]{"A", "C", "D", "E", "G", "H"};
        } else if (className.equals("Traveller")) {
            seatMap = travellerClassSeatMap;
            columns = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        }

        if (seatMap != null && columns != null) {
            // Extract and sort seat numbers
            List<Integer> seatNumbers = new ArrayList<>(seatMap.keySet());
            Collections.sort(seatNumbers);

            // Display column labels
            System.out.print("   ");
            for (String column : columns) {
                System.out.printf("%-10s", column);
            }
            System.out.println();

            // Display seat map in ascending seat number order
            for (Integer seatNumber : seatNumbers) {
                String[] seatRow = seatMap.get(seatNumber);

                System.out.printf("%-2d ", seatNumber);
                for (String seat : seatRow) {
                    System.out.printf("%-10s", seat);
                }
                System.out.println();
            }
        } else {
            System.out.println("Invalid class name");
        }
    }
    
    // Mark a seat as reserved
    public void markReservedSeat(String className, String seatTypeOrNumber) {
        Map<Integer, String[]> seatMap = null;
        String[] columns = null;

        if (className.equals("First")) {
            seatMap = firstClassSeatMap;
            columns = new String[]{"A", "D", "E", "H"};
        } else if (className.equals("Business")) {
            seatMap = businessClassSeatMap;
            columns = new String[]{"A", "C", "D", "E", "G", "H"};
        } else if (className.equals("Traveller")) {
            seatMap = travellerClassSeatMap;
            columns = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        }

        if (seatMap != null && columns != null) {
            // Extract seatNumber and seatType from seatTypeOrNumber
            String seatNumber = seatTypeOrNumber.substring(0, seatTypeOrNumber.length() - 1);
            String seatType = seatTypeOrNumber.substring(seatTypeOrNumber.length() - 1);

            // Calculate the row index and column index
            int rowIndex = Integer.parseInt(seatNumber) - 1;
            int columnIndex = Arrays.asList(columns).indexOf(seatType);

            // Update the seat map to mark the seat as reserved with "X"
            String[] seatRow = seatMap.get(rowIndex + 1);
            seatRow[columnIndex] = "X";
        }
    }

    // Mark a seat as cancelled
    public void markCancelledSeat(String seatReserved) {
        String[] seatInfo = seatReserved.split("");
        int rowIndex = Integer.parseInt(seatInfo[0]) - 1;
        String seatType = seatInfo[1];

        Map<Integer, String[]> seatMap = getSeatMapBySeatType(seatType);

        if (seatMap != null) {
            String[] seatRow = seatMap.get(rowIndex + 1);
            int columnIndex = Arrays.asList(seatRow).indexOf("X");

            if (columnIndex != -1) {
                seatRow[columnIndex] = seatType;
            }
        }
    }

    // Get the seat map based on seat type
    private Map<Integer, String[]> getSeatMapBySeatType(String seatType) {
        if (seatType.equals("A") || seatType.equals("D") || seatType.equals("E") || seatType.equals("H")) {
            return firstClassSeatMap;
        } else if (seatType.equals("A") || seatType.equals("C") || seatType.equals("D") || seatType.equals("E") || seatType.equals("G") || seatType.equals("H")) {
            return businessClassSeatMap;
        } else {
            return null;
        }
    }

    
    
    public static void main(String[] args) {
        // You can add your code here to test the SeatMap class
        // For example:
        SeatMap seatMap = new SeatMap();
        System.out.println("First Class Seat Map:");
        seatMap.displaySeatMap("First");

        System.out.println("\nBusiness Class Seat Map:");
        seatMap.displaySeatMap("Business");

        System.out.println("\nTraveller Class Seat Map:");
        seatMap.displaySeatMap("Traveller");
    }
   
}
