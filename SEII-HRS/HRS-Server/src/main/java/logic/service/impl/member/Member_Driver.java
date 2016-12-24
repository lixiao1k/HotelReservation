package logic.service.impl.member;

import java.rmi.RemoteException;

import logic.service.MemberLogicService;
import vo.BasicMemberVO;
import vo.ManageClientVO;
import vo.ManageHotelWorkerVO;
import vo.ManageWEBSalerVO;
import vo.VIPVO;

public class Member_Driver {
	public void drive(MemberLogicService service) throws RemoteException{
		VIPVO vo = new VIPVO();
		vo.setUserId(1);
		service.registerVIP(vo);
		vo.setUserId(2);
		service.registerVIP(vo);
		vo.setUserId(3);
		service.registerVIP(vo);
		vo.setUserId(4);
		service.registerVIP(vo);
		service.cancel(1);
		service.cancel(2);
		service.getClient(null);
		service.getClient("11");
		service.getAllHotelWorker("ИзјТ");
		service.getAllHotelWorker("1");
		service.getWEBSaler(null);
		service.getWEBSaler("111");
		ManageWEBSalerVO mwvo = new ManageWEBSalerVO();
		mwvo.setUsername("123");
		service.addWEBSaler(mwvo);
		mwvo.setUsername("111");
		service.addWEBSaler(mwvo);
		BasicMemberVO bmvo = new BasicMemberVO(1, "1", "1");
		service.changeInfo(bmvo);
		bmvo.setUserid(2);
		service.changeInfo(bmvo);
		ManageClientVO mcvo = new ManageClientVO();
		mcvo.setUserid(1);
		service.updateClient(mcvo);
		mcvo.setUserid(2);
		service.updateClient(mcvo);
		ManageHotelWorkerVO mhwvo = new ManageHotelWorkerVO(1, 1, "1", "1", "1");
		service.updateHotelWorker(mhwvo);
		mhwvo.setUserid(2);
		service.updateHotelWorker(mhwvo);
		ManageWEBSalerVO mwsvo = new ManageWEBSalerVO();
		mwsvo.setUserid(1);
		service.updateWEBSaler(mwsvo);
		mwsvo.setUserid(2);
		service.updateWEBSaler(mwsvo);
		service.delete(1);
		service.delete(2);
		service.getInfo(1);
		service.getInfo(2);
		service.setVIPscale(0);
		service.setVIPscale(1);
	}
}
