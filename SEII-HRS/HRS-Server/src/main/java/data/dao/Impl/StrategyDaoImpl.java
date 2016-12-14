package data.dao.Impl;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.StrategyDao;
import data.datahelper.StrategyDataHelper;
import data.datahelper.impl.DataFactory;
import info.ListWrapper;
import info.StrategyType;
import po.StrategyPO;

public class StrategyDaoImpl implements StrategyDao{
	private StrategyDataHelper strategyDataHelper;
	public StrategyDaoImpl() {
		strategyDataHelper = DataFactory
							.getInstance()
							.getStrategyDataHelper();
	}
	@Override
	public void insert(StrategyPO po) {
		strategyDataHelper.insert(po);
	}

	@Override
	public void update(StrategyPO po) {
		strategyDataHelper.update(po);
	}

	@Override
	public StrategyPO getInfo(long strategyId) {
		return strategyDataHelper.getInfo(strategyId);
	}

	@Override
	public ListWrapper<StrategyPO> getHotelStrategyList(long hotelId) throws RemoteException {
		List<StrategyPO> list = strategyDataHelper.getHotelStrategyList(hotelId);
		return new ListWrapper<StrategyPO>(list);
	}

	@Override
	public ListWrapper<StrategyPO> getWEBStrategyList() throws RemoteException {
		List<StrategyPO> list = strategyDataHelper.getWEBStrategyList();
		return new ListWrapper<StrategyPO>(list);
	}
	@Override
	public ListWrapper<StrategyType> getTypes() throws RemoteException {
		List<StrategyType> list = strategyDataHelper.getTypes();
		return new ListWrapper<StrategyType>(list);
	}
}
