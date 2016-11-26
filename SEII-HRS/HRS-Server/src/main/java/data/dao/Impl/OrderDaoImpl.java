package data.dao.Impl;

import java.util.Iterator;
import java.util.List;

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
	public ListWrapper<OrderPO> getHotelOrders(long hotelId) {
		List<OrderPO> list = orderDataHelper.getHotelOrders(hotelId);
		ListWrapper<OrderPO> res = new ListWrapper<OrderPO>(list);
		return res;
	}
	@Override
	public ListWrapper<OrderPO> getUserOrders(long userId) {
		List<OrderPO> list = orderDataHelper.getHotelOrders(userId);
		ListWrapper<OrderPO> res = new ListWrapper<OrderPO>(list);
		return res;
	}
	@Override
	public ListWrapper<OrderPO> getTodayOrders() {
		List<OrderPO> list = orderDataHelper.getWEBOrders();
		ListWrapper<OrderPO> res = new ListWrapper<OrderPO>(list);
		return res;
	}

}
