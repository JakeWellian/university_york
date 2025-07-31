package airline;

public class Passenger {
    // Fields
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String seatReserved;
    private String passengerClass; // First, Business, or Traveller
    private String seatType;       // Aisle, Standard, or Window

    // Constructor
    public Passenger(String firstName, String lastName, String passportNumber, String seatReserved, String passengerClass, String seatType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.seatReserved = seatReserved;
        this.passengerClass = passengerClass;
        this.seatType = seatType;
    }

    // Getter methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getSeatReserved() {
        return seatReserved;
    }

    public String getPassengerClass() {
        return passengerClass;
    }

    public String getSeatType() {
        return seatType;
    }

    // Setter methods
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setSeatReserved(String seatReserved) {
        this.seatReserved = seatReserved;
    }

    public void setPassengerClass(String passengerClass) {
        this.passengerClass = passengerClass;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
}
