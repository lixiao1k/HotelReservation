package Presentation.MainUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterMainController implements Initializable{
	@FXML TextField usernameField;
	@FXML PasswordField passwordField;
	@FXML 
	protected void save(ActionEvent e) throws IOException{
		// save logic
		System.out.println("注册成功(由于逻辑层还未写，账号不存储。。。请用预设账号登录查看)");
		Parent root = FXMLLoader.load(getClass().getResource("LoginMainUI.fxml"));
		Scene scene = new Scene(root);
		Stage login = new Stage();
		login.setScene(scene);
		login.setTitle("酒店预订系统登录");
		login.show();
		Stage register = (Stage) usernameField.getScene().getWindow();
		register.close();
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
