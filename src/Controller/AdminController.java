package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.DBConnect;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminController implements Initializable {

	
	@FXML
    private Button Job_req;

	@FXML
    private TextField job;
	@FXML
    private TextField msBs;
	@FXML
    private TextField users;
	
	
    @FXML
    private Button he_req;

    @FXML
    private Button user_req;
    
    @FXML
    private Button admin_closeLbl;
   
   
    @FXML
    public void admin_closeLbl(ActionEvent e) {
        Platform.exit();
    }
    
    @FXML
    public void he_req(ActionEvent e) {
    	try {
			int a= DBConnect.count(DBConnect.countQuery("ragi_lor1"));
			users.setText("");
	    	job.setText( "");
	    	msBs.setText(Integer.toString(a));
		} catch (Exception e1) {
			MainController.showAlert("Failed to get count", e1.getMessage());
		}
    }
    
    @FXML
    public void user_req(ActionEvent e) {
    	try {
			int a= DBConnect.count(DBConnect.countQuery("ragi_users2"));
			msBs.setText("");
	    	job.setText( "");
	    	users.setText(Integer.toString(a));
		} catch (Exception e1) {
			MainController.showAlert("Failed to get count", e1.getMessage());
		}
    	
    }

    
    
    @FXML
    public void Job_req(ActionEvent e) {
    	try {
			int a= DBConnect.count(DBConnect.countQuery("ragi_job2"));
			msBs.setText("");
	    	users.setText( "");
	    	job.setText(Integer.toString(a));
		} catch (Exception e1) {
			MainController.showAlert("Failed to get count", e1.getMessage());
		}
    	
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
