package presentation;

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
		System.out.println("◊¢≤·≥…π¶(”…”⁄¬ﬂº≠≤„ªπŒ¥–¥£¨’À∫≈≤ª¥Ê¥¢°£°£°£«Î”√‘§…Ë’À∫≈µ«¬º≤Èø¥)");
		Parent root = FXMLLoader.load(getClass().getResource("LoginMainUI.fxml"));
		Scene scene = new Scene(root);
		Stage login = new Stage();
		login.setScene(scene);
		login.setTitle("æ∆µÍ‘§∂©œµÕ≥µ«¬º");
		login.show();
		Stage register = (Stage) usernameField.getScene().getWindow();
		register.close();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
