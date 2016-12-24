package logic.service.impl.credit;

import java.rmi.RemoteException;

import data.dao.CreditDao;
import po.CreditPO;

public class CreditDao_Driver {
	public void drive(CreditDao service) throws RemoteException{
		service.insert(new CreditPO());
		service.getinfo(0);
		service.getinfo(1);
		service.getinfo(2);
	}
}
