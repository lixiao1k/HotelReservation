package Presentation.MemberUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.service.MemberLogicService;
import logic.service.ServiceFactory;
import resultmessage.MemberResultMessage;
import rmi.RemoteHelper;
import vo.ManageWEBSalerVO;

public class ManageWebSalerController implements Initializable{
	@FXML private TextField searchField;
	@FXML private TextField userName;//�û���
	@FXML private TextField Name;//�ǳ�
	@FXML private TextField password;
	@FXML private Button addBT;
	@FXML private Button commitBT;
	
	private ServiceFactory servicefactory;
	private MemberLogicService memberlogic;
	private ManageWEBSalerVO upwebvo;
	private ManageWEBSalerVO addwebvo;
	public void Search(ActionEvent e)
	{
		String webUser=searchField.getText();
		try {
			upwebvo=memberlogic.getWEBSaler(webUser);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		userName.setText(upwebvo.getUsername());
		Name.setText(upwebvo.getName());
		password.setText(upwebvo.getPassword());
	}
	
	public void Commit()throws IOException
	{
		  String userup=userName.getText();
		  String nameup=Name.getText();
		  String passup=password.getText();
		  boolean empty=userName.getText().equals("")||Name.getText().equals("")||password.getText().equals("");
		  if(empty)
		  {
				Notifications.create().owner(searchField.getScene().getWindow()).title("错误信息").text("填写内容不能为空").showError();

		  }
		  else
		  {
			  upwebvo.setName(nameup);
			  upwebvo.setPassword(passup);
			  upwebvo.setUsername(userup);
			  if(MemberResultMessage.SUCCESS==memberlogic.updateWEBSaler(upwebvo))
			  {
				  Stage clickCheck=new Stage();
				  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheck.fxml"));
				  Scene scene=new Scene(root,275,125);
				  clickCheck.setScene(scene);
				  clickCheck.show();
			  }
			  else if(MemberResultMessage.FAIL_PASSWORDLENGTH==memberlogic.updateWEBSaler(upwebvo))
			  {
					Notifications.create().owner(searchField.getScene().getWindow()).title("错误信息").text("密码格式错误").showError();

			  }
		
		  }
		 
	}
	

	public void AddInfo() throws IOException
	{
		  boolean empty=userName.getText().equals("")||Name.getText().equals("")||password.getText().equals("");

			String useradd=userName.getText();
			String nameadd=Name.getText();
			String passadd=password.getText();
			if(empty)
			{
				Notifications.create().owner(searchField.getScene().getWindow()).title("错误信息").text("填写内容不能为空").showError();
			}
			else
			{
				addwebvo.setName(nameadd);
				addwebvo.setPassword(passadd);
				addwebvo.setUsername(useradd);
				
				if(MemberResultMessage.SUCCESS==memberlogic.addWEBSaler(addwebvo))
				{
					Stage addCheck=new Stage();
					  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/addCheck.fxml"));
					  Scene scene=new Scene(root,275,125);
					  addCheck.setScene(scene);
					  addCheck.show();
				}
				else if(MemberResultMessage.FAIL_PASSWORDLENGTH==memberlogic.addWEBSaler(addwebvo))
				{
					Notifications.create().owner(searchField.getScene().getWindow()).title("错误信息").text("密码格式错误").showError();

				}

			}
	 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(servicefactory==null)
		{
			servicefactory=RemoteHelper.getInstance().getServiceFactory();
		}
		try {
			memberlogic=servicefactory.getMemberLogicService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
