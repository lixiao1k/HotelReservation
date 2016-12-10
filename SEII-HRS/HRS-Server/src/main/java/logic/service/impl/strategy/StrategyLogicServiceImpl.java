package logic.service.impl.strategy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import info.ListWrapper;
import info.OrderStrategy;
import logic.service.StrategyLogicService;
import resultmessage.StrategyResultMessage;
import vo.HotelStrategyVO;
import vo.StrategyResultVO;
import vo.StrategyVO;

public class StrategyLogicServiceImpl extends UnicastRemoteObject implements StrategyLogicService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3862734563580471202L;
	private StrategyDO strategyDO;
	public StrategyLogicServiceImpl() throws RemoteException {
		strategyDO = new StrategyDO();
	}
	/*
	 * 指定orderDO中缓存大小的构造器
	 * @param size of orderDO's cache
	 */
	public StrategyLogicServiceImpl(int size) throws RemoteException {
		strategyDO = new StrategyDO(size);
	}

	@Override
	public StrategyResultMessage delete(long strategyId) throws RemoteException {
		// TODO Auto-generated method stub
		return strategyDO.delete(strategyId);
	}

	@Override
	public StrategyResultVO create(StrategyVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return strategyDO.create(vo);
	}

	@Override
	public ListWrapper<HotelStrategyVO> getStrategyList(long hotelId) throws RemoteException {
		// TODO Auto-generated method stub
		return strategyDO.getStrategyList(hotelId);
	}

	@Override
	public ListWrapper<HotelStrategyVO> getStrategyForOrder(OrderStrategy vo) throws RemoteException {
		// TODO Auto-generated method stub
		return strategyDO.getStrategyForOrder(vo);
	}
	@Override
	public ListWrapper<String> getTypes() throws RemoteException {
		// TODO Auto-generated method stub
		return strategyDO.getTypes();
	}

}
