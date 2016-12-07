import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

import info.ListWrapper;
import logic.service.CreditLogicService;
import logic.service.impl.credit.CreditLogicServiceImpl;
import resultmessage.CreditResultMessage;
import vo.CreditVO;

public class TestCreditBL {
	@Test
	public void testgetInfo() throws RemoteException {
		CreditLogicService creditLogic=new CreditLogicServiceImpl();
		ListWrapper<CreditVO> resultlist=creditLogic.getInfo(2);
		CreditVO result=resultlist.iterator().next();
		Assert.assertNotEquals("wrong", null, result);
		System.out.println(result.toString());
	}
	
	@Test
	public void testexcharge() throws RemoteException {
		CreditLogicService creditLogic=new CreditLogicServiceImpl();
		CreditResultMessage result=null;
		result=creditLogic.excharge(2, 10000);
		Assert.assertEquals("Insert success.", CreditResultMessage.SUCCESS, result);
	}
}
