package Presentation.UserUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class ManagePVIPClientController implements Initializable{
	@FXML  private TextField BirthDayTime;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	//	FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Presentation/UserUI/ManageClient1.fxml"));
		ManageClientController my=new ManageClientController();
		String birth=my.getBirth();
		BirthDayTime.setText(birth);
		
	}
	
	public String SetBirth()
	{
		String setBirth=BirthDayTime.getText();
		return setBirth;
	}
	
	
}
