package data.dao.impl;

import data.dao.UserDao;
import data.datahelper.UserDataHelper;
import data.datahelper.impl.DataFactory;
import po.UserPO;

public class UserDaoImpl implements UserDao{
	private UserDataHelper userDataHelper;
	public UserDaoImpl() {
		userDataHelper = DataFactory
							.getInstance()
							.getUserDataHelper();
	}
	@Override
	public void insert(UserPO po) {
		userDataHelper.insert(po);
	}

	@Override
	public UserPO getInfo(long userId) {
		return userDataHelper.getInfo(userId);
	}
	@Override
	public UserPO getInfo(String username) {
		return userDataHelper.getInfo(username);
	}
	@Override
	public void update(UserPO po) {
		userDataHelper.update(po);
	}

}
