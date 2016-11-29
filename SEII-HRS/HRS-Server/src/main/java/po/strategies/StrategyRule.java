package po.strategies;

import info.OrderStrategy;

public interface StrategyRule {
	public boolean canBeApplied(OrderStrategy po);
	public String getInfo();

}
