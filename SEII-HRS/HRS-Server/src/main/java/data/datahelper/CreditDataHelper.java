package data.datahelper;

import po.CreditPO;

public interface CreditDataHelper {
	public void add(long userID,int value);
	public void decrease(long userID,int value);
	public void insert(CreditPO po);
}
