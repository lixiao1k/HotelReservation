package po.strategies;

import java.io.Serializable;
import java.util.Date;

import info.OrderStrategy;
import po.OrderPO;

public class FestivalStrategyRule implements StrategyRule,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5936186122679448745L;
	private Date beginTime;
	private Date endTime;
	public FestivalStrategyRule(String data) {
		if (data==null)
			throw new IllegalArgumentException("illegal argument");
		String[] datas = data.split("|");
		if (datas.length!=2)
			throw new IllegalArgumentException("illegal argument");
		beginTime = new Date(datas[0]);
		endTime = new Date(datas[1]);
	}
	@Override
	public boolean canBeApplied(OrderStrategy po) {
		Date checkInTime = po.getCheckInTime();
		if(checkInTime.before(endTime)&&checkInTime.after(beginTime))
			return true;
		return false;
	}
	@Override
	public String getInfo() {
		return beginTime.toString()+"|"+endTime.toString();
	}
}
