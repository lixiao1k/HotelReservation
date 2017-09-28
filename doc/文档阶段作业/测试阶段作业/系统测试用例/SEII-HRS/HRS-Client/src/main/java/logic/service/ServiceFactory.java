package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
/*
 * RMI 远程工厂对象，只在客户端需要用到某服务时才创建，节省服务器资源
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
