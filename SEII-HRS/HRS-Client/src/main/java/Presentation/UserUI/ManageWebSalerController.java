package Presentation.UserUI;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManageWebSalerController {
	@FXML private TextField searchField;
	@FXML private TextField userName;//用户名
	@FXML private TextField Name;//昵称
	@FXML private TextField password;
	     
	

	public void Search(ActionEvent e)
	{
		String webUser=searchField.getText();
		//调用Web.getWEBSaler方法   得到WEBSalerVO
		userName.setText("用户");
		Name.setText("昵称");
		password.setText("密码");
	}
	
	public void Commit()throws IOException
	{
		  String userup=userName.getText();
		  String nameup=Name.getText();
		  String passup=password.getText();
		  //将更改后的信息包装为一个ManageWEBSalerVO   
		  boolean empty=userName.getText().equals("")||Name.getText().equals("")||password.getText().equals("");
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
			  Stage clickCheck=new Stage();
			  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheck.fxml"));
			  Scene scene=new Scene(root,275,125);
			  clickCheck.setScene(scene);
			  clickCheck.show();
		  }
		 
	}
	

	public void AddInfo() throws IOException
	{
		  boolean empty=userName.getText().equals("")||Name.getText().equals("")||password.getText().equals("");

			String useradd=userName.getText();
			String nameadd=Name.getText();
			String passadd=password.getText();
			//将信息包装为ManageWEBSalerVO
			if(empty)
			{
				 Stage addCheck=new Stage();
				  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheckFalse.fxml"));
				  Scene scene=new Scene(root,275,125);
				  addCheck.setScene(scene);
				  addCheck.show();
			}
			else
			{
				
				Stage addCheck=new Stage();
				  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/addCheck.fxml"));
				  Scene scene=new Scene(root,275,125);
				  addCheck.setScene(scene);
				  addCheck.show();

			}
	 }
	

}
