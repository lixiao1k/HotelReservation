package data.datahelper.impl;

import data.datahelper.HibernateUtil;
import data.datahelper.UserDataHelper;
import po.UserPO;

public class UserDataHelperMysqlImpl implements UserDataHelper{

	@Override
	public void insert(UserPO po) {
		HibernateUtil.getCurrentSession()
		.save(po);
	}

	@Override
	public UserPO getInfo(long userId) {
		UserPO po = (UserPO)HibernateUtil.getCurrentSession()
				.get(UserPO.class, userId);

		return po;
	}

}
