package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewLoader {
	public void loadView(Stage stage, String file) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(file));
			Scene scene = new Scene(root);
		//	scene.setRoot(root);
			

			
			stage.initStyle(StageStyle.UNDECORATED);
			
			scene.getStylesheets().add(getClass().getResource("/View/application.css").toExternalForm());
			stage.setScene(scene);
			
			stage.show();
		} catch(Exception e) {
			System.out.println("Error loading view " + file + e.getMessage());
		}
	}
	
    public static void showInfo(final String header, final String content) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(content);
    	alert.setHeaderText(header);
    	alert.showAndWait();
    }
	}
