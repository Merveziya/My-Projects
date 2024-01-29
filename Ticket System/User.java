import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public abstract class User implements ILoginable
{

    Label label1 = new Label("Name: ");
    Label label2 = new Label("Password: ");
    protected String userName;
    protected String password;
    protected boolean login = false;

    protected StringBuilder userBuilder;
    public boolean userLogin(String userName1, String password1)
    {
        if (userName.equals(userName1) && password.equals(password1))
        {
            login=true;
            return true;
        } else {
/*            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Incorrect password !!");
            alert.showAndWait();
            login=false;*/
            return false;
        }
    }

    public boolean login()
    {
        return login;
    }

    //public Button getUserButton() { return userButton; }

    public static void main(String[] args) {}

}