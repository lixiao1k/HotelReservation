package logic.service.impl.strategy;

import java.rmi.RemoteException;

import info.OrderStrategy;
import logic.service.StrategyLogicService;
import vo.StrategyVO;

public class Strategy_Driver {
	public void drive(StrategyLogicService service) throws RemoteException{
		service.delete(1);
		service.delete(2);
		StrategyVO vo = new StrategyVO();
		vo.setHotelId(1);
		service.create(vo);
		vo.setHotelId(2);
		service.create(vo);
		service.getStrategyList(1);
		service.getStrategyList(2);
		OrderStrategy osvo = new OrderStrategy();
		service.getStrategyForOrder(osvo);
		osvo.setHotelId(1);
		service.getStrategyForOrder(osvo);
		osvo.setHotelId(2);
		osvo.setUserId(1);
		service.getStrategyForOrder(osvo);
		service.getTypes();
		service.getWEBStrategyList();
	}
}
