package businesslogic.StrategyBL;

import java.util.List;

import vo.StrategyVO;

public class StrategyList {
	private List<StrategyVO> list;
	
	public StrategyList(List<StrategyVO> list){
		this.list=list;
	}

	public List<StrategyVO> getStrategyList() {
		return list;
	}

	public void setStrategyList(List<StrategyVO> list) {
		this.list = list;
	}
}
