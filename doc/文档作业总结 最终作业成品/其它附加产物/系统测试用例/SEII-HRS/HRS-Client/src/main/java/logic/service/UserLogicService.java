package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import resultmessage.RegisterResultMessage;
import vo.LoginResultVO;

public interface UserLogicService extends Remote{
	public LoginResultVO login(String username,String password) throws RemoteException;
	public void logout(long userid) throws RemoteException;
	public RegisterResultMessage register(String username,String password) throws RemoteException;
}
