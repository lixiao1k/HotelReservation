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
		System.out.println("注锟斤拷晒锟�(锟斤拷锟斤拷锟竭硷拷锟姐还未写锟斤拷锟剿号诧拷锟芥储锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷预锟斤拷锟剿号碉拷录锟介看)");
		Parent root = FXMLLoader.load(getClass().getResource("LoginMainUI.fxml"));
		Scene scene = new Scene(root);
		Stage login = new Stage();
		login.setScene(scene);
		login.setTitle("锟狡碉拷预锟斤拷系统锟斤拷录");
		login.show();
		Stage register = (Stage) usernameField.getScene().getWindow();
		register.close();
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
