package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
/*
 * RMI Զ�̹�������ֻ�ڿͻ�����Ҫ�õ�ĳ����ʱ�Ŵ�������ʡ��������Դ
 */
public interface ServiceFactory extends Remote{
	public CommentLogicService getCommentLogicService() throws RemoteException;
	public CreditLogicService getCreditLogicService() throws RemoteException;
	public HotelLogicService getHotelLogicService() throws RemoteException;
	public MemberLogicService getMemberLogicService() throws RemoteException;
	public OrderLogicService getOrderLogicService() throws RemoteException;
	public StrategyLogicService getStrategyLogicService() throws RemoteException;
	public UserLogicService getUserLogicService() throws RemoteException;
}
