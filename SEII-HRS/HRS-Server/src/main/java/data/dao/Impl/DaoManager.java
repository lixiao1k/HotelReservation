package data.dao.Impl;

import data.dao.DaoManagerService;
import data.dao.OrderDao;

public class DaoManager implements DaoManagerService{
	private static DaoManager instance;
	private OrderDao orderDao;
	static{
		try{
		instance = new DaoManager();
		}catch(Throwable e){
			throw new ExceptionInInitializerError(e);
		}
	}
	public static DaoManager getInstance(){
		return instance;
	}
	@Override
	public OrderDao getOrderDao() {
		if (orderDao==null)
			orderDao = new OrderDaoImpl();
		return orderDao;
	}
	
}
