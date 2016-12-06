package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import info.OrderStrategy;
import resultmessage.StrategyResultMessage;
import vo.HotelStrategyVO;
import vo.StrategyResultVO;
import vo.StrategyVO;

public interface StrategyLogicService extends Remote{
	public StrategyResultMessage delete(long strategyId) throws RemoteException;
	public StrategyResultVO create(StrategyVO vo) throws RemoteException;
	public ListWrapper<HotelStrategyVO> getStrategyList(long hotelId) throws RemoteException;
	public ListWrapper<HotelStrategyVO> getStrategyForOrder(OrderStrategy vo) throws RemoteException;
	public ListWrapper<String> getTypes() throws RemoteException;
}
