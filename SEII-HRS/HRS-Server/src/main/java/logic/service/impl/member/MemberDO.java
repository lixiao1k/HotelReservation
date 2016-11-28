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
		UserPO upo=null;
		upo=memberDao.getInfo(vo.getUserId());
		if(upo==null){
			return MemberResultMessage.REGISTERVIP_FAIL_WRONGID;
		}
		if(upo.getCredit()<10000){
			return MemberResultMessage.REGISTERVIP_FAIL_CREDITNOTENOUGH;
		}
		VIPPO vpo=null;
		vpo=memberDao.getVIPInfo(vo.getUserId());
		if(vpo!=null){
			return MemberResultMessage.REGISTERVIP_FAIL_ALREADYVIP;
		}
		VIPPO po=DozerMappingUtil.getInstance().map(vo, VIPPO.class);
		memberDao.registerVIP(po);
		return MemberResultMessage.REGISTERVIP_SUCCESS;
		
	}
	
	public MemberResultMessage cancel(long id) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		UserPO upo=null;
		upo=memberDao.getInfo(id);
		if(upo==null){
			return MemberResultMessage.CANCELVIP_FAIL_WRONGID;
		}
		if(upo.getCredit()>=10000){
			return MemberResultMessage.CANCELVIP_FAIL_CREDITENOUGH;
		}
		VIPPO vpo=null;
		vpo=memberDao.getVIPInfo(id);
		if(vpo==null){
			return MemberResultMessage.CANCELVIP_FAIL_NOTVIP;
		}
		memberDao.cancel(id);
		return MemberResultMessage.CANCELVIP_SUCCESS;
	}

	public MemberVO getInfo(long id) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		UserPO upo=null;
		upo=memberDao.getInfo(id);
		return DozerMappingUtil.getInstance().map(upo,MemberVO.class);
	}

	public MemberResultMessage changeInfo(MemberVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		UserPO upo=null;
		upo=memberDao.getInfo(vo.getId());
		if(upo==null){
			return MemberResultMessage.CHANGEINFO_FAIL_WORNDID;
		}
		UserPO po=DozerMappingUtil.getInstance().map(vo, UserPO.class);
		memberDao.update(po);
		return MemberResultMessage.CHANGEINFO_SUCCESS;
	}

	public ListWrapper<MemberVO> manageInfo() throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
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
