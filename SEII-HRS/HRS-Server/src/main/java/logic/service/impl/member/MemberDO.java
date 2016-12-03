package logic.service.impl.member;

import java.rmi.RemoteException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.hibernate.engine.HibernateIterator;

import data.dao.MemberDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.ListWrapper;
import info.VIPType;
import po.ClientMemberPO;
import po.MemberPO;
import po.VIPPO;
import resultmessage.MemberResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.ManageClientVO;
import vo.ManageHotelVO;
import vo.ManageHotelWorkerVO;
import vo.ManageWEBSalerVO;
import vo.MemberVO;
import vo.VIPVO;

public class MemberDO {
	private MemberDao memberDao;
	private Cache<MemberPO> members;
	private final int VipCredit = 10000;
	private VIPType[] vipType = {};
	public MemberDO() {
		memberDao=DaoManager.getInstance().getMemberDao();
		members = new Cache<>(20);
	}
	public MemberDO(int size){
		memberDao=DaoManager.getInstance().getMemberDao();
		members = new Cache<>(size);
	}
	public MemberResultMessage registerVIP(VIPVO vo) throws RemoteException {
		MemberPO cachePO = null;
		cachePO = members.get(vo.getUserId());
		boolean flag = false;
		if (cachePO!=null){
			if(cachePO instanceof ClientMemberPO){
				ClientMemberPO cmpo = (ClientMemberPO)cachePO;
				if(cmpo.isVip())
					return MemberResultMessage.FAIL_ALREADYVIP;
				else{
					if(cmpo.getCredit()<VipCredit)
						return MemberResultMessage.FAIL_CREDITNOTENOUGH;
					else{
						try{
							HibernateUtil.getCurrentSession()
											.beginTransaction();
							flag = true;
							cmpo.setVip(true);
							VIPPO vippo = DozerMappingUtil.getInstance().map(vo, VIPPO.class);
							vippo.setType(vipType[vo.getType()]);
							cmpo.setVipInfo(vippo);
							members.remove(vo.getUserId());
							members.put(vo.getUserId(), cmpo);
							memberDao.update(cmpo);
							HibernateUtil.getCurrentSession()
											.getTransaction()
											.commit();
							return MemberResultMessage.SUCCESS;
						}catch(RuntimeException e){
							members.remove(vo.getUserId());
							try{
								HibernateUtil.getCurrentSession()
												.getTransaction()
												.rollback();
								return MemberResultMessage.FAIL;
							}catch(RuntimeErrorException ex){
								ex.printStackTrace();
							}
							throw e;
						}
				
					}
				}
			}else
				return MemberResultMessage.FAIL_WRONGID;
		}else{
			try{
				if(!flag)
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				MemberPO po = memberDao.getInfo(vo.getUserId());
				if(po==null)
					return MemberResultMessage.FAIL_WRONGID;
				else{
					if(po instanceof ClientMemberPO){
						if(((ClientMemberPO) po).isVip())
							return MemberResultMessage.FAIL_ALREADYVIP;
						else{
							if(((ClientMemberPO) po).getCredit()<VipCredit)
								return MemberResultMessage.FAIL_CREDITNOTENOUGH;
							else{
								((ClientMemberPO) po).setVip(true);
								VIPPO vippo = DozerMappingUtil.getInstance().map(vo, VIPPO.class);
								vippo.setType(vipType[vo.getType()]);
								((ClientMemberPO) po).setVipInfo(vippo);
								memberDao.update(po);
								members.put(vo.getUserId(), po);
								HibernateUtil.getCurrentSession()
												.getTransaction()
												.commit();
								return MemberResultMessage.SUCCESS;
							}
						}
					}else
						return MemberResultMessage.FAIL_WRONGID;
				}
			}catch(RuntimeException e){
				members.remove(vo.getUserId());
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return MemberResultMessage.FAIL;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
	}
	
	public MemberResultMessage cancel(long id) throws RemoteException {
		MemberPO cachePO = null;
		cachePO = members.get(id);
		boolean flag = false;
		if(cachePO!=null){
			if(cachePO instanceof ClientMemberPO){
				cachePO = (ClientMemberPO) cachePO;
				if(((ClientMemberPO) cachePO).isVip()){
					if(((ClientMemberPO) cachePO).getCredit()>VipCredit)
						return MemberResultMessage.FAIL;
					else{
						try{
							HibernateUtil.getCurrentSession()
											.beginTransaction();
							
							((ClientMemberPO) cachePO).setVipInfo(null);
							((ClientMemberPO) cachePO).setVip(false);
							memberDao.update(cachePO);
							HibernateUtil.getCurrentSession()
											.getTransaction()
											.commit();
							return MemberResultMessage.SUCCESS;
						}catch(RuntimeException e){
							members.remove(id);
							try{
								HibernateUtil.getCurrentSession()
												.getTransaction()
												.rollback();
								return MemberResultMessage.FAIL;
							}catch(RuntimeErrorException ex){
								ex.printStackTrace();
							}
							throw e;
						}
					
						
					}
				}else
					return MemberResultMessage.FAIL_NOTVIP;
			}else
				return MemberResultMessage.FAIL_WRONGID;
		}else{
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				MemberPO po = memberDao.getInfo(id);
				if(po!=null&&po instanceof ClientMemberPO){
					po = (ClientMemberPO) po;
					if(((ClientMemberPO)po).isVip()){
						if(((ClientMemberPO)po).getCredit()>VipCredit)
							return MemberResultMessage.FAIL;
						else{
							((ClientMemberPO)po).setVip(false);
							((ClientMemberPO)po).setVipInfo(null);
							memberDao.update(po);
							members.put(id, po);
							HibernateUtil.getCurrentSession().getTransaction().commit();
							return MemberResultMessage.SUCCESS;
						}
					}else
						return MemberResultMessage.FAIL_NOTVIP;
				}else
					return MemberResultMessage.FAIL_WRONGID;
			}catch(RuntimeException e){
				members.remove(id);
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return MemberResultMessage.FAIL;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
	}

	public MemberVO getInfo(long id) throws RemoteException {
		MemberPO cachePO = null;
		cachePO = members.get(id);
		if(cachePO!=null){
			MemberVO vo = DozerMappingUtil.getInstance().map(cachePO, MemberVO.class);
			return vo;
		}else{
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				MemberPO po = memberDao.getInfo(id);
				members.put(id, po);
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
				MemberVO vo = DozerMappingUtil.getInstance().map(cachePO, MemberVO.class);
				return vo;
			}catch(RuntimeException e){
				members.remove(id);
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return null;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
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
		return MemberResultMessage.SUCCESS;
	}
	
	public MemberResultMessage addHotelWorker(ManageHotelWorkerVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		memberDao.add(po);
		return MemberResultMessage.SUCCESS;
	}
	
	public MemberResultMessage addWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		memberDao.add(po);
		return MemberResultMessage.SUCCESS;
	}
	
	public MemberResultMessage updateClient(ManageClientVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		if(po==null){
			return MemberResultMessage.FAIL_WRONGID;
		}else{
			memberDao.update(po);
			return MemberResultMessage.SUCCESS;
		}
	}
	
	public MemberResultMessage updateHotelWorker(ManageHotelWorkerVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		if(po==null){
			return MemberResultMessage.FAIL_WRONGID;
		}else{
			memberDao.update(po);
			return MemberResultMessage.SUCCESS;
		}
	}
	
	public MemberResultMessage updateWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
		if(po==null){
			return MemberResultMessage.FAIL_WRONGID;
		}else{
			memberDao.update(po);
			return MemberResultMessage.SUCCESS;
		}
	}
	
	public MemberResultMessage delete(long id) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		MemberPO po=null;
		po=memberDao.getInfo(id);
		if(po==null){
			return MemberResultMessage.FAIL_WRONGID;
		}else{
			memberDao.delete(id);
			return MemberResultMessage.SUCCESS;
		}
	}
}
