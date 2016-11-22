package data.dao;

import java.util.Iterator;

import info.ListWrapper;
import po.CommentPO;
import po.OrderPO;

public interface OrderDao {
	public void update(OrderPO po);
	public void insert(OrderPO po);
	public OrderPO getInfo(long orderId);
	public ListWrapper<OrderPO> getHotelOrders(long hotelId);
	public ListWrapper<OrderPO> getUserOrders(long hotelId);
	public ListWrapper<OrderPO> getTodayOrders();
}
