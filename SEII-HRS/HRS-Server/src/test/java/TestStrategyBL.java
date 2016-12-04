import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import info.Room;
import info.StrategyType;
import logic.service.StrategyLogicService;
import logic.service.impl.strategy.StrategyLogicServiceImpl;
import resultmessage.StrategyResultMessage;
import vo.StrategyItemVO;
import vo.StrategyResultVO;
import vo.StrategyVO;

public class TestStrategyBL {
	@Test
	public void testCreate() throws RemoteException{
		StrategyLogicService strategyService = new StrategyLogicServiceImpl();
		StrategyVO vo = new StrategyVO();
		StrategyType type = new StrategyType();
		type.setName("FestivalStrategy");
		type.setId(1);
		vo.setStrategyType(type);
		vo.setName("双十二促销策略");
		vo.setHotelId(1);
		Set<StrategyItemVO> list = new HashSet<>();
		StrategyItemVO sivo = new StrategyItemVO();
		sivo.setOff(0.3);
		Room room = new Room();
		room.setRid(1);
		room.setType("大床房");
		sivo.setRoom(room);
		list.add(sivo);
		vo.setItems(list);
		vo.setExtraInfo("2016-12-12|2016-12-13");
		StrategyResultVO result = strategyService.create(vo);
		Assert.assertNotEquals("wrong", null,result);
	}
}
