package logic.service.impl.member;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.MemberDao;
import data.dao.Impl.DaoManager;
import info.ListWrapper;
import po.UserPO;
import po.VIPPO;
import resultmessage.MemberResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.MemberVO;
import vo.VIPVO;

public class MemberDO {
	private MemberDao memberDao;
	public MemberDO() {
		memberDao=DaoManager.getInstance().getMemberDao();
	}
	public MemberResultMessage registerVIP(VIPVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		VIPPO po=DozerMappingUtil.getInstance().map(vo, VIPPO.class);
		memberDao.registerVIP(po);
		return MemberResultMessage.REGISTERVIP_SUCCESS;
	}
	
	public MemberResultMessage cancel(long id) throws RemoteException {
		memberDao.cancel(id);
		return MemberResultMessage.CANCELVIP_SUCCESS;
	}

	public MemberVO getInfo(long id) throws RemoteException {
		UserPO po=memberDao.getInfo(id);
		return DozerMappingUtil.getInstance().map(po,MemberVO.class);
	}

	public MemberResultMessage changeInfo(MemberVO vo) throws RemoteException {
		UserPO po=DozerMappingUtil.getInstance().map(vo, UserPO.class);
		memberDao.update(po);
		return MemberResultMessage.CHANGEINFO_SUCCESS;
	}

	public ListWrapper<MemberVO> manageInfo() throws RemoteException {
		ListWrapper<UserPO> polist=memberDao.manageInfo();
		List<MemberVO> volist=null;
		while(polist.iterator().hasNext()){
			UserPO po=polist.iterator().next();
			MemberVO vo=DozerMappingUtil.getInstance().map(po, MemberVO.class);
			volist.add(vo);
		}
		return DozerMappingUtil.getInstance().map(volist, ListWrapper.class);
	}
}
