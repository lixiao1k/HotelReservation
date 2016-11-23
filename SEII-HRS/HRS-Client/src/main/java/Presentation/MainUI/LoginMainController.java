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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginMainController implements Initializable{
	@FXML TextField usernameField;
	@FXML PasswordField passwordField;
	@FXML 
	protected void register(ActionEvent e) throws IOException{
		Parent register = FXMLLoader.load(getClass().getResource("RegisterMainUI.fxml"));
		Scene scene = new Scene(register);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("ע��");
		stage.show();
		
		Stage login = (Stage) usernameField.getScene().getWindow();
		login.close();
	}
	@FXML
	protected void login(ActionEvent e) throws IOException {
		String username = usernameField.getText();
		String password = passwordField.getText();
		boolean flag = true;
		Parent content = null;
		FXMLLoader loader = new FXMLLoader();
		String title = null;
		if (username.equals("client")&&password.equals("client")){
			loader.setLocation(getClass().getResource("ClientMainUI.fxml"));
			content = FXMLLoader.load(getClass().getClassLoader().getResource("presentation/MemberUI/Keeppersoninfo.fxml"));
			content.getProperties().put("NAME", "PersonInfoPane");
			title = "�Ƶ�Ԥ��ϵͳ  -  �ͻ�";
		}
		else if (username.equals("hotelworker")&&password.equals("hotelworker")){
			loader.setLocation(getClass().getResource("HotelWorkerMainUI.fxml"));
			content = FXMLLoader.load(getClass().getResource("HotelUI/SetHotelInfo.fxml"));
			content.getProperties().put("NAME", "HotelInfoPane");
			title = "�Ƶ�Ԥ��ϵͳ  -  �Ƶ깤����Ա";
		}
		else if (username.equals("websaler")&&password.equals("websaler")){
			loader.setLocation(getClass().getResource("WebSalerMainUI.fxml"));
			content = FXMLLoader.load(getClass().getResource("BrowseUI/BrowseStrategyListUI.fxml"));
			content.getProperties().put("NAME", "browseStrategyList");
			title = "�Ƶ�Ԥ��ϵͳ  -  ��վӪ����Ա";
		}
		else if (username.equals("webmanager")&&password.equals("webmanager")){
			loader.setLocation(getClass().getResource("WebManagerMainUI.fxml"));
			content = FXMLLoader.load(getClass().getResource("HotelUI/SetHotelInfo.fxml"));
			content.getProperties().put("NAME", "HotelInfoPane");
			title = "�Ƶ�Ԥ��ϵͳ  -  ��վ������Ա";
		}
		else {
			System.out.println("�˺Ż��������");
			flag = false;
		}
		if (flag){
			GridPane root = (GridPane) loader.load();
			root.add(content, 2, 1);
			Scene scene = new Scene(root,900,600);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("css/main.css").toExternalForm());
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
			Stage login = (Stage) usernameField.getScene().getWindow();
			login.close();
		}
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
