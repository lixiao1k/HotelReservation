package Presentation.UserUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class ManageCVIPController implements Initializable{
	@FXML TextField CompanyName;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ManageClientController my=new ManageClientController();
		String comName=my.getCompany();
		CompanyName.setText(comName);
		
	}

}
