package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import info.OrderStatus;
import po.OrderPO;

public interface OrderDao {
	/**
	 * 更新订单
	 * @param po
	 */
	public void update(OrderPO po);
	/**
	 * 插入订单
	 * @param po
	 */
	public void insert(OrderPO po);
	/**
	 * 根据id获取订单
	 * @param orderId
	 * @return
	 */
	public OrderPO getInfo(long orderId);
	/**
	 * 获取酒店的所有订单
	 * @param hotelId
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<OrderPO> getHotelOrders(long hotelId)throws RemoteException ;
	/**
	 * 获取用户的所有订单
	 * @param userId
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<OrderPO> getUserOrders(long userId)throws RemoteException ;
	/**
	 * 获取当日所有异常订单
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<OrderPO> getTodayOrders()throws RemoteException ;
	/**
	 * 获取和该酒店和用户关联的所有订单
	 * @param hotelId
	 * @param userId
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<OrderPO> getHotelUserOrders(long hotelId,long userId)throws RemoteException ;
}
