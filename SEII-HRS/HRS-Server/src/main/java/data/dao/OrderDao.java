package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import info.OrderStatus;
import po.OrderPO;

public interface OrderDao {
	/**
	 * ���¶���
	 * @param po
	 */
	public void update(OrderPO po);
	/**
	 * ���붩��
	 * @param po
	 */
	public void insert(OrderPO po);
	/**
	 * ����id��ȡ����
	 * @param orderId
	 * @return
	 */
	public OrderPO getInfo(long orderId);
	/**
	 * ��ȡ�Ƶ�����ж���
	 * @param hotelId
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<OrderPO> getHotelOrders(long hotelId)throws RemoteException ;
	/**
	 * ��ȡ�û������ж���
	 * @param userId
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<OrderPO> getUserOrders(long userId)throws RemoteException ;
	/**
	 * ��ȡ���������쳣����
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<OrderPO> getTodayOrders()throws RemoteException ;
	/**
	 * ��ȡ�͸þƵ���û����������ж���
	 * @param hotelId
	 * @param userId
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<OrderPO> getHotelUserOrders(long hotelId,long userId)throws RemoteException ;
}
