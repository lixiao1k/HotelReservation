package data.dao;

import java.rmi.RemoteException;

import info.ListWrapper;
import info.StrategyType;
import po.StrategyPO;

public interface StrategyDao {
	/**
	 * 插入策略
	 * @param po
	 */
	public void insert(StrategyPO po);
	/**
	 * 更新策略
	 * @param po
	 */
	public void update(StrategyPO po);
	/**
	 * 根据id查询策略
	 * @param strategyId
	 * @return
	 */
	public StrategyPO getInfo(long strategyId);
	/**
	 * 获取所有酒店列表
	 * @param hotelId
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<StrategyPO> getHotelStrategyList(long hotelId)throws RemoteException ;
	/**
	 * 获取网站策略列表
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<StrategyPO> getWEBStrategyList()throws RemoteException ;
	/**
	 * 获取策略类型
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<StrategyType> getTypes() throws RemoteException;
}
