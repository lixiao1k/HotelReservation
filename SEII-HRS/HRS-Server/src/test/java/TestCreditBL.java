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
		ListWrapper<CreditVO> resultlist=creditLogic.getInfo(1);
		CreditVO result=resultlist.iterator().next();
		Assert.assertEquals("Right message", "UserId:1; date:1970-01-01; 100000000 -> 0", result.toString());
	}
	
	@Test
	public void testexcharge() throws RemoteException {
		CreditLogicService creditLogic=new CreditLogicServiceImpl();
		CreditResultMessage result=null;
		result=creditLogic.excharge(1, 10000);
		Assert.assertEquals("Insert success.", CreditResultMessage.SUCCESS, result);
		creditLogic.excharge(1, -10000);
		result=creditLogic.excharge(1, -1);
		Assert.assertEquals("Insert fail. Less than zero.", CreditResultMessage.FAIL_LESSTHANZERO, result);
	}
}
