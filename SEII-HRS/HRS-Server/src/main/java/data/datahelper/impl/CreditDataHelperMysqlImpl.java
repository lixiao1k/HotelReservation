package data.datahelper.impl;

import data.datahelper.CreditDataHelper;
import info.ListWrapper;
import po.CreditPO;
import util.HibernateUtil;

public class CreditDataHelperMysqlImpl implements CreditDataHelper{

	@Override
	public void insert(CreditPO po) {
		HibernateUtil.getCurrentSession().save(po);
	}

	@Override
	public ListWrapper<CreditPO> getinfo(long userId) {
		// TODO �Զ����ɵķ������
		return null;
	}

}
