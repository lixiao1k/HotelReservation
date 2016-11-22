package data.datahelper;

import po.OrderPO;

public interface OrderDataHelper {
	public void insert(OrderPO po);
	public void update(OrderPO po);
	public OrderPO getInfo(long orderId);
	
}
