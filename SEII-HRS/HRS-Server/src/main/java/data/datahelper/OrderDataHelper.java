package data.datahelper;

import java.util.List;

import po.OrderPO;

public interface OrderDataHelper {
	public void insert(OrderPO po);
	public void update(OrderPO po);
	public OrderPO getInfo(long orderId);
	public List<OrderPO> getWEBOrders();
	public List<OrderPO> getUserOrders(long userId);
	public List<OrderPO> getHotelOrders(long hotelId);
	public List<OrderPO> getHotelUserOrders(long hotelId, long userId);
}
