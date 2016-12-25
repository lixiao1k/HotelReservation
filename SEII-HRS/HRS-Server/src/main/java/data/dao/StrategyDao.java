package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import info.StrategyType;
import po.StrategyPO;

public interface StrategyDao {
	/**
	 * �������
	 * @param po
	 */
	public void insert(StrategyPO po);
	/**
	 * ���²���
	 * @param po
	 */
	public void update(StrategyPO po);
	/**
	 * ����id��ѯ����
	 * @param strategyId
	 * @return
	 */
	public StrategyPO getInfo(long strategyId);
	/**
	 * ��ȡ���оƵ��б�
	 * @param hotelId
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<StrategyPO> getHotelStrategyList(long hotelId)throws RemoteException ;
	/**
	 * ��ȡ��վ�����б�
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<StrategyPO> getWEBStrategyList()throws RemoteException ;
	/**
	 * ��ȡ��������
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<StrategyType> getTypes() throws RemoteException;
}
