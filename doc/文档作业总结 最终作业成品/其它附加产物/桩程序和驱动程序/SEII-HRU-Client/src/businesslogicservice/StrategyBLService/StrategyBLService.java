package businesslogicservice.StrategyBLService;

import java.util.List;

import vo.StrategyVO;

public interface StrategyBLService {
	public StrategyResultMessage create(StrategyVO vo);
	public void delete(long strategyID);
	public StrategyResultMessage change(StrategyVO vo);
	public StrategyVO getInfo(long strategyID); 
	public List<StrategyVO> getStrategyList();
}
