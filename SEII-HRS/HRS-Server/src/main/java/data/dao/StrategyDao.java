package data.dao;

import info.ListWrapper;
import po.StrategyPO;

public interface StrategyDao {
	public void insert(StrategyPO po);
	public void delete(StrategyPO po);
	public void update(StrategyPO po);
	public StrategyPO getInfo(long strategyId);
	public ListWrapper<StrategyPO> getHotelStrategyList(long hotelId);
}
