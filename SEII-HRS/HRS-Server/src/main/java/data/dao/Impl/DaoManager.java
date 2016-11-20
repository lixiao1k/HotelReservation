package data.dao.Impl;

import data.dao.CreditDao;
import data.dao.DaoManagerService;
import data.dao.OrderDao;
import data.dao.UserDao;

public class DaoManager implements DaoManagerService{
	private static DaoManager instance;
	private OrderDao orderDao;
	private UserDao userDao;
	private CreditDao creditDao;
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
	@Override
	public UserDao getUserDao() {
		if (userDao==null)
			userDao = new UserDaoImpl();
		return userDao;
	}
	@Override
	public CreditDao getCreditDao() {
		if (creditDao==null)
			creditDao = new CreditDaoImpl();
		return creditDao;
	}
	
}
