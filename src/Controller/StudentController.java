package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.DBConnect;
import application.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StudentController implements Initializable {


    private  String num = null;
    private  String userName = null;


    @FXML
    private TextField email;

    @FXML
    private TextField gpa;

    @FXML
    private TextField linkedIn;

    @FXML
    private TextField facebook;



    @FXML
    public void job_closeLbl(ActionEvent e) {
        Platform.exit();
    }


    @FXML
    public void job_SubmitBtn(ActionEvent e) {
			try {
				DBConnect.insertInTable(DBConnect.getJobQuery(this.num, this.userName, email.getText(), Double.parseDouble(gpa.getText()), linkedIn.getText(), facebook.getText()));
				ViewLoader.showInfo("Job request submitted by  "+ this.userName,"Job request submitted successfully" );
			} catch (NumberFormatException e1) {
				MainController.showAlert("Invalid Entries", "Please Enter Valid Entries"  + gpa.getText());
			} catch (Exception e1) {
				MainController.showAlert( "Failed to submit job request", e1.getMessage());
			}
    }


    public void setAnum(final String anum){
        this.num = anum;
    }

    public void setUserName(final String userName){
        this.userName = userName;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub


    }

    @FXML
    public void job_btn(ActionEvent e) {
        openView("/View/JobView.fxml", new Stage(), this.num, this.userName);
    }
    
    
    

    public Stage openStudentView(final Stage stage, String num, String uname) {

        return openView("/View/Admin.fxml", stage,  num,  uname);
    }

    @FXML
    public void lor_btn(ActionEvent e) {

        new LorController().openView("/View/Ms_Bs.fxml", new Stage(), this.num, this.userName);


    }

    @FXML
    public void back_btnMs(ActionEvent e) {
        //System.out.println("gchgr");
        openView("/View/Admin.fxml", new Stage(), this.num, this.userName);
    }

    
  
    
    
    @FXML
    public void job_backBtn(ActionEvent e) {
        //System.out.println("gchgr");
        openView("/View/Admin.fxml", new Stage(), this.num, this.userName );
    }




    @FXML
    public void iit_btn(ActionEvent e) {
    	System.out.println("Other request page working");
    	openView("/View/CollegeJobView.fxml", new Stage(), this.num, this.userName);
    }

    @FXML
    public void other_btn(ActionEvent e) {
    	new OtherRequestController().openView("/View/Others.fxml", new Stage(), this.num, this.userName);
    }

    @FXML
    public void close_MsLbl(ActionEvent e) {
        Platform.exit();
    }



      Stage  openView(final String fxmlFile, final Stage stage, String num, String uname) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

            Parent root = (Parent)fxmlLoader.load();
            StudentController controller = fxmlLoader.<StudentController>getController();
            controller.setAnum(num);
            controller.setUserName(uname);

            Scene scene = new Scene(root);

            // scene.setRoot(root);


            stage.initStyle(StageStyle.UNDECORATED);

            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stage;
    }
      
      @FXML
      public void admin_closeLbl(ActionEvent e) {
          Platform.exit();
      }


}