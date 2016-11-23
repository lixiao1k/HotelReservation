package data.datahelper.impl;

import data.datahelper.CreditDataHelper;
import po.CreditPO;
import po.UserPO;
import util.HibernateUtil;

public class CreditDataHelperMysqlImpl implements CreditDataHelper{

	@Override
	public void add(long userID, int value) {
		UserPO user=(UserPO)HibernateUtil.getCurrentSession().get(UserPO.class, userID);
		//user.getCredit().addCredit(value);
		HibernateUtil.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public void decrease(long userID, int value) {
		UserPO user=(UserPO)HibernateUtil.getCurrentSession().get(UserPO.class, userID);
		//user.getCredit().decreaseCredit(value);
		HibernateUtil.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public void insert(CreditPO po) {
		HibernateUtil.getCurrentSession().save(po);
	}

}
