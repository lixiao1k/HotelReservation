package data.dao.Impl;

import data.dao.CreditDao;
import data.datahelper.CreditDataHelper;
import data.datahelper.impl.DataFactory;
import po.CreditPO;

public class CreditDaoImpl implements CreditDao{
	private CreditDataHelper creditDataHelper;
	public CreditDaoImpl() {
		creditDataHelper=DataFactory.getInstance().getCreditDataHelper();
	}
	@Override
	public void add(long userID, int value) {
		creditDataHelper.add(userID, value);
	}

	@Override
	public void decrease(long userID, int value) {
		creditDataHelper.decrease(userID, value);
	}

	@Override
	public void insert(CreditPO po) {
		creditDataHelper.insert(po);
	}

}
