package dataservice;

import po.OrderPO;

public class OrderDataService_Stub implements OrderDataService{

	@Override
	public void insert(OrderPO po) {
		// TODO Auto-generated method stub
		System.out.println("OrderDataService.Insert SUCCESS");
	}

	@Override
	public void changeStatus(OrderPO po) {
		// TODO Auto-generated method stub
		System.out.println("OrderDataService.changeStatus SUCCESS");
	}

	@Override
	public OrderPO getInfo(long orderid) {
		// TODO Auto-generated method stub
		return new OrderPO();
	}
	
}
