package data.dao.Impl;

import data.dao.OrderDao;
import data.datahelper.OrderDataHelper;
import data.datahelper.impl.DataFactory;
import po.OrderPO;

public class OrderDaoImpl implements OrderDao{
	private OrderDataHelper orderDataHelper;
	public OrderDaoImpl() {
		orderDataHelper = DataFactory
							.getInstance()
							.getOrderDataHelper();
	}
	@Override
	public void update(OrderPO po) {
		orderDataHelper.update(po);
	}

	@Override
	public void insert(OrderPO po) {
		orderDataHelper.insert(po);
	}
	@Override
	public OrderPO getInfo(long orderId) {
		
		return orderDataHelper.getInfo(orderId);
	}

}
