package businesslogic.StrategyBL;

import java.util.ArrayList;
import java.util.List;

import businesslogicservice.StrategyBLService.StrategyBLService;
import businesslogicservice.StrategyBLService.StrategyResultMessage;
import vo.StrategyVO;

public class StrategyBLService_Stub implements StrategyBLService{

	@Override
	public StrategyResultMessage create(StrategyVO vo) {
		// TODO Auto-generated method stub
		// 讲新策略存入，涉及到数据库存储，默认返回成功
		return StrategyResultMessage.SUCCESS;
	}

	@Override
	public void delete(long strategyID) {
		// TODO Auto-generated method stub
		//删除数据库中的策略信息
	}

	@Override
	public StrategyResultMessage change(StrategyVO vo) {
		// TODO Auto-generated method stub
		//更改策略信息
		return StrategyResultMessage.SUCCESS;
	}

	@Override
	public StrategyVO getInfo(long strategyID) {
		// TODO Auto-generated method stub
		// 获取数据库中策略的信息
		return new StrategyVO();
	}

	@Override
	public List<StrategyVO> getStrategyList() {
		// TODO Auto-generated method stub
		//返回策略列表
		List<StrategyVO> list = new ArrayList<StrategyVO>();
		StrategyVO vo = new StrategyVO();
		list.add(vo);
		return list;
	}

}
