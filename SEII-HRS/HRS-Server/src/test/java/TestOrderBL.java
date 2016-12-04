import java.rmi.RemoteException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import info.Room;
import logic.service.OrderLogicService;
import logic.service.impl.order.OrderLogicServiceImpl;
import resultmessage.OrderResultMessage;
import vo.NewOrderVO;

public class TestOrderBL {
	@Test 
	public void testCreate() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		Room room = new Room();
		room.setRid(1);
		room.setType("´ó´²·¿");
		NewOrderVO vo = new NewOrderVO(1,1,false,4,"13307612344","ÃÈÐÂ",new Date(),new Date(),room,2,233,1,0.3);
		OrderResultMessage result = orderLogic.create(vo);
		Assert.assertEquals("wrong", OrderResultMessage.SUCCESS,result);
	}
	@Test
	public void testAbnormal() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		OrderResultMessage result = orderLogic.abnormal(1);
	//	Assert.assertEquals("Failed in abnormal success test",OrderResultMessage.ABNORMAL_SUCCESS,result);
		result = orderLogic.abnormal(1);
		Assert.assertEquals("Failed in abnormal fail-status test", OrderResultMessage.FAIL_WRONGSTATUS,result);
		result = orderLogic.abnormal(2);
		Assert.assertEquals("Failed in abnormal fail-wrong-id test",OrderResultMessage.FAIL_WRONGID, result);
	}
	@Test
	public void testGetTotal() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
	//	double sum = orderLogic.getTotal(1);
	//	Assert.assertEquals("Failed in getTotal success test", 375,sum,0.1);
	//	sum = orderLogic.getTotal(2);
	//	Assert.assertEquals("Failed in getTotal failed-not-found test", -1,sum,0.1);
	}
}
