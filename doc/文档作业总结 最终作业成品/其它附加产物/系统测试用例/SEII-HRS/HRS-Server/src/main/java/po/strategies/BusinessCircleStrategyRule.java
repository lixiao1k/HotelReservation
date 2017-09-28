package po.strategies;

import java.io.Serializable;

import info.BusinessCircle;
import info.OrderStrategy;

public class BusinessCircleStrategyRule implements StrategyRule,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2812196608262207338L;
	private String bc;
	@Override
	public boolean canBeApplied(OrderStrategy po) {
		if(bc.equals(po.getBcir().getName())&&po.isVIP())
			return true;
		return false;
	}
	public BusinessCircleStrategyRule(String bcir) {
		this.bc = bcir;
	}
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return bc;
	}

}
