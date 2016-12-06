package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.CreditResultMessage;
import vo.CreditVO;

public interface CreditLogicService extends Remote{
	public CreditResultMessage excharge(long userId, int delta);
	public ListWrapper<CreditVO> getInfo(long userId) throws RemoteException;
}
