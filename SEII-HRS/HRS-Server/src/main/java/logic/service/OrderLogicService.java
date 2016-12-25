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
	 * �ͻ��鿴������ʱ�����õķ���
	 * @param userId
	 * �û�id
	 * @return ListWrapper<OrderVO> 
	 * �û������ж���
	 * @throws RemoteException
	 */
	public ListWrapper<OrderVO> getUserOrderInfo(long userId) throws RemoteException;
	/**
	 * �Ƶ깤����Ա�鿴������ʱ�����õķ���
	 * @param hotelId
	 * �Ƶ�id
	 * @return ListWrapper<OrderVO> 
	 * �Ƶ�����ж���
	 * @throws RemoteException
	 */
	public ListWrapper<OrderVO> getHotelOrderInfo(long hotelId) throws RemoteException;
	/**
	 * ��վӪ����Ա�鿴������ʱ�����õķ���
	 * @return ListWrapper<OrderVO> 
	 * ����������쳣�Ķ���
	 * @throws RemoteException
	 */
	public ListWrapper<OrderVO> getWEBOrderInfo() throws RemoteException;
	/**
	 * �ͻ����½�����ʱ�����õķ���
	 * @param vo
	 * �¶�����Ϣ
	 * @return OrderResultMessage
	 * �����ɹ��Ľ��
	 * @throws RemoteException
	 */
	public OrderResultMessage create(NewOrderVO vo) throws RemoteException;
	/**
	 * �����쳣����
	 * @param id
	 * ����id
	 * @return OrderResultMessage
	 * �������
	 * @throws RemoteException
	 */
	public OrderResultMessage abnormal(long orderId) throws RemoteException;
	/**
	 * ������������
	 * @param id
	 * ����id
	 * @return OrderResultMessage
	 * �������
	 * @throws RemoteException
	 */
	public OrderResultMessage userRevoke(long orderId) throws RemoteException;
	/**
	 * ����ִ�в���
	 * @param id
	 * ����id
	 * @return OrderResultMessage
	 * �������
	 * @throws RemoteException
	 */
	public OrderResultMessage execute(long orderId) throws RemoteException;
	/**
	 * ����ִ�в���
	 * @param id
	 * ����id
	 * @return OrderResultMessage
	 * �������
	 * @throws RemoteException
	 */
	public OrderResultMessage reExecute(long orderId) throws RemoteException;
	/**
	 * ������ִ�в���
	 * @param id
	 * ����id
	 * @return OrderResultMessage
	 * �������
	 * @throws RemoteException
	 */
	public OrderResultMessage webRevoke(long orderId,int rank) throws RemoteException;
}
