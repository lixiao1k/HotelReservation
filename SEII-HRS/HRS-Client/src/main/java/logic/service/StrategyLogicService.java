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
	 * ��ɾ���Ĳ���id
	 * @return StrategyResultMessage
	 * ɾ�����
	 * @throws RemoteException
	 */
	public StrategyResultMessage delete(long strategyId) throws RemoteException;
	/**
	 * @param StrategyVO vo
	 * �����������������������
	 * @return StrategyResultVO
	 * ������� �ɹ�����SUCCESS����Ӧ�������Ϣ��ʧ�ܷ���FAIL
	 * @throws RemoteException
	 */
	public StrategyResultVO create(StrategyVO vo) throws RemoteException;
	/**
	 * �Ƶ깤����Ա��ȡ����Ƶ궩�������õķ���
	 * @param hotelId
	 * ��ȡ�����б�ľƵ�ID
	 * @return ListWrapper<HotelStrategyVO>
	 * �Ƶ�����б�
	 * @throws RemoteException
	 */
	public ListWrapper<HotelStrategyVO> getStrategyList(long hotelId) throws RemoteException;
	/**
	 * ��������ȡ�ܲ��õĲ����б�
	 * �˷������¶���ʱѡ����Ӧ�Ƶ꣬���估����ʱ����
	 * @param OrderStrategy 
	 * �����Ķ������ݣ����������Щ���ݻ�ȡ�ܲ��õĲ���
	 * @return ListWrapper<HotelStrategyVO>
	 * �����ܲ��õĲ����б�
	 * �����Ƶ���Ժ���վӪ������
	 * @throws RemoteException
	 */
	public ListWrapper<HotelStrategyVO> getStrategyForOrder(OrderStrategy vo) throws RemoteException;
	/**
	 * ��������ʱ��Ҫ֪�����пɴ����Ĳ�������
	 * �˷����������в�������
	 * @return ListWrapper<StrategyType>
	 * ���еĲ�������
	 * @throws RemoteException
	 */
	public ListWrapper<StrategyType> getTypes() throws RemoteException;
	/**
	 * ��ȡ��վӪ����Ա�Ĳ����б�
	 * @return ListWrapper<HotelStrategyVO>
	 * @throws RemoteException
	 */
	public ListWrapper<HotelStrategyVO> getWEBStrategyList() throws RemoteException;
}
