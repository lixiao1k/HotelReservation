package data.dao;

import info.ListWrapper;
import info.OrderStatus;
import po.OrderPO;

public interface OrderDao {
	public void update(OrderPO po);
	public void insert(OrderPO po);
	public OrderPO getInfo(long orderId);
	public ListWrapper<OrderPO> getHotelOrders(long hotelId);
	public ListWrapper<OrderPO> getUserOrders(long userId);
	public ListWrapper<OrderPO> getTodayOrders();
}
