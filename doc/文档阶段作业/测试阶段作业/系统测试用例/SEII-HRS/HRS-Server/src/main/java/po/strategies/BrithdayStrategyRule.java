package po.strategies;

import java.io.Serializable;

import info.OrderStrategy;

public class BrithdayStrategyRule implements StrategyRule,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2995416007646717175L;
	public BrithdayStrategyRule(String s) {
	}
	@Override
	public boolean canBeApplied(OrderStrategy po) {
		if(po.getCheckInTime().before(po.getBirthday())&&po.getCheckOutTime().after(po.getBirthday()))
			return true;
		return false;
	}

	@Override
	public String getInfo() {
		return "";
	}

}
