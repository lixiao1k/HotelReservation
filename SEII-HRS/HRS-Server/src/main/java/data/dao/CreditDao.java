package data.dao;

import po.CreditPO;

public interface CreditDao {
	public void add(long userID,int value);
	public void decrease(long userID,int value);
	public void insert(CreditPO po);
}
