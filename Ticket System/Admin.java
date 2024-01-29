import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
public class Admin extends User
{
    protected Button userButton;
    protected Button userLoginButton;
    TextField adminUserNameField = new TextField("");
    TextField adminPasswordField = new TextField("");
    TextField addNewCompanyField = new TextField("");
    TextField deleteCompanyField = new TextField("");
    public Admin()
    {
        adminUserNameField.setPromptText("Enter Admin User Name");
        adminPasswordField.setPromptText("Enter Admin Password");
        addNewCompanyField.setPromptText("Enter new company name ");
        deleteCompanyField.setPromptText("Enter delete company name ");
        userName = "Merve";
        password = "12345";
        userButton = new Button("ADMIN");
        userButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        userButton.getStyleClass().add("custom-button");
        userButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        userButton.setPrefWidth(200);
        userButton.setPrefHeight(40);
        userButton.setMinWidth(400);
        userBuilder = new StringBuilder();
    }

    public void showCompanies(List<Company> companies) {
        companies.forEach(System.out::println);
    }

    public StringBuilder getAdminStageBuilder() { return userBuilder; }
    public Button getAdminButton() { return userButton; }


    public void adminStage(Transport transport,
                           Label companyLabel
    )
    {
        userButton.setOnAction(new EventHandler<ActionEvent>()
                               {
                                   public void handle(ActionEvent e)
                                   {
                                       Stage adminLoginStage = new Stage();
                                       BorderPane adminLoginPane = new BorderPane();
                                       userLoginButton = new Button("LOGIN");
                                       userLoginButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                                       userLoginButton.getStyleClass().add("custom-button");
                                       userLoginButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                                       userLoginButton.setPrefWidth(200);
                                       userLoginButton.setPrefHeight(40);
                                       userLoginButton.setOnAction(event->
                                       {
                                           String adminUserName = adminUserNameField.getText().trim();
                                           String adminPassword= adminPasswordField.getText().trim();

                                           if (((!adminUserName.isEmpty()) && (!adminPassword.isEmpty())) && userLogin(adminUserName,adminPassword))
                                           {
                                               Stage adminStage = new Stage();
                                               BorderPane adminPane = new BorderPane();

                                               Button newCompanyButton = new Button("ADD COMPANY");
                                               newCompanyButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                                               newCompanyButton.getStyleClass().add("custom-button");
                                               newCompanyButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                                               newCompanyButton.setPrefWidth(200);
                                               newCompanyButton.setPrefHeight(40);

                                               Button deleteCompanyButton = new Button("DELETE COMPANY");
                                               deleteCompanyButton.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
                                               deleteCompanyButton.getStyleClass().add("custom-button");
                                               deleteCompanyButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                                               deleteCompanyButton.setPrefWidth(200);
                                               deleteCompanyButton.setPrefHeight(40);

                                               HBox companyBox = new HBox(20, newCompanyButton, deleteCompanyButton);
                                               companyBox.setPadding(new Insets(20, 10, 10, 70));

                                               companyLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
                                               adminPane.setCenter(companyLabel);
                                               adminPane.setBottom(companyBox);

                                               BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
                                               Background background = new Background(backgroundFill);
                                               adminPane.setBackground(background);
                                               adminPane.setCenter(companyLabel);
                                               adminPane.setBottom(companyBox);
                                               adminPane.setAlignment(companyBox, Pos.CENTER);
                                               adminPane.getStyleClass().add("admin-pane");

                                               adminStage.setScene(new Scene(adminPane, 750, 350));
                                               adminStage.show();

                                               newCompanyButton.setOnAction(new EventHandler<ActionEvent>()
                                               {
                                                   @Override
                                                   public void handle(ActionEvent event)
                                                   {
                                                       String textFieldStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
                                                       String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";

                                                       Button addNewCompanyButton = new Button("Add New Company");
                                                       addNewCompanyButton.getStyleClass().add("custom-button");
                                                       addNewCompanyButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                                                       addNewCompanyButton.setPrefWidth(200);
                                                       addNewCompanyButton.setPrefHeight(40);

                                                       Stage addCompanyStage = new Stage();
                                                       Pane addCompanyPane = new Pane();

                                                       Label newCompanyNameLabel = new Label("Company Name You Add :        ");
                                                       newCompanyNameLabel.setStyle(commonStyle);
                                                       TextField newCompanyNameField = new TextField("");
                                                       newCompanyNameField.setPromptText("Company Name");

                                                       Label newCompanyUserNameLabel = new Label("Company User Name You Add: ");
                                                       newCompanyUserNameLabel.setStyle(commonStyle);
                                                       TextField newCompanyUserNameField = new TextField("");
                                                       newCompanyUserNameField.setPromptText("Company User Name");
                                                       newCompanyUserNameField.setStyle(textFieldStyle);

                                                       Label newCompanyPassLabel = new Label("Company Password You Add:    ");
                                                       newCompanyPassLabel.setStyle(commonStyle);
                                                       TextField newCompanyPassField = new TextField("");
                                                       newCompanyPassField.setPromptText("Company Password");
                                                       newCompanyPassField.setStyle(textFieldStyle);

                                                       HBox companyNameBox = new HBox(20, newCompanyNameLabel, newCompanyNameField);
                                                       HBox companyUserNameBox = new HBox(20, newCompanyUserNameLabel, newCompanyUserNameField);
                                                       HBox companyPassBox = new HBox(20, newCompanyPassLabel, newCompanyPassField);

                                                       companyNameBox.setTranslateX(35);
                                                       companyNameBox.setTranslateY(35);
                                                       companyUserNameBox.setTranslateX(35);
                                                       companyUserNameBox.setTranslateY(85);
                                                       companyPassBox.setTranslateX(35);
                                                       companyPassBox.setTranslateY(135);
                                                       addNewCompanyButton.setTranslateX(125);
                                                       addNewCompanyButton.setTranslateY(250);

                                                       BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
                                                       Background background = new Background(backgroundFill);
                                                       addCompanyPane.setBackground(background);
                                                       addCompanyPane.getChildren().add(companyNameBox);
                                                       addCompanyPane.getChildren().add(companyUserNameBox);
                                                       addCompanyPane.getChildren().add(companyPassBox);
                                                       addCompanyPane.getChildren().add(addNewCompanyButton);

                                                       addNewCompanyButton.setOnAction(new EventHandler<ActionEvent>() {
                                                           @Override
                                                           public void handle(ActionEvent actionEvent)
                                                           {
                                                               addNewCompany(newCompanyUserNameField, newCompanyPassField, addNewCompanyField, transport, companyLabel);
                                                               addCompanyStage.close();
                                                           }
                                                       });

                                                       addCompanyStage.setScene(new Scene(addCompanyPane, 500, 500));
                                                       addCompanyStage.show();
                                                   }
                                               });


                                               deleteCompanyButton.setOnAction(new EventHandler<ActionEvent>()
                                               {
                                                   @Override

                                                   public void handle(ActionEvent actionEvent)
                                                   {
                                                       deleteCompany(adminUserNameField, adminPasswordField, transport, companyLabel);
                                                       adminLoginStage.close();
                                                   }
                                               });
                                           }
                                       });

                                       adminLoginPane.setCenter(userLoginButton);
                                       BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
                                       Background background = new Background(backgroundFill);
                                       adminLoginPane.setBackground(background);
                                       HBox adminLoginBox = new HBox(8);
                                       adminLoginBox.setPadding(new Insets(20, 7 , 7, 60));
                                       adminLoginBox.getChildren().addAll(label1, adminUserNameField, label2, adminPasswordField);

                                       adminLoginPane.setTop(adminLoginBox);
                                       Scene adminScene = new Scene(adminLoginPane, 500, 200);
                                       adminLoginStage.setScene(adminScene);
                                       adminLoginStage.show();
                                   }
                               }
        );
    }


