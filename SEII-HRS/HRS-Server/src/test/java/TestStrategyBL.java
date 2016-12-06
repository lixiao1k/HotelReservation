import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import info.ListWrapper;
import info.OrderStrategy;
import info.Room;
import info.StrategyType;
import logic.service.StrategyLogicService;
import logic.service.impl.strategy.StrategyLogicServiceImpl;
import resultmessage.StrategyResultMessage;
import vo.HotelStrategyVO;
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
	@Test
	public void testDelete() throws RemoteException{
		StrategyLogicService strategyService = new StrategyLogicServiceImpl();
		StrategyResultMessage result = strategyService.delete(1);
		Assert.assertEquals("wrong", StrategyResultMessage.SUCCESS,result);
	}
	@Test
	public void testGetStrategyList() throws RemoteException{
		StrategyLogicService strategyService = new StrategyLogicServiceImpl();
		ListWrapper<HotelStrategyVO> result = strategyService.getStrategyList(1);
		Assert.assertNotEquals("wrong", null,result);
		Iterator<HotelStrategyVO> it = result.iterator();
		while(it.hasNext()){
			HotelStrategyVO hsvo = it.next();
			System.out.println(hsvo.getExtraInfo());
		}
	}
	@Test
	public void testGetStrategyForOrder() throws RemoteException{
		StrategyLogicService strategyService = new StrategyLogicServiceImpl();
		OrderStrategy os = new OrderStrategy();
		os.setUserId(3);
		os.setHotelId(1);
		os.setCompanyName("birhday");
		os.setBirthday(new Date());
		os.setCheckInTime(new Date());
		ListWrapper<HotelStrategyVO> result = strategyService.getStrategyForOrder(os);
		Assert.assertNotEquals("wrong", null,result);
		Iterator<HotelStrategyVO> it = result.iterator();
		while(it.hasNext()){
			HotelStrategyVO hsvo = it.next();
			System.out.println(hsvo.getExtraInfo());
		}
	}
	@Test
	public void testGetTypes() throws RemoteException{
		StrategyLogicService strategyService = new StrategyLogicServiceImpl();
		ListWrapper<String> types = strategyService.getTypes();
		Assert.assertNotEquals("wrong", null,types);
		Iterator<String> it = types.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
