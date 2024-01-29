import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Search
{

    private Button searchTicketButton;

    Search()
    {
        searchTicketButton = new Button("SEARCH TICKET");
        searchTicketButton.setStyle("-fx-background-color: #4CAF50;  -fx-border-color: black; -fx-border-width: 0.1mm;");
        searchTicketButton.getStyleClass().add("custom-button");
        searchTicketButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        searchTicketButton.setPrefWidth(400);
        searchTicketButton.setPrefHeight(40);
    }

    public void searchStage(Label[] daysLabel, Transport transport)
    {
        Label dateLabel = new Label("Date (d/m)");
        String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 2px; -fx-padding: 5px;";
        dateLabel.setStyle(commonStyle);
        dateLabel.setTranslateX(30);
        dateLabel.setTranslateY(10);

        Button[] showTripsButton = { new Button("Show Trips"),
                new Button("Show Trips"),
                new Button("Show Trips"),
                new Button("Show Trips"),
                new Button("Show Trips"),
                new Button("Show Trips"),
                new Button("Show Trips")};

        for (Button button : showTripsButton) {
            button.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
            button.getStyleClass().add("custom-button");
            button.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
            button.setPrefWidth(100);
            button.setPrefHeight(4);
        }

        HBox[] dayAndButtonBox = new HBox[7];

        searchTicketButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                searchTicket(transport, dateLabel, daysLabel, showTripsButton, dayAndButtonBox);
            }
        });
    }

    public void showExistedTrips(Transport transport, Label day)
    {
        Label tripInformationLabel = new Label("Bus Name\t\tTime\t Price\t   Seat\tTrip Name");
        tripInformationLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        tripInformationLabel.setTranslateX(35);
        tripInformationLabel.setTranslateY(45);

        Label currentDayLabel = new Label(day.getText().trim());
        currentDayLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        currentDayLabel.setTranslateX(210);
        currentDayLabel.setTranslateY(15);

        Stage existedTripStage = new Stage();
        Pane existedTripPane = new Pane();

        existedTripPane.getChildren().add(tripInformationLabel);

        existedTripPane.getChildren().add(currentDayLabel);

        Label[] companyName = new Label[reservationSystem.getCorrectTripList(day.getText().trim()).size()];
        Label[] tripNameLabels = new Label[reservationSystem.getCorrectTripList(day.getText().trim()).size()];
        Label[] vehicleNameLabels = new Label[reservationSystem.getCorrectTripList(day.getText().trim()).size()];
        Label[] hourLabels = new Label[reservationSystem.getCorrectTripList(day.getText().trim()).size()];
        Label[] priceLabels = new Label[reservationSystem.getCorrectTripList(day.getText().trim()).size()];
        Label[] availableSeatAmountLabel = new Label[reservationSystem.getCorrectTripList(day.getText().trim()).size()];
        HBox[] tripBoxes = new HBox[reservationSystem.getCorrectTripList(day.getText().trim()).size()];

        Button[] makeReservationButtons = new Button[reservationSystem.getCorrectTripList(day.getText().trim()).size()];

        int yPosition = 75;
        for (int i = 0; i < reservationSystem.getCorrectTripList(day.getText().trim()).size(); i++)
        {
            String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";

            tripNameLabels[i] = new Label(reservationSystem.getCorrectTripList(day.getText().trim()).get(i).getTripNumber());
            System.out.println(tripNameLabels[i].getText().trim());
            tripNameLabels[i].setStyle(commonStyle + "-fx-font-size: 14px;");
            vehicleNameLabels[i] = new Label(reservationSystem.getCorrectTripList(day.getText().trim()).get(i).getVehicleName() + " (" + reservationSystem.getCorrectTripList(day.getText().trim()).get(i).getOwnCompany() + ")");
            vehicleNameLabels[i].setStyle(commonStyle + "-fx-font-size: 14px;");
            hourLabels[i] = new Label(reservationSystem.getCorrectTripList(day.getText().trim()).get(i).getHour());
            hourLabels[i].setStyle(commonStyle + "-fx-font-size: 14px;");
            priceLabels[i] = new Label(reservationSystem.getCorrectTripList(day.getText().trim()).get(i).getPrice());
            priceLabels[i].setStyle(commonStyle + "-fx-font-size: 14px;");
            availableSeatAmountLabel[i] = new Label(reservationSystem.getCorrectTripList(day.getText()).get(i).getVehicle().getAvaliableSeatAndCapacityRate());
            availableSeatAmountLabel[i].setStyle(commonStyle + "-fx-font-size: 14px;");

            makeReservationButtons[i] = new Button("Make Reservation");
            makeReservationButtons[i].setStyle(commonStyle + "-fx-font-size: 14px;");
            makeReservationButtons[i].setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
            makeReservationButtons[i].getStyleClass().add("custom-button");
            makeReservationButtons[i].setFont(Font.font("Verdana", FontWeight.BOLD, 14));
            makeReservationButtons[i].setPrefWidth(180);
            makeReservationButtons[i].setPrefHeight(4);

            tripBoxes[i] = new HBox(20, vehicleNameLabels[i], hourLabels[i], priceLabels[i], availableSeatAmountLabel[i], tripNameLabels[i], makeReservationButtons[i]);
            tripBoxes[i].setTranslateX(35);
            tripBoxes[i].setTranslateY(yPosition);
            existedTripPane.getChildren().add(tripBoxes[i]);
            yPosition += 40;
        }

        for (int i = 0; i < makeReservationButtons.length; i++)
        {
            int indexOfBus = i;
            makeReservationButtons[i].setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    transport.makeReservation(reservationSystem.getCorrectTripList(day.getText().trim()).get(indexOfBus).getTripNumber(), day, indexOfBus, vehicleNameLabels[indexOfBus], reservationSystem.getCorrectTripList(day.getText().trim()).get(indexOfBus).getVehicle());
                    existedTripStage.close();
                }
            });
        }

        existedTripStage.setScene(new Scene(existedTripPane, 675, 350));
        existedTripStage.show();
    }

    public void searchTicket(Transport transport, Label dateLabel, Label[] daysLabel, Button[] showTripsButton, HBox[] dayAndButtonBox)
    {
        Stage searchStage = new Stage();
        Pane searchPane = new Pane();
        searchPane.getChildren().add(dateLabel);

        int yPosition = 50;

        for (int i = 0; i < daysLabel.length; i++)
        {
            dayAndButtonBox[i] = new HBox(20, daysLabel[i], showTripsButton[i]);
            dayAndButtonBox[i].setTranslateX(30);
            dayAndButtonBox[i].setTranslateY(yPosition);
            yPosition += 40;
            searchPane.getChildren().add(dayAndButtonBox[i]);
        }

        for (int i = 0; i < 7; i++)
        {
            Label day = daysLabel[i];

            showTripsButton[i].setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    showExistedTrips(transport, day);
                }
            });
        }

        searchStage.setScene(new Scene(searchPane, 250, 375));
        BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        searchPane.setBackground(background);
        searchStage.show();
    }

    public Button getSearchTicketButton() { return searchTicketButton; }

}
