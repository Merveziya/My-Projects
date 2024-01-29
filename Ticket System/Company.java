import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

public class Company extends User implements IProfitable
{
    List<Customer> customers;
    private int planeCount = 0;
    private int busCount = 0;
    private int trainCount = 0;
    private String companyName;
    private List<Vehicle> vehicleList = new ArrayList<>();
    private List<Vehicle> busList = new ArrayList<>();
    private List<Vehicle> trainList = new ArrayList<>();
    private List<Vehicle> planeList = new ArrayList<>();
    private Label vehiclesLabel = new Label();
    private List<Trip> companysTrips;
    TextField addNewVehicleField = new TextField("");
    TextField deleteVehicleField = new TextField("");

    public List<Vehicle> getVehicleList() { return vehicleList; }

    public Label getVehicleLabel() { return vehiclesLabel; }

    public Company(String companyName, String userName, String password)
    {
        addNewVehicleField.setPromptText("Enter new vehicle ");
        deleteVehicleField.setPromptText("Enter delete vehicle ");
        userBuilder = new StringBuilder();
        this.companyName= companyName;
        this.userName = userName;
        this.password = password;
        companysTrips = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public void companyStage(Stage companyLoginStage, TextField companyNameTextField, TextField companyPasswordTextField)
    {
        String textFieldStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        String companyUserName = companyNameTextField.getText().trim();
        companyNameTextField.setStyle(textFieldStyle);
        String companyPassword= companyPasswordTextField.getText().trim();
        companyPasswordTextField.setStyle(textFieldStyle);
        Stage companyStage = new Stage();
        Pane companyPane = new Pane();

        Button addVehicleButton = new Button("ADD VEHICLE");
        addVehicleButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        addVehicleButton.getStyleClass().add("custom-button");
        addVehicleButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        addVehicleButton.setPrefWidth(200);
        addVehicleButton.setPrefHeight(40);

        Button deleteVehicleButton = new Button("DELETE VEHICLE");
        deleteVehicleButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        deleteVehicleButton.getStyleClass().add("custom-button");
        deleteVehicleButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        deleteVehicleButton.setPrefWidth(200);
        deleteVehicleButton.setPrefHeight(40);

        Button addTripButton = new Button("ADD TRIP");
        addTripButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        addTripButton.getStyleClass().add("custom-button");
        addTripButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        addTripButton.setPrefWidth(200);
        addTripButton.setPrefHeight(40);

        Button deleteTripButton = new Button("DELETE TRIP");
        deleteTripButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        deleteTripButton.getStyleClass().add("custom-button");
        deleteTripButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        deleteTripButton.setPrefWidth(200);
        deleteTripButton.setPrefHeight(40);

        Label companyNameLabel = new Label("COMPANY NAME : " + companyName);
        companyNameLabel.setStyle(commonStyle);
        companyNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        companyNameLabel.setTranslateX(195);
        companyNameLabel.setTranslateY(30);
        companyNameLabel.setTranslateX(30);
        companyNameLabel.setTranslateY(30);

        Button showVehicles = new Button("SHOW VEHICLE");
        showVehicles.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        showVehicles.getStyleClass().add("custom-button");
        showVehicles.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        showVehicles.setPrefWidth(200);
        showVehicles.setPrefHeight(40);

        HBox vehicleBox = new HBox(20, addVehicleButton, deleteVehicleButton);
        vehicleBox.setTranslateX(50);
        vehicleBox.setTranslateY(75);

        HBox tripBox = new HBox(20, addTripButton, deleteTripButton);
        tripBox.setTranslateX(50);
        tripBox.setTranslateY(125);

        HBox showInfoBox = new HBox(20, showVehicles);
        showInfoBox.setTranslateX(50);
        showInfoBox.setTranslateY(175);

        companyPane.getChildren().add(vehicleBox);
        companyPane.getChildren().add(getVehicleLabel());
        companyPane.getChildren().add(showInfoBox);
        companyPane.getChildren().add(tripBox);
        companyPane.getChildren().add(companyNameLabel);

        companyStage.setScene(new Scene(companyPane, 520, 250));
        BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        companyPane.setBackground(background);
        companyLoginStage.show();
        companyStage.show();

        addVehicleButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                addVehicle();
            }
        });

        deleteVehicleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                deleteVehicle(companyNameTextField, companyPasswordTextField);
            }
        });

        addTripButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                addTrip();
            }
        });


        deleteTripButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                deleteTrip();
            }
        });

        showVehicles.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Stage existedVehicleStage = new Stage();
                Pane existedVehiclePane = new Pane();

                getVehicleLabel().setTranslateY(35);
                getVehicleLabel().setTranslateX(35);
                vehiclesLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                existedVehiclePane.getChildren().add(vehiclesLabel);

                existedVehicleStage.setScene(new Scene(existedVehiclePane, 250, 250));
                existedVehicleStage.show();
            }
        });
    }

    public void addVehicle()
    {
        Button[] gasTypeButtons = new Button[2];

        gasTypeButtons[0] = new Button("Gas");
        gasTypeButtons[0].setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        gasTypeButtons[0].getStyleClass().add("custom-button");
        gasTypeButtons[0].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        gasTypeButtons[0].setPrefWidth(50);
        gasTypeButtons[0].setPrefHeight(1);
        gasTypeButtons[1] = new Button("Diesel");
        gasTypeButtons[1].setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        gasTypeButtons[1].getStyleClass().add("custom-button");
        gasTypeButtons[1].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        gasTypeButtons[1].setPrefWidth(70);
        gasTypeButtons[1].setPrefHeight(1);


        String textFieldStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";

        Label busGasTypeLabel = new Label("Chose Gas Type of Bus : ");
        busGasTypeLabel.setStyle(commonStyle);
        TextField busGasTypeField = new TextField();
        busGasTypeField.setStyle(textFieldStyle);
        busGasTypeField.setPromptText("Enter Gas Type");

        Label busCapacityLabel = new Label("Enter Capacity of Bus :  ");
        busCapacityLabel.setStyle(commonStyle);
        TextField busCapacityField = new TextField();
        busCapacityField.setStyle(textFieldStyle);
        busCapacityField.setPromptText("Enter Capacity");

        Label trainCapacityLabel = new Label("Enter Capacity of Train : ");
        trainCapacityLabel.setStyle(commonStyle);
        TextField trainCapacityField = new TextField();
        trainCapacityField.setStyle(textFieldStyle);
        trainCapacityField.setPromptText("Enter Capacity");

        Label planeCapacityLabel = new Label("Enter Capacity of Plain : ");
        planeCapacityLabel.setStyle(commonStyle);
        TextField planeCapacityField = new TextField();
        planeCapacityField.setStyle(textFieldStyle);
        planeCapacityField.setPromptText("Enter Capacity");

        Stage vehicleChoiceStage = new Stage();
        Pane addVehiclePane = new Pane();

        HBox busGasBox = new HBox(20, busGasTypeLabel, gasTypeButtons[0], gasTypeButtons[1], busGasTypeField);
        HBox busCapacityBox = new HBox(20, busCapacityLabel, busCapacityField);
        for (var gasTypeButton : gasTypeButtons)
        {
            gasTypeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    busGasTypeField.setText(((Button)actionEvent.getSource()).getText());
                }
            });
        }

        busGasBox.setTranslateX(30);
        busGasBox.setTranslateY(50);
        busCapacityBox.setTranslateX(30);
        busCapacityBox.setTranslateY(90);
        Button busButton = new Button("BUS");
        busButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        busButton.getStyleClass().add("custom-button");
        busButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        busButton.setPrefWidth(70);
        busButton.setPrefHeight(4);
        busButton.setTranslateX(30);
        busButton.setTranslateY(10);

        HBox trainBox = new HBox(20, trainCapacityLabel, trainCapacityField);
        trainBox.setTranslateX(30);
        trainBox.setTranslateY(175);
        Button trainButton = new Button("TRAIN");
        trainButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        trainButton.getStyleClass().add("custom-button");
        trainButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        trainButton.setPrefWidth(70);
        trainButton.setPrefHeight(4);
        trainButton.setTranslateX(30);
        trainButton.setTranslateY(135);

        HBox planeBox = new HBox(20, planeCapacityLabel, planeCapacityField);
        planeBox.setTranslateX(30);
        planeBox.setTranslateY(260);
        Button planeButton = new Button("PLANE");
        planeButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        planeButton.getStyleClass().add("custom-button");
        planeButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        planeButton.setPrefWidth(70);
        planeButton.setPrefHeight(4);
        planeButton.setTranslateX(30);
        planeButton.setTranslateY(220);

        addVehiclePane.getChildren().add(busButton);
        addVehiclePane.getChildren().add(trainButton);
        addVehiclePane.getChildren().add(planeButton);
        addVehiclePane.getChildren().add(planeBox);
        addVehiclePane.getChildren().add(trainBox);
        addVehiclePane.getChildren().add(busGasBox);
        addVehiclePane.getChildren().add(busCapacityBox);

        vehicleChoiceStage.setScene(new Scene(addVehiclePane, 600, 350));
        BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        addVehiclePane.setBackground(background);
        vehicleChoiceStage.show();

        busButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                String gasType = busGasTypeField.getText().trim();
                int capacity = Integer.parseInt(busCapacityField.getText());

                if ((!gasType.isEmpty() && (gasType.equals("Gas") || gasType.equals("Diesel"))) && capacity > 0)
                {
                    increaseBusCount();
                    String newBusName = "new Bus " + getBusCount();
                    Bus newBus = new Bus(newBusName, gasType, capacity);
                    updateLabelVehicleAdd(newBus);
                    busList.add(newBus);
                }
            }
        });

        trainButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                int capacity = Integer.parseInt(trainCapacityField.getText());

                if (capacity > 0)
                {
                    increaseTrainCount();
                    String newTrainName = "new Train " + getTrainCount();
                    Train newTrain = new Train(newTrainName, "Electric", capacity);
                    updateLabelVehicleAdd(newTrain);
                    trainList.add(newTrain);
                }
            }
        });

        planeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                int capacity = Integer.parseInt(planeCapacityField.getText());
                if (capacity > 0)
                {
                    increasePlaneCount();
                    String newPlaneName = "new Plane " + getPlaneCount();
                    Airplane newPlane = new Airplane(newPlaneName, "Electric", capacity);
                    updateLabelVehicleAdd(newPlane);
                    planeList.add(newPlane);
                }
            }
        });
    }

    public void deleteVehicle(TextField companyNameTextField, TextField companyPasswordTextField)
    {
        companyNameTextField.setText("");
        companyPasswordTextField.setText("");

        Stage deleteStage = new Stage();
        Pane deletePane = new Pane();

        String textFieldStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        Label existedVehicleLabel = new Label("- Existed Vehicles -");
        existedVehicleLabel.setStyle(commonStyle);

        existedVehicleLabel.setTranslateX(30);
        existedVehicleLabel.setTranslateY(30);
        deletePane.getChildren().add(existedVehicleLabel);
        BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        deletePane.setBackground(background);

        Button confirmDeleteButton = new Button("Delete");
        confirmDeleteButton.setTranslateX(135);
        confirmDeleteButton.setTranslateY(150);
        TextField deleteVehicleNameField = new TextField("");

        Button[] vehiclesButton = new Button[vehicleList.size()];

        int yPosition = 75;
        for (int i = 0; i < vehicleList.size(); i++)
        {
            vehiclesButton[i] = new Button(vehicleList.get(i).getVehicleId());
            vehiclesButton[i].setTranslateX(25);
            vehiclesButton[i].setTranslateY(yPosition);
            yPosition += 30;
        }

        for (var vehicleButton : vehiclesButton)
        {
            deletePane.getChildren().add(vehicleButton);
        }

        for (var vehicleButton : vehiclesButton)
        {
            vehicleButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    Stage completeDeletionStage = new Stage();
                    Pane completeDeletionPane = new Pane();

                    Label companyUserNameLabel = new Label("Company User Name : ");
                    Label companyPasswordLabel = new Label("Company Password : ");

                    HBox companyUserNameBox = new HBox(20, companyUserNameLabel, companyNameTextField);
                    companyUserNameBox.setTranslateX(30);
                    companyUserNameBox.setTranslateY(50);

                    HBox companyPasswordBox = new HBox(20, companyPasswordLabel, companyPasswordTextField);
                    companyPasswordBox.setTranslateX(30);
                    companyPasswordBox.setTranslateY(85);

                    completeDeletionPane.getChildren().add(confirmDeleteButton);
                    completeDeletionPane.getChildren().add(companyUserNameBox);
                    completeDeletionPane.getChildren().add(companyPasswordBox);
                    confirmDeleteButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            boolean isVehicleExist = false;
                            int deletedVehicleIndex = 0;
                            String deletedVehicle = vehicleButton.getText().trim();
                            String companyName = companyNameTextField.getText().trim();
                            String companyPassword = companyPasswordTextField.getText().trim();

                            if (deletedVehicle != null)
                            {
                                for (int i = 0; i < getVehicleList().size(); i++)
                                {
                                    if (getVehicleList().get(i).getVehicleId().equals(deletedVehicle))
                                    {
                                        deletedVehicleIndex = i;
                                        isVehicleExist = true;
                                    }
                                }
                            }

                            if (isVehicleExist && userLogin(companyName, companyPassword))
                            {
                                updateLabelVehicleSubtract(deletedVehicleIndex);

                                String vehicleType = getTheVehicleType(deletedVehicle);

                                for (var vehicle : getCorrectVehicleList(vehicleType))
                                {
                                    if (vehicle.getVehicleId().equals(deletedVehicle))
                                    {
                                        getCorrectVehicleList(vehicleType).remove(vehicle);
                                    }
                                }
                            }

                            deleteVehicleNameField.setText("");
                            companyNameTextField.setText("");
                            companyPasswordTextField.setText("");
                            deleteStage.close();
                            completeDeletionStage.close();
                        }
                    });

                    completeDeletionStage.setScene(new Scene(completeDeletionPane, 350, 200));
                    completeDeletionStage.show();
                }
            });
        }

        deleteStage.setScene(new Scene(deletePane, 160, 250));
        deleteStage.show();
    }

    public String getTheVehicleType(String vehicleFullName)
    {
        String vehicleType = "";

        for (int i = 4; vehicleFullName.charAt(i) != ' ' ; i++)
        {
            vehicleType += vehicleFullName.charAt(i);
        }

        return vehicleType.trim();
    }

    public List<Vehicle> getCorrectVehicleList(String vehicleType)
    {
        if (vehicleType.equals("Bus"))
        {
            return busList;
        }

        if (vehicleType.equals("Plane"))
        {
            return planeList;
        }

        if (vehicleType.equals("Train"))
        {
            return trainList;
        }

        return new ArrayList<>();
    }

    public void addTrip()
    {
        String textFieldStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        Label tripLabel = new Label("Chose The Vehicle Type To Add Trip");
        tripLabel.setStyle(commonStyle);
        tripLabel.setTranslateX(25);
        tripLabel.setTranslateY(20);

        Button busButton = new Button("Bus");
        busButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        busButton.getStyleClass().add("custom-button");
        busButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        busButton.setPrefWidth(70);
        busButton.setPrefHeight(4);
        busButton.setTranslateX(90);
        busButton.setTranslateY(70);

        Button trainButton = new Button("Train");
        trainButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        trainButton.getStyleClass().add("custom-button");
        trainButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        trainButton.setPrefWidth(70);
        trainButton.setPrefHeight(4);
        trainButton.setTranslateX(90);
        trainButton.setTranslateY(120);

        Button planeButton = new Button("Plane");
        planeButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        planeButton.getStyleClass().add("custom-button");
        planeButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        planeButton.setPrefWidth(70);
        planeButton.setPrefHeight(4);
        planeButton.setTranslateX(90);
        planeButton.setTranslateY(180);

        Stage addTripStage = new Stage();
        Pane addTripPane = new Pane();
        BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        addTripPane.setBackground(background);
        addTripPane.getChildren().add(busButton);
        addTripPane.getChildren().add(trainButton);
        addTripPane.getChildren().add(planeButton);
        addTripPane.getChildren().add(tripLabel);

        busButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Stage busTripStage = new Stage();
                Pane busTripPane = new Pane();

                completeTripAddition(busTripPane, busList, Trip.busTripRoutes, Route.busRoutesAndDistances, Route.busRoutesAndPrices, "Trip-3", "Trip-4");

                busTripStage.setScene(new Scene(busTripPane, 670, 140));
                busTripStage.show();
                addTripStage.close();
            }
        });

        trainButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Stage trainTripStage = new Stage();
                Pane trainTripPane = new Pane();

                completeTripAddition(trainTripPane, trainList, Trip.trainTripRoutes, Route.trainRoutesAndDistances, Route.trainRoutesAndPrices, "Trip-1", "Trip-2");

                trainTripStage.setScene(new Scene(trainTripPane, 670, 140));
                trainTripStage.show();
                addTripStage.close();
            }
        });

        planeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Stage planeTripStage = new Stage();
                Pane planeTripPane = new Pane();

                completeTripAddition(planeTripPane, planeList, Trip.planeTripRoutes, Route.planeRoutesAndDistances, Route.planeRoutesAndPrices, "Trip-5", "Trip-6");

                planeTripStage.setScene(new Scene(planeTripPane, 670, 140));
                planeTripStage.show();
                addTripStage.close();
            }
        });

        addTripStage.setScene(new Scene(addTripPane, 250, 250));
        addTripStage.show();
    }

    public void completeTripAddition(Pane vehiclePane, List<Vehicle> vehicleList, String[][] vehicleTripRoutes, Route[] routeAndDistances, String[][] routeAndPrices, String trip1Name, String trip2Name)
    {
        Label[] vehicleLabels = new Label[vehicleList.size()];
        Label[] tripLabels = new Label[vehicleTripRoutes.length];
        int yPosition = 165;

        for (int i = 0; i < vehicleLabels.length; i++)
        {
            vehicleLabels[i] = new Label(vehicleList.get(i).getVehicleId());
            vehicleLabels[i].setTranslateX(175);
            vehicleLabels[i].setTranslateY(yPosition);
            yPosition += 20;
            vehiclePane.getChildren().add(vehicleLabels[i]);
        }

        for (int i = 0; i < vehicleTripRoutes.length; i++)
        {
            String currentRoute = new String("");
            for (var route : vehicleTripRoutes[i])
            {
                currentRoute += route;
                currentRoute += "-";
            }

            tripLabels[i] = new Label(currentRoute);
        }

        String textFieldStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        Button[] tripsButton = {new Button(trip1Name), new Button(trip2Name)};

        tripsButton[0].setTranslateX(50);
        tripsButton[0].setTranslateY(15);
        tripsButton[0].setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        tripsButton[0].getStyleClass().add("custom-button");
        tripsButton[0].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        tripsButton[0].setPrefWidth(100);
        tripsButton[0].setPrefHeight(4);
        tripLabels[0].setTranslateX(50);
        tripLabels[0].setTranslateY(50);
        tripLabels[0].setStyle("-fx-background-color: #D3D3D3; -fx-border-color: black; -fx-border-width: 0.1mm;");
        tripLabels[0].getStyleClass().add("custom-button");
        tripLabels[0].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        tripLabels[0].setPrefWidth(550);
        tripLabels[0].setPrefHeight(4);

        tripsButton[1].setTranslateX(50);
        tripsButton[1].setTranslateY(80);
        tripsButton[1].setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        tripsButton[1].getStyleClass().add("custom-button");
        tripsButton[1].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        tripsButton[1].setPrefWidth(100);
        tripsButton[1].setPrefHeight(4);
        tripLabels[1].setTranslateX(50);
        tripLabels[1].setTranslateY(110);
        tripLabels[1].setStyle("-fx-background-color: #D3D3D3; -fx-border-color: black; -fx-border-width: 0.1mm;");
        tripLabels[1].getStyleClass().add("custom-button");
        tripLabels[1].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        tripLabels[1].setPrefWidth(550);
        tripLabels[1].setPrefHeight(4);


        vehiclePane.getChildren().add(tripsButton[0]);
        vehiclePane.getChildren().add(tripsButton[1]);
        vehiclePane.getChildren().add(tripLabels[0]);
        vehiclePane.getChildren().add(tripLabels[1]);
        BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vehiclePane.setBackground(background);

        for (int i = 0; i < 2; i++)
        {
            int tripIndex = i;
            tripsButton[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    TextField choseVehicleField = new TextField("");
                    choseVehicleField.setStyle(textFieldStyle);
                    Button choseVehicle = new Button("Chose Vehicle");
                    choseVehicle.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                    choseVehicle.getStyleClass().add("custom-button");
                    choseVehicle.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                    choseVehicle.setPrefWidth(200);
                    choseVehicle.setPrefHeight(40);

                    TextField choseStartLocationField = new TextField("");
                    choseStartLocationField.setStyle(textFieldStyle);
                    Button choseStartLocation= new Button("Chose Start Location");
                    choseStartLocation.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                    choseStartLocation.getStyleClass().add("custom-button");
                    choseStartLocation.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                    choseStartLocation.setPrefWidth(200);
                    choseStartLocation.setPrefHeight(40);

                    TextField choseEndLocationField = new TextField("");
                    choseEndLocationField.setStyle(textFieldStyle);
                    Button choseEndLocation = new Button("Chose End Location");
                    choseEndLocation.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                    choseEndLocation.getStyleClass().add("custom-button");
                    choseEndLocation.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                    choseEndLocation.setPrefWidth(200);
                    choseEndLocation.setPrefHeight(40);

                    TextField choseDateField = new TextField("");
                    choseDateField.setStyle(textFieldStyle);
                    Button choseDate = new Button("Chose Date");
                    choseDate.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                    choseDate.getStyleClass().add("custom-button");
                    choseDate.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                    choseDate.setPrefWidth(200);
                    choseDate.setPrefHeight(40);

                    TextField choseHourField = new TextField("");
                    choseHourField.setStyle(textFieldStyle);
                    Button choseHour = new Button("Chose Hour");
                    choseHour.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                    choseHour.getStyleClass().add("custom-button");
                    choseHour.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                    choseHour.setPrefWidth(200);
                    choseHour.setPrefHeight(40);

                    HBox choseVehicleBox = new HBox(20, choseVehicle, choseVehicleField);
                    choseVehicleBox.setTranslateX(50);
                    choseVehicleBox.setTranslateY(50);
                    HBox choseStartLocationBox = new HBox(20, choseStartLocation, choseStartLocationField);
                    choseStartLocationBox.setTranslateX(50);
                    choseStartLocationBox.setTranslateY(100);
                    HBox choseEndLocationBox = new HBox(20, choseEndLocation, choseEndLocationField);
                    choseEndLocationBox.setTranslateX(50);
                    choseEndLocationBox.setTranslateY(150);
                    HBox choseDateBox = new HBox(20, choseDate, choseDateField);
                    choseDateBox.setTranslateX(50);
                    choseDateBox.setTranslateY(200);
                    HBox choseHourBox = new HBox(20, choseHour, choseHourField);
                    choseHourBox.setTranslateX(50);
                    choseHourBox.setTranslateY(250);

                    Label routeLabel = new Label("Route : " + tripLabels[tripIndex].getText());
                    routeLabel.setStyle(commonStyle);

                    routeLabel.setTranslateX(25);
                    routeLabel.setTranslateY(10);

                    Button completeAddTripButton = new Button("COMPLETE");
                    completeAddTripButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                    completeAddTripButton.getStyleClass().add("custom-button");
                    completeAddTripButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                    completeAddTripButton.setPrefWidth(100);
                    completeAddTripButton.setPrefHeight(4);
                    completeAddTripButton.setTranslateX(200);
                    completeAddTripButton.setTranslateY(310);

                    Stage vehicleTripStage = new Stage();
                    Pane vehicleTripPane = new Pane();

                    vehicleTripPane.getChildren().add(choseVehicleBox);
                    vehicleTripPane.getChildren().add(choseStartLocationBox);
                    vehicleTripPane.getChildren().add(choseEndLocationBox);
                    vehicleTripPane.getChildren().add(choseDateBox);
                    vehicleTripPane.getChildren().add(choseHourBox);
                    vehicleTripPane.getChildren().add(completeAddTripButton);
                    vehicleTripPane.getChildren().add(routeLabel);

                    choseVehicle.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            Stage vehicleStage = new Stage();
                            Pane vehiclePane = new Pane();

                            Button[] vehicleButtons = new Button[vehicleList.size()];

                            int yPosition = 30;
                            for (int i = 0; i < vehicleList.size(); i++)
                            {
                                vehicleButtons[i] = new Button(vehicleList.get(i).getVehicleId());
                                vehicleButtons[i].setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                                vehicleButtons[i].getStyleClass().add("custom-button");
                                vehicleButtons[i].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                                vehicleButtons[i].setPrefWidth(100);
                                vehicleButtons[i].setPrefHeight(4);
                                vehicleButtons[i].setTranslateX(40);
                                vehicleButtons[i].setTranslateY(yPosition);
                                yPosition += 30;
                            }

                            for (var vehicleButton : vehicleButtons)
                            {
                                vehiclePane.getChildren().add(vehicleButton);
                            }

                            for (var vehicleButton : vehicleButtons)
                            {
                                vehicleButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent)
                                    {
                                        choseVehicleField.setText(vehicleButton.getText().trim());
                                        vehicleStage.close();

                                    }
                                });
                            }

                            vehicleStage.setScene(new Scene(vehiclePane, 180, 200));
                            vehicleStage.show();
                        }
                    });

                    choseStartLocation.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            Stage vehicleStage = new Stage();
                            Pane vehiclePane = new Pane();

                            Button[] startLocationButtons = new Button[(vehicleTripRoutes[tripIndex].length/2) + 1];

                            int yPosition = 30;
                            int startLocIndex = 0;
                            for (int i = 0; i < (vehicleTripRoutes[tripIndex].length/2) + 1; i++)
                            {
                                startLocationButtons[i] = new Button(vehicleTripRoutes[tripIndex][i].trim());
                                startLocationButtons[i].setTranslateX(50);
                                startLocationButtons[i].setTranslateY(yPosition);
                                startLocationButtons[startLocIndex].setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                                startLocationButtons[startLocIndex].getStyleClass().add("custom-button");
                                startLocationButtons[startLocIndex].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                                startLocationButtons[startLocIndex].setPrefWidth(100);
                                startLocationButtons[startLocIndex].setPrefHeight(4);
                                startLocIndex++;
                                yPosition += 30;
                            }

                            for (var startLocButton: startLocationButtons)
                            {
                                vehiclePane.getChildren().add(startLocButton);
                            }

                            for (var startLocButton: startLocationButtons)
                            {
                                startLocButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent)
                                    {
                                        choseStartLocationField.setText(startLocButton.getText());
                                        vehicleStage.close();
                                    }
                                });
                            }

                            vehicleStage.setScene(new Scene(vehiclePane, 180, 200));
                            BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
                            Background background = new Background(backgroundFill);
                            vehiclePane.setBackground(background);
                            vehicleStage.show();
                        }
                    });

                    choseEndLocation.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            Stage vehicleStage = new Stage();
                            Pane vehiclePane = new Pane();

                            Button[] endLocationButtons = new Button[vehicleTripRoutes[tripIndex].length/2];

                            int yPosition = 30;
                            int endLocIndex = 0;

                            for (int i = 0; i < (vehicleTripRoutes[tripIndex].length/2 + 1); i++)
                            {
                                if (!vehicleTripRoutes[tripIndex][i].equals(choseStartLocationField.getText().trim()))
                                {
                                    endLocationButtons[endLocIndex] = new Button(vehicleTripRoutes[tripIndex][i].trim());
                                    endLocationButtons[endLocIndex].setTranslateX(40);
                                    endLocationButtons[endLocIndex].setTranslateY(yPosition);
                                    endLocationButtons[endLocIndex].setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                                    endLocationButtons[endLocIndex].getStyleClass().add("custom-button");
                                    endLocationButtons[endLocIndex].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                                    endLocationButtons[endLocIndex].setPrefWidth(100);
                                    endLocationButtons[endLocIndex].setPrefHeight(4);
                                    endLocIndex++;
                                    yPosition += 30;
                                }
                            }

                            for (var endLocButton: endLocationButtons)
                            {
                                vehiclePane.getChildren().add(endLocButton);
                            }

                            for (var endLocButton: endLocationButtons)
                            {
                                endLocButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent)
                                    {
                                        choseEndLocationField.setText(endLocButton.getText());
                                        vehicleStage.close();
                                    }
                                });
                            }

                            vehicleStage.setScene(new Scene(vehiclePane, 200, 250));
                            BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
                            Background background = new Background(backgroundFill);
                            vehiclePane.setBackground(background);
                            vehicleStage.show();
                        }
                    });
                    choseDate.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            Stage vehicleStage = new Stage();
                            Pane vehiclePane = new Pane();

                            Button[] datesButton = new Button[7];

                            int yPosition = 30;
                            int dateIndex = 0;
                            for (int i = 0; i < 7; i++)
                            {
                                datesButton[i] = new Button(reservationSystem.daysLabel[i].getText().trim());
                                datesButton[i].setTranslateX(40);
                                datesButton[i].setTranslateY(yPosition);
                                datesButton[dateIndex].setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                                datesButton[dateIndex].getStyleClass().add("custom-button");
                                datesButton[dateIndex].setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                                datesButton[dateIndex].setPrefWidth(120);
                                datesButton[dateIndex].setPrefHeight(4);
                                dateIndex++;
                                yPosition += 30;
                            }

                            for (var dateButton : datesButton)
                            {
                                vehiclePane.getChildren().add(dateButton);
                            }

                            for (var dateButton : datesButton)
                            {
                                dateButton.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent)
                                    {
                                        choseDateField.setText(dateButton.getText().trim());
                                        vehicleStage.close();
                                    }
                                });
                            }

                            vehicleStage.setScene(new Scene(vehiclePane, 180, 250));
                            BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
                            Background background = new Background(backgroundFill);
                            vehiclePane.setBackground(background);
                            vehicleStage.show();
                        }
                    });

                    choseHour.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            Stage vehicleStage = new Stage();
                            Pane vehiclePane = new Pane();

                            Button[] hours = new Button[24];

                            int xPosition = 30;
                            int yPosition = 30;
                            for (int i = 1; i <= 24; i++)
                            {
                                hours[i-1] = new Button(Integer.toString(i));
                                hours[i-1].setTranslateX(xPosition);
                                hours[i-1].setTranslateY(yPosition);
                                yPosition += 30;

                                if (i % 4 == 0)
                                {
                                    yPosition = 30;
                                    xPosition += 40;
                                }
                            }

                            for (var hour : hours)
                            {
                                vehiclePane.getChildren().add(hour);
                            }

                            for (var hour : hours)
                            {
                                hour.setOnAction(new EventHandler<ActionEvent>()
                                {
                                    @Override
                                    public void handle(ActionEvent actionEvent)
                                    {
                                        choseHourField.setText(hour.getText().trim()  + ".00");
                                        vehicleStage.close();
                                    }
                                });
                            }

                            vehicleStage.setScene(new Scene(vehiclePane, 400, 200));
                            BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
                            Background background = new Background(backgroundFill);
                            vehiclePane.setBackground(background);
                            vehicleStage.show();
                        }
                    });

                    completeAddTripButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            String busName = choseVehicleField.getText().trim();
                            String startLocation = choseStartLocationField.getText().trim();
                            String endLocation = choseEndLocationField.getText().trim();
                            String date = choseDateField.getText().trim();
                            String hour = choseHourField.getText().trim();

                            boolean doesVehicleExisted = false;
                            boolean doesRouteExisted = false;
                            int correctRouteIndex = 0;
                            int correctVehicleIndex = 0;

                            for (int i = 0; i < vehicleList.size(); i++)
                            {
                                if (vehicleList.get(i).getVehicleId().equals(busName))
                                {
                                    correctVehicleIndex = i;
                                    doesVehicleExisted = true;
                                    break;
                                }
                            }

                            for (int i = 0; i < routeAndDistances.length; i++)
                            {
                                if (startLocation.equals(routeAndDistances[i].getStartingPoint()) && endLocation.equals(routeAndDistances[i].getDestination()))
                                {
                                    correctRouteIndex = i;
                                    doesRouteExisted = true;
                                    break;
                                }
                            }

                            if (doesVehicleExisted && doesRouteExisted)
                            {
                                Trip newTrip = new Trip(routeAndDistances[correctRouteIndex], date, hour, routeAndPrices[correctRouteIndex][2], vehicleList.get(correctVehicleIndex), tripsButton[tripIndex].getText(), companyName);
                                reservationSystem.getCorrectTripList(date).add(newTrip);
                                companysTrips.add(newTrip);
                                System.out.println(date + " " + reservationSystem.getCorrectTripList(date).size());
                            }

                            vehicleTripStage.close();
                        }
                    });

                    vehicleTripStage.setScene(new Scene(vehicleTripPane, 550, 350));
                    vehicleTripStage.show();
                }
            });
        }

    }

    public void deleteTrip()
    {
        Stage deleteTripStage = new Stage();
        Pane deleteTripPane = new Pane();
        String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        Label choseTripLabel = new Label("Trips Already Exist");
        choseTripLabel.setStyle(commonStyle);

        choseTripLabel.setTranslateX(200);
        choseTripLabel.setTranslateY(10);

        deleteTripPane.getChildren().add(choseTripLabel);
        BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        deleteTripPane.setBackground(background);

        Label[] tripNameLabels = new Label[companysTrips.size()];
        Label[] vehicleNameLabels = new Label[companysTrips.size()];
        Label[] hourLabels = new Label[companysTrips.size()];
        Label[] priceLabels = new Label[companysTrips.size()];
        Button[] deleteButtons = new Button[companysTrips.size()];
        HBox[] tripBoxes = new HBox[companysTrips.size()];

        int yPosition = 50;

        for (int i = 0; i < companysTrips.size(); i++)
        {
            tripNameLabels[i] = new Label(companysTrips.get(i).getTripNumber());
            vehicleNameLabels[i] = new Label(companysTrips.get(i).getVehicleName());
            hourLabels[i] = new Label(companysTrips.get(i).getHour());
            priceLabels[i] = new Label(companysTrips.get(i).getPrice());
            deleteButtons[i] = new Button("Delete");

            tripBoxes[i] = new HBox(20, vehicleNameLabels[i], hourLabels[i], priceLabels[i], tripNameLabels[i], deleteButtons[i]);
            tripBoxes[i].setTranslateX(65);
            tripBoxes[i].setTranslateY(yPosition);
            deleteTripPane.getChildren().add(tripBoxes[i]);
            yPosition += 40;
        }

        for (int i = 0; i < companysTrips.size(); i++)
        {
            int currentIndex = i;
            deleteButtons[i].setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {

                    Stage completeDeletionStage = new Stage();
                    Pane completeDeletionPane = new Pane();

                    Button completeButton = new Button("Complete");
                    completeButton.setTranslateX(215);
                    completeButton.setTranslateY(200);

                    Label deletionExplanationLabel = new Label("Complete the Trip Deletion, Enter the User Name and Password");
                    deletionExplanationLabel.setTranslateX(30);
                    deletionExplanationLabel.setTranslateY(30);

                    Label companyNameLabel = new Label("Company Name : ");
                    TextField companyNameField = new TextField("");

                    Label companyPassLabel = new Label("Company Name : ");
                    TextField companyPassField = new TextField("");

                    HBox companyNameBox = new HBox(20, companyNameLabel, companyNameField);
                    companyNameBox.setTranslateX(45);
                    companyNameBox.setTranslateY(75);

                    HBox companyPassBox = new HBox(20, companyPassLabel, companyPassField);
                    companyPassBox.setTranslateX(45);
                    companyPassBox.setTranslateY(125);

                    completeDeletionPane.getChildren().add(companyNameBox);
                    completeDeletionPane.getChildren().add(companyPassBox);
                    completeDeletionPane.getChildren().add(deletionExplanationLabel);
                    completeDeletionPane.getChildren().add(completeButton);

                    completeButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent)
                        {
                            String compName = companyNameField.getText().trim();
                            String compPass = companyPassField.getText().trim();

                            if (userLogin(compName, compPass))
                            {
                                completeTripDeletion(tripNameLabels[currentIndex].getText(), vehicleNameLabels[currentIndex].getText());
                                completeDeletionStage.close();
                                deleteTripStage.close();

                            }
                        }
                    });

                    completeDeletionStage.setScene(new Scene(completeDeletionPane, 450, 350));
                    completeDeletionStage.show();
                }
            });
        }

        deleteTripStage.setScene(new Scene(deleteTripPane, 450, 150));
        deleteTripStage.show();
    }

    public void completeTripDeletion(String tripName, String vehicleID)
    {
        for (var trip : companysTrips)
        {
            if (trip.getTripNumber().equals(tripName) && trip.getVehicleName().equals(vehicleID))
            {
                companysTrips.remove(trip);
                break;
            }
        }

        for (var oneDayTripList : reservationSystem.oneWeekTripList)
        {
            for (var trip : oneDayTripList)
            {
                if (trip.getTripNumber().equals(tripName) && trip.getVehicleName().equals(vehicleID))
                {
                    oneDayTripList.remove(trip);
                    break;
                }
            }
        }
    }

    public void updateLabelVehicleAdd(Vehicle newVehicle)
    {
        vehicleList.add(newVehicle);
        userBuilder.setLength(0);

        userBuilder.append("Vehicles Already Exist\n\n");

        for (var vehicle : vehicleList)
        {
            userBuilder.append(vehicle.getVehicleId() + "  " + " Capacity : " + vehicle.getCapacity()).append("\n");
        }

        vehiclesLabel.setText(userBuilder.toString());
    }

    public void updateLabelVehicleSubtract(int vehicleIndex)
    {
        vehicleList.remove(vehicleIndex);
        userBuilder.setLength(0);

        userBuilder.append("Vehicles Already Exist\n\n");

        for (var vehicle : vehicleList)
        {
            userBuilder.append(vehicle.getVehicleId() + "  " + " Capacity : " + vehicle.getCapacity()).append("\n");
        }

        vehiclesLabel.setText(userBuilder.toString());
    }

    public void increaseBusCount() { busCount++; }
    public void increaseTrainCount() { trainCount++; }
    public void increasePlaneCount() { planeCount++; }
    public void decreaseBusCount() { busCount--; }
    public void decreaseTrainCount() { trainCount--; }
    public void decreasePlaneCount() { planeCount--; }
    public int getBusCount() { return busCount; }
    public int getTrainCount() { return trainCount; }
    public int getPlaneCount() { return planeCount; }
    public String getName(){
        return this.companyName;
    }
    public void setName(String name){
        this.companyName=name;
    }
    public static void main(String[] args) {}
}