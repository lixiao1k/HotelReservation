package logic.service.impl.member;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.hibernate.Hibernate;

import data.dao.MemberDao;
import data.dao.impl.DaoManager;
import info.Cache;
import info.ListWrapper;
import info.UserType;
import po.ClientMemberPO;
import po.HotelPO;
import po.HotelWorkerPO;
import po.MemberPO;
import po.UserPO;
import po.VIPPO;
import resultmessage.MemberResultMessage;
import util.Base64Util;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.BasicMemberVO;
import vo.ManageClientVO;
import vo.ManageHotelVO;
import vo.ManageHotelWorkerVO;
import vo.ManageWEBSalerVO;
import vo.MemberVO;
import vo.VIPVO;

public class MemberDO {
	private MemberDao memberDao;
	//memberPO����
	private Cache<MemberPO> members;
	//��ʼVIP��
	private int VipCredit = 10000;
	
	//��ʼ��
	public MemberDO() {
		memberDao=DaoManager.getInstance().getMemberDao();
		members = new Cache<>(20);
	}
	public MemberDO(int size){
		memberDao=DaoManager.getInstance().getMemberDao();
		members = new Cache<>(size);
	}
	
	public void setVIPscale(int scale) throws RemoteException {
		VipCredit=scale;
	}
	
