package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import resultmessage.LoginResultMessage;
import resultmessage.RegisterResultMessage;

public interface UserLogicService extends Remote{
	public LoginResultMessage login(String username,String password) throws RemoteException;
	public void logout(long userid) throws RemoteException;
	public RegisterResultMessage register(String username,String password) throws RemoteException;
}
