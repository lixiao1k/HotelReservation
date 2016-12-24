package logic.service.impl.comment;

import java.rmi.RemoteException;

import data.dao.CommentDao;
import po.CommentPO;

public class CommentDao_Driver {
	public void drive(CommentDao service) throws RemoteException{
		service.insert(new CommentPO());
		service.getUserComment(0);
		service.getUserComment(1);
		service.getUserComment(2);
		service.getHotelComment(0);
		service.getHotelComment(1);
		service.getHotelComment(2);
	}
}
