package data.dao.Impl;

import java.util.List;

import data.dao.StrategyDao;
import data.datahelper.StrategyDataHelper;
import data.datahelper.impl.DataFactory;
import info.ListWrapper;
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
	public void delete(StrategyPO po) {
		strategyDataHelper.delete(po);
	}

	@Override
	public StrategyPO getInfo(long strategyId) {
		return strategyDataHelper.getInfo(strategyId);
	}

	@Override
	public ListWrapper<StrategyPO> getHotelStrategyList(long hotelId) {
		List<StrategyPO> list = strategyDataHelper.getHotelStrategyList(hotelId);
		return new ListWrapper<StrategyPO>(list);
	}

	@Override
	public ListWrapper<StrategyPO> getWEBStrategyList() {
		List<StrategyPO> list = strategyDataHelper.getWEBStrategyList();
		return new ListWrapper<StrategyPO>(list);
	}
}
