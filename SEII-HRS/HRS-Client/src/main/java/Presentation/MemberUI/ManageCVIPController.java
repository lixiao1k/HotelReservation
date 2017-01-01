package Presentation.MemberUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
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

public class ManageCVIPController implements Initializable{
	@FXML private TextField CompanyName;
	
		private ManageClientVO clvo;
		private MemberLogicService memberlogic;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			memberlogic=RemoteHelper.getInstance().getServiceFactory().getMemberLogicService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clvo=(ManageClientVO)DataController.getInstance().get("searchClient");
		CompanyName.setText(clvo.getCompanyname());
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
			Notifications.create().owner(CompanyName.getScene().getWindow()).title("错误信息").text("信息不能为空").showError();

		}
		else
		{
			String nameup=CompanyName.getText();
			clvo.setCompanyname(nameup);
			MemberResultMessage result=memberlogic.updateClient(clvo);
			if(MemberResultMessage.SUCCESS==result)
			{
				Notifications.create().owner(CompanyName.getScene().getWindow()).title("提示信息").text("成功").show();

				  CompanyName.clear();
			}
			else
			{
				Notifications.create().owner(CompanyName.getScene().getWindow()).title("错误信息").text("未知错误").showError();

			}

		}
	}

}
