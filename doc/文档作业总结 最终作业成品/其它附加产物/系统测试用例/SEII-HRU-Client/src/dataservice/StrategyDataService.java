package dataservice;

import java.util.List;

import po.StrategyPO;

public interface StrategyDataService extends DatabaseService{
	public void create(StrategyPO po);
	public void delete(long strategyid);
	public 	void change(StrategyPO po);
	public StrategyPO getInfo(long strategyid);
	public List<StrategyPO> getStrategies();
}
