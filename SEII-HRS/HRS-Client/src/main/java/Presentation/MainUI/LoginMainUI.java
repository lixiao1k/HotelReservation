package Presentation.MainUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMainUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root=FXMLLoader.load(getClass().getResource("LoginMainUI.fxml"));
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("酒店预订系统登录");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
