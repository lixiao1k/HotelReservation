package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import list.CreditList;
import resultmessage.CreditResultMessage;
import vo.CreditVO;

public interface CreditLogicService extends Remote{
	public CreditList getInfo(long userId) throws RemoteException;
	public CreditResultMessage update(long userId,int value) throws RemoteException;
	public CreditResultMessage insert(CreditVO vo) throws RemoteException;
}
