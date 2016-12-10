package logic.service.impl.order;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;

import javax.management.RuntimeErrorException;

import data.dao.OrderDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.ListWrapper;
import info.OrderStatus;
import logic.service.OrderLogicService;
import po.OrderPO;
import resultmessage.OrderResultMessage;
import util.HibernateUtil;
import vo.NewOrderVO;
import vo.OrderVO;
import vo.StrategyVO;

public class OrderLogicServiceImpl extends UnicastRemoteObject implements OrderLogicService{
	private static final long serialVersionUID = 3582538173761709513L;
	private OrderDO orderDO;
	public OrderLogicServiceImpl() throws RemoteException {
		orderDO = new OrderDO();
	}
	/*
	 * 指定orderDO中缓存大小的构造器
	 * @param size of orderDO's cache
	 */
	public OrderLogicServiceImpl(int size) throws RemoteException {
		orderDO = new OrderDO(size);
	}

	@Override
	public ListWrapper<OrderVO> getWEBOrderInfo() throws RemoteException {
		return orderDO.getWEBOrderInfo();
	}

	@Override
	public OrderResultMessage abnormal(long orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return orderDO.abnormal(orderId);
	}

	@Override
	public OrderResultMessage execute(long orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return orderDO.execute(orderId);
	}
	@Override
	public OrderResultMessage reExecute(long orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return orderDO.reExecute(orderId);
	}
	@Override
	public ListWrapper<OrderVO> getUserOrderInfo(long userId) throws RemoteException {
		// TODO Auto-generated method stub
		return orderDO.getUserOrderInfo(userId);
	}
	@Override
	public ListWrapper<OrderVO> getHotelOrderInfo(long hotelId) throws RemoteException {
		// TODO Auto-generated method stub
		return orderDO.getHotelOrderInfo(hotelId);
	}
	@Override
	public OrderResultMessage create(NewOrderVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		
		return orderDO.create(vo);
	}
	@Override
	public OrderResultMessage userRevoke(long orderId) throws RemoteException {
		return orderDO.userRevoke(orderId);
	}
	@Override
	public OrderResultMessage webRevoke(long orderId,int rank) throws RemoteException {
		// TODO Auto-generated method stub
		return orderDO.webRevoke(orderId,rank);
	}
	
}
