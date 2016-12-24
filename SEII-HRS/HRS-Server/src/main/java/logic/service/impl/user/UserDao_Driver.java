package logic.service.impl.user;

import java.rmi.RemoteException;

import data.dao.UserDao;
import po.UserPO;

public class UserDao_Driver {
	public void drive(UserDao service) throws RemoteException{
		service.insert(new UserPO());
		service.getInfo(1);
		service.getInfo(2);
		service.getInfo("111");
		service.getInfo("123");
		service.update(new UserPO());
	}
}
