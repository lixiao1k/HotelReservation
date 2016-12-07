package logic.service.impl.user;

import java.rmi.RemoteException;
import java.util.Iterator;

import data.dao.UserDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.UserStatus;
import info.UserType;
import po.UserPO;
import resultmessage.LoginResultMessage;
import resultmessage.RegisterResultMessage;
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
		boolean flag = false;
		while(cacheItem.hasNext()){
			long userid=(long)cacheItem.next();
			UserPO cachePO=users.get(userid);
			if(upo==null&&cachePO.getUsername().equals(username)){
				upo=cachePO;
			}
			if(upo!=null){
				break;
			}
		}
		if(upo==null){
			HibernateUtil.getCurrentSession().beginTransaction();
			flag = true;
			upo=userDao.getInfo(username);
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
			if(upo.getPassword().equals(password)){
				upo.setStatus(UserStatus.ONLINE);
				users.put(upo.getUid(), upo);
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
		HibernateUtil.getCurrentSession().beginTransaction();
		UserPO po=userDao.getInfo(userid);
		po.setStatus(UserStatus.OFFLINE);
		userDao.update(po);
	}
	
	//×¢²á
	public RegisterResultMessage register(String username,String password) throws RemoteException{
		if(password.length()>=15||password.length()<=5){
			return RegisterResultMessage.FAIL_PASSWORDLENGTH;
		}
		Iterator cacheItem=users.getKeys();
		while(cacheItem.hasNext()){
			long userid=(long)cacheItem.next();
			UserPO cachePO=users.get(userid);
			if(cachePO.getUsername().equals(username)){
				return RegisterResultMessage.FAIL_USEREXIST;
			}
		}
		HibernateUtil.getCurrentSession().beginTransaction();
		UserPO upo=userDao.getInfo(username);
		if(upo!=null){
			return RegisterResultMessage.FAIL_USEREXIST;
		}else{
			upo=new UserPO(username, password);
			upo.setType(UserType.CLIENT);
			userDao.insert(upo);
			return RegisterResultMessage.SUCCESS;
		}
	}
}
