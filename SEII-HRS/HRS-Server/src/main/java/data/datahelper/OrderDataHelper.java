package data.datahelper;

import java.util.List;

import info.OrderStatus;
import po.OrderPO;

public interface OrderDataHelper {
	public void insert(OrderPO po);
	public void update(OrderPO po);
	public OrderPO getInfo(long orderId);
	public List<OrderPO> getWEBOrders();
	public List<OrderPO> getUserOrders(long userId,OrderStatus status);
	public List<OrderPO> getHotelOrders(long hotelId,OrderStatus status);
}
