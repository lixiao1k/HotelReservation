package logic.service.impl.member;

import java.rmi.RemoteException;

import info.ListWrapper;
import logic.service.ManageClientVO;
import logic.service.ManageHotelVO;
import logic.service.ManageHotelWorkerVO;
import logic.service.ManageWEBSalerVO;
import logic.service.MemberLogicService;
import resultmessage.MemberResultMessage;
import vo.MemberVO;
import vo.VIPVO;

public class MemerLogicServiceImpl implements MemberLogicService{
	private MemberDO memberDO;
	public MemerLogicServiceImpl() {
		memberDO=new MemberDO();
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
	public MemberVO getInfo(long id) throws RemoteException {
		return memberDO.getInfo(id);
	}

	@Override
	public MemberResultMessage changeInfo(MemberVO vo) throws RemoteException {
		return null;
	}

	@Override
	public ManageClientVO getClient(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListWrapper<ManageHotelVO> getAllHotelWorker(String hotelname) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ManageWEBSalerVO getWEBSaler(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberResultMessage addClient(ManageClientVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberResultMessage addHotelWorker(ManageHotelWorkerVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberResultMessage addWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberResultMessage updateClient(ManageClientVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberResultMessage updateHotelWorker(ManageHotelWorkerVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberResultMessage updateWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MemberResultMessage delete(long id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
