package businesslogic.UserBL;

import businesslogicservice.UserBLService.LoginResultMessage;
import businesslogicservice.UserBLService.RegisterResultMessage;
import businesslogicservice.UserBLService.UserBLService;

public class UserBLService_Stub implements UserBLService{

	@Override
	public LoginResultMessage login(String username, String password) {
		// TODO Auto-generated method stub
		if (username.equals("")||password.equals("")) 
			return LoginResultMessage.FAIL_NOINFO;
		if (username.equals("1234")&&password.equals("123"))
			return LoginResultMessage.SUCCESS;
		else return LoginResultMessage.FAIL_WRONG;
	}

	@Override
	public void logout(long userid) {
		// TODO Auto-generated method stub
		// Search the database and remove UserPO
	}

	@Override
	public RegisterResultMessage register(String username, String password) {
		// TODO Auto-generated method stub
		if (username.equals("1234")) return RegisterResultMessage.FAIL_USEREXIST;
		if (password.length()<8) return RegisterResultMessage.FAIL_PASSWORDLENGTH;
		return RegisterResultMessage.SUCCESS;
	}

}
