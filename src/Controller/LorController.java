package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Dao.DBConnect;
import application.ViewLoader;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LorController implements Initializable {

    private  String num = null;
    private  String userName = null;


    @FXML
    private TextField course1;

    @FXML
    private TextField course2;

    @FXML
    private TextField course3;

    @FXML
    private TextField p1;

    @FXML
    private TextField p2;

    @FXML
    private TextField p3;
    @FXML
    public ComboBox<String> level_combobox = new ComboBox<>() ;
    ObservableList<String> list0 = FXCollections.observableArrayList("Bachelors", "Masters");
    @FXML
    public ComboBox<String> term1_combobox = new ComboBox<>();
    ObservableList<String> list1 = FXCollections.observableArrayList("Sem1", "Sem2", "Sem3", "Sem4");
    @FXML
    public ComboBox<String> term2_combobox = new ComboBox<>();
    ObservableList<String> list2 = FXCollections.observableArrayList("Sem1", "Sem2", "Sem3", "Sem4");
    @FXML
    public ComboBox<String> term3_combobox = new ComboBox<>();
    ObservableList<String> list3 = FXCollections.observableArrayList("Sem1", "Sem2", "Sem3", "Sem4");

    public void setAnum(final String anum){
        this.num = anum;
    }

    public void setUserName(final String userName){
        this.userName = userName;
    }

    @FXML
    public void job_closeLbl(ActionEvent e) {
        Platform.exit();
    }


    @FXML
    public void job_SubmitBtn(ActionEvent e) {
        try {
			DBConnect.insertInTable(DBConnect.getLorQuery(this.num, this.userName, level_combobox.getValue(), course1.getText(), course2.getText(), course3.getText(), p1.getText(), p2.getText(), p3.getText(), term1_combobox.getValue(), term2_combobox.getValue(), term3_combobox.getValue()));
			ViewLoader.showInfo("LOR submitted by  "+ this.userName,"LOR submitted successfully" );
        } catch (Exception e1) {
			MainController.showAlert("Failed to submit LOR", e1.getMessage());
		}
    }

    @FXML
    public void job_backBtn(ActionEvent e) {
    	new ViewLoader().loadView(new Stage(), "/View/Admin.fxml");
    }

    Stage openView(final String fxmlFile, final Stage stage, String num, String uname) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

            Parent root = (Parent)fxmlLoader.load();
            LorController controller = fxmlLoader.<LorController>getController();
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		level_combobox.setItems(list0);
		term1_combobox.setItems(list1);
		term2_combobox.setItems(list2);
		term3_combobox.setItems(list3);
	}
}