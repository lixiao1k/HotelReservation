package logic.service.impl.member;

import java.rmi.RemoteException;
import java.util.List;

import javax.management.RuntimeErrorException;

import data.dao.MemberDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.ListWrapper;
import info.VIPType;
import po.ClientMemberPO;
import po.MemberPO;
import po.VIPPO;
import po.WebSalerMemberPO;
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
	public MemberVO getInfo(long userId){
		return null;
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

	public MemberVO getInfo(String username) throws RemoteException {
		MemberPO cachePO = null;
		cachePO = members.get(username);
		if(cachePO!=null){
			MemberVO vo = DozerMappingUtil.getInstance().map(cachePO, MemberVO.class);
			return vo;
		}else{
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				MemberPO po = memberDao.getInfo(username);
				if(po!=null){
					members.put(username, po);
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.commit();
					MemberVO vo = DozerMappingUtil.getInstance().map(po, MemberVO.class);
					return vo;
				}else{
					return null;
				}
			}catch(RuntimeException e){
				members.remove(username);
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
		return DozerMappingUtil.getInstance().map(getInfo(username), ManageClientVO.class);
	}
	
	public ListWrapper<ManageHotelVO> getAllHotelWorker(String hotelname) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			ListWrapper<MemberPO> polist=memberDao.manageInfo(hotelname);
			HibernateUtil.getCurrentSession().getTransaction().commit();
			List<ManageHotelVO> volist=null;
			while(polist.iterator().hasNext()){
				MemberPO po=polist.iterator().next();
				ManageHotelVO vo=DozerMappingUtil.getInstance().map(po, ManageHotelVO.class);
				volist.add(vo);
			}
			return new ListWrapper<ManageHotelVO>(volist);
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	
	public ManageWEBSalerVO getWEBSaler(String username) throws RemoteException {
		return DozerMappingUtil.getInstance().map(getInfo(username), ManageWEBSalerVO.class);
	}
	
	public MemberResultMessage addWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		MemberPO mpo=null;
		mpo=members.get(vo.getUserid());
		if(mpo!=null){
			return MemberResultMessage.FAIL_SAMEID;
		}
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			mpo=memberDao.getInfo(vo.getUserid());
			HibernateUtil.getCurrentSession().getTransaction().commit();
			if(mpo!=null){
				return MemberResultMessage.FAIL_SAMEID;
			}
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
		try{
			MemberPO po=null;
			po=DozerMappingUtil.getInstance().map(vo, MemberPO.class);
			if(po==null){
				return MemberResultMessage.FAIL_WRONGID;
			}else{
				HibernateUtil.getCurrentSession().beginTransaction();
				memberDao.add(po);
				HibernateUtil.getCurrentSession().getTransaction().commit();
				members.put(vo.getUserid(), po);
				return MemberResultMessage.SUCCESS;
			}
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	
	public MemberResultMessage updateVO (Object o) throws RemoteException {
		try{
			MemberPO po=null;
			if(o!=null){
				po=DozerMappingUtil.getInstance().map(o, MemberPO.class);
				if(po==null){
					return MemberResultMessage.FAIL_WRONGID;
				}else{
					HibernateUtil.getCurrentSession().beginTransaction();
					memberDao.update(po);
					HibernateUtil.getCurrentSession().getTransaction().commit();
					return MemberResultMessage.SUCCESS;
				}
			}else{
				return MemberResultMessage.FAIL;
			}
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	
	public MemberResultMessage updateClient(ManageClientVO vo) throws RemoteException {
		return updateVO(vo);
	}
	
	public MemberResultMessage updateHotelWorker(ManageHotelWorkerVO vo) throws RemoteException {
		return updateVO(vo);
	}
	
	public MemberResultMessage updateWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		return updateVO(vo);
	}
	
	public MemberResultMessage delete(long id) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			MemberPO po=null;
			po=memberDao.getInfo(id);
			HibernateUtil.getCurrentSession().getTransaction().commit();
			if(po==null){
				return MemberResultMessage.FAIL_WRONGID;
			}else{
				memberDao.delete(id);
				return MemberResultMessage.SUCCESS;
			}
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
}
