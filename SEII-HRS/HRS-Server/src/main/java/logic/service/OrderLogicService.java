package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.OrderStatus;
import list.OrderList;
import resultmessage.OrderResultMessage;
import vo.OrderVO;
import vo.StrategyVO;

public interface OrderLogicService extends Remote{
	public OrderList getUserOrderInfo(long userId,OrderStatus status) throws RemoteException;
	public OrderList getHotelOrderInfo(long hotelId,OrderStatus status) throws RemoteException;
	public OrderList getWEBOrderInfo() throws RemoteException;
	public OrderResultMessage create(OrderVO vo) throws RemoteException;
	public OrderResultMessage abnormal(long orderId) throws RemoteException;
	public OrderResultMessage userCancel(long orderId) throws RemoteException;
	public OrderResultMessage execute(long orderId) throws RemoteException;
	public OrderResultMessage reExecute(long orderId) throws RemoteException;
	public boolean isUsed(StrategyVO vo) throws RemoteException;
	public double getTotal(long orderId) throws RemoteException;
}
