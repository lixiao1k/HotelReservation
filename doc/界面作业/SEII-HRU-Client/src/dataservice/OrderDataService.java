package dataservice;

import po.OrderPO;

public interface OrderDataService extends DatabaseService{
	public void insert(OrderPO po);
	public void changeStatus(OrderPO po);
	public OrderPO getInfo(long orderid);

}
