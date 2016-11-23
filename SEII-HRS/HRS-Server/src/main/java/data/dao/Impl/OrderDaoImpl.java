package data.dao.Impl;

import java.util.Iterator;

import data.dao.OrderDao;
import data.datahelper.OrderDataHelper;
import data.datahelper.impl.DataFactory;
import info.ListWrapper;
import info.OrderStatus;
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
	@Override
	public ListWrapper<OrderPO> getHotelOrders(long hotelId,OrderStatus status) {
		// TODO Auto-generated method stub
		
		return null;
	}
	@Override
	public ListWrapper<OrderPO> getUserOrders(long userId,OrderStatus status) {
		// TODO Auto-generated method stub
		
		return null;
	}
	@Override
	public ListWrapper<OrderPO> getTodayOrders() {
		
		return null;
	}

}
