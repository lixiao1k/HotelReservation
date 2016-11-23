package presentation.commentUI;

import java.util.List;

import businesslogicservice.CommentBLService.CommentBLService;
import businesslogicservice.CommentBLService.CommentResultMessage;
import vo.CommentVO;

public class CommentBLService_Driver {
	public void drive(CommentBLService service){
		List list = service.getInfo(1234);
		if (list!=null) System.out.println("CommentBLService.getInfo SUCCESS");
		CommentResultMessage result = service.review(new CommentVO());
		if (result==CommentResultMessage.SUCCESS) System.out.println("CommentBLService.review SUCCESS");
	}
}
