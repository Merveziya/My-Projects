import javafx.scene.control.Label;

public interface IReservable
{
    void makeReservation(String tripNumber, Label day, int indexOfBus, Label nameLabel, Vehicle reservedVehicle);
}