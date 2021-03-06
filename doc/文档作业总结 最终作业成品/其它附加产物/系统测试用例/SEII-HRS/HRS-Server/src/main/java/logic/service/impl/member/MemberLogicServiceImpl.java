package logic.service.impl.member;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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

public class MemberLogicServiceImpl extends UnicastRemoteObject implements MemberLogicService{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3051828411249225643L;
	private MemberDO memberDO;
	public MemberLogicServiceImpl() throws RemoteException {
		memberDO=new MemberDO();
	}
	public MemberLogicServiceImpl(int size) throws RemoteException{
		memberDO=new MemberDO(size);
	}
	@Override
	public MemberResultMessage registerVIP(VIPVO vo) throws RemoteException {
		return memberDO.registerVIP(vo);
	}

	@Override
	public MemberResultMessage cancel(long id) throws RemoteException {
		return memberDO.cancel(id);
	}
	@Override
	public ManageClientVO getClient(String username) throws RemoteException {
		return memberDO.getClient(username);
	}
	@Override
	public ListWrapper<ManageHotelVO> getAllHotelWorker(String hotelname) throws RemoteException {
		return memberDO.getAllHotelWorker(hotelname);
	}
	@Override
	public ManageWEBSalerVO getWEBSaler(String username) throws RemoteException {
		return memberDO.getWEBSaler(username);
	}
	@Override
	public MemberResultMessage addWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		return memberDO.addWEBSaler(vo);
	}
	@Override
	public MemberResultMessage updateClient(ManageClientVO vo) throws RemoteException {
		return memberDO.updateClient(vo);
	}
	@Override
	public MemberResultMessage updateHotelWorker(ManageHotelWorkerVO vo) throws RemoteException {
		return memberDO.updateHotelWorker(vo);
	}
	@Override
	public MemberResultMessage updateWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		return memberDO.updateWEBSaler(vo);
	}
	@Override
	public MemberResultMessage delete(long id) throws RemoteException {
		return memberDO.delete(id);
	}
	@Override
	public MemberVO getInfo(long userId) throws RemoteException {
		return memberDO.getInfo(userId);
	}
	@Override
	public void setVIPscale(int scale) throws RemoteException {
		memberDO.setVIPscale(scale);
	}
	@Override
	public MemberResultMessage changeInfo(BasicMemberVO vo) throws RemoteException {
		return memberDO.changeInfo(vo);
	}

}
