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
		// ���²��Դ��룬�漰�����ݿ�洢��Ĭ�Ϸ��سɹ�
		return StrategyResultMessage.SUCCESS;
	}

	@Override
	public void delete(long strategyID) {
		// TODO Auto-generated method stub
		//ɾ�����ݿ��еĲ�����Ϣ
	}

	@Override
	public StrategyResultMessage change(StrategyVO vo) {
		// TODO Auto-generated method stub
		//���Ĳ�����Ϣ
		return StrategyResultMessage.SUCCESS;
	}

	@Override
	public StrategyVO getInfo(long strategyID) {
		// TODO Auto-generated method stub
		// ��ȡ���ݿ��в��Ե���Ϣ
		return new StrategyVO();
	}

	@Override
	public List<StrategyVO> getStrategyList() {
		// TODO Auto-generated method stub
		//���ز����б�
		List<StrategyVO> list = new ArrayList<StrategyVO>();
		StrategyVO vo = new StrategyVO();
		list.add(vo);
		return list;
	}

}
