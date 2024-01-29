import java.util.ArrayList;
import java.util.List;

public class Reservation
{
    private List<Passenger> passengers;
    private Vehicle vehicle;
    private List<Integer> seatNumbers;

    public Reservation(Vehicle vehicle)
    {
        this.vehicle = vehicle;
        seatNumbers = new ArrayList<Integer>();
        passengers = new ArrayList<>();
    }

    public List<Passenger> getPassengerslist() { return passengers; }
    public Vehicle getVehicle() { return vehicle; }
    public List<Integer> getSeatNumberslist() { return seatNumbers; }

    public static void main(String[] args) {}
}