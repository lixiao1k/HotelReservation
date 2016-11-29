package po.strategies;

import po.OrderPO;

public interface StrategyRule {
	public boolean canBeApplied(OrderPO po);
	
}
