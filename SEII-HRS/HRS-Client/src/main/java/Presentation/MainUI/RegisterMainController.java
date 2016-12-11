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
			Notifications.create().title("ע��").text("�������û���").showWarning();
			return;
		}
		if(password.equals("")||password==null){
			Notifications.create().title("ע��").text("����������").showWarning();
			return;
		}
		if(username.length()<6||username.length()>16){
			Notifications.create().title("ע��").text("�û�������Ϊ8��15λ��").showWarning();
			return;
		}
		if(password.length()<6||password.length()>16){
			Notifications.create().title("ע��").text("���볤��Ϊ8��15λ��").showWarning();
			return;
		}
		RegisterResultMessage result = serviceFactory.getUserLogicService().register(username, password);
		if(result==RegisterResultMessage.FAIL_USEREXIST){
			Notifications.create().title("ע��").text("�û��Ѵ��ڣ�").showWarning();
			return;
		}
		if(result==RegisterResultMessage.FAIL_PASSWORDLENGTH){
			Notifications.create().title("ע��").text("���볤�Ȳ����ϣ�").showWarning();
			return;
		}
		Notifications.create().title("ע��").text("ע��ɹ���").showConfirm();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Parent root = FXMLLoader.load(getClass().getResource("LoginMainUI.fxml"));
		Scene scene = new Scene(root);
		Stage login = new Stage();
		login.setScene(scene);
		login.setTitle("�Ƶ�Ԥ��ϵͳ��¼");
		login.show();
		Stage register = (Stage) usernameField.getScene().getWindow();
		register.close();
	}
	public void initialize(URL location, ResourceBundle resources) {
		if(serviceFactory==null)
			serviceFactory = RemoteHelper.getInstance().getServiceFactory();
	}

}
