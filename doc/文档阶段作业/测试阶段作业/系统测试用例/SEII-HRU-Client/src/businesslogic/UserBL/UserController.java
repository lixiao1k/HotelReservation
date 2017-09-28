package businesslogic.UserBL;

import java.util.List;

import businesslogicservice.UserBLService.LoginResultMessage;
import businesslogicservice.UserBLService.RegisterResultMessage;
import businesslogicservice.UserBLService.UserBLService;

public class UserController implements UserBLService{
	User userObject = new User();
	public void setUsers(List<UserInfo> users){
		userObject.setUsers(users);
	}
	@Override
	public LoginResultMessage login(String username, String password) {
		return userObject.login(username, password);
	}

	@Override
	public void logout(long userid) {
		userObject.logout(userid);
	}

	@Override
	public RegisterResultMessage register(String username, String password) {
		return userObject.register(username, password);
	}

}
