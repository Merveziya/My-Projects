import java.util.Formatter;
import java.util.List;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import org.w3c.dom.Text;

public abstract class Vehicle
{
    private String vehicleId;
    private String type;
    private int capacity;
    Button[] vehicleWholeSeats;
    Passenger[] passengers;
    private int availableSeatCount;
    private List<Integer> reservedSeats;
    private List<Integer> chosenSeatsList;
    private List<String> seatsColor;

    public Vehicle(String vehicleId,String type, int capacity)
    {
        this.vehicleId = vehicleId;
        this.type = type;
        this.capacity = capacity;
        availableSeatCount = capacity;
        vehicleWholeSeats = new Button[capacity];
        passengers = new Passenger[capacity];
        reservedSeats = new ArrayList<>();
        chosenSeatsList = new ArrayList<>();
        seatsColor = new ArrayList<>();

        for (int i = 0; i < vehicleWholeSeats.length; i++)
        {
            vehicleWholeSeats[i] = new Button(Integer.toString(i+1));
            vehicleWholeSeats[i].setStyle("-fx-background-color: rgb(0, 125, 0)");
        }
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    void decreaseAvailableSeatCount(int choosenSeat)
    {
        availableSeatCount -= choosenSeat;
    }

    void increaseVehicleCapacity(int choosetSeat)
    {
        availableSeatCount += choosetSeat;
    }

    public int getAvailableSeats() {
        return capacity - reservedSeats.size();
    }

    public String getAvaliableSeatAndCapacityRate()
    {
        return Integer.toString(getAvailableSeats()) + "/" + Integer.toString(capacity);
    }

    void showSeatsOnScreen(Pane pane, TextField chosenSeatsField, List<Integer> chosenSeats)
    {
        chosenSeatsField.setText("");
        int seatCounter = 0;
        int xPosition = 65;
        int yPosition = 100;

        for (var seat : vehicleWholeSeats)
        {
            if (seat.getStyle().equals("-fx-background-color: rgb(125, 125, 0) "))
            {
                seat.setStyle("-fx-background-color: rgb(0, 125, 0) ");
            }
        }

        for(int i = 0; i < capacity; i++)
        {
            vehicleWholeSeats[i].setTranslateX(xPosition);
            vehicleWholeSeats[i].setTranslateY(yPosition);
            seatCounter++;
            yPosition += 30;

            if (seatCounter % 4 == 0)
            {
                xPosition += 35;
                yPosition = 100;
            }
        }

        for (var seat : vehicleWholeSeats)
        {
            pane.getChildren().add(seat);
        }

        for (var vehicleSeat : vehicleWholeSeats)
        {
            vehicleSeat.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    if (!((Button)actionEvent.getSource()).getStyle().equals("-fx-background-color: rgb(125, 125, 0) "))
                    {
                        chosenSeats.add(Integer.parseInt(vehicleSeat.getText().trim()));
                        chosenSeatsList.add(Integer.parseInt(vehicleSeat.getText().trim()));
                        System.out.println(chosenSeats.size());

                        String seatNumber = vehicleSeat.getText().trim();
                        String chosenSeatsNumbers;

                        if (chosenSeatsField.getText().isEmpty()) {
                            chosenSeatsNumbers = seatNumber;
                        } else {
                            chosenSeatsNumbers = chosenSeatsField.getText() + "-" + seatNumber;
                        }
                        chosenSeatsField.setText(chosenSeatsNumbers);
                        ((Button)actionEvent.getSource()).setStyle("-fx-background-color: rgb(125, 125, 0) ");
                    }

                    else if (((Button)actionEvent.getSource()).getStyle().equals("-fx-background-color: rgb(125, 125, 0) "))
                    {
                        ((Button)actionEvent.getSource()).setStyle("-fx-background-color: rgb(0, 125, 0)");
                        chosenSeatsField.setText("");
                        int currentSeat = Integer.parseInt(vehicleSeat.getText().trim());

                        for (int i = 0; i < chosenSeats.size(); i++)
                        {
                            if (chosenSeats.get(i).equals(currentSeat))
                            {
                                chosenSeats.remove(i);
                                chosenSeatsList.remove(i);
                                break;
                            }
                        }

                        for (var chosenSeat : chosenSeats)
                        {
                            String chosenSeatsNumbers = chosenSeatsField.getText() + "-" + chosenSeat;
                            chosenSeatsField.setText(chosenSeatsNumbers);
                        }

                        System.out.println(chosenSeats.size());
                    }
                }
            });
        }
    }

    public void lockReservedSeats()
    {
        for (var seat : vehicleWholeSeats)
        {
            for (var chosenSeat: chosenSeatsList)
            {
                if (seat.getText().trim().equals(Integer.toString(chosenSeat)))
                {
                    reservedSeats.add(Integer.parseInt(seat.getText().trim()));
                    seat.setStyle("-fx-background-color: rgb(200, 0, 0)");
                    seat.setDisable(true);
                }
            }
        }
    }

    public boolean reserveTicket(int seatNumber) {
        if (!reservedSeats.contains(seatNumber) && seatNumber > 0 && seatNumber <= capacity) {
            reservedSeats.add(seatNumber);
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Seat already reserved! Try another one.");
            alert.showAndWait();

            return false;
        }
    }

    public static void main(String[] args) {}
}