package data.datahelper;

import java.util.List;

import po.StrategyPO;

public interface StrategyDataHelper {
	public void insert(StrategyPO po);
	public void delete(StrategyPO po);
	public StrategyPO getInfo(long strategyId);
	public List<StrategyPO> getHotelStrategyList(long hotelId);
	public List<StrategyPO> getWEBStrategyList();
}
