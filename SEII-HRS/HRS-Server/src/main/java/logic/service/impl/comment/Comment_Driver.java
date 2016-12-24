package logic.service.impl.comment;

import java.rmi.RemoteException;

import logic.service.CommentLogicService;
import vo.CommentVO;

public class Comment_Driver {
	public void drive(CommentLogicService service) throws RemoteException{
		service.getHotelInfo(1);
		CommentVO vo = new CommentVO();
		service.review(vo);
		vo.setHotelId(1);
		service.review(vo);
		
	}
}
