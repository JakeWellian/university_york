import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AirlineSeatsGUI {
    private JFrame frame;
    private JPanel panel;
    private Map<String, String[]> seatMap;

    public AirlineSeatsGUI(Map<String, String[]> seatMap) {
        this.seatMap = seatMap;
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Airline Seat Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        panel = new JPanel(new GridLayout(seatMap.size() + 1, seatMap.get("01").length + 1));
        frame.add(panel);

        // Create column labels
        panel.add(new JLabel("Seat"));
        for (String column : seatMap.get("01")) {
            panel.add(new JLabel(column));
        }

        // Display seat map
        for (Map.Entry<String, String[]> entry : seatMap.entrySet()) {
            String seatNumber = entry.getKey();
            String[] seatRow = entry.getValue();

            panel.add(new JLabel(seatNumber));
            for (String seat : seatRow) {
                panel.add(new JLabel(seat));
            }
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Map<String, String[]> firstClassSeatMap = new HashMap<>();
        // Initialize firstClassSeatMap (similarly, initialize other seat maps)

        SwingUtilities.invokeLater(() -> {
            new AirlineSeatsGUI(firstClassSeatMap); // Pass the seat map you want to display
        });
    }
}

