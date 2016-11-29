package logic.service.impl.strategy;

import java.rmi.RemoteException;

import info.ListWrapper;
import logic.service.StrategyLogicService;
import resultmessage.StrategyResultMessage;
import vo.RoomVO;
import vo.StrategyVO;

public class StrategyLogicServiceImpl implements StrategyLogicService{
	private StrategyDO strategyDO;
	@Override
	public ListWrapper<StrategyVO> getStrateInfo(long hotelId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StrategyResultMessage deleteStrategyInfo(long hotelId, long Strategyid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StrategyResultMessage addStrategyInfo(long hotelId, StrategyVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoomVO getRoomInfo(long Hotelid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
