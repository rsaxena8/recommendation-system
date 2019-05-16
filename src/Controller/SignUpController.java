package Controller;

import static Dao.DBConnect.getUserQuery;

import com.sun.prism.Image;

import Dao.DBConnect;
import application.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

	@FXML
    private TextField email;

    @FXML
    private TextField gpa;

    @FXML
    private TextField username;
    
    @FXML
    private PasswordField passcode;

    @FXML
    private TextField anum;
    
    
    @FXML
    private TextField name;
    @FXML
    private TextField lname;
    
    
    @FXML
    private TextField dep;
	
    @FXML
    private Button reg_SignUp;
    
    @FXML
    private Label close;
    
	@FXML
    public void reg_SignUp(ActionEvent e) {
        
		try {
			DBConnect.insertInTable(getUserQuery(anum.getText(), 0, username.getText(), passcode.getText(), name.getText(), lname.getText(), null, dep.getText(), email.getText()));
			ViewLoader.showInfo("Welcome "+username.getText() ," Account creation Successful" );
		} catch (Exception e1) {
			System.out.println("Signup failed "+ e1.getMessage());
		}
    }
	@FXML
    public void close(ActionEvent e) {
        Platform.exit();
    }
}
