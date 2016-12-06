package Presentation.UserUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManageCVIPController implements Initializable{
	@FXML TextField CompanyName;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ManageClientController my=new ManageClientController();
		String comName=my.getCompany();
		CompanyName.setText(comName);
		
	}
	
	public void Cancel()
	{
		CompanyName.clear();
	}
	
	public void Commit () throws IOException
	{
		boolean empty=CompanyName.getText().equals("");
		if(empty)
		{
			 Stage clickCheck=new Stage();
			  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheckFalse.fxml"));
			  Scene scene=new Scene(root,275,125);
			  clickCheck.setScene(scene);
			  clickCheck.show();
		}
		else
		{
			String nameup=CompanyName.getText();
			//×°½øManageClientVO
			Stage clickCheck=new Stage();
			  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheck.fxml"));
			  Scene scene=new Scene(root,275,125);
			  clickCheck.setScene(scene);
			  clickCheck.show();
			  CompanyName.clear();
		}
	}

}
