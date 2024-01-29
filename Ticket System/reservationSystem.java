import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class reservationSystem extends Application
{
    Admin admin = new Admin();
    Transport transport = new Transport();
    Search search = new Search();
    static public List<List<Trip>> oneWeekTripList;

    static Label[] daysLabel = {   new Label("4-December"),
            new Label("5-December"),
            new Label("6-December"),
            new Label("7-December"),
            new Label("8-December"),
            new Label("9-December"),
            new Label("10-December") };

    public void initTripNestedList()
    {
        oneWeekTripList = new ArrayList<>();

        for (int i = 0; i < 7; i++)
        {
            oneWeekTripList.add(new ArrayList<>());
        }
    }

    static public List<Trip> getCorrectTripList(String date)
    {
        if (date.equals("4-December"))  { return oneWeekTripList.get(0); }
        else if (date.equals("5-December")) { return oneWeekTripList.get(1); }
        else if (date.equals("6-December")) { return oneWeekTripList.get(2); }
        else if (date.equals("7-December")) { return oneWeekTripList.get(3); }
        else if (date.equals("8-December")) { return oneWeekTripList.get(4); }
        else if (date.equals("9-December")) { return oneWeekTripList.get(5); }
        else if (date.equals("10-December")) { return oneWeekTripList.get(6); }

        return new ArrayList<>();
    }


    public void start(Stage primaryStage)
    {
        TextField companyNameTextField = new TextField("");
        TextField companyPasswordTextField = new TextField("");

        transport.initCompaniesList();
        initTripNestedList();
        admin.setAdminStringBuilder(transport);

        Label adminStageLabel = new Label(admin.getAdminStageBuilder().toString());

        BorderPane pane = new BorderPane();
        pane.setTop(admin.getAdminButton());
        pane.setLeft(transport.getCompanyButton());
        pane.setBottom(search.getSearchTicketButton());

        admin.adminStage(transport, adminStageLabel);
        transport.companiesStages(companyNameTextField, companyPasswordTextField);
        search.searchStage(daysLabel, transport);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}