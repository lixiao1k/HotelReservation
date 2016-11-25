package data.datahelper;

import info.ListWrapper;
import po.CreditPO;

public interface CreditDataHelper {
	public ListWrapper<CreditPO> getinfo(long userId);
	public void insert(CreditPO po);
}
