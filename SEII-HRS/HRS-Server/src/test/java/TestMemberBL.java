 import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import info.BusinessCircle;
import info.BusinessCity;
import info.Rank;
import info.Room;
import info.VIPType;
import logic.service.CreditLogicService;
import logic.service.HotelLogicService;
import logic.service.MemberLogicService;
import logic.service.UserLogicService;
import logic.service.impl.credit.CreditLogicServiceImpl;
import logic.service.impl.hotel.HotelLogicServiceImpl;
import logic.service.impl.member.MemberLogicServiceImpl;
import logic.service.impl.user.UserLogicServiceImpl;
import resultmessage.MemberResultMessage;
import vo.AddHotelVO;
import vo.HotelItemVO;
import vo.ManageClientVO;
import vo.ManageHotelWorkerVO;
import vo.ManageWEBSalerVO;
import vo.VIPVO;

public class TestMemberBL {
	@Test
	public void testregisterVIP() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		UserLogicService userLogic=new UserLogicServiceImpl();
		CreditLogicService creditLogic=new CreditLogicServiceImpl();
		userLogic.register("bingyuhuo2", "bingyuhuo2");
		creditLogic.excharge(2, 100);
		MemberResultMessage result=null;
		VIPVO testvo=null;
		LocalDate time=LocalDate.of(1970, 1, 1);
		testvo=new VIPVO(VIPType.PERSON, time, 2, "bingyuhuo");
		result=memberLogic.registerVIP(testvo);
		result=memberLogic.registerVIP(testvo);
		Assert.assertEquals("Fail in registerVIP alreadyVIP test.", MemberResultMessage.FAIL_ALREADYVIP, result);
		memberLogic.cancel(2);
		testvo.setUserId(0);
		result=memberLogic.registerVIP(testvo);
		Assert.assertEquals("Fail in registerVIP wrong ID test.", MemberResultMessage.FAIL_WRONGID, result);
		memberLogic.setVIPscale(12000);
		testvo.setUserId(2);
		memberLogic.cancel(2);
		result=memberLogic.registerVIP(testvo);
		Assert.assertEquals("Fail in registerVIP credit not enough test.", MemberResultMessage.FAIL_CREDITNOTENOUGH, result);
	}
	
	@Test
	public void testcancel() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		UserLogicService userLogic=new UserLogicServiceImpl();
		CreditLogicService creditLogic=new CreditLogicServiceImpl();
		userLogic.register("bingyuhuo", "bingyuhuo");
		MemberResultMessage result=null;	
		result=memberLogic.cancel(1);
		Assert.assertEquals("Fail in cancelVIP not VIP test.", MemberResultMessage.FAIL_NOTVIP, result);
		VIPVO testvo=null;
		LocalDate time=LocalDate.of(1970, 1, 1);
		testvo=new VIPVO(VIPType.PERSON, time, 1, "bingyuhuo");
		creditLogic.excharge(1, 100);
		memberLogic.registerVIP(testvo);
		result=memberLogic.cancel(1);
		Assert.assertEquals("Fail in cancelVIP enough credit test.", MemberResultMessage.FAIL, result);
		result=memberLogic.cancel(2);
		Assert.assertEquals("Fail in cancelVIP wrong ID test.", MemberResultMessage.FAIL_WRONGID, result);
	}
	
	@Test
	public void testClient() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		LocalDate testbirth=LocalDate.of(1970, 1, 1);
		List<String> phone=new ArrayList<>();
		ManageClientVO testvo=new ManageClientVO(2, "bingyuhuo22", testbirth, "bingyuhuo", phone);
		testvo.addPhonenumber("12345678901");
		testvo.setUserid(-1);
		resultmessage=memberLogic.updateClient(testvo);
		Assert.assertEquals("Fail in client wrong ID test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
	}

	@Test
	public void testHotelWorker() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		HotelLogicService hotelLogic=new HotelLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		BusinessCity bc = new BusinessCity();
		bc.setName("鍗椾含");
		bc.setBcityId(1);
		BusinessCircle bci = new BusinessCircle();
		bci.setBcircleId(1);
		bci.setName("澶у悓璺�");
		HotelItemVO hi = new HotelItemVO();
		hi.setNum(88);
		hi.setPrice(233);
		Room room = new Room("澶у簥鎴�");
		room.setRid(1);
		hi.setRoom(room);
		hi.setTotal(100);
		Set<HotelItemVO> items = new HashSet<>();
		items.add(hi);
		AddHotelVO vo = new AddHotelVO("濡傚","濡傚閰掑簵", "搴婏紝wifi", "姹熻嫃鐪佸崡浜競鏂拌鍙�233鍙�", "浣忔埧锛岄楗�", bci,  bc, Rank.THREE, items);
		vo.setUsername("NanjingXJKRuJia");
		vo.setPassword("123456");
		vo.setMemberName("灏忎父瀛�");
		hotelLogic.addHotel(vo);
		ManageHotelWorkerVO testvo=new ManageHotelWorkerVO(1, 4, "jiangyoujun", "jiangyou", "jiangyou");
		testvo.setUsername("jiangyoujunjun");
		testvo.setHotelid(-1);
		resultmessage=memberLogic.updateHotelWorker(testvo);
		Assert.assertEquals("Fail in hotelworker wrong ID test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
	}
	
	@Test
	public void testWEBSaler() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		ManageWEBSalerVO testvo=new ManageWEBSalerVO(3, "pianzijun", "pianzi", "pianzi");
		memberLogic.addWEBSaler(testvo);
		testvo.setUsername("pianzijunjun");
		testvo.setUserid(-1);
		resultmessage=memberLogic.updateWEBSaler(testvo);
		Assert.assertEquals("Fail in WEBSaler wrong ID test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
	}
	
	@Test
	public void testdelete() throws RemoteException {
		MemberLogicService memberLogic=new MemberLogicServiceImpl();
		MemberResultMessage resultmessage=null;
		resultmessage=memberLogic.delete(2);
		Assert.assertEquals("Fail in delete client test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
		resultmessage=memberLogic.delete(3);
		Assert.assertEquals("Fail in delete hotelworker test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
		resultmessage=memberLogic.delete(4);
		Assert.assertEquals("Fail in delete WEBSaler test.", MemberResultMessage.FAIL_WRONGID, resultmessage);
	}
}
