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
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
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
	public void testClient() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		LocalDate testbirth=LocalDate.of(1970, 1, 1);
		ManageClientVO testvo=new ManageClientVO(2, "bingyuhuo", testbirth, "bingyuhuo", null);
		memberLogic.addClient(testvo);
		ManageClientVO getvo=memberLogic.getClient("bingyuhuo");
		Assert.assertEquals("Right message.", "ManageClientVO [userid=2, username=bingyuhuo, birthday=1970-01-01, companyname=bingyuhuo, phonenumber=]", getvo.toString());
		testvo.addPhonenumber("12345678901");
		testvo.setUserid(-1);
		resultmessage=memberLogic.updateClient(testvo);
		Assert.assertEquals("Update fail. Wrong ID.", MemberResultMessage.CHANGEINFO_FAIL_WORNDID, resultmessage);
		testvo.setUserid(2);
		resultmessage=memberLogic.updateClient(testvo);
		Assert.assertEquals("Update success.", MemberResultMessage.CHANGEINFO_SUCCESS, resultmessage);
		getvo=memberLogic.getClient("bingyuhuo");
		Assert.assertEquals("Right message.", "ManageClientVO [userid=2, username=bingyuhuo, birthday=1970-01-01, companyname=bingyuhuo, phonenumber=12345678901]", getvo.toString());
	}

	@Test
	public void testHotelWorker() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		HotelLogicService hotelLogic=new HotelLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		AddHotelVO testhotelvo=new AddHotelVO("jiangyoujiudian", null, null, "jiangyoulu", null, null, null, null, null);
		ManageHotelWorkerVO testvo=new ManageHotelWorkerVO(1, 3, "jiangyoujun", "jiangyou", "jiangyou");
		memberLogic.addHotelWorker(testvo);
		ListWrapper<ManageHotelVO> getvo=memberLogic.getAllHotelWorker("jiangyoujiudian");
		ManageHotelVO resultvo=getvo.iterator().next();
		Assert.assertEquals("Right message.", "ManageHotelVO [hotelname=jiangyoujiudian, address=jiangyoulu, bussinesscity=, bussinesscircle=, hotelid=1, userid=3, username=jiangyoujun, password=jiangyou, name=jiangyou]", resultvo.toString());
		testvo.setUsername("jiangyoujunjun");
		testvo.setHotelid(-1);
		resultmessage=memberLogic.updateHotelWorker(testvo);
		Assert.assertEquals("Update fail. Wrong ID.", MemberResultMessage.CHANGEINFO_FAIL_WORNDID, resultmessage);
		testvo.setHotelid(1);
		resultmessage=memberLogic.updateHotelWorker(testvo);
		Assert.assertEquals("Update success.", MemberResultMessage.CHANGEINFO_SUCCESS, resultmessage);
		getvo=memberLogic.getAllHotelWorker("jiangyoujiudian");
		resultvo=getvo.iterator().next();
		Assert.assertEquals("Right message.", "ManageHotelVO [hotelname=jiangyoujiudian, address=jiangyoulu, bussinesscity=, bussinesscircle=, hotelid=1, userid=3, username=jiangyoujunjun, password=jiangyou, name=jiangyou]", resultvo.toString());
	}
	
	@Test
<<<<<<< HEAD
	public void testchangeinfo() throws RemoteException {
		MemberLogicService memberLogic=new MemerLogicServiceImpl();
		MemberResultMessage result=null;
		MemberVO testvo=new MemberVO("12345678901", "None");
		result=memberLogic.changeInfo(testvo);
	//	Assert.assertEquals("Change info fail. No such ID.", MemberResultMessage.CHANGEINFO_FAIL_WORNDID, result);
		testvo.setName("Admin");
		result=memberLogic.changeInfo(testvo);
	//	Assert.assertEquals("Change info success.", MemberResultMessage.CHANGEINFO_SUCCESS, result);
=======
	public void testWEBSaler() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		ManageWEBSalerVO testvo=new ManageWEBSalerVO(4, "pianzijun", "pianzi", "pianzi");
		memberLogic.addWEBSaler(testvo);
		ManageWEBSalerVO getvo=memberLogic.getWEBSaler("pianzijun");
		Assert.assertEquals("Right message.", "ManageWEBSalerVO [userid=4, username=pianzijun, password=pianzi, name=pianzi]", getvo.toString());
		testvo.setUsername("pianzijunjun");
		testvo.setUserid(-1);
		resultmessage=memberLogic.updateWEBSaler(testvo);
		Assert.assertEquals("Update fail. Wrong ID.", MemberResultMessage.CHANGEINFO_FAIL_WORNDID, resultmessage);
		testvo.setUserid(4);
		resultmessage=memberLogic.updateWEBSaler(testvo);
		Assert.assertEquals("Update success.", MemberResultMessage.CHANGEINFO_SUCCESS, resultmessage);
		getvo=memberLogic.getWEBSaler("pianzijunjun");
		Assert.assertEquals("Right message.", "ManageWEBSalerVO [userid=4, username=pianzijunjun, password=pianzi, name=pianzi]", getvo.toString());
>>>>>>> origin/master
	}
	
	@Test
	public void testdelete() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		LocalDate testbirth=LocalDate.of(1970, 1, 1);
		ManageClientVO testclientvo=new ManageClientVO(2, "bingyuhuo", testbirth, "bingyuhuo", null);
		memberLogic.addClient(testclientvo);
		resultmessage=memberLogic.delete(2);
		Assert.assertEquals("Delete client fail. No such ID.", MemberResultMessage.DELETE_FAIL_WRONGID, resultmessage);
		Assert.assertEquals("Delete client success.", MemberResultMessage.DELETE_SUCCESS, resultmessage);
		ManageHotelWorkerVO testhotelworkervo=new ManageHotelWorkerVO(1, 3, "jiangyoujun", "jiangyou", "jiangyou");
		memberLogic.addHotelWorker(testhotelworkervo);
		resultmessage=memberLogic.delete(3);
		Assert.assertEquals("Delete hotelworker fail. No such ID.", MemberResultMessage.DELETE_FAIL_WRONGID, resultmessage);
		Assert.assertEquals("Delete hotelworker success.", MemberResultMessage.DELETE_SUCCESS, resultmessage);
		ManageWEBSalerVO testwebsalervo=new ManageWEBSalerVO(4, "pianzijun", "pianzi", "pianzi");
		memberLogic.addWEBSaler(testwebsalervo);
		resultmessage=memberLogic.delete(4);
		Assert.assertEquals("Delete websaler fail. No such ID.", MemberResultMessage.DELETE_FAIL_WRONGID, resultmessage);
		Assert.assertEquals("Delete websaler success.", MemberResultMessage.DELETE_SUCCESS, resultmessage);
	}
}
