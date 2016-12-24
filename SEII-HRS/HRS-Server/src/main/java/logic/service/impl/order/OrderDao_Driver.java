package logic.service.impl.order;

import java.rmi.RemoteException;

import data.dao.OrderDao;
import po.OrderPO;

public class OrderDao_Driver {
	public void drive(OrderDao service) throws RemoteException{
		service.update(new OrderPO());
		service.insert(new OrderPO());
		service.getInfo(1);
		service.getInfo(2);
		service.getHotelOrders(0);
		service.getHotelOrders(1);
		service.getHotelOrders(2);
		service.getUserOrders(0);
		service.getUserOrders(1);
		service.getUserOrders(2);
		service.getTodayOrders();
		service.getHotelUserOrders(0,1);
		service.getHotelUserOrders(1, 1);
		service.getHotelUserOrders(2, 2);
	}
}
