package Controller;

import Dao.DBConnect;
import Dao.TestRecords;
import application.ViewLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static Dao.DBConnect.getUserQuery;


public class MainController implements Initializable {
    @FXML
    private Label labelSI;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPass;

    @FXML
    private Button login_cont;

    @FXML
    private Button fpButton;

    @FXML
    private Button signUp_cont;
    @FXML
    private Button close_cont;
    static ArrayList<List<String>> array = new ArrayList<>();

    @FXML
    public void onClick_Login(ActionEvent e) {
        final String text = txtUser.getText();
        final String pass = txtPass.getText();
        if ("".equals(text) || "".equals(pass)) {
            showAlert("Username or Password missing", "Please enter valid user and pwd");
        }
        final TestRecords tr = invalidUser(text, pass);
        if (tr == null) {
            showAlert("User Authentication Failure", "User and Password don't match");
        } else {
            // spin whatever view
            if (tr.isAdmin()) {
            	new ViewLoader().loadView(new Stage(), "/View/AdminView.fxml");
            } else {
                final StudentController studentController = new StudentController();
                studentController.setAnum(tr.getNum());
                studentController.setUserName(tr.getUserName());
                studentController.openStudentView(new Stage(), tr.getNum(), tr.getUserName());
            }
        }

    }

    private TestRecords invalidUser(final String username, final String password) {
    	try {
			return DBConnect.login(DBConnect.loginQuery(username, password));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			showAlert("Internal Error", "Please retry");
			return null;
		}
    }

    public static void showAlert(final String title, final String rec) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(rec);
        alert.showAndWait();
    }

    Scanner txtUser_inp = new Scanner(System.in);

    public MainController() throws Exception {
        DBConnect.createTable(DBConnect.TableType.JOB);
        DBConnect.createTable(DBConnect.TableType.USER);
        DBConnect.createTable(DBConnect.TableType.LOR);
        FileReader.readRec();
        System.out.println("Loaded " + this.getClass().getSimpleName());
    }

    @FXML
    public void onClick_SignUp(ActionEvent e) {
    	new ViewLoader().loadView(new Stage(), "/View/SignUp.fxml");
    }

    @FXML
    public void onClick_close(ActionEvent e) {
        Platform.exit();
    }

    @FXML
    public void onClick_fpButton(ActionEvent e) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


    public static class FileReader {
        public static List<TestRecords> TEST_RECORDS = new ArrayList<>();

        static ArrayList<List<String>> array = new ArrayList<>();

        public static void readRec() {
            BufferedReader fileread;
            try {
                String line = "";
                String csvSplit = ",";

                fileread = new BufferedReader(new java.io.FileReader("./UserDB.csv"));

                try {
                    while ((line = fileread.readLine()) != null) {
                        String[] list1 = line.split(csvSplit);
                        List<String> list = Arrays.asList(list1);
                        array.add(list);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            processData();
        }

        static void processData() {
            for (List<String> rowData : array) {
                final TestRecords testRecords = new TestRecords(rowData.get(0), rowData.get(1), rowData.get(2), rowData.get(3));
                TEST_RECORDS.add(testRecords);
                try {
					DBConnect.insertInTable(getUserQuery(testRecords.getNum(), testRecords.getIsAdmin(), testRecords.getUserName(), testRecords.getPassword(), null, null, null, null, null));
				} catch (Exception e) {
					//System.out.println(testRecords + "already exists");
				}
            }
        }
    }
}