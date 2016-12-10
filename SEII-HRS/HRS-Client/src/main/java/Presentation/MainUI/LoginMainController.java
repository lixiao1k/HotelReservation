package Presentation.MainUI;

import java.awt.GraphicsDevice;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import Presentation.MemberUI.KeepPersonInfoController;
import datacontroller.DataController;
import info.UserType;
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
import resultmessage.LoginResultMessage;
import test.TestLoginResultVO;
import vo.LoginResultVO;

public class LoginMainController implements Initializable{
	@FXML TextField usernameField;
	@FXML PasswordField passwordField;
	long userid;
	String username;
	String password;
	@FXML 
	protected void register(ActionEvent e) throws IOException{ 
		Parent register =FXMLLoader.load(getClass().getResource("RegisterMainUI.fxml"));
//		Parent register= FXMLLoader.load(getClass().getResource("RegisterMainUI.fxml"));
		Scene scene =new Scene(register);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Register");
		stage.show();
		Stage login = (Stage) usernameField.getScene().getWindow();
		login.close();
	}
	@FXML
	protected void login(ActionEvent e) throws IOException {
		username = usernameField.getText();
	    password = passwordField.getText();
		boolean flag = true;
		Parent content = null;
//		FXMLLoader loader = new FXMLLoader();
		String title = null;
		LoginResultVO result = null;
//		try{
//			UserLogicService loginservice = null;
//		    result =loginservice.login(username, password);
//		    userid=result.getUserID();
//		    }catch(NullPointerException e1){
//		    	e1.printStackTrace();
//		    }
		TestLoginResultVO resultVO= new TestLoginResultVO();
		result=resultVO.returnResultVO();
		
		
		if (result.getResultMessage()==LoginResultMessage.SUCCESS&&result.getUserType()==UserType.CLIENT){
			DataController.getInstance().put("UserId", (long)1);
			FXMLLoader loader=new FXMLLoader(getClass().getResource("ClientMainUI.fxml"));
			content = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/MemberUI/KeepPersonInfo.fxml"));
			content.getProperties().put("NAME", "KeepPersonInfoPane");
			title = "HRS-Client";
			GridPane root = (GridPane) loader.load();
			root.add(content, 2, 1);
			Scene scene = new Scene(root,900,600);
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
			Stage login = (Stage) usernameField.getScene().getWindow();
			login.close();
		}
		
		
		else if (result.getResultMessage()==LoginResultMessage.SUCCESS&&result.getUserType()==UserType.HOTEL_WORKER){
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(getClass().getResource("HotelWorkerMainUI.fxml"));
			content = FXMLLoader.load(getClass().getResource("HotelUI/SetHotelInfo.fxml"));
			content.getProperties().put("NAME", "HotelInfoPane");
			title = "HRS-HotelWorker";
			GridPane root = (GridPane) loader.load();
			root.add(content, 2, 1);
			Scene scene = new Scene(root,900,600);
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
			Stage login = (Stage) usernameField.getScene().getWindow();
			login.close();
		}
		else if (result.getResultMessage()==LoginResultMessage.SUCCESS
				&&result.getUserType()==UserType.WEB_SALER){
			FXMLLoader loader =new FXMLLoader();
			loader.setLocation(getClass().getResource("WebSalerMainUI.fxml"));
			content = FXMLLoader.load(getClass().getResource("BrowseUI/BrowseStrategyListUI.fxml"));
			content.getProperties().put("NAME", "browseStrategyList");
			title = "HRS-WebSaler";
			GridPane root = (GridPane) loader.load();
		    root.add(content, 2, 1);
			Scene scene = new Scene(root,900,600);
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
			Stage login = (Stage) usernameField.getScene().getWindow();
			login.close();
		}
		else if (result.getResultMessage()==LoginResultMessage.SUCCESS
				&&result.getUserType()==UserType.WEB_MANAGER){
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(getClass().getResource("WebManagerMainUI.fxml"));
			content = FXMLLoader.load(getClass().getResource("HotelUI/SetHotelInfo.fxml"));
			content.getProperties().put("NAME", "HotelInfoPane");
			title = "HRS-WebManager";
			GridPane root = (GridPane) loader.load();
		    root.add(content, 2, 1);
			Scene scene = new Scene(root,900,600);
			Stage stage = new Stage();
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
			Stage login = (Stage) usernameField.getScene().getWindow();
			login.close();
		}
		else{
			JOptionPane.showMessageDialog(null, "Accout or Password Error");
			System.out.println("Accout or Password Error!");
			flag = false;
		}
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
