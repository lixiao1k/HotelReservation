package po.strategies;

import java.io.Serializable;

import info.OrderStrategy;

public class RoomNumStrategy implements Serializable,StrategyRule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5388478336987541019L;
	private int num;
	public RoomNumStrategy(String str) {
		// TODO Auto-generated constructor stub
		num = Integer.parseInt(str);
	}
	@Override
	public boolean canBeApplied(OrderStrategy po) {
		if(num<=po.getRoomNum())
			return true;
		return false;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return "" + num;
	}

}
