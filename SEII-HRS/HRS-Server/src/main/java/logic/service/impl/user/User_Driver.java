package logic.service.impl.user;

import java.rmi.RemoteException;

import logic.service.UserLogicService;

public class User_Driver {
	public void drive(UserLogicService service) throws RemoteException{
		service.login("111", null);
		service.login("123", null);
		service.login("1", null);
		service.logout(1);
		service.logout(2);
		service.register("111", null);
		service.register("123", "111");
		service.register("123", "11111111111");
		service.changePassword(1, "1", "1");
		service.changePassword(2, "1", "1");
		service.changePassword(2, "123", "1");
		
	}
}
