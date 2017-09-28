package vo;

import java.util.Set;

public class CreateStrategyVO {
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
