//package Controller;
//
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import Dao.DBConnect;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//public class CampusController implements Initializable{
//
//	
//	
//	
//	@FXML
//    private TextField email;
//	
//	@FXML
//    private TextField position;
//	@FXML
//    private Button submit;
//	@FXML
//    private Button close;
//	@FXML
//    private Button back_Other;
//
//    @FXML
//    public void back(ActionEvent e) {
//        //System.out.println("gchgr");
//        openView("/application/Admin.fxml", new Stage(), this.num, this.userName);
//    }
//	
//    @FXML
//    public void close(ActionEvent e) {
//       Platform.exit();
//    }
//	
//    @FXML
//    public void submit(ActionEvent e) {
//        try {
//			DBConnect.insertInTable(DBConnect.getOtherQuery(this.num, this.userName, ));
//		} catch (Exception e1) {
//			MainController.showAlert(e1.getMessage(), "LOR Request has been submitted");
//		}
//    }
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	
//	
//	
//	
//	
//	
//}
