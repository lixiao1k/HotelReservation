package logic.service.impl.member;

import java.rmi.RemoteException;

import javax.naming.ldap.ManageReferralControl;

import com.sun.prism.impl.ManagedResource;

import info.ListWrapper;
import logic.service.MemberLogicService;
import resultmessage.MemberResultMessage;
import vo.BasicMemberVO;
import vo.ManageClientVO;
import vo.ManageHotelVO;
import vo.ManageHotelWorkerVO;
import vo.ManageWEBSalerVO;
import vo.MemberVO;
import vo.VIPVO;

public class Member_Stub implements MemberLogicService{

	@Override
	public MemberResultMessage registerVIP(VIPVO vo) throws RemoteException {
		if(vo.getUserId()==1){
			System.out.println("Member.registerVIP  :  vo.getUserId()==1|return MemberResultMessage.FAIL_WRONGID");
			return MemberResultMessage.FAIL_WRONGID;
		}
		if(vo.getUserId()==2){
			System.out.println("Member.registerVIP  :  vo.getUserId()==2|return MemberResultMessage.FAIL_CREDITNOTENOUGH");
			return MemberResultMessage.FAIL_CREDITNOTENOUGH;
		}
		if(vo.getUserId()==3){
			System.out.println("Member.registerVIP  :  vo.getUserId()==3|return MemberResultMessage.FAIL_ALREADYVIP");
			return MemberResultMessage.FAIL_ALREADYVIP;
		}
		System.out.println("Member.registerVIP  :  vo.getUserId()==other number|return MemberResultMessage.SUCCESS");
		return MemberResultMessage.SUCCESS;
	}

	@Override
	public MemberResultMessage cancel(long id) throws RemoteException {
		if(id==1){
			System.out.println("Member.cancel  :  id==1|return MemberResultMessage.FAIL_NOTVIP");
			return MemberResultMessage.FAIL_NOTVIP;
		}
		System.out.println("Member.cancel  :  id==other number|return MemberResultMessage.SUCCESS");
		return MemberResultMessage.SUCCESS;
	}

	@Override
	public ManageClientVO getClient(String username) throws RemoteException {
		ManageClientVO mcvo = new ManageClientVO();
		
		if(username==null||username.equals("\\s")){
			System.out.println("Member.getClient  :  username==null||username.equals(\"\\s\")|return null");
			return null;
		}
		System.out.println("Member.getClient  :  normal info|return normal result");
		mcvo.setUserid(1);
		return mcvo;
	}

	@Override
	public ListWrapper<ManageHotelVO> getAllHotelWorker(String hotelname) throws RemoteException {
		ListWrapper<ManageHotelVO> result = new ListWrapper<>();
		if(hotelname.equals("ИзјТ")){
			System.out.println("Member.getAllHotelWorker  :  normal info|return normal result");
			ManageHotelVO h1 = new ManageHotelVO();
			result.add(h1);
			return result;
		}
		System.out.println("Member.getAllHotelWorker  :  abnormal info|return null");
		return null;
	}

	@Override
	public ManageWEBSalerVO getWEBSaler(String username) throws RemoteException {
		if(username==null||username.equals("\\s")){
			System.out.println("Member.getWEBSaler  :  username==null||username.equals(\"\\s\")|return null");
			return null;
		}
		ManageWEBSalerVO mvo = new ManageWEBSalerVO();
		System.out.println("Member.getWEBSaler  :  normal info|return normal result");
		return mvo;
	}

	@Override
	public MemberResultMessage addWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		if(vo.getUsername().equals("123")){
			System.out.println("Member.addWEBSaler  :  vo.getUsername().equals(\"123\")|return MemberResultMessage.FAIL_SAMEID");
			return MemberResultMessage.FAIL_SAMEID;
		}
			
		System.out.println("Member.addWEBSaler  :  normal info|return MemberResultMessage.SUCCESS");
		return MemberResultMessage.SUCCESS;
	}

	@Override
	public MemberResultMessage changeInfo(BasicMemberVO vo) throws RemoteException {
		if(vo.getUserid()==1){
			System.out.println("Member.changeInfo  :  vo.getUserid()==1|return MemberResultMessage.FAIL_WRONGID");
			return MemberResultMessage.FAIL_WRONGID;
		}
		System.out.println("Member.changeInfo  :  normal info|return MemberResultMessage.SUCCESS");
		return MemberResultMessage.SUCCESS;
	}

	@Override
	public MemberResultMessage updateClient(ManageClientVO vo) throws RemoteException {
		if(vo.getUserid()==1){
			System.out.println("Member.updateClient  :  vo.getUserid()==1|return MemberResultMessage.FAIL_WRONGID");
			return MemberResultMessage.FAIL_WRONGID;
		}
		System.out.println("Member.updateClient  :  normal info|return MemberResultMessage.SUCCESS");
		return MemberResultMessage.SUCCESS;
	}

	@Override
	public MemberResultMessage updateHotelWorker(ManageHotelWorkerVO vo) throws RemoteException {
		if(vo.getUserid()==1){
			System.out.println("Member.updateHotelWorker  :  vo.getUserid()==1|return MemberResultMessage.FAIL_WRONGID");
			return MemberResultMessage.FAIL_WRONGID;
		}
		System.out.println("Member.updateHotelWorker  :  normal info|return MemberResultMessage.SUCCESS");
		return MemberResultMessage.SUCCESS;
	}

	@Override
	public MemberResultMessage updateWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		if(vo.getUserid()==1){
			System.out.println("Member.updateWEBSaler  :  vo.getUserid()==1|return MemberResultMessage.FAIL_WRONGID");
			return MemberResultMessage.FAIL_WRONGID;
		}
		System.out.println("Member.updateWEBSaler  :  normal info|return MemberResultMessage.SUCCESS");
		return MemberResultMessage.SUCCESS;
	}

	@Override
	public MemberResultMessage delete(long id) throws RemoteException {
		if(id==1){
			System.out.println("Member.delete  :  id==1|return MemberResultMessage.FAIL_WRONGID");
			return MemberResultMessage.FAIL_WRONGID;
		}
		System.out.println("Member.delete  :  normal info|return MemberResultMessage.SUCCESS");
		return MemberResultMessage.SUCCESS;
	}

	@Override
	public MemberVO getInfo(long userId) throws RemoteException {
		if(userId==1){
			System.out.println("Member.getInfo  :  userId==1|return null");
			return null;
		}
		System.out.println("Member.getInfo  :  normal info|return normal result");
		return new MemberVO();
	}

	@Override
	public void setVIPscale(int scale) throws RemoteException {
		if(scale<=0)
			System.out.println("wrong");
		else
			System.out.println("success");
	}

}
