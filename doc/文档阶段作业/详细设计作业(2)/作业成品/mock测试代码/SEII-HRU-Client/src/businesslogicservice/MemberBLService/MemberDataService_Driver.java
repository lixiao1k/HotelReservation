package businesslogicservice.MemberBLService;

import dataservice.MemberDataService;
import po.MemberPO;

public class MemberDataService_Driver {
	public void driver(MemberDataService service){
		service.cancel(new MemberPO());
		service.changeInfo(new MemberPO());
		service.insert(new MemberPO());
		MemberPO po = service.getInfo(1234);
		if (po!=null) System.out.println("MemberDataService.getInfo SUCCESS");
	}
}
