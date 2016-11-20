import java.rmi.RemoteException;
import org.junit.Assert;
import org.junit.Test;

import logic.service.OrderLogicService;
import logic.service.impl.order.OrderLogicServiceImpl;
import resultmessage.OrderResultMessage;

public class TestOrderBL {
	@Test
	public void testAbnormal() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		OrderResultMessage result = orderLogic.abnormal(1);
		Assert.assertEquals("Failed in abnormal success test",OrderResultMessage.ABNORMAL_SUCCESS,result);
		result = orderLogic.abnormal(1);
		Assert.assertEquals("Failed in abnormal fail-status test", OrderResultMessage.FAIL_WRONGSTATUS,result);
		result = orderLogic.abnormal(2);
		Assert.assertEquals("Failed in abnormal fail-wrong-id test",OrderResultMessage.FAIL_WRONGID, result);
	}
	@Test
	public void testGetTotal() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		double sum = orderLogic.getTotal(1);
		Assert.assertEquals("Failed in getTotal success test", 375,sum,0.1);
		sum = orderLogic.getTotal(2);
		Assert.assertEquals("Failed in getTotal failed-not-found test", -1,sum,0.1);
	}
}
