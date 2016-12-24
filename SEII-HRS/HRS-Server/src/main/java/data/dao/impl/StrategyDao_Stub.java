package data.dao.impl;

import java.rmi.RemoteException;

import data.dao.StrategyDao;
import info.ListWrapper;
import info.StrategyType;
import po.OrderPO;
import po.StrategyPO;

public class StrategyDao_Stub implements StrategyDao{

	@Override
	public void insert(StrategyPO po) {
		System.out.println("StrategyDao.insert  :  insert success!");
	}

	@Override
	public void update(StrategyPO po) {
		System.out.println("StrategyDao.update  :  update success!");
	}

	@Override
	public StrategyPO getInfo(long strategyId) {
		if(strategyId==1){
			System.out.println("StrategyDao.getInfo  :  strategyId=1|return NULL");
			return null;
		}
		System.out.println("StrategyDao.getInfo  :  strategyId=other number|return normal result");
		return new StrategyPO();
	}

	@Override
	public ListWrapper<StrategyPO> getHotelStrategyList(long hotelId) throws RemoteException {
		if(hotelId==1){
			System.out.println("StrategyDao.getHotelStrategyList  :  hotelId=1|return empty ListWrapper");
			return new ListWrapper<>();
		}
		if(hotelId==0){
			System.out.println("StrategyDao.getHotelStrategyList  :  hotelId=0|return null");
			return null;
		}
		ListWrapper<StrategyPO> list = new ListWrapper<>();
		list.add(new StrategyPO());
		System.out.println("StrategyDao.getHotelStrategyList  :  hotelId=other number|return normal result");
		return list;
	}

	@Override
	public ListWrapper<StrategyPO> getWEBStrategyList() throws RemoteException {
		ListWrapper<StrategyPO> list = new ListWrapper<>();
		list.add(new StrategyPO());
		System.out.println("StrategyDao.getWEBStrategyList  :  return normal result");
		return list;
	}

	@Override
	public ListWrapper<StrategyType> getTypes() throws RemoteException {
		ListWrapper<StrategyType> list = new ListWrapper<>();
		list.add(new StrategyType());
		System.out.println("StrategyDao.getTypes  :  return normal result");
		return list;
	}

}
