package data.dao.impl;

import java.rmi.RemoteException;

import data.dao.OrderDao;
import info.ListWrapper;
import po.CreditPO;
import po.HotelPO;
import po.OrderPO;

public class OrderDao_Stub implements OrderDao{

	@Override
	public void update(OrderPO po) {
		System.out.println("OrderDao.update  :  update success!");
	}

	@Override
	public void insert(OrderPO po) {
		System.out.println("OrderDao.insert  :  insert success!");
	}

	@Override
	public OrderPO getInfo(long orderId) {
		if(orderId==1){
			System.out.println("OrderDao.getInfo  :  orderId=1|return NULL");
			return null;
		}
		System.out.println("OrderDao.getInfo  :  orderId=other number|return normal result");
		return new OrderPO();
	}

	@Override
	public ListWrapper<OrderPO> getHotelOrders(long hotelId) throws RemoteException {
		if(hotelId==1){
			System.out.println("OrderDao.getHotelOrders  :  hotelId=1|return empty ListWrapper");
			return new ListWrapper<>();
		}
		if(hotelId==0){
			System.out.println("OrderDao.getHotelOrders  :  hotelId=0|return null");
			return null;
		}
		ListWrapper<OrderPO> list = new ListWrapper<>();
		list.add(new OrderPO());
		System.out.println("OrderDao.getHotelOrders  :  hotelId=other number|return normal result");
		return list;
	}

	@Override
	public ListWrapper<OrderPO> getUserOrders(long userId) throws RemoteException {
		if(userId==1){
			System.out.println("OrderDao.getUserOrders  :  userId=1|return empty ListWrapper");
			return new ListWrapper<>();
		}
		if(userId==0){
			System.out.println("OrderDao.getUserOrders  :  userId=0|return null");
			return null;
		}
		ListWrapper<OrderPO> list = new ListWrapper<>();
		list.add(new OrderPO());
		System.out.println("OrderDao.getUserOrders  :  userId=other number|return normal result");
		return list;
	}

	@Override
	public ListWrapper<OrderPO> getTodayOrders() throws RemoteException {
		ListWrapper<OrderPO> list = new ListWrapper<>();
		list.add(new OrderPO());
		System.out.println("OrderDao.getTodayOrders  :  return normal result");
		return list;
	}

	@Override
	public ListWrapper<OrderPO> getHotelUserOrders(long hotelId, long userId) throws RemoteException {

		if(userId==0||hotelId==0){
			System.out.println("OrderDao.getHotelUserOrders  :  userId=0||hotelId=0|return null");
			return null;
		}
		if(userId==1||hotelId==1){
			System.out.println("OrderDao.getHotelUserOrders  :  userId=1||hotelId=1|return empty ListWrapper");
			return new ListWrapper<>();
		}
		ListWrapper<OrderPO> list = new ListWrapper<>();
		list.add(new OrderPO());
		System.out.println("OrderDao.getHotelUserOrders  :  userId=other number and hotelId=othernumber|return normal result");
		return list;
	}

}
