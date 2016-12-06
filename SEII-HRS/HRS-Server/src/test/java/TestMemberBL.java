 import java.rmi.RemoteException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import info.ListWrapper;
import logic.service.CreditLogicService;
import logic.service.HotelLogicService;
import logic.service.MemberLogicService;
import logic.service.impl.credit.CreditLogicServiceImpl;
import logic.service.impl.hotel.HotelLogicServiceImpl;
import logic.service.impl.member.MemberLogicServiceImpl;
import resultmessage.MemberResultMessage;
import vo.AddHotelVO;
import vo.ManageClientVO;
import vo.ManageHotelVO;
import vo.ManageHotelWorkerVO;
import vo.ManageWEBSalerVO;
import vo.MemberVO;
import vo.VIPVO;

public class TestMemberBL {
	@Test
	public void testregisterVIP() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		CreditLogicService creditLogic=new CreditLogicServiceImpl();
		creditLogic.excharge(1, 10000);
		MemberResultMessage result=null;
		VIPVO testvo=null;
		LocalDate time=LocalDate.of(1970, 1, 1);
		testvo=new VIPVO(1, time, 1, "Bingyuhuo");
		result=memberLogic.registerVIP(testvo);
		result=memberLogic.registerVIP(testvo);
		Assert.assertEquals("Fail in registerVIP alreadyVIP test.", MemberResultMessage.FAIL_ALREADYVIP, result);
		memberLogic.cancel(1);
		testvo.setUserId(0);
		result=memberLogic.registerVIP(testvo);
		Assert.assertEquals("Fail in registerVIP wrong ID test.", MemberResultMessage.FAIL_WRONGID, result);
		creditLogic.excharge(1, -10000);
		testvo.setUserId(1);
		memberLogic.cancel(1);
		result=memberLogic.registerVIP(testvo);
		Assert.assertEquals("Fail in registerVIP credit not enough test.", MemberResultMessage.FAIL_CREDITNOTENOUGH, result);
	}
	
	@Test
	public void testcancel() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		CreditLogicService creditLogic=new CreditLogicServiceImpl();
		MemberResultMessage result=null;	
		result=memberLogic.cancel(1);
		Assert.assertEquals("Fail in cancelVIP not VIP test.", MemberResultMessage.FAIL_NOTVIP, result);
		creditLogic.excharge(1, 10000);
		VIPVO testvo=null;
		LocalDate time=LocalDate.of(1970, 1, 1);
		testvo=new VIPVO(1, time, 1, "Bingyuhuo");
		memberLogic.registerVIP(testvo);
		result=memberLogic.cancel(1);
		Assert.assertEquals("Fail in cancelVIP enough credit test.", MemberResultMessage.FAIL, result);
		result=memberLogic.cancel(2);
		Assert.assertEquals("Fail in cancelVIP wrong ID test.", MemberResultMessage.FAIL_WRONGID, result);
		creditLogic.excharge(1, -10000);
		result=memberLogic.cancel(1);
	}
	
	@Test
	public void testClient() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		LocalDate testbirth=LocalDate.of(1970, 1, 1);
		ManageClientVO testvo=new ManageClientVO(2, "bingyuhuo", testbirth, "bingyuhuo", null);
		//memberLogic.addClient(testvo);
		testvo.addPhonenumber("12345678901");
		testvo.setUserid(-1);
		resultmessage=memberLogic.updateClient(testvo);
		Assert.assertEquals("Fail in client wrong ID test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
		memberLogic.delete(2);
	}

	@Test
	public void testHotelWorker() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		HotelLogicService hotelLogic=new HotelLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		AddHotelVO testhotelvo=new AddHotelVO("jiangyoujiudian", null, null, "jiangyoulu", null, null, null, null, null);
		ManageHotelWorkerVO testvo=new ManageHotelWorkerVO(1, 3, "jiangyoujun", "jiangyou", "jiangyou");
		//memberLogic.addHotelWorker(testvo);
		testvo.setUsername("jiangyoujunjun");
		testvo.setHotelid(-1);
		resultmessage=memberLogic.updateHotelWorker(testvo);
		Assert.assertEquals("Fail in hotelworker wrong ID test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
		memberLogic.delete(3);
	}
	
	public void testWEBSaler() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		ManageWEBSalerVO testvo=new ManageWEBSalerVO(4, "pianzijun", "pianzi", "pianzi");
		memberLogic.addWEBSaler(testvo);
		testvo.setUsername("pianzijunjun");
		testvo.setUserid(-1);
		resultmessage=memberLogic.updateWEBSaler(testvo);
		Assert.assertEquals("Fail in WEBSaler wrong ID test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
		memberLogic.delete(4);
	}
	
	@Test
	public void testdelete() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		LocalDate testbirth=LocalDate.of(1970, 1, 1);
		ManageClientVO testclientvo=new ManageClientVO(2, "bingyuhuo", testbirth, "bingyuhuo", null);
	//	memberLogic.addClient(testclientvo);
		resultmessage=memberLogic.delete(2);
		Assert.assertEquals("Fail in delete client test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
		ManageHotelWorkerVO testhotelworkervo=new ManageHotelWorkerVO(1, 3, "jiangyoujun", "jiangyou", "jiangyou");
	//	memberLogic.addHotelWorker(testhotelworkervo);
		resultmessage=memberLogic.delete(3);
		Assert.assertEquals("Fail in delete hotelworker test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
		ManageWEBSalerVO testwebsalervo=new ManageWEBSalerVO(4, "pianzijun", "pianzi", "pianzi");
		memberLogic.addWEBSaler(testwebsalervo);
		resultmessage=memberLogic.delete(4);
		Assert.assertEquals("Fail in delete WEBSaler test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
	}
}
