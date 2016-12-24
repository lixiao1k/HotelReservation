package logic.service.impl.credit;

import java.rmi.RemoteException;

import logic.service.CreditLogicService;
import vo.CommentVO;

public class Credit_Driver {
	public void drive(CreditLogicService service) throws RemoteException{
		service.excharge(1, 0);
		service.excharge(1, 1);
		service.excharge(2, 1);
		service.getInfo(1);
	}
}
