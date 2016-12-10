package logic.service.impl.credit;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import info.ListWrapper;
import logic.service.CreditLogicService;
import resultmessage.CreditResultMessage;
import vo.CreditVO;

public class CreditLogicServiceImpl extends UnicastRemoteObject  implements CreditLogicService{
	CreditDO creditDO;
	public CreditLogicServiceImpl() throws RemoteException {
		creditDO=new CreditDO();
	}
	@Override
	public ListWrapper<CreditVO> getInfo(long userId) throws RemoteException {
		return creditDO.getInfo(userId);
	}

	@Override
	public CreditResultMessage excharge(long userId, int delta) {
		return creditDO.excharge(userId, delta);
	}
	
}
