package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import po.CommentPO;

public interface CommentDao {
	/**
	 * 插入评论
	 * @param po
	 */
	public void insert(CommentPO po);
	
	/**
	 * 获取用户的评论
	 * @param userId
	 * @return
	 * 用户关联的评论列表
	 * @throws RemoteException
	 */
	public ListWrapper<CommentPO> getUserComment(long userId)throws RemoteException ;
	/**
	 * 获取酒店的评论
	 * @param hotelId
	 * @return
	 * 酒店关联的评论列表
	 * @throws RemoteException
	 */
	public ListWrapper<CommentPO> getHotelComment(long hotelId) throws RemoteException;
}
