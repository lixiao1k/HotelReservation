package po.strategies;

import java.io.Serializable;

import info.OrderStrategy;
import po.OrderPO;

public class CompanyStrategyRule implements StrategyRule,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8036427359254956543L;
	private String name;
	public CompanyStrategyRule(String data) {
		if (data==null)
			throw new IllegalArgumentException("illegal argument!");
		this.name = data;
	}
	@Override
	public boolean canBeApplied(OrderStrategy po) {
		if (po.getCompanyName().equals(name))
			return true;
		return false;
	}
	@Override
	public String getInfo() {
		return null;
	}

}
