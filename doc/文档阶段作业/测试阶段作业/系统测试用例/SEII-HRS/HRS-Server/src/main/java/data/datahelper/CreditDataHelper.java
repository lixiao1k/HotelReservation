package data.datahelper;

import java.util.List;

import info.ListWrapper;
import po.CreditPO;

public interface CreditDataHelper {
	public void insert(CreditPO po);
	public List<CreditPO> getinfo(long userId);
}
