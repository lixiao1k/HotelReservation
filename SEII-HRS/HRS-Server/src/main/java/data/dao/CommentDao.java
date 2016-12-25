package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import po.CommentPO;

public interface CommentDao {
	/**
	 * ��������
	 * @param po
	 */
	public void insert(CommentPO po);
	
	/**
	 * ��ȡ�û�������
	 * @param userId
	 * @return
	 * �û������������б�
	 * @throws RemoteException
	 */
	public ListWrapper<CommentPO> getUserComment(long userId)throws RemoteException ;
	/**
	 * ��ȡ�Ƶ������
	 * @param hotelId
	 * @return
	 * �Ƶ�����������б�
	 * @throws RemoteException
	 */
	public ListWrapper<CommentPO> getHotelComment(long hotelId) throws RemoteException;
}
