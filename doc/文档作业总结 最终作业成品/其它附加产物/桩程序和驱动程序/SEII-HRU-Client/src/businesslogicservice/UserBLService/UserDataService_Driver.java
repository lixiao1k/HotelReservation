package businesslogicservice.UserBLService;

import dataservice.UserDataService;
import po.UserPO;

public class UserDataService_Driver {
	public void drive(UserDataService service){
		service.insert(new UserPO());
	}
}
