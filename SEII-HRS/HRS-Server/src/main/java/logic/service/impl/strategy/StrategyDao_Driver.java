package logic.service.impl.strategy;

import java.rmi.RemoteException;

import data.dao.StrategyDao;
import po.StrategyPO;

public class StrategyDao_Driver {
	public void drive(StrategyDao service) throws RemoteException{
		service.insert(new StrategyPO());
		service.update(new StrategyPO());
		service.getInfo(1);
		service.getInfo(2);
		service.getHotelStrategyList(0);
		service.getHotelStrategyList(1);
		service.getHotelStrategyList(2);
		service.getWEBStrategyList();
		service.getTypes();
	}
}
