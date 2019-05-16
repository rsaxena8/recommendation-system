package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.DBConnect;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OtherRequestController implements Initializable{

	private  String num = null;
    private  String userName = null;
    public void setAnum(final String anum){
        this.num = anum;
    }

    public void setUserName(final String userName){
        this.userName = userName;
    }
    
    
    @FXML
    private Button back_Other;

    @FXML
    public void back_Other(ActionEvent e) {
        //System.out.println("gchgr");
        openView("/application/Admin.fxml", new Stage(), this.num, this.userName);
    }

    @FXML
    public void Sub_Other(ActionEvent e) {
        try {
			//DBConnect.insertInTable(DBConnect.getLorQuery(this.num, this.userName, ));
		} catch (Exception e1) {
			MainController.showAlert(e1.getMessage(), "LOR Request has been submitted");
		}
    }
	
	
	@FXML
    public void close_other(ActionEvent e) {
        Platform.exit();
    }
	
	
	Stage openView(final String fxmlFile, final Stage stage, String num, String uname) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

            Parent root = (Parent)fxmlLoader.load();
            OtherRequestController controller = fxmlLoader.<OtherRequestController>getController();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
