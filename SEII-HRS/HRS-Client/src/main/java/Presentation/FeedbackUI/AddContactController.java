package Presentation.FeedbackUI;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import Presentation.MemberUI.KeepPersonInfoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddContactController implements Initializable {
	@FXML TextField phoneNumberTextField;
	private KeepPersonInfoController keepPersonInfoController;
	@FXML 
	protected void addPhoneNumber(ActionEvent e){
		String phoneNumber=phoneNumberTextField.getText();
		if(phoneNumber!=null){
		keepPersonInfoController.addPhoneNumber(phoneNumber);
		Stage stage=(Stage)phoneNumberTextField.getScene().getWindow();
		stage.close();
		}else{
			Notifications.create().title("无对象").text("请输入电话号码").showWarning();
		}
	}
	public void setController(KeepPersonInfoController controller){
		this.keepPersonInfoController=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
