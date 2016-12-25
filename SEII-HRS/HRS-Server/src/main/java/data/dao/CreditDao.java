package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import po.CreditPO;

public interface CreditDao {
	
	/**
	 * �������ü�¼
	 * @param po
	 */
	public void insert(CreditPO po);
	
	/**
	 * ��ȡ���ü�¼
	 * @param userId
	 * �û�id
	 * @return
	 * ���ü�¼�б�
	 * @throws RemoteException
	 */
	public ListWrapper<CreditPO> getinfo(long userId) throws RemoteException;
	
}
