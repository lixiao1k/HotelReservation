package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import info.OrderStatus;
import resultmessage.OrderResultMessage;
import vo.NewOrderVO;
import vo.OrderVO;
import vo.StrategyVO;

public interface OrderLogicService extends Remote{
	public ListWrapper<OrderVO> getUserOrderInfo(long userId) throws RemoteException;
	public ListWrapper<OrderVO> getHotelOrderInfo(long hotelId) throws RemoteException;
	public ListWrapper<OrderVO> getWEBOrderInfo() throws RemoteException;
	public OrderResultMessage create(NewOrderVO vo) throws RemoteException;
	public OrderResultMessage abnormal(long orderId) throws RemoteException;
	public OrderResultMessage userRevoke(long orderId) throws RemoteException;
	public OrderResultMessage execute(long orderId) throws RemoteException;
	public OrderResultMessage reExecute(long orderId) throws RemoteException;
	public OrderResultMessage webRevoke(long orderId) throws RemoteException;
}
