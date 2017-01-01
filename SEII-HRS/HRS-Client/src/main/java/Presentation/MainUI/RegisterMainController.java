package Presentation.MainUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.service.ServiceFactory;
import resultmessage.RegisterResultMessage;
import rmi.RemoteHelper;

public class RegisterMainController implements Initializable{
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	private ServiceFactory serviceFactory;
	@FXML 
	protected void save(ActionEvent e) throws IOException{
		String username = usernameField.getText();
		String password = passwordField.getText();
		if(username.equals("")||username==null){
			Notifications.create().title("注册").text("请输入用户名").showWarning();
			return;
		}
		if(password.equals("")||password==null){
			Notifications.create().title("注册").text("请输入密码").showWarning();
			return;
		}
		if(username.length()<6||username.length()>16){
			Notifications.create().title("注册").text("用户名长度为8到15位！").showWarning();
			return;
		}
		if(password.length()<6||password.length()>16){
			Notifications.create().title("注册").text("密码长度为8到15位！").showWarning();
			return;
		}
		RegisterResultMessage result = serviceFactory.getUserLogicService().register(username, password);
		if(result==RegisterResultMessage.FAIL_USEREXIST){
			Notifications.create().title("注册").text("用户已存在！").showWarning();
			return;
		}
		if(result==RegisterResultMessage.FAIL_PASSWORDLENGTH){
			Notifications.create().title("注册").text("密码长度不符合！").showWarning();
			return;
		}
		Notifications.create().title("注册").text("注册成功！").showConfirm();
		Parent root = FXMLLoader.load(getClass().getResource("LoginMainUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("ClientButton.css").toExternalForm());
		Stage login = new Stage();
		login.setScene(scene);
		login.setTitle("酒店预订系统登录");
		login.show();
		Stage register = (Stage) usernameField.getScene().getWindow();
		register.close();
	}
	@FXML
	protected void exit(ActionEvent e){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("LoginMainUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("ClientButton.css").toExternalForm());
			Stage login = new Stage();
			login.setScene(scene);
			login.setTitle("酒店预订系统登录");
			login.initStyle(StageStyle.UNDECORATED);
			login.show();
			((Stage)usernameField.getScene().getWindow()).close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void initialize(URL location, ResourceBundle resources) {
		if(serviceFactory==null)
			serviceFactory = RemoteHelper.getInstance().getServiceFactory();
	}

}
