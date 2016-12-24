package logic.service.impl.strategy;

import java.rmi.RemoteException;

import info.ListWrapper;
import info.OrderStrategy;
import info.StrategyType;
import logic.service.StrategyLogicService;
import resultmessage.StrategyResultMessage;
import vo.HotelStrategyVO;
import vo.StrategyResultVO;
import vo.StrategyVO;

public class Strategy_Stub implements StrategyLogicService{

	@Override
	public StrategyResultMessage delete(long strategyId) throws RemoteException {
		if(strategyId==1){
			System.out.println("Strategy.delete  :  strategyId==1|return StrategyResultMessage.FAIL_WRONGID");
			return StrategyResultMessage.FAIL_WRONGID;
		}
		System.out.println("Strategy.delete  :  strategyId==other number|return StrategyResultMessage.SUCCESS");
		return StrategyResultMessage.SUCCESS;
	}

	@Override
	public StrategyResultVO create(StrategyVO vo) throws RemoteException {
		StrategyResultVO result = new StrategyResultVO();
		if(vo.getHotelId()==1){
			System.out.println("Strategy.create  :  vo.getHotelId()==1|return StrategyResultMessage.FAIL_WRONGID");
			result.setResultMessage(StrategyResultMessage.FAIL_WRONGID);
			return result;
		}
		System.out.println("Strategy.create  :  normal info|return StrategyResultMessage.SUCCESS");
		result.setResultMessage(StrategyResultMessage.SUCCESS);
		return result;
	}

	@Override
	public ListWrapper<HotelStrategyVO> getStrategyList(long hotelId) throws RemoteException {
		if(hotelId==1){
			System.out.println("Strategy.getStrategyList  :  hotelId==1|return null");
			return null;
		}
		ListWrapper<HotelStrategyVO> result = new ListWrapper<>();
		result.add(new HotelStrategyVO());
		System.out.println("Strategy.getStrategyList  :  normal info|return normal result");
		return result;
	}

	@Override
	public ListWrapper<HotelStrategyVO> getStrategyForOrder(OrderStrategy vo) throws RemoteException {
		if(vo.getHotelId()==1){
			System.out.println("Strategy.getStrategyForOrder  :  vo.getHotelId()==1|return empty list");
			return new ListWrapper<>();
		}
		if(vo.getUserId()==0){
			System.out.println("Strategy.getStrategyForOrder  :  vo.getUserId()==0|return null");
			return null;
		}
		ListWrapper<HotelStrategyVO> result = new ListWrapper<>();
		result.add(new HotelStrategyVO());
		System.out.println("Strategy.getStrategyForOrder  :  normal info|return normal list");
		return result;
	}

	@Override
	public ListWrapper<StrategyType> getTypes() throws RemoteException {
		ListWrapper<StrategyType> result = new ListWrapper<>();
		result.add(new StrategyType());
		System.out.println("Strategy.getTypes  :  return normal list");
		return result;
	}

	@Override
	public ListWrapper<HotelStrategyVO> getWEBStrategyList() throws RemoteException {
		ListWrapper<HotelStrategyVO> result = new ListWrapper<>();
		result.add(new HotelStrategyVO());
		System.out.println("Strategy.getWEBStrategyList  :  return normal list");
		return result;
	}

}