	public MemberVO getInfo(long userId){
		MemberPO cachePO = null;
		cachePO = members.get(userId);
		MemberVO vo = null;
		if(cachePO!=null){
			vo = DozerMappingUtil.getInstance().map(cachePO, MemberVO.class);
			if(((ClientMemberPO)cachePO).getVipInfo()!=null)
				vo.setVIPInfo(((ClientMemberPO)cachePO).getVipInfo().toString());
			return vo;
		}else{
			try{
				HibernateUtil.getCurrentSession().beginTransaction();
				MemberPO po = memberDao.getInfo(userId);
				if(po==null)
					return null;
				vo = DozerMappingUtil.getInstance().map(po, MemberVO.class);
				if(((ClientMemberPO)po).getVipInfo()!=null)
					vo.setVIPInfo(((ClientMemberPO)po).getVipInfo().toString());
				HibernateUtil.getCurrentSession().getTransaction().commit();
				members.put(userId, po);
				return vo;
			}catch(RuntimeException e){
				e.printStackTrace();
				members.remove(userId);
				try{
					HibernateUtil.getCurrentSession().getTransaction().rollback();
					return null;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
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
							VIPPO vippo = new VIPPO( vo.getType(), vo.getBirthday(), vo.getCompanyName());	
							vippo.setType(vo.getType());
							cmpo.setVipInfo(vippo);
							members.remove(vo.getUserId());
							members.put(vo.getUserId(), cmpo);
							HibernateUtil.getCurrentSession().save(vippo);
							memberDao.update(cmpo);
							HibernateUtil.getCurrentSession()
											.getTransaction()
											.commit();
							return MemberResultMessage.SUCCESS;
						}catch(RuntimeException e){
							e.printStackTrace();
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
				HibernateUtil.getCurrentSession().beginTransaction();
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
								VIPPO vippo = new VIPPO(vo.getType(), vo.getBirthday(), vo.getCompanyName());	
								vippo.setType(vo.getType());
								((ClientMemberPO) po).setVipInfo(vippo);
								HibernateUtil.getCurrentSession().save(vippo);
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
				e.printStackTrace();
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
				MemberPO po = memberDao.getInfo(Base64Util.encode(username));
				System.out.println(Base64Util.encode(username));
				if(po!=null){
					members.put(username, po);
					MemberVO vo = DozerMappingUtil.getInstance().map(po, MemberVO.class);
					HibernateUtil.getCurrentSession()
						.getTransaction()
						.commit();
					return vo;
				}else{
					return null;
				}
			}catch(RuntimeException e){
				e.printStackTrace();
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
		try{
			ManageClientVO vo=DozerMappingUtil.getInstance().map(getInfo(username), ManageClientVO.class);
			System.out.println(vo);
		return vo;
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ListWrapper<ManageHotelVO> getAllHotelWorker(String hotelname) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			ListWrapper<MemberPO> polist=memberDao.manageInfo(hotelname);
			List<ManageHotelVO> volist=new ArrayList<>();
			Iterator<MemberPO> it=polist.iterator();
			while(it.hasNext()){
				MemberPO po=it.next();
				HotelWorkerPO hotelworker=(HotelWorkerPO)po;
				HotelPO hotel=hotelworker.getHotel();
				Hibernate.initialize(hotel.getBusinessCircle());
				Hibernate.initialize(hotel.getBusinessCity());
				ManageHotelVO managehotelvo=new ManageHotelVO(hotel.getName(), hotel.getAddress(), hotel.getBusinessCity(), hotel.getBusinessCircle(), hotel.getHid(), po.getMid(), po.getUser().getUsername(), po.getUser().getPassword(), po.getName());
				volist.add(managehotelvo);
			}

			HibernateUtil.getCurrentSession().getTransaction().commit();
			return new ListWrapper<ManageHotelVO>(volist);
		}catch(RuntimeException e){
			e.printStackTrace();
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	
	
	public ManageWEBSalerVO getWEBSaler(String username) throws RemoteException {
		try{
			ManageWEBSalerVO vo=DozerMappingUtil.getInstance().map(getInfo(username), ManageWEBSalerVO.class);
			HibernateUtil.getCurrentSession().beginTransaction();
			UserPO po = DaoManager.getInstance().getUserDao().getInfo(username);
			if(po!=null){
			vo.setPassword(Base64Util.decode(po.getPassword()));
			vo.setUsername(Base64Util.decode(po.getUsername()));
			}
			HibernateUtil.getCurrentSession().getTransaction().commit();
		return vo;
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public MemberResultMessage addWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		//������볤��
		if(vo.getPassword().length()>=15||vo.getPassword().length()<=5){
			return MemberResultMessage.FAIL_PASSWORDLENGTH;
		}
		//�������
		MemberPO mpo=null;
		mpo=members.get(vo.getUserid());
		if(mpo!=null){
			return MemberResultMessage.FAIL_SAMEID;
		}
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			mpo=memberDao.getInfo(vo.getUserid());
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
			UserPO user=new UserPO(Base64Util.encode(vo.getUsername()), Base64Util.encode(vo.getPassword()), UserType.WEB_SALER);
			MemberPO po=new MemberPO(vo.getName(), UserType.WEB_SALER, user);
			user.setMember(po);
			memberDao.add(po);
			members.put(vo.getUsername(), po);
			HibernateUtil.getCurrentSession().getTransaction().commit();
			return MemberResultMessage.SUCCESS;
	    }catch(RuntimeException e){
	    	members.remove(vo.getUsername());
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	
	public String PhonetoString(String string){
		return string;
	}
	
	public MemberResultMessage changeInfo(BasicMemberVO vo) throws RemoteException {
		try{
			MemberPO po=null;
			if(vo!=null){
				HibernateUtil.getCurrentSession().beginTransaction();
				po=memberDao.getInfo(vo.getUserid());
				if(po==null){
					return MemberResultMessage.FAIL_WRONGID;
				}else{
					ClientMemberPO cmpo=(ClientMemberPO)po;
					cmpo.setContactWay(vo.getPhonenumber());
					po.setName(vo.getName());
					memberDao.update(po);
					members.put(vo.getUserid(), po);
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
		try{
			MemberPO po=null;
			if(vo!=null){
				HibernateUtil.getCurrentSession().beginTransaction();
				po=memberDao.getInfo(vo.getUserid());
				if(po==null){
					return MemberResultMessage.FAIL_WRONGID;
				}else{
					UserPO upo=po.getUser();
					ClientMemberPO cmpo=(ClientMemberPO)po;
					VIPPO vpo=cmpo.getVipInfo();
					vpo.setBirthday(vo.getBirthday());
					vpo.setCompanyName(vo.getCompanyname());
					cmpo.setVipInfo(vpo);
					cmpo.setContactWay(vo.getPhonenumber());
					po.setUser(upo);
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
	
	
	
	public MemberResultMessage updateHotelWorker(ManageHotelWorkerVO vo) throws RemoteException {
		if(vo==null){
			return MemberResultMessage.FAIL;
		}
		//������볤��
		if(vo.getPassword().length()<=5||vo.getPassword().length()>=15){
			return MemberResultMessage.FAIL_PASSWORDLENGTH;
		}
		try{
			MemberPO po=null;	
			HibernateUtil.getCurrentSession().beginTransaction();
			po=memberDao.getInfo(vo.getUsername());
			if(po==null){
				return MemberResultMessage.FAIL_WRONGID;
			}else{
				UserPO upo=po.getUser();
				upo.setPassword(Base64Util.encode(vo.getPassword()));
				po.setUser(upo);
				po.setName(vo.getName());				
				memberDao.update(po);
				HibernateUtil.getCurrentSession().getTransaction().commit();
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
	
	
	public MemberResultMessage updateWEBSaler(ManageWEBSalerVO vo) throws RemoteException {
		if(vo==null){
			return MemberResultMessage.FAIL;
		}
		//������볤��
		if(vo.getPassword().length()<=5||vo.getPassword().length()>=15){
			return MemberResultMessage.FAIL_PASSWORDLENGTH;
		}
		try{
			MemberPO po=null;			
			HibernateUtil.getCurrentSession().beginTransaction();
			po=memberDao.getInfo(vo.getUsername());
			if(po==null){
				return MemberResultMessage.FAIL_WRONGID;
			}else{
				UserPO upo=po.getUser();
				upo.setPassword(Base64Util.encode(vo.getPassword()));
				po.setUser(upo);
				po.setName(vo.getName());				
				memberDao.update(po);
				HibernateUtil.getCurrentSession().getTransaction().commit();
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
	
	
	public MemberResultMessage delete(long id) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			MemberPO po=null;
			po=memberDao.getInfo(id);
			if(po==null){
				HibernateUtil.getCurrentSession().getTransaction().commit();
				return MemberResultMessage.FAIL_WRONGID;
			}else{
				memberDao.delete(id);
				HibernateUtil.getCurrentSession().getTransaction().commit();
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