    public void addNewCompany(TextField newCompanyUserNameField,
                              TextField newCompanyPassField,
                              TextField addNewCompanyTextField,
                              Transport transport,
                              Label companyLabel
    )
    {

        String newCompanyName = newCompanyUserNameField.getText().trim();
        String newCompanyUserName = newCompanyUserNameField.getText().trim();
        String newCompanyPassword = newCompanyPassField.getText().trim();

        if((newCompanyName != null && !newCompanyName.isEmpty()) && (newCompanyUserName != null && !newCompanyUserName.isEmpty()) && (newCompanyPassword != null && !newCompanyPassword.isEmpty()))
        {
            Company newCompany = new Company (newCompanyName, newCompanyUserName, newCompanyPassword);
            transport.getCompaniesList().add(newCompany);
            getAdminStageBuilder().setLength(0);

            for (var company : transport.getCompaniesList())
            {
                getAdminStageBuilder().append("Company Name: ").append(company.getName()).append("\n");
            }
            addNewCompanyTextField.setText("");
            companyLabel.setText(getAdminStageBuilder().toString());
        }
    }

    public void deleteCompany(TextField adminNameTextField,
                              TextField adminPasswordTextField,
                              Transport transport,
                              Label companyLabel
    )
    {
        String textFieldStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        String commonStyle = "-fx-border-color: #a0a0a0; -fx-border-width: 1px; -fx-padding: 5px;";
        adminNameTextField.setText("");
        adminPasswordTextField.setText("");
        adminPasswordTextField.setStyle(textFieldStyle);
        adminNameTextField.setStyle(textFieldStyle);

        Stage deleteStage = new Stage();
        Pane deletePane = new Pane();

        Button completeDelete = new Button("Delete");
        completeDelete.setStyle("-fx-background-color: #4CAF50; -fx-border-color: black; -fx-border-width: 0.1mm;");
        completeDelete.getStyleClass().add("custom-button");
        completeDelete.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        completeDelete.setPrefWidth(200);
        completeDelete.setPrefHeight(40);
        completeDelete.setTranslateX(125);
        completeDelete.setTranslateY(250);

        Label adminUserNameLabel = new Label("Admin User Name :             ");
        Label adminPasswordLabel = new Label("Admin Password Name :     ");
        Label deletedCompanyLabel = new Label("Company Name To Delete :");
        TextField deleteCompanyField = new TextField("");
        deleteCompanyField.setPromptText("Company Name");

        HBox deletedCompanyBox = new HBox(20, deletedCompanyLabel, deleteCompanyField);
        deletedCompanyBox.setTranslateX(30);
        deletedCompanyBox.setTranslateY(65);

        HBox adminUserNameConfirmBox = new HBox(20, adminUserNameLabel, adminNameTextField);
        adminUserNameConfirmBox.setTranslateX(30);
        adminUserNameConfirmBox.setTranslateY(130);

        HBox adminPasswordConfirmBox = new HBox(20, adminPasswordLabel, adminPasswordTextField);
        adminPasswordConfirmBox.setTranslateX(30);
        adminPasswordConfirmBox.setTranslateY(195);

        BackgroundFill backgroundFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        deletePane.setBackground(background);
        deletePane.getChildren().add(completeDelete);
        deletePane.getChildren().add(deletedCompanyBox);
        deletePane.getChildren().add(adminPasswordConfirmBox);
        deletePane.getChildren().add(adminUserNameConfirmBox);

        completeDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                completeDelete(deleteCompanyField,
                        adminNameTextField,
                        adminPasswordTextField,
                        transport,
                        deleteStage,
                        companyLabel);

                deleteStage.close();
            }
        });

        deleteStage.setScene(new Scene(deletePane, 450, 350));
        deleteStage.show();
    }

    public void completeDelete(TextField deleteCompanyField,
                               TextField adminNameTextField,
                               TextField adminPasswordTextField,
                               Transport transport,
                               Stage deleteStage,
                               Label companyLabel)
    {

        boolean isCompanyExist = false;
        String companyToRemove = deleteCompanyField.getText().trim();
        String adminUserName = adminNameTextField.getText().trim();
        String adminPassword = adminPasswordTextField.getText().trim();

        if (companyToRemove != null)
        {
            for (var company : transport.getCompaniesList())
            {
                if (company.getName().equals(companyToRemove))
                {
                    isCompanyExist = true;
                }
            }
        }

        if (isCompanyExist && userLogin(adminUserName, adminPassword))
        {
            getAdminStageBuilder().append("-Companies Already Exist-");
            for (int i = 0; i < transport.getCompaniesList().size(); i++)
            {
                if (companyToRemove.equals(transport.getCompaniesList().get(i).getName().trim()))
                {
                    getAdminStageBuilder().setLength(0);
                    transport.getCompaniesList().remove(i);

                    for (var company : transport.getCompaniesList())
                    {
                        getAdminStageBuilder().append("Company Name: ").append(company.getName()).append("\n");
                    }

                    deleteCompanyField.setText("");
                    adminPasswordTextField.setText("");
                    adminNameTextField.setText("");
                    companyLabel.setText(getAdminStageBuilder().toString());
                    deleteStage.close();
                }
            }
        }
    }

    public void setAdminStringBuilder(Transport transport)
    {
        userBuilder.append("Companies Already Exist\n\n");

        for (var company : transport.getCompaniesList())
        {
            userBuilder.append("     Company Name: ").append(company.getName()).append("\n");
        }
    }

    public static void main(String[] args) {}
}