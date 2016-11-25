package logic.service.impl.user;

import java.rmi.RemoteException;
import java.util.Iterator;

import data.dao.UserDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.UserStatus;
import po.UserPO;
import resultmessage.LoginResultMessage;
import resultmessage.RegisterResultMessage;
import util.HibernateUtil;

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
	public LoginResultMessage login(String username,String password) throws RemoteException{
		Iterator cacheItem=users.getKeys();
		UserPO upo=null;
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
			upo=userDao.getInfo(username);
		}
		if(upo==null){
			return LoginResultMessage.FAIL_NOINFO;
		}else if(upo.getStatus()==UserStatus.ONLINE){
			return LoginResultMessage.FAIL_LOGGED;
		}else{
			if(upo.getPassword().equals(password)){
				upo.setStatus(UserStatus.ONLINE);
				users.put(upo.getUid(), upo);
				userDao.update(upo);
				return LoginResultMessage.SUCCESS;
			}else{
				users.put(upo.getUid(), upo);
				return LoginResultMessage.FAIL_WRONG;
			}
		}
	}
	
	//µÇ³ö
	public void logout(long userid) throws RemoteException{
		UserPO po=userDao.getInfo(userid);
		po.setStatus(UserStatus.OFFLINE);
		userDao.update(po);
	}
	
	//×¢²á
	public RegisterResultMessage register(String username,String password) throws RemoteException{
		if(password.length()<=14){
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
			userDao.insert(upo);
			return RegisterResultMessage.SUCCESS;
		}
	}
}
