package data.dao.Impl;

import java.util.List;

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
	public ListWrapper<CreditPO> getinfo(long userId) {
		List<CreditPO> list = creditDataHelper.getinfo(userId);
		return new ListWrapper<CreditPO>(list);
	}

	@Override
	public void insert(CreditPO po) {
		creditDataHelper.insert(po);
	}


}
