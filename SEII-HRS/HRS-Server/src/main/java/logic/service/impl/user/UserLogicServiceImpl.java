package logic.service.impl.user;

import java.rmi.RemoteException;

import logic.service.UserLogicService;
import resultmessage.LoginResultMessage;
import resultmessage.RegisterResultMessage;

public class UserLogicServiceImpl implements UserLogicService{
	private UserDO userDO;
	public UserLogicServiceImpl() {
		userDO=new UserDO(); 
	}
	@Override
	public LoginResultMessage login(String username, String password) throws RemoteException {
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

}
