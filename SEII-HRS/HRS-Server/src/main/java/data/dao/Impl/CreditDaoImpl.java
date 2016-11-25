package data.dao.Impl;

import data.dao.CreditDao;
import data.datahelper.CreditDataHelper;
import data.datahelper.impl.DataFactory;
import info.ListWrapper;
import po.CreditPO;

public class CreditDaoImpl implements CreditDao{
	private CreditDataHelper creditDataHelper;
	public CreditDaoImpl() {
		creditDataHelper=DataFactory.getInstance().getCreditDataHelper();
	}

	@Override
	public void insert(CreditPO po) {
		creditDataHelper.insert(po);
	}
	@Override
	public ListWrapper<CreditPO> getinfo(long userId) {
		// TODO �Զ����ɵķ������
		return null;
	}

}
