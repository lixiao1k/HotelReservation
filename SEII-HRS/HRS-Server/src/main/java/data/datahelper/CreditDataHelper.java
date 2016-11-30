package data.datahelper;

import info.ListWrapper;
import po.CreditPO;

public interface CreditDataHelper {
	public void insert(CreditPO po);
	public ListWrapper<CreditPO> getinfo(long userId);
}
