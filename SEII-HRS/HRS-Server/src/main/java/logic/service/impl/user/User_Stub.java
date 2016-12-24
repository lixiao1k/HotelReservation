package logic.service.impl.user;

import java.rmi.RemoteException;

import logic.service.UserLogicService;
import resultmessage.LoginResultMessage;
import resultmessage.RegisterResultMessage;
import resultmessage.UserResultMessage;
import vo.LoginResultVO;

public class User_Stub implements UserLogicService{

	@Override
	public LoginResultVO login(String username, String password) throws RemoteException {
		LoginResultVO result = new LoginResultVO(null, null, 0);
		if(username.equals("111")){
			System.out.println("User.login  :  username.equals(\"111\")|return LoginResultMessage.FAIL_NOINFO");
			result.setLoginResultMessage(LoginResultMessage.FAIL_NOINFO);
			return result;
		}
		if(username.equals("123")){
			System.out.println("User.login  :  username.equals(\"123\")|return LoginResultMessage.FAIL_LOGGED");
			result.setLoginResultMessage(LoginResultMessage.FAIL_LOGGED);
			return result;
		}
		System.out.println("User.login  :  normalInfo|return LoginResultMessage.SUCCESS");
		result.setLoginResultMessage(LoginResultMessage.SUCCESS);
		return result;
	}

	@Override
	public void logout(long userid) throws RemoteException {
		if(userid==1){
			System.out.println("User.logout  :  userid==1|wrong not login!");
		}
		else
			System.out.println("User.logout  :  userid==other number|success!");
	}

	@Override
	public RegisterResultMessage register(String username, String password) throws RemoteException {
		if(username.equals("111")){
			System.out.println("User.register  :  username.equals(\"111\")|return RegisterResultMessage.FAIL_USEREXIST");
			return RegisterResultMessage.FAIL_USEREXIST;
		}
		if(password.length()<=8){
			System.out.println("User.register  :  password.length()<=8|return RegisterResultMessage.FAIL_PASSWORDLENGTH");
			return RegisterResultMessage.FAIL_PASSWORDLENGTH;
		}
		System.out.println("User.register  :  normal info|return RegisterResultMessage.SUCCESS");
		return RegisterResultMessage.SUCCESS;
	}

	@Override
	public UserResultMessage changePassword(long userId, String password, String newPassword) throws RemoteException {
		if(userId==1)
			return UserResultMessage.FAIL_WRONGID;
		if(!password.equals("123"))
			return UserResultMessage.FAIL_WRONGINFO;
		return UserResultMessage.SUCCESS;
	}

}
