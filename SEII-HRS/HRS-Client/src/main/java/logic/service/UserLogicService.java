package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import resultmessage.RegisterResultMessage;
import resultmessage.UserResultMessage;
import vo.LoginResultVO;

public interface UserLogicService extends Remote{
	/**
	 * �û���¼ʱ����֤����
	 * @param username
	 * ��¼���û���
	 * @param password
	 * ��¼������
	 * @return LoginResultVO
	 * ��¼�ɹ���VO��resultmessageΪ�ɹ�����������Ӧ���û����ͺ�id���Ƶ깤����Ա���᷵�ؾƵ�id
	 * ��¼ʧ�ܣ�VO��resultmessageΪʧ�ܣ�������ֵΪ��Ч��ֵ
	 * @throws RemoteException
	 */
	public LoginResultVO login(String username,String password) throws RemoteException;
	/**�û��ǳ��ķ������ڴ��ڹرպ��û�����ǳ�ͼ�꣬������ô˷���
	 * @param userid
	 * �û�id
	 * @throws RemoteException
	 */
	public void logout(long userid) throws RemoteException;
	/**�û�ע��ķ���
	 * @param username
	 * ע����û���
	 * @param password
	 * ע�������
	 * @return RegisterResultMessage
	 * ע���Ƿ�ɹ�����Ϣ
	 * @throws RemoteException
	 */
	public RegisterResultMessage register(String username,String password) throws RemoteException;
	/**
	 * �û��������������õķ���
	 * @param userId
	 * Ҫ����������û�id
	 * @param password
	 * Ҫ�����û������ԭ����
	 * @param newPassword
	 * Ҫ���ĵ�������
	 * @return UserResultMessage
	 * ���Ľ��
	 */
	public UserResultMessage changePassword(long userId,String password,String newPassword) throws RemoteException;
}
