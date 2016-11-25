package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.StrategyResultMessage;
import vo.RoomVO;
import vo.StrategyVO;

public interface StrategyLogicService extends Remote{
	public ListWrapper<StrategyVO> GetStrateInfo(long Hotelid) throws RemoteException;
	public StrategyResultMessage DeleteStrategyInfo(long Hotelid,long Strategyid) throws RemoteException;
	public StrategyResultMessage AddStrategyInfo(StrategyVO vo) throws RemoteException;
	public StrategyResultMessage ChangeStrategyInfo(StrategyVO vo) throws RemoteException;
	public RoomVO getRoomInfo(long Hotelid) throws RemoteException;
}
