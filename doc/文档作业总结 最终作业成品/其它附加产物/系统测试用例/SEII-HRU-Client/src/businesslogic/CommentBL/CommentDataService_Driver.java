package businesslogic.CommentBL;

import dataservice.CommentDataService;
import po.CommentPO;

public class CommentDataService_Driver {
	public void drive(CommentDataService service){
		service.insert(new CommentPO());
		CommentPO po = service.getInfo(1234);
		if(po!=null) System.out.println("CommentDataService.getInfo SUCCESS");
	}
}
