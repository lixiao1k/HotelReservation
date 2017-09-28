package dataservice;

import java.util.ArrayList;
import java.util.List;

import po.StrategyPO;

public class StrategyDataService_Stub implements StrategyDataService{

	@Override
	public void create(StrategyPO po) {
		// TODO Auto-generated method stub
		System.out.println("StrategyDataService.create SUCCESS");
	}

	@Override
	public void delete(long strategyid) {
		// TODO Auto-generated method stub
		System.out.println("StrategyDataService.delete SUCCESS");
	}

	@Override
	public void change(StrategyPO po) {
		// TODO Auto-generated method stub
		System.out.println("StrategyDataService.change SUCCESS");
	}

	@Override
	public StrategyPO getInfo(long strategyid) {
		// TODO Auto-generated method stub
		return new StrategyPO();
	}

	@Override
	public List<StrategyPO> getStrategies() {
		// TODO Auto-generated method stub
		List<StrategyPO> list = new ArrayList<StrategyPO>();
		return list;
	}

}
