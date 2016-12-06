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
	
	public void Cancel()
	{
		BirthDayTime.clear();
	}
	
	public void Commit() throws IOException
	{
		boolean empty=BirthDayTime.getText().equals("");
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
			String Birthup=BirthDayTime.getText();
			//×°½øManageClientVO
			Stage clickCheck=new Stage();
			  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheck.fxml"));
			  Scene scene=new Scene(root,275,125);
			  clickCheck.setScene(scene);
			  clickCheck.show();
			  BirthDayTime.clear();
		}
	}
	
	
	public String SetBirth()
	{
		String setBirth=BirthDayTime.getText();
		return setBirth;
	}
	
	
}
