package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.CreditResultMessage;
import vo.CreditVO;

public interface CreditLogicService extends Remote{
	/**
	 * ���ó�ֵ
	 * ��վӪ����Ա�������ó�ֵ���������õķ���
	 * @param userId
	 * ��Ҫ��ֵ�Ķ���
	 * �û�id
	 * @param delta
	 * �û������Ǯ��ʵ�ʳ�ֵ����Ϊdelta*100
	 * @return CreditResultMessage
	 * ��ֵ�ɹ���Ϣ
	 */
	public CreditResultMessage excharge(long userId, int delta)throws RemoteException;
	/**
	 * �ͻ��鿴�������ü�¼ʱ���ã������û������е����ü�¼
	 * @param userId
	 * �û�id
	 * @return ListWrapper<CreditVO>
	 * �û������ü�¼�б�
	 * @throws RemoteException
	 */
	public ListWrapper<CreditVO> getInfo(long userId) throws RemoteException;
}
