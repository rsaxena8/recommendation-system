package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

public class Main extends Application {
	
	@Override
	
	public void start(Stage stage)throws Exception {
		new ViewLoader().loadView(stage, "/View/Login.fxml");
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
