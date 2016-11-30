package logic.service.impl.strategy;

import java.rmi.RemoteException;

import info.ListWrapper;
import info.OrderStrategy;
import logic.service.StrategyLogicService;
import logic.service.impl.order.OrderDO;
import resultmessage.StrategyResultMessage;
import vo.HotelStrategyVO;
import vo.RoomVO;
import vo.StrategyResultVO;
import vo.StrategyVO;

public class StrategyLogicServiceImpl implements StrategyLogicService{
	private StrategyDO strategyDO;
	public StrategyLogicServiceImpl(){
		strategyDO = new StrategyDO();
	}
	/*
	 * 指定orderDO中缓存大小的构造器
	 * @param size of orderDO's cache
	 */
	public StrategyLogicServiceImpl(int size){
		strategyDO = new StrategyDO(size);
	}

	@Override
	public StrategyResultMessage delete(long strategyId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StrategyResultVO create(StrategyVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListWrapper<HotelStrategyVO> getStrategyList(long hotelId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListWrapper<HotelStrategyVO> getStrategyForOrder(OrderStrategy vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListWrapper<String> getTypes() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
