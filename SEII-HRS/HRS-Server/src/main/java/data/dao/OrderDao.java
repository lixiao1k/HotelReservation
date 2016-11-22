package data.dao;

import po.OrderPO;

public interface OrderDao {
	public void update(OrderPO po);
	public void insert(OrderPO po);
	public OrderPO getInfo(long orderId);
	//public Ite
}
