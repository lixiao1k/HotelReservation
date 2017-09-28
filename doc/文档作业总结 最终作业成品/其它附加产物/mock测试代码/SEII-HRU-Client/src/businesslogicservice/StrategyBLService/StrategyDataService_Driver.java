package businesslogicservice.StrategyBLService;

import java.util.List;

import dataservice.StrategyDataService;
import po.StrategyPO;

public class StrategyDataService_Driver {
	public void drive(StrategyDataService service){
		service.create(new StrategyPO());
		service.delete(1234);
		service.change(new StrategyPO());
		StrategyPO po = service.getInfo(1234);
		if (po!=null) System.out.println("StrategyDataService.getInfo SUCCESS");
		List<StrategyPO> list = service.getStrategies();
		if (list!=null) System.out.println("StrategyDataService.getStrategies SUCCESS");
	}
}
