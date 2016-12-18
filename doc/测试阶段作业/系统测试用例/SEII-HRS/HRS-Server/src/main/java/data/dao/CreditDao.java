package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import po.CreditPO;

public interface CreditDao {
	public void insert(CreditPO po);
	public ListWrapper<CreditPO> getinfo(long userId) throws RemoteException;
	
}
