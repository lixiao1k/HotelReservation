package Presentation.MemberUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class RegisterController implements Initializable {
	@FXML TextField nameOfCompanyTextField;
	@FXML DatePicker birthdayDatePicker;
	
	@FXML
	protected void registerbuttonAction(ActionEvent e){
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		final Tooltip tip=new Tooltip();
		tip.setText("个人用户必填！");
		birthdayDatePicker.setTooltip(tip);
		final Tooltip tip1=new Tooltip();
		tip1.setText("企业用户必填，个人用户选填！");
		nameOfCompanyTextField.setTooltip(tip1);
		
	}
}
