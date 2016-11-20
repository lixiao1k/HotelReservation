package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.OrderStatus;
import list.CreditList;
import list.HotelList;
import list.OrderList;
import vo.HotelVO;
import vo.MemberVO;
import vo.StrategyVO;

public interface BrowseLogicService extends Remote{
	public OrderList getUserOrderInfo(long userId,OrderStatus status) throws RemoteException;
	public OrderList getHotelOrderInfo(long hotelId,OrderStatus status) throws RemoteException;
	public OrderList getWEBOrderInfo() throws RemoteException;
	public HotelList getHotels() throws RemoteException;
	public CreditList getCreditInfo() throws RemoteException;
	public CreditList getCreditInfo(long userId) throws RemoteException;
	public HotelVO getHotel(long hotelId) throws RemoteException;
	public StrategyVO getStrategyInfo(String strategyName) throws RemoteException;
	public MemberVO getMemberInfo(long userid) throws RemoteException;
}
