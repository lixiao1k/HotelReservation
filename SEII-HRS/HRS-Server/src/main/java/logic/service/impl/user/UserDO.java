package logic.service.impl.user;

import java.rmi.RemoteException;
import java.util.Iterator;

import javax.management.RuntimeErrorException;

import org.hibernate.Hibernate;

import data.dao.UserDao;
import data.dao.impl.DaoManager;
import info.Cache;
import info.UserStatus;
import info.UserType;
import po.ClientMemberPO;
import po.HotelWorkerPO;
import po.MemberPO;
import po.UserPO;
import resultmessage.LoginResultMessage;
import resultmessage.RegisterResultMessage;
import resultmessage.UserResultMessage;
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
	
	
	/**
	 * 用户登录时的验证方法
	 * @param username
	 * 登录的用户名
	 * @param password
	 * 登录的密码
	 * @return LoginResultVO
	 * 登录成功，VO内resultmessage为成功，并返回相应的用户类型和id，酒店工作人员还会返回酒店id
	 * 登录失败，VO内resultmessage为失败，其他数值为无效数值
	 * @throws RemoteException
	 */
	public LoginResultVO login(String username,String password) throws RemoteException{
		try{
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
					
					if(!flag){
						HibernateUtil.getCurrentSession().beginTransaction();
						flag = true;
						System.out.println(1);
					}
					MemberPO po = DaoManager.getInstance().getMemberDao().getInfo(upo.getUid());
					if(po instanceof HotelWorkerPO)
						lrvo.setHotelid(((HotelWorkerPO)po).getHotel().getHid());
					userDao.update((UserPO) HibernateUtil.getCurrentSession().merge(upo));
					lrvo.setLoginResultMessage(LoginResultMessage.SUCCESS);
					lrvo.setUserType(upo.getType());
					lrvo.setUserID(upo.getUid());
					Hibernate.initialize(upo.getMember());
					users.put(upo.getUid(), upo);
					if(flag){
						HibernateUtil.getCurrentSession().getTransaction().commit();
						System.out.println("finish");
					}
					return lrvo;
				}else{
					users.put(upo.getUid(), upo);
					lrvo.setLoginResultMessage(LoginResultMessage.FAIL_WRONG);
					if(flag)
						HibernateUtil.getCurrentSession().getTransaction().commit();
					return lrvo;
				}
			}
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//登出
	/**用户登出的方法，在窗口关闭和用户点击登出图标，将会调用此方法
	 * @param userid
	 * 用户id
	 * @throws RemoteException
	 */
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
			userDao.update((UserPO) HibernateUtil.getCurrentSession().merge(po));
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
	
	//注册
	/**用户注册的方法
	 * @param username
	 * 注册的用户名
	 * @param password
	 * 注册的密码
	 * @return RegisterResultMessage
	 * 注册是否成功的消息
	 * @throws RemoteException
	 */
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
				upo.setStatus(UserStatus.OFFLINE);
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
	/**
	 * 用户更改密码所调用的方法
	 * @param userId
	 * 要更改密码的用户id
	 * @param password
	 * 要更改用户传入的原密码
	 * @param newPassword
	 * 要更改的新密码
	 * @return UserResultMessage
	 * 更改结果
	 */
	public UserResultMessage changePassword(long userId,String password,String newPassword){
			try{
				HibernateUtil.getCurrentSession().beginTransaction();
				UserPO po = userDao.getInfo(userId);
				if(po==null)
					return UserResultMessage.FAIL_WRONGID;
				String pass = Base64Util.encode(password);
				if(pass.equals(po.getPassword())){
					po.setPassword(Base64Util.encode(newPassword));
					userDao.update(po);
					Hibernate.initialize(po);
					HibernateUtil.getCurrentSession().getTransaction().commit();
					users.put(userId, po);
					return UserResultMessage.SUCCESS;
				}else{
					Hibernate.initialize(po);
					HibernateUtil.getCurrentSession().getTransaction().commit();
					users.put(userId, po);
					return UserResultMessage.FAIL_WRONGINFO;
				}
			}catch(RuntimeException e){
				e.printStackTrace();
				users.remove(userId);
				try{
					HibernateUtil.getCurrentSession().getTransaction().rollback();
					return UserResultMessage.FAIL;
				}catch(RuntimeException ex){
					ex.printStackTrace();
				}
				throw e;
			}
	}
}
