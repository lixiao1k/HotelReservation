package Presentation.UserUI;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManageWebSalerController {
	@FXML private TextField searchField;
	@FXML private TextField userName;//�û���
	@FXML private TextField Name;//�ǳ�
	@FXML private TextField password;
	@FXML private Button addBT;
	@FXML private Button commitBT;
	

	public void Search(ActionEvent e)
	{
		String webUser=searchField.getText();
		//����Web.getWEBSaler����   �õ�WEBSalerVO
		userName.setText("�û�");
		Name.setText("�ǳ�");
		password.setText("����");
	}
	
	public void Commit()throws IOException
	{
		  String userup=userName.getText();
		  String nameup=Name.getText();
		  String passup=password.getText();
		  //�����ĺ����Ϣ��װΪһ��ManageWEBSalerVO   
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
			//����Ϣ��װΪManageWEBSalerVO
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
