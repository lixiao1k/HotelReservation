import java.rmi.RemoteException;
import java.time.LocalDate;

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
	public void testinsert() throws RemoteException {
		CreditLogicService creditLogic=new CreditLogicServiceImpl();
		CreditResultMessage result=null;
		LocalDate time=LocalDate.of(1970, 1, 1);
		CreditVO testvo=new CreditVO(1, time, 100000000, 0);
		result=creditLogic.insert(testvo);
		Assert.assertEquals("Insert success.", CreditResultMessage.SUCCESS, result);
		CreditVO testvo2=new CreditVO(1, time, -1, 0);
		result=creditLogic.insert(testvo2);
		Assert.assertEquals("Insert fail. Less than zero.", CreditResultMessage.FAIL_LESSTHANZERO, result);
	}
}
