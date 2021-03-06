package data.datahelper;

import java.util.List;

import info.StrategyType;
import po.StrategyPO;

public interface StrategyDataHelper {
	public void insert(StrategyPO po);
	public void update(StrategyPO po);
	public StrategyPO getInfo(long strategyId);
	public List<StrategyPO> getHotelStrategyList(long hotelId);
	public List<StrategyPO> getWEBStrategyList();
	public List<StrategyType> getTypes();
}
