package data.datahelper.impl;

import java.util.List;

import org.hibernate.Query;

import data.datahelper.CreditDataHelper;
import info.ListWrapper;
import po.CreditPO;
import po.MemberPO;
import util.HibernateUtil;

public class CreditDataHelperMysqlImpl implements CreditDataHelper{
	private static final String getInfo = "from Credit as c where c.member=:MEMBER";
	@Override
	public List<CreditPO> getinfo(long userId) {
		MemberPO po = (MemberPO) HibernateUtil.getCurrentSession().get(MemberPO.class,userId);
		Query query = HibernateUtil.getCurrentSession().createQuery(getInfo);
		query.setEntity("MEMBER", po);
		return query.list();
	}

	@Override
	public void insert(CreditPO po) {
		HibernateUtil.getCurrentSession().save(po);
	}

}
