package logic.service.impl.order;

import java.rmi.RemoteException;
import java.util.Iterator;

import javax.management.RuntimeErrorException;

import data.dao.OrderDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.ListWrapper;
import info.OrderItem;
import info.OrderStatus;
import logic.service.OrderLogicService;
import po.OrderPO;
import resultmessage.OrderResultMessage;
import util.HibernateUtil;
import vo.OrderVO;
import vo.StrategyVO;

public class OrderLogicServiceImpl implements OrderLogicService{
	private OrderDO orderDO;
	public OrderLogicServiceImpl(){
		orderDO = new OrderDO();
	}
	/*
	 * 指定orderDO中缓存大小的构造器
	 * @param size of orderDO's cache
	 */
	public OrderLogicServiceImpl(int size){
		orderDO = new OrderDO(size);
	}
	@Override
	public ListWrapper<OrderVO> getUserOrderInfo(long userId, OrderStatus status) throws RemoteException {
		return orderDO.getUserOrderInfo(userId, status);
	}
	@Override
	public ListWrapper<OrderVO> getHotelOrderInfo(long hotelId, OrderStatus status) throws RemoteException {
		return orderDO.getHotelOrderInfo(hotelId, status);
	}
	@Override
	public ListWrapper<OrderVO> getWEBOrderInfo() throws RemoteException {
		return orderDO.getWEBOrderInfo();
	}
	@Override
	public OrderResultMessage create(OrderVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OrderResultMessage abnormal(long orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OrderResultMessage userCancel(long orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OrderResultMessage execute(long orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OrderResultMessage reExecute(long orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isUsed(StrategyVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public double getTotal(long orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public OrderResultMessage webCancel(long orderId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
