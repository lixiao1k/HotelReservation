package businesslogic.CreditBL;

import dataservice.CreditDataService;
import po.CreditPO;

public class CreditDataService_Driver {
	public void drive(CreditDataService service){
		service.add(1234, 5);
		service.decrease(1234, 5);
		service.insert(new CreditPO());
	}
}
