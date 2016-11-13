package presentation.MemberUI;

import businesslogicservice.MemberBLService.MemberBLService;
import businesslogicservice.MemberBLService.MemberResultMessage;
import vo.UserVO;

public class MemberBLService_Driver {
	public void drive(MemberBLService service){
		MemberResultMessage result = service.getInfo(1234);
		if (result==MemberResultMessage.SUCCESS) System.out.println("MemberBLService.getInfo SUCCESS");
		result = service.cancel(new UserVO("1234", "123"));
		if (result==MemberResultMessage.FAIL) System.out.println("MemberBLService.cancel FAIL");
		result = service.registerVIP(new UserVO("1234", "123"));
		if (result==MemberResultMessage.SUCCESS) System.out.println("MemberBLService.registerVIP SUCCESS");		
		result = service.registerVIP(new UserVO("123", "123"));
		if (result==MemberResultMessage.FAIL) System.out.println("MemberBLService.registerVIP FAIL");	
		
	}
}
