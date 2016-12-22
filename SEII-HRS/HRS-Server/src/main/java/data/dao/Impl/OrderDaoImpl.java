package data.dao.impl;

import java.rmi.RemoteException;
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
	public ListWrapper<OrderPO> getHotelOrders(long hotelId) throws RemoteException {
		List<OrderPO> list = orderDataHelper.getHotelOrders(hotelId);
	
		ListWrapper<OrderPO> res = new ListWrapper<OrderPO>(list);
		return res;
	}
	@Override
	public ListWrapper<OrderPO> getUserOrders(long userId) throws RemoteException {
		List<OrderPO> list = orderDataHelper.getUserOrders(userId);
		ListWrapper<OrderPO> res = new ListWrapper<OrderPO>(list);
		return res;
	}
	@Override
	public ListWrapper<OrderPO> getTodayOrders()throws RemoteException  {
		List<OrderPO> list = orderDataHelper.getWEBOrders();
		ListWrapper<OrderPO> res = new ListWrapper<OrderPO>(list);
		return res;
	}
	@Override
	public ListWrapper<OrderPO> getHotelUserOrders(long hotelId, long userId)throws RemoteException  {
		List<OrderPO> list = orderDataHelper.getHotelUserOrders(hotelId, userId);
		ListWrapper<OrderPO> res = null;
		if(list!=null)
			res = new ListWrapper<>(list);
		return res;
	}

}
