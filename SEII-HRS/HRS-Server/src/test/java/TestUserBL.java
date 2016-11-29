import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

import logic.service.UserLogicService;
import logic.service.impl.user.UserLogicServiceImpl;
import resultmessage.LoginResultMessage;
import resultmessage.RegisterResultMessage;

public class TestUserBL {
	@Test
	public void testlogin() throws RemoteException {
		UserLogicService userLogic=new UserLogicServiceImpl();
		LoginResultMessage result=null;
		result=userLogic.login("Admin", "Admin");
		Assert.assertEquals("Login success.", LoginResultMessage.SUCCESS, result);
		result=userLogic.login("Admin", "Admin");
		Assert.assertEquals("Login fail. Already logged.", LoginResultMessage.FAIL_LOGGED, result);
		result=userLogic.login("None", "None");
		Assert.assertEquals("Login fail. No Such User.", LoginResultMessage.FAIL_NOINFO, result);
		userLogic.logout(1);
		result=userLogic.login("Admin", "Adminn");
		Assert.assertEquals("Login fail. Wrong password.", LoginResultMessage.FAIL_WRONG, result);
	}
	
	@Test
	public void testlogout() throws RemoteException {
		UserLogicService userLogic=new UserLogicServiceImpl();
		LoginResultMessage result=null;		
		userLogic.login("Admin", "Admin");
		userLogic.logout(1);
		result=userLogic.login("Admin", "Admin");
		Assert.assertEquals("Logout success and login.", LoginResultMessage.SUCCESS, result);
		userLogic.logout(2);
		result=userLogic.login("Admin", "Admin");
		Assert.assertEquals("Logout fail. Cannot login.", LoginResultMessage.FAIL_LOGGED, result);
	}
	
	@Test
	public void testregister() throws RemoteException {
		UserLogicService userLogic=new UserLogicServiceImpl();
		RegisterResultMessage result=null;
		result=userLogic.register("Mengxin", "Mengxin");
		Assert.assertEquals("Register success.", RegisterResultMessage.SUCCESS, result);
		result=userLogic.register("Mengxinxin", "Mengxinmengxinxin");
		Assert.assertEquals("Register fail. Passwordlength too long.", RegisterResultMessage.FAIL_PASSWORDLENGTH, result);
		result=userLogic.register("Admin", "Admin");
		Assert.assertEquals("Register fail. User exists", RegisterResultMessage.FAIL_USEREXIST, result);
	}
}
