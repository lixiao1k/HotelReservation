package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import info.OrderStatus;
import po.OrderPO;

public interface OrderDao {
	public void update(OrderPO po);
	public void insert(OrderPO po);
	public OrderPO getInfo(long orderId);
	public ListWrapper<OrderPO> getHotelOrders(long hotelId)throws RemoteException ;
	public ListWrapper<OrderPO> getUserOrders(long userId)throws RemoteException ;
	public ListWrapper<OrderPO> getTodayOrders()throws RemoteException ;
	public ListWrapper<OrderPO> getHotelUserOrders(long hotelId,long userId)throws RemoteException ;
}
