package logic.service.impl.user;

import java.rmi.RemoteException;
import java.util.Iterator;

import javax.management.RuntimeErrorException;

import data.dao.UserDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.UserStatus;
import info.UserType;
import po.ClientMemberPO;
import po.MemberPO;
import po.UserPO;
import resultmessage.LoginResultMessage;
import resultmessage.RegisterResultMessage;
import util.Base64Util;
import util.HibernateUtil;
import vo.LoginResultVO;

public class UserDO {
	private UserDao userDao;
	private Cache<UserPO> users;
	
	public UserDO() {
		userDao=DaoManager.getInstance().getUserDao();
		users=new Cache<>(10);
	}
	
	public UserDO(int cachesize) {
		userDao=DaoManager.getInstance().getUserDao();
		users=new Cache<>(cachesize);
	}
	
	//µÇÂ½
	public LoginResultVO login(String username,String password) throws RemoteException{
		Iterator cacheItem=users.getKeys();
		UserPO upo=null;
		LoginResultVO lrvo=new LoginResultVO(null, null, 0);
		String name = Base64Util.encode(username);
		String pass = Base64Util.encode(password);
		boolean flag = false;
		while(cacheItem.hasNext()){
			long userid=(long)cacheItem.next();
			UserPO cachePO=users.get(userid);
			if(upo==null&&cachePO.getUsername().equals(name)){
				upo=cachePO;
			}
			if(upo!=null){
				break;
			}
		}
		if(upo==null){
			HibernateUtil.getCurrentSession().beginTransaction();
			flag = true;
			upo=userDao.getInfo(name);
		}
		if(upo==null){
			lrvo.setLoginResultMessage(LoginResultMessage.FAIL_NOINFO);
			if(flag)
				HibernateUtil.getCurrentSession().getTransaction().commit();
			return lrvo;
		}else if(upo.getStatus()==UserStatus.ONLINE){
			lrvo.setLoginResultMessage(LoginResultMessage.FAIL_LOGGED);
			if(flag)
				HibernateUtil.getCurrentSession().getTransaction().commit();
			return lrvo;
		}else{
			if(upo.getPassword().equals(pass)){
				upo.setStatus(UserStatus.ONLINE);
				users.put(upo.getUid(), upo);
				if(!flag)
					HibernateUtil.getCurrentSession().beginTransaction();
				userDao.update(upo);
				lrvo.setLoginResultMessage(LoginResultMessage.SUCCESS);
				lrvo.setUserType(upo.getType());
				lrvo.setUserID(upo.getUid());
				if(flag)
					HibernateUtil.getCurrentSession().getTransaction().commit();
				return lrvo;
			}else{
				users.put(upo.getUid(), upo);
				lrvo.setLoginResultMessage(LoginResultMessage.FAIL_WRONG);
				if(flag)
					HibernateUtil.getCurrentSession().getTransaction().commit();
				return lrvo;
			}
		}
	}
	
	//µÇ³ö
	public void logout(long userid) throws RemoteException{
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			UserPO po = null;
			po = users.get(userid);
			if(po==null)
				po=userDao.getInfo((long)userid);
			if(po==null)
				return;
			po.setStatus(UserStatus.OFFLINE);
			userDao.update(po);
			HibernateUtil.getCurrentSession().getTransaction().commit();
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
	
	//×¢²á
	public RegisterResultMessage register(String username,String password) throws RemoteException{
		if(password.length()>=15||password.length()<=5){
			return RegisterResultMessage.FAIL_PASSWORDLENGTH;
		}
		String name = Base64Util.encode(username);
		String pass = Base64Util.encode(password);
		Iterator cacheItem=users.getKeys();
		while(cacheItem.hasNext()){
			long userid=(long)cacheItem.next();
			UserPO cachePO=users.get(userid);
			if(cachePO.getUsername().equals(name)){
				return RegisterResultMessage.FAIL_USEREXIST;
			}
		}
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			UserPO upo=userDao.getInfo(name);
			if(upo!=null){
				return RegisterResultMessage.FAIL_USEREXIST;
			}else{
				upo=new UserPO(name, pass);
				upo.setType(UserType.CLIENT);
				MemberPO mpo = new ClientMemberPO();
				mpo.setUser(upo);
				mpo.setType(UserType.CLIENT);
				((ClientMemberPO)mpo).setCredit(1000);
				((ClientMemberPO)mpo).setVip(false);
				((ClientMemberPO)mpo).setVipInfo(null);
				upo.setMember(mpo);
				userDao.insert(upo);
				DaoManager.getInstance().getMemberDao().add(mpo);
				HibernateUtil.getCurrentSession().getTransaction().commit();
				return RegisterResultMessage.SUCCESS;
			}
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
				return RegisterResultMessage.FAIL;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
}
