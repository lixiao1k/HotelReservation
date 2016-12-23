package vo;

import java.io.Serializable;
import java.util.Set;

public class CreateStrategyVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 890105449692526869L;
	private long strategyId;
	private Set<StrategyItemVO> items;
	public void setItems(Set<StrategyItemVO> items){
		this.items = items;
	}
	public void setStrategyId(long strategyId){
		this.strategyId = strategyId;
	}
	public long getStrategyId(){
		return strategyId;
	}
	public Set<StrategyItemVO> getItems(){
		return items;
	}
}
