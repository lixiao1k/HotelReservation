package presentation.StrategyUI;

import java.util.List;

import businesslogicservice.StrategyBLService.StrategyBLService;
import businesslogicservice.StrategyBLService.StrategyResultMessage;
import vo.StrategyVO;

public class StrategyBLService_Driver {
	public void drive(StrategyBLService service){
		StrategyVO vo = new StrategyVO();
		StrategyResultMessage result = service.change(vo);
		if (result==StrategyResultMessage.SUCCESS) System.out.println("StrategyBLService.change SUCCESS");
		result = service.create(vo);
		if (result==StrategyResultMessage.SUCCESS) System.out.println("StrategyBLService.create SUCCESS");
		vo = service.getInfo(1234);
		if (vo!=null) System.out.println("StrategyBLService.getInfo SUCCESS");
		List<StrategyVO> list = service.getStrategyList();
		if (list!=null) System.out.println("StrategyBLService.getStrategyList SUCCESS");
	}
}
