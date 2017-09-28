package data.datahelper.impl;

import java.util.List;

import org.hibernate.Query;

import data.datahelper.CreditDataHelper;
import info.ListWrapper;
import po.CreditPO;
import po.MemberPO;
import util.HibernateUtil;

public class CreditDataHelperMysqlImpl implements CreditDataHelper{
	private static final String getInfo = "from CreditPO as c where c.member=:MEMBER";
	@Override
	public List<CreditPO> getinfo(long userId) {
		MemberPO po = (MemberPO) HibernateUtil.getCurrentSession().get(MemberPO.class,userId);
		Query query = HibernateUtil.getCurrentSession().createQuery(getInfo);
		if(po!=null)
			query.setEntity("MEMBER", po);
		else
			return null;
		return query.list();
	}

	@Override
	public void insert(CreditPO po) {
		HibernateUtil.getCurrentSession().save(po);
	}

}
