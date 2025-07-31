import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import java.util.*;


public class Quarterly_Report extends JFrame {

    private Object[][] data = {
            {"April", 67000, 65000, 63000, 18000, 16000},
            {"May", 63000, 67000, 63000, 24000, 23000},
            {"June", 78000, 56000, 65000, 22000, 21000},
            {"July", 78000, 45000, 71000, 19000, 19000},
            {"August", 104000, 56000, 73000, 17000, 20000},
            {"September", 103000, 72000, 69000, 16000, 19000},
            };

    private String[] columnNames = {"Month", "Electrical", "Kitchen", "Bathroom", "Soft Furnishing", "Accessories"};

    public Quarterly_Report() {
        initializeUI();
    }
 	
    private void initializeUI() {
        // Set up the frame
        setTitle("Quarterly Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the table using data and column names
        JTable table = new JTable(data, columnNames);

        // Wrap the table in a scroll pane to handle large data sets
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        // Set the size of the frame
        setSize(600, 300);
     
        // Calculate the sum per department for Q2 ("April," "May," and "June")
        int[] Q2 = calculateSumPerDeptartment(new String[]{"April", "May", "June"});

        // Calculate the sum per department for Q3 ("July," "August," and "September")
        int[] Q3 = calculateSumPerDeptartment(new String[]{"July", "August", "September"});

        // Print the sums for Q2
        System.out.println("Sum of Sales per Department for Q2 (April, May, and June):");
        printColumnSums(columnNames, Q2);

        // Print the sums for Q3
        System.out.println("\nSum of Sales per Department for Q3 (July, August, and September):");
        printColumnSums(columnNames, Q3);

        // Find the best and worst performing department per quarter with its respective monthly sales
        findBestAndWorstPerformingDepartmentPerQuarter(Q2, Q3);

        // Calculate the 17% tax that needs to be paid for each quarter
        calculateTaxPerQuarter();
    }

    private int[] calculateSumPerDeptartment(String[] targetMonths) {
        int[] columnSums = new int[columnNames.length - 1];
        for (int row = 0; row < data.length; row++) {
            String month = (String) data[row][0];
            if (isTargetMonth(month, targetMonths)) {
                for (int col = 1; col < data[row].length; col++) { 
                    columnSums[col - 1] += (int) data[row][col];
                }
            }
        }
        return columnSums;
    }

    private boolean isTargetMonth(String month, String[] targetMonths) {
        for (String target : targetMonths) {
            if (month.equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    private void printColumnSums(String[] columnNames, int[] columnSums) {
        for (int i = 1; i < columnNames.length; i++) {
            System.out.println(columnNames[i] + ": " + columnSums[i - 1]);
        }
    }

    private void findBestAndWorstPerformingDepartmentPerQuarter(int[] Q2, int[] Q3) {
        int[][] salesPerDepartmentPerQuarter = calculateSalesPerDepartmentPerQuarter();

        // For Q2
        int bestSalesQ2 = 0;
        int worstSalesQ2 = Integer.MAX_VALUE;
        String bestDepartmentQ2 = "";
        String worstDepartmentQ2 = "";

        // For Q3
        int bestSalesQ3 = 0;
        int worstSalesQ3 = Integer.MAX_VALUE;
        String bestDepartmentQ3 = "";
        String worstDepartmentQ3 = "";

        for (int i = 0; i < columnNames.length - 1; i++) {
            // Q2
            if (salesPerDepartmentPerQuarter[i][0] > bestSalesQ2) {
                bestSalesQ2 = salesPerDepartmentPerQuarter[i][0];
                bestDepartmentQ2 = columnNames[i + 1];
            }
            if (salesPerDepartmentPerQuarter[i][0] < worstSalesQ2) {
                worstSalesQ2 = salesPerDepartmentPerQuarter[i][0];
                worstDepartmentQ2 = columnNames[i + 1];
            }

            // Q3
            if (salesPerDepartmentPerQuarter[i][1] > bestSalesQ3) {
                bestSalesQ3 = salesPerDepartmentPerQuarter[i][1];
                bestDepartmentQ3 = columnNames[i + 1];
            }
            if (salesPerDepartmentPerQuarter[i][1] < worstSalesQ3) {
                worstSalesQ3 = salesPerDepartmentPerQuarter[i][1];
                worstDepartmentQ3 = columnNames[i + 1];
            }
        }

        // Print the results for Q2
        System.out.println("\nBest Performing Department in Q2: " + bestDepartmentQ2 + " with sales: " + bestSalesQ2);
        printSalesPerMonth("Q2", new String[]{"April", "May", "June"}, bestDepartmentQ2);

        System.out.println("\nWorst Performing Department in Q2: " + worstDepartmentQ2 + " with sales: " + worstSalesQ2);
        printSalesPerMonth("Q2", new String[]{"April", "May", "June"}, worstDepartmentQ2);

        // Print the results for Q3
        System.out.println("\nBest Performing Department in Q3: " + bestDepartmentQ3 + " with sales: " + bestSalesQ3);
        printSalesPerMonth("Q3", new String[]{"July", "August", "September"}, bestDepartmentQ3);

        System.out.println("\nWorst Performing Department in Q3: " + worstDepartmentQ3 + " with sales: " + worstSalesQ3);
        printSalesPerMonth("Q3", new String[]{"July", "August", "September"}, worstDepartmentQ3);
    }

    private int[][] calculateSalesPerDepartmentPerQuarter() {
        int[][] salesPerDepartmentPerQuarter = new int[columnNames.length - 1][2]; // 2 quarters

        for (int i = 0; i < columnNames.length - 1; i++) {
            int salesQ2 = 0;
            int salesQ3 = 0;
            for (int row = 0; row < data.length; row++) {
                String month = (String) data[row][0];
                int sales = (int) data[row][i + 1];
                if (month.equals("April") || month.equals("May") || month.equals("June")) {
                    salesQ2 += sales;
                } else if (month.equals("July") || month.equals("August") || month.equals("September")) {
                    salesQ3 += sales;
                }
            }
            salesPerDepartmentPerQuarter[i][0] = salesQ2;
            salesPerDepartmentPerQuarter[i][1] = salesQ3;
        }

        return salesPerDepartmentPerQuarter;
    }

    private void printSalesPerMonth(String quarter, String[] targetMonths, String department) {
        for (int row = 0; row < data.length; row++) {
            String month = (String) data[row][0];
            if (isTargetMonth(month, targetMonths)) {
                int sales = (int) data[row][findIndex(columnNames, department) + 1];
                System.out.println(month + ": " + sales);
            }
        }
    }

    private int findIndex(String[] columnNames, String department) {
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(department)) {
                return i - 1;
            }
        }
        return -1;
    }

    private void calculateTaxPerQuarter() {
        int[] totalSalesQ2 = calculateSumPerDeptartment(new String[]{"April", "May", "June"});
        int[] totalSalesQ3 = calculateSumPerDeptartment(new String[]{"July", "August", "September"});

        double taxRate = 0.17;

        double taxQ2 = (totalSalesQ2[0] + totalSalesQ2[1] + totalSalesQ2[2] + totalSalesQ2[3] + totalSalesQ2[4]) * taxRate;
        double taxQ3 = (totalSalesQ3[0] + totalSalesQ3[1] + totalSalesQ3[2] + totalSalesQ3[3] + totalSalesQ3[4]) * taxRate;

        System.out.println("\nTax to be paid for Q2: " + Math.round(taxQ2));
        System.out.println("Tax to be paid for Q3: " +  Math.round(taxQ3));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Quarterly_Report report = new Quarterly_Report();
            report.setVisible(true);
        });
    }
}
