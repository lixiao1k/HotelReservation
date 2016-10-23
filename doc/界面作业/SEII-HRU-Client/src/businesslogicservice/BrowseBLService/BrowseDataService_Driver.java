package businesslogicservice.BrowseBLService;

import java.util.ArrayList;
import java.util.List;

import dataservice.BrowseDataService;
import po.BrowsePO;

public class BrowseDataService_Driver {
	public void drive(BrowseDataService service){
		List<BrowsePO> list = service.getInfo(1234);
		if (list!=null) System.out.println("BrowseDataService.getInfo SUCCESS");
		service.clear(1234);
		service.insert(new BrowsePO());
	}
}
