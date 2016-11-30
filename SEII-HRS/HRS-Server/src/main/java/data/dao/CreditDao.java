package data.dao;

import info.ListWrapper;
import po.CreditPO;

public interface CreditDao {
	public void insert(CreditPO po);
	public ListWrapper<CreditPO> getinfo(long userId);
}
