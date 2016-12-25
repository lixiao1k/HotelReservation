package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import info.OrderStrategy;
import info.StrategyType;
import resultmessage.StrategyResultMessage;
import vo.HotelStrategyVO;
import vo.StrategyResultVO;
import vo.StrategyVO;

public interface StrategyLogicService extends Remote{
	/**
	 * @param strategyId
	 * 待删除的策略id
	 * @return StrategyResultMessage
	 * 删除结果
	 * @throws RemoteException
	 */
	public StrategyResultMessage delete(long strategyId) throws RemoteException;
	/**
	 * @param StrategyVO vo
	 * 传入新增策略所必须的数据
	 * @return StrategyResultVO
	 * 创建结果 成功返回SUCCESS和相应额外的信息，失败返回FAIL
	 * @throws RemoteException
	 */
	public StrategyResultVO create(StrategyVO vo) throws RemoteException;
	/**
	 * 酒店工作人员获取自身酒店订单所调用的方法
	 * @param hotelId
	 * 获取策略列表的酒店ID
	 * @return ListWrapper<HotelStrategyVO>
	 * 酒店策略列表
	 * @throws RemoteException
	 */
	public ListWrapper<HotelStrategyVO> getStrategyList(long hotelId) throws RemoteException;
	/**
	 * 给订单获取能采用的策略列表
	 * 此方法在下订单时选择相应酒店，房间及数量时调用
	 * @param OrderStrategy 
	 * 基本的订单数据，将会根据这些数据获取能采用的策略
	 * @return ListWrapper<HotelStrategyVO>
	 * 所有能采用的策略列表
	 * 包括酒店策略和网站营销策略
	 * @throws RemoteException
	 */
	public ListWrapper<HotelStrategyVO> getStrategyForOrder(OrderStrategy vo) throws RemoteException;
	/**
	 * 创建策略时需要知道所有可创建的策略类型
	 * 此方法返回所有策略类型
	 * @return ListWrapper<StrategyType>
	 * 所有的策略类型
	 * @throws RemoteException
	 */
	public ListWrapper<StrategyType> getTypes() throws RemoteException;
	/**
	 * 获取网站营销人员的策略列表
	 * @return ListWrapper<HotelStrategyVO>
	 * @throws RemoteException
	 */
	public ListWrapper<HotelStrategyVO> getWEBStrategyList() throws RemoteException;
}
