package presentation.UserUI;

import businesslogicservice.UserBLService.LoginResultMessage;
import businesslogicservice.UserBLService.RegisterResultMessage;
import businesslogicservice.UserBLService.UserBLService;

public class UserBLService_Driver {
	public void drive(UserBLService service){
		LoginResultMessage result = service.login("1234", "123");
		if (result==LoginResultMessage.SUCCESS) System.out.println("UserBLService.login SUCCESS");
		result = service.login("", "");
		if (result==LoginResultMessage.FAIL_NOINFO) System.out.println("UserBLService.login FAIL_NOINFO");
		result = service.login("1234", "23");
		if (result==LoginResultMessage.FAIL_WRONG) System.out.println("UserBLService.login PASSWORD WRONG");
		RegisterResultMessage result2 = service.register("1234", "123");
		if (result2==RegisterResultMessage.FAIL_USEREXIST) System.out.println("UserBLService.register USEREXIST");
		result2 = service.register("123", "123");
		if (result2==RegisterResultMessage.SUCCESS) System.out.println("UserBLService.register SUCCESS");
	}
}
