package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.StrategyResultMessage;
import vo.RoomVO;
import vo.StrategyVO;

public interface StrategyLogicService extends Remote{
	public ListWrapper<StrategyVO> getStrateInfo(long hotelId) throws RemoteException;
	public StrategyResultMessage deleteStrategyInfo(long hotelId,long Strategyid) throws RemoteException;
	public StrategyResultMessage addStrategyInfo(long hotelId,StrategyVO vo) throws RemoteException;
	public RoomVO getRoomInfo(long Hotelid) throws RemoteException;
}
