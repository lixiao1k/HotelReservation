package Presentation.FeedbackUI;

import java.net.URL;
import java.util.ResourceBundle;

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
		keepPersonInfoController.addPhoneNumber(phoneNumber);
		Stage stage=(Stage)phoneNumberTextField.getScene().getWindow();
		stage.close();
	}
	public void setController(KeepPersonInfoController controller){
		this.keepPersonInfoController=controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
