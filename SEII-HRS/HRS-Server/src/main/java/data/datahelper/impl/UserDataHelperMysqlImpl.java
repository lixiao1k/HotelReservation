package data.datahelper.impl;

import javax.persistence.NonUniqueResultException;

import org.hibernate.Query;
import org.hibernate.engine.HibernateIterator;

import data.datahelper.UserDataHelper;
import po.UserPO;
import util.HibernateUtil;

public class UserDataHelperMysqlImpl implements UserDataHelper{
	private static final String getInfoByUsername = "from user as u where u.username=:USERNAME";
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

	@Override
	public UserPO getInfo(String username) {
		Query query = HibernateUtil.getCurrentSession().createQuery(getInfoByUsername);
		query.setString("USERNAME", username);
		UserPO result = null;
		try{
			result = (UserPO)query.uniqueResult();
		}catch(NonUniqueResultException e){
			throw e;
		}
		return result;
	}

	@Override
	public void update(UserPO po) {
		HibernateUtil.getCurrentSession().update(po);
	}

}
