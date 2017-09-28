package businesslogic.UserBL;

import java.util.List;

import businesslogicservice.UserBLService.LoginResultMessage;
import businesslogicservice.UserBLService.RegisterResultMessage;

public class User {
	private List<UserInfo> users;
	public void setUsers(List<UserInfo> users){
		this.users = users;
	}
	public LoginResultMessage login(String username, String password) {
		if (username.equals("")||password.equals("")) 
			return LoginResultMessage.FAIL_NOINFO;
		for (UserInfo user:users){
			if (user.getUsername().equals(username)&&user.getPassword().equals(password)){
				if (user.getStatus()==1) return LoginResultMessage.FAIL_LOGGED;
				else{
					user.setStatus(1);
					return LoginResultMessage.SUCCESS;
				}
			}
		}
		return LoginResultMessage.FAIL_WRONG;
	}
	public void logout(long userid) {
		for (UserInfo user:users){
			if (user.getId()==userid){
				user.setStatus(0);
				return;
			}
		}
	}
	public RegisterResultMessage register(String username, String password) {
		// TODO Auto-generated method stub
		if (password.length()<=8) return RegisterResultMessage.FAIL_PASSWORDLENGTH;
		for (UserInfo user:users){
			if (user.getUsername().equals(username)){
				return RegisterResultMessage.FAIL_USEREXIST;
			}
		}
		UserInfo newUser = new MockUserInfo(username,password);
		users.add(newUser);
		return RegisterResultMessage.SUCCESS;
	}

}
