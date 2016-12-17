package logic.service.impl.credit;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import info.ListWrapper;
import logic.service.CreditLogicService;
import resultmessage.CreditResultMessage;
import vo.CreditVO;

public class CreditLogicServiceImpl extends UnicastRemoteObject  implements CreditLogicService{
	/**
	 * 
	 */
	private static final long serialVersionUID = -856479585123573192L;
	CreditDO creditDO;
	public CreditLogicServiceImpl() throws RemoteException {
		creditDO=new CreditDO();
	}
	@Override
	public ListWrapper<CreditVO> getInfo(long userId) throws RemoteException {
		return creditDO.getInfo(userId);
	}

	@Override
	public CreditResultMessage excharge(long userId, int delta) throws RemoteException{
		return creditDO.excharge(userId, delta);
	}
	
}
