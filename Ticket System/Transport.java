import java.util.List;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
public class Transport implements IReservable{
    private List<Company> companies;
    Button companyButton = new Button("COMPANY");

    public Transport()
    {
        companyButton.setMinWidth(400);
        this.companies = new ArrayList<>();
    }

    public List<Company> getCompaniesList() { return companies; }

    public void initCompaniesList()
    {
        Company companyA = new Company("A", "A", "A");
        Company companyB = new Company("B", "B", "B");
        Company companyC = new Company("C", "C", "C");
        Company companyD = new Company("D", "D", "D");
        Company companyF = new Company("F", "F", "F");

        companies.add(companyA);
        companies.add(companyB);
        companies.add(companyC);
        companies.add(companyD);
        companies.add(companyF);
    }

    public void companiesStages(TextField companyNameTextField, TextField companyPasswordTextField)
    {
        String textFieldStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        Label label1 = new Label("Name: ");
        label1.setStyle(commonStyle);
        Label label2 = new Label("Password: ");
        label2.setStyle(commonStyle);

        companyButton.setStyle("-fx-background-color: #4CAF50;  -fx-border-color: black; -fx-border-width: 0.1mm;");
        companyButton.getStyleClass().add("custom-button");
        companyButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        companyButton.setPrefWidth(200);
        companyButton.setPrefHeight(40);

        companyButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                Stage companyLoginStage = new Stage();
                BorderPane companyLoginPane = new BorderPane();
                Button userLoginButton = new Button("LOGIN");
                userLoginButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                userLoginButton.getStyleClass().add("custom-button");
                userLoginButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                userLoginButton.setPrefWidth(200);
                userLoginButton.setPrefHeight(40);
                HBox companyLoginBox = new HBox(8);
                companyLoginBox.setPadding(new Insets(20, 7 , 7, 60));
                companyLoginBox.getChildren().addAll(label1, companyNameTextField, label2, companyPasswordTextField);

                userLoginButton.setOnAction(event->
                {
                    for (int i = 0; i < companies.size(); i++)
                    {
                        if (companies.get(i).userLogin(companyNameTextField.getText().trim(), companyPasswordTextField.getText().trim()))
                        {
                            companies.get(i).companyStage(companyLoginStage, companyNameTextField, companyPasswordTextField);
                        }
                    }
                });

                companyLoginPane.setCenter(userLoginButton);
                companyLoginPane.setTop(companyLoginBox);
                Scene companyScene = new Scene(companyLoginPane, 550, 200);
                companyLoginStage.setScene(companyScene);
                BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
                Background background = new Background(backgroundFill);
                companyLoginPane.setBackground(background);
                companyLoginStage.show();
            }
        });
    }

    @Override
    public void makeReservation(String tripNumber, Label day, int indexOfBus, Label vehicleNameLabel, Vehicle reservedVehicle)
    {
        Customer currentCustomer = new Customer();
        Button continueButton = new Button("Continue ");
        continueButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        continueButton.getStyleClass().add("custom-button");
        continueButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        continueButton.setPrefWidth(150);
        continueButton.setPrefHeight(4);

        Button completeReservationButton = new Button("Complete Reservation");
        completeReservationButton.setTranslateX(170);
        completeReservationButton.setTranslateY(50);
        completeReservationButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        completeReservationButton.getStyleClass().add("custom-button");
        completeReservationButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        completeReservationButton.setPrefWidth(180);
        completeReservationButton.setPrefHeight(4);

        Stage seatInfoStage = new Stage();
        Pane seatInfoPane = new Pane();
        String textFieldStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";

        Label chosenSeatsLabel = new Label("Chosen Seats : ");
        chosenSeatsLabel.setStyle(commonStyle);
        TextField chosenSeatsField = new TextField("");
        chosenSeatsField.setStyle(textFieldStyle);
        HBox chosenSeatsBox = new HBox(20, chosenSeatsLabel, chosenSeatsField,continueButton);
        chosenSeatsBox.setTranslateX(40);
        chosenSeatsBox.setTranslateY(250);

        seatInfoPane.getChildren().add(chosenSeatsBox);

        Label busName = new Label(reservationSystem.getCorrectTripList(day.getText().trim()).get(indexOfBus).getVehicleName());
        busName.setTranslateX(25);
        busName.setTranslateY(25);

        seatInfoPane.getChildren().add(busName);
        reservationSystem.getCorrectTripList(day.getText().trim()).get(indexOfBus).getVehicle().showSeatsOnScreen(seatInfoPane, chosenSeatsField, currentCustomer.chosenSeats);

        seatInfoStage.setScene(new Scene(seatInfoPane, 600, 350));
        seatInfoStage.show();

        continueButton.setOnAction(event ->
        {
            Stage personStage = new Stage();
            Pane personPane = new Pane();
            Label[] passengersNamesLabels = new Label[currentCustomer.chosenSeats.size()];
            Label[] passengersTCLabels = new Label[currentCustomer.chosenSeats.size()];
            Label[] passengersBirthDateLabels = new Label[currentCustomer.chosenSeats.size()];
            Label[] passengersSurnamesLabels = new Label[currentCustomer.chosenSeats.size()];
            Label[] passengersSeatNumbersLabels = new Label[currentCustomer.chosenSeats.size()];

            TextField[] passengersNamesFields = new TextField[currentCustomer.chosenSeats.size()];
            TextField[] passengersSurnamesFields = new TextField[currentCustomer.chosenSeats.size()];
            TextField[] passengersTCFields = new TextField[currentCustomer.chosenSeats.size()];
            TextField[] passengersBirthDateFields = new TextField[currentCustomer.chosenSeats.size()];

            HBox[] passengerNameBox = new HBox[currentCustomer.chosenSeats.size()];
            HBox[] passengerSurnameBox = new HBox[currentCustomer.chosenSeats.size()];
            HBox[] passengerTCBox = new HBox[currentCustomer.chosenSeats.size()];
            HBox[] passengerBirthDateBox = new HBox[currentCustomer.chosenSeats.size()];

            int yPosition = 50;

            for (int i = 0; i < currentCustomer.chosenSeats.size(); i++)
            {
                passengersSeatNumbersLabels[i] = new Label("Seat Number : " + Integer.toString(+ currentCustomer.chosenSeats.get(i)));
                passengersNamesLabels[i] = new Label("Passenger Name :   ");
                passengersSurnamesLabels[i] = new Label("Passenger Surname:");
                passengersTCLabels[i] = new Label("Passengers TC :       ");
                passengersBirthDateLabels[i] = new Label("Passengers Birt Date:");
                passengersNamesFields[i] = new TextField("");
                passengersSurnamesFields[i] = new TextField("");
                passengersTCFields[i] = new TextField("");
                passengersBirthDateFields[i] = new TextField("");
                passengerNameBox[i] = new HBox(20, passengersNamesLabels[i], passengersNamesFields[i]);
                passengerSurnameBox[i] = new HBox(20, passengersSurnamesLabels[i], passengersSurnamesFields[i]);
                passengerTCBox[i] = new HBox(20, passengersTCLabels[i], passengersTCFields[i]);
                passengerBirthDateBox[i] = new HBox(20, passengersBirthDateLabels[i], passengersBirthDateFields[i]);

                passengersSeatNumbersLabels[i].setTranslateX(50);
                passengersSeatNumbersLabels[i].setTranslateY(yPosition);
                yPosition += 35;
                passengerNameBox[i].setTranslateX(50);
                passengerNameBox[i].setTranslateY(yPosition);
                yPosition += 35;
                passengerSurnameBox[i].setTranslateX(50);
                passengerSurnameBox[i].setTranslateY(yPosition);
                yPosition += 35;
                passengerTCBox[i].setTranslateX(50);
                passengerTCBox[i].setTranslateY(yPosition);
                yPosition += 35;
                passengerBirthDateBox[i].setTranslateX(50);
                passengerBirthDateBox[i].setTranslateY(yPosition);
                yPosition += 50;

                personPane.getChildren().add(passengerNameBox[i]);
                personPane.getChildren().add(passengerSurnameBox[i]);
                personPane.getChildren().add(passengerTCBox[i]);
                personPane.getChildren().add(passengerBirthDateBox[i]);
                personPane.getChildren().add(passengersSeatNumbersLabels[i]);
            }

            personPane.getChildren().add(completeReservationButton);

            completeReservationButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    completeReservation(tripNumber, currentCustomer, passengersNamesFields, passengersSurnamesFields, passengersTCFields, passengersBirthDateFields, currentCustomer.chosenSeats, reservedVehicle, vehicleNameLabel, chosenSeatsField);
                    personStage.close();
                    currentCustomer.chosenSeats.clear();
                }
            });

            Scene personScene = new Scene(personPane, 500, 400);
            personStage.setScene(personScene);
            personStage.show();
        });
    }

    public void completeReservation(String tripNumber, Customer customer, TextField[] nameFields, TextField[] surnameFields, TextField[] TCFields, TextField[] BirthDateFields, List<Integer> seatsNumbers, Vehicle vehicle, Label vehicleNameLabel, TextField chosenSeatsField)
    {
        Stage completeReservationStage = new Stage();
        Pane completeReservationPane = new Pane();

        Label reservationsYouMade = new Label("- Reservations You Made ( " + tripNumber + " )");
        reservationsYouMade.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        reservationsYouMade.setTranslateX(185);
        reservationsYouMade.setTranslateY(20);
        completeReservationPane.getChildren().add(reservationsYouMade);

        Label reservationsInfoLabel = new Label("Surname-LastName\t\t    T.C.\t\t\tBirt Date\t\t\tSeat Number");
        reservationsInfoLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        reservationsInfoLabel.setTranslateX(35);
        reservationsInfoLabel.setTranslateY(50);
        completeReservationPane.getChildren().add(reservationsInfoLabel);

        vehicle.decreaseAvailableSeatCount(seatsNumbers.size());
        vehicle.lockReservedSeats();

        Passenger[] newPassengers = new Passenger[seatsNumbers.size()];

        for (int i = 0; i < seatsNumbers.size(); i++)
        {
            String name = nameFields[i].getText().trim();
            String surname = surnameFields[i].getText().trim();
            String TC = TCFields[i].getText().trim();
            String birthDate = BirthDateFields[i].getText().trim();
            int seatNumber = seatsNumbers.get(i);

            newPassengers[i] = new Passenger(name, surname, TC, birthDate);

            String vehicleOwnCompany = new String("");
            for (int j = vehicleNameLabel.getText().trim().length()-2; vehicleNameLabel.getText().trim().charAt(j) != '(' ; j--)
            {
                vehicleOwnCompany += vehicleNameLabel.getText().trim().charAt(j);
            }

            chosenSeatsField.setText("");
            getCorrectCompany(vehicleOwnCompany).customers.add(customer);
        }

        Reservation newReservation = new Reservation(vehicle);

        for (int i = 0; i < seatsNumbers.size(); i++)
        {
            newReservation.getPassengerslist().add(newPassengers[i]);
        }

        customer.madeReservationsList.add(newReservation);

        HBox[] passengersInformationsBox = new HBox[customer.madeReservationsList.get(customer.madeReservationsList.size()-1).getPassengerslist().size()];

        int yPosition = 100;

        for (int i = 0; i < customer.madeReservationsList.get(customer.madeReservationsList.size()-1).getPassengerslist().size(); i++)
        {
            Label passengerFirstAndLastName= new Label(customer.madeReservationsList.get(customer.madeReservationsList.size()-1).getPassengerslist().get(i).getFirstName() +
                    "-" + customer.madeReservationsList.get(customer.madeReservationsList.size()-1).getPassengerslist().get(i).getLastName() + "\t\t");
            Label passengerTC = new Label(customer.madeReservationsList.get(customer.madeReservationsList.size()-1).getPassengerslist().get(i).getTC() + "\t\t");
            Label passengerBirthDate = new Label(customer.madeReservationsList.get(customer.madeReservationsList.size()-1).getPassengerslist().get(i).getBirthDate() + "\t\t\t");
            Label passengersSeat = new Label(Integer.toString(seatsNumbers.get(i)));

            passengersInformationsBox[i] = new HBox(20, passengerFirstAndLastName, passengerTC, passengerBirthDate, passengersSeat);
            passengersInformationsBox[i].setTranslateX(50);
            passengersInformationsBox[i].setTranslateY(yPosition);
            yPosition += 35;
            completeReservationPane.getChildren().add(passengersInformationsBox[i]);
        }

        completeReservationStage.setScene(new Scene(completeReservationPane, 630, 250));
        completeReservationStage.show();
    }

    public Company getCorrectCompany(String companyName)
    {
        for (var company : companies)
        {
            if (company.getName().equals(companyName))
            {
                return company;
            }
        }

        return new Company("", "", "");
    }

    public Button getCompanyButton() { return companyButton; }

    public static void main(String[] args) {}
}