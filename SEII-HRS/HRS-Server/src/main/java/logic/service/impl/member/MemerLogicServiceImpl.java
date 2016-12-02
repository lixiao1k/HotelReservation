package logic.service.impl.member;

import java.rmi.RemoteException;

import info.ListWrapper;
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
	public ListWrapper<MemberVO> manageInfo() throws RemoteException {
		return null;
	}
}
