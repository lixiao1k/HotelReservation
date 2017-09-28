package dataservice;

import po.CreditPO;

public interface CreditDataService extends DatabaseService {
	public void add(long userid,int value);
	public void decrease(long userid,int value);
	public void insert(CreditPO po);
}
