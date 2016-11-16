package list;

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
	public boolean delete(long id){
		for(StrategyVO vo:list){
			if(vo.getID()==id){
				list.remove(vo);
				return true;
			}
		}
		return false;
	}
}
