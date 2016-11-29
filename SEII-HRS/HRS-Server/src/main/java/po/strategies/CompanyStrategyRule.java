package po.strategies;

import java.io.Serializable;

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
	public boolean canBeApplied(OrderPO po) {
		//if (po.getMember().)
		return false;
	}

}
