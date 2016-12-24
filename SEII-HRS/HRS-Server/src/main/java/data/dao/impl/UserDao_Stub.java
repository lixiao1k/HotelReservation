package data.dao.impl;

import data.dao.UserDao;
import po.MemberPO;
import po.StrategyPO;
import po.UserPO;

public class UserDao_Stub implements UserDao{

	@Override
	public void insert(UserPO po) {
		System.out.println("UserDao.insert  :  insert success!");
	}

	@Override
	public UserPO getInfo(long userId) {
		if(userId==1){
			System.out.println("UserDao.getInfo  :  userId=1|return NULL");
			return null;
		}
		System.out.println("UserDao.getInfo  :  userId=other number|return normal result");
		return new UserPO();
	}

	@Override
	public UserPO getInfo(String username) {
		if(username.equals("111")){
			System.out.println("UserDao.getInfo  :  username=111|return NULL");
			return null;
		}
		System.out.println("UserDao.getInfo  :  username=other string|return normal result");
		return new UserPO();
	}

	@Override
	public void update(UserPO po) {
		System.out.println("UserDao.update  :  update success!");
	}

}
