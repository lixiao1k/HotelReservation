package businesslogicservice.OrderBLService;

import dataservice.OrderDataService;
import po.OrderPO;

public class OrderDataService_Driver {
	public void drive(OrderDataService service){
		service.insert(new OrderPO());
		service.changeStatus(new OrderPO());
		OrderPO po = service.getInfo(1234);
		if (po!=null) System.out.println("OrderDataService.getInfo SUCCESS");
	}
}
