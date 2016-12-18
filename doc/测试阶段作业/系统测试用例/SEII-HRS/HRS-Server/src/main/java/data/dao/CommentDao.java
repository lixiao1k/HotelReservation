package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import po.CommentPO;

public interface CommentDao {
	public void insert(CommentPO po);
	public ListWrapper<CommentPO> getUserComment(long userId)throws RemoteException ;
	public ListWrapper<CommentPO> getHotelComment(long hotelId) throws RemoteException;
}
