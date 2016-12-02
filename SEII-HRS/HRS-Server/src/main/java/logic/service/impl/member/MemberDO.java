package logic.service.impl.member;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.MemberDao;
import data.dao.Impl.DaoManager;
import info.ListWrapper;
import po.MemberPO;
import po.VIPPO;
import resultmessage.MemberResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.ManageClientVO;
import vo.ManageHotelVO;
import vo.ManageHotelWorkerVO;
import vo.ManageWEBSalerVO;
import vo.VIPVO;

public class MemberDO {
	private MemberDao memberDao;
	public MemberDO() {
		memberDao=DaoManager.getInstance().getMemberDao();
	}
	public MemberResultMessage registerVIP(VIPVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO upo=null;
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
		MemberPO upo=null;
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
	
	public ManageClientVO getClient(String username) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=memberDao.getInfo(username);
		return DozerMappingUtil.getInstance().map(po, ManageClientVO.class);
	}
	
	public ListWrapper<ManageHotelVO> getAllHotelWorker(String hotelname) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		ListWrapper<MemberPO> polist=memberDao.manageInfo(hotelname);
		List<ManageHotelVO> volist=null;
		while(polist.iterator().hasNext()){
			MemberPO po=polist.iterator().next();
			ManageHotelVO vo=DozerMappingUtil.getInstance().map(po, ManageHotelVO.class);
			volist.add(vo);
		}
		return DozerMappingUtil.getInstance().map(volist, ListWrapper.class);
	}
	
	public ManageWEBSalerVO getWEBSaler(String username) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=memberDao.getInfo(username);
		return DozerMappingUtil.getInstance().map(po, ManageWEBSalerVO.class);
	}
	
	public MemberResultMessage addClient(ManageClientVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		memberDao.add(po);
		return MemberResultMessage.CHANGEINFO_SUCCESS;
	}
	
	public MemberResultMessage addHotelWorker(ManageHotelWorkerVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		memberDao.add(po);
		return MemberResultMessage.CHANGEINFO_SUCCESS;
	}
	
	public MemberResultMessage addWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		memberDao.add(po);
		return MemberResultMessage.CHANGEINFO_SUCCESS;
	}
	
	public MemberResultMessage updateClient(ManageClientVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		if(po==null){
			return MemberResultMessage.CHANGEINFO_FAIL_WORNDID;
		}else{
			memberDao.update(po);
			return MemberResultMessage.CHANGEINFO_SUCCESS;
		}
	}
	
	public MemberResultMessage updateHotelWorker(ManageHotelWorkerVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		if(po==null){
			return MemberResultMessage.CHANGEINFO_FAIL_WORNDID;
		}else{
			memberDao.update(po);
			return MemberResultMessage.CHANGEINFO_SUCCESS;
		}
	}
	
	public MemberResultMessage updateWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		if(po==null){
			return MemberResultMessage.CHANGEINFO_FAIL_WORNDID;
		}else{
			memberDao.update(po);
			return MemberResultMessage.CHANGEINFO_SUCCESS;
		}
	}
	
	public MemberResultMessage delete(long id) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=memberDao.getInfo(id);
		if(po==null){
			return MemberResultMessage.DELETE_FAIL_WRONGID;
		}else{
			memberDao.delete(id);
			return MemberResultMessage.DELETE_SUCCESS;
		}
	}
}
