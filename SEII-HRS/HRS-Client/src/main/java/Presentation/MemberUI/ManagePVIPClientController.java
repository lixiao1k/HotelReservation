package Presentation.MemberUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import datacontroller.DataController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.service.MemberLogicService;
import resultmessage.MemberResultMessage;
import rmi.RemoteHelper;
import vo.ManageClientVO;

public class ManagePVIPClientController implements Initializable{
	@FXML  private TextField BirthDayTime;
	private ManageClientVO clvo;
	private MemberLogicService memberlogic;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	//	FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Presentation/UserUI/ManageClient1.fxml"));
		try {
			memberlogic=RemoteHelper.getInstance().getServiceFactory().getMemberLogicService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clvo=(ManageClientVO)DataController.getInstance().get("searchClient");
		
		if(clvo.getBirthday()!=null)
		BirthDayTime.setText(clvo.getBirthday().toString());;

		
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
			Notifications.create().owner(BirthDayTime.getScene().getWindow()).title("错误信息").text("填写内容不能为空").showError();

		}
		else
		{
			String Birthup=BirthDayTime.getText();
			
			clvo.setCompanyname(Birthup);
			MemberResultMessage result=memberlogic.updateClient(clvo);
			if(MemberResultMessage.SUCCESS==result)
			{
				Notifications.create().owner(BirthDayTime.getScene().getWindow()).title("提示信息").text("提交成功").show();

				  BirthDayTime.clear();
			}
			else
			{
				Notifications.create().owner(BirthDayTime.getScene().getWindow()).title("错误信息").text("提交失败").showError();

			}
		}
	}
	
	

	
	
}
