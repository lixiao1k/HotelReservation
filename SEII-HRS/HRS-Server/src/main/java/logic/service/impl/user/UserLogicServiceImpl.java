package logic.service.impl.user;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import logic.service.UserLogicService;
import resultmessage.RegisterResultMessage;
import resultmessage.UserResultMessage;
import vo.LoginResultVO;

public class UserLogicServiceImpl extends UnicastRemoteObject implements UserLogicService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6009433617963199447L;
	private UserDO userDO;
	public UserLogicServiceImpl() throws RemoteException{
		userDO=new UserDO(); 
	}
	@Override
	public LoginResultVO login(String username, String password) throws RemoteException {
		return userDO.login(username, password);
	}

	@Override
	public void logout(long userid) throws RemoteException {
		userDO.logout(userid);
	}

	@Override
	public RegisterResultMessage register(String username, String password) throws RemoteException {
		return userDO.register(username, password);
	}
	@Override
	public UserResultMessage changePassword(long userId, String password,String newPassword) throws RemoteException {
		// TODO Auto-generated method stub
		return userDO.changePassword(userId, password, newPassword);
	}

}
