package data.dao;

import info.ListWrapper;
import po.CreditPO;

public interface CreditDao {
	public ListWrapper<CreditPO> getinfo(long userId);
	public void insert(CreditPO po);
}
