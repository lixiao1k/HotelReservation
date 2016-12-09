package Presentation.CreditUI;

import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.service.CreditLogicService;
import rmi.RemoteHelper;

public class RechargeCreditController {
	@FXML TextField userID;
	@FXML TextField money;
	
	CreditLogicService credit;
	
	public void commit()
	{
		long userid=Long.parseLong(userID.getText());
		int delta=Integer.parseInt(money.getText());
		try {
			credit=RemoteHelper.getInstance().getServiceFactory().getCreditLogicService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		credit.excharge(userid, delta);
		System.out.println("提交成功");
	}
	
	public void cancel()
	{
		userID.clear();
		money.clear();
	}
	

}
