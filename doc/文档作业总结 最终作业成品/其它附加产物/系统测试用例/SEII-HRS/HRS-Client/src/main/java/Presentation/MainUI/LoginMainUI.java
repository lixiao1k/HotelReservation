package Presentation.MainUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMainUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("LoginMainUI.fxml"));
		Parent root=loader.load();
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("�Ƶ�Ԥ��ϵͳ��¼");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
