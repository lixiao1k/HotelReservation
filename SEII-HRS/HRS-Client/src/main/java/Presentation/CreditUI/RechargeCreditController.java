package Presentation.CreditUI;

import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.service.CreditLogicService;
import resultmessage.CreditResultMessage;
import rmi.RemoteHelper;

public class RechargeCreditController {
	@FXML TextField userID;
	@FXML TextField money;
	
private	CreditLogicService credit;
private CreditResultMessage result;
	public void commit() throws RemoteException
	{
		long userid=Long.parseLong(userID.getText());
		int delta=Integer.parseInt(money.getText());
		try {
			credit=RemoteHelper.getInstance().getServiceFactory().getCreditLogicService();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	if(	credit.excharge(userid, delta)==CreditResultMessage.SUCCESS)
	{
		
	}
	else if(credit.excharge(userid, delta)==CreditResultMessage.FAIL)
	{
		
	}
	else if(credit.excharge(userid, delta)==CreditResultMessage.FAIL_LESSTHANZERO)
	{
		
	}
	
	}
	
	public void cancel()
	{
		userID.clear();
		money.clear();
	}
	

}
