 import java.rmi.RemoteException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import info.ListWrapper;
import logic.service.CreditLogicService;
import logic.service.MemberLogicService;
import logic.service.impl.credit.CreditLogicServiceImpl;
import logic.service.impl.member.MemerLogicServiceImpl;
import resultmessage.MemberResultMessage;
import vo.MemberVO;
import vo.VIPVO;

public class TestMemberBL {
	@Test
	public void testregisterVIP() throws RemoteException {
		MemberLogicService memberLogic=new MemerLogicServiceImpl();
		CreditLogicService creditLogic=new CreditLogicServiceImpl();
		creditLogic.excharge(1, 10000);
		MemberResultMessage result=null;
		VIPVO testvo=null;
		LocalDate time=LocalDate.of(1970, 1, 1);
		testvo=new VIPVO(1, time, 1, "Bingyuhuo");
		result=memberLogic.registerVIP(testvo);
	//	Assert.assertEquals("Register VIP success.", MemberResultMessage.REGISTERVIP_SUCCESS, result);
		result=memberLogic.registerVIP(testvo);
	//	Assert.assertEquals("Register VIP fail. Already VIP.", MemberResultMessage.REGISTERVIP_FAIL_ALREADYVIP, result);
		memberLogic.cancel(1);
		testvo.setUserId(0);
		result=memberLogic.registerVIP(testvo);
	//	Assert.assertEquals("Register VIP fail. Wrong ID.", MemberResultMessage.REGISTERVIP_FAIL_WRONGID, result);
		creditLogic.excharge(1, -10000);
		testvo.setUserId(1);
		memberLogic.cancel(1);
		result=memberLogic.registerVIP(testvo);
	//	Assert.assertEquals("Register VIP fail. No enough credit.", MemberResultMessage.REGISTERVIP_FAIL_CREDITNOTENOUGH, result);
	}
	
	@Test
	public void testcancel() throws RemoteException {
		MemberLogicService memberLogic=new MemerLogicServiceImpl();
		CreditLogicService creditLogic=new CreditLogicServiceImpl();
		MemberResultMessage result=null;	
		result=memberLogic.cancel(1);
	//	Assert.assertEquals("Cancel VIP fail. Not VIP.", MemberResultMessage.CANCELVIP_FAIL_NOTVIP, result);
		creditLogic.excharge(1, 10000);
		VIPVO testvo=null;
		LocalDate time=LocalDate.of(1970, 1, 1);
		testvo=new VIPVO(1, time, 1, "Bingyuhuo");
		memberLogic.registerVIP(testvo);
		result=memberLogic.cancel(1);
	//	Assert.assertEquals("Cancel VIP fail. Enough credit.", MemberResultMessage.CANCELVIP_FAIL_CREDITENOUGH, result);
		result=memberLogic.cancel(2);
	//	Assert.assertEquals("Cancel VIP fail. Wrong ID.", MemberResultMessage.CANCELVIP_FAIL_WRONGID, result);
		creditLogic.excharge(1, -10000);
		result=memberLogic.cancel(1);
	//	Assert.assertEquals("Cancel VIP success.", MemberResultMessage.CANCELVIP_SUCCESS, result);
	}
	
	@Test
	public void testgetinfo() throws RemoteException {
		MemberLogicService memberLogic=new MemerLogicServiceImpl();
		MemberVO testvo=new MemberVO("12345678901", "Admin");
		memberLogic.changeInfo(testvo);
		MemberVO resultvo=memberLogic.getInfo(1);
		Assert.assertEquals("Right message.", "name:Admin; phone:12345678901; credit:0", resultvo.toString());
	}
	
	@Test
	public void testchangeinfo() throws RemoteException {
		MemberLogicService memberLogic=new MemerLogicServiceImpl();
		MemberResultMessage result=null;
		MemberVO testvo=new MemberVO("12345678901", "None");
		result=memberLogic.changeInfo(testvo);
	//	Assert.assertEquals("Change info fail. No such ID.", MemberResultMessage.CHANGEINFO_FAIL_WORNDID, result);
		testvo.setName("Admin");
		result=memberLogic.changeInfo(testvo);
	//	Assert.assertEquals("Change info success.", MemberResultMessage.CHANGEINFO_SUCCESS, result);
	}
	
	@Test
	public void testmanageinfo() throws RemoteException {
		MemberLogicService memberLogic=new MemerLogicServiceImpl();
		MemberVO testvo=new MemberVO("12345678901", "Admin");
		memberLogic.changeInfo(testvo);
		ListWrapper<MemberVO> resultlist=memberLogic.manageInfo();
		MemberVO resultvo=resultlist.iterator().next();
		Assert.assertEquals("Right message.", "name:Admin; phone:12345678901; credit:0", resultvo.toString());
	}
}
