package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import info.OrderStatus;
import resultmessage.OrderResultMessage;
import vo.NewOrderVO;
import vo.OrderVO;
import vo.StrategyVO;

public interface OrderLogicService extends Remote{
	/**
	 * 客户查看自身订单时所调用的方法
	 * @param userId
	 * 用户id
	 * @return ListWrapper<OrderVO> 
	 * 用户的所有订单
	 * @throws RemoteException
	 */
	public ListWrapper<OrderVO> getUserOrderInfo(long userId) throws RemoteException;
	/**
	 * 酒店工作人员查看自身订单时所调用的方法
	 * @param hotelId
	 * 酒店id
	 * @return ListWrapper<OrderVO> 
	 * 酒店的所有订单
	 * @throws RemoteException
	 */
	public ListWrapper<OrderVO> getHotelOrderInfo(long hotelId) throws RemoteException;
	/**
	 * 网站营销人员查看自身订单时所调用的方法
	 * @return ListWrapper<OrderVO> 
	 * 当天的所有异常的订单
	 * @throws RemoteException
	 */
	public ListWrapper<OrderVO> getWEBOrderInfo() throws RemoteException;
	/**
	 * 客户在新建订单时所调用的方法
	 * @param vo
	 * 新订单信息
	 * @return OrderResultMessage
	 * 创建成功的结果
	 * @throws RemoteException
	 */
	public OrderResultMessage create(NewOrderVO vo) throws RemoteException;
	/**
	 * 订单异常操作
	 * @param id
	 * 订单id
	 * @return OrderResultMessage
	 * 操作结果
	 * @throws RemoteException
	 */
	public OrderResultMessage abnormal(long orderId) throws RemoteException;
	/**
	 * 订单撤销操作
	 * @param id
	 * 订单id
	 * @return OrderResultMessage
	 * 操作结果
	 * @throws RemoteException
	 */
	public OrderResultMessage userRevoke(long orderId) throws RemoteException;
	/**
	 * 订单执行操作
	 * @param id
	 * 订单id
	 * @return OrderResultMessage
	 * 操作结果
	 * @throws RemoteException
	 */
	public OrderResultMessage execute(long orderId) throws RemoteException;
	/**
	 * 订单执行操作
	 * @param id
	 * 订单id
	 * @return OrderResultMessage
	 * 操作结果
	 * @throws RemoteException
	 */
	public OrderResultMessage reExecute(long orderId) throws RemoteException;
	/**
	 * 订单补执行操作
	 * @param id
	 * 订单id
	 * @return OrderResultMessage
	 * 操作结果
	 * @throws RemoteException
	 */
	public OrderResultMessage webRevoke(long orderId,int rank) throws RemoteException;
}
