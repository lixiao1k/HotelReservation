package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import po.CreditPO;

public interface CreditDao {
	
	/**
	 * 插入信用记录
	 * @param po
	 */
	public void insert(CreditPO po);
	
	/**
	 * 获取信用记录
	 * @param userId
	 * 用户id
	 * @return
	 * 信用记录列表
	 * @throws RemoteException
	 */
	public ListWrapper<CreditPO> getinfo(long userId) throws RemoteException;
	
}
