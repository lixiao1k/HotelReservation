package data.datahelper.impl;


import data.datahelper.MemberDataHelper;
import po.MemberPO;
import util.HibernateUtil;

public class MemberDataHelperMysqlImpl implements MemberDataHelper{

	@Override
	public void insert(MemberPO po) {
		HibernateUtil.getCurrentSession().save(po);
	}
	
}
